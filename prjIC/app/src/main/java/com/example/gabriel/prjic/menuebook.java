package com.example.gabriel.prjic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class menuebook extends AppCompatActivity {
Intent intent;
        ImageButton btnArrasta, btnGraph, btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuebook);
        btnHome = findViewById(R.id.btnHome);
        btnArrasta= findViewById(R.id.btnArrasta);
        btnGraph = findViewById(R.id.btnGraph);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentBtn(0);
            }
        });




    }
    public void intentBtn(int i){


        intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }




}
