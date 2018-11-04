package com.example.android.newsfeedapp.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;

import com.example.android.newsfeedapp.Fragments.DetailedBusinessNewsFragment;
import com.example.android.newsfeedapp.R;

public class BusinessNewsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_news);

        //Hosts the DetailedManMadePlaceFragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new DetailedBusinessNewsFragment())
                .commit();
    }
}
