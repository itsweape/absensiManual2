package com.example.presensimanualapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Button submitButton;
    EditText tanggal, waktu, keterangan;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tanggal = findViewById(R.id.tanggal);
        waktu = findViewById(R.id.waktu);
        spinner = findViewById(R.id.label_spinner);
        keterangan = findViewById(R.id.keterangan);

        //spinner pada xml
        if(spinner != null){
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

        adapter = ArrayAdapter.createFromResource(this,
                R.array.labels_array, android.R.layout.simple_spinner_item); //label yang akan ditampilkan di spinner
        spinner.setAdapter(adapter);

        //spinner pada main activity
        if(spinner != null){
            spinner.setOnItemSelectedListener(this);
        }

        //menampilkan datepicker
        tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        //menampilkan timpicker
        waktu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });

        //menampilkan alert dialog
        submitButton = findViewById(R.id.btn_submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });
    }

    //fungsi untuk menampilkan datepicker
    public void showDatePicker(){
        DialogFragment dateFragment = new DatePickerFragment();
        dateFragment.show(getSupportFragmentManager(), "date-picker");
    }

    //fungsi untuk menampilkan timepicker
    public void showTimePicker(){
        DialogFragment timeFragment = new TimePickerFragment();
        timeFragment.show(getSupportFragmentManager(), "time-picker");
    }

    //kondisi ketika spinner di klik
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (spinner.getSelectedItem().toString().equals("Hadir tepat waktu")) {
            keterangan.setVisibility(View.INVISIBLE);
        }else{
            keterangan.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    //menampilkan alert ketika button submit di klik
    public void showAlertDialog(){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
        alertBuilder.setTitle("Konfirmasi");
        alertBuilder.setMessage("Apakah kamu yakin data yang akan kamu kirim sudah sesuai?");

        alertBuilder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Absen Berhasil", Toast.LENGTH_SHORT).show();
                waktu.setText("");
                tanggal.setText("");
                spinner.setAdapter(adapter);
                keterangan.setText("");

            }
        });

        alertBuilder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                waktu.setText("");
                tanggal.setText("");
                spinner.setAdapter(adapter);
                keterangan.setText("");

            }
        });

        alertBuilder.show();
    }
    //method mencetak tanggal
    public void processDatePickerResult(int day, int month, int year){
        String day_string = Integer.toString(day);
        String month_string = Integer.toString(month+1);
        String year_string = Integer.toString(year);

        String dateMessage = day_string + "-" + month_string + "-" + year_string;
        tanggal.setText(dateMessage);
    }

    //method mencetak waktu
    public void processTimePickerResult(int hour, int minute){
        String hour_string = Integer.toString(hour);
        String minute_string = Integer.toString(minute);

        String timeMessage = hour_string + ":" + minute_string;
        waktu.setText(timeMessage);

    }
}