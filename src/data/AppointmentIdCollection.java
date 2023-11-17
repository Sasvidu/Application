package data;

import java.util.HashMap;

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

}
