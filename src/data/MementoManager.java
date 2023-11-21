package data;

public class MementoManager {

    private static MementoManager instance;
    private final CareTaker careTaker = CombinedMementoCareTaker.getCombinedMementoCareTaker();

    private MementoManager(){}

    public static MementoManager getMementoManager(){
        if(instance == null){
            instance = new MementoManager();
        }
        return instance;
    }

    public CombinedMemento createMemento() throws CloneNotSupportedException {
        ScheduleCollection schedules = ScheduleCollection.getScheduleCollection();
        AppointmentIdCollection appointments = AppointmentIdCollection.getAppointmentIdCollection();
        CombinedMemento memento = new CombinedMemento(appointments, schedules);
        careTaker.add(memento);
        return memento;
    }

    public void undo(){
        CombinedMemento undoMemento = (CombinedMemento) careTaker.get();
        if(undoMemento != null){
            ScheduleCollection.getScheduleCollection().restoreStateFromMemento(undoMemento);
            AppointmentIdCollection.getAppointmentIdCollection().restoreStateFromMemento(undoMemento);
        }
    }

    public void redo(){
        CombinedMemento redoMemento = (CombinedMemento) careTaker.redo();
        if(redoMemento != null){
            ScheduleCollection.getScheduleCollection().restoreStateFromMemento(redoMemento);
            AppointmentIdCollection.getAppointmentIdCollection().restoreStateFromMemento(redoMemento);
        }
    }

}
