package com.developer.ruddbeltrao.keycontroller.Helpers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.EditText;

import com.developer.ruddbeltrao.keycontroller.R;
import com.developer.ruddbeltrao.keycontroller.domain.Lab;

/**
 * Criado por Rudda Beltrao em 21/07/2015 as 17:34.
 */
public class AlocaLabHelper {

    public EditText numero;
    public EditText bloco;
    public EditText pavimento;
    public EditText descricao;

    public AlocaLabHelper(EditText numero, EditText bloco, EditText pavimento, EditText descricao) {

        this.numero = numero;
        this.bloco = bloco;
        this.pavimento = pavimento;
        this.descricao = descricao;

    }

    public Lab getLab() {
        Lab lab = new Lab();
        if (vereficNull()) {
            lab.setNumero(Integer.parseInt(numero.getText().toString()));
            lab.setPavimento(pavimento.getText().toString());
            lab.setDescricao(descricao.getText().toString());
            lab.setBloco(bloco.getText().toString());
            return lab;

        }
        return null;
    }

    public boolean vereficNull() {

        if (numero.getText().toString() == null || pavimento.getText().toString() == null
                || descricao.getText().toString() == null || bloco.getText().toString() == null) {

            return false;
        }
        return true;
    }

    public void clear() {

        numero.setText("");
        bloco.setText("");
        pavimento.setText("");
        descricao.setText("");

    }


}
