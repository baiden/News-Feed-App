package com.example.android.newsfeedapp.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.newsfeedapp.Adapters.DetailedNewsAdapter;
import com.example.android.newsfeedapp.Data.NewsData;
import com.example.android.newsfeedapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailedEntertainmentNewsFragment extends Fragment {
    public static final String NEWS_INFO = "com.example.android.newsfeedapp.Data.NewsData";
    private NewsData newsData;
    private DetailedNewsAdapter mAdapter;

    public DetailedEntertainmentNewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_news, container, false);



        //Receives the data that was passed through the intent
        readDisplayedDetailedNews();

        // Updates title of news story
        TextView news_title_text_view = (TextView) rootView.findViewById(R.id.mtitleOfStory_text_view);

        // Updates image of news story
        ImageView news_image_view = (ImageView) rootView.findViewById(R.id.mimageOfStory_image_view);

        // Updates section of news story
        TextView sectionView = (TextView) rootView.findViewById(R.id.msection);

        // Updates author of news story
        TextView authorView = (TextView) rootView.findViewById(R.id.mauthorName);

        // Updates date of news story
        TextView dateView = (TextView) rootView.findViewById(R.id.date);
        TextView timeView = (TextView) rootView.findViewById(R.id.time);

        // Updates body of news story
        TextView body_of_news_story = (TextView) rootView.findViewById(R.id.bodyOfStory);

        displayDetailedNews(news_title_text_view, news_image_view, sectionView, authorView, body_of_news_story);

//        ListView newsListView = (ListView) rootView.findViewById(R.id.list);
//        // Create a new adapter that takes an empty list of earthquakes as input
//        mAdapter = new DetailedNewsAdapter(getContext(), new ArrayList<NewsData>());
//        // Set the adapter on the {@link ListView}
//        // so the list can be populated in the user interface
//        newsListView.setAdapter(mAdapter);

        return rootView;
    }

    private void displayDetailedNews(TextView news_title_text_view, ImageView news_image_view, TextView sectionView, TextView authorView, TextView body_of_news_story) {
        news_title_text_view.setText(newsData.getTitleOfStory());

        Picasso.get().load(newsData.getImageOfStoryResource()).into(news_image_view);

        String mystring = "#" + newsData.getSectionOfStory();
        sectionView.setText(mystring);

        List<String> author = newsData.getReporterName();
        if(!author.isEmpty()){
            StringBuilder output = new StringBuilder();
            for(int i = 0; i<author.size();i++){
                String all_author_names = author.get(i);
                output.append(all_author_names);
                output.append(" & ");
            }
            output.deleteCharAt(output.length()-2);

            authorView.setText(output);
        }
        else{
            authorView.setVisibility(View.GONE);
        }

//        String originalDateTime = newsData.getDateTimeOfStory();
//        String Date = originalDateTime.substring(0,10);
//        dateView.setText(Date);
//        String Time = originalDateTime.substring(11,16);
//        timeView.setText(Time);

        body_of_news_story.setText(newsData.getBodyOfStory());
    }


    private void readDisplayedDetailedNews() {
        Intent intent = getActivity().getIntent();
        newsData = intent.getParcelableExtra(NEWS_INFO);
    }

}
