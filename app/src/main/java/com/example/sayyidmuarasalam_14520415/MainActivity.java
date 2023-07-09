package com.example.sayyidmuarasalam_14520415;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private DataAdapter dataAdapter;
    private List<DataItem> dataList;
    private RecyclerView recyclerView;
    private Button addDataButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Inisialisasi RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inisialisasi Tombol Tambah Data
        addDataButton = findViewById(R.id.addDataButton);
        addDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Buka CreateActivity untuk menambah data baru
                Intent intent = new Intent(MainActivity.this, CreateActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Memuat data dari database dan menampilkan dalam RecyclerView
        loadData();
    }

    private void loadData() {
        // Mendapatkan semua data dari database
        dataList = databaseHelper.getAllData();

        // Inisialisasi DataAdapter dan mengatur ke RecyclerView
        dataAdapter = new DataAdapter(dataList, databaseHelper, this);
        recyclerView.setAdapter(dataAdapter);
    }
}
