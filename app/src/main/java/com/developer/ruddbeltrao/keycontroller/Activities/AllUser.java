package com.developer.ruddbeltrao.keycontroller.Activities;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TabHost;

import com.developer.ruddbeltrao.keycontroller.R;
import com.developer.ruddbeltrao.keycontroller.mFragments.FragmentMain;

public class AllUser extends ActionBarActivity {


    private FragmentManager manager;
    private android.support.v4.app.Fragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_user);

        manager = getSupportFragmentManager();

        if(savedInstanceState == null){

            fragment = new FragmentMain();
            manager =  getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.mcontainerAll, fragment).commit();



        }



    }




}
