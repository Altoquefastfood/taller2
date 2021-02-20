package com.juandev.taller1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity implements View.OnClickListener {

    private CheckBox checkBox1,checkBox2,checkBox3,checkBox4;
    private Button btnNext,btnBack;
    List<String> items = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        checkBox1=findViewById(R.id.checkBox1);
        checkBox2=findViewById(R.id.checkBox2);
        checkBox3=findViewById(R.id.checkBox3);
        checkBox4=findViewById(R.id.checkBox4);
        btnNext=findViewById(R.id.btnNext);
        btnBack=findViewById(R.id.btnBack);
        btnNext.setOnClickListener(this);
        btnBack.setOnClickListener(this);


        checkBox1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                Toast.makeText(Home.this,checkBox1.getText().toString(),Toast.LENGTH_LONG).show();
            }
        });
        checkBox2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                Toast.makeText(Home.this,checkBox2.getText().toString(),Toast.LENGTH_LONG).show();
            }
        });
        checkBox3.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                Toast.makeText(Home.this,checkBox3.getText().toString(),Toast.LENGTH_LONG).show();
            }
        });
        checkBox4.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                Toast.makeText(Home.this,checkBox4.getText().toString(),Toast.LENGTH_LONG).show();
            }
        });


    }
    private List validate(){
        if(checkBox1.isChecked()){
            items.add(checkBox1.getText().toString());
        }
        if(checkBox2.isChecked()){
            items.add(checkBox2.getText().toString());
        }
        if(checkBox3.isChecked()){
            items.add(checkBox3.getText().toString());
        }
        if(checkBox4.isChecked()){
            items.add(checkBox4.getText().toString());
        }
        return items;
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNext:
                validate();
                Intent intent = new Intent(Home.this, SpinnerActivity.class);
                intent.putExtra("data",(ArrayList<String>) items);
                startActivity(intent);
                finish();break;

            case R.id.btnBack:
                startActivity(new Intent(Home.this, MainActivity.class));
                finish();
                break;
        }
    }
}
