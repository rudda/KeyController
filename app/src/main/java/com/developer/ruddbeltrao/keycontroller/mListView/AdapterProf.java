package com.developer.ruddbeltrao.keycontroller.mListView;

/**
 * Criado por Rudda Beltrao em 24/07/2015 as 18:51.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.developer.ruddbeltrao.keycontroller.R;
import com.developer.ruddbeltrao.keycontroller.domain.Aluno;

import java.util.List;



import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.developer.ruddbeltrao.keycontroller.R;
import com.developer.ruddbeltrao.keycontroller.domain.Aluno;
import com.developer.ruddbeltrao.keycontroller.domain.Professor;

import java.util.List;

/**
 * Criado por Rudda Beltrao em 24/07/2015 as 18:21.
 */
public class AdapterProf extends BaseAdapter {

    private Context com;
    private LayoutInflater inflater;
    private List<Professor> mList;

    public AdapterProf(Context com, List<Professor> mList) {
        this.com = com;
        this.mList = mList;

        inflater = (LayoutInflater) com.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Professor al = mList.get(position);

        ViewHolderAluno vh = new ViewHolderAluno();
        convertView = inflater.inflate(R.layout.layout_item_aluno,null );

        vh.nome = (TextView) convertView.findViewById(R.id.nomeAlunoItem);
        vh.subTitle = (TextView)convertView.findViewById(R.id.subTitleAlunoitem);
        vh.foto = (ImageView)convertView.findViewById(R.id.imageAlunoItem);
        vh.nome.setText(al.getNome());
        vh.subTitle.setText(al.getSiape());
        vh.foto.setImageResource(R.drawable.professor);



        return convertView;
    }


    public class  ViewHolderAluno{

        private TextView nome;
        private TextView subTitle;
        private ImageView foto;


    }



}

