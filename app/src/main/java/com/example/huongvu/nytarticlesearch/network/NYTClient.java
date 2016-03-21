package com.example.huongvu.nytarticlesearch.network;

import com.example.huongvu.nytarticlesearch.models.ArticleSearch;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by HUONGVU on 3/19/2016.
 */
public class NYTClient {
    private static final String API_BASE_URL = "http://api.nytimes.com/svc/search/v2/articlesearch.json?";
    private static final String API_KEY = "53fe4a35a91c2ccc5be81bdfcb10360e:16:74717665";
    private AsyncHttpClient client;


    public NYTClient() {
        this.client = new AsyncHttpClient();
        client.setTimeout(20 * 1000); // Increase default timeout
    }

    private String getApiUrl() {
        return API_BASE_URL;
    }

    // Method for accessing the search API
    public void getArticles(final String query, ArticleSearch paraSearch, JsonHttpResponseHandler handler) {
            String url = getApiUrl();
            RequestParams params = new RequestParams();

            params.put("api-key", API_KEY);
            params.put("q", query);
            if(paraSearch.getNewDeskValues()!= null){params.put("fq", "news_desk:("+ paraSearch.getNewDeskValues() +")");}
            params.put("sort",paraSearch.getSortOder());

            client.get(url,params,handler);
    }
}
