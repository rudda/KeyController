package com.developer.ruddbeltrao.keycontroller.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.developer.ruddbeltrao.keycontroller.R;

public class ChooseUser extends ActionBarActivity {

    private ImageView ivAluno;
    private ImageView ivProfessor;
    private ImageView ivGuarda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_user);


        ivAluno = (ImageView) findViewById(R.id.alunoic);
        ivProfessor = (ImageView) findViewById(R.id.professor);
        ivGuarda = (ImageView) findViewById(R.id.guarda);


        ivAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ChooseUser.this, CadastrarAluno.class);
                startActivity(it);
            }
        });


        ivProfessor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent it = new Intent(ChooseUser.this, CadProfessor.class);

                startActivity(it);
            }
        });


        ivGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(ChooseUser.this, CadastrarGuarda.class);

                startActivity(it);

            }
        });

    }


}
