package com.example.myfirstapplication.network;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class ClientWebServerManager extends Volley {

    private String hostname;
    private JsonObjectRequest requestJSON;
    private JSONObject responseJSON;
    private RequestQueue requestQueue;
    private Context context;

    ClientWebServerManager(String hostnamem, Context context){
        this.hostname = hostname;
        requestQueue = Volley.newRequestQueue(context);
        this.context = context;
    }


    public void sendRequest(int method, String path,JSONObject jsonRequest){
        requestJSON = new JsonObjectRequest(method,
            "https://"+hostname + "/" + path,
                jsonRequest,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        responseJSON = response;
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.getMessage(),Toast.LENGTH_LONG)
                        .show();
                    }
                });
        requestQueue.add(requestJSON);
    }

    public JSONObject getResponseJSON(){
        return responseJSON;
    }

}
