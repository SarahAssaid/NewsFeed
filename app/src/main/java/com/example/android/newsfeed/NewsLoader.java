package com.example.android.newsfeed;

import android.content.AsyncTaskLoader;
import android.content.Context;

public class NewsLoader extends AsyncTaskLoader<String> {

    /**
     * Query URL
     */
    private final String queryUrl;

    /**
     * Constructs a new {@link NewsLoader}.
     *
     * @param context of the activity
     * @param url     to load data from
     */
    public NewsLoader(Context context, String url) {
        super(context);
        queryUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public String loadInBackground() {
        if (queryUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of news.
        return QueryUtils.fetchNewsData(queryUrl);
    }
}
