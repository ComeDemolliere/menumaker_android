package com.ihm.project.menumaker;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ihm.project.menumaker.fragments.CreateGuestFragment;
import com.ihm.project.menumaker.fragments.DishFinderFragment;
import com.ihm.project.menumaker.fragments.DishesFragment;
import com.ihm.project.menumaker.fragments.FridgeFragment;
import com.ihm.project.menumaker.fragments.HomeFragment;
import com.ihm.project.menumaker.fragments.IngredientAddingProvision;
import com.ihm.project.menumaker.fragments.IngredientAddingToBuyList;
import com.ihm.project.menumaker.fragments.ValidateDishFragment;
import com.ihm.project.menumaker.models.Dishes;
import com.ihm.project.menumaker.models.Ingredients;
import com.ihm.project.menumaker.utils.CalendarManager;

public class MainActivity extends AppCompatActivity {

    private DishFinderFragment dishFinderFragment;
    private CreateGuestFragment createGuestFrament;
    private CalendarManager calendarManager;
    private IngredientAddingProvision ingredientAddingProvision;
    private IngredientAddingToBuyList ingredientAddingToBuyList;
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
                    openDishFragment(new DishesFragment());
                    return true;
            }
            return false;
        }
    };

    private void openDishFragment(DishesFragment dishesFragment) {
        openFragment(dishesFragment);
        dishesFragment.setActivity(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //init
        Dishes.init(this.getBaseContext());
        Ingredients.init(this.getBaseContext());

        //Request permissions
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR}, 1);

        calendarManager = new CalendarManager(this);
        calendarManager.init();

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //home view by default
        navigation.setSelectedItemId(R.id.navigation_home);

        dishFinderFragment = new DishFinderFragment();
        createGuestFrament = new CreateGuestFragment();
        ingredientAddingProvision = new IngredientAddingProvision();
        ingredientAddingToBuyList= new IngredientAddingToBuyList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container, fragment);
        transaction.commit();
    }

    public void openAddGuestActivity(View v) {
        this.openFragment(createGuestFrament);
    }

    public void openDishFinder (View v) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container, dishFinderFragment);
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

    public void chooseDish(View v){
        Dishes.setCurrentDish(Dishes.getDishes().get(dishFinderFragment.getViewPager().getCurrentItem()));
        openFragment(new ValidateDishFragment());
    }

    public void validateDish(View v){
        addEventToCalendar();
        Dishes.eatDish();
        openFragment(new HomeFragment());
    }

    private void addEventToCalendar(){
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            System.out.println("Holidays in United States");
            return;
        } else calendarManager.insert(Dishes.getCurrentDish());
    }

    public void createIngredient(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container, ingredientAddingProvision);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void createIngredientToBuyList(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container, ingredientAddingToBuyList);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void addIngredient(){
        onBackPressed();
    }
}
