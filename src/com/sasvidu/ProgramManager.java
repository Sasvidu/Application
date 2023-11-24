package com.sasvidu;

import forms.*;

public class ProgramManager {

    //This class starts the execution flow by calling the login form.
    //As there is only execution flow, the class is implemented using the singleton pattern.

    //Private, class variable to hold the unique instance
    private static ProgramManager instance;

    //Private constructor, only accessible by the class itself
    private ProgramManager(){}

    //Public method to retrieve the unique instance, it ensures that the constructor is only called if there are no running instances.
    public static ProgramManager getProgramManager(){
        if (instance == null){
            instance = new ProgramManager();
        }
        return instance;
    }

    //Method to open the login form
    public void startApp(){
        LoginForm.getLoginForm();
    }

}
