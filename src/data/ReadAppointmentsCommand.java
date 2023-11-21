package data;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.Map;

public class ReadAppointmentsCommand implements Command<String[][]>{

    private ScheduleCollection schedules = ScheduleCollection.getScheduleCollection();
    private String[][] result;

    @Override
    public void execute() {
        Iterator schedulesIterator = schedules.getIterator();
        Map.Entry<LocalDate, Schedule> entry;
        Schedule schedule;
        while (schedulesIterator.hasNext()){
            entry = (Map.Entry<LocalDate, Schedule>) schedulesIterator.next();
            schedule = entry.getValue();
            String[][] currentScheduleAppointmentList = schedule.getAppointmentsArray();
            result = combineArraysVertically(result, currentScheduleAppointmentList);
        }
    }

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
        }else {
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

    @Override
    public void undo(){
        MementoManager.getMementoManager().undo();
    }

    @Override
    public void redo(){
        MementoManager.getMementoManager().redo();
    }


}

