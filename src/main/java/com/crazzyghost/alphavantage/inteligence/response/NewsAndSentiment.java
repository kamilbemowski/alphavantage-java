package com.crazzyghost.alphavantage.inteligence.response;

import com.squareup.moshi.Json;

import java.util.List;

public class NewsAndSentiment {

    @Json(name="Information")
    private String information;
    private String items;
    @Json(name="sentiment_score_definition")
    private String sentimentScoreDefinition;
    @Json(name="relevance_score_definition")
    private String relevanceScoreDefinition;
    private List<Feed> feed;

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getSentimentScoreDefinition() {
        return sentimentScoreDefinition;
    }

    public void setSentimentScoreDefinition(String sentimentScoreDefinition) {
        this.sentimentScoreDefinition = sentimentScoreDefinition;
    }

    public String getRelevanceScoreDefinition() {
        return relevanceScoreDefinition;
    }

    public void setRelevanceScoreDefinition(String relevanceScoreDefinition) {
        this.relevanceScoreDefinition = relevanceScoreDefinition;
    }

    public List<Feed> getFeed() {
        return feed;
    }

    public void setFeed(List<Feed> feed) {
        this.feed = feed;
    }

    @Override
    public String toString() {
        return "NewsAndSentiment{" +
                "items='" + items + '\'' +
                ", sentimentScoreDefinition='" + sentimentScoreDefinition + '\'' +
                ", relevanceScoreDefinition='" + relevanceScoreDefinition + '\'' +
                ", feed=" + feed +
                '}';
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
