package com.example.sayyidmuarasalam_14520415;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateActivity extends AppCompatActivity {

    private EditText namaEditText, nimEditText, jurusanEditText;
    private Spinner jenisKelaminSpinner;
    private Button submitButton;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        // Inisialisasi DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Inisialisasi UI Components
        namaEditText = findViewById(R.id.namaEditText);
        nimEditText = findViewById(R.id.nimEditText);
        jurusanEditText = findViewById(R.id.jurusanEditText);
        jenisKelaminSpinner = findViewById(R.id.jenisKelaminSpinner);
        submitButton = findViewById(R.id.submitButton);

        // Inisialisasi array adapter untuk jenis kelamin
        ArrayAdapter<CharSequence> jenisKelaminAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.jenis_kelamin_array,
                android.R.layout.simple_spinner_item
        );
        jenisKelaminAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jenisKelaminSpinner.setAdapter(jenisKelaminAdapter);

        // Mengambil data yang dikirim dari MainActivity (jika ada)
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // Mendapatkan data yang akan diubah
            int id = extras.getInt("id");
            String nama = extras.getString("nama");
            String nim = extras.getString("nim");
            String jurusan = extras.getString("jurusan");
            String jenisKelamin = extras.getString("jenis_kelamin");

            // Mengisi nilai awal pada UI Components
            namaEditText.setText(nama);
            nimEditText.setText(nim);
            jurusanEditText.setText(jurusan);
            setSpinnerSelection(jenisKelamin);

            // Mengubah judul activity menjadi "Ubah Data"
            setTitle("Ubah Data");

            // Mengatur teks pada tombol submit menjadi "Simpan Perubahan"
            submitButton.setText("Simpan Perubahan");

            // Menyimpan perubahan data saat tombol submit ditekan
            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Mengambil nilai input dari UI Components
                    String updatedNama = namaEditText.getText().toString();
                    String updatedNim = nimEditText.getText().toString();
                    String updatedJurusan = jurusanEditText.getText().toString();
                    String updatedJenisKelamin = jenisKelaminSpinner.getSelectedItem().toString();

                    // Memperbarui data dalam database
                    databaseHelper.updateData(id, updatedNama, updatedNim, updatedJurusan, updatedJenisKelamin);

                    // Menampilkan pesan sukses
                    Toast.makeText(CreateActivity.this, "Data berhasil diperbarui", Toast.LENGTH_SHORT).show();

                    // Menutup activity saat perubahan data selesai
                    finish();
                }
            });
        } else {
            // Tidak ada data yang dikirim, berarti sedang menambah data baru

            // Menyimpan data baru saat tombol submit ditekan
            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Mengambil nilai input dari UI Components
                    String nama = namaEditText.getText().toString();
                    String nim = nimEditText.getText().toString();
                    String jurusan = jurusanEditText.getText().toString();
                    String jenisKelamin = jenisKelaminSpinner.getSelectedItem().toString();

                    // Menyimpan data ke database
                    databaseHelper.insertData(nama, nim, jurusan, jenisKelamin);

                    // Menampilkan pesan sukses
                    Toast.makeText(CreateActivity.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();

                    // Menutup activity saat data baru berhasil disimpan
                    finish();
                }
            });
        }
    }

    private void setSpinnerSelection(String jenisKelamin) {
        // Mengatur pilihan pada spinner berdasarkan jenis kelamin
        ArrayAdapter<CharSequence> adapter = (ArrayAdapter<CharSequence>) jenisKelaminSpinner.getAdapter();
        int index = adapter.getPosition(jenisKelamin);
        if (index != -1) {
            jenisKelaminSpinner.setSelection(index);
        }
    }
}

