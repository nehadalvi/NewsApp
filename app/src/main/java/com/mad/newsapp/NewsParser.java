package com.mad.newsapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by neha5 on 06-02-2017.
 */

public class NewsParser {

    static class NewsJSONParser{


        public static ArrayList<News> parseNewsData(String json) throws JSONException {

            ArrayList<News> newsArrayList=new ArrayList<News>();
            JSONObject articlesObject=new JSONObject(json);
            JSONArray articlesArray=articlesObject.getJSONArray("articles");

            for(int i=0;i<articlesArray.length();i++){
                JSONObject newsObject = articlesArray.getJSONObject(i);
                News news = News.createNews(newsObject);
                newsArrayList.add(news);
            }
            return newsArrayList;
        }
    }
}
