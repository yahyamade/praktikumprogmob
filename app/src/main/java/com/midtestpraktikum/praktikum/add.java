package com.midtestpraktikum.praktikum;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class add extends AppCompatActivity {

    EditText nama, email, notelp, alamat;
    SeekBar seekbarumur;
    TextView seekbarumurvalue;
    RadioGroup rggender;
    RadioButton gender;
    CheckBox pelajar,bekerja,tidakbekerja;
    String status;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //Text
        nama = (EditText) findViewById(R.id.etNama);
        email = (EditText) findViewById(R.id.etEmail);
        notelp = (EditText) findViewById(R.id.etNotelp);
        alamat = (EditText) findViewById(R.id.etAlamat);

        //Seekbar
        seekbarumur = (SeekBar) findViewById(R.id.sbUmur);
        seekbarumurvalue = (TextView) findViewById(R.id.tvsbUmur);

        seekbarumur.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                seekbarumurvalue.setText(String.valueOf (progress + " Tahun"));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Radio  Button
        rggender = (RadioGroup) findViewById(R.id.rgJeniskelamin);

        //Checkbox
        pelajar = (CheckBox) findViewById(R.id.cb1);
        bekerja = (CheckBox) findViewById(R.id.cb2);
        tidakbekerja = (CheckBox) findViewById(R.id.cb3);

        submit = (Button) findViewById(R.id.btnsubmit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int radioId = rggender.getCheckedRadioButtonId();
                gender = (RadioButton) findViewById(radioId);

                status = "";
                if(pelajar.isChecked()){
                    status += pelajar.getText().toString()+ ", ";
                }
                if(bekerja.isChecked()){
                    status +=bekerja.getText().toString() + " ";
                }
                if (tidakbekerja.isChecked()){
                    status += tidakbekerja.getText().toString() + " ";
                }
                showDialog();
            }
        });

    }

    private void showDialog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Konfirmasi Data");
        dialogBuilder.setMessage("Apakah anda sudah yakin dengan data berikut?\n\n"+
                "Nama : " + nama.getText() + "\n" +
                "Email : " + email.getText() + "\n" +
                "No Telepon : " + notelp.getText() + "\n" +
                "Alamat : " + alamat.getText() + "\n"+
                "Umur : " + seekbarumurvalue.getText().toString() + "\n" +
                "Jenis Kelamin : " + gender.getText() + "\n" +
                "Status : " + status)

                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.cancel();
                    }
                })
                .setCancelable(true)

                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "Data diterima!", Toast.LENGTH_SHORT).show();

//                        Intent pindahAct = new Intent(getApplicationContext(), Modul2.class);
//                        pindahAct.putExtra("nama", nama.getText().toString());
//                        pindahAct.putExtra("email", email.getText().toString());
//                        pindahAct.putExtra("notelp", notelp.getText().toString());
//                        pindahAct.putExtra("alamat", alamat.getText().toString());
//                        pindahAct.putExtra("umur", seekbarumurvalue.getText());
//                        pindahAct.putExtra("gender", gender.getText());
//                        pindahAct.putExtra("status", status);
//
//                        startActivity(pindahAct);

                        MyDatabaseHelper myDB = new MyDatabaseHelper(add.this);
                        myDB.addMhs(nama.getText().toString().trim(),
                                email.getText().toString().trim(),
                                Integer.valueOf(notelp.getText().toString().trim()),
                                alamat.getText().toString().trim(),
                                seekbarumurvalue.getText().toString(),
                                gender.getText().toString().trim(),
                                status.trim());
                    }
                });

        AlertDialog confirmDialog = dialogBuilder.create();
        confirmDialog.show();
    }

}