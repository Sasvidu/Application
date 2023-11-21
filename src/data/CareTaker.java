package data;

public interface CareTaker {

    public void add(Memento state);
    public Memento get();
    public Memento redo();

}
