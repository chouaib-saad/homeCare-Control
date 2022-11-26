package com.example.myapplication;


import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.myapplication.Utility.NetworkChangeListener;
import com.example.myapplication.main_fragments.Control;
import com.example.myapplication.main_fragments.Settings;
import com.example.myapplication.main_fragments.Stats;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;
import soup.neumorphism.NeumorphCardView;

public class SmartHome_Main extends AppCompatActivity {


    NetworkChangeListener networkChangeListener = new NetworkChangeListener();

//    public DataClass dataClass ;

    //bottomBar
    SmoothBottomBar bottomBar;
//    BottomNavigationView bottom_navigation;

    //inisialise fragments
    Control control = new Control();
    Stats stats = new Stats();
    Settings settings = new Settings();

    public static NeumorphCardView progressBar;


    //set user Informationsj
    TextView addresse, name, example3, example4, user_name;

    //profile image
    ImageView profil_image;




    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListener);
        super.onStop();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        startActivity(new Intent(SmartHome_Main.this, PinActivity.class));
//        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        setTheUserData();
    }



    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener,filter);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        super.onStart();
        setTheUserData();
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
        setContentView(R.layout.smart_home_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        //progressbar hook;
        progressBar = findViewById(R.id.progress_bar_block);


        //profil components
        user_name = findViewById(R.id.user_name);
        profil_image = findViewById(R.id.profil_image);

        // ** set the first fragment as active **
//        replace(new Control());
//        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.frame,control).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame,control).commit();

        // ** make the bottom menu **
        bottomMenu();

        // ** set the user data **
        setTheUserData();



















      }  // *** OnCreate end ***
















    private void bottomMenu(){
        bottomBar = findViewById(R.id.bottomBar);
        bottomBar.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public boolean onItemSelect(int i) {
//                Fragment fragment = null;
                switch (i) {
                    case 0:
                        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.frame,control).commit();

//                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out);
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                transaction.replace(R.id.frame,control).commit();
//                            }
//                        },200);
//                        fragment = control;
//                        replace(new Control());
                        return true;

                    case 1:
                        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.frame,stats).commit();

//                        replace(new Stats());
//                        fragment = stats;
                        return true;

                    case 2:
                        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.frame,settings).commit();
//                        fragment = settings;
//                        replace(new Settings());
                        return true;

                }
//                if(fragment != null){
//                    getSupportFragmentManager().beginTransaction().replace(R.id.frame,fragment).commit();
//                }
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
                  return false;
            }
        });

    }





//    private void replace(Fragment fragment) {
//
//        getSupportFragmentManager().beginTransaction().replace(R.id.frame,fragment).commit();
////        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
////        transaction.replace(R.id.frame,fragment);
////        transaction.commit();
//    }



    private void setTheUserData() {

        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(SmartHome_Main.this);
        if (googleSignInAccount != null) {


            //set profil name
            user_name.setText("welcome " + googleSignInAccount.getFamilyName().toLowerCase());
            //set profil image
            String profil_photo_link = googleSignInAccount.getPhotoUrl().toString();
            Glide.with(this)
                    .load(profil_photo_link)
                    .placeholder(R.drawable.image_loader)
                    .into(profil_image);

        } else {

            String username = getIntent().getStringExtra("name");
            String phone = getIntent().getStringExtra("phoneNumber");

            user_name.setText("welcome " + username);
            profil_image.setImageDrawable(getResources().getDrawable(R.drawable.default_user_img));
            profil_image.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.bgBlue));

        }

    }

}//class end