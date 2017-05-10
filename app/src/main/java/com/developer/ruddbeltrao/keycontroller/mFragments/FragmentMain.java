package com.developer.ruddbeltrao.keycontroller.mFragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.ruddbeltrao.keycontroller.R;


public class FragmentMain extends android.support.v4.app.Fragment {

    private FragmentTabHost mTabHost;

    public FragmentMain() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        mTabHost = new FragmentTabHost(getActivity());
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.containerFragMain);
        mTabHost.addTab(mTabHost.newTabSpec("professores").setIndicator("Professores"),
                Professores.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("alunos").setIndicator("Alunos"),
                AlunosFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("servidor").setIndicator("Servidores"),
                Servidores.class, null);
        mTabHost.getTabWidget().setBackgroundColor(getResources().getColor(R.color.myColorPrimary));


        return mTabHost;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }



}
