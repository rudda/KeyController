package com.developer.ruddbeltrao.keycontroller.mDataBase;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.widget.Toast;
import com.developer.ruddbeltrao.keycontroller.domain.Adm;
import com.developer.ruddbeltrao.keycontroller.domain.Aluno;
import com.developer.ruddbeltrao.keycontroller.domain.Check;
import com.developer.ruddbeltrao.keycontroller.domain.Guarda;
import com.developer.ruddbeltrao.keycontroller.domain.Lab;
import com.developer.ruddbeltrao.keycontroller.domain.Professor;
import com.developer.ruddbeltrao.keycontroller.domain.Servidor;
import com.developer.ruddbeltrao.keycontroller.domain.Usuario;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Criado por Rudda Beltrao em 17/07/2015.
 */

public class KeyDataBase {
/*
*       CLASS RESPONSE TO CREAT DATABASE AND YOUR TRANSACTIONS
*
* RULES
*
* 1 para inserir um aluno voce deve primeiro inserir um usuario e um laboratorio.
* 2 ao excluir um usuario vc tambem eclui suas dependias seja na tabela [aluno, professor ou servidor]
*
* */

    //nome de todas as tabelas do banco de dados. :D

    private static final String NOME_BANCO = "BANCO2";
    private static final int VERSAO_BANCO = 16;


    private Context mContext;
    protected SQLiteDatabase mDataBase;


    private SQLiteHelper mHelper;


    public KeyDataBase(Context mContext) {

        this.mContext = mContext;

        this.mHelper = new SQLiteHelper(mContext, NOME_BANCO, VERSAO_BANCO);
        this.mDataBase = mHelper.getWritableDatabase();

    }


    private boolean insert(String table, ContentValues values) {
        try {


            mDataBase.insert(table, null, values);

            Log.i("DataBase", "Insert ok");

            return true;
        } catch (Exception erro) {
            Log.i("DataBase", "Insert fail");
            return false;
        }

    }


    private boolean update(String table, String where, String[] whereArgs, ContentValues values) {

        try {

            int count = mDataBase.update(table, values, where, whereArgs);
            Log.i("DataBase", "Update " + count);
            return true;
        } catch (Exception erro) {

            Log.i("DataBase", "Update fail");
            return false;
        }

    }

    private boolean delete(String table, String where, String[] whereArgs) {

        try {

            mDataBase.delete(table, where, whereArgs);

            Log.i("DataBase", "Delete ok");
            return true;
        } catch (Exception erro) {
            Log.i("Database", "Delete fail");
            return false;
        }


    }


//INSERT METHODS

