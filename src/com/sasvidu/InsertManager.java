package com.sasvidu;

import data.*;
import forms.HomeFrame;

import java.time.*;
import java.util.*;

public class InsertManager {

    //This class is used to implement the functionality of the Insert(Appointment) GUI component. It is a singleton.

    //Private, class variable to hold the unique instance
    private static InsertManager instance;

    //Instantiate all the data structures that need to be accessed
    private ScheduleCollection schedules = ScheduleCollection.getScheduleCollection();
    private AppointmentIdCollection appointments = AppointmentIdCollection.getAppointmentIdCollection();

    //Instantiate the factory class for treatments
    private TreatmentFactory treatmentFactory = TreatmentFactory.getTreatmentFactory();

    //Declare the days on which the doctor is unavailable
    private String[] unavailableDays = {"TUESDAY", "THURSDAY", "FRIDAY"};

    //Declare the schedule start and end times for each day the doctor is available
    private final LocalTime mondayStartTime = LocalTime.parse("18:00");
    private final LocalTime wednesdayStartTime = LocalTime.parse("18:00");
    private final LocalTime saturdayStartTime = LocalTime.parse("15:00");
    private final LocalTime sundayStartTime = LocalTime.parse("15:00");

    private final LocalTime mondayEndTime = LocalTime.parse("21:00");
    private final LocalTime wednesdayEndTime = LocalTime.parse("21:00");
    private final LocalTime saturdayEndTime = LocalTime.parse("22:00");
    private final LocalTime sundayEndTime = LocalTime.parse("22:00");

    //Declare variables to represent common error messages that need to be returned to the insert window
    private final String retrievalError = "Retrieval Error";
    private final String nullError = "Null Error";
    private final String unavailableError = "Unavailable Error";
    private final String overflowError = "Overflow Error";
    private final String error = "Error";
    private final String success = "Success";

    //Private constructor
    private InsertManager() {}

    //Public method to retrieve the unique instance
    public static InsertManager getInsertManager() {
        if (instance == null) {
            instance = new InsertManager();
        }
        return instance;
    }

    //Method to handle the click event of the Check date button.
    //It should check whether the schedule of a day a user has requested allows enough time for an appointment.
    //If there is time, it passes the time possible, if not, it sends an error message.
    //The method takes a date parameter for the day the user requests, and a treatment type parameter to see how much time needs to be reserved.
    public String checkDate(Date selectedDate, String treatmentType) {

        //Variable to store the time at which an appointment is available
        String availableTime;

        //See if the user has indeed selected a date
        if (selectedDate != null) {

            //Convert the selected date to a LocalDate object, which is easier to work with
            LocalDate date = LocalDate.ofInstant(selectedDate.toInstant(), ZoneId.systemDefault());
            //Determine which day of week (ex: Monday, Tuesday) the date is
            String dayOfWeek = date.getDayOfWeek().toString();

            //See if the selected date is one of the days in week in which the doctor is unavailable
            if (!isAnUnavailableDay(dayOfWeek)) {

                //See whether a schedule object has already been created, if it is, we need to confirm that the schedule is not full
                if (!schedules.hasSchedule(date)) {

                    //If a schedule hasn't been created, the available time is the default schedule starting time for the day
                    if (getStartingTime(dayOfWeek) != error) {
                        availableTime = getStartingTime(dayOfWeek);
                    } else {
                        //IIf the method to get the starting time for the day fails, send an error message that can be interpreted by the Insert frame
                        return retrievalError;
                    }

                } else {

                    //If a schedule is created, we should see if it has enough time to accommodate the patient's appointment.
                    //Find how long it takes for the treatment
                    short appointmentDuration = treatmentFactory.getTreatment(treatmentType).getTimeInMinutes();
                    //Get the schedule for the desired date from the schedules collection (instantiated near the beginning)
                    Schedule scheduleOfSelectedDate = schedules.getSchedule(date);
                    //Get the available time for an appointment, create a string variable for returning, and a LocalTIme variable for further program logic checks
                    availableTime = scheduleOfSelectedDate.getAvailableTime().toString();
                    LocalTime properAvailableTime = LocalTime.parse(availableTime);
                    //Calculate the time once the treatment is completed
                    properAvailableTime = properAvailableTime.plusMinutes(appointmentDuration);
                    //Get the time at which the doctor's shift is scheduled to end for the day
                    LocalTime properEndTime = LocalTime.parse(getEndingTime(dayOfWeek));

                    //Compare the shift ending time and time after completing the treatment
                    int comparisonValue = properEndTime.compareTo(properAvailableTime);
                    if (comparisonValue < 0) {
                        //If completing the treatment takes longer than the shift ending time, give an overflow error which can be interpreted by the InsertFrame
                        return overflowError;
                    }

                }
                //If the there is enough time for the patient's request and the code ran without errors, return the available time
                return availableTime;

            } else {
                //If the day selected is not an active channeling date, give an error that can be interpreted by the InsertFrame
                return unavailableError;
            }
        } else {
            //If the user has not selected any date, give an error that can be interpreted by the InsertFrame
            return nullError;
        }

    }

