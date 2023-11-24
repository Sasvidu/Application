package data;

import java.util.Stack;

public class CombinedMementoCareTaker implements CareTaker{

    //Private variable for storing the unique instance
    private static CombinedMementoCareTaker instance;
    //Undo and Redo Stacks for holding mementos of all previous states captured
    private Stack<CombinedMemento> undoStack = new Stack<>();
    private Stack<CombinedMemento> redoStack = new Stack<>();

    //Private Constructor
    private CombinedMementoCareTaker(){}

    //Public method to retrieve the unique instance
    public static CombinedMementoCareTaker getCombinedMementoCareTaker() {
        if(instance == null){
            instance = new CombinedMementoCareTaker();
        }
        return instance;
    }

    //Method to add a new memento to the undo stack
    @Override
    public void add(Memento state) {
        if(undoStack.isEmpty() || !undoStack.peek().equals(state)){
            undoStack.push((CombinedMemento) state);
        }
    }

    //Method to obtain the top element of the undo stack, by removing it, and storing it in redo stack
    @Override
    public Memento get() {
        if(!undoStack.isEmpty()){
            CombinedMemento memento = undoStack.pop();
            redoStack.push(memento);
            return memento;
        }
        return null;
    }

    //Method to obtain the top element of the redo stack, by removing it, and storing it in undo stack
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
