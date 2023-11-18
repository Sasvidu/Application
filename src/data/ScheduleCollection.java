package data;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.Set;

public class ScheduleCollection{

    private static ScheduleCollection instance;
    private TreeMap<LocalDate, Schedule> schedules = new TreeMap<>();
    private ScheduleFactory scheduleFactory = ScheduleFactory.getScheduleFactory();

    private ScheduleCollection(){}

    public static ScheduleCollection getScheduleCollection(){

        if(instance == null){
            instance = new ScheduleCollection();
        }
        return instance;

    }

    public void addSchedule(LocalDate date){

        try {
            if (!hasSchedule(date)) {
                Schedule schedule = scheduleFactory.getSchedule(date.toString());
                if(schedule != null) {
                    schedules.put(date, schedule);
                } else {
                    throw new Exception("This date does not allow a schedule to be created!");
                }
            }else{
                throw new Exception("The schedule is already added!");
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    //Overloading
    public boolean hasSchedule(LocalDate date){
        return schedules.containsKey(date);
    }

    //Overloading
    public boolean hasSchedule(String date){
        LocalDate properDate = LocalDate.parse(date);
        return schedules.containsKey(properDate);
    }

    public Schedule getSchedule(LocalDate date){
        return schedules.get(date);
    }

    public TreeMap<LocalDate, Schedule> getSchedules() {
        return schedules;
    }

    public Iterator getIterator() {
        Set set = schedules.entrySet();
        return set.iterator();
    }

    public int getScheduleCollectionSize(){
        return schedules.size();
    }

//    public Iterator getKeyIterator() {
//        Set<LocalDate> set = schedules.keySet();
//        return set.iterator();
//    }

}
