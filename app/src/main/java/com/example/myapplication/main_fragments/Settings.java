package com.example.myapplication.main_fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Settings extends Fragment {


//  ****  here defined views ****
    DatabaseReference mydb;
    TextView fromEsp32data,fireState;

    //    progress Bar
    ProgressBar progressBar;

                        //*** onCreate begin ***


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_settings, container, false);



        //progressBar hook
        progressBar = v.findViewById(R.id.ProgressBar);


        fromEsp32data=v.findViewById(R.id.fromEsp32data);
        fireState = v.findViewById(R.id.fireState);

        mydb= FirebaseDatabase.getInstance().getReference().child("Sensor");
        try {

            mydb.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    String dataFromDB = dataSnapshot.child("motion").getValue().toString();
                    fromEsp32data.setText(dataFromDB);

                    Integer fireNu = Integer.parseInt(dataSnapshot.child("fireRate").getValue().toString());
                    progressBar.setProgress(fireNu);

                    String fireS = dataSnapshot.child("fireState").getValue().toString();
                    fireState.setText("Fire State: "+fireS);

                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value

                }
            });
        } catch(Exception e)
        {


        }


        return  v;
    }
}