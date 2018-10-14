package com.example.android.newsfeedapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.android.newsfeedapp.Adapters.MainNewsAdapter;
import com.example.android.newsfeedapp.Data.NewsData;
import com.example.android.newsfeedapp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class News360Fragment extends Fragment {


    public News360Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.news_list, container, false);

        // Create a list of words
        final ArrayList<NewsData> newsDataList = new ArrayList<NewsData>();

        newsDataList.add(new NewsData(R.string.KN_Mausoleum, R.drawable.abreast));
        newsDataList.add(new NewsData(R.string.Independence_Arc, R.drawable.abreast));
        newsDataList.add(new NewsData(R.string.CapeCoast_Castle, R.drawable.abreast));
        newsDataList.add(new NewsData(R.string.NMuseum_ofGhana, R.drawable.abreast));
        newsDataList.add(new NewsData(R.string.Elmina_Castle, R.drawable.abreast));

        //Puts the values into the MainNewsAdapter class
        MainNewsAdapter mainNewsAdapter = new MainNewsAdapter(getActivity(), newsDataList);
        GridView newsGridView = (GridView) rootView.findViewById(R.id.typeOfSideAttractionGridview);

        //Displays the data in the MainNewsAdapter
        newsGridView.setAdapter(mainNewsAdapter);

        return rootView;
    }

}
