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
import android.widget.RelativeLayout;
import com.ihm.project.menumaker.R;
import com.ihm.project.menumaker.adapters.DishFinderSlidePagerAdapter;

public class DishFinderFragment extends Fragment {

    private FragmentActivity myContext;
    private DishFinderSlidePagerAdapter dishAdapter;
    private ViewPager pager;

    public static DishFinderFragment newInstance() {
        return new DishFinderFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dish_finder_fragment, container, false);

        RelativeLayout layoutItem = (RelativeLayout) view;

        dishAdapter = new DishFinderSlidePagerAdapter(getChildFragmentManager());
        pager = layoutItem.findViewById(R.id.viewpager);
        pager.setAdapter(dishAdapter);

        return view;
    }

}
