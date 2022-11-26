package com.example.myapplication.main_fragments;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.myapplication.control_fragments.Bedroom_Frag;
import com.example.myapplication.control_fragments.Others_frag;
import com.example.myapplication.control_fragments.Living_Room_Frag;
import com.example.myapplication.R;
import com.example.myapplication.SmartHome_Main;
import com.example.myapplication.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import me.ibrahimsn.lib.SmoothBottomBar;
import soup.neumorphism.NeumorphCardView;

public class Control extends Fragment{

    //  ****  here defined views ****

    //    progress Bar
//    ProgressBar progressBar;
//    NeumorphCardView progress_bar_block;

    ConstraintLayout logout_btn,controle_layout;
//    TextView addresse, name, example3, example4, user_name;


//    //profile image
//    NeumorphImageView profil_image;


    //    initialize variables
    TabLayout tabLayout;
    ViewPager viewPager;
//    MainAdapter adapter;

    //    bottomBar
//    SmoothBottomBar bottomBar;





                             //*** onCreate begin ***


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment


//        getContext().getTheme().applyStyle(R.style.getStartedTheme,true);

        View v = inflater.inflate(R.layout.fragment_control, container, false);

        //logout_btn hook
        //logout_btn = findViewById(R.id.logout_btn);

        //progressBar hook
//        progressBar = v.findViewById(R.id.progressbar);
//        progress_bar_block = v.findViewById(R.id.progress_bar_block);
        controle_layout = v.findViewById(R.id.controle_layout);
//        progressBar.setVisibility(View.VISIBLE);
//        progress_bar_block.setVisibility(View.VISIBLE);
        SmartHome_Main.progressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
                                      @Override
                                      public void run() {

                                          controle_layout.setVisibility(View.VISIBLE);
                                          addFragment(v);
//                                          progressBar.setVisibility(View.INVISIBLE);
//                                          progress_bar_block.setVisibility(View.VISIBLE);


                                                  SmartHome_Main.progressBar.setVisibility(View.GONE);



                                      }
                                  },1500);























        //let the user logout
//       logout_btn.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View view) {
//               progressBar.setVisibility(View.VISIBLE);
//               FirebaseAuth.getInstance().signOut();
//
//               //**to add later**//
//
//               SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
//               SharedPreferences.Editor editor = preferences.edit();
//               editor.putString("remember","false");
//               editor.apply();
//
//               //**to add later**//
//
//               new Handler().postDelayed(new Runnable() {
//                   @Override
//                   public void run() {
//                       startActivity(new Intent(getApplicationContext(),Login_signup.class));
//                       finish();
//                   }
//               },700);
//
//           }
//       });




//        bottomBar.setOnItemReselectedListener(new OnItemReselectedListener() {
//            @Override
//            public void onItemReselect(int i) {
//                Toast.makeText(SmartHome_Main.this, "world", Toast.LENGTH_SHORT).show();
//
//            }
//        });



        // *** bottom navigation view ***
        //ensure that the first one opened is selected
//        bottomBar.setItemActiveIndex(R.id.control_butt);









        return  v;


    }

    private void addFragment(View v) {

        //Assign variables
        tabLayout = v.findViewById(R.id.tab_layout);
        viewPager = v.findViewById(R.id.view_pager);


        //Initialise adapter
//        adapter = new MainAdapter(requireActivity().getSupportFragmentManager());
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        //add fraglent
        adapter.addFragment(new Living_Room_Frag(), "Living Room");
        adapter.addFragment(new Bedroom_Frag(), "Bedroom");
        adapter.addFragment(new Others_frag(), "Others");


        //Set Adapter
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        //Connect tab layout  with view pager
        tabLayout.setupWithViewPager(viewPager, true);

    }

    //    *** onCreate End ***































}
