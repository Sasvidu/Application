package data;

import forms.HomeFrame;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.Iterator;

public abstract class Schedule implements Observable, Cloneable{

    protected LocalDate date;
    protected LocalTime startTime;
    protected LocalTime endTime;
    protected LocalTime availableTime;
    protected LinkedList<Appointment> listOfAppointments;
    private LinkedList<Observer> observers = new LinkedList<>();

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
                        HomeFrame.getHomeFrame().addObservable(appointment);
                        notifyObservers();
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
        int timeChangeInMinutes = treatment.getTimeInMinutes() - oldAppointment.getTreatment().getTimeInMinutes();
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

    public String[][] getAppointmentsArrayForPatient(String patientName) {
        // Use a dynamic list instead of a fixed-size array
        List<String[]> list = new ArrayList<>();

        for (Appointment appointment : listOfAppointments) {
            String currentPatientName = appointment.getPatient().getName();
            if (currentPatientName.matches(patientName)) {
                // Create an array for each appointment
                String[] appointmentArray = new String[8];
                appointmentArray[0] = appointment.getDate().toString();
                appointmentArray[1] = appointment.getTime().toString();
                appointmentArray[2] = String.valueOf(appointment.getAppointmentId());
                appointmentArray[3] = appointment.getPatient().getName();
                appointmentArray[4] = appointment.getPatient().getAddress();
                appointmentArray[5] = appointment.getPatient().getTelephoneNumber();
                appointmentArray[6] = appointment.getTreatment().getTreatmentType();
                appointmentArray[7] = String.valueOf(appointment.isPaid());

                // Add the array to the list
                list.add(appointmentArray);
            }
        }

        // Convert the list to a 2D array before returning
        return list.toArray(new String[0][0]);
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    @Override
    public Schedule clone() throws CloneNotSupportedException{
        Schedule cloned = (Schedule) super.clone();
        LinkedList<Appointment> clonedAppointments = new LinkedList<>();
        for (Appointment appointment : listOfAppointments) {
            clonedAppointments.addLast(appointment.clone());
        }
        cloned.listOfAppointments = clonedAppointments;
        return cloned;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Schedule scheduleObj = (Schedule) obj;
        return Objects.equals(this.date, scheduleObj.date) &&
                Objects.equals(this.startTime, scheduleObj.startTime) &&
                Objects.equals(this.endTime, scheduleObj.endTime) &&
                Objects.equals(this.availableTime, scheduleObj.availableTime) &&
                deepEquals (this.listOfAppointments, scheduleObj.listOfAppointments);
    }

    private boolean deepEquals(Object object1, Object object2){
        return Objects.deepEquals(object1, object2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, startTime, endTime, availableTime, listOfAppointments);
    }

}

