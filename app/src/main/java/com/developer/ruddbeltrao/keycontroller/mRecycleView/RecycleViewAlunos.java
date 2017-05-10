package com.developer.ruddbeltrao.keycontroller.mRecycleView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.ruddbeltrao.keycontroller.R;
import com.developer.ruddbeltrao.keycontroller.domain.Aluno;

import java.util.List;

/**
 * Criado por Rudda Beltrao em 22/07/2015 as 15:32.
 */
public class RecycleViewAlunos extends RecyclerView.Adapter<ViewHolderAluno> {

    private List<Aluno> ListAlunos;
    private Context com;
    private ClickToItem mClick;
    private LayoutInflater inflater;


    public RecycleViewAlunos(Context com, List<Aluno> listAlunos) {

        this.com = com;
        ListAlunos = listAlunos;
        inflater = (LayoutInflater) com.getSystemService(com.LAYOUT_INFLATER_SERVICE);
        Log.i("Create", "Criou");

    }

    @Override
    public ViewHolderAluno onCreateViewHolder(ViewGroup viewGroup, int i) {


        View v = inflater.inflate(R.layout.layout_item_aluno, viewGroup, false);

        ViewHolderAluno mvha = new ViewHolderAluno(v);
        Log.i("Create", "Criou");
        return mvha;
    }

    @Override
    public void onBindViewHolder(ViewHolderAluno viewHolderAluno, int i) {

        viewHolderAluno.nome.setText(ListAlunos.get(i).getNome());
        viewHolderAluno.subTitle.setText(ListAlunos.get(i).getMatricula()+", "+ListAlunos.get(i).getCurso());

        Log.i("Adp", "" + ListAlunos.get(i).getNome());

    }

    @Override
    public int getItemCount() {
        return 0;
    }






    public ClickToItem getmClick() {
        return mClick;
    }

    public void setmClick(ClickToItem mClick) {
        this.mClick = mClick;
    }



}
