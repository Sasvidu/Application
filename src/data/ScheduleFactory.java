package data;

import forms.HomeFrame;

import java.time.LocalDate;
import java.time.LocalTime;

public class ScheduleFactory {

    private final LocalTime mondayStartTime = LocalTime.parse("18:00");
    private final LocalTime mondayEndTime = LocalTime.parse("21:00");

    private final LocalTime wednesdayStartTime = LocalTime.parse("18:00");
    private final LocalTime wednesdayEndTime = LocalTime.parse("21:00");

    private final LocalTime saturdayStartTime = LocalTime.parse("15:00");
    private final LocalTime saturdayEndTime = LocalTime.parse("22:00");

    private final LocalTime sundayStartTime = LocalTime.parse("15:00");
    private final LocalTime sundayEndTime = LocalTime.parse("22:00");

    private String[] unavailableDays = {"TUESDAY", "THURSDAY", "FRIDAY"};

    private static ScheduleFactory instance;

    private ScheduleFactory(){}

    public static ScheduleFactory getScheduleFactory(){

        if(instance == null){
            instance = new ScheduleFactory();
        }
        return instance;

    }

    public Schedule getSchedule(String date){

        LocalDate properDate = LocalDate.parse(date);
        String dayOfWeek = properDate.getDayOfWeek().toString();

        if(date == null || date == ""){
            return null;
        }else if(isAnUnavailableDay(dayOfWeek)){
            return null;
        }else{
            if(dayOfWeek == "MONDAY"){
                Schedule schedule = new MondaySchedule(properDate, mondayStartTime, mondayEndTime);
                HomeFrame.getHomeFrame().addObservable(schedule);
                return schedule;
            }
            if(dayOfWeek == "WEDNESDAY"){
                Schedule schedule = new WednesdaySchedule(properDate, wednesdayStartTime, wednesdayEndTime);
                HomeFrame.getHomeFrame().addObservable(schedule);
                return schedule;
            }
            if(dayOfWeek == "SATURDAY"){
                Schedule schedule =  new SaturdaySchedule(properDate, saturdayStartTime, saturdayEndTime);
                HomeFrame.getHomeFrame().addObservable(schedule);
                return schedule;
            }
            if (dayOfWeek == "SUNDAY"){
                Schedule schedule = new SundaySchedule(properDate, sundayStartTime, sundayEndTime);
                HomeFrame.getHomeFrame().addObservable(schedule);
                return schedule;
            }
        }

    return null;

    }

    private boolean isAnUnavailableDay(String dayOfWeek){

        for (String unavailableDay: unavailableDays) {
            if(unavailableDay.matches(dayOfWeek)){
                return true;
            }
        }
        return false;
    }

}
