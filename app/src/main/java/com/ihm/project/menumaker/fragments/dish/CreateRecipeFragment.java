package com.ihm.project.menumaker.fragments.dish;

import android.Manifest;
import android.app.FragmentManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ihm.project.menumaker.R;
import com.ihm.project.menumaker.Samples.Dish;
import com.ihm.project.menumaker.Samples.Ingredient;
import com.ihm.project.menumaker.adapters.FridgeSlidePagerAdapter;
import com.ihm.project.menumaker.adapters.IngredientsListAdapter;
import com.ihm.project.menumaker.models.Dishes;
import com.ihm.project.menumaker.models.Ingredients;
import com.ihm.project.menumaker.utils.IngredientsType;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class CreateRecipeFragment extends Fragment {

    View myView;
    private EditText recipe;
    private EditText recipe_name;
    private Button buttonSave;
    private Button addIngredientButton;
    private ImageView takePictureImageView;
    private ListView recipeIngredientsView;
    private EditText ingredientQuantity;
    private EditText ingredientName;
    private int ingredientType;
    private Spinner spinner;
    private List<Ingredient> recipeIngredients;
    private final int REQUEST_ID_IMAGE_CAPTURE = 100;
    private final int PERMISSION_REQUEST_READ_MEDIA = 100;
    private Bitmap photo;
    private String pictureName="recipe_photo" ;
    private Dish dish;
    private String imagePath;
    private IngredientsListAdapter ingredientsListAdapter;

    public static CreateRecipeFragment newInstance(){ return new CreateRecipeFragment(); }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Initialisation des attributs à partir des éléments contenus dans le fragment
        recipeIngredients = new ArrayList<>();
        myView = inflater.inflate(R.layout.create_recipe_fragment, container, false);
        recipe = myView.findViewById(R.id.recipeAdded);
        recipe_name = myView.findViewById(R.id.recipeNameAdded);
        buttonSave = myView.findViewById(R.id.buttonSave);
        addIngredientButton = myView.findViewById(R.id.button3_add_ingredient);
        recipeIngredientsView = myView.findViewById(R.id.recipeIngredients);
        takePictureImageView = myView.findViewById(R.id.takePicture);
        ingredientName = myView.findViewById(R.id.ingredientName);
        ingredientQuantity = myView.findViewById(R.id.ingredientQuantity);

        spinner = myView.findViewById(R.id.ingredientType);
        String[] ingredientsTypes= {"g", "L", "u"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, ingredientsTypes);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ingredientType=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ingredientType=0;
            }
        });





        takePictureImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_ID_IMAGE_CAPTURE);
            }
        });

        addIngredientButton.setOnClickListener((view) ->{
            if (ingredientName.getText().toString().matches("")) {
                Toast.makeText(getContext(), "Vous devez saisir un nom d'ingrédient", Toast.LENGTH_SHORT).show();
                return;
            }

            if (ingredientQuantity.getText().toString().matches("")) {
                Toast.makeText(getContext(), "Vous devez saisir une quantité", Toast.LENGTH_SHORT).show();
                return;
            }

            if(ingredientType==0)  {
                recipeIngredients.add(new Ingredient(ingredientName.getText().toString(), IngredientsType.POUNDABLE, Integer.parseInt(ingredientQuantity.getText().toString())));
            }

            if(ingredientType==1) {
                recipeIngredients.add(new Ingredient(ingredientName.getText().toString(), IngredientsType.LIQUIDE, Integer.parseInt(ingredientQuantity.getText().toString())));
            }

            if(ingredientType==2){
                recipeIngredients.add(new Ingredient(ingredientName.getText().toString(), IngredientsType.COUNTABLE, Integer.parseInt(ingredientQuantity.getText().toString())));
            }
            IngredientsListAdapter adapter = new IngredientsListAdapter(getContext(), recipeIngredients, R.layout.ingredient_layout);
            recipeIngredientsView.setAdapter(adapter);
        });

        buttonSave.setOnClickListener((view) -> {

            if (photo !=null){
                int permissionCheck= ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if(permissionCheck!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},PERMISSION_REQUEST_READ_MEDIA);
                }
                else {
                    if (recipe_name.getText().toString().matches("")) {
                        Toast.makeText(getContext(), "Vous devez saisir un nom de recette", Toast.LENGTH_SHORT).show();
                    }
                    if (recipe.getText().toString().matches("")) {
                        Toast.makeText(getContext(), "Vous devez saisir votre recette", Toast.LENGTH_SHORT).show();
                    }



                   if(this.recipeIngredients.size()>0)
                   {

                        saveToInternalStorage(photo);
                        dish = new Dish(recipe_name.getText().toString(),imagePath, recipe.getText().toString(), recipeIngredients);
                        Dishes.add(dish);
                        Toast.makeText(getContext(),"Votre recette a bien été créee",Toast.LENGTH_LONG).show();
                        getFragmentManager().popBackStackImmediate();
                    }
                   else
                   {
                        Toast.makeText(getContext(),"Vous devez saisir les ingrédients de votre recette",Toast.LENGTH_LONG).show();
                    }


                }

            }
            else{
                Toast.makeText(getContext(), "Vous devez prendre en photo votre recette", Toast.LENGTH_SHORT).show();
            }


        });




       return myView;
     }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == REQUEST_ID_IMAGE_CAPTURE) {
            if (resultCode == RESULT_OK) {
                photo = (Bitmap) data.getExtras().get("data");
                takePictureImageView.setImageBitmap(photo);
                buttonSave.setAlpha(1f);
                        //setImageResource(dish.getImageWithContext(getContext()));


            } else if (resultCode == RESULT_CANCELED){
                Toast.makeText(getContext(),"Action Cancelled",Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(getContext(),"Action Failed",Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_READ_MEDIA:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, proceed to the normal flow.
                    saveToInternalStorage(photo);
                }
                break;
            default:break;


        }
    }

    private void saveToInternalStorage(Bitmap photo) {
        ContextWrapper cw = new ContextWrapper(getActivity().getApplicationContext());
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE) ;
        File file = new File(directory,pictureName);
        FileOutputStream fos = null ;
        try{
            fos = new FileOutputStream(file);
            photo.compress(Bitmap.CompressFormat.PNG,100,fos);

        }
        catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            }catch (IOException | NullPointerException e){
                e.printStackTrace();
            }
        }

        try {
            final String s = (String) MediaStore.Images.Media.insertImage((ContentResolver) getActivity().getContentResolver(), file.getPath(),pictureName,"");
            imagePath = getRealPathFromUri(getContext(),Uri.parse(s));
        }catch (FileNotFoundException e){
            e.printStackTrace();

        }

    }
    public static String getRealPathFromUri(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @Override
    public void onStop(){
        super.onStop();
        recipe.setText("");
        recipe_name.setText("");
        List<Ingredient> emptyList = new ArrayList<>();
        IngredientsListAdapter adapter1 = new IngredientsListAdapter(getContext(), emptyList, R.layout.ingredient_layout);
        recipeIngredientsView.setAdapter(adapter1);
        ingredientName.setText("");
        ingredientQuantity.setText("");
    }

}

