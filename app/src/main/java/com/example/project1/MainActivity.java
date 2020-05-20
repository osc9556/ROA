package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;





public class MainActivity extends AppCompatActivity {



    private Button login_button;
    private Button findID_button;
    private Button findPW_button;
    private Button sign_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        login_button=findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v) {
            Intent intent =new Intent(MainActivity.this,MenuActivity.class);
            startActivity(intent);//액티비티 이동
        }
    });

        findID_button = findViewById(R.id.findID_button); //아이디 찾기 버튼
        findID_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FindIdActivity.class);
                startActivity(intent);

            }
        });

        findPW_button = findViewById(R.id.findPW_button); //비밀번호 찾기 버튼
        findPW_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FindPassActivity.class);
                startActivity(intent);
            }
        });

        sign_button = findViewById(R.id.sign_button); //회원가입 버튼
        sign_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignActivity.class);
                startActivity(intent);
            }
        });
    }



}



