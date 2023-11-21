package data;

import forms.HomeFrame;

import java.time.LocalDate;
import java.util.*;
import java.util.Iterator;

public class ScheduleCollection implements Observable, Cloneable{

    private static ScheduleCollection instance;
    private TreeMap<LocalDate, Schedule> schedules = new TreeMap<>();
    private ScheduleFactory scheduleFactory = ScheduleFactory.getScheduleFactory();
    private LinkedList<Observer> observers = new LinkedList<>();

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
                    notifyObservers();
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

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    public CombinedMemento saveStateToMemento() throws CloneNotSupportedException {
        return MementoManager.getMementoManager().createMemento();
    }

    public void restoreStateFromMemento(CombinedMemento memento){
        this.schedules = memento.getSchedules().getSchedules();
        notifyObservers();
    }

    @Override
    public ScheduleCollection clone() throws CloneNotSupportedException{
        ScheduleCollection cloned = (ScheduleCollection) super.clone();
        TreeMap<LocalDate, Schedule> clonedSchedules = new TreeMap<>();
        for (Map.Entry<LocalDate, Schedule> entry : schedules.entrySet()) {
            LocalDate date = entry.getKey();
            Schedule schedule = entry.getValue().clone();
            clonedSchedules.put(date, schedule);
        }
        cloned.schedules = clonedSchedules;
        return cloned;
    }

//    public Iterator getKeyIterator() {
//        Set<LocalDate> set = schedules.keySet();
//        return set.iterator();
//    }

}
