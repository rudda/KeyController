package com.developer.ruddbeltrao.keycontroller.mListView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.developer.ruddbeltrao.keycontroller.R;
import com.developer.ruddbeltrao.keycontroller.domain.Check;

import java.util.List;

/**
 * Criado por Rudda Beltrao em 25/07/2015 as 12:16.
 */
public class AdapterCheck extends BaseAdapter {

    private LayoutInflater inflater;
    private Context com;
    private List<Check> mList;


    public AdapterCheck(Context com, List<Check> mList) {
        this.com = com;
        this.mList = mList;

    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {

        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

       Check c = mList.get(position);
            //inflater.from(com).inflate(R.layout.layout_item_check, parent, null);
       View v = inflater.from(com).inflate(R.layout.layout_item_check, parent, false);//inflater.inflate(R.layout.layout_item_check, null);
        ViewHolderItemLay item = new ViewHolderItemLay();

        item.hora = (TextView) v.findViewById(R.id.c_hora);
        item.nomeResponsavel = (TextView) v.findViewById(R.id.c_G_responsavel_G);
        item.nomeUsuario = (TextView) v.findViewById(R.id.c_user);

        item.hora.setText("Data: "+c.getData());
        item.nomeResponsavel.setText("Guarda: "+c.getGuarda());
        item.nomeUsuario.setText("Usuario: "+c.getUsuario());



        return v;
    }


    public class ViewHolderItemLay {

        private TextView nomeUsuario;
        private TextView nomeResponsavel;
        private TextView hora;


    }

}
