package com.example.huongvu.nytarticlesearch.models;

import android.text.TextUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HUONGVU on 3/20/2016.
 */
public class ArticleDeserializer implements JsonDeserializer<List<Article>> {
    @Override
    public List<Article> deserialize(JsonElement json, Type typeOfT,
                                        JsonDeserializationContext context) throws JsonParseException {

        ArrayList<Article> multimedias = new ArrayList<>();

        String str = null;

        try {
            str = json.getAsString();
        } catch (IllegalStateException e) {

        }

        if (str != null && TextUtils.isEmpty(str)) {
            return multimedias;
        } else {
            JsonArray jsonArray = json.getAsJsonArray();
            for (int i = 0; i < jsonArray.size(); i++) {
                multimedias.add((Article) context.deserialize(jsonArray.get(i), Article.class));
            }
        }
        return multimedias;
    }
}
