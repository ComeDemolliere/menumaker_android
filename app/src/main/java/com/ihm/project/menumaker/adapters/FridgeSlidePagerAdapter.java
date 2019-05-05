package com.ihm.project.menumaker.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ihm.project.menumaker.fragments.FridgeProvisions;
import com.ihm.project.menumaker.fragments.FridgeToBuyList;

public class FridgeSlidePagerAdapter extends FragmentPagerAdapter {

    public FridgeSlidePagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int i) {
            if (i==0) return new FridgeProvisions();

            else if (i==1)return new FridgeToBuyList();

            return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
