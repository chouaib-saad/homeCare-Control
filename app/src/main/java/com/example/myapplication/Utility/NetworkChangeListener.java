package com.example.myapplication.Utility;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.myapplication.R;

public class NetworkChangeListener extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        if (!Common.isConnectedToInternet(context)) {           //internet is not connected


            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            View layout_dialog = LayoutInflater.from(context).inflate(R.layout.check_internet_dialog, null);
            builder.setView(layout_dialog);


            ConstraintLayout btnRetry = layout_dialog.findViewById(R.id.retry_btn);
            ProgressBar progressBar = layout_dialog.findViewById(R.id.progressbar);
            ImageView retry_icon = layout_dialog.findViewById(R.id.retry_icon);

//            show dialog
            AlertDialog dialog = builder.create();
            dialog.show();
            dialog.setCancelable(false);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setGravity(Gravity.CENTER);


            //btn retry
            btnRetry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    retry_icon.setVisibility(View.GONE);
                    progressBar.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            retry_icon.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                            dialog.dismiss();
                            onReceive(context, intent);
                        }},600);
                }
            });


        }

    }
}
