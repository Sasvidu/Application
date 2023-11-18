package com.sasvidu;

import data.*;
import forms.HomeFrame;
import forms.InsertFrame;
import forms.Invoice;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.Map;

public class HomeManager {

    private static HomeManager instance;

    private HomeManager(){}

    public static HomeManager getHomeManager(){

        if(instance == null){
            instance = new HomeManager();
        }
        return instance;

    }

    public void insert(String patientName, String patientAddress, String patientTelephoneNumber, String treatmentType){

        InsertFrame insertFrame = new InsertFrame(patientName, patientAddress, patientTelephoneNumber, treatmentType);

    }

    public String[] search(String appointmentId){

        try {
            int properAppointmentId = Integer.parseInt(appointmentId);
            AppointmentIdCollection appointments = AppointmentIdCollection.getAppointmentIdCollection();
            Appointment appointment = appointments.getAppointment(properAppointmentId);
            String patientName = appointment.getPatient().getName();
            String patientAddress = appointment.getPatient().getAddress();
            String patientTelephoneNumber = appointment.getPatient().getTelephoneNumber();
            String treatmentType = appointment.getTreatment().getTreatmentType();
            String isPaid = String.valueOf(appointment.isPaid());
            return new String[]{appointmentId, patientName, patientAddress, patientTelephoneNumber, treatmentType, isPaid};
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "The ID must be a number!", "Warning", JOptionPane.WARNING_MESSAGE);
            return null;
        }

    }

    public void edit(String appointmentId, String patientName, String patientAddress, String patientTelephoneNumber, String treatmentType){

        try{
            int properAppointmentId = Integer.parseInt(appointmentId);
            AppointmentIdCollection appointments = AppointmentIdCollection.getAppointmentIdCollection();
            ScheduleCollection schedules = ScheduleCollection.getScheduleCollection();
            Appointment appointment = appointments.getAppointment(properAppointmentId);
            LocalDate date = appointment.getDate();
            Treatment treatment = TreatmentFactory.getTreatmentFactory().getTreatment(treatmentType);
            Schedule schedule = schedules.getSchedule(date);
            schedule.editAppointment(appointment, patientName, patientAddress, patientTelephoneNumber, treatment);
            JOptionPane.showMessageDialog(null, "Appointment successfully edited", "Success!", JOptionPane.INFORMATION_MESSAGE);
            refreshHomeFrame();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Exception occured: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void pay(String appointmentId){

        try {
            int properAppointmentId = Integer.parseInt(appointmentId);
            AppointmentIdCollection appointments = AppointmentIdCollection.getAppointmentIdCollection();
            Appointment appointment = appointments.getAppointment(properAppointmentId);
            String fee = String.valueOf(appointment.getTreatment().getFee());
            int confirmation = JOptionPane.showConfirmDialog(null, "The payment is : LkR. " + fee + "0/=\n\nConfirm Payment", "Payment Confirmation", JOptionPane.YES_NO_OPTION);
            if(confirmation == 0){
                appointment.pay();
                JOptionPane.showMessageDialog(null, "Payment Successful!", "Paid", JOptionPane.INFORMATION_MESSAGE);
                refreshHomeFrame();
                Invoice invoice= new Invoice(appointment);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Exception occured: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

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

    private void refreshHomeFrame(){
        HomeFrame.getHomeFrame().removeHomeFrame();
        HomeFrame.getHomeFrame();
    }

}
