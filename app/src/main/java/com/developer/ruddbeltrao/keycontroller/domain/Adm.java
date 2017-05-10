package com.developer.ruddbeltrao.keycontroller.domain;

import java.net.URL;

/**
 * Created by Rudda Beltrao on 17/07/2015.
 */
public class Adm {


    public final String COLUM_ID = "idAdm";
    public final String COLUM_NOME= "nomeAdm";
    public final String COLUM_CPF= "cpfAdm";
    public final String COLUM_SIAPE= "siapeAdm";
    public final String COLUM_SENHA = "senha";



    private long id;
    private String nome;
    private String cpf;
    private String siape;
    private URL urlFoto;
    private String senha;



    public Adm(long id, String nome, String cpf, String siape, String senha, URL urlFoto) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.siape = siape;
        this.senha = senha;
        this.urlFoto = urlFoto;
    }

    public Adm() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getSiape() {
        return siape;
    }

    public void setSiape(String siape) {
        this.siape = siape;
    }

    public URL getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(URL urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
