package com.ihm.project.menumaker.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.ihm.project.menumaker.R;
import com.ihm.project.menumaker.adapters.DishFinderSlidePagerAdapter;
import com.ihm.project.menumaker.adapters.FridgeSlidePagerAdapter;

public class FridgeFragment extends Fragment {

    public static FridgeFragment newInstance() {
        return new FridgeFragment();
    }
    private FragmentActivity myContext;
    private FridgeSlidePagerAdapter fridgeSlidePagerAdapter;
    private ViewPager pager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final View fridgeView = inflater.inflate(R.layout.fridge_fragment, container, false);
        RelativeLayout layoutItem = (RelativeLayout) fridgeView;

        fridgeSlidePagerAdapter = new FridgeSlidePagerAdapter(getChildFragmentManager());
        pager = layoutItem.findViewById(R.id.viewpager1);
        pager.setAdapter(fridgeSlidePagerAdapter);

        return fridgeView;

    }

}
