package com.example.weeklyplanner;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class RecipeBook extends Activity {
    ArrayList<String> allRecipiesString;
    ArrayList<Recipe> allRecipies;
    ListView recipe_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_book);
        allRecipiesString = getIntent().getStringArrayListExtra("allRecipiesForBook");
        Log.i(String.valueOf(allRecipiesString),"recipies arrived");
    }
}
