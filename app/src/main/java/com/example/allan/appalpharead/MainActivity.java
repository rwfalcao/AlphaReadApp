package com.example.allan.appalpharead;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        final EditText email = findViewById(R.id.Username_login);
        final EditText senha = findViewById(R.id.Password_login);
        Button login = findViewById(R.id.Login_login);

        Button registar = findViewById(R.id.Registrar_login);
        registar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), Registrar.class);
                startActivity(it);
            }
        });

        Button RecuperarSenha = findViewById(R.id.RecuperarSenha_login);
        RecuperarSenha.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                /*
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Aviso");
                alert
                        .setMessage("Sua senha foi enviada para o e-mail cadastrado!")
                        .setIcon(R.drawable.notification);
                AlertDialog alertDialog = alert.create();
                alertDialog.show();
                */
                startActivity(new Intent(MainActivity.this, RetrieveActivity.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailStr = (String) email.getText().toString();
                String senhaStr = (String) senha.getText().toString();

                login(emailStr,senhaStr);
            }
        });

    }
    public void login(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("log", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(MainActivity.this, "Authentication success.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
