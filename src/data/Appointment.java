package data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;

public class Appointment implements Observable{

    private int appointmentId;
    private Patient patient;
    private Treatment treatment;
    private LocalDate date;
    private LocalTime time;
    private boolean isPaid;
    private LinkedList<Observer> observers = new LinkedList<>();

    public Appointment(int appointmentId, String patientName, String patientAddress, String patientTelephoneNumber, Treatment treatment){
        this.appointmentId = appointmentId;
        this.patient = new Patient(patientName, patientAddress, patientTelephoneNumber);
        this.treatment = treatment;
        this.isPaid = false;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public Patient getPatient() {
        return patient;
    }

    public Treatment getTreatment() {
        return treatment;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPatient(String name, String address, String telephoneNumber) {
        this.patient.setName(name);
        this.patient.setAddress(address);
        this.patient.setTelephoneNumber(telephoneNumber);
    }

    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }


    public void pay(){
        this.isPaid = true;
        notifyObservers();
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
}
