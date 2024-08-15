package com.joeleze.termii.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joeleze.termii.domain.NotificationRequest;
import com.joeleze.termii.domain.NotificationResponse;
import com.joeleze.termii.services.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class NotificationServiceTest {
    @Mock
    private NotificationService notificationService;
    private HttpClient httpClientMock;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        // Initialize the mocks
        MockitoAnnotations.openMocks(this);
        httpClientMock = mock(HttpClient.class);
        objectMapper = new ObjectMapper();
        notificationService = new NotificationService("https://v3.api.termii.com/api/sms/send");
    }

    @Test
    void testSendNotificationSuccess() throws Exception {
        // Arrange
        NotificationRequest notificationRequest = new NotificationRequest.Builder()
                .to("recipient@example.com")
                .from("sender@example.com")
                .sms("Hello, this is a test message!")
                .apiKey("your-api-key")
                .build();

        NotificationResponse expectedResponse = new NotificationResponse();

        HttpResponse<String> httpResponseMock = mock(HttpResponse.class);
        when(httpResponseMock.statusCode()).thenReturn(200);
        when(httpResponseMock.body()).thenReturn(objectMapper.writeValueAsString(expectedResponse));

        when(httpClientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(httpResponseMock);

        // Act
        NotificationResponse actualResponse = notificationService.sendNotification(notificationRequest);

        // Assert
        assertNotNull(actualResponse);
        //assertEquals(expectedResponse.getStatus(), actualResponse.getStatus());

        // Verify that the correct HTTP request was made
        ArgumentCaptor<HttpRequest> requestCaptor = ArgumentCaptor.forClass(HttpRequest.class);
        verify(httpClientMock).send(requestCaptor.capture(), any(HttpResponse.BodyHandler.class));

        HttpRequest capturedRequest = requestCaptor.getValue();
        assertEquals("POST", capturedRequest.method());
        assertEquals("application/json", capturedRequest.headers().firstValue("Content-Type").orElse(""));
        assertEquals("http://example.com/api/notify", capturedRequest.uri().toString());
        assertEquals(objectMapper.writeValueAsString(notificationRequest), capturedRequest.bodyPublisher().get().toString());
    }

    @Test
    void testSendNotificationFailure() throws Exception {
        // Arrange
        NotificationRequest notificationRequest = new NotificationRequest.Builder()
                .to("recipient@example.com")
                .from("sender@example.com")
                .sms("Hello, this is a test message!")
                .apiKey("your-api-key")
                .build();

        HttpResponse<String> httpResponseMock = mock(HttpResponse.class);
        when(httpResponseMock.statusCode()).thenReturn(401);
        when(httpClientMock.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(httpResponseMock);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                notificationService.sendNotification(notificationRequest));

        assertEquals("Failed : HTTP error code : 401", exception.getMessage());
    }
}

