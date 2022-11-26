package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WelcomeScreen extends AppCompatActivity {

//    private FirebaseAuth mAuth;


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }



//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        FirebaseUser user = mAuth.getCurrentUser();
//        if(user != null){
//            startActivity(new Intent(getApplicationContext(),SmartHome_Main.class));
//        }
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);




    } //onCreate end








//    @Override
//    protected void onStart() {
//        super.onStart();
//        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
//    }

    public void login_signup(View view) {
        startActivity(new Intent(WelcomeScreen.this,setPinCode.class));
        finish();
    }

    @Override
    public void onBackPressed() {}

    //    @Override
//    protected void onStart() {
//        super.onStart();
//        startActivity(new Intent(getApplicationContext(),PinActivity.class));
//
//    }
}