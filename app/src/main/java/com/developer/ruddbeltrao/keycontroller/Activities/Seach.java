package com.developer.ruddbeltrao.keycontroller.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;

import com.developer.ruddbeltrao.keycontroller.R;

public class Seach extends ActionBarActivity {

    private ImageView xeckin, xecout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seach);

        xeckin = (ImageView)findViewById(R.id.checkin);
        xecout = (ImageView)findViewById(R.id.checkout);


        xeckin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Seach.this, realizaCheckin.class);
                startActivity(i);

            }
        });

        xecout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Seach.this, realizaCheckout.class);
                startActivity(i);




            }
        });



    }


}
