package com.joeleze.termii.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NotificationResponseTest {

    private NotificationResponse response;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        response = new NotificationResponse();
    }

    @Test
    void testGettersAndSetters() {
        // Arrange
        String expectedCode = "200";
        String expectedBalance = "100.00";
        String expectedMessageId = "msg-12345";
        String expectedMessage = "Success";
        String expectedUser = "testUser";

        // Act
        response.setCode(expectedCode);
        response.setBalance(expectedBalance);
        response.setMessageId(expectedMessageId);
        response.setMessage(expectedMessage);
        response.setUser(expectedUser);

        // Assert
        assertEquals(expectedCode, response.getCode(), "Code should match the expected value");
        assertEquals(expectedBalance, response.getBalance(), "Balance should match the expected value");
        assertEquals(expectedMessageId, response.getMessageId(), "Message ID should match the expected value");
        assertEquals(expectedMessage, response.getMessage(), "Message should match the expected value");
        assertEquals(expectedUser, response.getUser(), "User should match the expected value");
    }

    @Test
    void testJsonSerializationAndDeserialization() throws JsonProcessingException {
        // Arrange
        String expectedCode = "200";
        String expectedBalance = "100.00";
        String expectedMessageId = "msg-12345";
        String expectedMessage = "Success";
        String expectedUser = "testUser";

        response.setCode(expectedCode);
        response.setBalance(expectedBalance);
        response.setMessageId(expectedMessageId);
        response.setMessage(expectedMessage);
        response.setUser(expectedUser);

        // Act
        String json = objectMapper.writeValueAsString(response);
        NotificationResponse deserializedResponse = objectMapper.readValue(json, NotificationResponse.class);

        // Assert
        assertEquals(response.getCode(), deserializedResponse.getCode(), "Deserialized code should match original");
        assertEquals(response.getBalance(), deserializedResponse.getBalance(), "Deserialized balance should match original");
        assertEquals(response.getMessageId(), deserializedResponse.getMessageId(), "Deserialized messageId should match original");
        assertEquals(response.getMessage(), deserializedResponse.getMessage(), "Deserialized message should match original");
        assertEquals(response.getUser(), deserializedResponse.getUser(), "Deserialized user should match original");
    }

    @Test
    void testJsonPropertyMessageId() throws JsonProcessingException {
        // Arrange
        String json = "{\"code\":\"200\",\"balance\":\"100.00\",\"message_id\":\"msg-12345\",\"message\":\"Success\",\"user\":\"testUser\"}";

        // Act
        NotificationResponse deserializedResponse = objectMapper.readValue(json, NotificationResponse.class);

        // Assert
        assertEquals("msg-12345", deserializedResponse.getMessageId(), "Deserialized 'message_id' should match expected value");
    }
}

