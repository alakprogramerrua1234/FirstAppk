package com.example.pc_1.firstapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class IniciarSesionActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    private static final int LOGIN_CON_GOOGLE = 1;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private GoogleApiClient googleApiClient;
    private Button btnSignInGoogle;
    EditText correo,contraseña;
    String scorreo="",scontraseña="";
    boolean cr,cn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        correo = findViewById(R.id.eUsuario);
        contraseña = findViewById(R.id.econtraseña);
        btnSignInGoogle = findViewById(R.id.btnSignInGoogle);

       inicializar();

       btnSignInGoogle.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Toast.makeText(IniciarSesionActivity.this, "presionado", Toast.LENGTH_SHORT).show();
               Log.d("googleboton","boton presionado");
               Intent i = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
               startActivityForResult(i,LOGIN_CON_GOOGLE);
           }
       });
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

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    // estas funciones hay que crearlas , no estoy seguro porque pero hay que hacerlo
    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(authStateListener);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override   // esta funcion se activa cuando me envian datos de register_activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //aqui confirmamos que esta respuesta si sea de register_activity ( mire la linea 78)
        if(requestCode==123 && resultCode==RESULT_OK){

            if(data.getExtras()!=null) {
                scorreo = data.getExtras().getString("email");  //obtenemos los datos de la respuesta de register
                scontraseña = data.getExtras().getString("password");
            }

        }else if(requestCode==LOGIN_CON_GOOGLE){
            GoogleSignInResult googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            signInGoogle(googleSignInResult);

        }else Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show();
    }

    private void signInGoogle (GoogleSignInResult googleSignInResult){
        if(googleSignInResult.isSuccess()){
            AuthCredential authCredential = GoogleAuthProvider.getCredential(
                    googleSignInResult.getSignInAccount().getIdToken(), null);
            firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(this,
                    new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            goprincipal(2);
                        }
                    });
        }
    }

    public void registrarse(View view) {  // con esta funcion pasamos a register_activity(se activa si le da click en registro)

        Intent regis = new Intent(this,register_activity.class);
        startActivityForResult(regis,123); //se pone asi porque se va a esperar una respuesta, sino esperamos nada seria  solo startActivity
    }

    public void Ingresar(View view) {  // se activa al presionar ingresar

        if (correo.getText().toString().equals("") || contraseña.getText().toString().equals("")) { //verifico que no hayan campos vacios
            Toast.makeText(this, "Faltan Datos", Toast.LENGTH_SHORT).show();
        } else {

            firebaseAuth.signInWithEmailAndPassword(correo.getText().toString(),contraseña.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){

                        cr = true;
                        cn = true;
                        Log.d("FirebaseUser","true");
                        goprincipal(1);

                    }else{
                        Toast.makeText(IniciarSesionActivity.this, "Usuario o contraseña incorrectos"+task.getException(),Toast.LENGTH_SHORT).show();
                    }
                }

            });
            /*if (correo.getText().toString().equals(scorreo)) {
                cr = true;                                      //verifico que los datos si correspondan
                if (contraseña.getText().toString().equals(scontraseña)) {
                    cn = true;
                } else Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            } else Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();*/
        }

    }

    public void goprincipal(int a){
        Intent ingreso = new Intent(this,menu_lateral.class);   // si los datos estan correctos paso a Actividad_principal
        //ingreso.putExtra("usuario",scorreo);
        //ingreso.putExtra("contraseña",scontraseña);
        ingreso.putExtra("Inicio_con",a);
        startActivity(ingreso);
    }

}
