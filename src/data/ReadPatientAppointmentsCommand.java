package data;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.Map;

public class ReadPatientAppointmentsCommand implements Command<String[][]>{

    //Objects from classes that the command interacts with
    private ScheduleCollection schedules = ScheduleCollection.getScheduleCollection();

    //Data variable for storing the name of the patient whose appointment is to be read
    private String patientName;

    //Variable for storing the return value
    private String[][] result;

    //Parameterized constructor to initialize variables
    public ReadPatientAppointmentsCommand(String patientName){
        this.patientName = patientName;
    }

    @Override
    public void execute() {
        //Create an iterator for the schedule collection
        Iterator schedulesIterator = schedules.getIterator();
        //Variable to store each entry of the TreeMap
        Map.Entry<LocalDate, Schedule> entry;
        //Variable to store each schedule
        Schedule schedule;
        //Iterate over each entry (schedule)
        while (schedulesIterator.hasNext()){
            entry = (Map.Entry<LocalDate, Schedule>) schedulesIterator.next();
            schedule = entry.getValue();
            //Get the list of appointments for the schedule and store it in a 2D array
            String[][] currentScheduleAppointmentList = schedule.getAppointmentsArrayForPatient(patientName);
            //Combine the 2D array with the final value
            result = combineArraysVertically(result, currentScheduleAppointmentList);
        }
    }

    //Method to account for the edge case of no appointments being there to read
    @Override
    public String[][] getResult() {
        if(result == null){
            result = new String[1][8];
            return result;
        }
        return result;
    }

    // Method to combine two arrays vertically:
    private static String[][] combineArraysVertically(String[][] array1, String[][] array2) {
        if(array1 == null){
            return array2;
        } else {
            int rows = array1.length + array2.length;
            int columns = array1[0].length;
            String[][] resultArray = new String[rows][columns];
            for (int i = 0; i < array1.length; i++) {
                System.arraycopy(array1[i], 0, resultArray[i], 0, array1[i].length);
            }
            for (int i = 0; i < array2.length; i++) {
                System.arraycopy(array2[i], 0, resultArray[i + array1.length], 0, array2[i].length);
            }
            return resultArray;
        }
    }

    //Methods for implementing Memento
    @Override
    public void undo(){
            MementoManager.getMementoManager().undo();
        }

    @Override
    public void redo(){
            MementoManager.getMementoManager().redo();
        }

}
