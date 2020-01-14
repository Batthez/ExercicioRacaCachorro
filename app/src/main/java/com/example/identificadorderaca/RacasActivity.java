package com.example.identificadorderaca;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class RacasActivity extends AppCompatActivity {


    private TextView lblRaca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_racas);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String raca = intent.getStringExtra("raca");

        //TextView
        lblRaca = findViewById(R.id.lblRaca);

        lblRaca.setText(raca);





    }

}
