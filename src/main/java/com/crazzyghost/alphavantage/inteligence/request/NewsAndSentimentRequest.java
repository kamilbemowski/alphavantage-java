package com.crazzyghost.alphavantage.inteligence.request;

import com.crazzyghost.alphavantage.parameters.Function;

public class NewsAndSentimentRequest extends AlphaIntelligenceRequest {

    protected NewsAndSentimentRequest(Builder builder) { super(builder); }

    public static class Builder extends AlphaIntelligenceRequest.Builder<Builder> {

        public Builder() {
            this.function(Function.NEWS_SENTIMENT);
        }

        @Override
        public NewsAndSentimentRequest build() {
            return new NewsAndSentimentRequest(this);
        }
    }
}