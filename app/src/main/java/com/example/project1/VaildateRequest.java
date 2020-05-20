package com.example.project1;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

public class VaildateRequest extends StringRequest {

    final static private String URL = "http://15.164.233.187/phpmyadmin";
    private Map<String, String> parameters;

    public  VaildateRequest(String userID, String userPassword, String userName, String userPhonenum, String userEmail, Response.Listener<String> listener) {

        super(Method.POST,URL,listener,null);
        parameters=new HashMap<>();
        parameters.put("userID",userID);
    }
    @Override
    public Map<String,String> getParams(){
        return parameters;

    }
}

