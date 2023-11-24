package com.sasvidu;

import data.*;
import forms.InsertFrame;
import forms.ViewAppointmentsFrame;

import java.util.Stack;

public class HomeManager {

    //This class is used to implement the functionalities of the Home window. It is a singleton.

    //Private, class variable to hold the unique instance
    private static HomeManager instance;
    //Private field to store the current command
    private Command<?> currentCommand;
    //Stacks to store the history of all executed commands
    private Stack<Command<?>> commandHistory = new Stack<>();
    private Stack<Command<?>> redoHistory = new Stack<>();

    //Private constructor
    private HomeManager(){}

    //Public method to retrieve the unique instance
    public static HomeManager getHomeManager(){
        if(instance == null){
            instance = new HomeManager();
        }
        return instance;
    }

    //Method to schedule a command to be executed through the HomeManager
    public <T> void setCommand(Command<T> command){
        currentCommand = command;
        commandHistory.push(command);
        redoHistory.clear();
    }

    //Method to set a command to executed through the HomeManager, without adding it to the history of commands
    public <T> void setCommandWithoutRecording(Command<T> command){
        currentCommand = command;
    }

    //Method to execute a command through the HomeManager
    public void executeCommand(){
        if(this.currentCommand != null){
            currentCommand.execute();
        }
    }

    //Method to arrange the command history for an undo operation
    public void undo(){
        if(!commandHistory.isEmpty()){
            Command<?> undoneCommand = commandHistory.pop();
            redoHistory.push(undoneCommand);
            undoneCommand.undo();
        }
    }

    //Method to arrange the command history for a redo operation
    public void redo() {
        if (!redoHistory.isEmpty()) {
            Command<?> redoneCommand = redoHistory.pop();
            commandHistory.push(redoneCommand);
            redoneCommand.redo();
        }
    }

    //Method to handle the click event of the insert button
    public void insert(String patientName, String patientAddress, String patientTelephoneNumber, String treatmentType){
        new InsertFrame(patientName, patientAddress, patientTelephoneNumber, treatmentType);
    }

    //Method to handle the click event of the view button for the appointments of a patient
    public void view(String patientName){
        new ViewAppointmentsFrame(patientName);
    }

    //Method to add a command executed elsewhere to the command history stack
    protected void addCommand(Command<?> command){
        commandHistory.push(command);
        redoHistory.clear();
    }

}
