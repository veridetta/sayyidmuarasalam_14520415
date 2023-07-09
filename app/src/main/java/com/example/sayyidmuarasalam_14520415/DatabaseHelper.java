package com.example.sayyidmuarasalam_14520415;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "data.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "data";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAMA = "nama";
    private static final String COLUMN_NIM = "nim";
    private static final String COLUMN_JURUSAN = "jurusan";
    private static final String COLUMN_JENIS_KELAMIN = "jenis_kelamin";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Membuat tabel data
        String createTable = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAMA + " TEXT,"
                + COLUMN_NIM + " TEXT,"
                + COLUMN_JURUSAN + " TEXT,"
                + COLUMN_JENIS_KELAMIN + " TEXT"
                + ")";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Menghapus tabel data jika versi database berubah
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertData(String nama, String nim, String jurusan, String jenisKelamin) {
        // Memasukkan data baru ke dalam database
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAMA, nama);
        values.put(COLUMN_NIM, nim);
        values.put(COLUMN_JURUSAN, jurusan);
        values.put(COLUMN_JENIS_KELAMIN, jenisKelamin);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void updateData(int id, String nama, String nim, String jurusan, String jenisKelamin) {
        // Mengupdate data berdasarkan ID
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAMA, nama);
        values.put(COLUMN_NIM, nim);
        values.put(COLUMN_JURUSAN, jurusan);
        values.put(COLUMN_JENIS_KELAMIN, jenisKelamin);
        db.update(TABLE_NAME, values, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void deleteData(int id) {
        // Menghapus data berdasarkan ID
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public List<DataItem> getAllData() {
        // Mengambil semua data dari database
        List<DataItem> dataList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String nama = cursor.getString(cursor.getColumnIndex(COLUMN_NAMA));
                String nim = cursor.getString(cursor.getColumnIndex(COLUMN_NIM));
                String jurusan = cursor.getString(cursor.getColumnIndex(COLUMN_JURUSAN));
                String jenisKelamin = cursor.getString(cursor.getColumnIndex(COLUMN_JENIS_KELAMIN));
                DataItem dataItem = new DataItem(id, nama, nim, jurusan, jenisKelamin);
                dataList.add(dataItem);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return dataList;
    }

    public DataItem getDataById(int id) {
        // Mengambil data berdasarkan ID
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, COLUMN_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);
        DataItem dataItem = null;
        if (cursor != null && cursor.moveToFirst()) {
            String nama = cursor.getString(cursor.getColumnIndex(COLUMN_NAMA));
            String nim = cursor.getString(cursor.getColumnIndex(COLUMN_NIM));
            String jurusan = cursor.getString(cursor.getColumnIndex(COLUMN_JURUSAN));
            String jenisKelamin = cursor.getString(cursor.getColumnIndex(COLUMN_JENIS_KELAMIN));
            dataItem = new DataItem(id, nama, nim, jurusan, jenisKelamin);
            cursor.close();
        }
        db.close();
        return dataItem;
    }
}

