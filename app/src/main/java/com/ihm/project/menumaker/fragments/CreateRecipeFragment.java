package com.ihm.project.menumaker.fragments;

import android.Manifest;
import android.app.FragmentManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.ihm.project.menumaker.R;
import com.ihm.project.menumaker.Samples.Dish;
import com.ihm.project.menumaker.Samples.Ingredient;
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
    private ImageView takePictureImageView;
    private final int REQUEST_ID_IMAGE_CAPTURE = 100;
    private final int PERMISSION_REQUEST_READ_MEDIA = 100;
    private Bitmap photo;
    private String pictureName="recipe_photo" ;
    private Dish dish;
    private List<Ingredient> ingredientList;
    private String imagePath;

    public static CreateRecipeFragment newInstance(){ return new CreateRecipeFragment(); }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.create_recipe_fragment, container, false);
        recipe = myView.findViewById(R.id.recipeAdded);
        recipe_name = myView.findViewById(R.id.recipeNameAdded);
        buttonSave = myView.findViewById(R.id.buttonSave);
        takePictureImageView = myView.findViewById(R.id.takePicture);
        takePictureImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_ID_IMAGE_CAPTURE);
            }
        });

        ingredientList = new ArrayList<>();
        ingredientList.add(new Ingredient("carotte",IngredientsType.POUNDABLE,3));

        buttonSave.setOnClickListener((view) -> {
            if (photo !=null){
                int permissionCheck= ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if(permissionCheck!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},PERMISSION_REQUEST_READ_MEDIA);
                }
                else {
                    saveToInternalStorage(photo);
                    dish = new Dish(recipe_name.getText().toString(),imagePath, recipe.getText().toString(), ingredientList);

                }

            }
            else{
                Toast.makeText(getContext(), "Vous devez prendre en photo votre recette", Toast.LENGTH_SHORT).show();
            }
            if (recipe_name.getText().toString().matches("")) {
                Toast.makeText(getContext(), "Vous devez saisir un nom de recette", Toast.LENGTH_SHORT).show();
                if (recipe.getText().toString().matches("")) {
                    Toast.makeText(getContext(), "Vous devez saisir votre recette", Toast.LENGTH_SHORT).show();
                }
            }

        });




       return myView;
     }


    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
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
                Toast.makeText(getContext(),"Action Cancelled",Toast.LENGTH_LONG);
            }
            else {
                Toast.makeText(getContext(),"Action Failed",Toast.LENGTH_LONG);
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
        imagePath = directory.getPath();
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
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        try {
            final String s = (String) MediaStore.Images.Media.insertImage((ContentResolver) getActivity().getContentResolver(), file.getPath(),pictureName,"");
            Toast.makeText(getContext(),"New Picture Saved",Toast.LENGTH_LONG);
        }catch (FileNotFoundException e){
            e.printStackTrace();

        }

    }

}

