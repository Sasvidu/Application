package data;

import javax.swing.*;
import java.time.LocalDate;

public class EditAppointmentCommand implements Command<Void> {

    private AppointmentIdCollection appointments = AppointmentIdCollection.getAppointmentIdCollection();
    private ScheduleCollection schedules = ScheduleCollection.getScheduleCollection();
    private String appointmentIdString;
    private String treatmentType;
    private String patientName;
    private String patientAddress;
    private String patientTelephoneNumber;

    private CombinedMemento beforeMemento;
    private CombinedMemento afterMemento;

    public EditAppointmentCommand(String appointmentIdString, String patientName, String patientAddress, String patientTelephoneNumber, String treatmentType){
        this.appointmentIdString = appointmentIdString;
        this.patientName = patientName;
        this.patientAddress = patientAddress;
        this.patientTelephoneNumber = patientTelephoneNumber;
        this.treatmentType = treatmentType;
    }

    @Override
    public void execute(){
        try {
            //Capture state in a memento
            beforeMemento = MementoManager.getMementoManager().createMemento();
            int appointmentId = Integer.parseInt(appointmentIdString);
            Appointment appointment = appointments.getAppointment(appointmentId);
            if (appointment.isPaid()) {
                //Display a message box giving a message that the appointment has already been paid for
                JOptionPane.showMessageDialog(null, "Appointment has already been paid for.", "Already Paid", JOptionPane.INFORMATION_MESSAGE);
            } else {
                //Get the appointment date and obtain the relevant schedule
                LocalDate date = appointment.getDate();
                Schedule schedule = schedules.getSchedule(date);
                //Get the relevant treatment object
                Treatment treatment = TreatmentFactory.getTreatmentFactory().getTreatment(treatmentType);
                //Call the update method for the schedule
                schedule.editAppointment(appointment, patientName, patientAddress, patientTelephoneNumber, treatment);
                //Capture state in a memento
                afterMemento = MementoManager.getMementoManager().createMemento();
                //Show a success message
                JOptionPane.showMessageDialog(null, "Appointment successfully edited", "Success!", JOptionPane.INFORMATION_MESSAGE);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Exception occured: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public Void getResult() {
        return null;
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
