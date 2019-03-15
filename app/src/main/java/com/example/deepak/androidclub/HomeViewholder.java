package com.example.deepak.androidclub;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class HomeViewholder extends RecyclerView.ViewHolder {

    TextView videoName,difficulty,author,subject;

    CardView parentLayout;

    public HomeViewholder(@NonNull View itemView) {
        super(itemView);

        //initilize the views from the card view layout

        parentLayout = itemView.findViewById(R.id.home_parent_layout);
        videoName = itemView.findViewById(R.id.lecturenumber);
        author = itemView.findViewById(R.id.lectureby);
        subject = itemView.findViewById(R.id.lecturesubject);
        difficulty = itemView.findViewById(R.id.home_difficulty);

    }


    public void setVideoName(String videoName) {
        this.videoName.setText(videoName);
    }

    public void setDifficulty(String difficulty) {
        this.difficulty.setText(difficulty);
    }

    public void setAuthor(String author) {
        this.author.setText(author);
    }

    public void setSubject(String subject) {
        this.subject.setText(subject);
    }
}
