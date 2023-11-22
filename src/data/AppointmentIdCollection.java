package data;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AppointmentIdCollection implements Cloneable {

    //This class is used to hold a hashmap of all the appointments allocated to schedule. This allows the appointments to be accessed through their ID with a time complexity of O(1).
    private static AppointmentIdCollection instance;
    private HashMap<Integer, Appointment> appointments = new HashMap<>();

    private AppointmentIdCollection(){}

    public static AppointmentIdCollection getAppointmentIdCollection(){
        if(instance == null){
            instance = new AppointmentIdCollection();
        }
        return instance;
    }

    public Appointment getAppointment(int id){
        return appointments.get(id);
    }

    public boolean hasId(int id){
        return appointments.containsKey(id);
    }

    public HashMap<Integer, Appointment> getAppointments(){
        return appointments;
    }

    public void addAppointment(int id, Appointment appointment){
        appointments.put(id, appointment);
    }

    public CombinedMemento saveStateToMemento() throws CloneNotSupportedException {
        return MementoManager.getMementoManager().createMemento();
    }

    public void restoreStateFromMemento(CombinedMemento memento){
        this.appointments = memento.getAppointments().getAppointments();
    }

    @Override
    public AppointmentIdCollection clone() throws CloneNotSupportedException{
        AppointmentIdCollection cloned = (AppointmentIdCollection) super.clone();
        HashMap<Integer, Appointment> clonedAppointments = new HashMap<>();
        for (Map.Entry<Integer, Appointment> entry : appointments.entrySet()) {
            int id = entry.getKey();
            Appointment appointment = entry.getValue().clone();
            clonedAppointments.put(id, appointment);
        }
        cloned.appointments = clonedAppointments;
        return cloned;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AppointmentIdCollection appointmentsObj = (AppointmentIdCollection) obj;
        return deepEquals(this.appointments, appointmentsObj.appointments);
    }

    private boolean deepEquals(Object object1, Object object2){
        return Objects.deepEquals(object1, object2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appointments);
    }

}
