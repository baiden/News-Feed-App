package com.example.android.newsfeedapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.android.newsfeedapp.Adapters.TourSideAttractionAdapter;
import com.example.android.newsfeedapp.Data.PlaceData;
import com.example.android.newsfeedapp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SportsNewsFragment extends Fragment {


    public SportsNewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.news_list, container, false);

        // Create a list of words
        final ArrayList<PlaceData> tourSideAttractionData = new ArrayList<PlaceData>();

        tourSideAttractionData.add(new PlaceData(R.string.KN_Mausoleum, R.drawable.abreast));
        tourSideAttractionData.add(new PlaceData(R.string.Independence_Arc, R.drawable.abreast));
        tourSideAttractionData.add(new PlaceData(R.string.CapeCoast_Castle, R.drawable.abreast));
        tourSideAttractionData.add(new PlaceData(R.string.NMuseum_ofGhana, R.drawable.abreast));
        tourSideAttractionData.add(new PlaceData(R.string.Elmina_Castle, R.drawable.abreast));

        //Puts the values into the TourSideAttractionAdapter class
        TourSideAttractionAdapter tourSideAttractionAdapter = new TourSideAttractionAdapter(getActivity(), tourSideAttractionData);
        GridView gridView = (GridView) rootView.findViewById(R.id.typeOfSideAttractionGridview);

        //Displays the data in the TourSideAttractionAdapter
        gridView.setAdapter(tourSideAttractionAdapter);

        return rootView;
    }

}
