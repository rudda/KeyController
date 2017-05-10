package com.developer.ruddbeltrao.keycontroller.Activities;

import android.content.Intent;
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
import com.developer.ruddbeltrao.keycontroller.Utility.TheadTimeToFinish;
import com.developer.ruddbeltrao.keycontroller.domain.Lab;
import com.developer.ruddbeltrao.keycontroller.mDataBase.KeyDataBase;

public class UpdateLab extends ActionBarActivity {
    private EditText numero;
    private EditText bloco;
    private EditText desc;
    private EditText pavimento;
    private Button btok;
    private Lab mLab;
    private AlocaLabHelper helper;
    private KeyDataBase data;
    private Intent getIt;
    private String IDLAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_lab);

        getIt = getIntent();

        mLab = new Lab();
        mLab.setNumero(getIt.getIntExtra("numero", 0));
        IDLAB = String.valueOf(getIt.getIntExtra("numero", 0));

        mLab.setBloco(getIt.getStringExtra("bloco"));
        mLab.setPavimento(getIt.getStringExtra("pavimento"));
        mLab.setDescricao(getIt.getStringExtra("descricao"));


        numero = (EditText) findViewById(R.id.upnumero);
        bloco = (EditText) findViewById(R.id.upbloco);
        pavimento = (EditText) findViewById(R.id.uppavimento);
        desc = (EditText) findViewById(R.id.updesc);
        btok = (Button) findViewById(R.id.upbtok);


        numero.setText(String.valueOf(mLab.getNumero()));
        bloco.setText((mLab.getBloco()));
        pavimento.setText(mLab.getPavimento());
        desc.setText(mLab.getDescricao());

        helper = new AlocaLabHelper(numero, bloco, pavimento, desc);


        data = new KeyDataBase(this);


        btok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mLab = helper.getLab();
                Toast.makeText(getApplicationContext(), "Lab "+mLab.getNumero()+ " desc"+mLab.getDescricao(), Toast.LENGTH_LONG).show();

                if(mLab!=null){


                    mLab.setOn(0);

                    if (data.updateLab(mLab, IDLAB)) {
                        helper.clear();
                        Toast.makeText(getApplicationContext(), "Laboratorio Atualizado", Toast.LENGTH_LONG).show();
                        TheadTimeToFinish threadTimeToFinish = new TheadTimeToFinish( 1000, 500, UpdateLab.this);
                        threadTimeToFinish.start();

                    } else {
                        Toast.makeText(getApplicationContext(), "Ocorreu algum erro verefique os dados ", Toast.LENGTH_LONG).show();



                    }



                }


            }
        });



    }


}
