package com.developer.ruddbeltrao.keycontroller.domain;

import android.graphics.Bitmap;

/**
 * Created by Rudda Beltrao on 17/07/2015.
 */
public class Servidor extends  Usuario{


    public final String COLUM_PASEP= "serv_pasep";
    public final String COLUM_SIAPE= "serv_siape";
    public final String COLUM_FUNCAO = "serv_funcao";

    private String pasep;
    private String siape;
    private String funcao;


    public Servidor(String nome, String cpf, String rg, String email, String senha, String urlFoto, int type, String pasep, String siape, String funcao) {
        super(nome, cpf, rg, email, senha, urlFoto, type);
        this.pasep = pasep;
        this.siape = siape;
        this.funcao = funcao;
    }

    public Servidor(){}

    public String getPasep() {
        return pasep;
    }

    public void setPasep(String pasep) {
        this.pasep = pasep;
    }

    public String getSiape() {
        return siape;
    }

    public void setSiape(String siape) {
        this.siape = siape;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
}
