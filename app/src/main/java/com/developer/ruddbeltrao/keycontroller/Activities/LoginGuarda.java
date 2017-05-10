package com.developer.ruddbeltrao.keycontroller.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.developer.ruddbeltrao.keycontroller.R;
import com.developer.ruddbeltrao.keycontroller.domain.Guarda;
import com.developer.ruddbeltrao.keycontroller.mDataBase.KeyDataBase;

import de.greenrobot.event.EventBus;

public class LoginGuarda extends ActionBarActivity {
    private EditText etCpf;
    private EditText senha;
    private TextView tv;
    private Button entrar;
    private Guarda guarda;
    private LinearLayout login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_guarda);
        guarda = new Guarda();


        senha = (EditText) findViewById(R.id.etSehaGuarda);
        etCpf = (EditText) findViewById(R.id.etCPFGuarda);
        tv = (TextView) findViewById(R.id.msgERROGuarda);
        entrar = (Button) findViewById(R.id.btEntrarGuarda);
        login  = (LinearLayout)findViewById(R.id.linear_login);

        tv.setVisibility(View.INVISIBLE);

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                KeyDataBase data = new KeyDataBase(LoginGuarda.this);

                guarda.setCpf(""+etCpf.getText().toString());
                guarda.setSenha("" + senha.getText().toString());


                if (data.isGuarda(guarda.getCpf(), guarda.getSenha())) {

                    Log.i("database", guarda.getCpf());

                    guarda = data.rawGuarda(guarda.getCpf());

                    Log.i("database", "nome g: "+guarda.getNome());
                    Log.i("database", "cpf g:"+guarda.getCpf());


                    EventBus.getDefault().postSticky(guarda);

                    Intent it = new Intent(LoginGuarda.this, GuardaActivity.class);
                    startActivity(it);
                    finish();
                    data.close();


                } else {

                    tv.setVisibility(View.VISIBLE);

                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .playOn(login);




                }


            }
        });


    }

    public void close(View v){

        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }
}
