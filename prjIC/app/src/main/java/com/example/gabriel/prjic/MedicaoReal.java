package com.example.gabriel.prjic;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;

public class MedicaoReal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ImageButton btnArrasta, btnGraph, btnHome;
    Intent intent;
    DrawerLayout drawer;
    Button btnAnalise;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicao_real);

        btnHome = findViewById(R.id.btnHome);
        btnArrasta = findViewById(R.id.btnArrasta);
        btnGraph = findViewById(R.id.btnGraph);
        btnAnalise = findViewById(R.id.btnAnalise);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        btnArrasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Btn(1);
            }
        });
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Btn(0);
            }
        });
        btnGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Btn(2);
            }
        });
        btnAnalise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Btn(3);
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void Btn(int i) {

        switch (i) {
            case 0:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case 1:
                drawer.openDrawer(GravityCompat.START);
                break;
            case 2:
                intent = new Intent(this, SecondActivity.class);
                startActivity(intent);
                break;
            case 3:
                intent = new Intent(this, MedicaoParteDois.class);
                startActivity(intent);
                break;

        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.medicao_real, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.itemTela) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));

        } else if (id == R.id.itemContato) {

        } else if (id == R.id.itemSobre) {

        } else if (id == R.id.itemLivro) {
            startActivity(new Intent( getApplicationContext(),SecondActivity.class ));
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
