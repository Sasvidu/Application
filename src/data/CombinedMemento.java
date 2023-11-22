package data;

import java.util.Objects;

public class CombinedMemento implements Memento{

    private final AppointmentIdCollection appointmentsState;
    private final ScheduleCollection schedulesState;
    private final InvoiceCollection invoicesState;

    protected CombinedMemento(AppointmentIdCollection appointments, ScheduleCollection schedules, InvoiceCollection invoices) throws CloneNotSupportedException {
        this.appointmentsState = appointments.clone();
        this.schedulesState = schedules.clone();
        this.invoicesState = invoices.clone();
    }

    @Override
    public AppointmentIdCollection getAppointments() {
        return this.appointmentsState;
    }

    @Override
    public ScheduleCollection getSchedules() {
        return this.schedulesState;
    }

    @Override
    public InvoiceCollection getInvoices() {
        return this.invoicesState;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        CombinedMemento combinedMementoObj = (CombinedMemento) obj;
        return deepEquals(this.appointmentsState, combinedMementoObj.appointmentsState) &&
                deepEquals(this.schedulesState, combinedMementoObj.schedulesState) &&
                deepEquals(this.invoicesState, combinedMementoObj.invoicesState);
    }

    private boolean deepEquals(Object object1, Object object2){
        return Objects.deepEquals(object1, object2);
    }

}
