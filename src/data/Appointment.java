package data;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {

    private int appointmentId;
    private Patient patient;
    private Treatment treatment;
    private LocalDate date;
    private LocalTime time;
    private boolean isPaid;

    protected Appointment(int appointmentId, Patient patient, Treatment treatment){
        this.appointmentId = appointmentId;
        this.patient = patient;
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


    public void setPatient(String name, String address, String telNo) {
        this.patient.setName(name);
        this.patient.setAddress(address);
        this.patient.setTelNo(telNo);
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

    public Invoice pay(){
        this.isPaid = true;
        return new Invoice(this);
    }

}
