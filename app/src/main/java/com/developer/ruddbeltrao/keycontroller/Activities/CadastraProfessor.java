package com.developer.ruddbeltrao.keycontroller.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.developer.ruddbeltrao.keycontroller.R;
import com.developer.ruddbeltrao.keycontroller.Utility.TheadTimeToFinish;
import com.developer.ruddbeltrao.keycontroller.domain.Lab;
import com.developer.ruddbeltrao.keycontroller.domain.Professor;
import com.developer.ruddbeltrao.keycontroller.domain.Servidor;
import com.developer.ruddbeltrao.keycontroller.domain.Usuario;
import com.developer.ruddbeltrao.keycontroller.mDataBase.KeyDataBase;

import java.util.List;

import de.greenrobot.event.EventBus;

public class CadastraProfessor extends ActionBarActivity {

    private LinearLayout lay_servidor;
    private Intent it;
    private Button finishButton, cancel;
    private RadioButton chProfessor, chServidor;
    private EditText siape, funcao, pasep;
    private Spinner mSpinner;
    private List<Lab> list;
    private KeyDataBase data;
    private TheadTimeToFinish threadTimeToFinish;
    private String nome, cpf, rg, email, pass;
    private Usuario user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_professor);


        it = getIntent();
        nome = it.getStringExtra("nome");
        cpf = it.getStringExtra("cpf");
        rg = it.getStringExtra("rg");
        email = it.getStringExtra("email");
        pass = it.getStringExtra("pass");

        lay_servidor = (LinearLayout) findViewById(R.id.lay_servidor);
        lay_servidor.setVisibility(View.INVISIBLE);

        chProfessor = (RadioButton) findViewById(R.id.radio_professor);
        chServidor = (RadioButton) findViewById(R.id.radio_servidor);

        siape = (EditText) findViewById(R.id.professorSiape);
        pasep = (EditText) findViewById(R.id.professorPasep);
        funcao = (EditText) findViewById(R.id.serv_funcao);
        mSpinner = (Spinner) findViewById(R.id.spinnerLabCadastro);
        finishButton = (Button) findViewById(R.id.btfinalizarCadastro);
        cancel = (Button) findViewById(R.id.cancelCadastro);

        threadTimeToFinish = new TheadTimeToFinish(1000, 5000, CadastraProfessor.this);
        data = new KeyDataBase(this);

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (chProfessor.isChecked() && verefica()) {

                    Professor prof = new Professor();

                    prof.setNome(nome);
                    prof.setCpf(cpf);
                    prof.setRg(rg);
                    prof.setEmail(email);
                    prof.setSenha(pass);
                    prof.setSiape(siape.getText().toString());
                    prof.setPasep(pasep.getText().toString());

                    if (data.insertProfessor(prof)) {

                        if (data.insertUsuarioPertenceLab(prof.getCpf(), Integer.parseInt(mSpinner.getSelectedItem().toString()))) {

                            Toast.makeText(getApplicationContext(), "Professor Cadastrado com sucesso", Toast.LENGTH_LONG).show();

                            threadTimeToFinish.start();

                        }


                    }

                } else if (chServidor.isChecked() && verefica()) {
                    Servidor servidor = new Servidor();


                    servidor.setNome(nome);
                    servidor.setCpf(cpf);
                    servidor.setRg(rg);
                    servidor.setEmail(email);
                    servidor.setSenha(pass);
                    servidor.setSiape(siape.getText().toString());
                    servidor.setPasep(pasep.getText().toString());
                    servidor.setFuncao(funcao.getText().toString());

                    if (data.insertServidor(servidor)) {

                        if (data.insertUsuarioPertenceLab(servidor.getCpf(), Integer.parseInt(mSpinner.getSelectedItem().toString()))) {

                            Toast.makeText(getApplicationContext(), "Servidor Cadastrado com sucesso", Toast.LENGTH_LONG).show();

                            threadTimeToFinish.start();


                        }


                    }


                } else {


                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        initSpinner();


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(threadTimeToFinish!=null)
        threadTimeToFinish.cancel();

    }

    private void initSpinner() {


        list = data.rawLab();


        ArrayAdapter<String> labsNum = new ArrayAdapter<String>(getApplicationContext(), R.layout.item_spinner_pers);


        for (Lab l : list) {

            labsNum.add(String.valueOf(l.getNumero()));

        }

        labsNum.setDropDownViewResource(R.layout.dropdow_item_spinner);
        mSpinner.setAdapter(labsNum);


    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();


        switch (view.getId()) {
            case R.id.radio_professor:
                if (checked) {
                    lay_servidor.setVisibility(View.INVISIBLE);

                }
                break;
            case R.id.radio_servidor:
                if (checked) {

                    lay_servidor.setVisibility(View.VISIBLE);

                }
                break;
        }
    }

    private boolean verefica() {
        boolean b = true;

        if (siape.getText().toString() == null || siape.getText().toString().equals("")) {

            b = false;
        }

        if (pasep.getText().toString() == null || pasep.getText().toString().equals("")) {

            b = false;
        }


        return b;
    }



}
