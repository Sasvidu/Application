package data;

import java.util.HashMap;
import java.util.Map;

public class AppointmentIdCollection {

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

    public void addAppointment(int id, Appointment appointment){

        appointments.put(id, appointment);

    }

    public boolean hasId(int id){

        return appointments.containsKey(id);

    }

    public int getNumberOfAppointments(){

        return appointments.size();

    }

    public void printAppointments() {
        System.out.println("Printing Appointments:");
        for (Map.Entry<Integer, Appointment> entry : appointments.entrySet()) {
            int id = entry.getKey();
            Appointment appointment = entry.getValue();
            System.out.println("ID: " + id + ", Appointment: " + appointment);
        }
    }

}
