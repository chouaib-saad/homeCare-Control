package com.example.myapplication.stats_fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.main_fragments.Stats;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import mqtt.server.BaseFragment;
import mqtt.server.Connection;
import mqtt.server.Publish;
import mqtt.server.Subscription;


public class Mqtt extends BaseFragment implements MqttCallback   {



    private MqttAndroidClient mClient;
    Button publish_btn;
    EditText my_edit_texte;


    TextView switch_txt1,switch_txt2 ;
    SwitchCompat switch_btn1,switch_btn2 ;
    //switch images
    ImageView lampe_im,lampe_im2;






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_mqtt, container, false);




        publish_btn = v.findViewById(R.id.publish_btn);
        my_edit_texte = v.findViewById(R.id.my_edit_texte);


        //switch components hooks
        switch_btn1 = v.findViewById(R.id.switch_btn1);
        switch_txt1 = v.findViewById(R.id.switch_txt1);
        switch_btn2 = v.findViewById(R.id.switch_btn2);
        switch_txt2 = v.findViewById(R.id.switch_txt2);
        //switch image hook
        lampe_im = v.findViewById(R.id.lampe_im);
        lampe_im2 = v.findViewById(R.id.lampe_im);




        switch_btn1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                v.findViewById(R.id.led).startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.image_anim)); // starts animation


                if(switch_btn1.isChecked()){switch_txt1.setText("ON");
                    lampe_im.setColorFilter(ContextCompat.getColor(v.getContext(),R.color.green));
//                    lampe_circle.setBackground(ContextCompat.getDrawable(v.getContext(),R.drawable.device_circle_vert));
                    switch_txt1.setTextColor(getResources().getColor(R.color.green));


                    final Publish publish = new Publish("HomeLed/LED1","1", 0, true);
                    publish(publish, new IMqttActionListener() {
                        @Override
                        public void onSuccess(IMqttToken asyncActionToken) {
//                        mPublishList.add(0, publish);
//                        mAdapter.notifyItemInserted(0);
                        }

                        @Override
                        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                            Toast.makeText(fragmentActivity, "Failed to publish", Toast.LENGTH_SHORT).show();

                        }
                    });


                    //save state
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("led_state", Context.MODE_PRIVATE).edit();
                    editor.putBoolean("LED1", true);
                    editor.apply();

                }
                else{switch_txt1.setText("OFF");
                    lampe_im.setColorFilter(ContextCompat.getColor(v.getContext(),R.color.red));
//                    lampe_circle.setBackground(ContextCompat.getDrawable(v.getContext(),R.drawable.device_circle));
                    switch_txt1.setTextColor(getResources().getColor(R.color.red));


                    final Publish publish = new Publish("HomeLed/LED1","0", 0, true);
                    publish(publish, new IMqttActionListener() {
                        @Override
                        public void onSuccess(IMqttToken asyncActionToken) {
//                        mPublishList.add(0, publish);
//                        mAdapter.notifyItemInserted(0);
                        }

                        @Override
                        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                            Toast.makeText(fragmentActivity, "Failed to publish", Toast.LENGTH_SHORT).show();

                        }
                    });


                    //save state
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("led_state", Context.MODE_PRIVATE).edit();
                    editor.putBoolean("LED1", false);
                    editor.apply();

                }
            }});





        switch_btn2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                v.findViewById(R.id.led2).startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.image_anim)); // starts animation


                if(switch_btn2.isChecked()){switch_txt2.setText("ON");
                    lampe_im2.setColorFilter(ContextCompat.getColor(v.getContext(),R.color.green));
//                    lampe_circle.setBackground(ContextCompat.getDrawable(v.getContext(),R.drawable.device_circle_vert));
                    switch_txt2.setTextColor(getResources().getColor(R.color.green));


                    final Publish publish = new Publish("HomeLed/LED2","1", 0, true);
                    publish(publish, new IMqttActionListener() {
                        @Override
                        public void onSuccess(IMqttToken asyncActionToken) {
//                        mPublishList.add(0, publish);
//                        mAdapter.notifyItemInserted(0);
                        }

                        @Override
                        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                            Toast.makeText(fragmentActivity, "Failed to publish", Toast.LENGTH_SHORT).show();

                        }
                    });


                    //save state
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("led_state", Context.MODE_PRIVATE).edit();
                    editor.putBoolean("LED2", true);
                    editor.apply();

                }
                else{switch_txt1.setText("OFF");
                    lampe_im.setColorFilter(ContextCompat.getColor(v.getContext(),R.color.red));
//                    lampe_circle.setBackground(ContextCompat.getDrawable(v.getContext(),R.drawable.device_circle));
                    switch_txt1.setTextColor(getResources().getColor(R.color.red));


                    final Publish publish = new Publish("HomeLed/LED2","0", 0, true);
                    publish(publish, new IMqttActionListener() {
                        @Override
                        public void onSuccess(IMqttToken asyncActionToken) {
//                        mPublishList.add(0, publish);
//                        mAdapter.notifyItemInserted(0);
                        }

                        @Override
                        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                            Toast.makeText(fragmentActivity, "Failed to publish", Toast.LENGTH_SHORT).show();

                        }
                    });


                    //save state
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("led_state", Context.MODE_PRIVATE).edit();
                    editor.putBoolean("LED2", false);
                    editor.apply();

                }
            }});



        publish_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Publish publish = new Publish("chouaib/msg", my_edit_texte.getText().toString(), 0, true);
                publish(publish, new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
//                        mPublishList.add(0, publish);
//                        mAdapter.notifyItemInserted(0);
                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                        Toast.makeText(fragmentActivity, "Failed to publish", Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });


        Connection connection = new Connection(getContext(),"91.121.93.94", Integer.parseInt("1883"),"13141516","","",false);
        connect(connection, new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
//                Log.d("ConnectionFragment", "Connected to: " + asyncActionToken.getClient().getServerURI());
                Toast.makeText(fragmentActivity,  "Connected to: " + asyncActionToken.getClient().getServerURI().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Toast.makeText(getContext(), exception.toString(), Toast.LENGTH_SHORT).show();
                exception.printStackTrace();
            }
        });



        //check buttons states
        saveSwitchState(v);

        return v;


    }   //Oncreate View ENd

    @Override
    protected int getLayoutResId() {
        return 0;
    }

    @Override
    protected void setUpView(View view) {

    }


    public void connect(Connection connection, IMqttActionListener listener) {
        mClient = connection.getMqttAndroidClient(getContext());
        try {
            mClient.connect(connection.getMqttConnectOptions(), null, listener);
            mClient.setCallback(this);
        } catch (MqttException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Failed to connect", Toast.LENGTH_SHORT).show();
        }

    }

    public void disconnect() {
        if (notConnected(true)) {
            return;
        }
        try {
            mClient.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void subscribe(Subscription subscription, IMqttActionListener listener) {
        if (notConnected(true)) {
            return;
        }
        try {
            mClient.subscribe(subscription.getTopic(), subscription.getQos(), null, listener);
        } catch (MqttException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Failed to subscribe", Toast.LENGTH_SHORT).show();
        }
    }

    public void publish(Publish publish, IMqttActionListener callback) {
        if (notConnected(true)) {
            return;
        }
        try {
            mClient.publish(publish.getTopic(), publish.getPayload().getBytes(), publish.getQos(), publish.isRetained(), null, callback);
        } catch (MqttException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Failed to publish", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean notConnected(boolean showNotify) {
        if (mClient == null || !mClient.isConnected()) {
            if (showNotify) {
                Toast.makeText(getContext(), "Client is not connected", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        return false;
    }

    @Override
    public void connectionLost(Throwable cause) {
//        ((ConnectionFragment) mFragmentList.get(0)).updateButtonText();
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
//        ((MessageFragment) mFragmentList.get(3)).updateMessage(new Message(topic, message));

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }


//    publish("chouaib/msg", "hello world".getBytes(), 0, true, null, callback)




    //check buttons states
    public void saveSwitchState(View v) {

        //initialise sharedPreference state
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("led_state", Context.MODE_PRIVATE);

        if (sharedPreferences.getBoolean("LED1", false)) {

            switch_btn1.setChecked(true);
            lampe_im.setColorFilter(ContextCompat.getColor(v.getContext(), R.color.green));
//            lampe_circle.setBackground(ContextCompat.getDrawable(v.getContext(),R.drawable.device_circle_vert));
            switch_txt1.setTextColor(getResources().getColor(R.color.green));


        } else {

            switch_btn1.setChecked(false);
            lampe_im.setColorFilter(ContextCompat.getColor(v.getContext(), R.color.red));
//            lampe_circle.setBackground(ContextCompat.getDrawable(v.getContext(),R.drawable.device_circle));
            switch_txt1.setTextColor(getResources().getColor(R.color.red));

        }

        if (sharedPreferences.getBoolean("LED2", false)) {

            switch_btn2.setChecked(true);
            lampe_im2.setColorFilter(ContextCompat.getColor(v.getContext(), R.color.green));
//            lampe_circle.setBackground(ContextCompat.getDrawable(v.getContext(),R.drawable.device_circle_vert));
            switch_txt2.setTextColor(getResources().getColor(R.color.green));


        } else {

            switch_btn2.setChecked(false);
            lampe_im2.setColorFilter(ContextCompat.getColor(v.getContext(), R.color.red));
//            lampe_circle.setBackground(ContextCompat.getDrawable(v.getContext(),R.drawable.device_circle));
            switch_txt2.setTextColor(getResources().getColor(R.color.red));

        }

    }


}
