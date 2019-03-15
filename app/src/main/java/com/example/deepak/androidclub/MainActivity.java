package com.example.deepak.androidclub;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        loadfragment(new Homefrgment());

//        Toolbar myToolbar = (Toolbar) findViewById(R.id.app_toolbar);
//        myToolbar.setTitle("Android Club");
//
//        setSupportActionBar(myToolbar);
    }

    private boolean loadfragment(Fragment fragment)
    {
        if(fragment!=null)
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,fragment)
                    .commit();
            return true;
        }

        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment fragment = null;

        switch (menuItem.getItemId())
        {
            case R.id.navigation_home:
                fragment = new Homefrgment();
                break;
            case R.id.navigation_dashboard:
                fragment = new Profilefragment();
                break;
            case R.id.navigation_notifications:
                fragment = new Notificationfragment();
                break;
        }

        return loadfragment(fragment);
    }
}
