package data;

import forms.HomeFrame;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.*;
import java.util.Iterator;

public class ScheduleCollection implements Observable, Cloneable{

    //Private class variable to store the unique instance
    private static ScheduleCollection instance;
    //Sorted Data Structure (Tree Map) to store the list of schedules
    private TreeMap<LocalDate, Schedule> schedules = new TreeMap<>();
    //Objects of classes that the class interacts with
    private ScheduleFactory scheduleFactory = ScheduleFactory.getScheduleFactory();
    //List of observers
    private LinkedList<Observer> observers = new LinkedList<>();

    //Private constructor
    private ScheduleCollection(){}

    //Public method to retrieve the only instance
    public static ScheduleCollection getScheduleCollection(){
        if(instance == null){
            instance = new ScheduleCollection();
        }
        return instance;
    }

    //Method to create a new schedule in the collection
    public void addSchedule(LocalDate date){
        try {
            //Check if a schedule has already been created for that date
            if (!hasSchedule(date)) {
                //Create the schedule from the factory
                Schedule schedule = scheduleFactory.getSchedule(date.toString());
                //Ensure the schedule has been created properly
                if(schedule != null) {
                    //Add it to the Tree Map
                    schedules.put(date, schedule);
                    //Notify the HomeFrame
                    notifyObservers();
                } else {
                    throw new Exception("This date does not allow a schedule to be created!");
                }
            }else{
                throw new Exception("The schedule is already added!");
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e);
        }
    }

    //Methods to check whether a schedule has been created for a day
    public boolean hasSchedule(LocalDate date){
        return schedules.containsKey(date);
    }

    public boolean hasSchedule(String date){
        LocalDate properDate = LocalDate.parse(date);
        return schedules.containsKey(properDate);
    }

    //Getters
    public Schedule getSchedule(LocalDate date){
        return schedules.get(date);
    }

    public TreeMap<LocalDate, Schedule> getSchedules() {
        return schedules;
    }

    //Method to obtain an iterator
    public Iterator getIterator() {
        Set set = schedules.entrySet();
        return set.iterator();
    }

    //Methods as an Observable
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

    //Methods for implementing Memento
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ScheduleCollection schedulesObj = (ScheduleCollection) obj;
        return deepEquals(this.schedules, schedulesObj.schedules);
    }

    private boolean deepEquals(Object object1, Object object2){
        return Objects.deepEquals(object1, object2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schedules);
    }

}
