package com.example.android.newsfeed;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /**
     * URL for news data from the dataset
     */
    private static final String REQUEST_URL =
            "https://content.guardianapis.com/search?api-key=e2599cfc-4b90-4c22-b193-59817e37e5b9";

    /**
     * Constant value for the news loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int News_LOADER_ID = 1;

    //Context
    private Context currentContext;

    /**
     * Adapter for the list of news
     */
    private NewsAdapter mAdapter;

    /**
     * TextView that is displayed when the list is empty
     */
    private TextView mEmptyStateTextView;


    private final LoaderManager.LoaderCallbacks<List<News>> newsLoader
            = new LoaderManager.LoaderCallbacks<List<News>>() {
        @Override
        public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
            // Create a new loader for the given URL
            return new NewsLoader(currentContext, REQUEST_URL);
        }

        @Override
        public void onLoadFinished(Loader<List<News>> loader, List<News> news) {
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            // Set empty state text to display "No news found."
            mEmptyStateTextView.setText(R.string.no_news);

            // Clear the adapter of previous news data
            mAdapter.clear();

            // If there is a valid list of {@link News}, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (news != null && !news.isEmpty()) {
                mAdapter.addAll(news);
            }
        }

        @Override
        public void onLoaderReset(Loader<List<News>> loader) {
            // Loader reset, so we can clear out our existing data.
            mAdapter.clear();
        }
        };




        protected void onCreate(Bundle savedInstanceState) {
            MainActivity.super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            //Set context
            currentContext = this;

            // Find a reference to the {@link ListView} in the layout
            ListView newsListView = (ListView) findViewById(R.id.list);

            mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
            newsListView.setEmptyView(mEmptyStateTextView);

            // Create a new adapter that takes an empty list of news as input
            mAdapter = new NewsAdapter(this, new ArrayList<News>());

            // Set the adapter on the {@link ListView}
            // so the list can be populated in the user interface
            newsListView.setAdapter(mAdapter);

            // Set an item click listener on the ListView, which sends an intent to a web browser
            // to open a website with more information about the selected news.
            newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    // Find the current news that was clicked on
                    News currentNews = mAdapter.getItem(position);

                    // Convert the String URL into a URI object (to pass into the Intent constructor)
                    Uri newsUri = Uri.parse(currentNews.getUrl());

                    // Create a new intent to view the news URI
                    Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUri);

                    // Send the intent to launch a new activity
                    startActivity(websiteIntent);
                }
            });
            ConnectivityManager connMgr = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.isConnected()) {
                // Get a reference to the LoaderManager, in order to interact with loaders.
                LoaderManager loaderManager = getLoaderManager();
                // Initialize the loader. Pass in the int ID constant defined above and pass in null for
                // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
                // because this activity implements the LoaderCallbacks interface).
                loaderManager.initLoader(News_LOADER_ID, null, newsLoader);
            } else {
                View loadingIndicator = findViewById(R.id.loading_indicator);
                loadingIndicator.setVisibility(View.GONE);
                mEmptyStateTextView.setText(R.string.no_internet_connection);
            }
        }

    }