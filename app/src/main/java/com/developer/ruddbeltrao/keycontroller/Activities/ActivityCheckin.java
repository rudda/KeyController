package com.developer.ruddbeltrao.keycontroller.Activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
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

public class ActivityCheckin extends ActionBarActivity implements AdapterView.OnItemSelectedListener {

    private Spinner labs;
    private EditText senha, mcpf;
    private String labNum;
    private List<Lab> list;
    private TheadTimeToFinish mTheadTimeToFinish;
    private KeyDataBase data;
    private String  data_completa;
    private Button cancel, ok;
    private Guarda guarda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_checkin);

        labs = (Spinner) findViewById(R.id.labListCheckin);
        mcpf = (EditText) findViewById(R.id.etMcpf);
        senha = (EditText) findViewById(R.id.passUser);
        data = new KeyDataBase(this);
        cancel = (Button) findViewById(R.id.cancelarSearch);
        ok = (Button) findViewById(R.id.letsgo);

        //EVENT BUS  -  REGISTER
        EventBus.getDefault().registerSticky(this);

        list = data.labsOn("0");

        ArrayAdapter<String> labsNum = new ArrayAdapter<String>(getApplicationContext(), R.layout.item_spinner_pers);


        for (Lab l : list) {

            labsNum.add(String.valueOf(l.getNumero()));

        }

        labsNum.setDropDownViewResource(R.layout.dropdow_item_spinner);
        labs.setAdapter(labsNum);


        labs.setOnItemSelectedListener(this);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((data.logar("" + mcpf.getText().toString(), "" + senha.getText().toString())) &&

                        (data.findUserToLab("" + mcpf.getText().toString(), labs.getSelectedItem().toString())

                        ) || (data.findlabtoaluno("" + mcpf.getText().toString(), labs.getSelectedItem().toString()))

                        ) {


                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    Date datas;
                    datas = new Date();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(datas);
                    Date data_atual = cal.getTime();

                      data_completa = dateFormat.format(data_atual);


                    if (data.Check("" + mcpf.getText().toString(), labs.getSelectedItem().toString(), guarda.getCpf(), true)) {


                        Lab l = data.findLab(labs.getSelectedItem().toString());


                        if (l != null) {
                            l.setOn(1);

                            data.updateLab(l, String.valueOf(l.getNumero()));
                            Toast.makeText(getApplicationContext(), "Emprestimo realizado com sucesso", Toast.LENGTH_LONG).show();
                            mTheadTimeToFinish = new TheadTimeToFinish(1000, 500, ActivityCheckin.this);
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

        Toast.makeText(getApplicationContext(), "Click em nada", Toast.LENGTH_LONG).show();


    }

    public void onEvent(Guarda guarda){
        this.guarda = guarda;

        Log.i("database", "Checkin: >> " + guarda.getCpf());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mTheadTimeToFinish != null) {

            mTheadTimeToFinish.cancel();
        }
        EventBus.getDefault().unregister(this);

    }
}
