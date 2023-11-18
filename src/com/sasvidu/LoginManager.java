package com.sasvidu;

import forms.*;
import data.*;

public class LoginManager {

    private static LoginManager instance;

    //Declare the users of the system:
    static final UserCollection users = UserCollection.getUserCollection(new User[]{new User("Gayani", "t00thCAR3@2023"), new User("Ranasinghe", "123"), new User("Sasvidu", "2007")});

    //Declare HomeFrame Dimensions:
    static final int homeFrameWidth = 1550;
    static final int homeFrameHeight = 850;
    static final int homeFramePanelWidth = 600;
    static final int homeFrameSearchPanelHeight = 200;

    private LoginManager(){}

    public static LoginManager getLoginManager(){
        if (instance == null){
            instance = new LoginManager();
        }
        return instance;
    }

    public boolean login(String username, String password){
        //TIme Complexity - O(n)

        Iterator userIterator = users.getIterator();

        while(userIterator.hasNext()){
            User currentUser = (User) userIterator.next();
            String currentUsername = currentUser.getUsername();
            String currentPassword = currentUser.getPassword();
            if(currentUsername.matches(username) && currentPassword.matches(password)) {
                HomeFrame.getHomeFrame();
                System.out.println(true);
                return true;
            }
        }
        System.out.println(false);
        return false;

    }

}
