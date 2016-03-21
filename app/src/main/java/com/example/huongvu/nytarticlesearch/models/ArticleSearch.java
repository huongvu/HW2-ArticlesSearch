package com.example.huongvu.nytarticlesearch.models;

/**
 * Created by HUONGVU on 3/21/2016.
 */
public class ArticleSearch {
    private String sortOder;

    public String getSortOder() {
        return sortOder;
    }

    public void setSortOder(String sortOder) {
        this.sortOder = sortOder;
    }

    public String getNewDeskValues() {
        return newDeskValues;
    }

    public void setNewDeskValues(String newDeskValues) {
        this.newDeskValues = newDeskValues;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String newDeskValues;
    private String date;
}
