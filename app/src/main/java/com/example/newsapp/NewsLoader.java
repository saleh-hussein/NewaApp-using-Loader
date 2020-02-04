package com.example.newsapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.ArrayList;

public class NewsLoader extends AsyncTaskLoader<ArrayList<Article>> {

    private String mUrl;


    //constructor
    public NewsLoader(@NonNull Context context, String url) {
        super(context);
        mUrl = url;
    }

    // ForceLoad
    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    // Background Thread
    @Nullable
    @Override
    public ArrayList<Article> loadInBackground() {

        //if the is no url, return null
        if(mUrl == null){
            return null;
        }

        //perform the network request, parse the response, and extract the data we want
        ArrayList <Article> result = JSONData.jsonExtractData(mUrl);
        return result;
    }
}
