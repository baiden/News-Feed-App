package com.example.android.newsfeedapp.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.android.newsfeedapp.Helpers.BottomNavigationViewHelper;
import com.example.android.newsfeedapp.R;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity{
    @BindView(R.id.bottomNavigationView) BottomNavigationView bottomNavMenu;
    @BindView(R.id.all_news) TextView viewAllNews;
    @BindView(R.id.read_more_news) Button readMoreNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Sets the screen to fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView); //Disables the default transaction in the bottom navigation view

        //Sets onClick listeners on the buttons on the bottom navigation view
        bottomNavMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.home:
                        YoYo.with(Techniques.Tada)
                                .duration(700)
                                .playOn(findViewById(R.id.home));
                        break;

                    case R.id.headline:
                        Intent playListIntent = new Intent(MainActivity.this, HeadlinesActivity.class);
                        startActivity(playListIntent);
                        break;

                    case R.id.favourites:
                        Intent favourtieIntent = new Intent(MainActivity.this, FavouritesActivity.class);
                        startActivity(favourtieIntent);
                        break;

                    case R.id.trending:
                        Intent trendingIntent = new Intent(MainActivity.this, TrendingNewsActivity.class);
                        startActivity(trendingIntent);
                        break;
                }

                return false;
            }
        });

    }

    @OnClick(R.id.all_news)
    public void viewNews(View view) {
        Intent intent = new Intent(MainActivity.this, HeadlinesActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.read_more_news)
    public void submit(View view) {
        Intent intent = new Intent(MainActivity.this, HeadlinesActivity.class);
        startActivity(intent);
    }

}
