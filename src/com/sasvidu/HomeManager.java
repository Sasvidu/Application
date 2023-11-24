package com.sasvidu;

import data.*;
import forms.InsertFrame;
import forms.ViewAppointmentsFrame;

import java.util.Stack;

public class HomeManager {

    //This class is used to implement the functionalities of the Home window. It is a singleton/

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

    public <T> void setCommand(Command<T> command){
        currentCommand = command;
        commandHistory.push(command);
        redoHistory.clear();
    }

    public <T> void setCommandWithoutRecording(Command<T> command){
        currentCommand = command;
    }


    public void executeCommand(){
        if(this.currentCommand != null){
            currentCommand.execute();
        }
    }

    public void undo(){
        if(!commandHistory.isEmpty()){
            Command<?> undoneCommand = commandHistory.pop();
            redoHistory.push(undoneCommand);
            undoneCommand.undo();
        }
    }

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

    public void view(String patientName){
        new ViewAppointmentsFrame(patientName);
    }

    protected void addCommand(Command<?> command){
        commandHistory.push(command);
        redoHistory.clear();
    }

    public int getCommandHistorySize() {
        return commandHistory.size();
    }

    public int getRedoHistorySize() {
        return redoHistory.size();
    }

}
