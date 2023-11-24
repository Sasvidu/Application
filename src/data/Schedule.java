package data;

import forms.HomeFrame;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.Iterator;

public abstract class Schedule implements Observable, Cloneable{

    //Data to be stored in the schedule
    protected LocalDate date;
    protected LocalTime startTime;
    protected LocalTime endTime;
    protected LocalTime availableTime;
    protected LinkedList<Appointment> listOfAppointments;
    //List of observers
    private LinkedList<Observer> observers = new LinkedList<>();

    //Method to insert an appointment to the schedule
    public String addAppointment(Appointment appointment){
        //Calculate the time by which the appointment would end
        LocalTime newAvailableTime = availableTime.plusMinutes(appointment.getTreatment().getTimeInMinutes());
        //Compare the calculated time to the end time for the schedule
        int comparisonValue = endTime.compareTo(newAvailableTime);
        //See if the end time is larger than the calculated time
        if(comparisonValue >= 0){
            //See if the appointment list already contains that object
            if(!listOfAppointments.contains(appointment)) {
                //See if the appointment has already been allocated to some (other) schedule
                if (appointment.getDate() == null && appointment.getTime() == null) {
                    //Add the appointment and update the schedule's properties
                    appointment.setDate(date);
                    appointment.setTime(availableTime);
                    listOfAppointments.addLast(appointment);
                    availableTime = newAvailableTime;
                    //Register the new appointment as an observable for the HomeFrame and notify it of a state change in the schedule
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

    //Method to edit an appointment within a schedule, and adjust the available time for the day
    public void editAppointment(Appointment oldAppointment, String patientName, String patientAddress, String patientTelephoneNumber, Treatment treatment){
        //Find the appointment object within the linked-list of appointments
        int index = listOfAppointments.indexOf(oldAppointment);
        //Get an iterator to access all the objects succeeding the desired appointment
        Iterator<Appointment> iterator = listOfAppointments.listIterator(index);
        //Calculate the new increase (or decrease) in time taken for the appointment
        int timeChangeInMinutes = treatment.getTimeInMinutes() - oldAppointment.getTreatment().getTimeInMinutes();
        //Update the appointment data
        oldAppointment.setPatient(patientName, patientAddress, patientTelephoneNumber);
        oldAppointment.setTreatment(treatment);
        //Increment the iterator once to ensure the appointment itself isn't traversed
        iterator.next();
        while(iterator.hasNext()){
            //Add the time change to the starting time mentioned in each appointment
            Appointment appointment = iterator.next();
            LocalTime newTime = appointment.getTime().plusMinutes(timeChangeInMinutes);
            appointment.setTime(newTime);
        }
        //Update the avaiablable time of the schedule
        availableTime = availableTime.plusMinutes(timeChangeInMinutes);
    }

    //Getters
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

    //Methods to retrieve all the appointments within a single schedule for the appointments table
    public String[][] getAppointmentsArray() {
        //Create a multidimensional array of just enough size to hold each appointment entry
        String[][] array = new String[listOfAppointments.size()][8];
        //Iterate over each appointment and add its details to the 2D array
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

    //Methods to retrieve all the appointments within a single schedule for a specific person based on their name, for the patient's appointments table
    public String[][] getAppointmentsArrayForPatient(String patientName) {
        // Use a dynamic list instead of a fixed-size array
        List<String[]> list = new ArrayList<>();
        for (Appointment appointment : listOfAppointments) {
            String currentPatientName = appointment.getPatient().getName();
            //Check if the appointment belongs to the desired patient
            if (currentPatientName.matches(patientName)) {
                // Create an array for each appointment and add it to the Array-list
                String[] appointmentArray = new String[8];
                appointmentArray[0] = appointment.getDate().toString();
                appointmentArray[1] = appointment.getTime().toString();
                appointmentArray[2] = String.valueOf(appointment.getAppointmentId());
                appointmentArray[3] = appointment.getPatient().getName();
                appointmentArray[4] = appointment.getPatient().getAddress();
                appointmentArray[5] = appointment.getPatient().getTelephoneNumber();
                appointmentArray[6] = appointment.getTreatment().getTreatmentType();
                appointmentArray[7] = String.valueOf(appointment.isPaid());
                list.add(appointmentArray);
            }
        }
        // Convert the list to a 2D array before returning
        return list.toArray(new String[0][0]);
    }

    //Methods as an Observable
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

    //Methods for implementing Memento
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

