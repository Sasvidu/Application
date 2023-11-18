package com.sasvidu;

import data.*;
import forms.HomeFrame;

import java.time.*;
import java.util.*;

public class InsertManager {

    private static InsertManager instance;
    private ScheduleCollection schedules = ScheduleCollection.getScheduleCollection();
    private AppointmentIdCollection appointments = AppointmentIdCollection.getAppointmentIdCollection();
    private TreatmentFactory treatmentFactory = TreatmentFactory.getTreatmentFactory();
    private String[] unavailableDays = {"TUESDAY", "THURSDAY", "FRIDAY"};

    private final LocalTime mondayStartTime = LocalTime.parse("18:00");
    private final LocalTime wednesdayStartTime = LocalTime.parse("18:00");
    private final LocalTime saturdayStartTime = LocalTime.parse("15:00");
    private final LocalTime sundayStartTime = LocalTime.parse("15:00");

    private final LocalTime mondayEndTime = LocalTime.parse("21:00");
    private final LocalTime wednesdayEndTime = LocalTime.parse("21:00");
    private final LocalTime saturdayEndTime = LocalTime.parse("22:00");
    private final LocalTime sundayEndTime = LocalTime.parse("22:00");

    private final String retrievalError = "Retrieval Error";
    private final String nullError = "Null Error";
    private final String unavailableError = "Unavailable Error";
    private final String overflowError = "Overflow Error";
    private final String error = "Error";
    private final String success = "Success";

    private InsertManager() {
    }

    public static InsertManager getInsertManager() {

        if (instance == null) {
            instance = new InsertManager();
        }
        return instance;

    }

    public String checkDate(Date selectedDate, String treatmentType) {

        String availableTime;
        //See if the user has indeed selected a date:
        if (selectedDate != null) {
            LocalDate date = LocalDate.ofInstant(selectedDate.toInstant(), ZoneId.systemDefault()); //Convert the selected date to a LocalDate object, which is easier to work with
            String dayOfWeek = date.getDayOfWeek().toString(); //Determine which day of week (ex: Monday, Tuesday) the date is
            //See if the selected date is one of the weeks in which the doctor is unavailable:
            if (!isAnUnavailableDay(dayOfWeek)) {
                //See whether a schedule object has already been created, if it is, we need to confirm that the schedule is not full:
                if (!schedules.hasSchedule(date)) {
                    if (getStartingTime(dayOfWeek) != error) {
                        availableTime = getStartingTime(dayOfWeek);
                    } else {
                        return retrievalError;
                    }
                } else {
                    //See if the schedule for the day has enough time to accommodate the patient's appointment:
                    short appointmentDuration = treatmentFactory.getTreatment(treatmentType).getTimeInMinutes(); //Find how long it takes for the treatment
                    Schedule scheduleOfSelectedDate = schedules.getSchedule(date); //Get the schedule for the desired date
                    availableTime = scheduleOfSelectedDate.getAvailableTime().toString(); //Get the available time for an appointment
                    LocalTime properAvailableTime = LocalTime.parse(availableTime);
                    properAvailableTime = properAvailableTime.plusMinutes(appointmentDuration); //Get the time once the treatment is completed
                    LocalTime properEndTime = LocalTime.parse(getEndingTime(dayOfWeek)); //Get the time at which the doctor's shift is scheduled to end for the day
                    int comparisonValue = properEndTime.compareTo(properAvailableTime); //Compare the shift ending time and time after completing the treatment
                    if (comparisonValue < 0) {
                        return overflowError; //If completing the treatment takes longer than the shift ending time, give an overflow error which can be interpreted by the InsertFrame
                    }
                }
                return availableTime;
            } else {
                return unavailableError; //If the day selected is not an active channeling date, give an error that can be interpreted by the InsertFrame.
            }
        } else {
            return nullError; //If the user has not selected any date, give an error that can be interpreted by the InsertFrame.
        }

    }

    public String addAppointment(Date selectedDate, String patientName, String patientAddress, String patientTelephoneNumber, String treatmentType) {

        String availableTime = checkDate(selectedDate, treatmentType);
        if (availableTime.matches(nullError) || availableTime.matches(unavailableError) || availableTime.matches(overflowError) || availableTime.matches(retrievalError)) {
            return availableTime; //Run the checkDate Function to see if the date for which the appointment is supposed to be reserved for is valid. If it is not, return an error message that can be interpreted by the InsertFrame.
        } else {
            LocalDate date = LocalDate.ofInstant(selectedDate.toInstant(), ZoneId.systemDefault()); //Convert the selected date to a LocalDate object.
            //See if a schedule has already been created for the date:
            if (schedules.hasSchedule(date)) {
                return insertAppointment(patientName, patientAddress, patientTelephoneNumber, treatmentType, date); //Insert the appointment into the schedule and the appointment list.
            } else {
                schedules.addSchedule(date); //If a schedule is not already available, create a new one.
                return insertAppointment(patientName, patientAddress, patientTelephoneNumber, treatmentType, date); //Insert the appointment into the schedule and the appointment list.
            }
        }

    }

    private boolean isAnUnavailableDay(String dayOfWeek) {

        for (String unavailableDay : unavailableDays) {
            if (unavailableDay.matches(dayOfWeek)) {
                return true;
            }
        }
        return false;
    }

    private String getStartingTime(String dayOfWeek) {

        switch (dayOfWeek) {
            case "MONDAY":
                return mondayStartTime.toString();
            case "WEDNESDAY":
                return wednesdayStartTime.toString();
            case "SATURDAY":
                return saturdayStartTime.toString();
            case "SUNDAY":
                return sundayStartTime.toString();
            default:
                return error;
        }

    }

    private String getEndingTime(String dayOfWeek) {

        switch (dayOfWeek) {
            case "MONDAY":
                return mondayEndTime.toString();
            case "WEDNESDAY":
                return wednesdayEndTime.toString();
            case "SATURDAY":
                return saturdayEndTime.toString();
            case "SUNDAY":
                return sundayEndTime.toString();
            default:
                return error;
        }

    }

    private int createId() {

        Random random = new Random();
        int id = 100;
        while (appointments.hasId(id)) {
            id = random.nextInt(1000);
        }
        return id;

    }

    private String insertAppointment(String patientName, String patientAddress, String patientTelephoneNumber, String treatmentType, LocalDate date) {
        Schedule schedule = schedules.getSchedule(date);
        Treatment treatment = treatmentFactory.getTreatment(treatmentType); //Get the object representing the treatment required.
        int appointmentId = createId(); //Create a unique Id for the appointment
        Appointment appointment = new Appointment(appointmentId, patientName, patientAddress, patientTelephoneNumber, treatment); //Create a new appointment object to encapsulate all the information.
        String response = schedule.addAppointment(appointment); //Invoke operation for adding appointment to the schedule of the day, and store the result in a string variable.
        //See if the operation was successful
        if (response.matches(success)) {
            appointments.addAppointment(appointmentId, appointment); //Add the appointment to a hashmap of appointments
            refreshHomeFrame();
            return success; //Return a success message
        } else {
            return response; //If addition is not successful, forward the error to the JFrame.
        }
    }

    private void refreshHomeFrame(){
        HomeFrame.getHomeFrame().removeHomeFrame();
        HomeFrame.getHomeFrame();
    }

}
