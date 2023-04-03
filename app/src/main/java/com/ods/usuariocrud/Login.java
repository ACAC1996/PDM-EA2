package com.ods.usuariocrud;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    Button login, register;
    EditText usuario, password;

    private Cursor fila;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuario= findViewById(R.id.txtEmail);
        password=findViewById(R.id.txtPassword);
        login=findViewById(R.id.btnLogin);
        register=findViewById(R.id.btnRegister);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usuario.setError(null);
                password.setError(null);
                if(usuario.getText().toString().equals("")){
                    usuario.setError("Usuario vacío");
                    usuario.requestFocus();
                } else if (usuario.getText().toString().length()<5) {
                    usuario.setError("Usuario inválido");
                    usuario.requestFocus();
                } else {
                    Pattern pattern = Pattern
                            .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

                    // El email a validar
                    String email = usuario.getText().toString();

                    Matcher mather = pattern.matcher(email);

                    if (mather.find() == false) {
                        usuario.setError("El email ingresado es inválido.");
                    }
                }
                if(password.getText().toString().equals("")){
                    password.setError("Contraseña vacía");
                    password.requestFocus();
                } else if (password.getText().toString().length()<8) {
                    password.setError("Contraseña inválida");
                    password.requestFocus();
                }
                AdminSQLite admin=new AdminSQLite(getApplicationContext(),"usuario",null,1);
                SQLiteDatabase db=admin.getWritableDatabase();

                String user=usuario.getText().toString();
                String pass=password.getText().toString();

                fila=db.rawQuery("select * from usuario where email= ? and contrasena = ?",new String[]{user,pass});


                try{
                    if(fila.moveToFirst()){
                        String usua=fila.getString(0);
                        String passw=fila.getString(1);

                        if(user.equals(usua) && pass.equals(passw)){
                            Intent i= new Intent(Login.this,Success.class);
                            i.putExtra("user",user);
                            startActivityForResult(i,1234);
                            startActivity(i);
                            usuario.setText("");
                            password.setText("");
                        }
                    }else{
                        Toast.makeText(Login.this, "Datos incorrectos", Toast.LENGTH_SHORT).show();
                    }
                }catch(Exception e){
                    Toast.makeText(Login.this, "Error", Toast.LENGTH_SHORT).show();
                }


            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(Login.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
