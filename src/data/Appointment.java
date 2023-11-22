package data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.Objects;

public class Appointment implements Observable, Cloneable{

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
    public Appointment clone() throws CloneNotSupportedException{
        Appointment cloned = (Appointment) super.clone();
        cloned.patient = this.patient.clone();
        return cloned;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Appointment appointmentObj = (Appointment) obj;
        return appointmentId == appointmentObj.appointmentId &&
                deepEquals(patient, appointmentObj.patient) &&
                Objects.equals(treatment, appointmentObj.treatment) &&
                Objects.equals(date, appointmentObj.date) &&
                Objects.equals(time, appointmentObj.time) &&
                isPaid == appointmentObj.isPaid;
    }

    private boolean deepEquals(Object object1, Object object2){
        return Objects.deepEquals(object1, object2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appointmentId, patient, treatment, date, time, isPaid);
    }

}
