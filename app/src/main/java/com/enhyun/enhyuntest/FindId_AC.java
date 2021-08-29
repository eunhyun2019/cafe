package com.enhyun.enhyuntest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;
import android.content.Intent;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class FindId_AC extends AppCompatActivity {
    // LoginButton, findPswdbtn, joinbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_id);


        final EditText Name = (EditText)findViewById(R.id.Name);
        final EditText Phone = (EditText)findViewById(R.id.Phone);

        final Button findIdbtn = (Button)findViewById(R.id.findIdbtn);


        Button LoginButton = (Button)findViewById(R.id.LoginButton);
        Button findPswdbtn = (Button)findViewById(R.id.findPswdbtn);
        Button joinbtn = (Button)findViewById(R.id.joinbtn);

        LoginButton.setOnClickListener(btnListener);
        findPswdbtn.setOnClickListener(btnListener);
        joinbtn.setOnClickListener(btnListener);

        findIdbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String userName = Name.getText().toString();
                final String userPhone = Phone.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            Toast.makeText(getApplicationContext(), "success"+success, Toast.LENGTH_SHORT).show();

                            //서버에서 보내준 값이 true이면?
                            if(success){

                                //Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();

                                String userName = jsonResponse.getString("userName");
                                String userPhone = jsonResponse.getString("userPhone");
                                String userID = jsonResponse.getString("userID");

                                //로그인에 성공했으므로 MainActivity로 넘어감
                                Intent intent = new Intent(FindId_AC.this, ShowId_AC.class);
                                intent.putExtra("userName", userName);
                                intent.putExtra("userPhone", userPhone);
                                intent.putExtra("userID", userID);
                                startActivity(intent);

                            }else{//로그인 실패시
                                AlertDialog.Builder builder = new AlertDialog.Builder(FindId_AC.this);
                                builder.setMessage("find failed")
                                        .setNegativeButton("retry", null)
                                        .create()
                                        .show();


                            }

                        }catch(JSONException e){
                            e.printStackTrace();
                        }
                    }
                };

                FindId_Request FindRequest = new FindId_Request(userName, userPhone, responseListener);
                RequestQueue queue = Volley.newRequestQueue(FindId_AC.this);
                queue.add(FindRequest);
            }
        });

    }

    View.OnClickListener btnListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.LoginButton:
                    Intent intent1 = new Intent(FindId_AC.this, Login_AC.class);
                    startActivity(intent1);
                    break;
                case R.id.findPswdbtn:
                    Intent intent2 = new Intent(FindId_AC.this, FindPswd_AC.class);
                    startActivity(intent2);
                    break;
                case R.id.joinbtn:
                    Intent intent3 = new Intent(FindId_AC.this, Join_terms_AC.class);
                    startActivity(intent3);
                    break;
            }
        }
    };




}