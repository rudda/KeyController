package com.developer.ruddbeltrao.keycontroller.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.developer.ruddbeltrao.keycontroller.R;

public class CadastrarAluno extends ActionBarActivity {

    private Button next, cancel;
    private EditText nome, cpf, rg, email;
    private Intent it;

    private int index; // 0 = aluno, 1= professor, 2= guarda
    private String mNome, mCpf, mRg, mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_aluno);

        next = (Button) findViewById(R.id.prox);
        cancel = (Button) findViewById(R.id.btcancelar);

        nome = (EditText) findViewById(R.id.etnome);
        cpf = (EditText) findViewById(R.id.etcpf);
        rg = (EditText) findViewById(R.id.etrg);
        email = (EditText) findViewById(R.id.etemail);




        it = new Intent(CadastrarAluno.this, ContinueAluno.class);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(verefica()){

                    it.putExtra("nome", nome.getText().toString());
                    it.putExtra("cpf", cpf.getText().toString());
                    it.putExtra("rg", rg.getText().toString());
                    it.putExtra("email", email.getText().toString());

                    startActivity(it);

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


        return b;
    }


}
