package com.developer.ruddbeltrao.keycontroller.Network;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.developer.ruddbeltrao.keycontroller.Network.CustomRequests.CustomRequest;
import com.developer.ruddbeltrao.keycontroller.Network.Domain.User;
import com.developer.ruddbeltrao.keycontroller.Network.Interfaces.MacNetwork;
import com.developer.ruddbeltrao.keycontroller.Network.Interfaces.Transaction;
import com.developer.ruddbeltrao.keycontroller.Network.Interfaces.TrasactionTeste;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dev Aux on 08/01/2016.
 */
public class NetworkConnection {

    private static NetworkConnection instace;
    private Context mContext;
    private RequestQueue mRequestQueue;


    public NetworkConnection(Context mContext) {
        this.mContext = mContext;
        mRequestQueue = getRequestQueue();

    }

    public static NetworkConnection getInstace(Context com) {

        if (instace == null) {

            instace = new NetworkConnection(com.getApplicationContext());
        }

        return instace;
    }

    public RequestQueue getRequestQueue() {

        if (mRequestQueue == null) {

            mRequestQueue = Volley.newRequestQueue(mContext);

        }

        return mRequestQueue;
    }

    public <T> void addRequestQueue(Request<T> request) {

        getRequestQueue().add(request);

    }







    public void execute(final TrasactionTeste macNetwork, String tag) {

        String comand="" ;


        try {

        JSONObject object = new JSONObject();

            object.put("nome","LOHRAYNE ANDRADE");
            object.put("email","lolita.souza@gmail.com");
            object.put("record","123");
            object.put("estado","AM");
            object.put("cidade","Manaus");
            object.put("pais","Brasil");
            object.put("secret","ruh");
            comand = object.toString();

            Log.i("LOG", "dados:     "+comand);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        Map<String, String> params = new HashMap<>();


        params.put("function", "insert");
        params.put("dados", comand);



        CustomRequest request = new CustomRequest(Request.Method.POST,
                "http://www.lab312-icetufam.com.br/macteaching/webservice/executies/relese.php", params, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i("log", "erro "+error.networkResponse);
                macNetwork.doBefore("erro "+error.getMessage());


            }

        },
                new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Log.i("LOG", "OK");

                macNetwork.doBefore("okay "+response.toString());

            }
        }
        );

        request.setTag(tag);
        addRequestQueue(request);

    }





}




