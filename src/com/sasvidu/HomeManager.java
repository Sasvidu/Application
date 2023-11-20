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
    //Get reference to the appointment hashmap
    private AppointmentIdCollection appointments = AppointmentIdCollection.getAppointmentIdCollection();

    //Private constructor
    private HomeManager(){}

    //Public method to retrieve the unique instance
    public static HomeManager getHomeManager(){
        if(instance == null){
            instance = new HomeManager();
        }
        return instance;
    }

    //Method to handle the click event of the insert button
    public void insert(String patientName, String patientAddress, String patientTelephoneNumber, String treatmentType){
        new InsertFrame(patientName, patientAddress, patientTelephoneNumber, treatmentType);
    }

    //Method to handle the click event of the search button
    public String[] search(String appointmentId){

        try {

            //Convert the appointment id into and integer
            int properAppointmentId = Integer.parseInt(appointmentId);
            //Get the appointment object from the appointment hashmap
            Appointment appointment = appointments.getAppointment(properAppointmentId);
            //Read the values from the appointment
            String patientName = appointment.getPatient().getName();
            String patientAddress = appointment.getPatient().getAddress();
            String patientTelephoneNumber = appointment.getPatient().getTelephoneNumber();
            String treatmentType = appointment.getTreatment().getTreatmentType();
            String isPaid = String.valueOf(appointment.isPaid());
            //Return the results in an array
            return new String[]{appointmentId, patientName, patientAddress, patientTelephoneNumber, treatmentType, isPaid};

        }catch (Exception e){

            //If the string cannot be converted to an integer, shoe a warning message
            JOptionPane.showMessageDialog(null, "The ID must be a number!", "Warning", JOptionPane.WARNING_MESSAGE);
            return null;

        }

    }

    public void edit(String appointmentId, String patientName, String patientAddress, String patientTelephoneNumber, String treatmentType){

        try{

            //Convert the appointment id into an integer
            int properAppointmentId = Integer.parseInt(appointmentId);
            //Get reference to the schedules collection
            ScheduleCollection schedules = ScheduleCollection.getScheduleCollection();
            //Get the appointment object from the appointment hashmap
            Appointment appointment = appointments.getAppointment(properAppointmentId);

            //Check if the appointment has already been paid for
            if(appointment.isPaid()){

                //Display a message box giving a message that the appointment has already been paid for
                JOptionPane.showMessageDialog(null, "Appointment has already been paid for.", "Already Paid", JOptionPane.INFORMATION_MESSAGE);

            }else{

                //Get the appointment date and obtain the relevant schedule
                LocalDate date = appointment.getDate();
                Schedule schedule = schedules.getSchedule(date);
                //Get the relevant treatment object
                Treatment treatment = TreatmentFactory.getTreatmentFactory().getTreatment(treatmentType);
                //Call the update method for the schedule
                schedule.editAppointment(appointment, patientName, patientAddress, patientTelephoneNumber, treatment);
                //Show a success message
                JOptionPane.showMessageDialog(null, "Appointment successfully edited", "Success!", JOptionPane.INFORMATION_MESSAGE);
                //Reload data
                HomeFrame.getHomeFrame().refreshHomeFrame();

            }

        }catch (Exception e){

            //If an exception occurs, display it in an error message
            JOptionPane.showMessageDialog(null, "Exception occured: " + e, "Error", JOptionPane.ERROR_MESSAGE);

        }

    }

    public void pay(String appointmentId){

        try {

            //Convert the appointment id into an integer
            int properAppointmentId = Integer.parseInt(appointmentId);
            //Get the appointment object from the appointments hashmap
            Appointment appointment = appointments.getAppointment(properAppointmentId);

            //Check if the appointment has already been paid for
            if(appointment.isPaid()){

                //Display a message box giving a message that the appointment has already been paid for
                JOptionPane.showMessageDialog(null, "Appointment has already been paid for.", "Already Paid", JOptionPane.INFORMATION_MESSAGE);

            }else{

                //Get the value of the fee
                String fee = String.valueOf(appointment.getTreatment().getFee());
                //Display it in a message box and await user's reply
                int confirmation = JOptionPane.showConfirmDialog(null, "The payment is : LkR. " + fee + "0/=\n\nConfirm Payment", "Payment Confirmation", JOptionPane.YES_NO_OPTION);
                //See whether the reply is a confirmation
                if (confirmation == 0) {
                    //Update the data
                    appointment.pay();
                    //Show success message
                    JOptionPane.showMessageDialog(null, "Payment Successful!", "Paid", JOptionPane.INFORMATION_MESSAGE);
                    //Reload the data
                    HomeFrame.getHomeFrame().refreshHomeFrame();
                    //Display an invoice
                    new Invoice(appointment);
                }

            }

        }catch (Exception e){

            //If an exception occurs, display it in an error message
            JOptionPane.showMessageDialog(null, "Exception occured: " + e, "Error", JOptionPane.ERROR_MESSAGE);

        }

    }

    //Function to get the data for the appointments table
    public String[][] getAppointmentList(){

        String[][] appointmentsList = null;

        var schedules = ScheduleCollection.getScheduleCollection();
        var schedulesIterator = schedules.getIterator();

        Map.Entry<LocalDate, Schedule> entry;
        Schedule schedule;

        while (schedulesIterator.hasNext()){
            entry = (Map.Entry<LocalDate, Schedule>) schedulesIterator.next();
            schedule = entry.getValue();
            String[][] currentScheduleAppointmentList = schedule.getAppointmentsArray();
            appointmentsList = combineArraysVertically(appointmentsList, currentScheduleAppointmentList);
        }

        return appointmentsList;

    }

    // Method to combine two arrays vertically:
    private static String[][] combineArraysVertically(String[][] array1, String[][] array2) {
        if(array1 == null){
            return array2;
        }else {
            int rows = array1.length + array2.length;
            int columns = array1[0].length;

            String[][] resultArray = new String[rows][columns];

            for (int i = 0; i < array1.length; i++) {
                System.arraycopy(array1[i], 0, resultArray[i], 0, array1[i].length);
            }

            for (int i = 0; i < array2.length; i++) {
                System.arraycopy(array2[i], 0, resultArray[i + array1.length], 0, array2[i].length);
            }

            return resultArray;
        }
    }

}
