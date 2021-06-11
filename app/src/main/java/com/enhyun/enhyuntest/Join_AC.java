package com.enhyun.enhyuntest;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
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

    private EditText txt_id, txt_pwd, txt_pwdCheck, txt_phone, name, email;
    private Button button, button1, button2;
    private AlertDialog dialog;
    private boolean validate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_join );

        //아이디값 찾아주기
        txt_id=findViewById(R.id.txt_id);
        txt_pwd=findViewById(R.id.txt_pwd);
        txt_pwdCheck=findViewById(R.id.txt_pwdCheck);
        txt_phone=findViewById(R.id.txt_phone);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);


        //아이디 중복 체크
        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String userID = txt_id.getText().toString();
                if (validate) {
                    return; //검증 완료
                }

                if (userID.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Join_AC.this);
                    dialog = builder.setMessage("아이디를 입력하세요.").setPositiveButton("확인", null).create();
                    dialog.show();
                    return;
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            AlertDialog.Builder builder = new AlertDialog.Builder(Join_AC.this);
                            if (success) {
                                dialog = builder.setMessage("사용할 수 있는 아이디입니다.").setPositiveButton("확인", null).create();
                                dialog.show();
                                validate = true; //검증 완료
                                button1.setBackgroundColor(getResources().getColor(R.color.black));
                            }
                            else {
                                dialog = builder.setMessage("이미 존재하는 아이디입니다.").setNegativeButton("확인", null).create();
                                dialog.show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                ValidateRequest validateRequest = new ValidateRequest(userID, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Join_AC.this);
                queue.add(validateRequest);
            }
        });



        //회원가입 버튼 클릭 시 수행
        button = findViewById( R.id.button );
        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String userID = txt_id.getText().toString();
                final String userPassword = txt_pwd.getText().toString();
                final String PassCk = txt_pwdCheck.getText().toString();
                final String userPhone = txt_phone.getText().toString();
                final String userName = name.getText().toString();
                final String userEmail = email.getText().toString();


                //아이디 중복체크 했는지 확인
                if (!validate) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Join_AC.this);
                    dialog = builder.setMessage("중복된 아이디가 있는지 확인하세요.").setNegativeButton("확인", null).create();
                    dialog.show();
                    return;
                }

                //한 칸이라도 입력 안했을 경우
                if (userID.equals("") || userPassword.equals("") || userPhone.equals("") || userName.equals("") || userEmail.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Join_AC.this);
                    dialog = builder.setMessage("모두 입력해주세요.").setNegativeButton("확인", null).create();
                    dialog.show();
                    return;
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject( response );
                            boolean success = jsonObject.getBoolean( "success" );

                            //회원가입 성공시
                            if(userPassword.equals(PassCk)) {
                                if (success) {

                                    Toast.makeText(getApplicationContext(), String.format("%s님 가입을 환영합니다.", userName), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Join_AC.this, Login_AC.class);
                                    startActivity(intent);

                                    //회원가입 실패시
                                } else {
                                    Toast.makeText(getApplicationContext(), "회원가입에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Join_AC.this);
                                dialog = builder.setMessage("비밀번호가 동일하지 않습니다.").setNegativeButton("확인", null).create();
                                dialog.show();
                                return;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                //서버로 Volley를 이용해서 요청
                RegisterRequest registerRequest = new RegisterRequest( userID, userPassword, userPhone, userName, userEmail, responseListener);
                RequestQueue queue = Volley.newRequestQueue( Join_AC.this );
                queue.add( registerRequest );
            }
        });
    }
}