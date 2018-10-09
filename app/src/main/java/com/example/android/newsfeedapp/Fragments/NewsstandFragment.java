package com.example.android.newsfeedapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.example.android.newsfeedapp.Adapters.NewsStandAdapter;
import com.example.android.newsfeedapp.Data.NewsstandData;
import com.example.android.newsfeedapp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsstandFragment extends Fragment {


    public NewsstandFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.news_stand_list, container, false);

        // Create a list of words
        final ArrayList<NewsstandData> newsStandDataList = new ArrayList<NewsstandData>();

        newsStandDataList.add(new NewsstandData(R.string.entertainment, R.drawable.abreast));
        newsStandDataList.add(new NewsstandData(R.string.politics , R.drawable.abreast));
        newsStandDataList.add(new NewsstandData(R.string.science, R.drawable.abreast));
        newsStandDataList.add(new NewsstandData(R.string.sports, R.drawable.abreast));
        newsStandDataList.add(new NewsstandData(R.string.food, R.drawable.abreast));
        newsStandDataList.add(new NewsstandData(R.string.health, R.drawable.abreast));

        //Puts the values into the TourSideAttractionAdapter class
        NewsStandAdapter newsStandAdapter = new NewsStandAdapter(getActivity(), newsStandDataList);
        GridView gridView = (GridView) rootView.findViewById(R.id.newsStandGridview);

        //Displays the data in the TourSideAttractionAdapter
        gridView.setAdapter(newsStandAdapter);

        return rootView;
    }

}
