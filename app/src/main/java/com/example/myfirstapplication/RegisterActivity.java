package com.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.example.myfirstapplication.network.ClientWebServerManager;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    ClientWebServerManager clientWebServerManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    }

    public void submitRegister(View view) throws JSONException {
        clientWebServerManager = new ClientWebServerManager("movilapi.herokuapp.com",
                getApplicationContext());

        JSONObject newUser = new JSONObject();

        JSONObject params = new JSONObject();
        params.put("name",((EditText)findViewById(R.id.username_edit_text))
                                .getText().toString());
        params.put("password",((EditText)findViewById(R.id.password_edit_text))
                .getText().toString());

        newUser.put("user",params);

        clientWebServerManager.sendRequest(Request.Method.POST,"users",newUser);

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }

}
