package com.midtestpraktikum.praktikum;

import androidx.appcompat.app.AppCompatActivity;

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

public class update_data extends AppCompatActivity {

    EditText nama, email, notelp, alamat;
    SeekBar seekbarumur;
    TextView seekbarumurvalue;
    RadioGroup rggender;
    RadioButton gender;
    CheckBox pelajar,bekerja,tidakbekerja;
    String status;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);
        //Text
        nama = (EditText) findViewById(R.id.etNamaUp);
        email = (EditText) findViewById(R.id.etEmailUp);
        notelp = (EditText) findViewById(R.id.etNotelpUp);
        alamat = (EditText) findViewById(R.id.etAlamatUp);

        //Seekbar
        seekbarumur = (SeekBar) findViewById(R.id.sbUmurUp);
        seekbarumurvalue = (TextView) findViewById(R.id.tvsbUmurUp);

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
        rggender = (RadioGroup) findViewById(R.id.rgJeniskelaminUp);

        //Checkbox
        pelajar = (CheckBox) findViewById(R.id.cb1Up);
        bekerja = (CheckBox) findViewById(R.id.cb2Up);
        tidakbekerja = (CheckBox) findViewById(R.id.cb3Up);

        update = (Button) findViewById(R.id.btnupdate);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int radioId = rggender.getCheckedRadioButtonId();
                gender = (RadioButton) findViewById(radioId);

                status = "";
                if (pelajar.isChecked()) {
                    status += pelajar.getText().toString() + ", ";
                }
                if (bekerja.isChecked()) {
                    status += bekerja.getText().toString() + " ";
                }
                if (tidakbekerja.isChecked()) {
                    status += tidakbekerja.getText().toString() + " ";
                }
            }
        });
    }
}