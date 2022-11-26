package com.example.myapplication.control_fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

import soup.neumorphism.NeumorphCardView;

public class Living_Room_Frag extends Fragment {


    //switch text
    TextView switch_txt1;
    TextView switch_txt2;
    TextView switch_txt3;
    TextView switch_txt4;
    SwitchCompat switch_btn1;
    SwitchCompat switch_btn2;
    SwitchCompat switch_btn3;
    SwitchCompat switch_btn4;

    //switch images
    ImageView lampe_im;
    ImageView smarttv_im;
    ImageView aircond_im;
    ImageView speaker_im;

    //switches layout
//    LinearLayout lampe_circle;
//    LinearLayout tv_circle;
//    LinearLayout aircond_circle;
//    LinearLayout speaker_circle;


    //*** onCreate begin ***


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_living_room, container, false);




        //switch components hooks
        switch_btn1 = v.findViewById(R.id.switch_btn1);
        switch_txt1 = v.findViewById(R.id.switch_txt1);
        switch_btn2 = v.findViewById(R.id.switch_btn2);
        switch_txt2 = v.findViewById(R.id.switch_txt2);
        switch_btn3 = v.findViewById(R.id.switch_btn3);
        switch_txt3 = v.findViewById(R.id.switch_txt3);
        switch_btn4 = v.findViewById(R.id.switch_btn4);
        switch_txt4 = v.findViewById(R.id.switch_txt4);

        //switch image hook
        speaker_im = v.findViewById(R.id.speaker_im);
        lampe_im = v.findViewById(R.id.lampe_im);
        aircond_im = v.findViewById(R.id.aircond_im);
        smarttv_im = v.findViewById(R.id.smarttv_im);

        //switch circles layout
