package com.developer.ruddbeltrao.keycontroller.Activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.developer.ruddbeltrao.keycontroller.R;
import com.developer.ruddbeltrao.keycontroller.domain.Lab;
import com.developer.ruddbeltrao.keycontroller.mDataBase.KeyDataBase;
import com.developer.ruddbeltrao.keycontroller.mListView.AdapterLab;
import com.developer.ruddbeltrao.keycontroller.mListView.AdapterLabOn;

import java.util.List;

public class ChavesEmprestadas extends ActionBarActivity {
    private KeyDataBase mdata;
    private List<Lab> mList;
    private GridView mGridView;
    private AdapterLabOn mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chaves_emprestadas);

        mGridView = (GridView)findViewById(R.id.gridViewLabOn);
        mdata = new KeyDataBase(this);
        mList = mdata.labsOn("1");

        mAdapter = new AdapterLabOn(this, mList);

        mGridView.setAdapter(mAdapter);

    }


}
