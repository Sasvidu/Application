package com.sasvidu;

import forms.*;

public class Main {

    static final int loginFormWidth = 420;
    static final int loginFormHeight = 540;
    static final int homeFrameWidth = 800;
    static final int homeFrameHeight = 540;

    public static void main(String[] args){

        LoginForm.getLoginForm(loginFormWidth, loginFormHeight);

    }

    public static void login(String username, String password){

        HomeFrame homeFrame = HomeFrame.getHomeFrame(homeFrameWidth, homeFrameHeight);
        System.out.println(username);
        System.out.println(password);

    }

}
