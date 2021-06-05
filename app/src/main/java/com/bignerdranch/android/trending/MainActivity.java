package com.bignerdranch.android.trending;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bignerdranch.android.trending.View.List.MainFragment;
import com.githang.statusbar.StatusBarCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_container,new MainFragment(),"MainFragment")
                .commit();

        StatusBarCompat.setStatusBarColor(this, 0xFAFAFA,true);
    }
}