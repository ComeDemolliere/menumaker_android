package com.ihm.project.menumaker.fragments.fridge;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.ihm.project.menumaker.R;
import com.ihm.project.menumaker.adapters.FridgeSlidePagerAdapter;

public class FridgeFragment extends Fragment {

    public static FridgeFragment newInstance() {
        return new FridgeFragment();
    }

    private FridgeSlidePagerAdapter fridgeSlidePagerAdapter;
    private ViewPager pager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final View fridgeView = inflater.inflate(R.layout.fridge_fragment, container, false);
        RelativeLayout layoutItem = (RelativeLayout) fridgeView;

        fridgeSlidePagerAdapter = new FridgeSlidePagerAdapter(getChildFragmentManager());
        fridgeSlidePagerAdapter.addFragment(new FridgeProvisions(), "Provisions");
        fridgeSlidePagerAdapter.addFragment(new FridgeToBuyList(), "Liste de courses");

        TabLayout tabLayout = fridgeView.findViewById(R.id.fridgeTab);
        pager = layoutItem.findViewById(R.id.viewpager1);
        pager.setAdapter(fridgeSlidePagerAdapter);
        tabLayout.setupWithViewPager(pager);

        return fridgeView;

    }

}
