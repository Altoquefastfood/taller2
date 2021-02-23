package com.juandev.taller1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText editUser, editPassword;
    private Button btnLogin;
    private FirebaseAuth mAuth;
    private ArrayList<UsersModel> listUsers;
    Handler mHandler = new Handler();
    boolean isRunning = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editUser = findViewById(R.id.editUser);
        editPassword = findViewById(R.id.editPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
    }
    private boolean displayData() {
        ConnectivityManager cn=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cn.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
    @Override
    public void onStart() {
        super.onStart();
        new Hilo1().start();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser==null){
            Log.i("Mensaje", "No user is signed in");
        }
    }
    class Hilo1 extends Thread {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            while (isRunning) {
                try {
                    Thread.sleep(10000);
                    mHandler.post(new Runnable() {

                        @Override
                        public void run() {
                            Log.i("Mensaje", "Esto que WTF o.O");
                            // TODO Auto-generated method stub
                            // Write your code here to update the UI.
                            if(displayData()){
                                Toast.makeText(MainActivity.this, "Network is available", Toast.LENGTH_LONG).show();
                            } else {
                                startActivity(new Intent(MainActivity.this, MainActivity.class));
                                finish();
                                Toast.makeText(MainActivity.this, "Network is not available", Toast.LENGTH_LONG).show();
                            }

                        }
                    });
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        }
    }


    public void onClick(View v) {
        String strUser = editUser.getText().toString().trim();
        String strPassword = editPassword.getText().toString().trim();
        switch (v.getId()) {
            case R.id.btnLogin:
                if (!TextUtils.isEmpty(strUser) && !TextUtils.isEmpty(strPassword)) {

                    mAuth.signInWithEmailAndPassword(strUser, strPassword)
                            .addOnCompleteListener(this, task -> {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("Mensaje","signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    startActivity(new Intent(MainActivity.this, Home.class));
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("Mensaje", "signInWithEmail:failure", task.getException());
                                    Toast.makeText(MainActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                }else{
                    Toast.makeText(MainActivity.this,"Ups! Usuario o contraseña incorrectos",Toast.LENGTH_LONG).show();
                }

        }
    }
}
