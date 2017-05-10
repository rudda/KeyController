package com.developer.ruddbeltrao.keycontroller.Network.Interfaces;


import com.developer.ruddbeltrao.keycontroller.Network.WrapNetwork;
import com.developer.ruddbeltrao.keycontroller.domain.Aluno;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Dev Aux on 08/01/2016.
 */
public interface Transaction {

    public WrapNetwork doBefore();
    public void doAfter(JSONObject json);


}
