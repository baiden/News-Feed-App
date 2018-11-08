package com.example.android.newsfeedapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.newsfeedapp.Activities.BusinessNewsActivity;
import com.example.android.newsfeedapp.Adapters.MainNewsAdapter;
import com.example.android.newsfeedapp.Data.NewsData;
import com.example.android.newsfeedapp.Loader.NewsLoader;
import com.example.android.newsfeedapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BusinessNewsFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<NewsData>>{

    /** URL for News data from the Guardian dataset */
    private static final String GUARDIAN_REQUEST_URL = "https://content.guardianapis.com/search";
    private static final String apiKeyparameter = "api-key";
    private static final String apiKey = "78e94902-d37d-4c1e-9f5c-35b58b767f09";
    public static final String orderByParameter = "order-by";
    private static final String queryParameter = "q";
    private static String pageSize = "10";
    private static final String author = "show-tags";
    private static final String showFieldsParameter = "show-fields";
    private static final String showFieldsValue = "thumbnail";
    private static final String nameOfAuthor = "contributor";

    /** Adapter for the list of news stories */
    private MainNewsAdapter mAdapter;

    /** TextView that is displayed when the list is empty */
    private TextView mEmptyStateTextView;

    /**
     * Constant value for the news loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int NEWS_LOADER_ID = 1;

    LoaderManager loaderManager;
    boolean isConnected;
    private View progressBar;


    public BusinessNewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.news_list, container, false);

        // Find a reference to the {@link ListView} in the layout
        final ListView newsListView = (ListView) rootView.findViewById(R.id.list);

        /* *********** Checks if there is an internet connection ******/
        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = null;
        if (cm != null) {
            activeNetwork = cm.getActiveNetworkInfo();
        }
        isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        mEmptyStateTextView = (TextView) rootView.findViewById(R.id.empty_view);
        newsListView.setEmptyView(mEmptyStateTextView);

        progressBar = (View) rootView.findViewById(R.id.progress_bar);

        // Create a new adapter that takes an empty list of news as input
        mAdapter = new MainNewsAdapter(getContext(), new ArrayList<NewsData>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        newsListView.setAdapter(mAdapter);

        if (isConnected){
            // Get a reference to the LoaderManager, in order to interact with loaders.
            loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(NEWS_LOADER_ID, null, this);
        } else {
            progressBar.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }

        // Creates an onItemClickListen for the ListView
        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsData newsData = mAdapter.getItem(position);

                // Gets the url of the story
                Uri uriOfNews = Uri.parse(newsData.getUrlOfStory());

                // Creates an implicit intent to open the news story in a browser
                Intent intent = new Intent(Intent.ACTION_VIEW, uriOfNews);
                startActivity(intent);
            }
        });

        // Creates an onItemLongClickListen for the ListView
        newsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                NewsData newsData = mAdapter.getItem(position);

                Intent openMainNews = new Intent(getContext(), BusinessNewsActivity.class);
                openMainNews.putExtra(DetailedBusinessNewsFragment.NEWS_INFO, newsData);

                startActivity(openMainNews);

                return true;
            }
        });

        return rootView;
    }

    @Override
    public Loader<List<NewsData>> onCreateLoader(int id, Bundle args) {

        // Create a new loader for the given URL
        Uri.Builder builder = Uri.parse(GUARDIAN_REQUEST_URL).buildUpon();
        builder.appendQueryParameter(queryParameter, getString(R.string.country_default))
                .appendQueryParameter(orderByParameter, getString(R.string.newest))
                .appendQueryParameter("section","business")
                .appendQueryParameter(showFieldsParameter, "bodyText,thumbnail")
                .appendQueryParameter("page-size", "30")
                .appendQueryParameter(author, nameOfAuthor)
                .appendQueryParameter(apiKeyparameter, apiKey);
        Log.w("value of url : ", builder.toString());
        return new NewsLoader(getActivity(), builder.toString());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<NewsData>> loader, List<NewsData> data) {
        progressBar.setVisibility(View.GONE);

        // Set empty state text to display "No news found."
        mEmptyStateTextView.setText(R.string.no_news);

        // Clear the adapter of previous earthquake data
        mAdapter.clear();

        // If there is a valid list of {@link NewsData}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (data != null && !data.isEmpty()) {
            mAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<NewsData>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }
}