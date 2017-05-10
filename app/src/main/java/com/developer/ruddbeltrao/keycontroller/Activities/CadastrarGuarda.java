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

import com.developer.ruddbeltrao.keycontroller.R;
import com.developer.ruddbeltrao.keycontroller.Utility.TheadTimeToFinish;
import com.developer.ruddbeltrao.keycontroller.domain.Guarda;
import com.developer.ruddbeltrao.keycontroller.mDataBase.KeyDataBase;

public class CadastrarGuarda extends ActionBarActivity {


    private EditText nome, cpf, rg, senha, pasep;
    private Button cancel, ok;
    private KeyDataBase mData;
    private   TheadTimeToFinish mFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_guarda);

        nome = (EditText)findViewById(R.id.gnome);
        cpf = (EditText)findViewById(R.id.gcpf);
        rg = (EditText)findViewById(R.id.grg);
        senha = (EditText)findViewById(R.id.gsenha);
        pasep = (EditText)findViewById(R.id.gpasep);
        cancel =  (Button)findViewById(R.id.gcancelar);
        ok = (Button)findViewById(R.id.gprox);

        mData = new KeyDataBase(this);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(verefica()){

                    Guarda mGuarda = new Guarda();

                    mGuarda.setFoto("");
                    mGuarda.setNome(nome.getText().toString());
                    mGuarda.setCpf(cpf.getText().toString());
                    mGuarda.setRg(rg.getText().toString());
                    mGuarda.setSenha(senha.getText().toString());
                    mGuarda.setPasep(pasep.getText().toString());


                    if(mData.insertGuada(mGuarda)){

                        nome.setText("");
                        rg.setText("");
                        pasep.setText("");
                        senha.setText("");
                        cpf.setText("");

                        Toast.makeText(getApplicationContext(), "Guarda Cadastrado com Sucesso", Toast.LENGTH_LONG).show();

                        mFinish = new TheadTimeToFinish(1000, 500, CadastrarGuarda.this);
                        mFinish.start();


                    }

                    else{

                        Toast.makeText(getApplicationContext(), "Verefique os dados Cadastrais", Toast.LENGTH_LONG).show();


                    }


                }



            }
        });


    }



    private boolean verefica() {


        boolean b= true;

        if((nome.getText().toString().length() <=0 || nome == null || nome.getText().toString().equals(""))){


            nome.setText("");
            nome.setHint("Campo Obrigatorio");
            b = false;

        }

        if( cpf.getText().toString().length() <=0 || cpf == null || cpf.getText().toString().equals("")){


            cpf.setText("");
            cpf.setHint("campo obrigatorio");
            b = false;

        }



        if  (rg.getText().toString().length() <=0 || rg == null || rg.getText().toString().equals(""))

        {

            rg.setText("");
            rg.setHint("campo obrigatorio");

            b = false;

        }

        if  (pasep.getText().toString().length() <=0 || pasep == null || pasep.getText().toString().equals(""))

        {

            rg.setText("");
            rg.setHint("campo obrigatorio");

            b = false;

        }

        return b;
    }





    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(mData!= null){

            mData.close();
        }

        if(mFinish!=null){

            mFinish.cancel();
        }
    }
}
