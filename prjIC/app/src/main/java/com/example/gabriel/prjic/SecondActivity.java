package com.example.gabriel.prjic;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
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
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

public class SecondActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Intent intent;
    ImageButton btnArrasta, btnGraph, btnHome;


    DrawerLayout drawer;

    TextView txtTitulo;


    PDFView pdfTexto;
    LinearLayout linear, linear2;

    private String[] titulos = new String[6];
    int page = 0;
    Pages p = new Pages();
    long queueid;
    int cap = 1;
    DownloadManager downloadManager;

    //private float x,y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.vem, R.anim.sai);
        setContentView(R.layout.activity_second);
        // overridePendingTransition(R.anim.vem,R.anim.sai);


        btnHome = findViewById(R.id.btnHome);
        btnArrasta = findViewById(R.id.btnArrasta);
        btnGraph = findViewById(R.id.btnGraph);

        linear = findViewById(R.id.linear);
        txtTitulo = findViewById(R.id.lblTitulo);
        pdfTexto = (PDFView) findViewById(R.id.pdfTexto);
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

        titulos[0] = getString(R.string.cap1);
        titulos[1] = getString(R.string.cap2);
        titulos[2] = getString(R.string.cap3);
        titulos[3] = getString(R.string.cap4);
        titulos[4] = getString(R.string.cap5);
        titulos[5] = getString(R.string.cap6);

        pdfTexto.fromAsset("text.pdf").defaultPage(p.getPages()).load();
        //cap = pdfTexto.getCurrentPage();

        if(p.getPages() >= 122){
            txtTitulo.setText(titulos[5]);
        }
        else if(p.getPages() >= 81){
            txtTitulo.setText(titulos[4]);
        }
        else if(p.getPages() >= 28){
            txtTitulo.setText(titulos[3]);
        }
        else if(p.getPages() >= 15){
            txtTitulo.setText(titulos[2]);
        }
        else if(p.getPages() >= 9){
            txtTitulo.setText(titulos[1]);
        }
        else if(p.getPages() <= 8){
            txtTitulo.setText(titulos[0]);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        pdfTexto.zoomTo(1);
        pdfTexto.setMinZoom(1);
        pdfTexto.setMidZoom(2);


        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            pdfTexto.zoomTo(3);
            pdfTexto.setMinZoom(3);
            pdfTexto.setMidZoom(3.75f);
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

        //txtTitulo.setText(titulos[cap - 1]);
        pdfTexto.fromAsset("text.pdf").defaultPage(cap).load();


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

        downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);

        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent;
        if (id == R.id.itemCap1) {


            cap = 1;
            txtTitulo.setText(R.string.cap1);
            pdfTexto.fromAsset("text.pdf").defaultPage(6).load();

            p.setPages(6);
            pdfTexto.zoomTo(1);
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                pdfTexto.zoomTo(3);

            }

        } else if (id == R.id.itemCap2) {

            cap = 2;
            txtTitulo.setText(R.string.cap2);
            pdfTexto.fromAsset("text.pdf").defaultPage(9).load();
            p.setPages(9);
            pdfTexto.zoomTo(1);
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                pdfTexto.zoomTo(3);

            }

        } else if (id == R.id.itemCap3) {


            cap = 3;
            txtTitulo.setText(R.string.cap3);
            pdfTexto.fromAsset("text.pdf").defaultPage(15).load();
            p.setPages(15);
            pdfTexto.zoomTo(1);
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                pdfTexto.zoomTo(3);

            }

        } else if (id == R.id.itemCap4) {


            cap = 4;
            txtTitulo.setText(R.string.cap4);
            pdfTexto.fromAsset("text.pdf").defaultPage(28).load();
            p.setPages(28);
            pdfTexto.zoomTo(1);
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                pdfTexto.zoomTo(3);

            }


        } else if (id == R.id.itemCap5) {


            cap = 5;
            txtTitulo.setText(R.string.cap5);
            pdfTexto.fromAsset("text.pdf").defaultPage(81).load();
            p.setPages(81);
            pdfTexto.zoomTo(1);
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                pdfTexto.zoomTo(3);

            }


        } else if (id == R.id.itemCap6) {


            cap = 6;
            txtTitulo.setText(R.string.cap6);
            pdfTexto.fromAsset("text.pdf").defaultPage(122).load();
            p.setPages(122);
            pdfTexto.zoomTo(1);
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                pdfTexto.zoomTo(3);

            }

        } else if (id == R.id.itemInicio) {
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.itemMedReal) {
            intent = new Intent(this, MedicaoReal.class);
            startActivity(intent);
        } else if (id == R.id.itemSobre) {

            startActivity(new Intent(getApplicationContext(), Activity_SobreNos.class));
        } else if (id == R.id.itemContato) {

            startActivity(new Intent(getApplicationContext(), Contatos.class));
        } else if (id == R.id.itemEpub) {

         DownloadManager.Request request = new DownloadManager.Request(Uri.parse("https://drive.google.com/uc?authuser=0&id=1EyQoRi-BXbkP81yK_UO3r2BR7YEUwy1f&export=download"));
         queueid = downloadManager.enqueue(request);

        }else if (id == R.id.itemPDF) {
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse("https://drive.google.com/uc?authuser=0&id=1pU72mHoZMzA0kyYZuL12d7rzSlClp7Ga&export=download"));
            queueid = downloadManager.enqueue(request);

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


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //int muda = cap;

        cap = pdfTexto.getCurrentPage();
        // if(cap == 6){

        //    Toast.makeText(this, "page: "+ cap, Toast.LENGTH_LONG).show();
        //    setPage(cap);
        // }

        // if(muda != cap)
        //    setPage(cap);
        if(cap >= 122){
            txtTitulo.setText(titulos[5]);
        }
        else if(cap >= 81){
            txtTitulo.setText(titulos[4]);
        }
        else if(cap >= 28){
            txtTitulo.setText(titulos[3]);
        }
        else if(cap >= 15){
            txtTitulo.setText(titulos[2]);
        }
        else if(cap >= 9){
            txtTitulo.setText(titulos[1]);
        }
        else if(cap <= 8){
            txtTitulo.setText(titulos[0]);
        }

        p.setPages(pdfTexto.getCurrentPage());


        return super.dispatchTouchEvent(ev);
    }

}
