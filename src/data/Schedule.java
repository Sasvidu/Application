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

        try{
            LocalTime newAvailableTime = availableTime.plusMinutes(appointment.getTreatment().getTimeInMinutes());
            int comparisonValue = endTime.compareTo(newAvailableTime);
            if(comparisonValue >= 0){
                if(!listOfAppointments.contains(appointment)) {
                    if (appointment.getDate() == null && appointment.getTime() == null) {
                        appointment.setDate(date);
                        appointment.setTime(availableTime);
                        listOfAppointments.add(appointment);
                        availableTime = newAvailableTime;
                    }else{
                        throw new Exception("The appointment is already added to another schedule.");
                    }
                }else{
                    throw new Exception("The appointment is already added to the schedule.");
                }
            }
            else{
                throw new Exception("Appointment exceeds the doctor's scheduled time for the day.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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
