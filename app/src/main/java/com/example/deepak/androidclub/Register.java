package com.example.deepak.androidclub;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import maes.tech.intentanim.CustomIntent;

public class Register extends AppCompatActivity {

    private EditText email_field,pass_field,mob_field,regnum_field,name_field;
    Button reg_btn,login_butn;
    Context context;

    String name,email,mobnum,regnum,password;

    //firebase
    private FirebaseAuth mAuth;

    FirebaseDatabase userdatabase;
    DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        context = getApplicationContext();
        reg_btn = findViewById(R.id.register_reg);

        mAuth = FirebaseAuth.getInstance();

        userdatabase = FirebaseDatabase.getInstance();
        users = userdatabase.getReference("myusers");


        //name_field.requestFocus();
        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAuth.getCurrentUser()!=null)
                {
                    Onsignedin();
                }
                else
                {

                    RegisterUser();
                }
            }
        });

        login_butn = findViewById(R.id.login_reg);
        login_butn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginIntent();
            }
        });
    }

    private void LoginIntent() {
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
        CustomIntent.customType(this,"right-to-left");
    }

    private void Onsignedin() {

        Intent intent = new Intent(context,Login.class);
        startActivity(intent);
        CustomIntent.customType(context,"right-to-left");
    }

    private void RegisterUser() {

        email_field = findViewById(R.id.username_reg);
        pass_field = findViewById(R.id.password_reg);
        regnum_field = findViewById(R.id.regnum_reg);
        mob_field = findViewById(R.id.mobnum_reg);
        name_field = findViewById(R.id.name_reg);



        email = email_field.getText().toString();
        name = name_field.getText().toString();
        mobnum = mob_field.getText().toString();
        regnum = regnum_field.getText().toString();
        password = pass_field.getText().toString();

        if(email.isEmpty())
        {
            email_field.setError("required");
            //email_field.requestFocus();
            return;
        }

        if(password.isEmpty())
        {
            pass_field.setError("required");
           // pass_field.requestFocus();
            return;
        }

        if(regnum.isEmpty())
        {
            regnum_field.setError("required");
            //regnum_field.requestFocus();
            return;
        }

        if(name.isEmpty())
        {
            name_field.setError("required");
           // name_field.requestFocus();
            return;
        }

        if(mobnum.isEmpty())
        {
            mob_field.setError("required");
           // mob_field.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

//                            userdatabase = FirebaseDatabase.getInstance();
//                            users = userdatabase.getReference("myusers");
//                            final FirebaseUser user = mAuth.getCurrentUser();
//                            User userdetail = new User(
//                                    name,
//                                    email,
//                                    mobnum,
//                                    regnum,
//                                    user.getUid()
//                            );
                            Toast.makeText(context,"Registered Successfully",Toast.LENGTH_LONG).show();
                            //Onsignedin();


                            boolean check = true;
                            Intent intent = new Intent(Register.this,Betweenregtosignin.class);
                            if(name!=null)
                            {
                                intent.putExtra("name",name);
                            }
                            else
                            {
                                Toast.makeText(context,"name",Toast.LENGTH_LONG).show();
                                check=false;
                            }

                            if(name!=null)
                            {
                                intent.putExtra("email",email);
                            }
                            else
                            {
                                Toast.makeText(context,"email",Toast.LENGTH_LONG).show();
                                check=false;
                            }

                            if(name!=null)
                            {
                                intent.putExtra("mob",mobnum);
                            }
                            else
                            {
                                Toast.makeText(context,"mob",Toast.LENGTH_LONG).show();
                                check=false;
                            }

                            if(name!=null)
                            {
                                intent.putExtra("regnum",regnum);
                            }
                            else
                            {
                                Toast.makeText(context,"regnum",Toast.LENGTH_LONG).show();
                                check=false;
                            }




                            //intent.putExtra("uid",user.getUid());
                            if(check)
                            startActivity(intent);
                           // CustomIntent.customType(context,"right-to-left");
//                            users.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                                    .setValue(userdetail).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    if(task.isSuccessful())
//                                    {
//
//
//                                    }
//                                    else{
//                                        Toast.makeText(context, "Database failed.",
//                                                Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                            });


                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(context, task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();

                        }

                    }
                });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser()!=null)
        {
            Onsignedin();
        }
        else
        {

          //  RegisterUser();
        }
    }
}