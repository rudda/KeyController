package com.developer.ruddbeltrao.keycontroller.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.developer.ruddbeltrao.keycontroller.R;

public class AdmActivity extends ActionBarActivity {

    private ImageView cadastrarUser;
    private ImageView cadastrados;
    private ImageView labs;
    private ImageView Alocalabs;
    private ImageView seach;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm);

        cadastrarUser = (ImageView) findViewById(R.id.cadastrarUser);
        cadastrados = (ImageView) findViewById(R.id.cadastrados);
        labs = (ImageView) findViewById(R.id.labs);
        Alocalabs = (ImageView) findViewById(R.id.alocaLabs);
        seach = (ImageView) findViewById(R.id.seach);


        cadastrarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(AdmActivity.this, ChooseUser.class);
                startActivity(it);

            }
        });


        cadastrados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(AdmActivity.this, AllUser.class);
                startActivity(it);

            }
        });


        Alocalabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(AdmActivity.this, AlocaLab.class);
                startActivity(it);

            }
        });

        labs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(AdmActivity.this, AllLab.class);
                startActivity(it);

            }
        });


        seach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(AdmActivity.this, Seach.class);
                startActivity(it);

            }
        });
    }


}
