package com.developer.ruddbeltrao.keycontroller.domain;

/**
 * Criado  por Rudda Beltrao em 20/07/2015.
 */
public class Guarda {

    public static  final String COLUMN_CPF = "g_cpf";
    public static  final String COLUMN_NOME = "g_nome";
    public static  final String COLUMN_PASEP = "g_pasep";
    public static  final String COLUMN_FOTO = "g_foto";
    public static  final String COLUMN_RG = "g_rg";
    public static  final String COLUMN_SENHA = "g_senha";


    private String cpf;
    private String nome;
    private String pasep;
    private String foto;
    private  String rg;
    private String senha;




    public Guarda() {
    }


    public Guarda(String cpf, String nome, String pasep, String foto, String rg, String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.pasep = pasep;
        this.foto = foto;
        this.rg = rg;
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPasep() {
        return pasep;
    }

    public void setPasep(String pasep) {
        this.pasep = pasep;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
