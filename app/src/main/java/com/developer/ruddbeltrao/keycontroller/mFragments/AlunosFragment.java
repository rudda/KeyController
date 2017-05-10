package com.developer.ruddbeltrao.keycontroller.mFragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.developer.ruddbeltrao.keycontroller.R;
import com.developer.ruddbeltrao.keycontroller.domain.Aluno;
import com.developer.ruddbeltrao.keycontroller.mDataBase.KeyDataBase;
import com.developer.ruddbeltrao.keycontroller.mListView.AdapterAluno;
import com.developer.ruddbeltrao.keycontroller.mRecycleView.RecycleViewAlunos;

import java.util.ArrayList;
import java.util.List;


public class AlunosFragment extends android.support.v4.app.Fragment {


    private ListView mList;
    private AdapterAluno adp;
    private List<Aluno> list;
    private List<Aluno> listAux;
    private KeyDataBase mDataBase;
    private SearchView mSearchView;


    public AlunosFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_alunos, container, false);

        listAux = new ArrayList<>();
        mList = (ListView) view.findViewById(R.id.mlistAlunos);
        mSearchView = (SearchView) view.findViewById(R.id.searchViewAluno);
        mSearchView.setOnQueryTextListener(new seachListener());
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setEnabled(true);
        mDataBase = new KeyDataBase(getActivity());
        list = mDataBase.rawAluno();


        adp = new AdapterAluno(getActivity(), list);
        mList.setAdapter(adp);


        return view;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    private class seachListener implements android.widget.SearchView.OnQueryTextListener {


        @Override
        public boolean onQueryTextSubmit(String query) {

            Log.i("onQueryTextSubmit", query);


            if(listAux!=null){

                listAux.clear();
            }


            listAux = mDataBase.ImediateSeach(query);

            adp = new AdapterAluno(getActivity(), listAux);
            mList.setAdapter(adp);

            //Toast.makeText(getActivity(), "" + listAux.size(), Toast.LENGTH_LONG).show();

            if(listAux.size()==0){

                adp = new AdapterAluno(getActivity(), list);
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


            listAux = mDataBase.ImediateSeach(newText);

            adp = new AdapterAluno(getActivity(), listAux);
            mList.setAdapter(adp);

            //Toast.makeText(getActivity(), "" + listAux.size(), Toast.LENGTH_LONG).show();


            if(listAux.size()==0){

                adp = new AdapterAluno(getActivity(), list);
                mList.setAdapter(adp);
            }


            return false;
        }



    }

}
