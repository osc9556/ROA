package com.example.project1;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    private Button ROA_button;
    private Button GPS_button;
    private Button Friend_button;
    private Button Setting_button;
    private Button logout_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        logout_button = findViewById(R.id.button); //로그아웃 버튼
        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        ROA_button = findViewById(R.id.ROA_button); //ROA 버튼
        ROA_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ROAhomeActivity.class);
                startActivity(intent);
            }
        });

        GPS_button = findViewById(R.id.GPS_button); //GPS 버튼
        GPS_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GPShomeActivity.class);
                startActivity(intent);
            }
        });

        Friend_button = findViewById(R.id.Friend_button); //친구 버튼
        Friend_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FriendhomeActivity.class);
                startActivity(intent);
            }
        });

        Setting_button = findViewById(R.id.Setting_button); //설정 버튼
        Setting_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent);
            }
        });
    }
}
