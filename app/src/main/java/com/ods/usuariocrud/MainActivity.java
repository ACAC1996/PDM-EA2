package com.ods.usuariocrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText documento, nombre, apellido, email, contrasena;
    Button guardar, consultar, editar, eliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        documento=findViewById(R.id.txtDocumento);
        nombre=findViewById(R.id.txtNombre);
        apellido=findViewById(R.id.txtApellido);
        email=findViewById(R.id.txtEmail);
        contrasena=findViewById(R.id.txtContrasena);
        guardar=findViewById(R.id.btnGuardar);
        consultar=findViewById(R.id.btnConsultar);
        editar=findViewById(R.id.btnEditar);
        eliminar=findViewById(R.id.btnEliminar);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSQLite admin= new AdminSQLite(getApplicationContext(),"Usuario",null, 1);
                SQLiteDatabase db= admin.getWritableDatabase();


                if(documento.getText().toString().equals("")) {
                    documento.setError("Email vacío");
                    documento.requestFocus();
                    return;
                }
                if(nombre.getText().toString().equals("")) {
                    nombre.setError("Email vacío");
                    nombre.requestFocus();
                    return;
                }
                if(apellido.getText().toString().equals("")) {
                    apellido.setError("Email vacío");
                    apellido.requestFocus();
                    return;
                }
                if(email.getText().toString().equals("")){
                    email.setError("Email vacío");
                    email.requestFocus();
                    return;
                } else if (email.getText().toString().length()<5) {
                    email.setError("Email inválido");
                    email.requestFocus();
                    return;
                } else {
                    Pattern pattern = Pattern
                            .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

                    // El email a validar
                    String em = email.getText().toString();

                    Matcher mather = pattern.matcher(em);

                    if (mather.find() == false) {
                        email.setError("El email ingresado es inválido.");
                        return;
                    }
                }
                if(contrasena.getText().toString().equals("")){
                    contrasena.setError("Contraseña vacía");
                    contrasena.requestFocus();
                    return;
                } else if (contrasena.getText().toString().length()<8) {
                    contrasena.setError("Contraseña inválida");
                    contrasena.requestFocus();
                    return;
                }

                int doc=Integer.parseInt(documento.getText().toString());
                String nom=nombre.getText().toString();
                String ape=apellido.getText().toString();
                String correo=email.getText().toString();
                String pass=contrasena.getText().toString();

                ContentValues datos=new ContentValues();
                datos.put("documento",doc);
                datos.put("nombre",nom);
                datos.put("apellido",ape);
                datos.put("email",correo);
                datos.put("contrasena",pass);

                try{
                    db.insert("usuario",null,datos);
                    db.close();

                    documento.setText("");
                    nombre.setText("");
                    apellido.setText("");
                    email.setText("");
                    contrasena.setText("");

                    Toast.makeText(MainActivity.this, "Usuario creado con éxito", Toast.LENGTH_SHORT).show();
                }catch(Exception e){
                    Toast.makeText(MainActivity.this, "Error de creación", Toast.LENGTH_SHORT).show();
                }

            }
        });

        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSQLite admin= new AdminSQLite(getApplicationContext(),"Usuario",null, 1);
                SQLiteDatabase db= admin.getWritableDatabase();



                int doc=Integer.parseInt(documento.getText().toString());

                Cursor fila =db.rawQuery("select * from usuario where documento="+doc,null);
                if(fila.moveToFirst()){
                    nombre.setText(fila.getString(1));
                    apellido.setText(fila.getString(2));
                    email.setText(fila.getString(3));
                    contrasena.setText(fila.getString(4));
                }else{
                    Toast.makeText(MainActivity.this, "El usuario no existe", Toast.LENGTH_SHORT).show();
                }
                db.close();

            }
        });



        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSQLite admin= new AdminSQLite(getApplicationContext(),"Usuario",null, 1);
                SQLiteDatabase db= admin.getWritableDatabase();

                if(documento.getText().toString().equals("")) {
                    documento.setError("Email vacío");
                    documento.requestFocus();
                    return;
                }
                if(nombre.getText().toString().equals("")) {
                    nombre.setError("Email vacío");
                    nombre.requestFocus();
                    return;
                }
                if(apellido.getText().toString().equals("")) {
                    apellido.setError("Email vacío");
                    apellido.requestFocus();
                    return;
                }
                if(email.getText().toString().equals("")){
                    email.setError("Email vacío");
                    email.requestFocus();
                    return;
                } else if (email.getText().toString().length()<5) {
                    email.setError("Email inválido");
                    email.requestFocus();
                    return;
                } else {
                    Pattern pattern = Pattern
                            .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

                    // El email a validar
                    String em = email.getText().toString();

                    Matcher mather = pattern.matcher(em);

                    if (mather.find() == false) {
                        email.setError("El email ingresado es inválido.");
                        return;
                    }
                }
                if(contrasena.getText().toString().equals("")){
                    contrasena.setError("Contraseña vacía");
                    contrasena.requestFocus();
                    return;
                } else if (contrasena.getText().toString().length()<8) {
                    contrasena.setError("Contraseña inválida");
                    contrasena.requestFocus();
                    return;
                }

                int doc=Integer.parseInt(documento.getText().toString());
                String nom=nombre.getText().toString();
                String ape=apellido.getText().toString();
                String correo=email.getText().toString();
                String pass=contrasena.getText().toString();

                ContentValues updateusu=new ContentValues();
                updateusu.put("nombre",nom);
                updateusu.put("apellido",ape);
                updateusu.put("email",correo);
                updateusu.put("contrasena",pass);

                try{
                    db.update("usuario",updateusu,"documento="+doc,null);
                    Toast.makeText(MainActivity.this, "Usuario actualizado con éxito", Toast.LENGTH_SHORT).show();
                    documento.setText("");
                    nombre.setText("");
                    apellido.setText("");
                    email.setText("");
                    contrasena.setText("");
                    db.close();
                }catch(Exception e){
                Toast.makeText(MainActivity.this, "Error consulta", Toast.LENGTH_SHORT).show();
            }

            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSQLite admin= new AdminSQLite(getApplicationContext(),"Usuario",null, 1);
                SQLiteDatabase db= admin.getWritableDatabase();

                if(documento.getText().toString().equals("")) {
                    documento.setError("Email vacío");
                    documento.requestFocus();
                    return;
                }
                if(nombre.getText().toString().equals("")) {
                    nombre.setError("Email vacío");
                    nombre.requestFocus();
                    return;
                }
                if(apellido.getText().toString().equals("")) {
                    apellido.setError("Email vacío");
                    apellido.requestFocus();
                    return;
                }
                if(email.getText().toString().equals("")){
                    email.setError("Email vacío");
                    email.requestFocus();
                    return;
                } else if (email.getText().toString().length()<5) {
                    email.setError("Email inválido");
                    email.requestFocus();
                    return;
                } else {
                    Pattern pattern = Pattern
                            .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

                    // El email a validar
                    String em = email.getText().toString();

                    Matcher mather = pattern.matcher(em);

                    if (mather.find() == false) {
                        email.setError("El email ingresado es inválido.");
                        return;
                    }
                }
                if(contrasena.getText().toString().equals("")){
                    contrasena.setError("Contraseña vacía");
                    contrasena.requestFocus();
                    return;
                } else if (contrasena.getText().toString().length()<8) {
                    contrasena.setError("Contraseña inválida");
                    contrasena.requestFocus();
                    return;
                }

                int doc=Integer.parseInt(documento.getText().toString());
                try{
                    int c=db.delete("usuario","documento ="+doc,null);
                    if(c>0){
                        Toast.makeText(MainActivity.this, "Usuario eliminado con éxito", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this, "Usuario no existe", Toast.LENGTH_SHORT).show();
                    }
                }catch(Exception e){
                    Toast.makeText(MainActivity.this, "Error consulta", Toast.LENGTH_SHORT).show();
                }

                documento.setText("");
                nombre.setText("");
                apellido.setText("");
                email.setText("");
                contrasena.setText("");
                db.close();
            }
        });
    }
}