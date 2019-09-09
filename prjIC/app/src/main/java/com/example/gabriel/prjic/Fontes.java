package com.example.gabriel.prjic;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;

public class Fontes extends AppCompatActivity {

    Typeface montserrat = Typeface.createFromAsset(getAssets(), "Fonts/mont.ttf");
    Typeface montserratbold = Typeface.createFromAsset(getAssets(), "Fonts/montb.ttf");


    Typeface lemon = Typeface.createFromAsset(getAssets(), "Fonts/lemon.ttf");
    Typeface lemonbold = Typeface.createFromAsset(getAssets(), "Fonts/lemonb.ttf");

    Typeface proto = Typeface.createFromAsset(getAssets(), "Fonts/prototype.ttf");

    public Typeface getLemonemon() {
        return lemon;
    }

    public Typeface getLemonbold() {
        return lemonbold;
    }

    public Typeface getProto() {
        return proto;
    }

    public Typeface getMontserrat() {
        return montserrat;
    }

    public Typeface getMontserratbold() {
        return montserratbold;
    }

}
