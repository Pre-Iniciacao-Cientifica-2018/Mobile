package com.example.gabriel.prjic;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageButton;

public class Activity_SobreNos extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ImageButton btnArrasta, btnHome, btnGraph, btnLivro;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.vem, R.anim.sai);
        setContentView(R.layout.activity__sobre_nos);

        drawer = findViewById(R.id.drawer_layout);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        btnHome = findViewById(R.id.btnHome);
        btnLivro = findViewById(R.id.btnLivro);
        btnGraph = findViewById(R.id.btnGraph);
        btnArrasta = findViewById(R.id.btnArrasta);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

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
                Btn(3);
            }
        });
        btnLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Btn(2);
            }
        });
    }
    public void Btn(int i) {
        Intent intent;
        ;
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
                intent = new Intent(this, MedicaoReal.class);
                startActivity(intent);
                break;

        }

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity__sobre_nos, menu);
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
            startActivity(new Intent(getApplicationContext(), Activity_SobreNos.class));
        } else if (id == R.id.itemSobre) {
            startActivity(new Intent(getApplicationContext(), Activity_SobreNos.class));

        } else if (id == R.id.itemLivro) {
            startActivity(new Intent(getApplicationContext(), SecondActivity.class));
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.vem, R.anim.sai);
    }


}
