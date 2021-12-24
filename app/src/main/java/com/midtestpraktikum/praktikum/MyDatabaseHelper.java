package com.midtestpraktikum.praktikum;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private  Context context;
    private static final String DATABASE_NAME = "mahasiswa.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_mhs";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAMA = "nama";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_NOTELP = "notelp";
    private static final String COLUMN_ALAMAT = "alamat";
    private static final String COLUMN_UMUR = "umur";
    private static final String COLUMN_JK = "jk";
    private static final String COLUMN_STATUS = "status";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAMA + " TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_NOTELP + " INTEGER, " +
                COLUMN_ALAMAT + " TEXT, " +
                COLUMN_UMUR + " INTEGER, " +
                COLUMN_JK + " TEXT, " +
                COLUMN_STATUS + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addMhs(String namainpt, String emailinpt, int notelpinpt, String alamatinpt, String umurinpt, String jkinpt, String statusinpt){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAMA, namainpt);
        cv.put(COLUMN_EMAIL, emailinpt);
        cv.put(COLUMN_NOTELP, notelpinpt);
        cv.put(COLUMN_ALAMAT, alamatinpt);
        cv.put(COLUMN_UMUR, umurinpt);
        cv.put(COLUMN_JK, jkinpt);
        cv.put(COLUMN_STATUS, statusinpt);
        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "Gagal Memasukkan Data", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Berhasil Memasukkan Data", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM "  + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db !=null){
            cursor = db.rawQuery(query, null);
        }
        return  cursor;
    }
}
