package com.midtestpraktikum.praktikum;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Modul2 extends AppCompatActivity {

    TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modul2);

        tv1 =(TextView) findViewById(R.id.namauser);
        tv2 =(TextView) findViewById(R.id.emailuser);
        tv3 =(TextView) findViewById(R.id.notelpuser);
        tv4 =(TextView) findViewById(R.id.alamatuser);
        tv5 =(TextView) findViewById(R.id.umuruser);
        tv6 =(TextView) findViewById(R.id.genderuser);
        tv7 =(TextView) findViewById(R.id.statususer);

        String nama = getIntent().getExtras().getString("nama");
        String email = getIntent().getExtras().getString("email");
        String notelp = getIntent().getExtras().getString("notelp");
        String alamat = getIntent().getExtras().getString("alamat");
        String umur = getIntent().getExtras().getString("umur");
        String gender = getIntent().getExtras().getString("gender");
        String status = getIntent().getExtras().getString("status");

        tv1.setText("Nama\n"+nama);
        tv2.setText("Email\n"+email);
        tv3.setText("No Telp\n"+notelp);
        tv4.setText("Alamat\n"+alamat);
        tv5.setText("Umur\n"+umur);
        tv6.setText("Gender\n"+gender);
        tv7.setText("Status\n"+status);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Selamat Datang", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Selamat Tinggal", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Pause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Stop", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "Restart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Resume", Toast.LENGTH_SHORT).show();
    }
}