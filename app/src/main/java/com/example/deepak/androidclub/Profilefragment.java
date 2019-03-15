package com.example.deepak.androidclub;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profilefragment extends Fragment {
    TextView profilename;

    DatabaseReference details;
    String sname = ";";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,container,false);
         profilename= view.findViewById(R.id.nameprofile);

        details  = FirebaseDatabase.getInstance().getReference("userdetailsall").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("name");

        details.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String _name = dataSnapshot.getValue(String.class);
                sname = _name;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        if(profilename!=null)
        {

            profilename.setText(sname);
        }
        else  {
            Toast.makeText(getActivity(),sname,Toast.LENGTH_SHORT).show();
        }

       // Button about = view.findViewById(R.id.about_profile);
        /*about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to about

            }
        });*/
        return view;
    }
}