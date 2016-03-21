package com.example.huongvu.nytarticlesearch.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by HUONGVU on 3/20/2016.
 */
public class Article {
    @SerializedName("web_url")
    private String articleUrl;

    public String getArticleUrl() {
        return articleUrl;
    }

    public String getArticleId() {
        return articleId;
    }

    public headline getArticleHeadline() {
        return articleHeadline;
    }

    @SerializedName("_id")
    private String articleId;

    @SerializedName("headline")
    private headline articleHeadline;

    public class headline{

        @SerializedName("main")
        private String headlineInfor;

        public String getHeadline() {
            return headlineInfor;
        }

    }


    @SerializedName("multimedia")
    private ArrayList<Multimedia> imageUrlList;

    public ArrayList<Multimedia> getImageUrlList() {
        return imageUrlList;
    }

    public class Multimedia {

        public String getImageURL() {
            return imageURL;
        }

        @SerializedName("url")
        private String imageURL;


    }

    public String getThumbnailUrl() {
        for (Multimedia thumbnail:imageUrlList
             ) {
            if(thumbnail.getImageURL().contains("thumb")){
                thumbnailUrl = "http://www.nytimes.com/" + thumbnail.getImageURL();
                return thumbnailUrl;
            }
            return  null;
        }
        return null;
    }

    private String thumbnailUrl;

}
