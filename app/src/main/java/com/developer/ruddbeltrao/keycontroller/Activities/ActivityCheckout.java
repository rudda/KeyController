package com.developer.ruddbeltrao.keycontroller.Activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.developer.ruddbeltrao.keycontroller.R;
import com.developer.ruddbeltrao.keycontroller.Utility.TheadTimeToFinish;
import com.developer.ruddbeltrao.keycontroller.domain.Guarda;
import com.developer.ruddbeltrao.keycontroller.domain.Lab;
import com.developer.ruddbeltrao.keycontroller.mDataBase.KeyDataBase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.greenrobot.event.EventBus;

public class ActivityCheckout extends ActionBarActivity implements AdapterView.OnItemSelectedListener {

    private Spinner labs;
    private EditText senha, mcpf;
    private String labNum;
    private List<Lab> list;
    private TheadTimeToFinish mTheadTimeToFinish;
    private String  data_completa;
    private KeyDataBase data;
    private Guarda guarda;
    private Button cancel, ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_checkout);
        //EVENT BUS - REGISTER
        EventBus.getDefault().registerSticky(this);

        labs = (Spinner) findViewById(R.id.labListCheckout);
        mcpf = (EditText) findViewById(R.id.etMcpf2);
        senha = (EditText) findViewById(R.id.passUser2);
        data = new KeyDataBase(this);
        cancel = (Button) findViewById(R.id.cancelarSearch2);
        ok = (Button) findViewById(R.id.letsgo2);

        //SPINNER - PREENCHER COM OS DADOS DOS LABS QUE FIZERAM CHEKIN
        list = data.labsOn("1");

        ArrayAdapter<String> labsNum = new ArrayAdapter<String>(getApplicationContext(), R.layout.item_spinner_pers);

        for (Lab l : list) {

            labsNum.add(String.valueOf(l.getNumero()));

        }

        labsNum.setDropDownViewResource(R.layout.dropdow_item_spinner);
        labs.setAdapter(labsNum);
        labs.setOnItemSelectedListener(this);
        // SPINNER - FIM



        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //OPERAÇÕES PARA A AÇÃO DO BOTÃO OKAY
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //VEREFICA SE EXISTE UM USUARIO / SENHA E SE ESTE USUARIO PERTENCE AO LAB INFORMADO.
                if ((data.logar("" + mcpf.getText().toString(), "" + senha.getText().toString())) &&

                        (data.findUserToLab("" + mcpf.getText().toString(), labs.getSelectedItem().toString())) ||

                        (data.findlabtoaluno("" + mcpf.getText().toString(), labs.getSelectedItem().toString()))

                        ) {

                    /*SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

                    Date datas;
                    datas = new Date();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(datas);
                    Date data_atual = cal.getTime();

                    data_completa = dateFormat.format(data_atual);*/

                    //REALIZA CHECKOUT
                    if (data.Check("" + mcpf.getText().toString(), labNum, guarda.getCpf(), false)) {

                        //SE O PERSISTIR O CHECKOUT ENTÃO CAPTURA AS INFORMAÇÕES DO LAB QUE FOI FEITO O REGISTRO
                        Lab l = data.findLab(labNum);

                        //SE RETORNAR UM LAB ENTÃO MUDA SEU ESTADO ON [ 0 = {LIVRE} 1 = {EMPRESTADO}]
                        if (l != null) {
                            l.setOn(0);

                            //ATUALIZA O ESTADO DO LAB
                            data.updateLab(l, String.valueOf(l.getNumero()));


                            Toast.makeText(getApplicationContext(), "Voce Devolveu A chave com Sucesso!!!", Toast.LENGTH_LONG).show();

                            //FECHA A ACTIVITY
                            mTheadTimeToFinish = new TheadTimeToFinish(1000, 500, ActivityCheckout.this);
                            mTheadTimeToFinish.start();

                        }

                    }


                }

            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), "Click " + labs.getSelectedItem().toString(), Toast.LENGTH_LONG).show();


        labNum = labs.getSelectedItem().toString();
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



    public void onEvent(Guarda guarda){

        this.guarda = guarda;

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mTheadTimeToFinish!=null){

            mTheadTimeToFinish.cancel();
        }

        EventBus.getDefault().unregister(this);

    }
}
