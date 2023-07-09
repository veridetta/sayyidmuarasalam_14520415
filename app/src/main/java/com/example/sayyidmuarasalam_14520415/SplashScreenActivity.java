package com.example.sayyidmuarasalam_14520415;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private static final int SPLASH_TIMEOUT = 2000; // Waktu tunda dalam milidetik (2 detik)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Mengatur layout yang digunakan oleh SplashScreenActivity
        setContentView(R.layout.activity_splash_screen);

        // Membuat handler untuk menangani penundaan
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Membuat intent untuk pindah ke MainActivity
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);

                // Menutup SplashScreenActivity agar tidak dapat dikembalikan dengan tombol back
                finish();
            }
        }, SPLASH_TIMEOUT);
    }
}
