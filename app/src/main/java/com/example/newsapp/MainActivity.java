package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Article>> {



   //declare arrayList to store the news (Article name, article section and article date)
    private static  ArrayList<Article> data;

    private static final String   ARTICLE_REQUEST_URL ="https://content.guardianapis.com/search?api-key=182ede61-281d-438a-8a01-cf7c1c662a74" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get reference fot Loader manager, then initialize the loader
        LoaderManager loaderManager = getSupportLoaderManager();
        loaderManager.initLoader(1,null,this);

        //declare adapter
        CustomAdapter adapter = new CustomAdapter(this, data);

        //declare list item
        ListView listView = (ListView) findViewById(R.id.list_view);

        //set list item with adapter
        listView.setAdapter(adapter);


    }


    //update the arrayList with article, so we can use it with out adapter
    private static void updateUi (ArrayList<Article> articles){
        data = articles;
    }

    @NonNull
    @Override
    public Loader<ArrayList<Article>> onCreateLoader(int id, @Nullable Bundle args) {
        //create new loader for given url
        return new NewsLoader(this,ARTICLE_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Article>> loader, ArrayList<Article> articles) {
        //update ui thread. ( update the arrayList with article, so we can use it with out adapter )
        data = articles;

    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<Article>> loader) {
        data.clear();

    }

}
