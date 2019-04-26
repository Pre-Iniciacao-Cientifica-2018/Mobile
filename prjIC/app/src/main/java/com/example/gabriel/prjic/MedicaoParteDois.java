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
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MedicaoParteDois extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ImageButton btnArrasta, btnGraph, btnHome;
    Intent intent;
    DrawerLayout drawer;
    TextView txtNum1, txtNum2, txtNum3, txtNum4, txtNum5;
    Button btnAnalise;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicao_parte_dois);

        btnHome = findViewById(R.id.btnHome);
        btnArrasta = findViewById(R.id.btnArrasta);
        btnGraph = findViewById(R.id.btnGraph);
        txtNum1 = findViewById(R.id.txtNumero);
        txtNum2 = findViewById(R.id.txtNumero2);
        txtNum3 = findViewById(R.id.txtNumero3);
        txtNum4 = findViewById(R.id.txtNumero4);
        txtNum5 = findViewById(R.id.txtNumero5);
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

        txtNum1.setText(textoPHP(1 )+ txtNum1.getText());
        txtNum2.setText(textoPHP(2)+ txtNum2.getText());
        txtNum3.setText(textoPHP(3)+ txtNum3.getText());
        txtNum4.setText(textoPHP(4)+ txtNum4.getText());
        txtNum5.setText(textoPHP(5)+ txtNum5.getText());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public static String x = "sdlm";

    public String textoPHP(int i) {

        String url = "";
        RequestQueue queue = Volley.newRequestQueue(this);
        if (i == 1) {
            url = "https://conco2.000webhostapp.com/max-mes/";
        } else if (i == 2) {
            url = "https://conco2.000webhostapp.com/max-semana/";
        } else if (i == 3) {
            url = "https://conco2.000webhostapp.com/media-semana/";
        } else if (i == 4) {
            url = "https://conco2.000webhostapp.com/max-mes/";
        } else if (i == 5) {
            url = "https://conco2.000webhostapp.com/media-mes/";
        } else {
            url = "https://conco2.000webhostapp.com/max-mes/";

        }

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonObj = new JSONObject(response);
                          x = jsonObj.getJSONObject("data").getString("max-con");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                x = "aaaaaaaaaaaaaaaaaaaaaaaaaaa";
            }
        });
        queue.add(stringRequest);
        return x;
    }
//    public void PegaMaxSemana(){
//        String url = "";
//        RequestQueue queue = Volley.newRequestQueue(this);
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//
//                            JSONObject jsonObj = new JSONObject(response);
//                            txtNum2.setText(jsonObj.getJSONObject("data").getString("max-con"));
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                x = "aaaaaaaaaaaaaaaaaaaaaaaaaaa";
//            }
//        });
//
//        queue.add(stringRequest);
//    }
//    public void PegaMedSemana(){}
//    public void PegaMaxMes(){}
//    public void PegaMedMes(){}
//    public void PegaMedDia(){}

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
        getMenuInflater().inflate(R.menu.medicao_parte_dois, menu);
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
            startActivity(new Intent(getApplicationContext(), SecondActivity.class));
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
