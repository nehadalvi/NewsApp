package com.mad.newsapp;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by neha5 on 07-02-2017.
 */

public class GetData extends AsyncTask<String, Void, ArrayList<News>> {
    IData activity;

    public GetData(IData activity) {
        this.activity = activity;
    }

    @Override
    protected ArrayList<News> doInBackground(String... params) {

        try {
            URL url = new URL(params[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line=br.readLine();
            StringBuilder sb=new StringBuilder();
            while(line!=null){
                sb.append(line);
                line=br.readLine();
            }
            Log.d("demo","sb="+sb.toString());
            return NewsParser.NewsJSONParser.parseNewsData(sb.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<News> news) {
        super.onPostExecute(news);

        activity.sendData(news);
    }

    public static interface IData{
        public void sendData(ArrayList<News> news);
    }
}
