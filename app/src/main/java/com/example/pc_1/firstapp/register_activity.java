
package com.example.pc_1.firstapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class register_activity extends AppCompatActivity {

    EditText correo,contraseña,rcontraseña;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_activity);

        correo = findViewById(R.id.correoid);
        contraseña = findViewById(R.id.contraseñaid);
        rcontraseña = findViewById(R.id.rcontraseñaid);

        inicializar();
    }

    private void inicializar(){

        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if(firebaseUser!=null){
                    Log.d("firebaseuser","usuario logueado : "+firebaseUser.getEmail());
                }else{
                    Log.d("firebaseuser","cesion cerrada por el usuario");
                }
            }
        };
    }

    public void guardar(View view) {

        if (correo.getText().toString().equals("") || contraseña.getText().toString().equals("") || rcontraseña.getText().toString().equals("")) { //verifico que no hayan campos vacios
            Toast.makeText(this, "Faltan Datos", Toast.LENGTH_SHORT).show();
        }else {
            if(contraseña.getText().toString().equals(rcontraseña.getText().toString())) {

                firebaseAuth.createUserWithEmailAndPassword(correo.getText().toString(),contraseña.getText().toString()).addOnCompleteListener(this,
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(register_activity.this,"Cuenta creada",Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(register_activity.this,"Error al crear cuenta",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                Intent regeresomain = new Intent();
                regeresomain.putExtra("email", correo.getText().toString());          //esta parte es para devolverme al MainActivity y enviarle el correo y la contraseña
                regeresomain.putExtra("password", contraseña.getText().toString());
                setResult(RESULT_OK, regeresomain);
                finish();
            }else {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
