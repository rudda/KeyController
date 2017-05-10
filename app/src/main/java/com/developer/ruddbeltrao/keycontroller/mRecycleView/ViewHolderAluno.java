package com.developer.ruddbeltrao.keycontroller.mRecycleView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.developer.ruddbeltrao.keycontroller.R;

/**
 * Criado por Rudda Beltrao em 22/07/2015 as 15:36.
 */
public class ViewHolderAluno extends RecyclerView.ViewHolder
{

    public TextView nome;
    public TextView subTitle;
    public ImageView foto;


    public ViewHolderAluno(View itemView) {
        super(itemView);

        nome = (TextView)itemView.findViewById(R.id.nomeAlunoItem);
        subTitle = (TextView)itemView.findViewById(R.id.subTitleAlunoitem);
        foto= (ImageView)itemView.findViewById(R.id.imageAlunoItem);



    }
}
