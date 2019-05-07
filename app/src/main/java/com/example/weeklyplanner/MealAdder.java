package com.example.weeklyplanner;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MealAdder extends AppCompatActivity {
    ListView ingredients_list;
    ArrayList<String> ingredientsForDay;
    File ingredientsForDay_list;
    Button add_ingredient;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_adder);
        ingredients_list = findViewById(R.id.ingredients_list);
        ingredientsForDay_list = new File(getApplicationContext().getFilesDir(),"IngredientsForChosenDays.txt");
        ingredientsForDay = ShoppingList.loadArray(ingredientsForDay_list);
        add_ingredient = (Button) findViewById(R.id.add_ingredientsBtn);
        add_ingredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_ingredient.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast toast = Toast.makeText(getApplicationContext(), "button has been pressed", Toast.LENGTH_LONG);
                        toast.show();
                        AlertDialog alertDialog = new AlertDialog.Builder(MealAdder.this).create();
                        alertDialog.setTitle("Add Ingredient");
                        alertDialog.setMessage("Write an ingredient below and click ok");
                        final EditText input;
                        input = new EditText(getApplicationContext());
                        alertDialog.setView(input);
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        ingredientsForDay.add(ShoppingList.preferedCase(input.getText().toString()));
                                        Log.i("my message",input.getText().toString());
                                        //  ShoppingList shoppingList = new ShoppingList();
                                        //  shoppingList.list_items.add(ingredient);
                                        //shoppingList.list_items_days.add(ShoppingList.preferedCase(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("currentDay",null)));
                                        //ShoppingList.saveArray(shoppingList.list_items,shoppingList.shopping_list);
                                        //ShoppingList.saveArray(shoppingList.list_items_days,shoppingList.shopping_list_days);
                                        ShoppingList.saveArray(ingredientsForDay,ingredientsForDay_list);
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                    }
                });
            }
        });
        ingredients_list.setAdapter(new IngredientAdapter(this,ingredientsForDay,ingredientsForDay_list));
    }
}
