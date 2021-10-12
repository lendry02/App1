package com.example.appfragmentos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int columnCount = 1;


        int orientacion = getResources().getConfiguration().orientation;

        if(orientacion == Configuration.ORIENTATION_LANDSCAPE){
            setContentView(R.layout.activity_main_dua);
            columnCount =1;
        }else{
            setContentView(R.layout.activity_main);
        }

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_parent,ItemFragment.newInstance(columnCount))
                .commit();
    }
}