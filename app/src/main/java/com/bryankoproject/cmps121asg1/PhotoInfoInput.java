package com.bryankoproject.cmps121asg1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by imbko on 10/17/2017.
 */

public class PhotoInfoInput extends AppCompatActivity {

    private static final String TAG = "PhotoInfoInput";
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    DatabaseHelper myDB;
    EditText etPhotoNameInput, etPhotoDateInput, etPhotographerInput;
    TextView tvPhotoDateTap;
    Button btnInfoSubmit;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photoinfoinput_activity);


        // shows back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etPhotoNameInput = (EditText)findViewById(R.id.etPhotoNameInput);
        etPhotoDateInput = (EditText)findViewById(R.id.etPhotoDateInput);
        tvPhotoDateTap = (TextView)findViewById(R.id.tvPhotoDateTap);
        etPhotographerInput = (EditText)findViewById(R.id.etPhotographerInput);
        btnInfoSubmit = (Button)findViewById(R.id.btnInfoSubmit);
        myDB = new DatabaseHelper(this);
        etPhotoDateInput.setEnabled(false);

        btnInfoSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pName = etPhotoNameInput.getText().toString();
                String pDate = tvPhotoDateTap.getText().toString();
                String pGrapher = etPhotographerInput.getText().toString();

                if (etPhotoNameInput.length() == 0 && tvPhotoDateTap.equals("Select Date") && etPhotographerInput.length() == 0){
                    Toast.makeText(PhotoInfoInput.this, "Empty Text field(s)",Toast.LENGTH_LONG).show();
                } else if(etPhotoNameInput.length() != 0 && tvPhotoDateTap.length() != 0 && etPhotographerInput.length() != 0) {
                    AddData(pName, pDate, pGrapher);
                    etPhotoNameInput.setText("");
                    tvPhotoDateTap.setText("");
                    etPhotographerInput.setText("");
                    Intent intent = new Intent(PhotoInfoInput.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        tvPhotoDateTap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        PhotoInfoInput.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                tvPhotoDateTap.setText(date);
            }
        };

    }

    public void AddData(String photoName, String photoDate, String photoGrapher) {
        boolean insertData = myDB.addData(photoName, photoDate, photoGrapher);

        if(insertData==true){
            Toast.makeText(this, "Success", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Failed", Toast.LENGTH_LONG).show();
        }
    }
}
