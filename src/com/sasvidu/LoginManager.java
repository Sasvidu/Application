package com.sasvidu;

import forms.*;
import data.*;

public class LoginManager {

    //This class is used to implement the functionality of the Login Form GUI component. It is a singleton.

    //Private, class variable to hold the unique instance
    private static LoginManager instance;

    //Declare the users of the system
    static final User[] userArray = {new User("Gayani", "t00thCAR3@2023"), new User("Ranasinghe", "123"), new User("Sasvidu", "2007")};
    static final UserCollection users = UserCollection.getUserCollection(userArray);

    //Private constructor
    private LoginManager(){}

    //Public method to retrieve the unique instance
    public static LoginManager getLoginManager(){
        if (instance == null){
            instance = new LoginManager();
        }
        return instance;
    }

    //Login method, called upon button click on the login form
    public boolean login(String username, String password){
        //Iterate over users using the iterator pattern
        Iterator userIterator = users.getIterator();
        while(userIterator.hasNext()){
            User currentUser = (User) userIterator.next();
            String currentUsername = currentUser.getUsername();
            String currentPassword = currentUser.getPassword();
            //See if the entered username and password matches the username and password of any particular user object in the user collection
            if(currentUsername.matches(username) && currentPassword.matches(password)) {
                //If there is a matching set of data entered, login is successful.
                //Load the Home window GUI component
                HomeFrame.getHomeFrame();
                //Return a true message to indicate login was successful
                return true;
            }
        }
        //Return a false message to indicate login was not successful
        return false;
    }

}
