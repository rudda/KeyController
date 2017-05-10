package com.developer.ruddbeltrao.keycontroller.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.developer.ruddbeltrao.keycontroller.R;
import com.developer.ruddbeltrao.keycontroller.domain.Aluno;
import com.developer.ruddbeltrao.keycontroller.domain.Lab;
import com.developer.ruddbeltrao.keycontroller.mDataBase.KeyDataBase;

import java.util.List;

public class ContinueAluno extends ActionBarActivity {

    private Spinner s;
    private List<Lab> list;
    private KeyDataBase data;
    private Button finalizar;
    private Button voltar;
    private Button cancelar;
    private Intent mIntent;
    private Aluno mAluno;
    private EditText curso, senha, matricula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continue_aluno);

        curso = (EditText) findViewById(R.id.curso);
        senha = (EditText) findViewById(R.id.senha);
        matricula = (EditText) findViewById(R.id.matricula);

        s = (Spinner) findViewById(R.id.spinnerLab);
        finalizar = (Button) findViewById(R.id.btfinalizar);
        voltar = (Button) findViewById(R.id.back);

        mAluno = new Aluno();
        mIntent = getIntent();

        mAluno.setNome(mIntent.getStringExtra("nome"));
        mAluno.setCpf(mIntent.getStringExtra("cpf"));
        mAluno.setRg(mIntent.getStringExtra("rg"));
        mAluno.setEmail(mIntent.getStringExtra("email"));

        mAluno.setAnoCurso("0");


        data = new KeyDataBase(this);


        list = data.rawLab();


        ArrayAdapter<String> labsNum = new ArrayAdapter<String>(getApplicationContext(), R.layout.item_spinner_pers);


        for (Lab l : list) {

            labsNum.add(String.valueOf(l.getNumero()));

        }

        labsNum.setDropDownViewResource(R.layout.dropdow_item_spinner);
        s.setAdapter(labsNum);


        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (verefica()) {

                    mAluno.setCurso(curso.getText().toString());
                    mAluno.setSenha(senha.getText().toString());
                    mAluno.setMatricula(matricula.getText().toString());
                    mAluno.setLab_numero(Integer.parseInt(s.getSelectedItem().toString()));

                    if (data.insertAluno(mAluno)) {

                        finish();
                       Toast.makeText(getApplicationContext(), "Aluno Cadastrado", Toast.LENGTH_LONG).show();

                    }
                    else{

                        Toast.makeText(getApplicationContext(), "Erro verefique os dados cadastrais", Toast.LENGTH_LONG).show();

                    }


                }


            }
        });


        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });

    }

    private boolean verefica() {
        boolean b = true;

        if (matricula.getText().toString() == null || matricula.getText().toString().equals("")) {

            b = false;
        }

        if (curso.getText().toString() == null || curso.getText().toString().equals("")) {

            b = false;
        }
        if (senha.getText().toString() == null || senha.getText().toString().equals("")) {

            b = false;
        }

        return b;
    }


}
