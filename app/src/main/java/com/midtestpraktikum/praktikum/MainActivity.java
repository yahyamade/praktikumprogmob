package com.midtestpraktikum.praktikum;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton btn_tambah;

    MyDatabaseHelper myDB;
    ArrayList<String> mhs_id, mhs_nama, mhs_email, mhsnotelp, mhsalamat, mhsumur, mhsgender, mhsstatus;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        btn_tambah = findViewById(R.id.btntambah);
        btn_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, add.class);
                startActivity(intent);}
            });

        myDB = new MyDatabaseHelper(MainActivity.this);
        mhs_id = new ArrayList<>();
        mhs_nama = new ArrayList<>();
        mhs_email = new ArrayList<>();
        mhsnotelp = new ArrayList<>();
        mhsalamat = new ArrayList<>();
        mhsumur = new ArrayList<>();
        mhsgender = new ArrayList<>();
        mhsstatus = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(MainActivity.this,this, mhs_id, mhs_nama, mhs_email, mhsnotelp, mhsalamat, mhsumur, mhsgender, mhsstatus);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                mhs_id.add(cursor.getString(0));
                mhs_nama.add(cursor.getString(1));
                mhs_email.add(cursor.getString(2));
                mhsnotelp.add(cursor.getString(3));
                mhsalamat.add(cursor.getString(4));
                mhsumur.add(cursor.getString(5));
                mhsgender.add(cursor.getString(6));
                mhsstatus.add(cursor.getString(7));
            }
        }
    }
}
