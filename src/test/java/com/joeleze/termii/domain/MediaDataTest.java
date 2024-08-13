package com.joeleze.termii.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MediaDataTest {
    @Test
    void testBuilderCreatesMediaDataWithCorrectValues() {
        // Arrange
        String expectedUrl = "https://example.com/image.jpg";
        String expectedCaption = "Example Caption";

        // Act
        MediaData mediaData = new MediaData.Builder()
                .url(expectedUrl)
                .caption(expectedCaption)
                .build();

        // Assert
        assertNotNull(mediaData, "MediaData should not be null");
        assertEquals(expectedUrl, mediaData.getUrl(), "URL should match the expected value");
        assertEquals(expectedCaption, mediaData.getCaption(), "Caption should match the expected value");
    }

    @Test
    void testBuilderCreatesMediaDataWithNullValues() {
        // Act
        MediaData mediaData = new MediaData.Builder()
                .url(null)
                .caption(null)
                .build();

        // Assert
        assertNotNull(mediaData, "MediaData should not be null");
        assertEquals(null, mediaData.getUrl(), "URL should be null");
        assertEquals(null, mediaData.getCaption(), "Caption should be null");
    }

    @Test
    void testBuilderAllowsPartialInitialization() {
        // Arrange
        String expectedUrl = "https://example.com/image.jpg";

        // Act
        MediaData mediaData = new MediaData.Builder()
                .url(expectedUrl)
                .build();

        // Assert
        assertNotNull(mediaData, "MediaData should not be null");
        assertEquals(expectedUrl, mediaData.getUrl(), "URL should match the expected value");
        assertEquals(null, mediaData.getCaption(), "Caption should be null");
    }
}
