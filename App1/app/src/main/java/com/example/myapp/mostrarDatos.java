package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

public class mostrarDatos extends AppCompatActivity {

    private ArrayList<String> lenguajes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_datos);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();

        TextView vnombre = findViewById(R.id.showNombre);
        TextView vfechagenero= findViewById(R.id.showfechagenero);
        TextView vlenguajes = findViewById(R.id.showlenguaje);

        vnombre.setText("Hola!, Mi nombre es "+intent.getStringExtra("nombre")+".");
        vfechagenero.setText("Soy "+intent.getStringExtra("genero") + ", y en fecha "+ intent.getStringExtra("fecha")+".");
        if(intent.getStringExtra("opcion").equals("Si")){

            lenguajes = (ArrayList<String>) intent.getSerializableExtra("lenguajes");
            String lista = "";
            int i = 0;

            for (String s:lenguajes) {
                i++;
                if(lenguajes.size() == i){
                    lista += s + ". ";
                }else{
                    lista += s + ", ";
                }

            }

            vlenguajes.setText("Me gusta Programar. Mis lenguajes favoritos son: "+lista);
        }else{
            vlenguajes.setText("No me gusta Programar.");
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}