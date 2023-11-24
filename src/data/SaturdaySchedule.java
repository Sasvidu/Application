package data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;

public class SaturdaySchedule extends Schedule{

    //Parameterized Constructor
    protected SaturdaySchedule(LocalDate date, LocalTime startTime, LocalTime endTime){
        this.date = date;
        this.startTime = this.availableTime = startTime;
        this.endTime = endTime;
        listOfAppointments = new LinkedList<>();
    }

}
