package com.example.gabriel.prjic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebViewGraph extends AppCompatActivity {

    WebView mWebView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.vem, R.anim.sai);

        setContentView(R.layout.graph_webview);

        mWebView =  findViewById(R.id.webview);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.loadUrl("http://conco2.tpn.usp.br/dia-semana-mes_graphs.php");


    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.vem, R.anim.sai);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.back_vem, R.anim.back_sai);
    }
}
