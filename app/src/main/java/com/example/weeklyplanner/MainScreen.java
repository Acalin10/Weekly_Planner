package com.example.weeklyplanner;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainScreen extends AppCompatActivity {
   ListView planner;
   ArrayList<String> dates;
   ImageButton addMeal;
   ImageView meals;
   Calendar calendar = Calendar.getInstance();
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if (item.getItemId() == R.id.main_nav_seeList) {
                Intent i = new Intent(MainScreen.this, ShoppingList.class);

                startActivity(i);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
            return false;
        }
    };
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        BottomNavigationView navView = findViewById(R.id.main_nav);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        planner=findViewById(R.id.planner);
        dates = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE dd");
        String currentDay = sdf.format(calendar.getTime());
        dates.add(currentDay);
        for(int i = 0; i<5; i++){
            calendar.add(Calendar.DATE,1);
            String nextDay = sdf.format(calendar.getTime());
            dates.add(nextDay);
        }
        planner.setAdapter(new PlannerAdapter(this,dates,addMeal,meals));
    }

}
