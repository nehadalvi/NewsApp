package com.mad.newsapp;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements GetData.IData,GetImage.IImage{
    int index=0;
    Spinner spinner;
    String selection;
    ArrayList<News> newsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String baseUrl ="https://newsapi.org/v1/articles?apiKey=fd94eeb61bac4686a4c92b11c84fc84c&source=";
        findViewById(R.id.btn_finish).setEnabled(false);
        findViewById(R.id.btn_first).setEnabled(false);
        findViewById(R.id.btn_previous).setEnabled(false);
        findViewById(R.id.btn_next).setEnabled(false);
        findViewById(R.id.btn_last).setEnabled(false);

        spinner = (Spinner) findViewById(R.id.spinnerGenre);



        findViewById(R.id.btn_get_news).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selection = spinner.getSelectedItem().toString();
                index=0;
                switch (selection){
                    case "BBC": new GetData(MainActivity.this).execute(baseUrl+"bbc-news");
                        break;
                    case "CNN": new GetData(MainActivity.this).execute(baseUrl+"cnn");
                        break;
                    case "Buzzfeed": new GetData(MainActivity.this).execute(baseUrl+"buzzfeed");
                        break;
                    case "ESPN": new GetData(MainActivity.this).execute(baseUrl+"espn");
                        break;
                    case "Sky News": new GetData(MainActivity.this).execute(baseUrl+"sky-news");
                        break;
                    case "Select": new GetData(MainActivity.this).execute(baseUrl+"bbc-news");
                }

                findViewById(R.id.btn_finish).setEnabled(true);
                findViewById(R.id.btn_first).setEnabled(true);
                findViewById(R.id.btn_previous).setEnabled(true);
                findViewById(R.id.btn_next).setEnabled(true);
                findViewById(R.id.btn_last).setEnabled(true);
            }
        });


        findViewById(R.id.btn_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index==0){
                    Toast.makeText(MainActivity.this,"First news already displayed",Toast.LENGTH_SHORT).show();
                }else{
                    index=0;
                    displayData(newsArrayList);
                    new GetImage(MainActivity.this).execute(newsArrayList.get(index).getUrlToImage());
                }
            }
        });

        findViewById(R.id.btn_last).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index==(newsArrayList.size()-1)){
                    Toast.makeText(MainActivity.this,"Last news already displayed",Toast.LENGTH_SHORT).show();
                }else{
                    index=newsArrayList.size()-1;
                    displayData(newsArrayList);
                    new GetImage(MainActivity.this).execute(newsArrayList.get(index).getUrlToImage());
                }
            }
        });

        findViewById(R.id.btn_previous).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index==0){
                    Toast.makeText(MainActivity.this,"No previous entry",Toast.LENGTH_SHORT).show();
                }else{
                    index--;
                    displayData(newsArrayList);
                    new GetImage(MainActivity.this).execute(newsArrayList.get(index).getUrlToImage());
                }
            }
        });

        findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index==(newsArrayList.size()-1)){
                    Toast.makeText(MainActivity.this,"No more further entry",Toast.LENGTH_SHORT).show();
                }else{
                    index++;
                    displayData(newsArrayList);
                    new GetImage(MainActivity.this).execute(newsArrayList.get(index).getUrlToImage());
                }
            }
        });

        findViewById(R.id.btn_finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void sendData(ArrayList<News> news) {
        Log.d("demo",news.toString());
        newsArrayList = news;
        displayData(news);

        new GetImage(MainActivity.this).execute(news.get(index).getUrlToImage());

    }

    public void displayData(ArrayList<News> news){


        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.l1);

        linearLayout.removeAllViews();

        TextView tv=new TextView(this);
        tv.setText(news.get(index).getTitle());
        linearLayout.addView(tv);

        TextView tv1=new TextView(this);
        tv1.setText("Author: "+news.get(index).getAuthor());
        linearLayout.addView(tv1);

        TextView tv2=new TextView(this);
        if(news.get(index).getPublishedAt()!=null){
            String str = news.get(index).getPublishedAt().split("T")[0];
            /*Date d = new Date(str);
            SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");*/
            tv2.setText("Published on: "+ str);
        }else{
            tv2.setText("Published on: "+" ");
        }

        linearLayout.addView(tv2);

        TextView tv3=new TextView(this);
        tv3.setText("\n"+"Description:"+news.get(index).getDescription());
        linearLayout.addView(tv3);
    }

    @Override
    public void sendImage(Bitmap bitmap) {
        ImageView iv = (ImageView) findViewById(R.id.imageView);
        iv.setImageBitmap(bitmap);
    }
}
