package com.example.sayyidmuarasalam_14520415;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private CheckBox persetujuanCheckbox;
    private RadioGroup konfirmasiRadioGroup;
    private Button bukaAplikasiButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Inisialisasi UI Components
        persetujuanCheckbox = findViewById(R.id.checkboxPersetujuan);
        konfirmasiRadioGroup = findViewById(R.id.radioGroupKonfirmasi);
        bukaAplikasiButton = findViewById(R.id.bukaAplikasiButton);

        // Menyembunyikan tombol buka aplikasi saat belum ada persetujuan
        bukaAplikasiButton.setVisibility(View.GONE);

        // Mengatur listener untuk checkbox persetujuan
        persetujuanCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Menampilkan atau menyembunyikan tombol buka aplikasi berdasarkan status checkbox
            bukaAplikasiButton.setVisibility(isChecked ? View.VISIBLE : View.GONE);
        });

        // Mengatur listener untuk tombol buka aplikasi
        bukaAplikasiButton.setOnClickListener(v -> {
            // Mendapatkan konfirmasi dari radio button
            int selectedRadioButtonId = konfirmasiRadioGroup.getCheckedRadioButtonId();
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
            boolean konfirmasiBuka = selectedRadioButton != null && selectedRadioButton.getId() == R.id.radioButtonYa;

            if (konfirmasiBuka) {
                // Jika konfirmasi membuka aplikasi, lanjutkan ke MainActivity
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
