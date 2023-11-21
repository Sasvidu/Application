package data;

import java.util.Stack;

public class CombinedMementoCareTaker implements CareTaker{

    private static CombinedMementoCareTaker instance;
    private Stack<CombinedMemento> undoStack = new Stack<>();
    private Stack<CombinedMemento> redoStack = new Stack<>();

    private CombinedMementoCareTaker(){}

    public static CombinedMementoCareTaker getCombinedMementoCareTaker() {
        if(instance == null){
            instance = new CombinedMementoCareTaker();
        }
        return instance;
    }

    @Override
    public void add(Memento state) {
        if(undoStack.isEmpty() || !undoStack.peek().equals(state)){
            undoStack.push((CombinedMemento) state);
        }
    }

    @Override
    public Memento get() {
        if(!undoStack.isEmpty()){
            CombinedMemento memento = undoStack.pop();
            redoStack.push(memento);
            return memento;
        }
        return null;
    }

    @Override
    public Memento redo(){
        if(!redoStack.isEmpty()){
            CombinedMemento memento = redoStack.pop();
            undoStack.push(memento);
            return memento;
        }
        return null;
    }

}
