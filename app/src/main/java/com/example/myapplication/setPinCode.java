package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;
import androidx.security.crypto.MasterKeys;

import com.example.myapplication.Utility.SecureSharedPref;
import com.prashantsolanki.secureprefmanager.SecurePrefManager;
import com.prashantsolanki.secureprefmanager.SecurePrefManagerInit;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

public class setPinCode extends AppCompatActivity implements View.OnClickListener{


    TextView tv1, tv2, tv3, tv4;
    Button num1, num2, num3, num4, num5, num6, num7, num8, num9, num10, num11, num12;


    LinearLayout confirm_button;

    //    Secure Shared Pref
//    SecurePrefManager securePrefManager = new SecurePrefManagerInit.Initializer(getApplicationContext())
//            .useEncryption(true)
//               .initialize();

    private SecurePrefManager securePrefManager;



    //    Secure Shared Pref
    private SecureSharedPref secureSharedPref;

    ImageView logo_image;

    private static Integer Attempts = 5;

    String passCode = "";
    String num_01, num_02, num_03, num_04;
    ArrayList<String> numbers_List = new ArrayList<>();


    @Override
    public void onBackPressed() {
    }


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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_pin_code);


        //image hook
        logo_image = findViewById(R.id.logo_image);


        //inisialise the components
        initialiseComponents();


        //confirm button hook
        confirm_button = findViewById(R.id.confirm_code_next);


        //set onclick listeners on buttons
        num1.setOnClickListener(this);
        num2.setOnClickListener(this);
        num3.setOnClickListener(this);
        num4.setOnClickListener(this);
        num5.setOnClickListener(this);
        num6.setOnClickListener(this);
        num7.setOnClickListener(this);
        num8.setOnClickListener(this);
        num9.setOnClickListener(this);
        num10.setOnClickListener(this);
        num11.setOnClickListener(this);
        num12.setOnClickListener(this);







    }  //onCreate End

    private void initialiseComponents() {

        //code pin fields
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);

        //code numbers buttons
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        num3 = findViewById(R.id.num3);
        num4 = findViewById(R.id.num4);
        num5 = findViewById(R.id.num5);
        num6 = findViewById(R.id.num6);
        num7 = findViewById(R.id.num7);
        num8 = findViewById(R.id.num8);
        num9 = findViewById(R.id.num9);
        num10 = findViewById(R.id.num10);
        num11 = findViewById(R.id.num11);
        num12 = findViewById(R.id.num12);

    }


//
//    private void toggle() {
//        Transition transition = new Fade();
//        transition.setDuration(600);
//        transition.addTarget(R.id.image);
//
//        TransitionManager.beginDelayedTransition(parent, transition);
//        image.setVisibility(show ? View.VISIBLE : View.GONE);
//    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {


            //write code function
            switch (view.getId()) {
                case R.id.num1:
                    numbers_List.add("1");
                    passNumber(numbers_List);
                    break;
                case R.id.num2:
                    numbers_List.add("2");
                    passNumber(numbers_List);
                    break;
                case R.id.num3:
                    numbers_List.add("3");
                    passNumber(numbers_List);
                    break;
                case R.id.num4:
                    numbers_List.add("4");
                    passNumber(numbers_List);
                    break;
                case R.id.num5:
                    numbers_List.add("5");
                    passNumber(numbers_List);
                    break;
                case R.id.num6:
                    numbers_List.add("6");
                    passNumber(numbers_List);
                    break;
                case R.id.num7:
                    numbers_List.add("7");
                    passNumber(numbers_List);
                    break;
                case R.id.num8:
                    numbers_List.add("8");
                    passNumber(numbers_List);
                    break;
                case R.id.num9:
                    numbers_List.add("9");
                    passNumber(numbers_List);
                    break;
                case R.id.num10:

                    switch (numbers_List.size()) {

                        case (1):
                            numbers_List.remove(0);
                            tv1.setText(null);
                            break;
                        case (2):
                            numbers_List.remove(1);
                            tv2.setText(null);
                            break;
                        case (3):
                            numbers_List.remove(2);
                            tv3.setText(null);
                            break;

                        default:
                            tv4.setText(null);
                            for (int i = 3; i < numbers_List.size(); i++) {
                                numbers_List.remove(3);}
                            break;
                    }
                    break;
                case R.id.num11:
                    numbers_List.add("0");
                    passNumber(numbers_List);
                    break;
                case R.id.num12:
                    numbers_List.clear();
                    passNumber(numbers_List);
                    tv1.setText(null);
                    tv2.setText(null);
                    tv3.setText(null);
                    tv4.setText(null);
                    break;
            }



        switch (numbers_List.size()) {
            case 1:
                num_01 = numbers_List.get(0);

                tv1.setText(num_01);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tv1.setText("*");
                    }
                }, 250);

                break;
            case 2:
                num_02 = numbers_List.get(1);
                tv2.setText(num_02);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tv2.setText("*");
                    }
                }, 250);
                break;
            case 3:
                num_03 = numbers_List.get(2);
                tv3.setText(num_03);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tv3.setText("*");
                    }
                }, 250);
                break;
            case 4:
                num_04 = numbers_List.get(3);
                tv4.setText(num_04);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tv4.setText("*");
                    }
                }, 250);

                passCode = num_01 + num_02 + num_03 + num_04;
//                savePassCode(passCode);
                savePin(passCode);
                break;
        }

        //if statement to get the value
        if (numbers_List.size() == 4) {

//            savePassCode(passCode);
//            logo_image.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.green));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    confirm_button.setVisibility(View.VISIBLE);
                    logo_image.setImageResource(R.drawable.code_icon_verified);
                    logo_image.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.green));

                    //make vibration
                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        v.vibrate(VibrationEffect.createOneShot(60, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        //deprecated in API 26
                        v.vibrate(50);
                    }
                }
            }, 100);

        } else if (numbers_List.size() >= 1 && numbers_List.size() < 4) {
            logo_image.setImageResource(R.drawable.code_icon);
//            logo_image.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.code_icon));
            logo_image.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.red));
            confirm_button.setVisibility(View.GONE);
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    confirm_button.setVisibility(View.GONE);
//                }
//            }, 350);
        } else if (numbers_List.size() == 0) {
            logo_image.setImageResource(R.drawable.code_icon);
//            logo_image.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.code_icon));
            logo_image.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.myCustomWhite));
            confirm_button.setVisibility(View.GONE);

//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    confirm_button.setVisibility(View.GONE);
//                }
//            }, 350);
        }

    }


    private void passNumber(ArrayList<String> numbers_list) {
//        if(numbers_list == null)

    }


//    private SharedPreferences.Editor savePassCode(String passCode) {
//
//        SharedPreferences preferences = getSharedPreferences("passcode_pref", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.putString("passcode", passCode);
//        editor.apply();
//        return editor;
//
//    }


    private void savePin(String passCode){
//        secureSharedPref = new SecureSharedPref(this,"passcode_pref");
//        secureSharedPref.put("passcode",passCode);

        new SecurePrefManagerInit.Initializer(getApplicationContext())
                .useEncryption(true)
                .initialize();

        SecurePrefManager.with(this)
                .set("passcode")
                .value(passCode)
                .go();

    }

    public void goToNextActivity(View view) {

//        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        startActivity(new Intent(setPinCode.this, Login_signup.class));

    }

//    private String getPassCode() {
//        SharedPreferences preferences = getSharedPreferences("passcode_pref", Context.MODE_PRIVATE);
//        return preferences.getString("passcode", "");
//    }

//    private String getPin(){
//
//        return secureSharedPref.get("passcode");
//    }







}