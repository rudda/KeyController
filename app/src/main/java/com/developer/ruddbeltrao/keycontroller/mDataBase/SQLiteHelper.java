package com.developer.ruddbeltrao.keycontroller.mDataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Rudda Beltrao on 17/07/2015.
 */
public class SQLiteHelper extends SQLiteOpenHelper {


    //cria a base de dados [create  database]
    public SQLiteHelper(Context context, String name, int version) {

        super(context, name, null, version);


    }


    //cria as tabelas [create to tables]
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(mScripts.CREATE_TABLE_LAB);
        db.execSQL(mScripts.CREATE_TABLE_USUARIO);
        db.execSQL(mScripts.CREATE_TABLE_ALUNO);
        db.execSQL(mScripts.CREATE_TABLE_PROFESSOR);
        db.execSQL(mScripts.CREATE_TABLE_SERVIDOR);
        db.execSQL(mScripts.CREATE_TABLE_ADM);
        //  db.execSQL(mScripts.CREATE_TABLE_USUARIO_HAS_LAB);
        db.execSQL(mScripts.CREATE_TABLE_USUARIO_PERTENCE_LAB);
        db.execSQL(mScripts.CREATE_CHECK_IN);
        db.execSQL(mScripts.CREATE_CHECK_OUT);
        db.execSQL(mScripts.CREATE_GUARDA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        db.execSQL(mScripts.DROP_ADM);
        db.execSQL(mScripts.DROP_USUARIO);
        db.execSQL(mScripts.DROP_PROFESSOR);
        db.execSQL(mScripts.DROP_ALUNO);
        db.execSQL(mScripts.DROP_SERVIDOR);
        db.execSQL(mScripts.DROP_USUARIO_PERTENCE_LAB);
        db.execSQL(mScripts.DROP_USUARIO_HAS_LAB);
        db.execSQL(mScripts.DROP_LAB);
        db.execSQL(mScripts.DROP_CHECK_IN);
        db.execSQL(mScripts.DROP_CHECK_OUT);
        db.execSQL(mScripts.DROP_GUARDA);

        onCreate(db);


    }
}
