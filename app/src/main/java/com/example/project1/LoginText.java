package com.example.project1; // ID, PW 전역변수

import android.app.Application;

public class LoginText extends Application {
    private String ID;
    private String PW;
    public String getID() // ID 불러오기
    {
        return ID;
    }
    public String getPW() // PW 불러오기
    {
        return PW;
    }
    public void setID(String ID) // ID 저장하기
    {
        this.ID = ID;
    }
    public void setPW(String PW) // PW 저장하기
    {
        this.PW = PW;
    }
}
