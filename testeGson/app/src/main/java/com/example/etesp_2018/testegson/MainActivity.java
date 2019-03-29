package com.example.etesp_2018.testegson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    JSONObject obj = new JSONObject();
    TextView aa;
    ArrayList <String> numberlist  = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aa = findViewById(R.id.aa);
        getJSON();

    }
public void getJSON(){
        String json;
        try{

            InputStream is = getAssets().open("aa.json");
            int size  = is.available();
            byte [] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String (buffer,"UTF-8");
            JSONArray jsonarray = new JSONArray(json);

            for(int i = 0; i<jsonarray.length(); i++){
                 JSONObject obj = jsonarray.getJSONObject(i);

                 if(obj.getString("cap1").equals("Kyle")){

                     numberlist.add(obj.getString("cap1"));
                 }
            }
            numberlist.add(obj.getString("cap1"));
               Toast.makeText(getApplicationContext(),numberlist.toString(),Toast.LENGTH_LONG).show();

        } catch (Exception erro){


            erro.printStackTrace();
        }
}
}
