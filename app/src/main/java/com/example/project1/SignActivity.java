package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
import org.json.JSONException;
import org.json.JSONObject;

public class SignActivity extends AppCompatActivity {

    private Button sign_button;

    private ArrayAdapter adapter;
    private Spinner spinner;
    private String userID;
    private String userPassword;
    private String userName;
    private String userPhonenum;
    private String userEmail;
    private AlertDialog dialog;
    private boolean vaildate=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        final EditText idtext = (EditText) findViewById(R.id.idtext);
        final EditText pwtext = (EditText) findViewById(R.id.pwtext);
        final EditText nametext = (EditText) findViewById(R.id.nametext);
        final EditText phtext = (EditText) findViewById(R.id.phtext);
        final EditText etext = (EditText) findViewById(R.id.etext);




        Button sign_button=(Button) findViewById(R.id.sign_button);
        sign_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                String userID=idtext.getText().toString();
                String userPassword=pwtext.getText().toString();
                String userName=nametext.getText().toString();
                String userPhonenum =phtext.getText().toString();
                String userEmail=etext.getText().toString();

                if (userID.equals("") || userPassword.equals(("")))
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignActivity.this);
                    dialog = builder.setMessage("아이디와 비밀번호는 필수 입력입니다")
                            .setNegativeButton(("확인"), null)
                            .create();
                    dialog.show();
                    return;
                }


                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success)
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignActivity.this);
                                dialog = builder.setMessage("회원등록에 성공했습니다")
                                        .setPositiveButton("확인", null)
                                        .create();
                                dialog.show();
                                finish();
                            }
                            else

                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignActivity.this);
                                dialog = builder.setMessage("회원등록에 실패했습니다")
                                        .setNegativeButton(("확인"), null)
                                        .create();
                                dialog.show();
                            }
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();

                        }
                    }


                };
                RegisterRequest registerRequest=new RegisterRequest(userID,userPassword,userName,userPhonenum,userEmail,responseListener);
                RequestQueue queue= Volley.newRequestQueue(SignActivity.this);
                queue.add(registerRequest);

            }


        });
    }
    protected  void onStop(){
        super.onStop();
        if(dialog!=null)
        {
            dialog.dismiss();
            dialog=null;

        }
    }
}

