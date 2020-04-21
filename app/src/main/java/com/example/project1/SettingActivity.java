package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingActivity extends AppCompatActivity {

    private  Button ManageFriend_button;
    private  Button Alarm_button;
    private  Button MyPage_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        ManageFriend_button = findViewById(R.id.ManageFriend_button); //친구관리 버튼
        ManageFriend_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FriendaddActivity.class);
                startActivity(intent);
            }
        });


        Alarm_button=findViewById(R.id.Alarm_button);
        Alarm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(SettingActivity.this,AlarmActivity.class);
                startActivity(intent);//액티비티 이동

            }
        });

        MyPage_button=findViewById(R.id.MyPage_button);
        MyPage_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(SettingActivity.this,MypageActivity.class);
                startActivity(intent);//액티비티 이동

            }
        });
    }








}
