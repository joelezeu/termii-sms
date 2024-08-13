package com.joeleze.termii.services;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joeleze.termii.domain.NotificationRequest;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class NotificationService {
    private final String API_ENDPOINT;
    private final HttpClient httpClient;

    public NotificationService(String endpoint) {
        this.API_ENDPOINT = endpoint;
        // Create an HttpClient with connection pooling and HTTP/2 support
        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)  // Enable HTTP/2
                .connectTimeout(Duration.ofSeconds(10))  // Set connection timeout
                .build();
    }

    public String sendNotification(NotificationRequest request) throws Exception {
        String inputJson = toJson(request);
        System.out.println("Sending request: " + inputJson);
        // Create an HttpRequest with POST method and JSON body
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(API_ENDPOINT))
                .header("Content-Type", "application/json")
                .timeout(Duration.ofSeconds(30))  // Set request timeout
                .POST(HttpRequest.BodyPublishers.ofString(inputJson))
                .build();

        // Send the request asynchronously
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        // Check the response status code
        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.statusCode());
        }
        return response.body();
    }

    private String toJson(NotificationRequest request) {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            return objectMapper.writeValueAsString(request);
        }catch (Exception ignore){
        }
        return null;
    }
}
