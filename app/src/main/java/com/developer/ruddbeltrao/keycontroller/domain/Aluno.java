package com.developer.ruddbeltrao.keycontroller.domain;

import android.graphics.Bitmap;

/**
 * Criado por Rudda Beltrao em 17/07/2015.
 */
public class Aluno extends Usuario{


    //nome de todas as coluna da tabela usuario.

    public final String COLUM_MATRICULA= "aluno_matricula";
    public final String COLUM_CURSO = "aluno_curso";
    public final String COLUM_ANO= "aluno_ano_curso";
    public final String COLUM_LAB_NUMERO =  "lab_numero";


    private String curso;
    private String anoCurso;
    private String matricula;
    private int lab_numero;


    public Aluno(String nome, String cpf, String rg, String email, String senha, String urlFoto, int type, String curso, String anoCurso, String matricula, int lab_numero) {
        super(nome, cpf, rg, email, senha, urlFoto, type);
        this.curso = curso;
        this.anoCurso = anoCurso;
        this.matricula = matricula;
        this.lab_numero = lab_numero;
    }

    public Aluno() {
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getAnoCurso() {
        return anoCurso;
    }

    public void setAnoCurso(String anoCurso) {
        this.anoCurso = anoCurso;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getLab_numero() {
        return lab_numero;
    }

    public void setLab_numero(int lab_numero) {
        this.lab_numero = lab_numero;
    }
}
