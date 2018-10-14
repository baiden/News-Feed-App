package com.example.android.newsfeedapp.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.newsfeedapp.Data.NewsData;
import com.example.android.newsfeedapp.R;

import java.util.ArrayList;

public class MainNewsAdapter extends ArrayAdapter<NewsData> {

    private static final String LOG_TAG = MainNewsAdapter.class.getSimpleName();

    /**
     *
     * @param context The current context. Used to inflate the layout file.
     * @param pnewsData A List of NewsData objects to display in a list
     */
    public MainNewsAdapter(Activity context, ArrayList<NewsData> pnewsData) {
        super(context, 0, pnewsData);
    }

    /**
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View newsListView = convertView;
        if (newsListView == null) {
            newsListView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_item, parent, false);
        }

        // Get the NewsData object located at this position in the list
        NewsData currentNews = getItem(position);

        TextView news_title_text_view = (TextView) newsListView.findViewById(R.id.titleOfStory_text_view);
        news_title_text_view.setText(currentNews.getTitleOfStory());

        // Find the ImageView in the place_detailed_information_item.xml layout with the ID imageOfPlace_image_view
        ImageView news_image_view = (ImageView) newsListView.findViewById(R.id.imageOfStory_image_view);
        news_image_view.setImageResource(currentNews.getImageOfStoryResourceID());

        return newsListView;

    }
}

