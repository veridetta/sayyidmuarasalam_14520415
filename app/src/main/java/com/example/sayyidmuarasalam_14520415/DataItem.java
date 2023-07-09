package com.example.sayyidmuarasalam_14520415;

public class DataItem {
    private int id; // ID data
    private String nama; // Nama
    private String nim; // NIM
    private String jurusan; // Jurusan
    private String jenisKelamin; // Jenis Kelamin

    public DataItem(int id, String nama, String nim, String jurusan, String jenisKelamin) {
        this.id = id;
        this.nama = nama;
        this.nim = nim;
        this.jurusan = jurusan;
        this.jenisKelamin = jenisKelamin;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getNim() {
        return nim;
    }

    public String getJurusan() {
        return jurusan;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }
}
