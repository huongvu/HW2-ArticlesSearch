package com.example.huongvu.nytarticlesearch.interfaces;

import com.example.huongvu.nytarticlesearch.models.ArticleSearch;

/**
 * Created by HUONGVU on 3/21/2016.
 */
public interface AdvanceSettingListener {
    // These methods are the different events and
    // need to pass relevant arguments related to the event triggered
    public void onFinishSetting(ArticleSearch dataSetting);
}