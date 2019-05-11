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
import android.view.MenuItem;
import android.view.View;

import com.ihm.project.menumaker.fragments.guests.ContactsFragment;
import com.ihm.project.menumaker.fragments.guests.CreateGuestFragment;
import com.ihm.project.menumaker.fragments.guests.ManageGuestFragment;
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
    private ManageGuestFragment manageGuestFragment;
    private CalendarManager calendarManager;
    private IngredientAddingProvision ingredientAddingProvision;
    private IngredientAddingToBuyList ingredientAddingToBuyList;
    private CreateGuestFragment createGuestFragment;
    private ContactsFragment contactsFragment;
    private DishesFragment dishesFragment;

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
                    dishesFragment = new DishesFragment();
                    openDishFragment(dishesFragment);
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

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //home view by default
        navigation.setSelectedItemId(R.id.navigation_home);

        dishFinderFragment = new DishFinderFragment();
        manageGuestFragment = new ManageGuestFragment();
        ingredientAddingProvision = new IngredientAddingProvision();
        ingredientAddingToBuyList= new IngredientAddingToBuyList();
        manageGuestFragment = new ManageGuestFragment();
        createGuestFragment = new CreateGuestFragment();
        contactsFragment = new ContactsFragment();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }

    public void openCreateGuestWithoutContact(View v) {
       this.openFragment(createGuestFragment);
    }
    public void openCreateGuestWithContact(View v) {
       this.openFragment(contactsFragment);
    }
    public void openCreateGuestWithContactSelected(View v, String contactName) {
       this.openFragment(createGuestFragment);
       createGuestFragment.setName(contactName);
    }


    public void openDishesFragment() {
        this.openFragment(this.dishesFragment);
    }
    public void openAddGuestActivity(View v) {
        this.openFragment(manageGuestFragment);
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
        transaction.replace(R.id.container, ingredientAddingProvision);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void createIngredientToBuyList(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, ingredientAddingToBuyList);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void addIngredient(){
        onBackPressed();
    }
}
