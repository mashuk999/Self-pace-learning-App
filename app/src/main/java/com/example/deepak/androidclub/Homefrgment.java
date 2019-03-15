package com.example.deepak.androidclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Homefrgment extends Fragment {

    RecyclerView mrecyclerview;
    View mview;

    DatabaseReference videourl;
    FirebaseRecyclerAdapter<VideoUrl,HomeViewholder> adapter;
    static boolean calledalready_home = false;

    ProgressBar progressBar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mview = inflater.inflate(R.layout.fragment_home,container,false);
        mrecyclerview = mview.findViewById(R.id.homerecyclerview);
        mrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        if(!calledalready_home) // isse crash bachega
        {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            calledalready_home = true;
        }

        videourl = FirebaseDatabase.getInstance().getReference("videourl");
        videourl.keepSynced(true);
        progressBar = mview.findViewById(R.id.progressBar_home);

        fillelementsinrecyclerview();
//        for(int i=0;i<10;i++)
//        {
//            videourl.child(""+String.valueOf(i)).setValue(new VideoUrl("vid","jnjkn","sss","sdd","lecture"+String.valueOf(i)));
//        }

        videourl.child(""+String.valueOf(0)).setValue(new VideoUrl("3u1fu6f8Hto","Youtube","JAVA","easy","Java Tutorial for Beginners"));
        videourl.child(""+String.valueOf(1)).setValue(new VideoUrl("-kpSUw_FTg4","Youtube","JAVA","easy","Java Tutorial for Advance User"));

        return mview;
    }

    private void fillelementsinrecyclerview() {
        FirebaseRecyclerOptions<VideoUrl> options = new FirebaseRecyclerOptions.Builder<VideoUrl>()
                .setQuery(videourl,VideoUrl.class).build();

        adapter = new FirebaseRecyclerAdapter<VideoUrl,HomeViewholder>(options)
        {

            @NonNull
            @Override
            public HomeViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_videolistlayout,viewGroup,false);

                return new HomeViewholder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull final HomeViewholder holder, int position, @NonNull VideoUrl model) {

                final String lecturenum = model.getLecturenum(),
                        subject = model.getSubject(),
                        author = model.getSource(),
                        videourl = model.getVideourl(),
                        difficulty = model.getDifficulty();

                holder.setVideoName(lecturenum);
                holder.setDifficulty(difficulty);
                holder.setSubject(subject);
                holder.setAuthor(author);

                holder.parentLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(),WatchVideo.class);
                        intent.putExtra("urltag",videourl);
                        intent.putExtra("lecturename",lecturenum);
                        startActivity(intent);
                    }
                });
            }
        };

        adapter.startListening();
        mrecyclerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        videourl.getDatabase().goOffline();

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI

                // ...
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        };
        videourl.addValueEventListener(postListener);
    }

    @Override
    public void onResume()
    {
        super.onResume();

        if (FirebaseDatabase.getInstance() != null)
        {
            FirebaseDatabase.getInstance().goOnline();
        }
    }

    @Override
    public void onPause() {

        super.onPause();

        if(FirebaseDatabase.getInstance()!=null)
        {
            FirebaseDatabase.getInstance().goOffline();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if(FirebaseDatabase.getInstance()!=null)
        {
            FirebaseDatabase.getInstance().goOffline();
        }
    }



}
