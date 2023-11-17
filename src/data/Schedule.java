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

    public String addAppointment(Appointment appointment){

            LocalTime newAvailableTime = availableTime.plusMinutes(appointment.getTreatment().getTimeInMinutes());
            int comparisonValue = endTime.compareTo(newAvailableTime);
            if(comparisonValue >= 0){
                if(!listOfAppointments.contains(appointment)) {
                    if (appointment.getDate() == null && appointment.getTime() == null) {
                        appointment.setDate(date);
                        appointment.setTime(availableTime);
                        listOfAppointments.add(appointment);
                        availableTime = newAvailableTime;
                        return "Success";
                    }else{
                        return "Present Elsewhere Error";
                    }
                }else{
                    return "Already Added Error";
                }
            }
            else{
                return "Overflow Error";
            }

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
