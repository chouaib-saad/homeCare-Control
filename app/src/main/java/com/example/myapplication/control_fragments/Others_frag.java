package com.example.myapplication.control_fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.SmartHome_Main;

import soup.neumorphism.NeumorphCardView;

public class Others_frag extends Fragment {


    //switch text
    TextView switch_txt1 ;
    TextView switch_txt2 ;
    TextView switch_txt3 ;
    TextView switch_txt4 ;
    TextView switch_txt5 ;

    SwitchCompat switch_btn1 ;
    SwitchCompat switch_btn2 ;
    SwitchCompat switch_btn3 ;
    SwitchCompat switch_btn4 ;
    SwitchCompat switch_btn5 ;



    //switch images
    ImageView lampe_im1;
    ImageView lampe_im2;
    ImageView lampe_im3;
    ImageView door_im;
    ImageView garage_im;


    //add device animation
    NeumorphCardView addNewDevice;


    //switches layout
//    LinearLayout lampe_circle1;
//    LinearLayout lampe_circle2;
//    LinearLayout lampe_circle3;
//    LinearLayout door_circle;
//    LinearLayout garage_circle;





                        //*** onCreate begin ***

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_others, container, false);


        //switch components hooks
        switch_btn1 = v.findViewById(R.id.switch_btn1);
        switch_txt1 = v.findViewById(R.id.switch_txt1);
        switch_btn2 = v.findViewById(R.id.switch_btn2);
        switch_txt2 = v.findViewById(R.id.switch_txt2);
        switch_btn3 = v.findViewById(R.id.switch_btn3);
        switch_txt3 = v.findViewById(R.id.switch_txt3);
        switch_btn4 = v.findViewById(R.id.switch_btn4);
        switch_txt4 = v.findViewById(R.id.switch_txt4);
        switch_btn5 = v.findViewById(R.id.switch_btn5);
        switch_txt5 = v.findViewById(R.id.switch_txt5);

        //switch image hook
        lampe_im1 = v.findViewById(R.id.lampe_im1);
        lampe_im2 = v.findViewById(R.id.lampe_im2);
        lampe_im3 = v.findViewById(R.id.lampe_im3);
        door_im = v.findViewById(R.id.door_im);
        garage_im = v.findViewById(R.id.garage_im);




        //switch circles layout
