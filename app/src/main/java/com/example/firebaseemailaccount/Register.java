package com.example.firebaseemailaccount;

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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    private FirebaseAuth mF;
    private DatabaseReference mD;
    private EditText email, pw;
    private Button regi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        mF = FirebaseAuth.getInstance();
        mD = FirebaseDatabase.getInstance().getReference("minji");

        email = findViewById(R.id.edi_email);
        pw = findViewById(R.id.edi_pw);
        regi = findViewById(R.id.btn_regi);

        regi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sEmail = email.getText().toString();
                String sPw = pw.getText().toString();

                mF.createUserWithEmailAndPassword(sEmail, sPw).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user = mF.getCurrentUser();
                            UserAccount account = new UserAccount();
                            account.setIdToken(user.getUid());
                            account.setEmailId(user.getEmail());
                            account.setPassword(sPw);

                            mD.child("UserAccount").child(user.getUid()).setValue(account);

                            Toast.makeText(Register.this, "성공", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(Register.this, "실패", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}