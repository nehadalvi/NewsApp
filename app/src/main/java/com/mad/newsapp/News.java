package com.mad.newsapp;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by neha5 on 06-02-2017.
 */

public class News {

    String urlToImage,title,author,description,publishedAt;


    SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd");


    public static News createNews(JSONObject newsObject) throws JSONException {
        News news = new News();
        news.setAuthor(newsObject.getString("author"));
        news.setDescription(newsObject.getString("description"));
        news.setTitle(newsObject.getString("title"));
        news.setUrlToImage(newsObject.getString("urlToImage"));
        news.setPublishedAt((newsObject.getString("publishedAt")));
        return news;
    }


    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }
}
