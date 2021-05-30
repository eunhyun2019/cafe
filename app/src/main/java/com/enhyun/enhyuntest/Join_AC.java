package com.enhyun.enhyuntest;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Join_AC extends AppCompatActivity {
    EditText txt_id, txt_pwd, txt_pwdCheck, txt_phone, name, email;
    Button button, button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        txt_id=findViewById(R.id.txt_id);
        txt_pwd=findViewById(R.id.txt_pwd);
        txt_pwdCheck=findViewById(R.id.txt_pwdCheck);
        txt_phone=findViewById(R.id.txt_phone);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);

        button=findViewById(R.id.button);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID=txt_id.getText().toString();
                String userPassword=txt_pwd.getText().toString();
                String userPhone=txt_phone.getText().toString();
                String userName=name.getText().toString();
                String userEmail=email.getText().toString();

                if(!(userPassword.equals(txt_pwdCheck.getText().toString()))){
                    Toast.makeText(getApplicationContext(),"비밀번호가 일치하지 않습니다.",Toast.LENGTH_SHORT).show();
                }
                Response.Listener<String> responseListener= response -> {

                    try{
                            JSONObject jsonObject=new JSONObject(response);
                            boolean success=jsonObject.getBoolean("success");
                            if(success) {
                                Toast.makeText(getApplicationContext(), "회원 가입에 성공하셨습니다.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Join_AC.this, Login_AC.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(getApplicationContext(),"회원가입에 실패하셨습니다.",Toast.LENGTH_SHORT).show();
                                return;
                            }

                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                };

                RegisterRequest registerRequest=new RegisterRequest(userID, userPassword, userPhone, userName, userEmail, responseListener);
                RequestQueue queue= Volley.newRequestQueue(Join_AC.this);
                queue.add(registerRequest);
            }
        });
    }
}
