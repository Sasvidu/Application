package data;

public interface Memento {

    public AppointmentIdCollection getAppointments();
    public ScheduleCollection getSchedules();
    public InvoiceCollection getInvoices();

}
