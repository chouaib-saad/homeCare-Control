package com.example.myapplication.Utility;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

public class SecureSharedPref implements ISharedPreference{

    private  SharedPreferences sharedPreferences;


    public SecureSharedPref(Context context, String name) {


                try {
            MasterKey masterKey = new MasterKey.Builder(context)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build();


            sharedPreferences  = EncryptedSharedPreferences.create(
                    context,
                    name,
                    masterKey,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );



        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public String get(String name) {
        return sharedPreferences.getString(name,null);
    }

    @Override
    public void put(String name, String value) {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(name,value);
        editor.apply();

    }
}
