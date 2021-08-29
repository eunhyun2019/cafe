package com.enhyun.enhyuntest;

import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;

public class FindPswd_Request extends StringRequest {

    final static private String URL = "http://192.168.161.1/find_password.php";
    private Map<String, String> parameters;

    //생성자
    public FindPswd_Request(String userName, String userPhone, String userID, Response.Listener<String> listener){
        super(Request.Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userName", userName);
        parameters.put("userPhone", userPhone);
        parameters.put("userID", userID);
    }

    //추후 사용을 위한 부분
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }

}