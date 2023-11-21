package data;

public interface Command<T> {

    void execute();
    T getResult();
    void undo();
    void redo();

}
