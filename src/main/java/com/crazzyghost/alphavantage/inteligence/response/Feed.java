package com.crazzyghost.alphavantage.inteligence.response;

import com.squareup.moshi.Json;

import java.util.List;

public class Feed {
    private String title;
    private String url;
    @Json(name="time_published")
    private String timePublished;
    private List<String> authors;
    private String summary;
    @Json(name="banner_image")
    private String bannerImage;
    private String source;
    @Json(name="category_within_source")
    private String categoryWithinSource;
    @Json(name="source_domain")
    private String sourceDomain;
    private List<Topic> topics;
    @Json(name="overall_sentiment_score")
    private double overallSentimentScore;
    @Json(name="overall_sentiment_label")
    private String overallSentimentLabel;
    @Json(name="ticker_sentiment")
    private List<TickerSentiment> tickerSentiment;

    public String getTitle() { return title; }
    public void setTitle(String value) { this.title = value; }

    public String getURL() { return url; }
    public void setURL(String value) { this.url = value; }

    public String getTimePublished() { return timePublished; }
    public void setTimePublished(String value) { this.timePublished = value; }



    public String getSummary() { return summary; }
    public void setSummary(String value) { this.summary = value; }

    public String getBannerImage() { return bannerImage; }
    public void setBannerImage(String value) { this.bannerImage = value; }

    public String getSource() { return source; }
    public void setSource(String value) { this.source = value; }

    public String getCategoryWithinSource() { return categoryWithinSource; }
    public void setCategoryWithinSource(String value) { this.categoryWithinSource = value; }

    public String getSourceDomain() { return sourceDomain; }
    public void setSourceDomain(String value) { this.sourceDomain = value; }



    public double getOverallSentimentScore() { return overallSentimentScore; }
    public void setOverallSentimentScore(double value) { this.overallSentimentScore = value; }

    public String getOverallSentimentLabel() { return overallSentimentLabel; }
    public void setOverallSentimentLabel(String value) { this.overallSentimentLabel = value; }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public List<TickerSentiment> getTickerSentiment() {
        return tickerSentiment;
    }

    public void setTickerSentiment(List<TickerSentiment> tickerSentiment) {
        this.tickerSentiment = tickerSentiment;
    }

    @Override
    public String toString() {
        return "Feed{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", timePublished='" + timePublished + '\'' +
                ", authors=" + authors +
                ", summary='" + summary + '\'' +
                ", bannerImage='" + bannerImage + '\'' +
                ", source='" + source + '\'' +
                ", categoryWithinSource='" + categoryWithinSource + '\'' +
                ", sourceDomain='" + sourceDomain + '\'' +
                ", topics=" + topics +
                ", overallSentimentScore=" + overallSentimentScore +
                ", overallSentimentLabel='" + overallSentimentLabel + '\'' +
                ", tickerSentiment=" + tickerSentiment +
                '}';
    }
}
