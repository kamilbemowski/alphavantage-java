package com.crazzyghost.alphavantage.inteligence;

import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.Fetcher;
import com.crazzyghost.alphavantage.UrlExtractor;
import com.crazzyghost.alphavantage.cryptocurrency.Crypto;
import com.crazzyghost.alphavantage.inteligence.request.AlphaIntelligenceRequest.Builder;
import com.crazzyghost.alphavantage.inteligence.request.NewsAndSentimentRequest;
import com.crazzyghost.alphavantage.inteligence.response.NewsAndSentimentResponse;
import com.crazzyghost.alphavantage.parser.Parser;
import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class AlphaIntelligence implements Fetcher {
    private final Config config;

    private Builder<?> builder;
    private Fetcher.SuccessCallback<?> successCallback;
    private Fetcher.FailureCallback failureCallback;

    public AlphaIntelligence(Config config) {
        this.config = config;
    }

    public NewsAndSentimentRequestProxy newsAndSentiment() {
        return new NewsAndSentimentRequestProxy();
    }

    @Override
    public void fetch() {
        Config.checkNotNullOrKeyEmpty(config);

        config.getOkHttpClient().newCall(UrlExtractor.extract(builder.build(), config.getKey())).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (failureCallback != null) failureCallback.onFailure(new AlphaVantageException());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    if (failureCallback != null) failureCallback.onFailure(new AlphaVantageException());
                } else {
                    try (ResponseBody body = response.body()) {
                        parseAlphaIntelligenceResponse(Parser.parseJSON(body.string()));
                    }
                }
            }
        });
    }

    public class NewsAndSentimentRequestProxy extends RequestProxy<NewsAndSentimentRequestProxy, NewsAndSentimentResponse> {
        public NewsAndSentimentRequestProxy() {
            builder = new NewsAndSentimentRequest.Builder();
        }


    }

    @SuppressWarnings("unchecked")
    public abstract class RequestProxy<Proxy extends AlphaIntelligence.RequestProxy<?, ProxyResponse>, ProxyResponse> {
        protected Builder<?> builder;
        protected ProxyResponse syncResponse;

        private RequestProxy() {
        }

        public Proxy forTickers(List<String> symbol) {
            this.builder.tickers(String.join(",", symbol));
            return (Proxy) this;
        }

        public Proxy forLimit(int limit) {
            this.builder.limit(limit);
            return (Proxy) this;
        }

        public Proxy forTimeFrom(LocalDateTime timeFrom) {
            this.builder.timeFrom(timeFrom);
            return (Proxy) this;
        }

        public Proxy forTimeTo(LocalDateTime timeTo) {
            this.builder.timeTo(timeTo);
            return (Proxy) this;
        }

        public Proxy forSort(String sort) {
            this.builder.sort(sort);
            return (Proxy) this;
        }

        public Proxy onSuccess(SuccessCallback<?> callback) {
            AlphaIntelligence.this.successCallback = callback;
            return (Proxy) this;
        }

        public Proxy onFailure(FailureCallback callback) {
            AlphaIntelligence.this.failureCallback = callback;
            return (Proxy) this;
        }

        public void fetch() {
            AlphaIntelligence.this.builder = this.builder;
            AlphaIntelligence.this.fetch();
        }

        public void setSyncResponse(ProxyResponse response) {
            this.syncResponse = response;
        }


        /**
         * Set the right builder and make a synchronous request using {@link Crypto#fetch()}
         * When calling this method, any async callbacks will be overwritten
         *
         * @return The api response
         * @throws AlphaVantageException exception during call
         */
        public ProxyResponse fetchSync() throws AlphaVantageException {
            SuccessCallback<ProxyResponse> callback = this::setSyncResponse;
            AlphaIntelligence.this.builder = this.builder;
            AlphaIntelligence.this.fetchSync(callback);
            return this.syncResponse;
        }

    }

    /**
     * Make a blocking synchronous http request to fetch the data.
     * This will be called by the {@link AlphaIntelligence.RequestProxy#fetchSync()}.
     * <p>
     * Using this method will overwrite any async callback
     *
     * @param successCallback internally used {@link SuccessCallback}
     * @throws AlphaVantageException exception thrown
     * @since 1.6.0
     */
    private <ProxyResponse> void fetchSync(SuccessCallback<ProxyResponse> successCallback) {

        Config.checkNotNullOrKeyEmpty(config);

        this.successCallback = successCallback;
        this.failureCallback = null;
        okhttp3.OkHttpClient client = config.getOkHttpClient();
        try (Response response = client.newCall(UrlExtractor.extract(builder.build(), config.getKey())).execute()) {
            parseAlphaIntelligenceResponse(Parser.parseJSON(response.body().string()));
        } catch (IOException e) {
            throw new AlphaVantageException(e.getMessage());
        }
    }

    private void parseAlphaIntelligenceResponse(Map<String, Object> data) {
        switch (builder.function) {
            case NEWS_SENTIMENT:
                parseNewsAndSentimentResponse(data);
                break;
            default:
                break;
        }
    }

    private void parseNewsAndSentimentResponse(Map<String, Object> data) {
        NewsAndSentimentResponse response = NewsAndSentimentResponse.of(data);
        if (response.getErrorMessage() != null && failureCallback != null) {
            failureCallback.onFailure(new AlphaVantageException(response.getErrorMessage()));
        }
        if (successCallback != null) {
            ((Fetcher.SuccessCallback<NewsAndSentimentResponse>) successCallback).onSuccess(response);
        }
    }

}
