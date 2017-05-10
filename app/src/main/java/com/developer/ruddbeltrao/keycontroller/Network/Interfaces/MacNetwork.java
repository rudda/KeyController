package com.developer.ruddbeltrao.keycontroller.Network.Interfaces;

import com.developer.ruddbeltrao.keycontroller.Network.Domain.User;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by Ruddá Beltrão on 01/02/2016.
 */
public interface MacNetwork {



    public User sendScore();
    public void ListScories(JSONArray array);
    public User userUpdateScore();



}
