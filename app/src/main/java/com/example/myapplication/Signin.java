package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.Utility.NetworkChangeListener;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Signin extends AppCompatActivity {
    ConstraintLayout login_btn;
    EditText password,email,phone;
    ProgressBar progressBar;
    private GoogleSignInClient mGoogleSignInClient;
    private final static int RC_SIGN_IN = 123 ;
    private FirebaseAuth mAuth;

    NetworkChangeListener networkChangeListener = new NetworkChangeListener();


    //checkbox
    CheckBox checkbox_layout;


    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener,filter);
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
        String checkbox = preferences.getString("remember","");

        //complete the code here
        if((user != null) && (checkbox.equals("true"))){
            startActivity(new Intent(getApplicationContext(),SmartHome_Main.class));
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            finish();
        }else{
            Toast.makeText(this, "Please Sign In..", Toast.LENGTH_SHORT).show();
        }

        CheckBox checkbox_layout =  findViewById(R.id.checkbox_layout);
        if(checkbox.equals("true")){
            //open the smart_home_main activity too but we need another test on another variable
            checkbox_layout.setChecked(true);
        }else{
            checkbox_layout.setChecked(false);
        }



    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    @Override
    public void onBackPressed() {
        backRotationAnimation();
    }



    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListener);
        super.onStop();
    }



//onCreate   **main**
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);

        login_btn = findViewById(R.id.login_btn);


        //login fields hook
        email = findViewById(R.id.email_field);
        password = findViewById(R.id.password_field);
        phone = findViewById(R.id.phone_field);

        //progressBar hook
        progressBar = findViewById(R.id.progressbar);


        //fairebase inisialisation
        mAuth = FirebaseAuth.getInstance();



        //login the user
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                            progressBar.setVisibility(View.VISIBLE);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setVisibility(View.GONE);
                                }
                            },600);



                            if(!validatePassword()|!validatephone()){
                                return;


                                /* point d'entre au activite "inscription verfier*/
                            }else {


    //                    progressBar.setVisibility(View.INVISIBLE);
    //                            Toast.makeText(Signin.this, "success login", Toast.LENGTH_SHORT).show();

                                //	registration with firebase etc..

    //                            alertmessage 1 :
    //                            showCustomMessage("its my question here ?");

    //                            alertmessage 2 :
    //                            showAlertDialog("WARNING","are you sure you want to to that ?", "yes", "no",R.drawable.ic_error);


                                progressBar.setVisibility(View.VISIBLE);
                                isUser();

                            }


            }
        });
















    //final Dialog dialog = new Dialog(Signin.this);






        createRequest();


        findViewById(R.id.google_sigin_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });



        //remember_me button
        checkbox_layout = findViewById(R.id.checkbox_layout);

        checkbox_layout.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){

                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","true");
                    editor.apply();
//                    Toast.makeText(Signin.this, "Checked", Toast.LENGTH_SHORT).show();

                }
                else if(!compoundButton.isChecked()){

                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","false");
                    editor.apply();
//                    Toast.makeText(Signin.this, "Unchecked", Toast.LENGTH_SHORT).show();

                }
            }
        });



    }  //end OnCreate

    private void isUser() {

        String userEnteredPhone = phone.getText().toString().trim();
        String userEnteredPassword = password.getText().toString().trim();


        DatabaseReference  reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUser = reference.orderByChild("phoneNumber").equalTo(userEnteredPhone);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()) {

                    phone.setError(null);


                    String passwordFromDB = dataSnapshot.child(userEnteredPhone).child("password").getValue(String.class);
                    if(passwordFromDB.equals(userEnteredPassword)){

                        phone.setError(null);

                        String emailFromDB = dataSnapshot.child(userEnteredPhone).child("email").getValue(String.class);
                        String nameFromDB = dataSnapshot.child(userEnteredPhone).child("name").getValue(String.class);
                        String phoneNumberFromDB = dataSnapshot.child(userEnteredPhone).child("phoneNumber").getValue(String.class);

                        Intent intent = new  Intent(Signin.this,SmartHome_Main.class);

                        intent.putExtra("name",nameFromDB);
                        intent.putExtra("email",emailFromDB);
                        intent.putExtra("phoneNumber",phoneNumberFromDB);
                        intent.putExtra("password",passwordFromDB);

                        startActivity(intent);

                    }else {
                        password.setError("wrong password");
                        password.requestFocus();
                        progressBar.setVisibility(View.GONE);

                    }

                }
                else {
                    phone.setError("no such user exist");
                    phone.requestFocus();
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    private void createRequest(){
        //configure google sign in
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        //build  a GoogleSigninClient with the options specified by gso
        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);
    }

    private void signIn(){
        progressBar.setVisibility(View.VISIBLE);
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent,RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        Result returned from launching the Intent from GoogleSignApi getSignInIntent(...);
        if(requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            progressBar.setVisibility(View.VISIBLE);

            try {
//                Google Sign In was Successfully , authenticatione with Firebase
                        GoogleSignInAccount account = task.getResult(ApiException.class);
                    firebaseAuthWithGoogle(account);
                    progressBar.setVisibility(View.INVISIBLE);
                    Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    startActivityForResult(signInIntent,RC_SIGN_IN);

            } catch (ApiException e) {

                //google Signin failed => UI update
//                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(this,"please select an account", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(),null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this,new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
//                            sign in successfully => ui update
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(getApplicationContext(),SmartHome_Main.class));
                            progressBar.setVisibility(View.INVISIBLE);
                            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

                        }else{
//                        sign in failed display a message
                            Toast.makeText(Signin.this,"Authentication failed", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);

                        }
                    }
                });
    }

    public  void showAlertDialog(String title, String description, String positive_butt, String negative_butt, int dialog_ic) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(Signin.this,R.style.AlertDialogTheme);
