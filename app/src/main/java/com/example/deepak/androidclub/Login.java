package com.example.deepak.androidclub;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import maes.tech.intentanim.CustomIntent;


public class Login extends AppCompatActivity {

    private static final String TAG = "";
    private FirebaseAuth mAuth;
    private FirebaseUser user;


    String email,password;
    EditText emaile,pass;
    Button login,reg_btn;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBar = findViewById(R.id.progressBar_login);
        progressBar.setVisibility(View.INVISIBLE);

       /** CustomIntent.customType(this,"right-to-left"); //See class by clicking ctrl to see other animation */

       mAuth = FirebaseAuth.getInstance();
       user = mAuth.getCurrentUser();
       
       if(user!=null)
       {
           OnSignedin();
       }

       login = findViewById(R.id.login_btn);
       reg_btn = findViewById(R.id.registration_login_btn);

       login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               progressBar.setVisibility(View.VISIBLE);
               SigninUser();
           }
       });
       reg_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Registeration();
           }
       });
}

    private void Registeration() {
        Intent intent = new Intent(this,Register.class);
        startActivity(intent);
        CustomIntent.customType(this,"right-to-left");
    }

    private void SigninUser() {

        if(!validate())
        {
            return;
        }

        emaile = findViewById(R.id.username_login);
        pass = findViewById(R.id.password_login);

        String email = emaile.getText().toString();
        String password = pass.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //Signed in
                            progressBar.setVisibility(View.INVISIBLE);
                            OnSignedin();
                        } else {
                            progressBar.setVisibility(View.INVISIBLE);
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(Login.this, task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private boolean validate() {

        emaile = findViewById(R.id.username_login);
        pass = findViewById(R.id.password_login);

        boolean valid = true;
        String email = emaile.getText().toString();
        String password = pass.getText().toString();

        if (TextUtils.isEmpty(email)) {

            emaile.setError("Required.");
            progressBar.setVisibility(View.INVISIBLE);

            valid = false;

        } else {

           emaile.setError(null);

        }

        if (TextUtils.isEmpty(password)) {

            pass.setError("Required.");
            progressBar.setVisibility(View.INVISIBLE);

            valid = false;

        } else {

            pass.setError(null);

        }

        return valid;
    }

    private void OnSignedin() {
        Intent intent = new Intent(Login.this,MainActivity.class);
        startActivity(intent);
       CustomIntent.customType(this,"right-to-left"); //See class by clicking ctrl to see other animation
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(user!=null)
        {
            OnSignedin();
        }
    }
}
