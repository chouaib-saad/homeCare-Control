package com.example.myapplication;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Utility.NetworkChangeListener;

public class Login_signup extends AppCompatActivity {

    NetworkChangeListener networkChangeListener = new NetworkChangeListener();


    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener,filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListener);
        super.onStop();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

    }

    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_signup);
    }

    public void sigup_btn(View view) {
        startActivity(new Intent(Login_signup.this, Signup.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }


    public void login_btn(View view) {
        startActivity(new Intent(Login_signup.this, Signin.class));
//          startActivity(new Intent(Login_signup.this,SmartHome_Main.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}