package com.example.android.newsfeedapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.android.newsfeedapp.Helpers.BottomNavigationViewHelper;
import com.example.android.newsfeedapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavouritesActivity extends FragmentActivity {
    @BindView(R.id.bottomNavigationView) BottomNavigationView bottomNavMenu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        ButterKnife.bind(this);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView); //Disables the default transaction in the bottom navigation view

        //Sets onClick listeners on the buttons on the bottom navigation view
        bottomNavMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home:
                        Intent homeIntent = new Intent(FavouritesActivity.this, MainActivity.class);
                        startActivity(homeIntent);
                        break;

                    case R.id.headline:
                        Intent headlineIntent = new Intent(FavouritesActivity.this, HeadlinesActivity.class);
                        startActivity(headlineIntent);
                        break;

                    case R.id.favourites:
                        YoYo.with(Techniques.Tada)
                                .duration(700)
                                .playOn(findViewById(R.id.favourites));
                        break;

                    case R.id.trending:
                        Intent newsstandIntent = new Intent(FavouritesActivity.this, TrendingNewsActivity.class);
                        startActivity(newsstandIntent);
                        break;
                }

                return false;
            }
        });
    }
}
