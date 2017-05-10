package com.developer.ruddbeltrao.keycontroller.Activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.developer.ruddbeltrao.keycontroller.Helpers.AlocaLabHelper;
import com.developer.ruddbeltrao.keycontroller.R;
import com.developer.ruddbeltrao.keycontroller.domain.Lab;
import com.developer.ruddbeltrao.keycontroller.mDataBase.KeyDataBase;

public class AlocaLab extends ActionBarActivity {

    private EditText numero;
    private EditText bloco;
    private EditText desc;
    private EditText pavimento;
    private Button btok;
    private Lab mLab;
    private AlocaLabHelper helper;
    private KeyDataBase data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aloca_lab);

        numero = (EditText) findViewById(R.id.numero);
        bloco = (EditText) findViewById(R.id.bloco);
        pavimento = (EditText) findViewById(R.id.pavimento);
        desc = (EditText) findViewById(R.id.desc);
        btok = (Button) findViewById(R.id.btok);

         helper = new AlocaLabHelper(numero, bloco, pavimento, desc);

        mLab = new Lab();

        //define o lab como nao usado

         data = new KeyDataBase(this);

        btok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mLab = helper.getLab();

                if(mLab!=null){


                    mLab.setOn(0);

                    if (data.insertLab(mLab)) {
                        helper.clear();
                        Toast.makeText(getApplicationContext(), "Laboratorio Cadastrado", Toast.LENGTH_LONG).show();
                        finish();

                    } else {
                        Toast.makeText(getApplicationContext(), "Ocorreu algum erro verefique os dados ", Toast.LENGTH_LONG).show();

                        //aqui informar uma mensagem de erro

                    }



                }


            }
        });


    }


}
