package com.example.android.newsfeedapp.Loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.example.android.newsfeedapp.Data.NewsData;
import com.example.android.newsfeedapp.Utils.QueryUtils;

import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<NewsData>> {

    /** Tag for log messages */
    private static final String LOG_TAG = NewsLoader.class.getName();

    /** Query URL */
    private String mUrl;

    /**
     * Constructs a new {@link NewsLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    public NewsLoader(Context context, String url){
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG, "TEST: onStartLoading() called ...");
        forceLoad();
    }

    @Override
    public List<NewsData> loadInBackground() {
        Log.i(LOG_TAG, "TEST: onloadInBackground() called ...");

        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of earthquakes.
        List<NewsData> newsData = QueryUtils.fetchNewsData(mUrl);
        return newsData;
    }
}
