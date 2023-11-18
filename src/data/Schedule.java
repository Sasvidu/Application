package data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

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
                        listOfAppointments.addLast(appointment);
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

    public void editAppointment(Appointment oldAppointment, String patientName, String patientAddress, String patientTelephoneNumber, Treatment treatment){

        int index = listOfAppointments.indexOf(oldAppointment);
        Iterator<Appointment> iterator = listOfAppointments.listIterator(index);
        int timeChangeInMinutes = treatment.timeInMinutes = oldAppointment.getTreatment().getTimeInMinutes();
        oldAppointment.setPatient(patientName, patientAddress, patientTelephoneNumber);
        oldAppointment.setTreatment(treatment);
        iterator.next();
        while(iterator.hasNext()){
            Appointment appointment = iterator.next();
            LocalTime newTime = appointment.getTime().plusMinutes(timeChangeInMinutes);
            appointment.setTime(newTime);
        }
        availableTime = availableTime.plusMinutes(timeChangeInMinutes);

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

    public String[][] getAppointmentsArray() {
        String[][] array = new String[listOfAppointments.size()][8];
        int rowIndex = 0;
        for (Appointment appointment : listOfAppointments) {
            array[rowIndex][0] = appointment.getDate().toString();
            array[rowIndex][1] = appointment.getTime().toString();
            array[rowIndex][2] = String.valueOf(appointment.getAppointmentId());
            array[rowIndex][3] = appointment.getPatient().getName();
            array[rowIndex][4] = appointment.getPatient().getAddress();
            array[rowIndex][5] = appointment.getPatient().getTelephoneNumber();
            array[rowIndex][6] = appointment.getTreatment().getTreatmentType();
            array[rowIndex][7] = String.valueOf(appointment.isPaid());
            rowIndex++;
        }
        return array;
    }

}

