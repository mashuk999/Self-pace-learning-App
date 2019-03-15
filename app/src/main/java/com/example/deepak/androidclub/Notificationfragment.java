package com.example.deepak.androidclub;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Notificationfragment extends Fragment {

    RecyclerView recyclerView;
    View view;
    DatabaseReference notifications;

    FirebaseRecyclerAdapter<Announcement,NotificationHolder> adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_notification,container,false);
        recyclerView = view.findViewById(R.id.notificationrecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

       // FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        notifications = FirebaseDatabase.getInstance().getReference("announcement");

      //  notifications.keepSynced(true);

//        for(int i=0;i<10;i++)
//        {
//            notifications.child(""+String.valueOf(i)).setValue(new Announcement("* " + String.valueOf(i) + " ) " + "Announcement"));
//        }

        FirebaseRecyclerOptions<Announcement> options = new FirebaseRecyclerOptions.Builder<Announcement>()
                .setQuery(notifications,Announcement.class).build();

        adapter = new FirebaseRecyclerAdapter<Announcement, NotificationHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull NotificationHolder holder, int position, @NonNull Announcement model) {

                String announceT = model.getAnnouncement();

                holder.setAnnouncement(announceT);
            }

            @NonNull
            @Override
            public NotificationHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notification_layout_file,viewGroup,false);

                return new NotificationHolder(view);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        notifications.getDatabase().goOffline();

        return view;
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
