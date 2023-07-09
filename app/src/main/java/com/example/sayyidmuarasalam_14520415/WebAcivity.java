package com.example.sayyidmuarasalam_14520415;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class WebAcivity extends AppCompatActivity {

    private WebView webView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_acivity);

        // Inisialisasi WebView
        webView = findViewById(R.id.webView);
        progressBar = findViewById(R.id.progressBar);

        // Mengaktifkan JavaScript pada WebView
        WebSettings webSettings = webView.getSettings();
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setJavaScriptEnabled(true);

        // Mengatur WebViewClient untuk menangani navigasi dan loading halaman
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // Memulai loading halaman, tampilkan ProgressBar
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // Selesai loading halaman, sembunyikan ProgressBar
                progressBar.setVisibility(View.GONE);
            }
        });

        // Mengatur WebChromeClient untuk menampilkan progress loading halaman
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // Update progress loading pada ProgressBar
                progressBar.setProgress(newProgress);
            }
        });

        // Memuat halaman web
        webView.loadUrl("https://sayyidmuarasalam.blogspot.com");
    }

    @Override
    public void onBackPressed() {
        // Kembali ke halaman sebelumnya jika ada
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
