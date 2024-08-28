package com.example.javapractice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class LoginButton2 extends AppCompatActivity {

    EditText username, password;
    Button button;
    TextView signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_button2);

        // Link the UI components to the Java code
        username = findViewById(R.id.editText_username);
        password = findViewById(R.id.editText_password);
        signup = findViewById(R.id.sign_up);
        button = findViewById(R.id.btn_submit);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginButton2.this, SignUpPage.class);
                startActivity(intent);
            }
        });


        // Set the button click listener
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the input values from the EditText fields
                String inputUsername = username.getText().toString().trim();
                String inputPassword = password.getText().toString().trim();

                // Check if the entered username and password match the dummy values
                if (inputUsername.equals(correctUsername) && inputPassword.equals(correctPassword)) {
                    // If login is successful, go to the next activity
                    Intent intent = new Intent(LoginButton2.this, SignUpPage.class);
                    startActivity(intent);
                    finish(); // Finish the current activity so the user can't go back to it
                } else {
                    // If login fails, show a toast message
                    Toast.makeText(LoginButton2.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
