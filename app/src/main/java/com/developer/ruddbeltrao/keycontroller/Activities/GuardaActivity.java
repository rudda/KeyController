package com.developer.ruddbeltrao.keycontroller.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.developer.ruddbeltrao.keycontroller.R;
import com.developer.ruddbeltrao.keycontroller.domain.Guarda;

import de.greenrobot.event.EventBus;

public class GuardaActivity extends ActionBarActivity {

    private ImageView emprestar, devolver, visualizar;
    private Guarda guarda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guarda);

        //EVENT BUS - REGISTER
        EventBus.getDefault().registerSticky(this);


        emprestar = (ImageView)findViewById(R.id.imageView);
        devolver = (ImageView)findViewById(R.id.image);
        visualizar = (ImageView)findViewById(R.id.imageView3);


        emprestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)

            {

                Intent it =  new Intent(GuardaActivity.this, ActivityCheckin.class);
                EventBus.getDefault().postSticky(guarda);
                Log.i("database", guarda.getCpf());
                startActivity(it);



            }
        });


        devolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent it =  new Intent(GuardaActivity.this, ActivityCheckout.class);
                EventBus.getDefault().postSticky(guarda);
                Log.i("database", guarda.getCpf());

                startActivity(it);


            }
        });


        visualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            Intent it =  new Intent(GuardaActivity.this, ChavesEmprestadas.class);
                startActivity(it);


            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }

    public void onEvent(Guarda guarda){

        this.guarda = guarda;
    }





}
