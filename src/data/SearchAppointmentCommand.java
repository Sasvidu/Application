package data;

import javax.swing.*;

public class SearchAppointmentCommand implements Command<String[]>{

    private AppointmentIdCollection appointments = AppointmentIdCollection.getAppointmentIdCollection();
    private String appointmentId;
    private String[] result;

    public SearchAppointmentCommand(String appointmentId){
        this.appointmentId = appointmentId;
    }

    @Override
    public void execute() {
        try {
            //Convert the appointment id into and integer
            int properAppointmentId = Integer.parseInt(appointmentId);
            //Get the appointment object from the appointment hashmap
            Appointment appointment = appointments.getAppointment(properAppointmentId);
            //Read the values from the appointment
            String patientName = appointment.getPatient().getName();
            String patientAddress = appointment.getPatient().getAddress();
            String patientTelephoneNumber = appointment.getPatient().getTelephoneNumber();
            String treatmentType = appointment.getTreatment().getTreatmentType();
            String isPaid = String.valueOf(appointment.isPaid());
            //Return the results in an array
            result = new String[]{appointmentId, patientName, patientAddress, patientTelephoneNumber, treatmentType, isPaid};
        }catch (Exception e){
            //If the string cannot be converted to an integer, shoe a warning message
            JOptionPane.showMessageDialog(null, "Exception occurred : " + e, "Warning", JOptionPane.WARNING_MESSAGE);
            result = null;
        }
    }

    @Override
    public String[] getResult() {
        return result;
    }

    @Override
    public void undo(){
        MementoManager.getMementoManager().undo();
    }

    @Override
    public void redo(){
        MementoManager.getMementoManager().redo();
    }


}
