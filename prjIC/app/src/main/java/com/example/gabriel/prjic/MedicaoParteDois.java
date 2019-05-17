package com.example.gabriel.prjic;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    public String nome = "";
    public TextView t[] = new TextView[8];
    DrawerLayout drawer;
    public int i = 0, a = 0;
    public LinearLayout layoutMaxDia, layoutMinDia, layoutMaxSemana, layoutMedSemana, layoutMinSemana, layoutMaxMes, layoutMedMes, layoutMinMes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.vem, R.anim.sai);
        setContentView(R.layout.activity_medicao_parte_dois);
        SincronismoHTTP sincronismoHTTP = new SincronismoHTTP();
        btnHome = findViewById(R.id.btnHome);
        btnArrasta = findViewById(R.id.btnArrasta);
        btnGraph = findViewById(R.id.btnGraph);
        layoutMaxDia = findViewById(R.id.layoutMaxDia);
        layoutMinDia = findViewById(R.id.layoutMinDia);
        layoutMaxSemana = findViewById(R.id.layoutMaxSemana);
        layoutMedSemana = findViewById(R.id.layoutMediaSemana);
        layoutMinSemana = findViewById(R.id.layoutMinSemana);
        layoutMaxMes = findViewById(R.id.layoutMaxMes);
        layoutMedMes = findViewById(R.id.layoutMediaMes);
        layoutMinMes = findViewById(R.id.layoutMinMes);


        t[0] = findViewById(R.id.txtNumeroMaiorDia);
        t[1] = findViewById(R.id.txtNumeroMenorDia);
        t[2] = findViewById(R.id.txtNumeroMaiorSem);
        t[3] = findViewById(R.id.txtNumeroMediaSem);
        t[4] = findViewById(R.id.txtNumeroMenorSem);
        t[5] = findViewById(R.id.txtNumeroMaiorMes);
        t[6] = findViewById(R.id.txtNumeroMediaMes);
        t[7] = findViewById(R.id.txtNumeroMenorMes);

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


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        sincronismoHTTP.execute();


    }


    private class SincronismoHTTP extends AsyncTask<Void, Void, Void> {
        StringRequest stringRequest;
        RequestQueue queue;

        @Override
        protected Void doInBackground(Void... voids) {

            return null;
        }

        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(MedicaoParteDois.this);
            pd.setTitle("Sincronizando");
            pd.setMessage("Buscando Dados...");
            pd.setIcon(R.drawable.carregar); //CUIDADO, VER SE IMG EXISTE
            pd.setCancelable(false);
            pd.show();
        }


        @Override
        protected void onPostExecute(Void vd) {
            super.onPostExecute(vd);

            queue = Volley.newRequestQueue(getApplicationContext());


            a();
            pd.dismiss();
        }

        public void a() {

            stringRequest = new StringRequest(Request.Method.GET, "https://conco2.000webhostapp.com/all",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {

                                JSONObject jsonObj = new JSONObject(response);
                                t[0].setText(jsonObj.getJSONObject("data").getString("max-dia"));
                                t[1].setText(jsonObj.getJSONObject("data").getString("min-dia"));
                                t[2].setText(jsonObj.getJSONObject("data").getString("max-semana"));
                                t[3].setText(jsonObj.getJSONObject("data").getString("media-semana"));
                                t[4].setText(jsonObj.getJSONObject("data").getString("min-semana"));
                                t[5].setText(jsonObj.getJSONObject("data").getString("max-mes"));
                                t[6].setText(jsonObj.getJSONObject("data").getString("media-mes"));
                                t[7].setText(jsonObj.getJSONObject("data").getString("min-mes"));
                                for (int i = 0; i < t.length; i++) {

                                    t[i].setText(t[i].getText() + " ppm");
                                }
                                double maxDia, minDia, maxSemana, medSemana, minSemana, maxMes, medMes, minMes;

                                maxDia =jsonObj.getJSONObject("data").getDouble("max-dia");
                                minDia =jsonObj.getJSONObject("data").getDouble("min-dia");


                                if (minDia < 9)
                                    layoutMaxDia.setBackgroundColor(Color.parseColor("#7700ff00"));
                                 else if (minDia < 11)
                                    layoutMaxDia.setBackgroundColor(Color.parseColor("#77efe999"));
                                 else if (minDia<13)


                                    layoutMaxDia.setBackgroundColor(Color.parseColor("#77edcc9c"));
                                 else if (minDia<15)

                                    layoutMaxDia.setBackgroundColor(Color.parseColor("#77e59090"));
                                 else
                                    layoutMaxDia.setBackgroundColor(Color.parseColor("#77892ac9"));
                                if (minDia < 9)
                                    layoutMaxDia.setBackgroundColor(Color.parseColor("#7700ff00"));
                                else if (minDia < 11)
                                    layoutMaxDia.setBackgroundColor(Color.parseColor("#77efe999"));
                                else if (minDia<13)


                                    layoutMaxDia.setBackgroundColor(Color.parseColor("#77edcc9c"));
                                else if (minDia<15)

                                    layoutMaxDia.setBackgroundColor(Color.parseColor("#77e59090"));
                                else
                                    layoutMaxDia.setBackgroundColor(Color.parseColor("#77892ac9"));



                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    t[1].setText("aaaaaaaaaaaaaaaaaaaaaaaaaaa");
                }
            });
            queue.add(stringRequest);


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
            overridePendingTransition(R.anim.back_vem, R.anim.back_sai);
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

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.vem, R.anim.sai);
    }


}
