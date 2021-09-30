package com.example.apppermisos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {

    private final int permRequestCode = 200;
    private ArrayList<String> permisos = new ArrayList<>();
    Switch opLoc,opCar,opSto,opCon,opPho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        opLoc = findViewById(R.id.opLocation);
        opCar = findViewById(R.id.opCamara);
        opSto = findViewById(R.id.opStorage);
        opPho = findViewById(R.id.opPhone);
        opCon = findViewById(R.id.opContacts);
    }

    @Override
    protected void onResume() {
        super.onResume();
        verificarPermisos();
    }

    private boolean checkedPermission(String permiso){
        int resultpermiss = ContextCompat.checkSelfPermission(getApplicationContext(),permiso);
        return resultpermiss == PackageManager.PERMISSION_GRANTED ;
    }

    private void requestPermission(String[] permiso){
        ActivityCompat.requestPermissions(this,permiso,permRequestCode);
    }

    public void SendPermiss(View view){

        if(opLoc.isChecked() && !checkedPermission("android.permission.ACCESS_FINE_LOCATION")){
            permisos.add("android.permission.ACCESS_FINE_LOCATION");
        }
        if(opCar.isChecked() && !checkedPermission("android.permission.CAMERA")){
            permisos.add("android.permission.CAMERA");
        }
        if(opCon.isChecked() && !checkedPermission("android.permission.READ_CONTACTS")){
            permisos.add("android.permission.READ_CONTACTS");
        }
        if(opPho.isChecked() && !checkedPermission("android.permission.CALL_PHONE")){
            permisos.add("android.permission.CALL_PHONE");
        }
        if(opSto.isChecked() && !checkedPermission("android.permission.READ_EXTERNAL_STORAGE")){
            permisos.add("android.permission.READ_EXTERNAL_STORAGE");
        }

        String arregloString[] = permisos.toArray(new String[permisos.size()]);

        if(permisos.isEmpty()){
            Intent intent = new Intent(this,permisos.class);
            startActivity(intent);
        }else{
            requestPermission(arregloString);
        }

    }

    public void CerrarApp(View view){
        finish();
    }

    public void verificarPermisos(){
        if(checkedPermission("android.permission.ACCESS_FINE_LOCATION")){
            opLoc.setChecked(true);
            opLoc.setClickable(false);
        }
        if(checkedPermission("android.permission.CAMERA")){
            opCar.setChecked(true);
            opCar.setClickable(false);
        }
        if(checkedPermission("android.permission.READ_CONTACTS")){
            opCon.setChecked(true);
            opCon.setClickable(false);

        }
        if(checkedPermission("android.permission.CALL_PHONE")){
            opPho.setChecked(true);
            opPho.setClickable(false);
        }
        if(checkedPermission("android.permission.READ_EXTERNAL_STORAGE")){
            opSto.setChecked(true);
            opSto.setClickable(false);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        Intent intent = new Intent(this,permisos.class);
        startActivity(intent);

    }


}