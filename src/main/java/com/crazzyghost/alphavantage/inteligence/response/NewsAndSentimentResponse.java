package com.crazzyghost.alphavantage.inteligence.response;

import com.crazzyghost.alphavantage.parser.Parser;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class NewsAndSentimentResponse {
    private NewsAndSentiment newsAndSentiment;
    private String errorMessage;

    public NewsAndSentimentResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public NewsAndSentimentResponse(NewsAndSentiment newsAndSentiment) {
        this.newsAndSentiment = newsAndSentiment;
    }

    public NewsAndSentiment getNewsAndSentiment() {
        return newsAndSentiment;
    }

    public static NewsAndSentimentResponse of(Map<String, Object> data) {
        Parser<NewsAndSentimentResponse> parser = new NewsAndSentimentResponseParser();
        return parser.parse(data);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    private static class NewsAndSentimentResponseParser extends Parser<NewsAndSentimentResponse> {
        @Override
        public NewsAndSentimentResponse onParseError(String error) {
            return new NewsAndSentimentResponse(error);
        }

        @Override
        public NewsAndSentimentResponse parse(Map<String, Object> object) {
            try {
                return new NewsAndSentimentResponse(Parser.parseJSON(Parser.toJSON(object), NewsAndSentiment.class));
            } catch (IOException e) {
                return onParseError(e.getMessage());
            }
        }
    }

    @Override
    public String toString() {
        return "NewsAndSentimentResponse{" +
                "newsAndSentiment=" + newsAndSentiment +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
