package com.example.dcuniverse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class WebActivity extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        addView();
    }

    private void addView() {
        webView = findViewById(R.id.Webview);
        Intent intent = getIntent();
        String newUrl = intent.getStringExtra("newsUrl");
        webView.loadUrl(newUrl);
    }
}