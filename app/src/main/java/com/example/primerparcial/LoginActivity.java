package com.example.primerparcial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.EventLog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.MainThread;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_layout);
        ((Button)findViewById(R.id.submit))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(),
                                MainActivity.class);
                        intent.putExtra("user_name",
                                ((EditText)findViewById(
                                        R.id.username)).getText().toString());
                        intent.putExtra("user_password",
                                ((EditText)findViewById(
                                        R.id.password)).getText().toString());
                        startActivity(intent);

                    }
                });

    }

    public void ToRegister(View view){
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
    }

}
