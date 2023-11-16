package com.sasvidu;

import forms.*;
import data.User;

public class Main {

    //Declare dimensions of JFrame windows:
    static final int loginFormWidth = 420;
    static final int loginFormHeight = 540;
    static final int homeFrameWidth = 800;
    static final int homeFrameHeight = 540;

    //Declare the users of the system:
    static final User[] users = {new User("Gayani", "t00thCAR3@2023"), new User("Ranasinghe", "123")};

    public static void main(String[] args){

        LoginForm.getLoginForm(loginFormWidth, loginFormHeight);

    }

    public static boolean login(String username, String password){
        //TIme Complexity - O(n)

        for(int i = 0; i < users.length; i++){

            if(username.matches(users[i].getUsername()) && password.matches(users[i].getPassword())) {

                HomeFrame.getHomeFrame(homeFrameWidth, homeFrameHeight);
                return true;

            }

        }

        return false;

    }

}
