package com.developer.ruddbeltrao.keycontroller.domain;

/**
 * Created by Rudda Beltrao on 17/07/2015.
 */
public class Lab {

    public final String COLUM_LAB_NUMERO =  "lab_numero";
    public final String COLUM_BLOCO = "lab_bloco";
    public final String COLUM_PAVIMENTO = "lab_pavimento";
    public final String COLUM_DESCRICAO = "lab_desc";
    public final String COLUM_ON = "lab_on";


    private int numero;
    private String bloco;
    private String pavimento;
    private String descricao;
    private int on;


    public Lab(int numero, String bloco, String pavimento, int on) {
        this.numero = numero;
        this.bloco = bloco;
        this.pavimento = pavimento;
        this.on = on;
    }

    public Lab() {
    }

    public int getOn() {
        return on;
    }

    public void setOn(int on) {
        this.on = on;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBloco() {
        return bloco;
    }

    public void setBloco(String bloco) {
        this.bloco = bloco;
    }

    public String getPavimento() {
        return pavimento;
    }

    public void setPavimento(String pavimento) {
        this.pavimento = pavimento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
