package data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;

public abstract class Schedule {

    protected LocalDate date;
    protected LocalTime startTime;
    protected LocalTime endTime;
    protected LocalTime availableTime;
    protected LinkedList<Appointment> listOfAppointments;

    public void addAppointment(Appointment appointment){



    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public LocalTime getAvailableTime() {
        return availableTime;
    }

    public LinkedList<Appointment> getListOfAppointments() {
        return listOfAppointments;
    }
}

//LocalDate myObj = LocalDate.now(); - Current Time
// LocalDate.of(); - Specific Time
// LocalTime time = LocalTime.parse("09:00"); - Create a time object from string
// time.plusMinutes(30); - Adds 30 minutes
