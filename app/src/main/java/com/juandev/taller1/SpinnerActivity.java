package com.juandev.taller1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SpinnerActivity extends AppCompatActivity implements View.OnClickListener{

    private Spinner spinner;
    private Button btnBack;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spinner);
        spinner=findViewById(R.id.spSongs);
        btnBack=findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        Bundle b = getIntent().getExtras();
        ArrayList<String> arr = b.getStringArrayList("data");
        if (null != b) {
            Log.i("List", "Passed Array List :: " + arr);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item, arr);
            spinner.setAdapter(adapter);
        }else{

        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SpinnerActivity.this,arr.get(position)+" Seleccionado",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                startActivity(new Intent(SpinnerActivity.this, Home.class));
                finish();

        }
    }


}
