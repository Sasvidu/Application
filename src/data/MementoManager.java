package data;

public class MementoManager {

    //Private variable for storing the unique instance
    private static MementoManager instance;
    //Variable for storing an object of the caretaker class which the MementoManager has to interact with
    private final CareTaker careTaker = CombinedMementoCareTaker.getCombinedMementoCareTaker();

    //Private Constructor
    private MementoManager(){}

    //Public method to retrieve the unique instance
    public static MementoManager getMementoManager(){
        if(instance == null){
            instance = new MementoManager();
        }
        return instance;
    }

    //Method that encapsulates the creation of a memento at any place in the program
    public CombinedMemento createMemento() throws CloneNotSupportedException {
        ScheduleCollection schedules = ScheduleCollection.getScheduleCollection();
        AppointmentIdCollection appointments = AppointmentIdCollection.getAppointmentIdCollection();
        CombinedMemento memento = new CombinedMemento(appointments, schedules);
        careTaker.add(memento);
        return memento;
    }

    //Method for performing Undo
    public void undo(){
        //Get the last memento from the undo stack
        CombinedMemento undoMemento = (CombinedMemento) careTaker.get();
        if(undoMemento != null){
            //Restore the state
            ScheduleCollection.getScheduleCollection().restoreStateFromMemento(undoMemento);
            AppointmentIdCollection.getAppointmentIdCollection().restoreStateFromMemento(undoMemento);
        }
    }

    //Method for performing Redo
    public void redo(){
        //Get the last memento from the redo stack
        CombinedMemento redoMemento = (CombinedMemento) careTaker.redo();
        if(redoMemento != null){
            //Restore the state
            ScheduleCollection.getScheduleCollection().restoreStateFromMemento(redoMemento);
            AppointmentIdCollection.getAppointmentIdCollection().restoreStateFromMemento(redoMemento);
        }
    }

}
