package com.sasvidu;

import forms.*;
import data.*;

public class ProgramManager {

    private static ProgramManager instance;

    //Declare Treatment names:
    static final String cleaningCode = "Cleaning";
    static final String whiteningCode = "Whitening";
    static final String fillingCode = "Filling";
    static final String nerveFillingCode = "Nerve Filling";
    static final String RCTCode = "Root Canal Therapy";

    private ProgramManager(){}

    public static ProgramManager getProgramManager(){
        if (instance == null){
            instance = new ProgramManager();
        }
        return instance;
    }

    public void startApp(){

          LoginForm.getLoginForm();

    }

}
