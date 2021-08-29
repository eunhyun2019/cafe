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

public class FindPswd_AC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pswd);

        final EditText Name = (EditText)findViewById(R.id.Name);
        final EditText Phone = (EditText)findViewById(R.id.Phone);
        final EditText Id = (EditText)findViewById(R.id.Id);

        final Button findPswdbtn = (Button)findViewById(R.id.findPswdbtn);


        Button LoginButton = (Button)findViewById(R.id.LoginButton);
        Button findIdbtn = (Button)findViewById(R.id.findIdbtn);
        Button joinbtn = (Button)findViewById(R.id.joinbtn);

        LoginButton.setOnClickListener(btnListener);
        findIdbtn.setOnClickListener(btnListener);
        joinbtn.setOnClickListener(btnListener);


        findPswdbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String userName = Name.getText().toString();
                final String userPhone = Phone.getText().toString();
                final String userID = Id.getText().toString();

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
                                String userPassword = jsonResponse.getString("userPassword");

                                //로그인에 성공했으므로 MainActivity로 넘어감
                                Intent intent = new Intent(FindPswd_AC.this, ShowPswd_AC.class);
                                intent.putExtra("userName", userName);
                                intent.putExtra("userPhone", userPhone);
                                intent.putExtra("userID", userID);
                                intent.putExtra("userPassword", userPassword);
                                startActivity(intent);

                            }else{//로그인 실패시
                                AlertDialog.Builder builder = new AlertDialog.Builder(FindPswd_AC.this);
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

                FindPswd_Request FindpassRequest = new FindPswd_Request(userName, userPhone, userID, responseListener);
                RequestQueue queue = Volley.newRequestQueue(FindPswd_AC.this);
                queue.add(FindpassRequest);
            }
        });

    }

    View.OnClickListener btnListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.LoginButton:
                    Intent intent1 = new Intent(FindPswd_AC.this, Login_AC.class);
                    startActivity(intent1);
                    break;
                case R.id.findIdbtn:
                    Intent intent2 = new Intent(FindPswd_AC.this, FindId_AC.class);
                    startActivity(intent2);
                    break;
                case R.id.joinbtn:
                    Intent intent3 = new Intent(FindPswd_AC.this, Join_terms_AC.class);
                    startActivity(intent3);
                    break;
            }
        }
    };
}