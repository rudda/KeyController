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

import java.util.List;

/**
 * Criado por Rudda Beltrao em 25/07/2015 as 07:00.
 */
public class AdapterLabOn extends BaseAdapter {

    private LayoutInflater inflater;
    private Context com;
    private List<Lab> labList;

    public AdapterLabOn(Context com, List<Lab> labList) {
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
        convertView = inflater.inflate(R.layout.layout_lab_on, null);
        final Lab mLab = labList.get(position);

        holder.labNumero = (TextView) convertView.findViewById(R.id.n);
        holder.labDesc = (TextView) convertView.findViewById(R.id.labdescOn);
        holder.labNumero.setText(String.valueOf(mLab.getNumero()));
        holder.labDesc.setText(mLab.getDescricao());

        return convertView;
    }

    public class ViewHolderLab {

        TextView labNumero;
        TextView labDesc;


    }

}
