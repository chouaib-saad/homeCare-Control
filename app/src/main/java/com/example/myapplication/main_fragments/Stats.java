package com.example.myapplication.main_fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.ViewPagerAdapter;
import com.example.myapplication.control_fragments.Bedroom_Frag;
import com.example.myapplication.control_fragments.Living_Room_Frag;
import com.example.myapplication.control_fragments.Others_frag;
import com.example.myapplication.stats_fragments.Humidity;
import com.example.myapplication.stats_fragments.Mqtt;
import com.example.myapplication.stats_fragments.Temperature;
import com.google.android.material.tabs.TabLayout;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.ArrayList;
import java.util.List;

import mqtt.server.Connection;
import mqtt.server.Publish;
import mqtt.server.Subscription;

public class Stats extends Fragment {

    //  ****  here defined views *****



    //    progress Bar
    ProgressBar progressBar;



    TabLayout tabLayout;
    ViewPager viewPager;


                        //*** onCreate begin ***


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_stats, container, false);


        //progressBar hook
        progressBar = v.findViewById(R.id.progressbar);

        addFragment(v);






        return v;
    }












    private void addFragment(View v) {

        //Assign variables
        viewPager = v.findViewById(R.id.view_pager);


        //Initialise adapter
//        adapter = new MainAdapter(requireActivity().getSupportFragmentManager());
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        //add fraglent
        adapter.addFragment(new Temperature(), "Temperature");
        adapter.addFragment(new Humidity(), "Humidity");
        adapter.addFragment(new Mqtt(), "mqtt");


        //Set Adapter
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        //Connect tab layout  with view pager
    }


}