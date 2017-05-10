package com.developer.ruddbeltrao.keycontroller.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.developer.ruddbeltrao.keycontroller.R;
import com.developer.ruddbeltrao.keycontroller.mDataBase.KeyDataBase;

public class LoginAdm extends ActionBarActivity {

    private EditText etCpf;
    private EditText etSiape;
    private TextView tv;
    private Button entrar;
    private LinearLayout login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_adm);

        etSiape = (EditText) findViewById(R.id.etSIAPE);
        etCpf = (EditText) findViewById(R.id.etCPF);
        tv = (TextView) findViewById(R.id.msgERRO);
        entrar = (Button) findViewById(R.id.btEntrar);
        login = (LinearLayout)findViewById(R.id.linear_login_adm);
        tv.setVisibility(View.INVISIBLE);






        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                KeyDataBase data = new KeyDataBase(LoginAdm.this);

                String cpf = etCpf.getText().toString();
                String siape = etSiape.getText().toString();


                if (data.loginAdm(cpf, siape)) {

                    Intent it = new Intent(LoginAdm.this, AdmActivity.class);
                    startActivity(it);
                    finish();
                    data.close();


                } else
                {

                    tv.setVisibility(View.VISIBLE);
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .playOn(login);



                }



            }
        });



    }


    public void fClose(View v){
        finish();

    }


}
