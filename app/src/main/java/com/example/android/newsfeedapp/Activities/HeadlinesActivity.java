package com.example.android.newsfeedapp.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.android.newsfeedapp.Adapters.SimpleFragmentPagerAdapter;
import com.example.android.newsfeedapp.Helpers.BottomNavigationViewHelper;
import com.example.android.newsfeedapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HeadlinesActivity extends FragmentActivity {
    @BindView(R.id.bottomNavigationView) BottomNavigationView bottomNavMenu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_headlines);

        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        // Create an adapter that knows which fragment should be shown on each page
        SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), HeadlinesActivity.this);

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setTabTextColors(Color.parseColor(getString(R.string.color_of_inactive_tab_text)), Color.parseColor(getString(R.string.color_blue)));
        tabLayout.setupWithViewPager(viewPager);

        ButterKnife.bind(this);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView); //Disables the default transaction in the bottom navigation view

        //Sets onClick listeners on the buttons on the bottom navigation view
        bottomNavMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.home:
                        Intent homeIntent = new Intent(HeadlinesActivity.this, MainActivity.class);
                        startActivity(homeIntent);
                        break;

                    case R.id.headline:
                        YoYo.with(Techniques.Tada)
                                .duration(700)
                                .playOn(findViewById(R.id.headline));
                        break;

                    case R.id.favourites:
                        Intent favourtieIntent = new Intent(HeadlinesActivity.this, FavouritesActivity.class);
                        startActivity(favourtieIntent);
                        break;

                    case R.id.trending:
                        Intent albumIntent = new Intent(HeadlinesActivity.this, TrendingNewsActivity.class);
                        startActivity(albumIntent);
                        break;
                }

                return false;
            }
        });
    }
}
