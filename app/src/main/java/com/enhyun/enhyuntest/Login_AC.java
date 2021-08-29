package com.enhyun.enhyuntest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Login_AC extends AppCompatActivity {

    EditText txt_login_id, txt_login_pwd;
    Button button, button2, button4, login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txt_login_id=findViewById(R.id.txt_login_id);
        txt_login_pwd=findViewById(R.id.txt_login_pwd);

        button=findViewById(R.id.button);
        button2=findViewById(R.id.button2);
        button4=findViewById(R.id.button4);
        login_btn=findViewById(R.id.login_btn);

        button.setOnClickListener(btnListener);
        button2.setOnClickListener(btnListener);
        button4.setOnClickListener(btnListener);
        login_btn.setOnClickListener(loginListener);
    }

    View.OnClickListener btnListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.button:
                    Intent intent1=new Intent(Login_AC.this, FindId_AC.class);
                    startActivity(intent1);
                    break;
                case R.id.button2:
                    Intent intent2 =new Intent(Login_AC.this, FindPswd_AC.class);
                    startActivity(intent2);
                    break;
                case R.id.button4:
                    Intent intent3 = new Intent(Login_AC.this, Join_terms_AC.class);
                    startActivity(intent3);
                    break;
            }
        }
    };

    View.OnClickListener loginListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String userID=txt_login_id.getText().toString();
            String userPassword=txt_login_pwd.getText().toString();

            Response.Listener<String> responseListener=new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try{
                        JSONObject jsonObject=new JSONObject(response);
                        boolean success=jsonObject.getBoolean("success");
                        if(success){
                            String userID=jsonObject.getString("userID");
                            String userPassword=jsonObject.getString("userPassword");
                            Toast.makeText(getApplicationContext(),"로그인에 성공하였습니다.",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Login_AC.this, Store_AC.class);
                            intent.putExtra("userID",userID);
                            intent.putExtra("userPassword", userPassword);
                            startActivity(intent);
                        }else {
                            Toast.makeText(getApplicationContext(),"로그인에 실패하였습니다.",Toast.LENGTH_SHORT).show();
                            return;
                        }

                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            };

            LoginRequest loginRequest = new LoginRequest(userID, userPassword, responseListener);
            RequestQueue queue= Volley.newRequestQueue(Login_AC.this);
            queue.add(loginRequest);
        }
    };
}