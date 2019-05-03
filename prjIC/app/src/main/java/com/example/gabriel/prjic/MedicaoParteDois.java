package com.example.gabriel.prjic;

import android.app.ProgressDialog;
import android.content.Intent;
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
    DrawerLayout drawer;
    public TextView txtNumMenorDia, txtNumMaiorDia, txtNumMenorSem, txtNumMediaSem, txtNumMaiorSem, txtNumMenorMes, txtNumMediaMes, txtNumMaiorMes;
    public int i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicao_parte_dois);
        SincronismoHTTP sincronismoHTTP = new SincronismoHTTP();
        btnHome = findViewById(R.id.btnHome);
        btnArrasta = findViewById(R.id.btnArrasta);
        btnGraph = findViewById(R.id.btnGraph);

        txtNumMaiorDia = findViewById(R.id.txtNumeroMaiorDia);
        txtNumMenorDia = findViewById(R.id.txtNumeroMenorDia);
        txtNumMaiorSem = findViewById(R.id.txtNumeroMaiorSem);
        txtNumMediaSem = findViewById(R.id.txtNumeroMediaSem);
        txtNumMenorSem = findViewById(R.id.txtNumeroMenorSem);
        txtNumMaiorMes = findViewById(R.id.txtNumeroMaiorMes);
        txtNumMediaMes = findViewById(R.id.txtNumeroMediaMes);
        txtNumMenorMes = findViewById(R.id.txtNumeroMenorMes);

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

        txtNumMaiorDia.setText(txtNumMaiorDia.getText());
        txtNumMenorDia.setText(txtNumMenorDia.getText());
        txtNumMaiorSem.setText(txtNumMaiorSem.getText());
        txtNumMediaSem.setText(txtNumMediaSem.getText());
        txtNumMenorSem.setText(txtNumMenorSem.getText());
        txtNumMaiorMes.setText(txtNumMaiorMes.getText());
        txtNumMediaMes.setText(txtNumMediaMes.getText());
        txtNumMenorMes.setText(txtNumMenorMes.getText());

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
        StringRequest stringRequest, stringRequest2, stringRequest3, stringRequest4, stringRequest5, stringRequest6, stringRequest7, stringRequest8;
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

            //começo da 1 conexão
            stringRequest = new StringRequest(Request.Method.GET, "https://conco2.000webhostapp.com/max-dia/",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {

                                JSONObject jsonObj = new JSONObject(response);
                                txtNumMaiorDia.setText(jsonObj.getJSONObject("data").getString("max-con") + "ppm");

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    txtNumMaiorDia.setText("aaaaaaaaaaaaaaaaaaaaaaaaaaa");
                }
            });
            queue.add(stringRequest);
            // fim do 1


            //começo da 2 conexão
            stringRequest2 = new StringRequest(Request.Method.GET, "https://conco2.000webhostapp.com/min-dia/",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {

                                JSONObject jsonObj = new JSONObject(response);
                                txtNumMenorDia.setText(jsonObj.getJSONObject("data").getString("min-con") + "ppm");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    txtNumMenorDia.setText("aaaaaaaaaaaaaaaaaaaaaaaaaaa");
                }
            });
            queue.add(stringRequest2);
            // fim do 2
            //começo da 3 conexão
            stringRequest3 = new StringRequest(Request.Method.GET, "https://conco2.000webhostapp.com/max-semana/",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {

                                JSONObject jsonObj = new JSONObject(response);
                                txtNumMaiorSem.setText(jsonObj.getJSONObject("data").getString("max-con") + "ppm");

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    txtNumMaiorSem.setText("aaaaaaaaaaaaaaaaaaaaaaaaaaa");
                }
            });
            queue.add(stringRequest3);
            // fim do 3
            //começo da 4 conexão
            stringRequest4 = new StringRequest(Request.Method.GET, "https://conco2.000webhostapp.com/media-semana/",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {

                                JSONObject jsonObj = new JSONObject(response);
                                txtNumMediaSem.setText(jsonObj.getJSONObject("data").getString("media-semana") + "ppm");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    txtNumMediaSem.setText("aaaaaaaaaaaaaaaaaaaaaaaaaaa");
                }
            });
            queue.add(stringRequest4);
            // fim do 4 dado
            //Começo da 5
            stringRequest5 = new StringRequest(Request.Method.GET, "https://conco2.000webhostapp.com/min-semana/",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {

                                JSONObject jsonObj = new JSONObject(response);
                                txtNumMenorSem.setText(jsonObj.getJSONObject("data").getString("min-con") + "ppm");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    txtNumMenorSem.setText("aaaaaaaaaaaaaaaaaaaaaaaaaaa");
                }
            });
            queue.add(stringRequest5);
            //fim 5
            // Começo da 6
            stringRequest6 = new StringRequest(Request.Method.GET, "https://conco2.000webhostapp.com/max-mes/",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {

                                JSONObject jsonObj = new JSONObject(response);
                                txtNumMaiorMes.setText(jsonObj.getJSONObject("data").getString("max-con") + "ppm");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    txtNumMaiorMes.setText("aaaaaaaaaaaaaaaaaaaaaaaaaaa");
                }
            });
            queue.add(stringRequest6);
            //fim 6
            // Começo da 7
            stringRequest7 = new StringRequest(Request.Method.GET, "https://conco2.000webhostapp.com/media-mes/",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {

                                JSONObject jsonObj = new JSONObject(response);
                                txtNumMediaMes.setText(jsonObj.getJSONObject("data").getString("media-mes") + "ppm");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    txtNumMaiorMes.setText("aaaaaaaaaaaaaaaaaaaaaaaaaaa");
                }
            });
            queue.add(stringRequest7);
            //fim 7

            // Começo da 8
            stringRequest8 = new StringRequest(Request.Method.GET, "https://conco2.000webhostapp.com/min-mes/",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {

                                JSONObject jsonObj = new JSONObject(response);
                                txtNumMenorMes.setText(jsonObj.getJSONObject("data").getString("min-con") + "ppm");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    txtNumMenorMes.setText("aaaaaaaaaaaaaaaaaaaaaaaaaaa");
                }
            });
            queue.add(stringRequest8);
            //fim 8

        }


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
