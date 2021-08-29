package com.enhyun.enhyuntest;

import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;

public class FindId_Request extends StringRequest {

    final static private String URL = "http://192.168.161.1/find_id.php";
    private Map<String, String> parameters;

    //생성자
    public FindId_Request(String userName, String userPhone, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userName", userName);
        parameters.put("userPhone", userPhone);
    }

    //추후 사용을 위한 부분
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }

}