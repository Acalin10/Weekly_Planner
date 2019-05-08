package com.example.weeklyplanner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class RecipeBookAdapter  extends BaseAdapter {
    ArrayList<Recipe> myRecipies;
    LayoutInflater mInflater;
    RecipeBookAdapter(Context context, ArrayList<Recipe> recipeBook, File file){
        this.myRecipies=recipeBook;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return myRecipies.size();
    }

    @Override
    public Object getItem(int position) {
        return myRecipies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.ingredients_view, null);
        TextView recipeTextView = v.findViewById(R.id.recipeText);
        ImageButton delete_recipe = v.findViewById(R.id.recipe_delete);
        delete_recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        String name = myRecipies.get(position).getName();
        recipeTextView.setText(name);
        return v;
    }
}


