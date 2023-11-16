package com.sasvidu;

import forms.*;
import data.*;

public class Main {

    //Declare dimensions of JFrame windows:
    static final int loginFormWidth = 420;
    static final int loginFormHeight = 540;
    static final int homeFrameWidth = 1920;
    static final int homeFrameHeight = 1080;

    //Declare the users of the system:
    static final UserCollection users = UserCollection.getUserCollection(new User[]{new User("Gayani", "t00thCAR3@2023"), new User("Ranasinghe", "123"), new User("Sasvidu", "2007")});


    public static void main(String[] args){
        LoginForm.getLoginForm(loginFormWidth, loginFormHeight);
    }


    public static boolean login(String username, String password){
        //TIme Complexity - O(n)

        Iterator userIterator = users.getIterator();

        while(userIterator.hasNext()){

            User currentUser = (User) userIterator.next();

            if(username.matches(currentUser.getUsername()) && password.matches(currentUser.getPassword())) {
                HomeFrame.getHomeFrame(homeFrameWidth, homeFrameHeight);
                return true;
            }

        }

        return false;

    }

}
