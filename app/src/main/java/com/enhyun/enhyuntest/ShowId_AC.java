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

public class ShowId_AC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_id);

        //TextView nameText  = (TextView)findViewById(R.id.nameText);
        //TextView phoneText = (TextView)findViewById(R.id.phoneText);
        TextView welcome = (TextView)findViewById(R.id.WelcomeMessage);
        TextView idText = (TextView)findViewById(R.id.idText);
        Button managementButton = (Button)findViewById(R.id.LoginButton);


        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");
        //String userPhone = intent.getStringExtra("userPhone");
        String userID = intent.getStringExtra("userID");
        String msg = userName + "님의 아이디";

        //nameText.setText(userName);
        //phoneText.setText(userPhone);
        idText.setText(userID);
        welcome.setText(msg);

        managementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(ShowId_AC.this, Login_AC.class);
                startActivity(intent1);
            }
        });


    }
}