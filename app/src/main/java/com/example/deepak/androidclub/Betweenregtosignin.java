package com.example.deepak.androidclub;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import maes.tech.intentanim.CustomIntent;

public class Betweenregtosignin extends AppCompatActivity {

    //firebase
    private FirebaseAuth mAuth;


    Context context;
    DatabaseReference users;
    boolean check = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_betweenregtosignin);

        Intent ints = getIntent();
        String name = ints.getExtras().getString("name"),
                email = ints.getExtras().getString("email"),
                mob = ints.getExtras().getString("mob"),
                regnum = ints.getExtras().getString("regnum"),
               uid = FirebaseAuth.getInstance().getCurrentUser().getUid();


        users = FirebaseDatabase.getInstance().getReference("usersdetailsall");

        context = getApplicationContext();

        if(name==null)
        {
            Toast.makeText(this,"name",Toast.LENGTH_LONG).show();
            check=false;
        }
        if(email==null)
        {
            Toast.makeText(this,"email",Toast.LENGTH_LONG).show();
            check=false;
        }
        if(mob==null)
        {
            Toast.makeText(this,"mob",Toast.LENGTH_LONG).show();
            check=false;
        }
        if(regnum==null)
        {
            Toast.makeText(this,"regnum",Toast.LENGTH_LONG).show();
            check=false;
        }

        if(users!=null)
        users.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(new User(
                        name,
                        email,
                        mob,
                        regnum,

                        uid
                ));

        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {

                Toast.makeText(context,"Time Left : " + millisUntilFinished/1000+"",Toast.LENGTH_SHORT).show();
            }

            public void onFinish() {
                if (FirebaseAuth.getInstance().getCurrentUser() != null&&check)
                {
                    Intent intent = new Intent(context,Login.class);
                    startActivity(intent);
                    CustomIntent.customType(context,"right-to-left");
                }
            }
        }.start();


        }



}