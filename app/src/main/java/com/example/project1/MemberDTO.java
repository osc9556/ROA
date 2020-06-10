package com.example.project1;

import java.io.Serializable;

/**
 * Created by choi on 2017-05-08.
 */

public class MemberDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userID;
    private String userName;
    private String userEmail;



    public MemberDTO() {
    }

    public MemberDTO(String userID,  String userName, String userEmail) {
        this.userID = userID;
        this.userName = userName;
        this.userEmail = userEmail;

    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }



    @Override
    public String toString() {
        return "MemberDTO{" +
                "userID='" + userID + '\'' +
                ", userPassword='" + userEmail + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}