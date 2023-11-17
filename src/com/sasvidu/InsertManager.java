package com.sasvidu;

import data.Schedule;
import data.ScheduleCollection;
import java.time.*;
import java.util.*;

public class InsertManager {

    private static InsertManager instance;
    private ScheduleCollection schedules;
    private String[] unavailableDays = {"TUESDAY", "THURSDAY", "FRIDAY"};

    private final LocalTime mondayStartTime = LocalTime.parse("18:00");
    private final LocalTime wednesdayStartTime = LocalTime.parse("18:00");
    private final LocalTime saturdayStartTime = LocalTime.parse("15:00");
    private final LocalTime sundayStartTime = LocalTime.parse("15:00");

    private InsertManager(){
        schedules = ScheduleCollection.getScheduleCollection();
    }

    public static InsertManager getInsertManager(){

        if(instance == null){
            instance = new InsertManager();
        }
        return instance;

    }

    public String checkDate(Date selectedDate){

        String availableTime;
        //See if the user has indeed selected a date:
        if(selectedDate != null) {
            //Convert the selected date to a LocalDate object, which is easier to work with
            LocalDate date = LocalDate.ofInstant(selectedDate.toInstant(), ZoneId.systemDefault());
            //Determine which day of week (ex: Monday, Tuesday) the date is
            String dayOfWeek = date.getDayOfWeek().toString();
            //See if the selected date is one of the weeks in which the doctor is unavailable:
            if(!isAnUnavailableDay(dayOfWeek)){
                //See whether a schedule object has already been created, if it is, we need to confirm that the schedule is not full:
                if(!schedules.hasSchedule(date)){
                    if(getStartingTime(dayOfWeek) != "Error"){
                        availableTime = getStartingTime(dayOfWeek);
                    }else{
                        return "Retrieval Error";
                    }
                }else{
                    Schedule scheduleOfSelectedDate = schedules.getSchedule(date);
                    availableTime = scheduleOfSelectedDate.getAvailableTime().toString();
                }
                return availableTime;
            }else{
                return "Unavailable Error";
            }
        } else {
            return "Null Error";
        }

    }

    private boolean isAnUnavailableDay(String dayOfWeek){

        for (String unavailableDay: unavailableDays) {
            if(unavailableDay.matches(dayOfWeek)){
                return true;
            }
        }
        return false;
    }

    private String getStartingTime(String dayOfWeek){

        switch (dayOfWeek){
            case "MONDAY" : return mondayStartTime.toString();
            case "WEDNESDAY" : return wednesdayStartTime.toString();
            case "SATURDAY" : return saturdayStartTime.toString();
            case "SUNDAY" : return sundayStartTime.toString();
            default: return "Error";
        }

    }

}
