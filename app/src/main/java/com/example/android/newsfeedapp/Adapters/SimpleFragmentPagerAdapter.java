/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.newsfeedapp.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.newsfeedapp.Fragments.BusinessNewsFragment;
import com.example.android.newsfeedapp.Fragments.MusicNewsFragment;
import com.example.android.newsfeedapp.Fragments.WorldNewsFragment;
import com.example.android.newsfeedapp.Fragments.PoliticalNewsFragment;
import com.example.android.newsfeedapp.Fragments.SportsNewsFragment;
import com.example.android.newsfeedapp.Fragments.TechnologicalNewsFragment;

/**
 * Provides the appropriate {@link Fragment} for a view pager.
 */
public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    //Determines the number of tabs to be created
    final int PAGE_COUNT = 6;

    //Stores the titles of the strings in an array
    private String tabTitles[] = new String[] { "World News", "Politics", "Sports", "Business", "Technology", "Music"};
    private Context context;

    /**
     * Creates a constructor class of the SimpleFragmentPagerAdaper
     * @param fm is used to control the behaviour of the fragment
     * @param context is used to specify where the fragment is to be hosted
     */
    public SimpleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    //Specifies which fragment appears first as tab 1 or not
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new WorldNewsFragment();
        } else if (position == 1){
            return new PoliticalNewsFragment();
        } else if (position == 2){
            return new SportsNewsFragment();
        } else if (position == 3){
            return new BusinessNewsFragment();
        } else if (position == 4){
            return new TechnologicalNewsFragment();
        } else if (position == 5){
            return new MusicNewsFragment();
        } else {
            return new WorldNewsFragment();
        }
    }

    //Gets the number of tabs to be created
    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    //Makes both the title and the icons to appear on the tabs
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }

}
