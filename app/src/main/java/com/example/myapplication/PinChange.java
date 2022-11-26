package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class PinChange extends AppCompatActivity {

    Button valideButt;
    TextInputLayout new_pin_field;


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pin_change);

        //hook views
        valideButt = findViewById(R.id.valideButt);
        new_pin_field = findViewById(R.id.new_pin_field);


        valideButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validate_pin()) {
                    return;
                } else {
                    changePinConfirmation();
//                    EditTest();
                }
            }
        });


    }


    //validate new_pin_field
    private Boolean validate_pin() {
        String pin = new_pin_field.getEditText().getText().toString().trim();
        if (pin.isEmpty()) {
            new_pin_field.setError("ecrire le nouveau code pin");
            new_pin_field.setErrorEnabled(true);
            return false;
        } else if (pin.length() != 4) {
            new_pin_field.setError("le longeur de pin est exactement 4");
            new_pin_field.setErrorEnabled(true);
            return false;
        } else {
            new_pin_field.setError(null);
            new_pin_field.setErrorEnabled(false);
            return true;
        }
    }


    public void return_back(View view) {
        finish();
    }


    private SharedPreferences.Editor editPassCode(String passCode) {
        SharedPreferences preferences = getSharedPreferences("passcode_pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("passcode", passCode);
        editor.commit();

        return editor;
    }


    //     fct de message si la cnx ne pas etablir :
    private void changePinConfirmation() {

        //initialiser une dialogue de anvertissement
//        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(PinChange.this, R.style.AlertDialog);
        AlertDialog.Builder builder = new AlertDialog.Builder(PinChange.this, R.style.AlertDialogTheme);

        //defenir la titre de l'alert :
        builder.setCancelable(false);
        builder.setTitle("CODE PIN");
        builder.setMessage("soueter vous modifier votre code pin ?");
        //positionnez la bouton oui
        builder.setPositiveButton("oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String newEmail = new_pin_field.getEditText().getText().toString().trim();
                editPassCode(newEmail);
                Toast.makeText(PinChange.this, "code pin change avec succes", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(PinChange.this, PinActivity.class));
                finish();


            }
        });
        //bouton non
        builder.setNegativeButton("annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        //afficher le dialog
        builder.show();


    }


//        //fct afficher un message d'erreur si la cnx ne pas etablir :
//        private void EditTest(){
//            AlertDialog.Builder builder = new AlertDialog.Builder(PinChange.this,R.style.AlertDialog);
//            builder.setTitle("cv");
//            builder.setMessage("cvcv");
//            builder.setCancelable(false);
//
//            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    dialogInterface.dismiss();
//                }
//            });
//            builder.setNegativeButton("annuler", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    dialogInterface.dismiss();
//                }
//            });
//            AlertDialog alertDialog = builder.create();
//            alertDialog.show();
//        }


}