    public boolean insertLab(Lab lab) {

        try {
            ContentValues values = new ContentValues();

            values.put(lab.COLUM_LAB_NUMERO, lab.getNumero());
            values.put(lab.COLUM_BLOCO, lab.getBloco());
            values.put(lab.COLUM_PAVIMENTO, lab.getPavimento());
            values.put(lab.COLUM_DESCRICAO, lab.getDescricao());
            values.put(lab.COLUM_ON, lab.getOn());


            mDataBase.insert(mScripts.TAB_LAB, null, values);

            Toast.makeText(mContext, "OK", Toast.LENGTH_LONG).show();

            return true;
        } catch (SQLiteException err) {

            Toast.makeText(mContext, "erro " + err.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        }


    }

    public boolean insertUsuario(Usuario usuario) {

        ContentValues values = new ContentValues();

        try {
            values.put(usuario.COLUM_CPF, usuario.getCpf());
            values.put(usuario.COLUM_NOME, usuario.getNome());
            values.put(usuario.COLUM_SENHA, usuario.getSenha());
            values.put(usuario.COLUM_FOTO, usuario.getUrlFoto());
            values.put(usuario.COLUM_TIPO, usuario.getType());
            values.put(usuario.COLUM_EMAIL, usuario.getEmail());


            mDataBase.insert(mScripts.TAB_USUARIO, null, values);

            return  true;
        } catch (SQLiteException erro) {
            Toast.makeText(mContext, "erro " + erro.getMessage(), Toast.LENGTH_LONG).show();
            return  false;
        }


    }


    public boolean insertAluno(Aluno aluno) {

        Usuario user = (Usuario) aluno;


            if (insertUsuario(user)) {
                try {
                ContentValues values = new ContentValues();


                values.put(aluno.COLUM_CPF, aluno.getCpf());
                values.put(aluno.COLUM_LAB_NUMERO, aluno.getLab_numero());
                values.put(aluno.COLUM_MATRICULA, aluno.getMatricula());
                values.put(aluno.COLUM_CURSO, aluno.getCurso());
                values.put(aluno.COLUM_ANO, aluno.getAnoCurso());


                mDataBase.insert(mScripts.TAB_ALUNO, null, values);
                Log.i("insertAluno", "nome: " + aluno.getCurso());
                return true;


            }

                catch (SQLiteException erro) {
                    Toast.makeText(mContext, "erro " + erro.getMessage(), Toast.LENGTH_LONG).show();

                    return false;

                }


        }

        return false;
    }

    public boolean insertProfessor(Professor prof) {

        Usuario user = (Usuario) prof;

        insertUsuario(user);

        ContentValues values = new ContentValues();
        try {
            values.put(prof.COLUM_CPF, prof.getCpf());
            values.put(prof.COLUM_PASEP, prof.getPasep());
            values.put(prof.COLUM_SIAPE, prof.getSiape());

            mDataBase.insert(mScripts.TAB_PROFESSOR, null, values);
            return true;
        } catch (SQLiteException erro) {
            Toast.makeText(mContext, "erro " + erro.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        }

    }

    public boolean insertServidor(Servidor servidor) {

        Usuario user = (Usuario) servidor;

        insertUsuario(user);
        ContentValues values = new ContentValues();

        try {
            values.put(servidor.COLUM_CPF, servidor.getCpf());
            values.put(servidor.COLUM_SIAPE, servidor.getSiape());
            values.put(servidor.COLUM_FUNCAO, servidor.getFuncao());
            values.put(servidor.COLUM_SIAPE, servidor.getSiape());

            mDataBase.insert(mScripts.TAB_SERVIDOR, null, values);
            return true;
        } catch (SQLiteException erro) {
            Toast.makeText(mContext, "erro " + erro.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        }


    }

    public boolean insertGuada(Guarda guarda) {

        ContentValues values = new ContentValues();

        try {

            values.put(guarda.COLUMN_CPF, guarda.getCpf());
            values.put(guarda.COLUMN_FOTO, guarda.getFoto());
            values.put(guarda.COLUMN_NOME, guarda.getNome());
            values.put(guarda.COLUMN_PASEP, guarda.getPasep());
            values.put(guarda.COLUMN_RG, guarda.getRg());
            values.put(guarda.COLUMN_SENHA, guarda.getSenha());

            mDataBase.insert(mScripts.TAB_GUARDA, null, values);

            return true;

        } catch (SQLiteException erro) {

            Log.i("insert", erro.getMessage());
            return false;
        }


    }


    public boolean insertAdm(Adm adm) {


        ContentValues values = new ContentValues();

        try {

            values.put(adm.COLUM_CPF, adm.getCpf());
            values.put(adm.COLUM_NOME, adm.getNome());
            values.put(adm.COLUM_SENHA, adm.getSenha());
            values.put(adm.COLUM_SIAPE, adm.getSiape());

            mDataBase.insert(mScripts.TAB_ADM, null, values);

            return true;

        } catch (SQLiteException erro) {

            Log.i("insertADM ", erro.getMessage());

        }


        return false;
    }

    public boolean insertUsuarioSetLab() {


        return false;
    }


    // UPDATE METHODS


    public boolean updateLab(Lab lab, String ID) {

        ContentValues values = new ContentValues();
        String where = lab.COLUM_LAB_NUMERO + "= ?";
        String whereArgs[] = {ID};

        try {

            values.put(lab.COLUM_LAB_NUMERO, lab.getNumero());
            values.put(lab.COLUM_BLOCO, lab.getBloco());
            values.put(lab.COLUM_PAVIMENTO, lab.getPavimento());
            values.put(lab.COLUM_DESCRICAO, lab.getDescricao());
            values.put(lab.COLUM_ON, lab.getOn());

            mDataBase.update(mScripts.TAB_LAB, values, where, whereArgs);

            return true;
        } catch (SQLiteException erro) {

            Log.e("SQLiteException", "erro: " + erro.getMessage());

        }
        return false;
    }

    public boolean updateUsuario(Usuario usuario, String where, String whereArgs[]) {

        ContentValues values = new ContentValues();

        try {
            values.put(usuario.COLUM_CPF, usuario.getCpf());
            values.put(usuario.COLUM_NOME, usuario.getNome());
            values.put(usuario.COLUM_SENHA, usuario.getSenha());
            values.put(usuario.COLUM_FOTO, usuario.getUrlFoto());
            values.put(usuario.COLUM_TIPO, usuario.getType());
            values.put(usuario.COLUM_EMAIL, usuario.getEmail());

            return mDataBase.update(mScripts.TAB_USUARIO, values, where, whereArgs) > 0;

        } catch (SQLiteException erro) {
            Toast.makeText(mContext, "erro " + erro.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        }

    }


    public boolean updateAluno(Aluno aluno, String ID) {

        Usuario user = (Usuario) aluno;
        String where = user.COLUM_CPF + "=?";
        String whereArgs[] = {ID};

        if (updateUsuario(user, where, whereArgs)) {


            ContentValues values = new ContentValues();

            try {

                String whereAluno = user.COLUM_CPF + "=?";
                String whereArgsAluno[] = {aluno.getCpf()};

                values.put(aluno.COLUM_CPF, aluno.getCpf());
                values.put(aluno.COLUM_LAB_NUMERO, aluno.getLab_numero());
                values.put(aluno.COLUM_MATRICULA, aluno.getMatricula());
                values.put(aluno.COLUM_CURSO, aluno.getCurso());
                values.put(aluno.COLUM_ANO, aluno.getAnoCurso());


                mDataBase.update(mScripts.TAB_ALUNO, values, whereAluno, whereArgsAluno);
                return true;
            } catch (SQLiteException erro) {

                Toast.makeText(mContext, "erro " + erro.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("mDataBase", "erro " + erro.getMessage());
                return false;
            }


        } else {

            return false;
        }

    }


    public boolean updateProfessor(Professor professor, String ID) {


        Usuario user = (Usuario) professor;
        String where = user.COLUM_CPF + "=?";
        String whereArgs[] = {ID};

        if (updateUsuario(user, where, whereArgs)) {


            ContentValues values = new ContentValues();

            try {

                String whereProfessor = user.COLUM_CPF + "=?";
                String whereArgsProfessor[] = {professor.getCpf()};

                values.put(professor.COLUM_CPF, professor.getCpf());
                values.put(professor.COLUM_SIAPE, professor.getSiape());
                values.put(professor.COLUM_PASEP, professor.getPasep());


                mDataBase.update(mScripts.TAB_PROFESSOR, values, whereProfessor, whereArgsProfessor);
                return true;
            } catch (SQLiteException erro) {

                Toast.makeText(mContext, "erro " + erro.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("mDataBase", "erro " + erro.getMessage());
                return false;
            }


        } else {

            return false;
        }


    }


    public boolean updateServidor(Servidor servidor, String ID) {

        Usuario user = (Usuario) servidor;
        String where = user.COLUM_CPF + "=?";
        String whereArgs[] = {ID};

        if (updateUsuario(user, where, whereArgs)) {


            ContentValues values = new ContentValues();

            try {

                String whereServidor = servidor.COLUM_CPF + "=?";
                String whereArgsServidor[] = {servidor.getCpf()};

                values.put(servidor.COLUM_CPF, servidor.getCpf());
                values.put(servidor.COLUM_SIAPE, servidor.getSiape());
                values.put(servidor.COLUM_PASEP, servidor.getPasep());
                values.put(servidor.COLUM_FUNCAO, servidor.getFuncao());


                mDataBase.update(mScripts.TAB_SERVIDOR, values, whereServidor, whereArgsServidor);
                return true;
            } catch (SQLiteException erro) {

                Toast.makeText(mContext, "erro " + erro.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("mDataBase", "erro " + erro.getMessage());
                return false;
            }


        } else {

            return false;
        }


    }


    public boolean updateGuarda(Guarda guarda, String ID) {


        String where = guarda.COLUMN_CPF + "=?";
        String whereArgs[] = {ID};

        try {

            ContentValues values = new ContentValues();
            values.put(guarda.COLUMN_NOME, guarda.getNome());
            values.put(guarda.COLUMN_CPF, guarda.getCpf());
            values.put(guarda.COLUMN_FOTO, guarda.getFoto());
            values.put(guarda.COLUMN_SENHA, guarda.getSenha());
            values.put(guarda.COLUMN_PASEP, guarda.getPasep());
            values.put(guarda.COLUMN_RG, guarda.getRg());

            if (mDataBase.update(mScripts.TAB_GUARDA, values, where, whereArgs) > 0) {

                return true;
            }


        } catch (SQLiteException erro) {

            Log.i("Update", erro.getMessage());

        }


        return false;
    }

    //QUERYS METHODS

    public Aluno rawAluno(String ID) {
        Aluno aluno = new Aluno();

        try {
            Cursor c = mDataBase.rawQuery("select u." + aluno.COLUM_CPF + ", u." + aluno.COLUM_NOME +
                    ", u." + aluno.COLUM_EMAIL + ", u." + aluno.COLUM_FOTO + ", u." + aluno.COLUM_SENHA + ", u." + aluno.COLUM_TIPO +
                    ",a." + aluno.COLUM_MATRICULA + ", a." + aluno.COLUM_CURSO + ",a." + aluno.COLUM_LAB_NUMERO + ", a." + aluno.COLUM_ANO +
                    " from usuario as u inner join aluno as a on u.usuario_cpf = ?", new String[]{ID});


            c.moveToFirst();
            int indexSenha = c.getColumnIndex(aluno.COLUM_SENHA);
            int indexName = c.getColumnIndex(aluno.COLUM_NOME);
            int indexCpf = c.getColumnIndex(aluno.COLUM_CPF);
            int indexEmail = c.getColumnIndex(aluno.COLUM_EMAIL);
            int indexFoto = c.getColumnIndex(aluno.COLUM_FOTO);
            int indexMatricula = c.getColumnIndex(aluno.COLUM_MATRICULA);
            int indexLAb = c.getColumnIndex(aluno.COLUM_LAB_NUMERO);
            int indexAno = c.getColumnIndex(aluno.COLUM_ANO);
            int indexCurso = c.getColumnIndex(aluno.COLUM_CURSO);

            int indexTipo = c.getColumnIndex(aluno.COLUM_TIPO);


            aluno.setNome(c.getString(indexName));
            aluno.setCpf(c.getString(indexCpf));
            aluno.setCurso(c.getColumnName(indexCurso));
            aluno.setEmail(c.getString(indexEmail));
            aluno.setAnoCurso(c.getString(indexAno));
            aluno.setUrlFoto(c.getString(indexFoto));
            aluno.setMatricula(c.getString(indexMatricula));
            aluno.setLab_numero(c.getInt(indexLAb));
            aluno.setSenha(c.getString(indexSenha));
            aluno.setType(c.getInt(indexTipo));


            Log.i("rawQueryAluno", "aluno: " + aluno.getNome() + " was return ");

            return aluno;
        } catch (SQLiteException erro) {

            return null;
        }


    }


    public List<Aluno> rawAluno() {

        List<Aluno> alunosList = new ArrayList<>();
        Aluno aluno = new Aluno();

        try {
            String sql = "select u." + aluno.COLUM_CPF + ", u." + aluno.COLUM_NOME +
                    ", u." + aluno.COLUM_EMAIL + ", u." + aluno.COLUM_FOTO + ", u." + aluno.COLUM_SENHA + ", u." + aluno.COLUM_TIPO +
                    ", a." + aluno.COLUM_MATRICULA + ", a.aluno_curso" + ",a." + aluno.COLUM_LAB_NUMERO + ", a." + aluno.COLUM_ANO +
                    " from usuario as u inner join aluno as a where u.usuario_cpf = a.usuario_cpf order by u.usuario_nome desc";
            Cursor c = mDataBase.rawQuery(sql, null);

            Log.i("rawQueryAluno", "iniciou " + c.getCount());
            Log.i("rawQueryAluno", "sql " + sql);

            while (c.moveToNext()) {

                aluno = new Aluno();

                int indexName = c.getColumnIndex(aluno.COLUM_NOME);
                int indexCpf = c.getColumnIndex(aluno.COLUM_CPF);
                int indexEmail = c.getColumnIndex(aluno.COLUM_EMAIL);
                int indexFoto = c.getColumnIndex(aluno.COLUM_FOTO);
                int indexMatricula = c.getColumnIndex(aluno.COLUM_MATRICULA);
                int indexLAb = c.getColumnIndex(aluno.COLUM_LAB_NUMERO);
                int indexAno = c.getColumnIndex(aluno.COLUM_ANO);
                int indexCurso = c.getColumnIndex(aluno.COLUM_CURSO);
                int indexSenha = c.getColumnIndex(aluno.COLUM_SENHA);
                int indexTipo = c.getColumnIndex(aluno.COLUM_TIPO);


                aluno.setNome(c.getString(indexName));
                aluno.setCpf(c.getString(indexCpf));
                aluno.setCurso(c.getString(indexCurso));
                aluno.setEmail(c.getString(indexEmail));
                aluno.setAnoCurso(c.getString(indexAno));
                aluno.setUrlFoto(c.getString(indexFoto));
                aluno.setMatricula(c.getString(indexMatricula));
                aluno.setLab_numero(c.getInt(indexLAb));
                aluno.setSenha(c.getString(indexSenha));
                aluno.setType(c.getInt(indexTipo));


                Log.i("rawQueryAluno", "aluno Curso: " + aluno.getCurso() + " was return ");

                alunosList.add(aluno);
            }


        } catch (SQLiteException erro) {

            Log.i("rawQueryAluno", "erro " + erro.getMessage());

        }


        return alunosList;
    }


    public List<Servidor> rawServidor() {

        Servidor servidor = new Servidor();
        List<Servidor> servidorList = new ArrayList<>();


        try {
            String sql = "select u." + servidor.COLUM_CPF + ", u." + servidor.COLUM_NOME +
                    ", u." + servidor.COLUM_EMAIL + ", u." + servidor.COLUM_FOTO + ", u." + servidor.COLUM_SENHA + ", u." + servidor.COLUM_TIPO +
                    ",s." + servidor.COLUM_FUNCAO + ", s." + servidor.COLUM_SIAPE + ",s." + servidor.COLUM_PASEP
                    + " from usuario as u inner join servidor as s where u.usuario_cpf = s.usuario_cpf order by u.usuario_nome";

            Cursor c = mDataBase.rawQuery(sql, null);

            Log.i("rawQuerySERIDOR", "count " + c.getCount());
            Log.i("rawQuerySERVIDOR", "sql " + sql);

            while (c.moveToNext()) {

                servidor = new Servidor();

                int indexName = c.getColumnIndex(servidor.COLUM_NOME);
                int indexCpf = c.getColumnIndex(servidor.COLUM_CPF);
                int indexEmail = c.getColumnIndex(servidor.COLUM_EMAIL);
                int indexFoto = c.getColumnIndex(servidor.COLUM_FOTO);
                int indexTipo = c.getColumnIndex(servidor.COLUM_TIPO);
                int indexPASEP = c.getColumnIndex(servidor.COLUM_PASEP);
                int indexSIAPE = c.getColumnIndex(servidor.COLUM_SIAPE);
                int indexFUNCAO = c.getColumnIndex(servidor.COLUM_FUNCAO);


                servidor.setNome(c.getString(indexName));
                servidor.setCpf(c.getString(indexCpf));
                servidor.setEmail(c.getString(indexEmail));
                servidor.setUrlFoto(c.getString(indexFoto));
                servidor.setFuncao(c.getString(indexFUNCAO));
                servidor.setPasep(c.getString(indexPASEP));
                servidor.setSiape(c.getString(indexSIAPE));
                servidor.setType(c.getInt(indexTipo));


                Log.i("rawQueryAluno", "aluno: " + servidor.getNome() + " was return ");

                servidorList.add(servidor);
            }

            c.close();
        } catch (SQLiteException erro) {

            Log.i("rawQueryAluno", "erro " + erro.getMessage());

        }


        return servidorList;

    }

    public Servidor rawServidor(String ID) {

        Servidor servidor = new Servidor();

        try {
            String sql = "select u." + servidor.COLUM_CPF + ", u." + servidor.COLUM_NOME +
                    ", u." + servidor.COLUM_EMAIL + ", u." + servidor.COLUM_FOTO + ", u." + servidor.COLUM_SENHA + ", u." + servidor.COLUM_TIPO +
                    ",s." + servidor.COLUM_FUNCAO + ", s." + servidor.COLUM_SIAPE + ",s." + servidor.COLUM_PASEP
                    + " from usuario as u inner join servidor as s where u.usuario_cpf = ?";

            Cursor c = mDataBase.rawQuery(sql, new String[]{ID});

            Log.i("rawQuerySERIDOR", "count " + c.getCount());
            Log.i("rawQuerySERVIDOR", "sql " + sql);


            c.moveToFirst();

            servidor = new Servidor();

            int indexName = c.getColumnIndex(servidor.COLUM_NOME);
            int indexCpf = c.getColumnIndex(servidor.COLUM_CPF);
            int indexEmail = c.getColumnIndex(servidor.COLUM_EMAIL);
            int indexFoto = c.getColumnIndex(servidor.COLUM_FOTO);
            int indexTipo = c.getColumnIndex(servidor.COLUM_TIPO);
            int indexPASEP = c.getColumnIndex(servidor.COLUM_PASEP);
            int indexSIAPE = c.getColumnIndex(servidor.COLUM_SIAPE);
            int indexFUNCAO = c.getColumnIndex(servidor.COLUM_FUNCAO);


            servidor.setNome(c.getString(indexName));
            servidor.setCpf(c.getString(indexCpf));
            servidor.setEmail(c.getString(indexEmail));
            servidor.setUrlFoto(c.getString(indexFoto));
            servidor.setFuncao(c.getString(indexFUNCAO));
            servidor.setPasep(c.getString(indexPASEP));
            servidor.setSiape(c.getString(indexSIAPE));
            servidor.setType(c.getInt(indexTipo));


            Log.i("rawQuerySERIVIDOR", "aluno: " + servidor.getNome() + " was return ");

            c.close();
            return servidor;
        } catch (SQLiteException erro) {

            Log.i("rawQuerySERIVIDOR", "erro " + erro.getMessage());
            return null;
        }


    }

    public Professor rawProfessor(String ID) {

        Professor professor = new Professor();

        try {
            String sql = "select u." + professor.COLUM_CPF + ", u." + professor.COLUM_NOME +
                    ", u." + professor.COLUM_EMAIL + ", u." + professor.COLUM_FOTO + ", u." + professor.COLUM_SENHA + ", u." + professor.COLUM_TIPO +
                    ", p." + professor.COLUM_SIAPE + ",p." + professor.COLUM_PASEP
                    + " from usuario as u inner join professor as s where u.usuario_cpf = ?";

            Cursor c = mDataBase.rawQuery(sql, new String[]{ID});

            Log.i("rawQueryPROFESSOR", "count " + c.getCount());
            Log.i("rawQueryPROFESSOR", "sql " + sql);

            c.moveToFirst();


            int indexName = c.getColumnIndex(professor.COLUM_NOME);
            int indexCpf = c.getColumnIndex(professor.COLUM_CPF);
            int indexEmail = c.getColumnIndex(professor.COLUM_EMAIL);
            int indexFoto = c.getColumnIndex(professor.COLUM_FOTO);
            int indexTipo = c.getColumnIndex(professor.COLUM_TIPO);
            int indexPASEP = c.getColumnIndex(professor.COLUM_PASEP);
            int indexSIAPE = c.getColumnIndex(professor.COLUM_SIAPE);


            professor.setNome(c.getString(indexName));
            professor.setCpf(c.getString(indexCpf));
            professor.setEmail(c.getString(indexEmail));
            professor.setUrlFoto(c.getString(indexFoto));
            professor.setPasep(c.getString(indexPASEP));
            professor.setSiape(c.getString(indexSIAPE));
            professor.setType(c.getInt(indexTipo));


            Log.i("rawQueryProfessor", "Professor: " + professor.getNome() + " was return ");


            return professor;

        } catch (SQLiteException erro) {

            Log.i("rawQueryProfessor", "erro " + erro.getMessage());

            return null;
        }


    }

    public List<Professor> rawProfessor() {

        List<Professor> professorList = new ArrayList<>();


        Professor professor = new Professor();

        try {
            String sql = "select u." + professor.COLUM_CPF + ", u." + professor.COLUM_NOME +
                    ", u." + professor.COLUM_EMAIL + ", u." + professor.COLUM_FOTO + ", u." + professor.COLUM_SENHA + ", u." + professor.COLUM_TIPO +
                    ", p." + professor.COLUM_SIAPE + ",p." + professor.COLUM_PASEP
                    + " from usuario as u inner join professor as p on u.usuario_cpf = p.usuario_cpf order by u.usuario_nome";

            Cursor c = mDataBase.rawQuery(sql, null);

            Log.i("rawQuerySERIDOR", "count " + c.getCount());
            Log.i("rawQuerySERVIDOR", "sql " + sql);

            while (c.moveToNext()) {

                professor = new Professor();


                int indexName = c.getColumnIndex(professor.COLUM_NOME);
                int indexCpf = c.getColumnIndex(professor.COLUM_CPF);
                int indexEmail = c.getColumnIndex(professor.COLUM_EMAIL);
                int indexFoto = c.getColumnIndex(professor.COLUM_FOTO);
                int indexTipo = c.getColumnIndex(professor.COLUM_TIPO);
                int indexPASEP = c.getColumnIndex(professor.COLUM_PASEP);
                int indexSIAPE = c.getColumnIndex(professor.COLUM_SIAPE);


                professor.setNome(c.getString(indexName));
                professor.setCpf(c.getString(indexCpf));
                professor.setEmail(c.getString(indexEmail));
                professor.setUrlFoto(c.getString(indexFoto));
                professor.setPasep(c.getString(indexPASEP));
                professor.setSiape(c.getString(indexSIAPE));
                professor.setType(c.getInt(indexTipo));


                Log.i("rawQueryProfessor", "Professor: " + professor.getNome() + " was return ");


                professorList.add(professor);


            }


            return professorList;

        } catch (SQLiteException erro) {

            Log.i("rawQueryAluno", "erro " + erro.getMessage());

            return null;
        }


    }

    public Guarda rawGuarda(String ID) {

        Guarda guarda = new Guarda();

        try {

            Cursor c = mDataBase.query(true, mScripts.TAB_GUARDA, new String[]{guarda.COLUMN_CPF, guarda.COLUMN_FOTO, guarda.COLUMN_NOME, guarda.COLUMN_PASEP
                    , guarda.COLUMN_RG, guarda.COLUMN_SENHA}, guarda.COLUMN_CPF + "=" + ID, null, null, null, null, null);

            if (c.getCount() > 0) {

                c.moveToFirst();

                guarda.setCpf(c.getString(0));
                guarda.setFoto(c.getString(1));
                guarda.setNome(c.getString(2));
                guarda.setPasep(c.getString(3));
                guarda.setRg(c.getString(4));
                guarda.setSenha(c.getString(5));


                return guarda;


            }


        } catch (SQLiteException erro) {

            Log.i("rawGuarda", erro.getMessage());

            return null;
        }


        return guarda;
    }

    public List<Guarda> rawGuarda() {
        List<Guarda> guardas = new ArrayList<>();
        Guarda guarda = new Guarda();

        try {

            String sql = "select * from guardas";

            Cursor c = mDataBase.rawQuery(sql, null);

            int indexNome = c.getColumnIndex(guarda.COLUMN_NOME);
            int indexCpf = c.getColumnIndex(guarda.COLUMN_CPF);
            int indexRg = c.getColumnIndex(guarda.COLUMN_RG);
            int indexSenha = c.getColumnIndex(guarda.COLUMN_SENHA);
            int indexPasep = c.getColumnIndex(guarda.COLUMN_PASEP);
            int indexFoto = c.getColumnIndex(guarda.COLUMN_FOTO);


            while (c.moveToNext()) {

                guarda = new Guarda();

                guarda.setSenha(c.getString(indexSenha));
                guarda.setRg(c.getString(indexRg));
                guarda.setPasep(c.getString(indexPasep));
                guarda.setNome(c.getString(indexNome));
                guarda.setFoto(c.getString(indexFoto));

                guardas.add(guarda);

            }


            return guardas;


        } catch (SQLiteException err) {

            return null;
        }


    }



    public List<Lab> rawLab() {

        try {
            Lab lab = new Lab();
            String sql = "select * from lab order by lab_numero asc";
            List<Lab> labList = new ArrayList<>();

            Cursor cursor = mDataBase.rawQuery(sql, null);

            int indexNumero = cursor.getColumnIndex(lab.COLUM_LAB_NUMERO);
            int indexBloco = cursor.getColumnIndex(lab.COLUM_BLOCO);
            int indexDesc = cursor.getColumnIndex(lab.COLUM_DESCRICAO);
            int indexPav = cursor.getColumnIndex(lab.COLUM_PAVIMENTO);
            int indexOn = cursor.getColumnIndex(lab.COLUM_ON);


            while (cursor.moveToNext()) {

                lab = new Lab();

                lab.setBloco(cursor.getString(indexBloco));
                lab.setDescricao(cursor.getString(indexDesc));
                lab.setNumero(cursor.getInt(indexNumero));
                lab.setOn(cursor.getInt(indexOn));
                lab.setPavimento(cursor.getString(indexPav));

                labList.add(lab);

            }

            return labList;
        } catch (SQLiteException erro) {


            Log.i("rawLab", erro.getMessage());

        }

        return null;
    }


    public Lab findLab(String num){

        String sql = "select * from lab where lab_numero ="+num;
        Lab lab = new Lab();
        Cursor cursor = mDataBase.rawQuery(sql, null);

        if(cursor.getCount()>0){

            int indexNumero = cursor.getColumnIndex(lab.COLUM_LAB_NUMERO);
            int indexBloco = cursor.getColumnIndex(lab.COLUM_BLOCO);
            int indexDesc = cursor.getColumnIndex(lab.COLUM_DESCRICAO);
            int indexPav = cursor.getColumnIndex(lab.COLUM_PAVIMENTO);
            int indexOn = cursor.getColumnIndex(lab.COLUM_ON);

            cursor.moveToFirst();

            lab.setBloco(cursor.getString(indexBloco));
            lab.setDescricao(cursor.getString(indexDesc));
            lab.setNumero(cursor.getInt(indexNumero));
            lab.setOn(cursor.getInt(indexOn));
            lab.setPavimento(cursor.getString(indexPav));

            return  lab;

        }

        return  null;



    }

    //methods  to verefic if exists users

    public boolean isUser(String cpf) {

        String sql = "select * from usuario where usuario_cpf = ?";
        String where[] = {cpf};
        Usuario user = new Usuario();
        Cursor c = mDataBase.rawQuery(sql, where);

        if (c.getCount() > 0) {
            c.moveToNext();
            user.setNome(c.getString(c.getColumnIndex("usuario_nome")));
            return true;
        }


        return false;
    }


    public Usuario isUser(String cpf, boolean retorno) {

        String sql = "select * from usuario where usuario_cpf = ?";
        String where[] = {cpf};
        Usuario user = new Usuario();
        Cursor c = mDataBase.rawQuery(sql, where);

        if (c.getCount() > 0) {
            c.moveToNext();
            user.setNome(c.getString(c.getColumnIndex("usuario_nome")));

        }


        return user;
    }


    public boolean isUser(String cpf, int flag) {

        /*
        * flag = 1 eh aluno
        * flag = 1 eh professor
        * flag = 3 eh servidor
        * */

        String sql = "select * from usuario where usuario_cpf = ? and usuario_tipo= ?";
        String where[] = {cpf, String.valueOf(flag)};

        Cursor c = mDataBase.rawQuery(sql, where);

        if (c.getCount() > 0) {

            return true;

        }


        return false;
    }

    public boolean isLab(int labNumber) {

        String sql = "select * from lab where lab_numero = ?";
        String where[] = {String.valueOf(labNumber)};

        Cursor c = mDataBase.rawQuery(sql, where);

        if (c.getCount() > 0) {

            return true;

        }


        return false;

    }

    public boolean isGuarda(String cpf, String senha) {

        String sql = "select * from guarda  where g_cpf = ? and g_senha = ?";
        String where[] = {cpf, senha};

        Cursor c = mDataBase.rawQuery(sql, where);

        if (c.getCount() > 0) {

            return true;

        }


        return false;

    }





    public boolean loginAdm(String cpf, String siape) {

        try {
            Adm adm = new Adm();

            String sql = "select * from adm where " + adm.COLUM_CPF + " = ?" + "and " + adm.COLUM_SIAPE + " = ? ";
            String arg[] = {cpf, siape};

            Cursor c = mDataBase.rawQuery(sql, arg);

            if (c.getCount() > 0) {

                return true;
            }
        } catch (SQLiteException erro) {

            Log.i("loginAdm", erro.getMessage());
            return false;

        }


        return false;
    }


    public void count(String sql) {

        Cursor c = mDataBase.rawQuery(sql, null);
        Toast.makeText(mContext, "Count " + c.getCount(), Toast.LENGTH_LONG).show();


    }


    public boolean insertUsuarioPertenceLab(String cpfUser, int Lab) {

        try {

            ContentValues contentValues = new ContentValues();
            contentValues.put("usuario_cpf", cpfUser);
            contentValues.put("lab_numero", Lab);

            mDataBase.insert(mScripts.TAB_USUARIO_PERTENCE_LAB, null, contentValues);


            return true;
        } catch (SQLiteException erro) {

            return false;
        }

    }

    public List<Aluno> ImediateSeach(String nome) {
        List<Aluno> mList = new ArrayList<>();
        Aluno aluno = new Aluno();

        String sql = "select u." + aluno.COLUM_CPF + ", u." + aluno.COLUM_NOME +
                ", u." + aluno.COLUM_EMAIL + ", u." + aluno.COLUM_FOTO + ", u." + aluno.COLUM_SENHA + ", u." + aluno.COLUM_TIPO +
                ", a." + aluno.COLUM_MATRICULA + ", a.aluno_curso" + ",a." + aluno.COLUM_LAB_NUMERO + ", a." + aluno.COLUM_ANO +
                " from usuario as u inner join aluno as a on u.usuario_cpf = a.usuario_cpf and u.usuario_nome like  '" + nome + "%' order by u.usuario_nome";

        String whereArg[] = new String[]{nome};

        Cursor c = mDataBase.rawQuery(sql, null);

        Log.i("ImediateSeach", "iniciou " + c.getCount());
        Log.i("ImediateSeach", "sql " + sql);

        try {
            while (c.moveToNext()) {

                aluno = new Aluno();

                int indexName = c.getColumnIndex(aluno.COLUM_NOME);
                int indexCpf = c.getColumnIndex(aluno.COLUM_CPF);
                int indexEmail = c.getColumnIndex(aluno.COLUM_EMAIL);
                int indexFoto = c.getColumnIndex(aluno.COLUM_FOTO);
                int indexMatricula = c.getColumnIndex(aluno.COLUM_MATRICULA);
                int indexLAb = c.getColumnIndex(aluno.COLUM_LAB_NUMERO);
                int indexAno = c.getColumnIndex(aluno.COLUM_ANO);
                int indexCurso = c.getColumnIndex(aluno.COLUM_CURSO);
                int indexSenha = c.getColumnIndex(aluno.COLUM_SENHA);
                int indexTipo = c.getColumnIndex(aluno.COLUM_TIPO);


                aluno.setNome(c.getString(indexName));
                aluno.setCpf(c.getString(indexCpf));
                aluno.setCurso(c.getString(indexCurso));
                aluno.setEmail(c.getString(indexEmail));
                aluno.setAnoCurso(c.getString(indexAno));
                aluno.setUrlFoto(c.getString(indexFoto));
                aluno.setMatricula(c.getString(indexMatricula));
                aluno.setLab_numero(c.getInt(indexLAb));
                aluno.setSenha(c.getString(indexSenha));
                aluno.setType(c.getInt(indexTipo));


                Log.i("rawQueryAluno", "aluno Curso: " + aluno.getCurso() + " was return ");

                mList.add(aluno);
            }


        } catch (SQLiteException erro) {

            Log.i("ImedoateSeach", "erro " + erro.getMessage());

        }


        return mList;

    }


    public List<Professor> ImediateSeachProf(String nome) {


        List<Professor> professorList = new ArrayList<>();
        Professor professor = new Professor();

        String sql = "select u." + professor.COLUM_CPF + ", u." + professor.COLUM_NOME +
                ", u." + professor.COLUM_EMAIL + ", u." + professor.COLUM_FOTO + ", u." + professor.COLUM_SENHA + ", u." + professor.COLUM_TIPO +
                ", p." + professor.COLUM_SIAPE + ",p." + professor.COLUM_PASEP
                + " from usuario as u inner join professor as s where u.usuario_cpf = p.usuario_cpf and u.usuario_nome like  '" + nome + "%' order by u.usuario_nome";


        try {

            Cursor c = mDataBase.rawQuery(sql, null);

            Log.i("ImediateSeach", "iniciou " + c.getCount());
            Log.i("ImediateSeach", "sql " + sql);

            while (c.moveToNext()) {

                professor = new Professor();


                int indexName = c.getColumnIndex(professor.COLUM_NOME);
                int indexCpf = c.getColumnIndex(professor.COLUM_CPF);
                int indexEmail = c.getColumnIndex(professor.COLUM_EMAIL);
                int indexFoto = c.getColumnIndex(professor.COLUM_FOTO);
                int indexTipo = c.getColumnIndex(professor.COLUM_TIPO);
                int indexPASEP = c.getColumnIndex(professor.COLUM_PASEP);
                int indexSIAPE = c.getColumnIndex(professor.COLUM_SIAPE);


                professor.setNome(c.getString(indexName));
                professor.setCpf(c.getString(indexCpf));
                professor.setEmail(c.getString(indexEmail));
                professor.setUrlFoto(c.getString(indexFoto));
                professor.setPasep(c.getString(indexPASEP));
                professor.setSiape(c.getString(indexSIAPE));
                professor.setType(c.getInt(indexTipo));


                Log.i("rawQueryProfessor", "Professor: " + professor.getNome() + " was return ");


                professorList.add(professor);


            }


        } catch (SQLiteException erro) {

            Log.i("rawQueryAluno", "erro " + erro.getMessage());


        }
        return professorList;
    }

    public List<Servidor> ImediateSeachServidor(String nome) {


        Servidor servidor = new Servidor();
        List<Servidor> servidorList = new ArrayList<>();


        try {
            String sql = "select u." + servidor.COLUM_CPF + ", u." + servidor.COLUM_NOME +
                    ", u." + servidor.COLUM_EMAIL + ", u." + servidor.COLUM_FOTO + ", u." + servidor.COLUM_SENHA + ", u." + servidor.COLUM_TIPO +
                    ",s." + servidor.COLUM_FUNCAO + ", s." + servidor.COLUM_SIAPE + ",s." + servidor.COLUM_PASEP
                    + " from usuario as u inner join servidor as s where u.usuario_cpf = s.usuario_cpf and u.usuario_nome like  '" + nome + "%'order by u.usuario_nome";

            Cursor c = mDataBase.rawQuery(sql, null);

            Log.i("rawQuerySERIDOR", "count " + c.getCount());
            Log.i("rawQuerySERVIDOR", "sql " + sql);

            while (c.moveToNext()) {

                servidor = new Servidor();

                int indexName = c.getColumnIndex(servidor.COLUM_NOME);
                int indexCpf = c.getColumnIndex(servidor.COLUM_CPF);
                int indexEmail = c.getColumnIndex(servidor.COLUM_EMAIL);
                int indexFoto = c.getColumnIndex(servidor.COLUM_FOTO);
                int indexTipo = c.getColumnIndex(servidor.COLUM_TIPO);
                int indexPASEP = c.getColumnIndex(servidor.COLUM_PASEP);
                int indexSIAPE = c.getColumnIndex(servidor.COLUM_SIAPE);
                int indexFUNCAO = c.getColumnIndex(servidor.COLUM_FUNCAO);


                servidor.setNome(c.getString(indexName));
                servidor.setCpf(c.getString(indexCpf));
                servidor.setEmail(c.getString(indexEmail));
                servidor.setUrlFoto(c.getString(indexFoto));
                servidor.setFuncao(c.getString(indexFUNCAO));
                servidor.setPasep(c.getString(indexPASEP));
                servidor.setSiape(c.getString(indexSIAPE));
                servidor.setType(c.getInt(indexTipo));


                Log.i("rawQueryAluno", "aluno: " + servidor.getNome() + " was return ");

                servidorList.add(servidor);
            }


        } catch (SQLiteException erro) {

            Log.i("rawQueryAluno", "erro " + erro.getMessage());

        }

        return servidorList;


    }


    public List<Check> testCk(String data, boolean isChekin, String lab){

        String sql;

        List<Check> checks = new ArrayList<>();

        if(isChekin){

            sql = "SELECT * from checkin"+
                    " where strftime('%Y-%m-%d', data_check) = strftime('%Y-%m-%d','"+data+"') and lab_numero = "+lab;


        }

        else{

            sql = "SELECT * from  checkout" +
                    " where strftime('%Y-%m-%d', data_check) = strftime('%Y-%m-%d','"+data+"') and lab_numero = "+lab;

        }


        Cursor x = mDataBase.rawQuery(sql, null);


        while(x.moveToNext() && x!=null){

            Check mCheck = new Check();
            mCheck.setData(x.getString(x.getColumnIndex("data_check")));
            mCheck.setGuarda(x.getString(x.getColumnIndex("guarda_g_cpf")));
            mCheck.setLabNumero(x.getString(x.getColumnIndex("lab_numero")));
            mCheck.setUsuario_cpf(x.getString(x.getColumnIndex("usuario_cpf")));

            Log.i("Database", "getGuarda>>>: " + mCheck.getGuarda());



            Usuario u = isUser(mCheck.getUsuario_cpf(), true);
            mCheck.setUsuario(u.getNome());
            Log.i("Database", "U: " + mCheck.getUsuario());


            Guarda g = rawGuarda(mCheck.getGuarda());
            mCheck.setGuarda(g.getNome());

            Log.i("Database", "U: " + mCheck.getGuarda());

            Log.i("Database", "Lab Numero: " + x.getString(x.getColumnIndex("lab_numero")));
            Log.i("Database", "Lab Guarda: " + x.getString(x.getColumnIndex("guarda_g_cpf")));
            Log.i("Database", "Lab Usuario: " + x.getString(x.getColumnIndex("usuario_cpf")));



            checks.add(mCheck);

        }


        return checks;

    }


    public List<Check> getCheckin(String dia, String Mes, String Ano, String lab,  String date) {
        List<Check> mList = new ArrayList<>();
        Check mCheck = new Check();

        String sql = "select * from " + mScripts.TAB_CHECK_IN +  " where data_entrada = '"+date+"' or data_entrada = '"+date+"'";

        //+Ano+"-"+Mes+"-"+dia+"'"+ "or  data_entrada = '"+Ano+"-"+Mes+"-"+dia+"'"; //where day (data_entrada)= " + dia + " and month ( data_entrada ) = " + Mes + " and year (data_entrada) =" + Ano + " and lab_numero = " + lab;

        Cursor c = mDataBase.rawQuery(sql, null);

        int indexHora = c.getColumnIndex("data_entrada");
        int indexUser = c.getColumnIndex("usuario_cpf");
        int indexGuarda = c.getColumnIndex("guarda_g_cpf");
        int indexLab = c.getColumnIndex("lab_numero");


        while (c.moveToNext()) {

            mCheck.setData(c.getString(indexHora));
            mCheck.setGuarda(c.getString(indexGuarda));
            mCheck.setUsuario(c.getString(indexUser));
            mCheck.setLabNumero(c.getString(indexLab));

            mList.add(mCheck);

        }


        return mList;
    }


    public List<Check> getCheckout(String dia, String Mes, String Ano, String lab) {
        List<Check> mList = new ArrayList<>();
        Check mCheck = new Check();

        String sql = "select * from " + mScripts.TAB_CHECK_OUT+ " where DAY (data_entrada)= " + dia + " and MONTH( data_entrada ) = " + Mes + " and YEAR (data_entrada) =" + Ano + " and lab_numero = " + lab;

        Cursor c = mDataBase.rawQuery(sql, null);

        int indexHora = c.getColumnIndex("data_saida");
        int indexUser = c.getColumnIndex("usuario_cpf");
        int indexGuarda = c.getColumnIndex("guarda_g_cpf");
        int indexLab = c.getColumnIndex("lab_numero");


        while (c.moveToNext()) {

            mCheck.setData(c.getString(indexHora));
            mCheck.setGuarda(c.getString(indexGuarda));
            mCheck.setUsuario(c.getString(indexUser));
            mCheck.setLabNumero(c.getString(indexLab));

            mList.add(mCheck);

        }


        return mList;
    }

    public void allCheck(){

        String sql = "select * from checkin";

        Cursor c = mDataBase.rawQuery(sql, null);

         while (c.moveToFirst()){



         }

    }



    public List<Lab> labsOn(String type) {
        List<Lab> labList = new ArrayList<>();
        Lab lab = new Lab();
        String sql = "select * from " + mScripts.TAB_LAB + " where " + lab.COLUM_ON + "= "+type;

        Cursor cursor = mDataBase.rawQuery(sql, null);

        int indexNumero = cursor.getColumnIndex(lab.COLUM_LAB_NUMERO);
        int indexBloco = cursor.getColumnIndex(lab.COLUM_BLOCO);
        int indexDesc = cursor.getColumnIndex(lab.COLUM_DESCRICAO);
        int indexPav = cursor.getColumnIndex(lab.COLUM_PAVIMENTO);
        int indexOn = cursor.getColumnIndex(lab.COLUM_ON);


        while (cursor.moveToNext()) {

            lab = new Lab();

            lab.setBloco(cursor.getString(indexBloco));
            lab.setDescricao(cursor.getString(indexDesc));
            lab.setNumero(cursor.getInt(indexNumero));
            lab.setOn(cursor.getInt(indexOn));
            lab.setPavimento(cursor.getString(indexPav));

            labList.add(lab);

        }

        return labList;

    }


    public List<Usuario> getUserToLab(String numLab){
        Usuario u = new Usuario();
        List<Usuario> userlist = new ArrayList<>();


        String sql = "select u.usuario_nome, u.usuario_cpf, u.usuario_senha, h.usuario_cpf, h.lab_numero from " +
                "usuario as u inner join  usuario_pertence_lab as h where  "+
                "u.usuario_cpf = h.usuario_cpf and h.lab_numero = "+numLab;

        Cursor c = mDataBase.rawQuery(sql, null);
        int indexSenha = c.getColumnIndex(u.COLUM_SENHA);
        int indexName = c.getColumnIndex(u.COLUM_NOME);
        int indexCpf = c.getColumnIndex(u.COLUM_CPF);


        while (c.moveToNext()){


            u.setCpf(c.getString(indexCpf));
            u.setSenha(c.getString(indexSenha));
            u.setNome(c.getString(indexName));

            userlist.add(u);
        }


        return  userlist;
    }


    public boolean logar(String cpf, String senha){

        String sql = "select usuario_cpf, usuario_senha from usuario where usuario_cpf = "+cpf+
                " and usuario_senha = "+senha;

        Cursor c = mDataBase.rawQuery(sql, null);
        if(c.getCount()>0){

            return  true;
        }

        else{

            return  false;
        }

    }

    public boolean findUserToLab(String cpf, String lab){

        String sql = "select usuario_cpf, lab_numero from usuario_pertence_lab where usuario_cpf = "+cpf+
                " and lab_numero = "+lab;

        Cursor c = mDataBase.rawQuery(sql, null);
        if(c.getCount()>0){

            return  true;
        }

        else{

            return  false;
        }

    }

    public boolean findlabtoaluno(String cpf, String lab){

    String sql = "select * from aluno where usuario_cpf ="+cpf+" and lab_numero ="+lab;

    Cursor c = mDataBase.rawQuery(sql, null);
    if(c.getCount()>0){

        return  true;
    }

    else{

        return  false;
    }

}

    public String dataNow(){



        String sql = "select datetime('now'), datetime('now','localtime') as d;";
        Cursor c = mDataBase.rawQuery(sql, null);
        String dataAtual="";
        if(c.getCount()>0 && c!=null){
            c.moveToNext();
            dataAtual = c.getString(c.getColumnIndex("d"));
            Log.i("database", "data "+dataAtual);

        }


         return dataAtual;


    }


    public boolean Check(String cpf, String lab, String guardaCpf, boolean isIn){

        String data  = dataNow();
        String table = isIn == true ? mScripts.TAB_CHECK_IN : mScripts.TAB_CHECK_OUT;
        Log.i("database",  "table: "+table);

        boolean response= false;
        try{


            ContentValues values = new ContentValues();
            values.put("usuario_cpf", cpf);
            values.put("lab_numero", lab);
            values.put("guarda_g_cpf", guardaCpf);
            values.put("data_check", data);
            Log.i("check", "data now :" + data);
          long i=  mDataBase.insert(table, null, values);

            Log.i("check", "id :"+guardaCpf+"LONG ID: "+i);
            response=  true;
        }
        catch (SQLiteException erro){
            Log.i("check ", erro.getMessage());

        }


        return response;

    }


    public List<Aluno> getAlunoToLab(String lab){

        List<Aluno> alunosList = new ArrayList<>();
        Aluno aluno = new Aluno();

        try {
            String sql = "select * from aluno inner join  usuario as u on u.usuario_cpf = aluno.usuario_cpf and  "+aluno.COLUM_LAB_NUMERO+" ="+lab;
            Cursor c = mDataBase.rawQuery(sql, null);


            int indexName = c.getColumnIndex(aluno.COLUM_NOME);
            int indexCpf = c.getColumnIndex(aluno.COLUM_CPF);
            int indexEmail = c.getColumnIndex(aluno.COLUM_EMAIL);

            int indexMatricula = c.getColumnIndex(aluno.COLUM_MATRICULA);
            int indexLAb = c.getColumnIndex(aluno.COLUM_LAB_NUMERO);

            int indexSenha = c.getColumnIndex(aluno.COLUM_SENHA);



            while (c.moveToNext()) {

                aluno = new Aluno();

                aluno.setNome(c.getString(indexName));
                aluno.setCpf(c.getString(indexCpf));

                aluno.setEmail(c.getString(indexEmail));

                aluno.setMatricula(c.getString(indexMatricula));
                aluno.setLab_numero(c.getInt(indexLAb));
                aluno.setSenha(c.getString(indexSenha));



                Log.i("rawQueryAluno", "aluno Curso: " + aluno.getCurso() + " was return ");

                alunosList.add(aluno);
            }


        } catch (SQLiteException erro) {

            Log.i("rawQueryAluno", "erro " + erro.getMessage());

        }


        return alunosList;

    }



    public List<Check> getCheck(String data, String lab, boolean isCheckin, boolean isNow){

        List<Check> mListCheck = new ArrayList<>();

        //strftime('%m', data_entrada) = strftime('%m', date('2015-08-06 01:46:46')) seach for mount
        //strftime('%Y-%m-%d ', data_entrada) seach for ano-mes-dia.

        String sql = "select b.usuario_nome, a.data_check, a.usuario_cpf,a.guarda_g_cpf, a.lab_numero  from "+ (isCheckin ? mScripts.TAB_CHECK_IN : mScripts.TAB_CHECK_OUT)+ " as a inner join " +
                " usuario as b where strftime('%Y-%m-%d', data_check) = strftime('%Y-%m-%d', date('"+(isNow? "now": data)+"')) and a.lab_numero = "+lab+
                " and a.usuario_cpf = b.usuario_cpf";

        Log.i("check", sql);

        try {
            Cursor c = mDataBase.rawQuery(sql, null);
           // Toast.makeText(mContext, "Size "+c.getCount(), Toast.LENGTH_LONG).show();

            int indexHora = c.getColumnIndex("data_check");
            int indexUser = c.getColumnIndex("usuario_cpf");
            int indexGuarda = c.getColumnIndex("guarda_g_cpf");
            int indexLab = c.getColumnIndex("lab_numero");
            int indexNome = c.getColumnIndex("usuario_nome");


            while (c.moveToNext()) {

                Check mChecks = new Check();

                mChecks.setLabNumero(c.getString(indexLab));
                mChecks.setUsuario(c.getString(indexNome));
                mChecks.setData(c.getString(indexHora));
                mChecks.setGuarda(c.getString(indexGuarda));
                mChecks.setUsuario_cpf(c.getString(indexUser));

                mListCheck.add(mChecks);


                Log.i("check", mChecks.getUsuario());
            }

        }
        catch(SQLiteException e){

            Log.i("check", e.getMessage());


        }


        return  mListCheck;
    }



    public void close() {

        if (mDataBase != null) {

            mDataBase.close();
        }

    }



}

