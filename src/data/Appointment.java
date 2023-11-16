package data;

public class Appointment {

    private int appointmentId;
    private Patient patient;
    private Treatment treatment;

    protected Appointment(int appointmentId, Patient patient){
        this.appointmentId = appointmentId;
        this.patient = patient;
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

}
