package com.ihm.project.menumaker.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ihm.project.menumaker.fragments.home.DishFinderContentFragment;
import com.ihm.project.menumaker.models.Dishes;

public class DishFinderSlidePagerAdapter extends FragmentPagerAdapter {

    public DishFinderSlidePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        new DishFinderContentFragment();
        return DishFinderContentFragment.newInstance(i);
    }

    @Override
    public int getCount() {
        return Dishes.size();
    }
}
