package com.example.firebaseemailaccount;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {

    private FirebaseAuth mF;
    private DatabaseReference mD;
    private EditText email, pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        mF = FirebaseAuth.getInstance();
        mD = FirebaseDatabase.getInstance().getReference("minji");

        email = findViewById(R.id.edi_email);
        pw = findViewById(R.id.edi_pw);

        Button login = findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String sEmail = email.getText().toString();
                String sPw = pw.getText().toString();

                mF.signInWithEmailAndPassword(sEmail,sPw).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent in = new Intent(Login.this, MainActivity.class);
                            startActivity(in);
                        } else {
                            Toast.makeText(Login.this, "실패", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        Button regi = findViewById(R.id.btn_regi);
        regi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Login.this, Register.class);
                startActivity(in);
            }
        });
    }
}