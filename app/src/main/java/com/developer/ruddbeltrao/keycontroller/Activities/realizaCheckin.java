package com.developer.ruddbeltrao.keycontroller.Activities;

import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.ruddbeltrao.keycontroller.R;
import com.developer.ruddbeltrao.keycontroller.domain.Check;
import com.developer.ruddbeltrao.keycontroller.domain.Lab;
import com.developer.ruddbeltrao.keycontroller.mDataBase.KeyDataBase;
import com.developer.ruddbeltrao.keycontroller.mListView.AdapterCheck;
import com.wdullaer.materialdatetimepicker.date.DatePickerController;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class realizaCheckin extends ActionBarActivity implements DatePickerDialog.OnDateSetListener, DialogInterface.OnCancelListener, AdapterView.OnItemSelectedListener {


    private int dia, mes, ano = 0;
    private Button seach;
    private ImageButton ButtondatePicket;
    private Spinner mSipnnerLab;
    private TextView data;
    private KeyDataBase mData;
    private List<Lab> mLabs;
    private List<Check> mChecks;
    private String mLab;

    private AdapterCheck adapter;
    private ListView mListCheck;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realiza_checkin);

        seach = (Button) findViewById(R.id.find);
        mSipnnerLab = (Spinner) findViewById(R.id.slab);
        mListCheck = (ListView) findViewById(R.id.mListC);
        data = (TextView) findViewById(R.id.curentdate);
        ButtondatePicket = (ImageButton) findViewById(R.id.bDate);

        mData = new KeyDataBase(this);
        mLabs = mData.rawLab();


        mChecks = new ArrayList<>();

        adapter = new AdapterCheck(realizaCheckin.this, mChecks);
        mListCheck.setAdapter(adapter);




        ArrayAdapter<String> labsNum = new ArrayAdapter<String>(getApplicationContext(), R.layout.item_spinner_pers);


        for (Lab l : mLabs) {

            labsNum.add(String.valueOf(l.getNumero()));

        }

        labsNum.setDropDownViewResource(R.layout.dropdow_item_spinner);
        mSipnnerLab.setAdapter(labsNum);


        ButtondatePicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                initDate();
                Calendar mCalendar = Calendar.getInstance();
                mCalendar.set(ano, mes, dia);

                DatePickerDialog date = DatePickerDialog.newInstance(realizaCheckin.this, mCalendar.get(Calendar.YEAR),
                        mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));

                Calendar cMax = Calendar.getInstance();
                date.setMaxDate(cMax);

                date.show(getFragmentManager(), "Selecione Data");
                date.setOnCancelListener(realizaCheckin.this);


            }
        });

        seach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (data.getText() != null & data.getText().toString().length() > 0) {

                    String day = (dia < 10 ? "0" + dia : "" + dia);
                    String month = ((mes + 1) < 10 ? "0" + (mes+1) : "" + (mes+1));
                    String year = "" + ano;
                    String myDate = year +"-"+  month+"-"  + day;

                    Log.i("database", myDate);

                    mChecks = mData.testCk(myDate , true, mSipnnerLab.getSelectedItem().toString());

                    Toast.makeText(realizaCheckin.this, "size "+mChecks.size(), Toast.LENGTH_SHORT).show();
                    adapter = new AdapterCheck(realizaCheckin.this, mChecks);

                    mListCheck.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }


            }
        });


    }


    private void initDate() {


        if (ano == 0) {

            Calendar c = Calendar.getInstance();
            ano = c.get(Calendar.YEAR);
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);


        }
    }

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int i, int i1, int i2) {

        ano = i;
        mes = i1;
        dia = i2;

        data.setText((dia < 10 ? "0" + dia : "" + dia) + "/" + ((mes + 1) < 10 ? "0" + (mes+1) : "" + ((mes+1) + "/" + ano)));


    }

    @Override
    public void onCancel(DialogInterface dialog) {
        ano = mes = dia = 0;

        data.setText("");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mLab = mSipnnerLab.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
