package com.example.gabriel.prjic;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;

public class SecondActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Intent intent;
    ImageButton btnArrasta, btnGraph, btnHome;

    private ImageView img = null;
    private ImageView img1 = null;
    DrawerLayout drawer;
    TextView txtTitulo;
    Button btnE;
    Button btnD;
    PDFView pdfTexto;
    LinearLayout linear;
    private String[] textos = new String[4];
    private String[] titulos = new String[4];
    int cap = 1;

    //private float x,y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.vem, R.anim.sai);
        setContentView(R.layout.activity_second);
        // overridePendingTransition(R.anim.vem,R.anim.sai);
        btnE = findViewById(R.id.btnEsquerda);
        btnD = findViewById(R.id.btnDireita);
        btnHome = findViewById(R.id.btnHome);
        btnArrasta = findViewById(R.id.btnArrasta);
        btnGraph = findViewById(R.id.btnGraph);
        txtTitulo = findViewById(R.id.lblTitulo);
        linear = findViewById(R.id.linear);
        pdfTexto = (PDFView)findViewById(R.id.pdfTexto);
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
        btnE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MudaTxt(-1);
            }
        });
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MudaTxt(1);
            }
        });





        titulos[0] = getString(R.string.cap1);
        titulos[1] = getString(R.string.cap2);
        titulos[2] = getString(R.string.cap3);
        titulos[3] = getString(R.string.cap4);

        pdfTexto.fromAsset("p.pdf").load();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        img = findViewById(R.id.imageView5);
        img1 = findViewById(R.id.imageView9);
        linear.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                //    x = event.getX();
                //    y = event.getY(); Um dia pode ser util
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (img.getVisibility() == View.VISIBLE && img1.getVisibility() == View.VISIBLE) {

                        img.setVisibility(View.INVISIBLE);
                        img1.setVisibility(View.INVISIBLE);
                        btnE.setVisibility(View.INVISIBLE);
                        btnD.setVisibility(View.INVISIBLE);
                    } else {
                        img.setVisibility(View.VISIBLE);
                        img1.setVisibility(View.VISIBLE);
                        btnE.setVisibility(View.VISIBLE);
                        btnD.setVisibility(View.VISIBLE);
                    }

                }

                return true;
            }

        });

        /*pdfTexto.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (img.getVisibility() == View.VISIBLE && img1.getVisibility() == View.VISIBLE) {

                        img.setVisibility(View.INVISIBLE);
                        img1.setVisibility(View.INVISIBLE);
                        btnE.setVisibility(View.INVISIBLE);
                        btnD.setVisibility(View.INVISIBLE);
                    } else {
                        img.setVisibility(View.VISIBLE);
                        img1.setVisibility(View.VISIBLE);
                        btnE.setVisibility(View.VISIBLE);
                        btnD.setVisibility(View.VISIBLE);
                    }

                }

                return true;
            }
        });*/
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            pdfTexto.zoomTo(3);
        }

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
                intent = new Intent(this, MedicaoReal.class);
                startActivity(intent);
                break;

        }

    }

    public void MudaTxt(int i) {
        cap = cap + i;
        if (cap > 4)
            cap = 1;
        if (cap < 1)
            cap = 1;

        txtTitulo.setText(titulos[cap - 1]);
        pdfTexto.fromAsset("p.pdf").defaultPage(cap).load();


    }


    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            overridePendingTransition(R.anim.back_vem, R.anim.back_sai);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.second, menu);
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
        Intent intent;
        if (id == R.id.itemCap1) {

            txtTitulo.setText(R.string.cap1);
            cap = 1;
        } else if (id == R.id.itemCap2) {



            txtTitulo.setText(R.string.cap2);
            cap = 2;
        } else if (id == R.id.itemCap3) {


            txtTitulo.setText(R.string.cap3);
            cap = 3;
        } else if (id == R.id.itemCap4) {

            txtTitulo.setText(R.string.cap4);
            cap = 4;

        } else if (id == R.id.itemInicio) {
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.itemMedReal) {
            intent = new Intent(this, MedicaoReal.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.vem, R.anim.sai);
    }

}
