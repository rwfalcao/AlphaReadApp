package com.example.allan.appalpharead;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class RetrieveActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    Button retrieveBtn;
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);

        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.Email_retrieve);
        retrieveBtn = findViewById(R.id.Retrieve_retrieve);

        retrieveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrievePassword();
            }
        });
    }

    public void retrievePassword(){

        String emailStr = email.getText().toString();

        mAuth.sendPasswordResetEmail(emailStr).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(RetrieveActivity.this, "Email com instruções para criação de nova senha enviado!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(RetrieveActivity.this, MainActivity.class));
                }
            }
        });
    }
}
