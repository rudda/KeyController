package com.developer.ruddbeltrao.keycontroller.RealNetwork.Interfaces;

import com.google.gson.JsonArray;

/**
 * Created by Dev Aux on 03/03/2016.
 */
public interface backup {


    public void backupUsers(JsonArray user, int type);
    public void backupLabs(JsonArray labs);
    public void backupCheckin(JsonArray checkin);
    public void backupChekout(JsonArray checkout);
    public void backupUserFromLab(JsonArray data);


}
