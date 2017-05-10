package com.developer.ruddbeltrao.keycontroller.domain;

/**
 * Criado por Rudda Beltrao em 20/07/2015 as 03:49.
 */
public class Check {

    private String labNumero;
    private String usuario;
    private String data;
    private String guarda;
    private String usuario_cpf;


    public Check() {
    }





    public Check(String labNumero, String usuario, String data, String guarda) {
        this.labNumero = labNumero;
        this.usuario = usuario;
        this.data = data;
        this.guarda = guarda;
    }


    public String getUsuario_cpf() {
        return usuario_cpf;
    }

    public void setUsuario_cpf(String usuario_cpf) {
        this.usuario_cpf = usuario_cpf;
    }

    public String getLabNumero() {
        return labNumero;
    }

    public void setLabNumero(String labNumero) {
        this.labNumero = labNumero;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getGuarda() {
        return guarda;
    }

    public void setGuarda(String guarda) {
        this.guarda = guarda;
    }
}
