package com.enhyun.enhyuntest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Start_AC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button login = (Button) findViewById(R.id.login_btn_main);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "로그인 페이지로 이동합니다.", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(Start_AC.this, Login_AC.class );
                startActivity(intent1);
                finish();
            }
        });

        Button join = (Button) findViewById(R.id.signup_btn);
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "회원가입 페이지로 이동합니다.", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent( Start_AC.this, Join_terms_AC.class);
                startActivity(intent1);
                finish();
            }
        });
    }
}