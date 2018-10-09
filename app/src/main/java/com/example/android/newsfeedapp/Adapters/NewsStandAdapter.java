package com.example.android.newsfeedapp.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.newsfeedapp.Data.NewsstandData;
import com.example.android.newsfeedapp.Data.PlaceData;
import com.example.android.newsfeedapp.R;

import java.util.ArrayList;

public class NewsStandAdapter extends ArrayAdapter<NewsstandData> {
    private static final String LOG_TAG = NewsStandAdapter.class.getSimpleName();

    /**
     *
     * @param context The current context. Used to inflate the layout file.
     * @param pnewsstandData A List of PlaceData objects to display in a list
     */
    public NewsStandAdapter(Activity context, ArrayList<NewsstandData> pnewsstandData) {
        super(context, 0, pnewsstandData);
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
        View newsstandListView = convertView;
        if (newsstandListView == null) {
            newsstandListView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_stand_item, parent, false);
        }

        // Get the PlaceData object located at this position in the list
        NewsstandData currentNewsStand = getItem(position);

        TextView newsstandTitle_text_view = (TextView) newsstandListView.findViewById(R.id.titleOfNewsstand_text_view);
        newsstandTitle_text_view.setText(currentNewsStand.getTitleOfCategory());

        // Find the ImageView in the place_detailed_information_item.xml layout with the ID imageOfPlace_image_view
        ImageView newsstandImage_img = (ImageView) newsstandListView.findViewById(R.id.imageOfNewsstand_image_view);
        newsstandImage_img.setImageResource(currentNewsStand.getImageOfCategoryResourceID());

        return newsstandListView;

    }
}