//        lampe_circle1 = v.findViewById(R.id.lampe_circle1);
//        lampe_circle2 = v.findViewById(R.id.lampe_circle2);
//        lampe_circle3 = v.findViewById(R.id.lampe_circle3);
//        door_circle = v.findViewById(R.id.door_circle);
//        garage_circle = v.findViewById(R.id.garage_circle);


        v.findViewById(R.id.add_new_device).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.findViewById(R.id.add_new_device).startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.shake_animation));
            }
        });


        switch_btn1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {


                v.findViewById(R.id.card1).startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.image_anim)); // starts animation

                if(switch_btn1.isChecked()){
//                    lampe_circle1.setBackground(ContextCompat.getDrawable(v.getContext(),R.drawable.device_circle_vert));
//                    deviceName1.setTextSize(14);
//                    switch_txt1.setTextSize(13);

                            switch_txt1.setText("ON");
                            switch_txt1.setTextColor(getResources().getColor(R.color.green));
                            lampe_im1.setColorFilter(ContextCompat.getColor(v.getContext(),R.color.green));




                    //save state
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("switches_state_others", Context.MODE_PRIVATE).edit();
                    editor.putBoolean("switch1", true);
                    editor.apply();
                }

                else{
//                    lampe_circle1.setBackground(ContextCompat.getDrawable(v.getContext(),R.drawable.device_circle));
//                    deviceName1.setTextSize(12);
//                    switch_txt1.setTextSize(11);

                            switch_txt1.setText("OFF");
                            switch_txt1.setTextColor(getResources().getColor(R.color.red));
                            lampe_im1.setColorFilter(ContextCompat.getColor(v.getContext(), R.color.red));






                    //save state
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("switches_state_others", Context.MODE_PRIVATE).edit();
                    editor.putBoolean("switch1", false);
                    editor.apply();
                }

            }});




        switch_btn2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                v.findViewById(R.id.card2).startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.image_anim)); // starts animation


                if(switch_btn2.isChecked()){
//                    lampe_circle2.setBackground(ContextCompat.getDrawable(v.getContext(),R.drawable.device_circle_vert));


                            switch_txt2.setText("ON");
                            switch_txt2.setTextColor(getResources().getColor(R.color.green));
                            lampe_im2.setColorFilter(ContextCompat.getColor(v.getContext(),R.color.green));



                    //save state
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("switches_state_others", Context.MODE_PRIVATE).edit();
                    editor.putBoolean("switch2", true);
                    editor.apply();

                }
                else{
//                    lampe_circle2.setBackground(ContextCompat.getDrawable(v.getContext(),R.drawable.device_circle));


                            switch_txt2.setText("OFF");
                            switch_txt2.setTextColor(getResources().getColor(R.color.red));
                            lampe_im2.setColorFilter(ContextCompat.getColor(v.getContext(), R.color.red));



                    //save state
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("switches_state_others", Context.MODE_PRIVATE).edit();
                    editor.putBoolean("switch2", false);
                    editor.apply();

                }
            }});




        switch_btn3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                v.findViewById(R.id.card3).startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.image_anim)); // starts animation


                if(switch_btn3.isChecked()){
//                    lampe_circle3.setBackground(ContextCompat.getDrawable(v.getContext(),R.drawable.device_circle_vert));

                            switch_txt3.setText("ON");
                            switch_txt3.setTextColor(getResources().getColor(R.color.green));
                            lampe_im3.setColorFilter(ContextCompat.getColor(v.getContext(),R.color.green));


                    //save state
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("switches_state_others", Context.MODE_PRIVATE).edit();
                    editor.putBoolean("switch3", true);
                    editor.apply();

                }
                else{
//                    lampe_circle3.setBackground(ContextCompat.getDrawable(v.getContext(),R.drawable.device_circle));


                            switch_txt3.setText("OFF");
                            switch_txt3.setTextColor(getResources().getColor(R.color.red));
                            lampe_im3.setColorFilter(ContextCompat.getColor(v.getContext(), R.color.red));



                    //save state
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("switches_state_others", Context.MODE_PRIVATE).edit();
                    editor.putBoolean("switch3", false);
                    editor.apply();

                }
            }});




        switch_btn4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                v.findViewById(R.id.card4).startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.image_anim)); // starts animation


                if(switch_btn4.isChecked()){
//                    door_circle.setBackground(ContextCompat.getDrawable(v.getContext(),R.drawable.device_circle_vert));

                            switch_txt4.setText("ON");
                            switch_txt4.setTextColor(getResources().getColor(R.color.green));
                            door_im.setColorFilter(ContextCompat.getColor(v.getContext(),R.color.green));


                    //save state
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("switches_state_others", Context.MODE_PRIVATE).edit();
                    editor.putBoolean("switch4", true);
                    editor.apply();

                }
                else{
//                    door_circle.setBackground(ContextCompat.getDrawable(v.getContext(),R.drawable.device_circle));

                            switch_txt4.setText("OFF");
                            switch_txt4.setTextColor(getResources().getColor(R.color.red));
                            door_im.setColorFilter(ContextCompat.getColor(v.getContext(), R.color.red));

                    //save state
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("switches_state_others", Context.MODE_PRIVATE).edit();
                    editor.putBoolean("switch4", false);
                    editor.apply();

                }
            }});




        switch_btn5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                v.findViewById(R.id.card5).startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.image_anim)); // starts animation


                if(switch_btn5.isChecked()){
//                    garage_circle.setBackground(ContextCompat.getDrawable(v.getContext(),R.drawable.device_circle_vert));


                            switch_txt5.setText("ON");
                            switch_txt5.setTextColor(getResources().getColor(R.color.green));
                            garage_im.setColorFilter(ContextCompat.getColor(v.getContext(),R.color.green));


                    //save state
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("switches_state_others", Context.MODE_PRIVATE).edit();
                    editor.putBoolean("switch5", true);
                    editor.apply();


                }
                else{
//                    garage_circle.setBackground(ContextCompat.getDrawable(v.getContext(),R.drawable.device_circle));


                            switch_txt5.setText("OFF");
                            switch_txt5.setTextColor(getResources().getColor(R.color.red));
                            garage_im.setColorFilter(ContextCompat.getColor(v.getContext(), R.color.red));



                    //save state
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("switches_state_others", Context.MODE_PRIVATE).edit();
                    editor.putBoolean("switch5", false);
                    editor.apply();

                }
            }});







        //check buttons states
        saveSwitchState(v);

        return v;

    }












    //check buttons states
    public void saveSwitchState(View v) {

        //initialise sharedPreference state
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("switches_state_others", Context.MODE_PRIVATE);

        if (sharedPreferences.getBoolean("switch1",false)) {

            switch_btn1.setChecked(true);
            lampe_im1.setColorFilter(ContextCompat.getColor(v.getContext(),R.color.green));
//            lampe_circle1.setBackground(ContextCompat.getDrawable(v.getContext(),R.drawable.device_circle_vert));
            switch_txt1.setTextColor(getResources().getColor(R.color.green));


        } else {

            switch_btn1.setChecked(false);
            lampe_im1.setColorFilter(ContextCompat.getColor(v.getContext(), R.color.red));
//            lampe_circle1.setBackground(ContextCompat.getDrawable(v.getContext(),R.drawable.device_circle));
            switch_txt1.setTextColor(getResources().getColor(R.color.red));


        }


        if (sharedPreferences.getBoolean("switch2", false)) {

            switch_btn2.setChecked(true);
            lampe_im2.setColorFilter(ContextCompat.getColor(v.getContext(),R.color.green));
//            lampe_circle2.setBackground(ContextCompat.getDrawable(v.getContext(),R.drawable.device_circle_vert));
            switch_txt2.setTextColor(getResources().getColor(R.color.green));



        } else {
            switch_btn2.setChecked(false);
            lampe_im2.setColorFilter(ContextCompat.getColor(v.getContext(), R.color.red));
//            lampe_circle2.setBackground(ContextCompat.getDrawable(v.getContext(),R.drawable.device_circle));
            switch_txt2.setTextColor(getResources().getColor(R.color.red));


        }


        if (sharedPreferences.getBoolean("switch3",false)) {

            switch_btn3.setChecked(true);
            lampe_im3.setColorFilter(ContextCompat.getColor(v.getContext(),R.color.green));
//            lampe_circle3.setBackground(ContextCompat.getDrawable(v.getContext(),R.drawable.device_circle_vert));
            switch_txt3.setTextColor(getResources().getColor(R.color.green));


        } else {

            switch_btn3.setChecked(false);
            lampe_im3.setColorFilter(ContextCompat.getColor(v.getContext(), R.color.red));
//            lampe_circle3.setBackground(ContextCompat.getDrawable(v.getContext(),R.drawable.device_circle));
            switch_txt3.setTextColor(getResources().getColor(R.color.red));


        }


        if (sharedPreferences.getBoolean("switch4", false)) {

            switch_btn4.setChecked(true);
            door_im.setColorFilter(ContextCompat.getColor(v.getContext(),R.color.green));
//            door_circle.setBackground(ContextCompat.getDrawable(v.getContext(),R.drawable.device_circle_vert));
            switch_txt4.setTextColor(getResources().getColor(R.color.green));


        } else {

            switch_btn4.setChecked(false);
            door_im.setColorFilter(ContextCompat.getColor(v.getContext(), R.color.red));
//            door_circle.setBackground(ContextCompat.getDrawable(v.getContext(),R.drawable.device_circle));
            switch_txt4.setTextColor(getResources().getColor(R.color.red));


        }


        if (sharedPreferences.getBoolean("switch5", false)) {

            switch_btn5.setChecked(true);
            garage_im.setColorFilter(ContextCompat.getColor(v.getContext(),R.color.green));
//            garage_circle.setBackground(ContextCompat.getDrawable(v.getContext(),R.drawable.device_circle_vert));
            switch_txt5.setTextColor(getResources().getColor(R.color.green));


        }else{

            switch_btn5.setChecked(false);
            garage_im.setColorFilter(ContextCompat.getColor(v.getContext(), R.color.red));
//            garage_circle.setBackground(ContextCompat.getDrawable(v.getContext(),R.drawable.device_circle));
            switch_txt5.setTextColor(getResources().getColor(R.color.red));


        }


    }



}