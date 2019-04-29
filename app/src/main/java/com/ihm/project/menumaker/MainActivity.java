package com.ihm.project.menumaker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.ihm.project.menumaker.fragments.DishFinderFragment;
import com.ihm.project.menumaker.fragments.DishesFragment;
import com.ihm.project.menumaker.fragments.FridgeFragment;
import com.ihm.project.menumaker.fragments.HomeFragment;
import com.ihm.project.menumaker.models.Dishes;

public class MainActivity extends AppCompatActivity {

    private DishFinderFragment dishFinderFragment;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    openFragment(new HomeFragment());
                    return true;
                case R.id.navigation_fridge:
                    openFragment(new FridgeFragment());
                    return true;
                case R.id.navigation_dishes:
                    openFragment(new DishesFragment());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //home view by default
        navigation.setSelectedItemId(R.id.navigation_home);

        dishFinderFragment = new DishFinderFragment();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Dishes.init();
    }

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }

    public void openDishFinder (View v) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, dishFinderFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void onBackPressed(View v){
        if(getFragmentManager().getBackStackEntryCount() > 0){
            getFragmentManager().popBackStackImmediate();
        }
        else{
            super.onBackPressed();
        }
    }
}
