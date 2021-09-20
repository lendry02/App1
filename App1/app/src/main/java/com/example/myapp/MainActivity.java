package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText fecha,nombre,apellido;
    Spinner genero;
    RadioButton opcionProgramarN,opcionProgramarS;
    Button enviar,limpiar;
    CheckBox c1,c2,c3,c4,c5,c6;
    private ArrayList<String> lenguajes = new ArrayList<>();
    private int dias,mes,ano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.cbxGenero);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.generos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        fecha = findViewById(R.id.txtFecha);
        nombre = findViewById(R.id.txtNombre);
        apellido = findViewById(R.id.txtApellido);
        genero = findViewById(R.id.cbxGenero);
        opcionProgramarN = findViewById(R.id.radioButton4);
        opcionProgramarS = findViewById(R.id.radioButton3);
        enviar = findViewById(R.id.button);
        limpiar = findViewById(R.id.button2);
        c1 = findViewById(R.id.checkBox);
        c2 = findViewById(R.id.checkBox2);
        c3 = findViewById(R.id.checkBox3);
        c4 = findViewById(R.id.checkBox4);
        c5 = findViewById(R.id.checkBox5);
        c6 = findViewById(R.id.checkBox6);

        fecha.setOnClickListener(this);
        enviar.setOnClickListener(this);
        limpiar.setOnClickListener(this);

       //Se puede hacer creando un metodo y buscar el atributo action colocando el metodo deseado
    }

    @Override
    public void onClick(View view) {
        if(view == fecha){
            final Calendar calendario = Calendar.getInstance();
            dias = calendario.get(Calendar.DAY_OF_MONTH);
            mes = calendario.get(Calendar.MONTH);
            ano = calendario.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {
                     fecha.setText(dia+"/"+(mes+1)+ "/" +ano);
                }
            },ano,mes,dias);

            datePickerDialog.show();

        }
        if (view == limpiar){
            limpiar();
        }

        if (view == enviar){

            if(nombre.getText().toString().isEmpty()){
                Toast.makeText(this,"Campo Nombre vacio",Toast.LENGTH_LONG).show();
                return;
            }
            if(apellido.getText().toString().isEmpty()){
                Toast.makeText(this,"Campo Apellido vacio",Toast.LENGTH_LONG).show();
                return;
            }
            if(fecha.getText().toString().isEmpty()){
                Toast.makeText(this,"Campo Fecha vacio",Toast.LENGTH_LONG).show();
                return;
            }

            Intent intent = new Intent(this,mostrarDatos.class);


            if(opcionProgramarN.isChecked()){
                intent.putExtra("opcion",opcionProgramarN.getText().toString());
                if(c1.isChecked() || c2.isChecked() || c3.isChecked() || c4.isChecked() || c5.isChecked()  || c6.isChecked()){
                    Toast.makeText(this,"No puede seleccionar ningun Lenguaje",Toast.LENGTH_LONG).show();
                    return;
                }
            }else{
                intent.putExtra("opcion",opcionProgramarS.getText().toString());
                if(!(c1.isChecked() || c2.isChecked() || c3.isChecked() || c4.isChecked() || c5.isChecked()  || c6.isChecked())){
                    Toast.makeText(this,"Campo Lenguaje vacio",Toast.LENGTH_LONG).show();
                    return;
                }
            }

           agregarCheck();

            intent.putExtra("lenguajes",lenguajes);
            intent.putExtra("nombre",nombre.getText().toString());
            intent.putExtra("fecha",fecha.getText().toString());
            intent.putExtra("genero",genero.getSelectedItem().toString());

            startActivity(intent);
        }
    }

    public void limpiar(){
        nombre.setText("");
        apellido.setText("");
        fecha.setText("");
        genero.setSelection(0);
        opcionProgramarS.setChecked(true);
        opcionProgramarN.setChecked(false);
        c1.setChecked(false);
        c2.setChecked(false);
        c3.setChecked(false);
        c4.setChecked(false);
        c5.setChecked(false);
        c6.setChecked(false);
    }

    public void agregarCheck(){
        if(c1.isChecked()){
            lenguajes.add("Java");
        }
        if(c2.isChecked()){
            lenguajes.add("JS");
        }
        if(c3.isChecked()){
            lenguajes.add("Python");
        }
        if(c4.isChecked()){
            lenguajes.add("C/C++");
        }
        if(c5.isChecked()){
            lenguajes.add("C#");
        }
        if(c6.isChecked()){
            lenguajes.add("Go Lang");
        }
    }
}