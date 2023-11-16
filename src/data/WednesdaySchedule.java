package data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;

public class WednesdaySchedule extends Schedule{

    protected WednesdaySchedule(LocalDate date, LocalTime startTime, LocalTime endTime){
        this.date = date;
        this.startTime = this.availableTime = startTime;
        this.endTime = endTime;
        listOfAppointments = new LinkedList<>();
    }

}
