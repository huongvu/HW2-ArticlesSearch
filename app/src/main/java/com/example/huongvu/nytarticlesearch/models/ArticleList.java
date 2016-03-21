package com.example.huongvu.nytarticlesearch.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HUONGVU on 3/20/2016.
 */
public class ArticleList {

    public List<Article> getArticlesList() {
        return articlesList;
    }

    @SerializedName("docs")
    public List<Article> articlesList;

    public ArticleList() {
        articlesList = new ArrayList<Article>();
    }
}

