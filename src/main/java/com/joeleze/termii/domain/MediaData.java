package com.joeleze.termii.domain;

public class MediaData {
    private final String url;
    private final String caption;

    private MediaData(Builder builder) {
        this.url = builder.url;
        this.caption = builder.caption;
    }

    public static class Builder {
        private String url;
        private String caption;

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder caption(String caption) {
            this.caption = caption;
            return this;
        }

        public MediaData build() {
            return new MediaData(this);
        }
    }

    // Getters for the fields, if needed
    public String getUrl() {
        return url;
    }

    public String getCaption() {
        return caption;
    }
}
