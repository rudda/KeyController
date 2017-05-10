package com.developer.ruddbeltrao.keycontroller.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.developer.ruddbeltrao.keycontroller.R;
import com.developer.ruddbeltrao.keycontroller.domain.Professor;
import com.developer.ruddbeltrao.keycontroller.domain.Usuario;

import de.greenrobot.event.EventBus;

public class CadProfessor extends ActionBarActivity {

    private EditText cadNome, cadcpf, cadRg, cademail;
    private Button cadprox, cadcancelar;
    private Usuario prof;
    private EditText pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_professor);

        prof = new Professor();
        cadcpf = (EditText) findViewById(R.id.cadcpf);
        cadNome = (EditText) findViewById(R.id.cadnome);
        cadRg = (EditText) findViewById(R.id.cadRg);
        cademail = (EditText)findViewById(R.id.cademail);
        cadprox  =  (Button)findViewById(R.id.cadprox);
        cadcancelar  =  (Button)findViewById(R.id.cadcancelar);
        pass = (EditText)findViewById(R.id.cad_senha_prof);

        cadcancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        cadprox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             if(verefica()){

                 Intent it = new Intent(CadProfessor.this, CadastraProfessor.class);


                 it.putExtra("nome", cadNome.getText().toString());
                 it.putExtra("cpf", cadcpf.getText().toString());
                 it.putExtra("rg", cadRg.getText().toString());
                 it.putExtra("email", cademail.getText().toString());
                 it.putExtra("pass", pass.getText().toString()+"");




                 //CLEAR EDITTEXTS

                 cadNome.setText("");
                 cadcpf.setText("");
                 cadRg.setText("");
                 cademail.setText("");

                 //CALL TO PROX ACTIVITY AND FINISH
                 startActivity(it);
                 finish();

             }


            }
        });


    }



    private boolean verefica() {


        boolean b= true;

        if((cadNome.getText().toString().length() <=0 || cadNome == null || cadNome.getText().toString().equals(""))){


            cadNome.setText("");
            cadNome.setHint("Campo Obrigatorio");
            b = false;

        }

        if( cadcpf.getText().toString().length() <=0 || cadcpf == null || cadcpf.getText().toString().equals("")){


            cadcpf.setText("");
            cadcpf.setHint("campo obrigatorio");
            b = false;

        }



        if  (cadRg.getText().toString().length() <=0 || cadRg == null || cadRg.getText().toString().equals(""))

        {

            cadRg.setText("");
            cadRg.setHint("campo obrigatorio");

            b = false;

        }


        return b;
    }

}
