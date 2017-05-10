package com.developer.ruddbeltrao.keycontroller.mListView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.ruddbeltrao.keycontroller.Activities.UpdateLab;
import com.developer.ruddbeltrao.keycontroller.R;
import com.developer.ruddbeltrao.keycontroller.domain.Lab;
import com.developer.ruddbeltrao.keycontroller.mRecycleView.ViewHolderAluno;

import java.util.List;

/**
 * Criado por Rudda Beltrao em 25/07/2015 as 00:45.
 */
public class AdapterLab extends BaseAdapter {

    private LayoutInflater inflater;
    private Context com;
    private List<Lab> labList;

    public AdapterLab(Context com, List<Lab> labList) {
        this.com = com;
        this.labList = labList;

        inflater = (LayoutInflater) com.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return labList.size();
    }

    @Override
    public Object getItem(int position) {
        return labList;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolderLab holder = new ViewHolderLab();
        convertView = inflater.inflate(R.layout.layout_lab, null);
        final Lab mLab = labList.get(position);

        holder.add = (ImageView) convertView.findViewById(R.id.add);
        holder.edit = (ImageView) convertView.findViewById(R.id.edit);
        holder.labNumero = (TextView) convertView.findViewById(R.id.numerodolab);
        holder.labDesc = (TextView)convertView.findViewById(R.id.labDesc);

        holder.labNumero.setText(String.valueOf(mLab.getNumero()));
        holder.labDesc.setText(mLab.getDescricao());

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(com, "add user", Toast.LENGTH_LONG).show();
            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(com, UpdateLab.class);

                it.putExtra("numero", mLab.getNumero());
                it.putExtra("bloco", mLab.getBloco());
                it.putExtra("pavimento", mLab.getPavimento());
                it.putExtra("descricao", mLab.getDescricao());

                com.startActivity(it);


            }
        });


        return convertView;
    }

    public class ViewHolderLab {

        TextView labNumero;
        TextView labDesc;
        ImageView add;
        ImageView edit;


    }

}