//        lampe_circle = v.findViewById(R.id.lampe_circle);
//        tv_circle = v.findViewById(R.id.tv_circle);
//        speaker_circle = v.findViewById(R.id.speaker_circle);
//        aircond_circle = v.findViewById(R.id.aircond_circle);



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


                if (switch_btn1.isChecked()) {
                    switch_txt1.setText("ON");
                    lampe_im.setColorFilter(ContextCompat.getColor(v.getContext(), R.color.green));
//                    lampe_circle.setBackground(ContextCompat.getDrawable(v.getContext(), R.drawable.device_circle_vert));
                    switch_txt1.setTextColor(getResources().getColor(R.color.green));



                    //save state
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("switches_state_living_room", Context.MODE_PRIVATE).edit();
                    editor.putBoolean("switch1", true);
                    editor.apply();


                } else {
                    switch_txt1.setText("OFF");
                    lampe_im.setColorFilter(ContextCompat.getColor(v.getContext(), R.color.red));
//                    lampe_circle.setBackground(ContextCompat.getDrawable(v.getContext(), R.drawable.device_circle));
                    switch_txt1.setTextColor(getResources().getColor(R.color.red));





                    //save state
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("switches_state_living_room", Context.MODE_PRIVATE).edit();
                    editor.putBoolean("switch1", false);
                    editor.apply();

                }
            }
        });


        switch_btn2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                v.findViewById(R.id.card2).startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.image_anim)); // starts animation


                if (switch_btn2.isChecked()) {
                    switch_txt2.setText("ON");
                    smarttv_im.setColorFilter(ContextCompat.getColor(v.getContext(), R.color.green));
//                    tv_circle.setBackground(ContextCompat.getDrawable(v.getContext(), R.drawable.device_circle_vert));
                    switch_txt2.setTextColor(getResources().getColor(R.color.green));


                    //save state
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("switches_state_living_room", Context.MODE_PRIVATE).edit();
                    editor.putBoolean("switch2", true);
                    editor.apply();


                } else {
                    switch_txt2.setText("OFF");
                    smarttv_im.setColorFilter(ContextCompat.getColor(v.getContext(), R.color.red));
//                    tv_circle.setBackground(ContextCompat.getDrawable(v.getContext(), R.drawable.device_circle));
                    switch_txt2.setTextColor(getResources().getColor(R.color.red));


                    //save state
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("switches_state_living_room", Context.MODE_PRIVATE).edit();
                    editor.putBoolean("switch2", false);
                    editor.apply();

                }
            }
        });


        switch_btn3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                v.findViewById(R.id.card3).startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.image_anim)); // starts animation

                if (switch_btn3.isChecked()) {
                    switch_txt3.setText("ON");
                    aircond_im.setColorFilter(ContextCompat.getColor(v.getContext(), R.color.green));
//                    aircond_circle.setBackground(ContextCompat.getDrawable(v.getContext(), R.drawable.device_circle_vert));
                    switch_txt3.setTextColor(getResources().getColor(R.color.green));


                    //save state
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("switches_state_living_room", Context.MODE_PRIVATE).edit();
                    editor.putBoolean("switch3", true);
                    editor.apply();


                } else {
                    switch_txt3.setText("OFF");
                    aircond_im.setColorFilter(ContextCompat.getColor(v.getContext(), R.color.red));
//                    aircond_circle.setBackground(ContextCompat.getDrawable(v.getContext(), R.drawable.device_circle));
                    switch_txt3.setTextColor(getResources().getColor(R.color.red));


                    //save state
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("switches_state_living_room", Context.MODE_PRIVATE).edit();
                    editor.putBoolean("switch3", false);
                    editor.apply();

                }
            }
        });


        switch_btn4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                v.findViewById(R.id.card4).startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.image_anim)); // starts animation


                if (switch_btn4.isChecked()) {
                    switch_txt4.setText("ON");
                    speaker_im.setColorFilter(ContextCompat.getColor(v.getContext(), R.color.green));
//                    speaker_circle.setBackground(ContextCompat.getDrawable(v.getContext(), R.drawable.device_circle_vert));
                    switch_txt4.setTextColor(getResources().getColor(R.color.green));

                    //save state
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("switches_state_living_room", Context.MODE_PRIVATE).edit();
                    editor.putBoolean("switch4", true);
                    editor.apply();

                } else {
                    switch_txt4.setText("OFF");
                    speaker_im.setColorFilter(ContextCompat.getColor(v.getContext(), R.color.red));
//                    speaker_circle.setBackground(ContextCompat.getDrawable(v.getContext(), R.drawable.device_circle));
                    switch_txt4.setTextColor(getResources().getColor(R.color.red));


                    //save state
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("switches_state_living_room", Context.MODE_PRIVATE).edit();
                    editor.putBoolean("switch4", false);
                    editor.apply();


                }
            }
        });


        //check buttons states
        saveSwitchState(v);
        return v;
    }














    //check buttons states
    public void saveSwitchState(View v) {

        //initialise sharedPreference state
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("switches_state_living_room", Context.MODE_PRIVATE);

        if (sharedPreferences.getBoolean("switch1",false)) {

            switch_btn1.setChecked(true);
//            switch_txt1.setText("ON");
            lampe_im.setColorFilter(ContextCompat.getColor(v.getContext(), R.color.green));
//            lampe_circle.setBackground(ContextCompat.getDrawable(v.getContext(), R.drawable.device_circle_vert));
            switch_txt1.setTextColor(getResources().getColor(R.color.green));

        } else {

            switch_btn1.setChecked(false);
//            switch_txt1.setText("OFF");
            lampe_im.setColorFilter(ContextCompat.getColor(v.getContext(), R.color.red));
//            lampe_circle.setBackground(ContextCompat.getDrawable(v.getContext(), R.drawable.device_circle));
            switch_txt1.setTextColor(getResources().getColor(R.color.red));
        }


        if (sharedPreferences.getBoolean("switch2", false)) {

            switch_btn2.setChecked(true);
//            switch_txt2.setText("ON");
            smarttv_im.setColorFilter(ContextCompat.getColor(v.getContext(), R.color.green));
//            tv_circle.setBackground(ContextCompat.getDrawable(v.getContext(), R.drawable.device_circle_vert));
            switch_txt2.setTextColor(getResources().getColor(R.color.green));

        } else {

            switch_btn2.setChecked(false);
//            switch_txt2.setText("OFF");
            smarttv_im.setColorFilter(ContextCompat.getColor(v.getContext(), R.color.red));
//            tv_circle.setBackground(ContextCompat.getDrawable(v.getContext(), R.drawable.device_circle));
            switch_txt2.setTextColor(getResources().getColor(R.color.red));

        }


        if (sharedPreferences.getBoolean("switch3", false)) {

            switch_btn3.setChecked(true);
//            switch_txt3.setText("ON");
            aircond_im.setColorFilter(ContextCompat.getColor(v.getContext(), R.color.green));
//            aircond_circle.setBackground(ContextCompat.getDrawable(v.getContext(), R.drawable.device_circle_vert));
            switch_txt3.setTextColor(getResources().getColor(R.color.green));

        } else {

            switch_btn3.setChecked(false);
//            switch_txt3.setText("OFF");
            aircond_im.setColorFilter(ContextCompat.getColor(v.getContext(), R.color.red));
//            aircond_circle.setBackground(ContextCompat.getDrawable(v.getContext(), R.drawable.device_circle));
            switch_txt3.setTextColor(getResources().getColor(R.color.red));

        }


        if (sharedPreferences.getBoolean("switch4", false)) {

            switch_btn4.setChecked(true);
//            switch_txt4.setText("ON");
            speaker_im.setColorFilter(ContextCompat.getColor(v.getContext(), R.color.green));
//            speaker_circle.setBackground(ContextCompat.getDrawable(v.getContext(), R.drawable.device_circle_vert));
            switch_txt4.setTextColor(getResources().getColor(R.color.green));

        } else {

            switch_btn4.setChecked(false);
//            switch_txt4.setText("OFF");
            speaker_im.setColorFilter(ContextCompat.getColor(v.getContext(), R.color.red));
//            speaker_circle.setBackground(ContextCompat.getDrawable(v.getContext(), R.drawable.device_circle));
            switch_txt4.setTextColor(getResources().getColor(R.color.red));
        }
    }



}