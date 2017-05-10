package com.developer.ruddbeltrao.keycontroller.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.developer.ruddbeltrao.keycontroller.Network.NetworkConnection;
import com.developer.ruddbeltrao.keycontroller.Network.Interfaces.TrasactionTeste;
import com.developer.ruddbeltrao.keycontroller.Network.WrapNetwork;
import com.developer.ruddbeltrao.keycontroller.R;
import com.developer.ruddbeltrao.keycontroller.domain.Aluno;

public class TestVolley extends AppCompatActivity implements TrasactionTeste{
        private WrapNetwork wrap;

    private Button b1, b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_volley);

        b1 = (Button)findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        wrap = new WrapNetwork();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Aluno al = new Aluno("Lohrayne Souza", "5412341221", "1284637-2", "loh@gmail.com",
                        "eulohSouza", "", 1, "farmacia", "2015", "21202922", 312
                );

                wrap.setAluno(al);
                wrap.setMethod("addAL");

                // NetworkConnection.getInstace(TestVolley.this).execute(TestVolley.this, TestVolley.class.getName());
                NetworkConnection.getInstace(TestVolley.this).execute(TestVolley.this, TestVolley.class.getName());



            }
        });



    }


    @Override
    public void doAfter() {


    }

    @Override
    public void doBefore(String params) {


        Toast.makeText(TestVolley.this, params, Toast.LENGTH_SHORT).show();
    }



}
