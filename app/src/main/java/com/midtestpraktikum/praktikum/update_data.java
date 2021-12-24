package com.midtestpraktikum.praktikum;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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

    EditText namaipt, emailipt, notelpipt, alamatipt;
    SeekBar seekbarumuript;
    TextView seekbarumurvalueipt;
    RadioGroup rggendeript;
    RadioButton gendeript;
    CheckBox pelajaript, bekerjaipt, tidakbekerjaipt;
    Button update;
    Button delete;

    String id, nama, email, notelp, alamat, umur, jk, status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);
        //Text
        namaipt = (EditText) findViewById(R.id.etNamaUp);
        emailipt = (EditText) findViewById(R.id.etEmailUp);
        notelpipt = (EditText) findViewById(R.id.etNotelpUp);
        alamatipt = (EditText) findViewById(R.id.etAlamatUp);

        //Seekbar
        seekbarumuript = (SeekBar) findViewById(R.id.sbUmurUp);
        seekbarumurvalueipt = (TextView) findViewById(R.id.tvsbUmurUp);

        seekbarumuript.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                seekbarumurvalueipt.setText(String.valueOf(progress + " Tahun"));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Radio  Button
        rggendeript = (RadioGroup) findViewById(R.id.rgJeniskelaminUp);

        //Checkbox
        pelajaript = (CheckBox) findViewById(R.id.cb1Up);
        bekerjaipt = (CheckBox) findViewById(R.id.cb2Up);
        tidakbekerjaipt = (CheckBox) findViewById(R.id.cb3Up);
        update = (Button) findViewById(R.id.btnupdate);
        delete = (Button) findViewById(R.id. btndelete);

        getandsetIntentData();

        ActionBar ab = getSupportActionBar();
        assert ab != null;{
        ab.setTitle(nama);}
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int radioId = rggendeript.getCheckedRadioButtonId();
                gendeript = (RadioButton) findViewById(radioId);

                status = "";
                if (pelajaript.isChecked()) {
                    status += pelajaript.getText().toString() + ", ";
                }
                if (bekerjaipt.isChecked()) {
                    status += bekerjaipt.getText().toString() + " ";
                }
                if (tidakbekerjaipt.isChecked()) {
                    status += tidakbekerjaipt.getText().toString() + " ";
                }
                MyDatabaseHelper myDB = new MyDatabaseHelper(update_data.this);
                        nama = namaipt.getText().toString().trim();
                        email = emailipt.getText().toString().trim();
                        notelp = notelpipt.getText().toString().trim();
                        alamat = alamatipt.getText().toString().trim();
                        umur = seekbarumurvalueipt.getText().toString();
                        jk = gendeript.getText().toString().trim();
                        status.trim();
                myDB.updateData(id, nama, email, notelp, alamat, umur, jk, status);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
    }

    void getandsetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("nama") &&
                getIntent().hasExtra("email") && getIntent().hasExtra("notelp") && getIntent().hasExtra("alamat") &&
                getIntent().hasExtra("umur") && getIntent().hasExtra("jk") && getIntent().hasExtra("status")) {
            id = getIntent().getStringExtra("id");
            nama = getIntent().getStringExtra("nama");
            email = getIntent().getStringExtra("email");
            notelp = getIntent().getStringExtra("notelp");
            alamat = getIntent().getStringExtra("alamat");
            umur = getIntent().getStringExtra("umur");
            jk = getIntent().getStringExtra("jk");
            status = getIntent().getStringExtra("status");

            namaipt.setText(nama);
            emailipt.setText(email);
            notelpipt.setText(notelp);
            alamatipt.setText(alamat);
            seekbarumurvalueipt.setText(umur);

        } else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hapus " + nama + " ?");
        builder.setMessage("Apakah anda yakin menghapus data ini?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(update_data.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.create().show();
    }
}
