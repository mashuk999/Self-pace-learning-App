package com.example.deepak.androidclub;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import maes.tech.intentanim.CustomIntent;

public class WatchVideo extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private static final String API_KEY = "AIzaSyARQOoY9gA9YNTXVpAEOhY1Luq331AE2h8";
    private String VIDEO_ID = "NtQ1PJ7XKbk";


    //firebase

    DatabaseReference videocompleted;
    String lecturename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_video);

        Intent intent = getIntent();
        String urltag = intent.getStringExtra("urltag");
        lecturename = intent.getStringExtra("lecturename");
        VIDEO_ID = urltag;

        videocompleted = FirebaseDatabase.getInstance().getReference("videocompleted");


        //init youtube
        YouTubePlayerView youTubePlayerView = findViewById(R.id.player);

        youTubePlayerView.initialize(API_KEY,this);



    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult result) {
        Toast.makeText(this, "Failured to Initialize!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        /** add listeners to YouTubePlayer instance **/
        player.setPlayerStateChangeListener(playerStateChangeListener);
        player.setPlaybackEventListener(playbackEventListener);
        player.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL);
        player.setFullscreen(true);

        /** Start buffering **/
        if (!wasRestored) {
            player.loadVideo(VIDEO_ID);
        }
    }

    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onBuffering(boolean arg0) {
        }
        @Override
        public void onPaused() {
        }
        @Override
        public void onPlaying() {
        }
        @Override
        public void onSeekTo(int arg0) {
        }
        @Override
        public void onStopped() {
        }
    };

    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onAdStarted() {
        }
        @Override
        public void onError(YouTubePlayer.ErrorReason arg0) {
        }
        @Override
        public void onLoaded(String arg0) {
        }
        @Override
        public void onLoading() {
        }
        @Override
        public void onVideoEnded() {
            videocompleted.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(lecturename);
        }
        @Override
        public void onVideoStarted() {
        }
    };

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(WatchVideo.this,Login.class);
        startActivity(intent);
        CustomIntent.customType(this,"right-to-left"); //See class by clicking ctrl to see other animation
    }

}
