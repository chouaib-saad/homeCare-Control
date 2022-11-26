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
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.example.myapplication.Utility.SecureSharedPref;
import com.prashantsolanki.secureprefmanager.SecurePrefManager;
import com.prashantsolanki.secureprefmanager.SecurePrefManagerInit;

import java.util.ArrayList;
import java.util.concurrent.Executor;

public class PinActivity extends AppCompatActivity implements View.OnClickListener {


////    vibration declaration
//Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


    //    Secure Shared Pref
    private SecureSharedPref secureSharedPref;

    TextView fingerprint;

    TextView logo_txt;


    TextView tv1, tv2, tv3, tv4;
    Button num1, num2, num3, num4, num5, num6, num7, num8, num9, num10, num11, num12;

    ImageView logo_image;

    private static Integer Attempts = 5;

    String passCode = "";
    String num_01, num_02, num_03, num_04;
    ArrayList<String> numbers_List = new ArrayList<>();




    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pin_activity);

        //image hook
        logo_image = findViewById(R.id.logo_image);

        //fingerprint inialising
        fingerprint = findViewById(R.id.fingerprint);


        initialiseComponents();


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



        logo_txt = findViewById(R.id.logo_txt);


        BiometricManager biometricManager = BiometricManager.from(this);
        switch (biometricManager.canAuthenticate()) {

//            case BiometricManager.BIOMETRIC_SUCCESS:
//                Toast.makeText(this, "You can use fingerprint sensor", Toast.LENGTH_SHORT).show();
//                break;

            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Toast.makeText(this, "Device doesn't have fingerprint sensor", Toast.LENGTH_SHORT).show();
                break;

            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Toast.makeText(this, "the biometric sensor is currently unvailable", Toast.LENGTH_SHORT).show();
                break;

            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                Toast.makeText(this, "your device don't have any fingerprint saved,please check your security settings", Toast.LENGTH_SHORT).show();
                break;

            case BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED:
                Toast.makeText(this, "fingerprint not supported in this device", Toast.LENGTH_SHORT).show();
                break;

            case BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED:
                Toast.makeText(this, "Security update required", Toast.LENGTH_SHORT).show();
                break;
        }



        //executer
        Executor executor = ContextCompat.getMainExecutor(this);
        //we can change all this line directly with this line ContextCompat.getMainExecutor(this)
        BiometricPrompt biometricPrompt = new BiometricPrompt(this,executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);

                //start the dashboard activity
                logo_image.setImageResource(R.drawable.code_icon_verified);
                logo_image.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.green));
                logo_txt.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.green));
                logo_txt.setText("CORRECTED PIN");
                startDashboardActivity();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(PinActivity.this, "Failed to authenticate", Toast.LENGTH_SHORT).show();
            }
        });

//        BiometricPrompt.PromptInfo info = new BiometricPrompt.PromptInfo.Builder()
//                .setTitle("Fingerprint")
//                .setDescription("use fingerprint to unlock your app")
//                .setNegativeButtonText("Cancel")
//                .build();


                final BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Fingerprint")
                .setDescription("use fingerprint to unlock your app")
                .setNegativeButtonText("Cancel")
                .build();



                fingerprint.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        biometricPrompt.authenticate(promptInfo);

                    }
                });




////        make vibration
//        final Vibrator vibe = (Vibrator);
//        PinActivity.this.getSystemService(Context.VIBRATOR_SERVICE);


    } //onCreate end




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


                        case (4):
                            numbers_List.remove(3);
                            tv4.setText(null);
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
                tv1.setText("*");
                break;
            case 2:
                num_02 = numbers_List.get(1);
                tv2.setText("*");
                break;
            case 3:
                num_03 = numbers_List.get(2);
                tv3.setText("*");
                break;
            case 4:
                num_04 = numbers_List.get(3);
                tv4.setText("*");
                passCode = num_01 + num_02 + num_03 + num_04;

                matchPasscode();

                break;
        }

    }

    private void matchPasscode() {
        if (getPin().equals(passCode)) {

            logo_image.setImageResource(R.drawable.code_icon_verified);
            logo_image.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.green));
            logo_txt.setText("CORRECTED PIN");
            logo_txt.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.green));

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Attempts = 5;
//                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    //        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                    startDashboardActivity();
                }
            }, 500);
        } else {
            Attempts--;
            //Toast.makeText(this, "code pin errone", Toast.LENGTH_SHORT).show();

            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(60, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                //deprecated in API 26
                v.vibrate(60);
            }

            logo_image.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.red));
            logo_txt.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
            logo_txt.setText("WRONG PIN");
//            Animation shake;
//            shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake_animation);
//            logo_image.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake_animation)); // starts animation
            //number 1
            tv4.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake_animation)); // starts animation
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    tv3.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake_animation)); // starts animation
                    tv4.setText(null);
                    //number 2
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            tv2.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake_animation)); // starts animation
                            tv3.setText(null);
                            //number 3
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    tv1.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake_animation)); // starts animation
                                    tv2.setText(null);
                                    //number 4
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            tv1.setText(null);
//                                            if (Attempts > 0) {
                                                logo_image.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.myCustomWhite));
                                                logo_txt.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.myCustomWhite));
                                                logo_txt.setText("ENTRE VOTRE CODE PIN");
//                                            } else {
//                                                logo_image.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.red));

//                                            }
                                            numbers_List.clear();
                                        }
                                    }, 125);
                                }
                            }, 125);
                        }
                    }, 125);
                }
            }, 300); //endHandlers

//            Toast.makeText(this, Attempts + " Attempts remained", Toast.LENGTH_SHORT).show();
//            if (Attempts == 0) {
//                Toast.makeText(this, "You're not the real user", Toast.LENGTH_LONG).show();
//                logo_image.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.red));
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(PinActivity.this, "Closing the App..", Toast.LENGTH_LONG).show();
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                finishAffinity();
//                                Attempts = 5;
//
//                            }
//                        }, 1500);
//                    }
//                }, 1000);
//            }
        }
    }

    private void startDashboardActivity() {
        startActivity(new Intent(getApplicationContext(), SmartHome_Main.class));
        finish();
    }


    private void passNumber(ArrayList<String> numbers_list) {
        //if(numbers_list == null)
    }


    private SharedPreferences.Editor savePassCode(String passCode) {
        SharedPreferences preferences = getSharedPreferences("passcode_pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("passcode", passCode);
        editor.apply();

        return editor;
    }

//    private String getPassCode() {
//        SharedPreferences preferences = getSharedPreferences("passcode_pref", Context.MODE_PRIVATE);
//        return preferences.getString("passcode", "");
//
//    }


    private String getPin(){

//        secureSharedPref = new SecureSharedPref(this,"passcode_pref");
//        return secureSharedPref.get("passcode");

        new SecurePrefManagerInit.Initializer(getApplicationContext())
                .useEncryption(true)
                .initialize();

        return    SecurePrefManager.with(this)
                .get("passcode")
                .defaultValue("unknown")
                .go();


    }


//    public void new_pin_restore(View view) {
//        startActivity(new Intent(getApplicationContext(), PinChange.class));
//    }




//    public void fingerPrint_unlock(View view) {
//        biometricPrompt.authenticate(promptInfo);
//    }


}   //class end