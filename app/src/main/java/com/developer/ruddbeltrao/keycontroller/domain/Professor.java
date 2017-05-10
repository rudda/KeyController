package com.developer.ruddbeltrao.keycontroller.domain;

import android.graphics.Bitmap;

/**
 * Created by Rudda Beltrao on 17/07/2015.
 */
public class Professor extends Usuario {

    public final String COLUM_SIAPE = "prof_siape";
    public final String COLUM_PASEP = "prof_pasep";

    private String pasep;
    private String siape;


    public Professor(String nome, String cpf, String rg, String email, String senha, String urlFoto, int type, String pasep, String siape) {
        super(nome, cpf, rg, email, senha, urlFoto, type);
        this.pasep = pasep;
        this.siape = siape;
    }

    public Professor() {
    }

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
}
