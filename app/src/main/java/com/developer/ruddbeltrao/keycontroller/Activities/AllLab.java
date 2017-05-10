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

import java.security.Key;
import java.util.List;

public class AllLab extends ActionBarActivity {

    private KeyDataBase mdata;
    private List<Lab> mList;
    private GridView mGridView;
    private AdapterLab mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_lab);

        mGridView = (GridView)findViewById(R.id.gridView);
        mdata = new KeyDataBase(this);
        mList = mdata.rawLab();

        mAdapter = new AdapterLab(this, mList);

        mGridView.setAdapter(mAdapter);



    }


    @Override
    protected void onRestart() {
        super.onRestart();

        mList = mdata.rawLab();
        mAdapter = new AdapterLab(AllLab.this, mList);
        mGridView.setAdapter(mAdapter);
    }
}
