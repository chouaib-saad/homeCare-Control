package com.example.myapplication;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.myapplication.Utility.NetworkChangeListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {


    NetworkChangeListener networkChangeListener = new NetworkChangeListener();


    ConstraintLayout signup_btn;

    //progressBar
    ProgressBar progressBar;

    //input fields
    EditText name, email, password, conf_password, phone;

    //realtime db connect
    FirebaseDatabase rootNode;
    DatabaseReference reference;


    //checkbox
    CheckBox agree_terms;


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }


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
    public void onBackPressed() {
        backRotationAnimation();
    }

    //   ***
    //onCreate methode begin
//   ***
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);


        //signup_btn hook
        signup_btn = findViewById(R.id.signup_btn);


        //hook fields
        name = findViewById(R.id.name_field);
        email = findViewById(R.id.email_field);
        password = findViewById(R.id.password_field);
        conf_password = findViewById(R.id.retype_password_field);
        phone = findViewById(R.id.phone_field);

        //progressBar hook
        progressBar = findViewById(R.id.progressbar);


        //signup the user
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBar.setVisibility(View.VISIBLE);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (!validatephone() | !validateConfPassword() | !validatePassword() | !validateEmail() | !validateName()) {
                            progressBar.setVisibility(View.GONE);
                            return;



                            /* point d'entre au activite "inscription verfier*/
                        } else {


                            if (!isTermesChecked()) {
                                progressBar.setVisibility(View.GONE);
                                return;
                            } else {

                                //delete focus
                                agree_terms.setError(null);

                                rootNode = FirebaseDatabase.getInstance();

                                //to create an instance
                                reference = rootNode.getReference("users");

                                //to create a referance
                                //reference.setValue("First data storage");


                                //get all the values from the edit_text fields
                                String user_name = name.getText().toString();
                                String user_email = email.getText().toString();
                                String user_password = password.getText().toString();
                                String user_phone = phone.getText().toString();


//                                            String userId = reference.push().getKey();
//                                            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();


                                //save user data in firebase with UserData class
                                UserData userData = new UserData(user_name, user_email, user_password, user_phone);

                                //save values on the user instance
                                reference.child(user_phone).setValue(userData).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(Signup.this, "success signup", Toast.LENGTH_SHORT).show();
                                    }

                                });

                            }


                        }
                    }
                }, 600);
            }
        });


    } //onCreate end

    public void returnBtn(View view) {
//        findViewById(R.id.log_back).setVisibility(View.INVISIBLE);
        backRotationAnimation();
    }


    private void backRotationAnimation() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                findViewById(R.id.log_back).setRotation(30);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        findViewById(R.id.log_back).setRotation(60);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                findViewById(R.id.log_back).setRotation(90);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Signup.super.onBackPressed();
                                        findViewById(R.id.log_back).setRotation(120);
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                findViewById(R.id.log_back).setRotation(150);
                                                new Handler().postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        findViewById(R.id.log_back).setRotation(180);
                                                    }
                                                }, 30);
                                            }
                                        }, 30);
                                    }
                                }, 30);
                            }
                        }, 30);
                    }
                }, 30);
            }
        }, 30);
    }


    //    **validation functions***


    private Boolean validateName() {
        String val = name.getText().toString();
        /*en peut utiliser aussi val.equals("")
        pour verifier que le champ est vide*/
        if (val.trim().isEmpty()) {
            name.requestFocus();
            name.setError("field can not be empty");
            return false;
        } else if (val.trim().length() <= 6) {
            name.requestFocus();
            name.setError("name is too short");
            name.setTextColor(getResources().getColor(R.color.red));
            return false;
        } else {
            name.setError(null);
            name.setTextColor(getResources().getColor(R.color.greey));
            return true;

        }
    }


    private Boolean validateEmail() {
        String val = email.getText().toString();
        /*regic expression: pour Respecter la forme de @ email*/
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        /*en peut utiliser aussi val.equals("")
        pour verifier que le champ est vide*/
        if (val.trim().isEmpty()) {
            email.requestFocus();
            email.setError("field can not be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            email.requestFocus();
            email.setTextColor(getResources().getColor(R.color.red));
            email.setError("email is not valid");
            return false;
        } else {
            email.setError(null);
            email.setTextColor(getResources().getColor(R.color.greey));
            return true;
        }

    }


    private Boolean validatePassword() {
        String val = password.getText().toString();
        //"^" : starting of the screen "$" : end of the screen
        //il faut que le mot de passe contient le suivant:

        /* a verifier
        String passwordVal ="^"+
                //"(?=.*[0-9])" +             //at least 1 digit
                //"(?=.*[a-z])" +             //at least 1 lower case letter
                //"(?=.*[A-Z])" +             //at least 1 upper case letter
                "(?=.*[a-zA-Z])"+           //any letter
                "(?=.*[@#$%^&+=])"+         //at least 1 special caracter
                //"(?=\\s+$)" +             //no white spaces 1
                "\\A\\w{4,20}\\z" +         //no white spaces 2
                ".{4,}" +                   //at least 4 characters
                "$";
        */
        /*en peut utiliser aussi val.equals("")
        pour verifier que le champ est vide*/
        if (val.trim().isEmpty()) {
            password.requestFocus();
            password.setError("field can not be empty");
            return false;
        }
//        else if(val.trim().length()<=6){
//            password.requestFocus();
//            password.setError("password is to");
//            password.setTextColor(getResources().getColor(R.color.red));
//            return false;
//        }
        else if (val.matches("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$") || val.trim().length() <= 7) {
            password.requestFocus();
            password.setTextColor(getResources().getColor(R.color.red));
            password.setError("password is too weak");
            return false;
        } else {
            password.setError(null);
            password.setTextColor(getResources().getColor(R.color.greey));
            return true;
        }

    }


    private Boolean validateConfPassword() {
        String val = conf_password.getText().toString();
        if (val.trim().isEmpty()) {
            conf_password.requestFocus();
            conf_password.setError("field can not be empty");
            return false;
        } else if (!conf_password.getText().toString().equals(password.getText().toString())) {
            conf_password.requestFocus();
            conf_password.setError("Passwords do not match");
            conf_password.setTextColor(getResources().getColor(R.color.red));
            return false;
        } else {
            conf_password.setError(null);
            conf_password.setTextColor(getResources().getColor(R.color.greey));
            return true;
        }

    }


    private Boolean validatephone() {
        String val = phone.getText().toString();
        /*en peut utiliser aussi val.equals("")
        pour verifier que le champ est vide*/
        if (val.trim().isEmpty()) {
            phone.requestFocus();
            phone.setError("field can not be empty");
            return false;
        } else if (val.trim().length() != 8) {
            phone.requestFocus();
            phone.setError("invalid phone number");
            phone.setTextColor(getResources().getColor(R.color.red));
            return false;
        } else if (!val.startsWith("2") && !val.startsWith("9") && !val.startsWith("5") && !val.startsWith("4")) {
            phone.requestFocus();
            phone.setError("invalid phone number");
            phone.setTextColor(getResources().getColor(R.color.red));
            return false;
        } else {
            phone.setError(null);
            phone.setTextColor(getResources().getColor(R.color.greey));
            return true;
        }

    }


    private Boolean isTermesChecked() {
        //agree termes button
        agree_terms = findViewById(R.id.agree_terms);
        if (!agree_terms.isChecked()) {
            agree_terms.setError("Kindly,accept Terms of Service and Privacy Policy");
            return false;
        } else {
            agree_terms.setError(null);
            return true;
        }

    }

//    **end validation functions***


} //class end