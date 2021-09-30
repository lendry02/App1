package com.example.apppermisos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.util.Locale;

public class permisos extends AppCompatActivity implements View.OnClickListener{

    Button  blocation,bcamera,bstorage,bphone,bcontacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permisos);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        blocation = findViewById(R.id.blocation);
        bcamera = findViewById(R.id.bcamara);
        bstorage = findViewById(R.id.bstorage);
        bphone = findViewById(R.id.bphone);
        bcontacts = findViewById(R.id.bcontacts);

        blocation.setOnClickListener(this);
        bcamera.setOnClickListener(this);
        bstorage.setOnClickListener(this);
        bphone.setOnClickListener(this);
        bcontacts.setOnClickListener(this);
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

    @Override
    public void onClick(View view){
        Intent intent;

        switch (view.getId()){
            case R.id.bcamara:
                if(checkedPermission("android.permission.CAMERA")){
                    intent = new Intent("android.media.action.IMAGE_CAPTURE");
                    startActivity(intent);
                }else{
                    Toast.makeText(this,"Please request Permission", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.blocation:
                if(checkedPermission("android.permission.ACCESS_FINE_LOCATION")){
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.setPackage("com.google.android.apps.maps");
                    startActivity(intent);

                }else{
                    Toast.makeText(this,"Please request Permission", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.bstorage:
                if(checkedPermission("android.permission.READ_EXTERNAL_STORAGE")){
                    intent = new Intent(Intent.ACTION_GET_CONTENT);
                    Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath() + "/myFolder/");
                    intent.setDataAndType(uri, "*/*");
                    startActivity(Intent.createChooser(intent, "Open folder"));

                }else{
                    Toast.makeText(this,"Please request Permission", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.bphone:
                if(checkedPermission("android.permission.CALL_PHONE")){
                    intent = new Intent(android.content.Intent.ACTION_CALL, Uri.parse("tel:8099040471"));
                    startActivity(intent);
                }else{
                    Toast.makeText(this,"Please request Permission", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.bcontacts:
                if(checkedPermission("android.permission.READ_CONTACTS")){
                    intent = new Intent(Intent.ACTION_PICK,  ContactsContract.Contacts.CONTENT_URI);
                    startActivity(intent);
                }else{
                    Toast.makeText(this,"Please request Permission", Toast.LENGTH_LONG).show();
                }
                break;
        }

    }

    private boolean checkedPermission(String permiso){
        int resultpermiss = ContextCompat.checkSelfPermission(getApplicationContext(),permiso);
        return resultpermiss == PackageManager.PERMISSION_GRANTED ;
    }
}