package com.joeleze.termii.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

public class NotificationRequest {
    private final String to;
    private final String from;
    private final String sms;
    private final String type;
    private final String channel;
    @JsonProperty("api_key")
    private final String apiKey;
    private final MediaData media;

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

    public String getSms() {
        return sms;
    }

    public String getType() {
        return type;
    }

    public String getChannel() {
        return channel;
    }

    public String getApiKey() {
        return apiKey;
    }

    public MediaData getMedia() {
        return media;
    }

    private NotificationRequest(Builder builder) {
        this.to = builder.to;
        this.from = builder.from;
        this.sms = builder.sms;
        this.type = builder.type;
        this.channel = builder.channel;
        this.apiKey = builder.apiKey;
        this.media = builder.media;
    }

    public static class Builder {
        private String to;
        private String from;
        private String sms;
        private String type = "plain"; // default value
        private String channel = "generic"; // default value
        @JsonProperty("api_key")
        private String apiKey;
        private MediaData media;

        public Builder to(String to) {
            this.to = to;
            return this;
        }

        public Builder from(String from) {
            this.from = from;
            return this;
        }

        public Builder sms(String sms) {
            this.sms = sms;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder channel(String channel) {
            this.channel = channel;
            return this;
        }

        public Builder apiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        public Builder media(MediaData media) {
            this.media = media;
            return this;
        }

        public NotificationRequest build() {
            return new NotificationRequest(this);
        }
    }
}
