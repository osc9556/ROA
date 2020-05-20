package com.example.project1;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    final static private String URL = "http://15.164.233.187/phpmyadmin";
    private Map<String, String> parameters;

    public RegisterRequest(String userID, String userPassword, String userName, String userPhonenum, String userEmail, Response.Listener<String> listener) {

        super(Method.POST,URL,listener,null);
        parameters=new HashMap<>();
        parameters.put("userID",userID);
        parameters.put("userPassword",userPassword);
        parameters.put("userName",userName);
        parameters.put("userPhonenum",userPhonenum);
        parameters.put("userEmail",userEmail);
    }

    @Override
    public Map<String,String> getParams(){
        return parameters;
        
    }
 }

