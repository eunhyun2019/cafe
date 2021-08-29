package com.enhyun.enhyuntest;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;


import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ShowPswd_AC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_pswd);

        TextView welcome = (TextView)findViewById(R.id.WelcomeMessage);
        TextView passwordText = (TextView)findViewById(R.id.passwordText);
        Button managementButton = (Button)findViewById(R.id.LoginButton);


        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");
        //String userPhone = intent.getStringExtra("userPhone");
        String userPassword = intent.getStringExtra("userPassword");
        String msg = userID + "님의 비밀번호";

        //nameText.setText(userName);
        //phoneText.setText(userPhone);
        passwordText.setText(userPassword);
        welcome.setText(msg);

        managementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(ShowPswd_AC.this, Login_AC.class);
                startActivity(intent1);
            }
        });


    }
}