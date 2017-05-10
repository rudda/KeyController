package com.developer.ruddbeltrao.keycontroller.domain;

import android.graphics.Bitmap;

import java.net.URL;

/**
 * Created by Rudda Beltrao on 17/07/2015.
 */
public class Usuario {


    //todos os nomes da tabela usuario no banco de dados
    public final String  COLUM_NOME = "usuario_nome";
    public final String COLUM_CPF = "usuario_cpf";
    public final String COLUM_SENHA = "usuario_senha";
    public final String COLUM_FOTO="usuario_foto";
    public final String COLUM_EMAIL="usuario_email";
    public final String COLUM_TIPO= "usuario_tipo";
    public final String COLUM_USER [] = {"usuario_nome","usuario_cpf", "usuario_senha", "usuario_foto", "usuario_email",  "usuario_tipo"};


    private String nome;
    private String cpf;
    private String rg;
    private String email;
    private String senha="";
    private String urlFoto;
    private int type;

    public Usuario(String nome, String cpf, String rg, String email, String senha, String urlFoto, int type) {
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.email = email;
        this.senha = senha;
        this.urlFoto = urlFoto;
        this.type = type;
    }

    public Usuario() {
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
