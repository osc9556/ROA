package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GPShomeActivity extends AppCompatActivity {


    private Button now_lo;
    private Button parking_lo;
    private Button logout_button;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_gps_home);
        Intent intent=getIntent();

        now_lo=findViewById(R.id.now_lo);
        now_lo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(GPShomeActivity.this,GPSnowActivity.class);
                startActivity(intent);//액티비티 이동

            }
        });


        parking_lo=findViewById(R.id.parking_lo);
        parking_lo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(GPShomeActivity.this,GPSparkActivity.class);
                startActivity(intent);//액티비티 이동

            }
        });

        logout_button = findViewById(R.id.button); //로그아웃 버튼
        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
