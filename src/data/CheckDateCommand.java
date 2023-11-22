package data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

//Command to handle the click event of the Check date button.
//It should check whether the schedule of a day a user has requested allows enough time for an appointment.
//If there is time, it passes the time possible, if not, it sends an error message.
//The command takes a date parameter for the day the user requests, and a treatment type parameter to see how much time needs to be reserved.

public class CheckDateCommand implements Command<String>{

    private ScheduleCollection schedules = ScheduleCollection.getScheduleCollection();
    private AppointmentIdCollection appointments = AppointmentIdCollection.getAppointmentIdCollection();
    private TreatmentFactory treatmentFactory = TreatmentFactory.getTreatmentFactory();

    private Date selectedDate;
    private String treatmentType;

    private String result;

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

    public CheckDateCommand(Date selectedDate, String treatmentType){
        this.selectedDate = selectedDate;
        this.treatmentType = treatmentType;
    }

    @Override
    public void execute() {
        String availableTime = null;
        if (selectedDate != null) {
            //Convert the selected date to a LocalDate object, which is easier to work with
            LocalDate date = LocalDate.ofInstant(selectedDate.toInstant(), ZoneId.systemDefault());
            String dayOfWeek = date.getDayOfWeek().toString();
            //See if the selected date is one of the days in week in which the doctor is unavailable
            if (!isAnUnavailableDay(dayOfWeek)) {
                //See whether a schedule object has already been created, if it is, we need to confirm that the schedule is not full
                if (!schedules.hasSchedule(date)) {
                    //If a schedule hasn't been created, the available time is the default schedule starting time for the day
                    if (getStartingTime(dayOfWeek) != error) {
                        availableTime = getStartingTime(dayOfWeek);
                    } else {
                        //If the method to get the starting time for the day fails, send an error message that can be interpreted by the Insert frame
                        result = retrievalError;
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
                        result = overflowError;
                    }
                }
                //If the there is enough time for the patient's request and the code ran without errors, return the available time
                result = availableTime;
            } else {
                //If the day selected is not an active channeling date, give an error that can be interpreted by the InsertFrame
                result = unavailableError;
            }
        } else {
            //If the user has not selected any date, give an error that can be interpreted by the InsertFrame
            result = nullError;
        }
    }

    @Override
    public String getResult() {
        return result;
    }

    //Method that checks whether a day of the week supports having a schedule
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

    @Override
    public void undo(){
        //MementoManager.getMementoManager().undo();
    }

    @Override
    public void redo(){
        //MementoManager.getMementoManager().redo();
    }


}
