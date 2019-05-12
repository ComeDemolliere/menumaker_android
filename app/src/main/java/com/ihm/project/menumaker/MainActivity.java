package com.ihm.project.menumaker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ihm.project.menumaker.fragments.SettingsFragment;
import com.ihm.project.menumaker.fragments.dish.CreateRecipeFragment;
import com.ihm.project.menumaker.fragments.guests.ContactsFragment;
import com.ihm.project.menumaker.fragments.guests.CreateGuestFragment;
import com.ihm.project.menumaker.fragments.guests.ManageGuestFragment;
import com.ihm.project.menumaker.fragments.home.DishFinderFragment;
import com.ihm.project.menumaker.fragments.dish.DishesFragment;
import com.ihm.project.menumaker.fragments.fridge.FridgeFragment;
import com.ihm.project.menumaker.fragments.home.HomeFragment;
import com.ihm.project.menumaker.fragments.fridge.IngredientAddingProvision;
import com.ihm.project.menumaker.fragments.fridge.IngredientAddingToBuyList;
import com.ihm.project.menumaker.fragments.ValidateDishFragment;
import com.ihm.project.menumaker.models.Dishes;
import com.ihm.project.menumaker.models.Ingredients;
import com.ihm.project.menumaker.utils.CalendarManager;

public class MainActivity extends AppCompatActivity {

    private DishFinderFragment dishFinderFragment;
    private ManageGuestFragment manageGuestFragment;
    private IngredientAddingProvision ingredientAddingProvision;
    private IngredientAddingToBuyList ingredientAddingToBuyList;
    private CreateGuestFragment createGuestFragment;
    private ContactsFragment contactsFragment;
    private DishesFragment dishesFragment;
    private CreateRecipeFragment createRecipeFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    openFragment(new HomeFragment(), false);
                    return true;
                case R.id.navigation_fridge:
                    openFragment(new FridgeFragment(), false);
                    return true;
                case R.id.navigation_dishes:
                    dishesFragment = new DishesFragment();
                    openFragment(dishesFragment, false);
                    openFragment(new DishesFragment(), false);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //init
        Dishes.init(this.getBaseContext());
        Ingredients.init(this.getBaseContext());

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.miCompose : openFragment(new SettingsFragment(), false);
                }
                return false;
            }
        });

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
        createRecipeFragment = new CreateRecipeFragment();
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

    public void openFragment(Fragment fragment, boolean stack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container, fragment);
        if(stack) transaction.addToBackStack(null);
        transaction.commit();
    }

    public void openCreateGuestWithoutContact(View v) {
       this.openFragment(createGuestFragment, false);
    }

    public void openCreateGuestWithContact(View v) {
       this.openFragment(contactsFragment, false);
    }

    public void openCreateGuestWithContactSelected(View v, String contactName) {
       this.openFragment(createGuestFragment, false);
       createGuestFragment.setName(contactName);
    }


    public void openDishesFragment() {
        this.openFragment(this.dishesFragment, false);
    }
    public void openAddGuestActivity(View v) {
        this.openFragment(manageGuestFragment, false);
    }

    public void openDishFinder (View v) { openFragment(dishFinderFragment, true);}

    public void openCreateRecipe(View v) {
        this.openFragment(createRecipeFragment, true);
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
        openFragment(new ValidateDishFragment(), true);
    }

    public void chooseDishRep(View v){ openFragment(new ValidateDishFragment(), true);}

    public void createIngredient(View view) {
        openFragment(ingredientAddingProvision, true);
    }


    public void createIngredientToBuyList(View view) {
        openFragment(ingredientAddingProvision, true);
    }

}

