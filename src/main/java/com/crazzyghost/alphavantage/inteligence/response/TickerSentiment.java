package com.crazzyghost.alphavantage.inteligence.response;

import com.squareup.moshi.Json;

public class TickerSentiment {
    private String ticker;
    @Json(name="relevance_score")
    private String relevanceScore;
    @Json(name="ticker_sentiment_score")
    private String tickerSentimentScore;
    @Json(name="ticker_sentiment_label")
    private String tickerSentimentLabel;

    public String getTicker() { return ticker; }
    public void setTicker(String value) { this.ticker = value; }

    public String getRelevanceScore() { return relevanceScore; }
    public void setRelevanceScore(String value) { this.relevanceScore = value; }

    public String getTickerSentimentScore() { return tickerSentimentScore; }
    public void setTickerSentimentScore(String value) { this.tickerSentimentScore = value; }

    public String getTickerSentimentLabel() { return tickerSentimentLabel; }
    public void setTickerSentimentLabel(String value) { this.tickerSentimentLabel = value; }

    @Override
    public String toString() {
        return "TickerSentiment{" +
                "ticker='" + ticker + '\'' +
                ", relevanceScore='" + relevanceScore + '\'' +
                ", tickerSentimentScore='" + tickerSentimentScore + '\'' +
                ", tickerSentimentLabel='" + tickerSentimentLabel + '\'' +
                '}';
    }
}