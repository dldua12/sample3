package com.example.firebaseemailaccount;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        mf = FirebaseAuth.getInstance();

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button logout = findViewById(R.id.btn_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mf.signOut();
                Intent in = new Intent(MainActivity.this, Login.class);
                startActivity(in);
                //탈퇴 처리 : mf.getCurrentUser().delete();
            }
        });
    }
}