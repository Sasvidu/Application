package com.sasvidu;

import data.*;
import forms.HomeFrame;

import javax.swing.*;
import java.time.*;
import java.util.*;

public class InsertManager {

    //This class is used to implement the functionality of the Insert(Appointment) GUI component. It is a singleton.

    //Private, class variable to hold the unique instance
    private static InsertManager instance;
    //private field to store the current command
    private Command<?> currentCommand;

    private final String warningTxt = "Warning!";
    private final String errorTxt = "Error!";
    private final String nullErrorMessage = "Please select a date to check availability.";
    private final String unavailableErrorMessage = "Appointments can only be made for Monday, Wednesday, Saturday and Sunday. The selected date is not one of these days.";
    private final String overflowErrorMessage = "Sorry, there are no available time slots for the selected date as the schedule is already full.";
    private final String retrievalErrorMessage = "There was an error reading a schedule, please contact your troubleshooting manager to solve this issue.";
    private final String uncalledErrorMessage = "There was an error invoking function related to the Check Date button, please contact your troubleshooting manager to solve this issue.";
    private final String addedAlreadyErrorMessage = "The appointment is already added to the date's schedule.";
    private final String addedElsewhereErrorMessage = "The appointment is already added to another date's schedule.";
    private final String errorMessage = "Something went wrong, please contact your troubleshooting manager for assistance.";
    private final String successAddtionMessage = "The appointment has been successfully added, the registration fee is LKR. 1000.00/=";

    //Private constructor
    private InsertManager() {}

    //Public method to retrieve the unique instance
    public static InsertManager getInsertManager() {
        if (instance == null) {
            instance = new InsertManager();
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

    public void checkDate(Date selectedDate, String treatmentType) {

        Command<String> validateCommand = new CheckDateCommand(selectedDate, treatmentType);
        this.setCommand(validateCommand);
        this.executeCommand();
        String response = validateCommand.getResult();
        switch (response) {
            case "Null Error":
                JOptionPane.showMessageDialog(null, nullErrorMessage, warningTxt, JOptionPane.WARNING_MESSAGE);
                break;
            case "Unavailable Error":
                JOptionPane.showMessageDialog(null, unavailableErrorMessage, warningTxt, JOptionPane.WARNING_MESSAGE);
                break;
            case "Overflow Error":
                JOptionPane.showMessageDialog(null, overflowErrorMessage, warningTxt, JOptionPane.WARNING_MESSAGE);
                break;
            case "Retrieval Error":
                JOptionPane.showMessageDialog(null, retrievalErrorMessage, errorTxt, JOptionPane.ERROR_MESSAGE);
                break;
            case "Uncalled Error":
                JOptionPane.showMessageDialog(null, uncalledErrorMessage, errorTxt, JOptionPane.ERROR_MESSAGE);
                break;
            default:
                JOptionPane.showMessageDialog(null, "An appointment can be reserved at : " + response, "Available TIme", JOptionPane.INFORMATION_MESSAGE);
                break;
        }

    }


    public void addAppointment(Date selectedDate, String patientName, String patientAddress, String patientTelephoneNumber, String treatmentType, JFrame insertFrame) {

        //Call the checkDate function to see whether there is time for the appointment to be reserved for the requested date, and get the available time if there is
        Command<String> addCommand = new AddAppointmentCommand(selectedDate, patientName, patientAddress, patientTelephoneNumber, treatmentType);
        this.setCommand(addCommand);
        this.executeCommand();
        String response = addCommand.getResult();

        switch (response) {
            case "Null Error":
                JOptionPane.showMessageDialog(null, nullErrorMessage, warningTxt, JOptionPane.WARNING_MESSAGE);
                break;
            case "Unavailable Error":
                JOptionPane.showMessageDialog(null, unavailableErrorMessage, warningTxt, JOptionPane.WARNING_MESSAGE);
                break;
            case "Overflow Error":
                JOptionPane.showMessageDialog(null, overflowErrorMessage, warningTxt, JOptionPane.WARNING_MESSAGE);
            case "Retrieval Error":
                JOptionPane.showMessageDialog(null, retrievalErrorMessage, errorTxt, JOptionPane.ERROR_MESSAGE);
                break;
            case "Uncalled Error":
                JOptionPane.showMessageDialog(null, uncalledErrorMessage, errorTxt, JOptionPane.ERROR_MESSAGE);
                break;
            case "Present Elsewhere Error":
                JOptionPane.showMessageDialog(null, addedElsewhereErrorMessage, errorTxt, JOptionPane.ERROR_MESSAGE);
                break;
            case "Already Added Error":
                JOptionPane.showMessageDialog(null, addedAlreadyErrorMessage, errorTxt, JOptionPane.ERROR_MESSAGE);
                break;
            case "Success":
                JOptionPane.showMessageDialog(null, successAddtionMessage, "Appointment Added!", JOptionPane.INFORMATION_MESSAGE);
                insertFrame.dispose();
                break;
            default:
                JOptionPane.showMessageDialog(null, errorMessage, errorTxt, JOptionPane.ERROR_MESSAGE);
                break;
        }

    }

}