//        AlertDialog.Builder builder = new AlertDialog.Builder(Signin.this,R.style.AlertDialogTheme);
        builder.setTitle(title)
                .setMessage(description)
                .setPositiveButton(positive_butt, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(Signin.this, "positive pressed", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(negative_butt, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(Signin.this, "Negative pressed", Toast.LENGTH_SHORT).show();
                    }
                })
                .setBackground(getResources().getDrawable(R.drawable.dialog_custom_bg))
                .setIcon(dialog_ic)
                .setCancelable(false)
                .show();
    }




























    public void returnBtn(View view) {
        backRotationAnimation();
//        findViewById(R.id.log_back).setVisibility(View.INVISIBLE);

    }





















//    **validation functions***

//    private Boolean validateEmail() {
//        String val = email.getText().toString();
//        /*regic expression: pour Respecter la forme de @ email*/
//        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
//        /*en peut utiliser aussi val.equals("")
//        pour verifier que le champ est vide*/
//        if (val.trim().isEmpty()) {
//            email.requestFocus();
//            email.setError("field can not be empty");
//            return false;
//        } else if(!val.matches(emailPattern)){
//            email.requestFocus();
//            email.setTextColor(getResources().getColor(R.color.red));
//            email.setError("email is not valid");
//            return false;
//        }
//        else {
//            email.setError(null);
//            email.setTextColor(getResources().getColor(R.color.greey));
//            return true;
//        }
//
//    }


    private Boolean validatephone() {
        String val = phone.getText().toString();
        /*en peut utiliser aussi val.equals("")
        pour verifier que le champ est vide*/
        if (val.trim().isEmpty()) {
            phone.requestFocus();
            phone.setError("field can not be empty");
            return false;
        } else if(val.trim().length()!=8){
            phone.requestFocus();
            phone.setError("invalid phone number");
            phone.setTextColor(getResources().getColor(R.color.red));
            return false;
        }else if(!val.startsWith("2")&&!val.startsWith("9")&&!val.startsWith("5")&&!val.startsWith("4")){
            phone.requestFocus();
            phone.setError("invalid phone number");
            phone.setTextColor(getResources().getColor(R.color.red));
            return false;
        }
        else {
            phone.setError(null);
            phone.setTextColor(getResources().getColor(R.color.greey));
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
        else {
            password.setError(null);
            password.setTextColor(getResources().getColor(R.color.greey));
            return true;
        }

    }


//    **end validation functions***




































    private void backRotationAnimation(){
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
                                        findViewById(R.id.log_back).setRotation(120);
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                Signin.super.onBackPressed();
                                                findViewById(R.id.log_back).setRotation(150);
                                                new Handler().postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        findViewById(R.id.log_back).setRotation(180);
                                                    }
                                                },30);
                                            }
                                        },30);
                                    }
                                },30);
                            }
                        },30);
                    }
                },30);
            }
        },30);
    }















//    private void showCustomMessage(String description){
//        Dialog dialog = new Dialog(Signin.this);
//        dialog.setContentView(R.layout.custom_dialog);
//        dialog.setCancelable(false);
//        dialog.show();
//        ConstraintLayout btn_yes,btn_no;
//        TextView description_tv;
//        btn_yes = dialog.findViewById(R.id.yes);
//        btn_no = dialog.findViewById(R.id.no);
//        description_tv = dialog.findViewById(R.id.dialog_description);
//        description_tv.setText(description);
//
//        btn_yes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(Signin.this, "button pressed !", Toast.LENGTH_SHORT).show();
//                dialog.dismiss();
//            }
//        });
//
//        btn_no.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });
//
//    }






}  //class end