package com.developer.ruddbeltrao.keycontroller.mFragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.developer.ruddbeltrao.keycontroller.R;
import com.developer.ruddbeltrao.keycontroller.domain.Professor;
import com.developer.ruddbeltrao.keycontroller.domain.Servidor;
import com.developer.ruddbeltrao.keycontroller.mDataBase.KeyDataBase;
import com.developer.ruddbeltrao.keycontroller.mListView.AdapterAluno;
import com.developer.ruddbeltrao.keycontroller.mListView.AdapterProf;
import com.developer.ruddbeltrao.keycontroller.mListView.AdapterServidor;
import com.developer.ruddbeltrao.keycontroller.mRecycleView.RecycleViewAlunos;

import java.util.List;


public class Servidores extends android.support.v4.app.Fragment {


    private List<Servidor> list;
    private List<Servidor> listAux;
    private KeyDataBase mDataBase;
    private SearchView mSearchView;
    private ListView mList;
    private AdapterServidor adp;


    public Servidores() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_servidores, container, false);
        mList = (ListView) view.findViewById(R.id.mlistServidores);
        mDataBase = new KeyDataBase(getActivity());
        list = mDataBase.rawServidor();
        mSearchView = (SearchView) view.findViewById(R.id.searchViewServ);
        mSearchView.setOnQueryTextListener(new seachListener());
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setEnabled(true);

        adp = new AdapterServidor(getActivity(), list);
        mList.setAdapter(adp);


        return  view;
    }
    private class seachListener implements android.widget.SearchView.OnQueryTextListener {


        @Override
        public boolean onQueryTextSubmit(String query) {

            Log.i("onQueryTextSubmit", query);

            if(listAux!=null){

                listAux.clear();
            }

            listAux.clear();


            listAux = mDataBase.ImediateSeachServidor(query);

            adp = new AdapterServidor(getActivity(), listAux);
            mList.setAdapter(adp);

           // Toast.makeText(getActivity(), "" + listAux.size(), Toast.LENGTH_LONG).show();

            if(listAux.size()==0){

                adp = new AdapterServidor(getActivity(), list);
                mList.setAdapter(adp);
            }


            return false;
        }



        @Override
        public boolean onQueryTextChange(String newText) {

            Log.i("onQueryTextSubmit", newText);

            if(listAux!=null){

                listAux.clear();
            }


            listAux = mDataBase.ImediateSeachServidor(newText);

            adp = new AdapterServidor(getActivity(), listAux);
            mList.setAdapter(adp);

           // Toast.makeText(getActivity(), "" + listAux.size(), Toast.LENGTH_LONG).show();
            if(listAux.size()==0){

                adp = new AdapterServidor(getActivity(), list);
                mList.setAdapter(adp);
            }


            return false;
        }



    }

}
