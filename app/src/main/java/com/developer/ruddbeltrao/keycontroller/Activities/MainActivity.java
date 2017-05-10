package com.developer.ruddbeltrao.keycontroller.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.developer.ruddbeltrao.keycontroller.R;
import com.developer.ruddbeltrao.keycontroller.domain.Adm;
import com.developer.ruddbeltrao.keycontroller.domain.Aluno;
import com.developer.ruddbeltrao.keycontroller.domain.Lab;
import com.developer.ruddbeltrao.keycontroller.domain.Professor;
import com.developer.ruddbeltrao.keycontroller.domain.Servidor;
import com.developer.ruddbeltrao.keycontroller.domain.Usuario;
import com.developer.ruddbeltrao.keycontroller.mDataBase.KeyDataBase;
import com.developer.ruddbeltrao.keycontroller.mDataBase.mScripts;


public class MainActivity extends ActionBarActivity {


    private ImageView ivAdm;
    private ImageView ivGuarda;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ivAdm = (ImageView) findViewById(R.id.imv_adm);
        ivGuarda = (ImageView) findViewById(R.id.imv_guarda);


        ivAdm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, LoginAdm.class);

                startActivity(it);


            }
        });


        ivGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, LoginGuarda.class);

                startActivity(it);


            }
        });



       KeyDataBase mDatabase = new KeyDataBase(this);




        Adm ad = new Adm();
        ad.setCpf("123");
        ad.setNome("adm");
        ad.setSiape("12345");
        mDatabase.insertAdm(ad);

        mDatabase.dataNow();
        mDatabase.testCk("2015-12-18", true, "312");

    }


}
