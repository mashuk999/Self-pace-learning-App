package com.example.deepak.androidclub;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class NotificationHolder extends RecyclerView.ViewHolder {

    TextView announcement;
    CardView parentlayout;

    public NotificationHolder(@NonNull View itemView) {
        super(itemView);

        announcement = itemView.findViewById(R.id.announcement_text);
        parentlayout = itemView.findViewById(R.id.notification_parent_layout);
    }


    public void setAnnouncement(String announcement)
    {
        this.announcement.setText(announcement);
    }
}
