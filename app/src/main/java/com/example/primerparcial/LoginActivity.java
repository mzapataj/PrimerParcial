package com.example.primerparcial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.EventLog;
import android.view.View;

import androidx.annotation.MainThread;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_layout);
    }

    public void ToRegister(View view){
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
    }

}
