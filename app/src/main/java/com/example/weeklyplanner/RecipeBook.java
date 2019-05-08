package com.example.weeklyplanner;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

public class RecipeBook extends Activity {
    ArrayList<String> allRecipiesString;
    ArrayList<Recipe> allRecipiesNew;
    ArrayList<Recipe> allRecipies;
    ArrayList<String> allRecipiesNewString;
    File savedRecipies;
    ListView recipe_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recipe_list = findViewById(R.id.recipe_book_list);
        setContentView(R.layout.activity_recipe_book);
        allRecipiesNewString = getIntent().getStringArrayListExtra("allRecipiesForBook");
        savedRecipies = new File(getApplicationContext().getFilesDir(),"savedRecipies.txt");
        allRecipiesString = ShoppingList.loadArray(savedRecipies);
        MainScreen.initialise(allRecipiesString);
        MainScreen.initialise(allRecipiesNewString);
        MainScreen.initialise(allRecipiesNew);
        MainScreen.initialise(allRecipies);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.7));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity= Gravity.CENTER;
        params.x=0;
        params.y= -20;
        getWindow().setAttributes(params);
        for(int i=0;i<allRecipiesNewString.size();i++){
            allRecipiesNew.add(Recipe.toRecipe(allRecipiesNewString.get(i)));
        }
        for(int i=0;i<allRecipiesString.size();i++){
            allRecipies.add(Recipe.toRecipe(allRecipiesString.get(i)));
        }
        if(allRecipies != null) {
            allRecipies.addAll(allRecipiesNew);
            for (int i = 0; i < allRecipies.size(); i++) {
                for (int j = 0; j < allRecipies.size(); j++) {
                    if (allRecipies.get(i).getName().equals(allRecipies.get(j).getName())) {
                        if (i < j) {
                            allRecipies.remove(i);
                        }
                        if (j < i) {
                            allRecipies.remove(j);
                        }
                    }
                }
            }

            for (int i = 0; i < allRecipies.size(); i++) {
                allRecipiesString.add(allRecipies.get(i).toString());
            }

            ShoppingList.saveArray(allRecipiesString, savedRecipies);
            recipe_list.setAdapter(new RecipeBookAdapter(this, allRecipies, savedRecipies));
        }
    }
}
