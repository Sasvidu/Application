package data;

import forms.Invoice;

import javax.swing.*;

public class ViewInvoiceCommand implements Command<Void>{

    private AppointmentIdCollection appointments = AppointmentIdCollection.getAppointmentIdCollection();
    private String appointmentId;

    public ViewInvoiceCommand(String appointmentId){
        this.appointmentId = appointmentId;
    }

    @Override
    public void execute() {
        try {
            //Convert the appointment id into an integer
            int properAppointmentId = Integer.parseInt(appointmentId);
            //Get the appointment object from the appointments hashmap
            Appointment appointment = appointments.getAppointment(properAppointmentId);
            //Check if the appointment has already been paid for
            if(appointment.isPaid()){
                InvoiceCollection.getInvoiceCollection().getInvoice(properAppointmentId);
            }else{
                //Display a message box giving a message that the appointment has already been paid for
                JOptionPane.showMessageDialog(null, "Appointment not yet paid for.", "Already Paid", JOptionPane.INFORMATION_MESSAGE);
            }
        }catch (Exception e){
            //If an exception occurs, display it in an error message
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
    public void redo(){ MementoManager.getMementoManager().redo(); }

}
