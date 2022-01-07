package com.example.when2meet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class TimeActivity extends AppCompatActivity {

    TimeAdapter timeadapter;
    RecyclerView toggles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        toggles = findViewById(R.id.toggle_rcv);
        timeadapter = new TimeAdapter();
        toggles.setAdapter(timeadapter);

    }
}