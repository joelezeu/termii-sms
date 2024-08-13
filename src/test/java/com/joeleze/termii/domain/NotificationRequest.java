package com.joeleze.termii.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class NotificationRequestTest {

    @Test
    void testBuilderCreatesNotificationRequestWithCorrectValues() {
        // Arrange
        String expectedTo = "recipient@example.com";
        String expectedFrom = "sender@example.com";
        String expectedSms = "Hello, this is a test message!";
        String expectedType = "SMS";
        String expectedChannel = "email";
        String expectedApiKey = "123456";
        MediaData expectedMedia = new MediaData.Builder()
                .url("https://example.com/media.jpg")
                .caption("Test Media")
                .build();

        // Act
        NotificationRequest request = new NotificationRequest.Builder()
                .to(expectedTo)
                .from(expectedFrom)
                .sms(expectedSms)
                .type(expectedType)
                .channel(expectedChannel)
                .apiKey(expectedApiKey)
                .media(expectedMedia)
                .build();

        // Assert
        assertNotNull(request, "NotificationRequest should not be null");
        assertEquals(expectedTo, request.getTo(), "To address should match the expected value");
        assertEquals(expectedFrom, request.getFrom(), "From address should match the expected value");
        assertEquals(expectedSms, request.getSms(), "SMS content should match the expected value");
        assertEquals(expectedType, request.getType(), "Type should match the expected value");
        assertEquals(expectedChannel, request.getChannel(), "Channel should match the expected value");
        assertEquals(expectedApiKey, request.getApiKey(), "API Key should match the expected value");
        assertEquals(expectedMedia, request.getMedia(), "Media should match the expected value");
    }

    @Test
    void testBuilderCreatesNotificationRequestWithDefaultValues() {
        // Arrange
        String expectedTo = "recipient@example.com";
        String expectedFrom = "sender@example.com";
        String expectedSms = "Hello, this is a test message!";
        String expectedApiKey = "123456";

        // Act
        NotificationRequest request = new NotificationRequest.Builder()
                .to(expectedTo)
                .from(expectedFrom)
                .sms(expectedSms)
                .apiKey(expectedApiKey)
                .build();

        // Assert
        assertNotNull(request, "NotificationRequest should not be null");
        assertEquals(expectedTo, request.getTo(), "To address should match the expected value");
        assertEquals(expectedFrom, request.getFrom(), "From address should match the expected value");
        assertEquals(expectedSms, request.getSms(), "SMS content should match the expected value");
        assertEquals("plain", request.getType(), "Default type should be 'plain'");
        assertEquals("generic", request.getChannel(), "Default channel should be 'generic'");
        assertEquals(expectedApiKey, request.getApiKey(), "API Key should match the expected value");
        assertEquals(null, request.getMedia(), "Media should be null");
    }

    @Test
    void testBuilderAllowsPartialInitialization() {
        // Arrange
        String expectedTo = "recipient@example.com";
        String expectedFrom = "sender@example.com";

        // Act
        NotificationRequest request = new NotificationRequest.Builder()
                .to(expectedTo)
                .from(expectedFrom)
                .build();

        // Assert
        assertNotNull(request, "NotificationRequest should not be null");
        assertEquals(expectedTo, request.getTo(), "To address should match the expected value");
        assertEquals(expectedFrom, request.getFrom(), "From address should match the expected value");
        assertEquals(null, request.getSms(), "SMS content should be null");
        assertEquals("plain", request.getType(), "Default type should be 'plain'");
        assertEquals("generic", request.getChannel(), "Default channel should be 'generic'");
        assertEquals(null, request.getApiKey(), "API Key should be null");
        assertEquals(null, request.getMedia(), "Media should be null");
    }
}
