package com.developer.ruddbeltrao.keycontroller.Network.CustomRequests;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by Dev Aux on 08/01/2016.
 */
public class CustomRequest extends Request<JSONArray> {

    private Response.Listener<JSONArray> listener;
    private Map<String, String> params;

    public CustomRequest(int method, String url,  Map<String, String> params, Response.ErrorListener Errorlistener, Response.Listener<JSONArray> listener) {
        super(method, url, Errorlistener);

        this.params = params;
        this.listener = listener;


    }

    @Override
    protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {

        try{

            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));

            return Response.success(new JSONArray(jsonString),
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
    protected void deliverResponse(JSONArray response) {

        listener.onResponse(response);

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }


}
