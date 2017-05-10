package com.developer.ruddbeltrao.keycontroller.mListView;

import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.developer.ruddbeltrao.keycontroller.R;
import com.developer.ruddbeltrao.keycontroller.domain.Aluno;

import java.util.List;

/**
 * Criado por Rudda Beltrao em 24/07/2015 as 18:21.
 */
public class AdapterAluno extends BaseAdapter {

    private Context com;
    private LayoutInflater inflater;
    private List<Aluno> mList;

    public AdapterAluno(Context com, List<Aluno> mList) {
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

        Aluno al = mList.get(position);

        ViewHolderAluno vh = new ViewHolderAluno();
        convertView = inflater.inflate(R.layout.layout_item_aluno,null );

        vh.nome = (TextView) convertView.findViewById(R.id.nomeAlunoItem);
        vh.subTitle = (TextView)convertView.findViewById(R.id.subTitleAlunoitem);

        vh.nome.setText(al.getNome());
        vh.subTitle.setText(al.getMatricula()+", "+al.getCurso());




        return convertView;
    }


    public class  ViewHolderAluno{

        private TextView nome;
        private TextView subTitle;
        private TextView foto;


    }



}
