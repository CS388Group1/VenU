package com.example.venu;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.example.venu.models.Badge;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.ParseException;
import com.parse.SignUpCallback;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class RegistrationActivity extends AppCompatActivity {
    public static final String TAG =  "RegisterActivity";
    private EditText etEmail;
    private EditText etUsername;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private Button btnRegister;
    private EditText etBio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);
        etBio = findViewById(R.id.etAbout);

        String password = etPassword.getText().toString();
        Log.i(TAG,password);

        String confPassword = etConfirmPassword.getText().toString();
        Log.i(TAG,confPassword);

        btnRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (password.equals(confPassword)) {
                    ParseUser user = new ParseUser();
                    // Set core properties
                    user.setEmail(etEmail.getText().toString());
                    user.setUsername(etUsername.getText().toString());
                    user.setPassword(etPassword.getText().toString());
                    user.put("bio", etBio.getText().toString());
                    // Invoke signUpInBackground
                    user.signUpInBackground(new SignUpCallback() {
                        public void done(ParseException e) {
                            if (e == null) {
                                // Hooray! Let them use the app now.
                                goLoginActivity();
                                Toast.makeText(RegistrationActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                            } else {
                                // Sign up didn't succeed. Look at the ParseException
                                // to figure out what went wrong
                                Log.e(TAG, "Issue with sign up!", e);
                                Toast.makeText(RegistrationActivity.this, "Issue with sign up!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Log.e(TAG, "Passwords do not match.");
                    Toast.makeText(RegistrationActivity.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }

    private void goLoginActivity() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }

}
