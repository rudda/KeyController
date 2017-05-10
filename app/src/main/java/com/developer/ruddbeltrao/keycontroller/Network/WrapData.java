package com.developer.ruddbeltrao.keycontroller.Network;

import com.developer.ruddbeltrao.keycontroller.domain.Adm;
import com.developer.ruddbeltrao.keycontroller.domain.Aluno;
import com.developer.ruddbeltrao.keycontroller.domain.Check;
import com.developer.ruddbeltrao.keycontroller.domain.Guarda;
import com.developer.ruddbeltrao.keycontroller.domain.Professor;
import com.developer.ruddbeltrao.keycontroller.domain.Servidor;

import java.util.List;

/**
 * Created by Dev Aux on 08/01/2016.
 */
public class WrapData {

    //usuarios
    private Aluno mAluno;
    private Servidor mServidor;
    private Adm mAdm;
    private Guarda mGuarda;
    private Professor mProfessor;

    //lista de usuarios
    private List<Aluno> mListAluno;
    private List<Servidor> mListServidor;
    private List<Adm> mListAdm;
    private List<Guarda> mListGuarda;
    private List<Professor> mListProfessor;

    //checks lists
    private List<Check> checkList;


    //nome das tabelas
    public static final String TAB_ALUNO= "aluno";
    public static final String TAB_PROFESSOR = "professor";
    public static final String TAB_ADM= "Adm";
    public static final String TAB_USUARIO= "usuario";
    public static final String TAB_LAB = "lab";
    public static final String TAB_USUARIO_HAS_LAB = "usuario_has_lab";
    public static final String TAB_USUARIO_PERTENCE_LAB= "usuario_pertence_lab";
    public static final String TAB_SERVIDOR= "servidor";
    public static final String TAB_GUARDA= "guarda";
    public static final String TAB_CHECK_IN= "checkin";
    public static final String TAB_CHECK_OUT= "checkout";




}

