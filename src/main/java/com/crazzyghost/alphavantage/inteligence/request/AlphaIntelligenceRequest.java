package com.crazzyghost.alphavantage.inteligence.request;

import com.crazzyghost.alphavantage.parameters.Function;
import com.squareup.moshi.Json;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class AlphaIntelligenceRequest {

    protected Function function;
    protected String tickers;
    protected int limit;
    @Json(name="time_from")
    protected String time_from;
    @Json(name="time_to")
    protected String time_to;
    protected String sort;

    protected AlphaIntelligenceRequest(Builder<?> builder) {
        this.function = builder.function;
        this.tickers = builder.tickers;
        this.limit = builder.limit;
        this.time_from = builder.timeFrom;
        this.time_to = builder.timeTo;
        this.sort = builder.sort;
    }


    public abstract static class Builder <T extends Builder<?>> {

        private String sort;
        private String timeTo;
        private String timeFrom;
        private String tickers;
        public Function function;
        private int limit;
        private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");

        public T tickers(String tickers){
            this.tickers = tickers;
            return (T) this;
        }

        public T function(Function function){
            this.function = function;
            return (T) this;
        }

        public T limit(int limit) {
            this.limit = limit;
            return (T) this;
        }

        public T timeFrom(LocalDateTime timeFrom) {
            this.timeFrom = timeFrom.format(DATE_TIME_FORMATTER);
            return (T) this;
        }

        public T timeTo(LocalDateTime timeTo) {
            this.timeTo = timeTo.format(DATE_TIME_FORMATTER);
            return (T) this;
        }

        public T sort(String sort) {
            this.sort = sort;
            return (T) this;
        }

        public abstract AlphaIntelligenceRequest build();


    }
}