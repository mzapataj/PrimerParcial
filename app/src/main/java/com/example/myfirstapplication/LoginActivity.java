package com.example.myfirstapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.myfirstapplication.network.ClientWebServerManager;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends Activity {
    ClientWebServerManager clientWebServerManager;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_layout);
        context = getApplicationContext();
        /*((Button)findViewById(R.id.login_button)).
                setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intetToBecalled=new
                        Intent(getApplicationContext(),
                        MainActivity.class);
                intetToBecalled.putExtra("user_name",
                        ((EditText)findViewById(
                                R.id.login_user_name)).getText().toString());
                intetToBecalled.putExtra("user_password",
                        ((EditText)findViewById(
                                R.id.login_password)).getText().toString());
                startActivity(intetToBecalled);
            }
        });
            */
    }

    public void toRegister(View view){
        Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
        startActivity(intent);
    }

    public void submitLogin(View view) throws JSONException {
        clientWebServerManager = new ClientWebServerManager("movilapi.herokuapp.com",
                getApplicationContext());

        JSONObject newUser = new JSONObject();

        JSONObject params = new JSONObject();
        params.put("name",((EditText)findViewById(R.id.login_user_name))
                .getText().toString());
        params.put("password",((EditText)findViewById(R.id.login_password))
                .getText().toString());

        newUser.put("user",params);

        clientWebServerManager.sendRequest(Request.Method.POST,"sessions",newUser, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Intent intetToBecalled=new
                        Intent(context,
                        MainActivity.class);
                intetToBecalled.putExtra("user_name",
                        ((EditText)findViewById(
                                R.id.login_user_name)).getText().toString());
                intetToBecalled.putExtra("user_password",
                        ((EditText)findViewById(
                                R.id.login_password)).getText().toString());
                startActivity(intetToBecalled);
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse = error.networkResponse;
                if(networkResponse != null && networkResponse.statusCode==422){
                    String raw = new String(networkResponse.data);
                    try {
                        JSONObject responseJSON = new JSONObject(raw);
                        Toast.makeText(getApplicationContext(),
                            responseJSON.getJSONObject("error").get("message").toString(),
                            Toast.LENGTH_LONG).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //Log.d("Error http response", raw);

                } else {
                    Toast.makeText(getApplicationContext(), error.getMessage(),Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

/*
        new Thread(){
            @Override
            public void run() {
                clientWebServerManager.waitResponse();
                switch (clientWebServerManager.getResponseStatusCode()){
                    case 201:
                        Intent intetToBecalled=new
                                Intent(context,
                                MainActivity.class);
                        intetToBecalled.putExtra("user_name",
                                ((EditText)findViewById(
                                        R.id.login_user_name)).getText().toString());
                        intetToBecalled.putExtra("user_password",
                                ((EditText)findViewById(
                                        R.id.login_password)).getText().toString());
                        startActivity(intetToBecalled);
                        break;

                    case 422:
                        Toast.makeText(context,"User name or/and password incorrect.",
                                Toast.LENGTH_LONG).show();
                        break;
                }
            }
        }.run();

*/

        //Toast.makeText(this,clientWebServerManager.getResponseJSON().toString(),Toast.LENGTH_LONG)
        //.show();
/*      Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
*/
    }


}
