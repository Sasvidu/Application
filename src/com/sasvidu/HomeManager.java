package com.sasvidu;

import data.*;
import forms.HomeFrame;
import forms.InsertFrame;
import forms.Invoice;
import javax.swing.*;
import java.time.LocalDate;
import java.util.Map;

public class HomeManager {

    //This class is used to implement the functionalities of the Home window. It is a singleton/

    //Private, class variable to hold the unique instance
    private static HomeManager instance;
    //private field to store the current command
    private Command<?> currentCommand;

    //Private constructor
    private HomeManager(){}

    //Public method to retrieve the unique instance
    public static HomeManager getHomeManager(){
        if(instance == null){
            instance = new HomeManager();
        }
        return instance;
    }

    public <T> void setCommand(Command<T> command){
        currentCommand = command;
    }

    public void executeCommand(){
        if(this.currentCommand != null){
            currentCommand.execute();
        }
    }

    //Method to handle the click event of the insert button
    public void insert(String patientName, String patientAddress, String patientTelephoneNumber, String treatmentType){
        new InsertFrame(patientName, patientAddress, patientTelephoneNumber, treatmentType);
    }

}
