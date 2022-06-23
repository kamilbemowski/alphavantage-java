package com.crazzyghost.alphavantage.inteligence.response;

import com.squareup.moshi.Json;

public class Topic {
    private String topic;
    @Json(name="relevance_score")
    private String relevanceScore;

    public String getTopic() { return topic; }
    public void setTopic(String value) { this.topic = value; }

    public String getRelevanceScore() { return relevanceScore; }
    public void setRelevanceScore(String value) { this.relevanceScore = value; }

    @Override
    public String toString() {
        return "Topic{" +
                "topic='" + topic + '\'' +
                ", relevanceScore='" + relevanceScore + '\'' +
                '}';
    }
}