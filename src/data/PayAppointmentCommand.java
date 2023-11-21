package data;

import forms.Invoice;
import javax.swing.*;

public class PayAppointmentCommand implements Command<Void>{

    private AppointmentIdCollection appointments = AppointmentIdCollection.getAppointmentIdCollection();
    private String appointmentId;

    private CombinedMemento beforeMemento;
    private CombinedMemento afterMemento;

    public PayAppointmentCommand(String appointmentId){
        this.appointmentId = appointmentId;
    }

    @Override
    public void execute() {
        try {
            //Capture state in a memento
            beforeMemento = MementoManager.getMementoManager().createMemento();
            //Convert the appointment id into an integer
            int properAppointmentId = Integer.parseInt(appointmentId);
            //Get the appointment object from the appointments hashmap
            Appointment appointment = appointments.getAppointment(properAppointmentId);
            //Check if the appointment has already been paid for
            if(appointment.isPaid()){
                //Display a message box giving a message that the appointment has already been paid for
                JOptionPane.showMessageDialog(null, "Appointment has already been paid for.", "Already Paid", JOptionPane.INFORMATION_MESSAGE);
            }else{
                //Get the value of the fee
                String fee = String.valueOf(appointment.getTreatment().getFee());
                //Display it in a message box and await user's reply
                int confirmation = JOptionPane.showConfirmDialog(null, "The payment is : LkR. " + fee + "0/=\n\nConfirm Payment", "Payment Confirmation", JOptionPane.YES_NO_OPTION);
                //See whether the reply is a confirmation
                if (confirmation == 0) {
                    //Update the data
                    appointment.pay();
                    //Capture state in a memento
                    afterMemento = MementoManager.getMementoManager().createMemento();
                    //Show success message
                    JOptionPane.showMessageDialog(null, "Payment Successful!", "Paid", JOptionPane.INFORMATION_MESSAGE);
                    //Display an invoice
                    new Invoice(appointment);
                }
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
    public void redo(){
        MementoManager.getMementoManager().redo();
    }


}
