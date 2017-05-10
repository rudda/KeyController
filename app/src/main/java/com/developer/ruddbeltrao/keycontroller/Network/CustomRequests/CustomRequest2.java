package com.developer.ruddbeltrao.keycontroller.Network.CustomRequests;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by Dev Aux on 11/01/2016.
 */
public class CustomRequest2 extends Request<JSONObject> {

    private Response.Listener<JSONObject> listener;
    private Map<String, String> params;

    public CustomRequest2(int method, String url, Map<String, String> params,
                          Response.ErrorListener Errorlistener, Response.Listener<JSONObject> listener) {
        super(method, url, Errorlistener);

        this.params = params;
        this.listener = listener;


    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {

        try {

            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));

            return Response.success(new JSONObject(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        } catch (JSONException e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        }


    }



    @Override
    protected void deliverResponse(JSONObject response) {

        listener.onResponse(response);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}