    //Method to handle the click event of the appointment button.
    //It takes in parameters for appointment details and the date during which the appointment is to be placed. The time will be the time calculated by the system via the check date method.
    public String addAppointment(Date selectedDate, String patientName, String patientAddress, String patientTelephoneNumber, String treatmentType) {

        //Call the checkDate function to see whether there is time for the appointment to be reserved for the requested date, and get the available time if there is
        String availableTime = checkDate(selectedDate, treatmentType);

        if (availableTime.matches(nullError) || availableTime.matches(unavailableError) || availableTime.matches(overflowError) || availableTime.matches(retrievalError)) {

            //If the checkDate functiom does not return an available time, forward the error message to the GUI component, to which it can react accordingly
            return availableTime;

        } else {

            //Convert the selected date to a LocalDate object
            LocalDate date = LocalDate.ofInstant(selectedDate.toInstant(), ZoneId.systemDefault());

            //See if a schedule has already been created for the date:
            if (schedules.hasSchedule(date)) {

                //Insert the appointment into the schedule and the appointment list
                return insertAppointment(patientName, patientAddress, patientTelephoneNumber, treatmentType, date);

            } else {

                //If a schedule is not already available, create a new one
                schedules.addSchedule(date);
                //Insert the appointment into the schedule and the appointment list
                return insertAppointment(patientName, patientAddress, patientTelephoneNumber, treatmentType, date);

            }

        }

    }

    //Private method that checks whether a day of the week supports having a schedule
    private boolean isAnUnavailableDay(String dayOfWeek) {
        for (String unavailableDay : unavailableDays) {
            if (unavailableDay.matches(dayOfWeek)) {
                return true;
            }
        }
        return false;
    }

    //Private method to retrieve the starting time of the schedule for a particular day
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

    //Private method to retrieve the ending time of the schedule for a particular day
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

    //Private method that generates a random id for an appointment
    private int createId() {
        Random random = new Random();
        int id = 100;
        while (appointments.hasId(id)) {
            id = random.nextInt(1000);
        }
        return id;
    }

    //Private method to represt the common operations for inserting an appointment into the data structures, called by the addAppointment method
    private String insertAppointment(String patientName, String patientAddress, String patientTelephoneNumber, String treatmentType, LocalDate date) {

        //Obtain the schedule for the dat
        Schedule schedule = schedules.getSchedule(date);
        //Get the object representing the treatment required
        Treatment treatment = treatmentFactory.getTreatment(treatmentType);
        //Create a unique Id for the appointment
        int appointmentId = createId();
        //Create a new appointment object to encapsulate all the information
        Appointment appointment = new Appointment(appointmentId, patientName, patientAddress, patientTelephoneNumber, treatment);
        //Invoke operation for adding appointment to the schedule of the day, and store the result in a string variable
        String response = schedule.addAppointment(appointment);

        //See if the operation was successful
        if (response.matches(success)) {

            //Add the appointment to a hashmap of appointments if the appointment was added to the schedule
            appointments.addAppointment(appointmentId, appointment);
            //Re-instantiate the home window so that its table is updated
            HomeFrame.getHomeFrame().refreshHomeFrame();
            //Return a success message
            return success;

        } else {

            //If addition is not successful, forward the error to the JFrame
            return response;

        }

    }

}
