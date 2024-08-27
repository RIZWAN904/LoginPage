package com.example.javapractice;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpPage extends AppCompatActivity {

    ProgressBar progressBar;
    FirebaseAuth mAuth;
    TextView logintext, editText_username, create_password, conformText_password;
    Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        logintext = findViewById(R.id.login);
        progressBar = findViewById(R.id.progressbar);
        editText_username = findViewById(R.id.editText_username);
        create_password = findViewById(R.id.create_password);
        conformText_password = findViewById(R.id.conformText_password);
        btn_submit = findViewById(R.id.btn_submit);

        mAuth = FirebaseAuth.getInstance();

        logintext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpPage.this, LoginButton2.class);
                startActivity(intent);
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                String username = String.valueOf(editText_username.getText());
                String createpassword = String.valueOf(create_password.getText());
                String comformpassword = String.valueOf(conformText_password.getText());

                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(SignUpPage.this, "Enter email", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                if (TextUtils.isEmpty(createpassword)) {
                    Toast.makeText(SignUpPage.this, "Enter user password", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                if (!comformpassword.equals(createpassword)) {
                    Toast.makeText(SignUpPage.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                mAuth.createUserWithEmailAndPassword(username, createpassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(SignUpPage.this, LoginButton2.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Exception exception = task.getException();
                                    if (exception != null) {
                                        Toast.makeText(SignUpPage.this, "Authentication failed: " + exception.getMessage(), Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(SignUpPage.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });

            }
        });

    }
}
