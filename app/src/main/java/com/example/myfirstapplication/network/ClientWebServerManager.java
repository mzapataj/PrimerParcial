package com.example.myfirstapplication.network;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class ClientWebServerManager extends Volley {

    private String hostname;
    //private JsonObjectRequest requestJSON;
    private volatile JSONObject responseJSON;
    private RequestQueue requestQueue;
    private Context context;
    private int mStatusCode = 0;
    private Response.Listener<JSONObject> mListener;

    public ClientWebServerManager(String hostname, Context context){
        this.hostname = hostname;
        requestQueue = Volley.newRequestQueue(context);
        this.context = context;
    }


    public void sendRequest(int method, String path,JSONObject jsonRequest, Response.Listener<JSONObject> listener,
    Response.ErrorListener errorListener){
        responseJSON = null;
        mStatusCode = 0;
        JsonObjectRequest requestJSON = new JsonObjectRequest(method,
                "https://"+hostname + "/" + path,
                jsonRequest,
                listener
                ,errorListener);
        requestQueue.add(requestJSON);
    }

    public void sendRequest(int method, String path,JSONObject jsonRequest){
        responseJSON = null;
        mStatusCode = 0;
        JsonObjectRequest requestJSON = new JsonObjectRequest(method,
                "https://"+hostname + "/" + path,
                jsonRequest,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        responseJSON = response;
                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(),Toast.LENGTH_LONG)
                        .show();
                mStatusCode=-1;
            }
        }){
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                if (response != null) {
                    mStatusCode = response.statusCode;
                }
                return super.parseNetworkResponse(response);
            }
        };
        requestQueue.add(requestJSON);
    }

    public void waitResponse(){
        while(mStatusCode==0){

        }
    }
    public int getResponseStatusCode(){
        return mStatusCode;
    }


    public JSONObject getResponseJSON(){
        return responseJSON;
    }

}
