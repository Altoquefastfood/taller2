package com.juandev.taller1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText editUser, editPassword;
    private Button btnLogin;
    UsersModel users;
    UsersModel users2;
    private ArrayList<UsersModel> listUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editUser = findViewById(R.id.editUser);
        editPassword = findViewById(R.id.editPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        listUsers=new ArrayList<UsersModel>();

        users=new UsersModel("Juanito","123qweasd");
        users2=new UsersModel("Anita","qweasd");

        listUsers.add(users);
        listUsers.add(users2);
    }
    public boolean validate(String strUser,String strPassword)
    {

        if (!TextUtils.isEmpty(strUser) && !TextUtils.isEmpty(strPassword)) {
            for (int i=0;i<listUsers.size();i++) {
                if (listUsers.get(i).getUser().equals(strUser) && listUsers.get(i).getPassword().equals(strPassword))
                {
                    return true;
                }
            }
        }

        return false;
    }

    public void onClick(View v) {
        String strUser = editUser.getText().toString().trim();
        String strPassword = editPassword.getText().toString().trim();
        switch (v.getId()) {
            case R.id.btnLogin:
                if (validate(strUser,strPassword)) {
                    startActivity(new Intent(MainActivity.this, Home.class));
                    finish();
                }else{
                    Toast.makeText(MainActivity.this,"Ups! Usuario o contraseña incorrectos",Toast.LENGTH_LONG).show();
                }

        }
    }
}
