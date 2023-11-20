package data;

public interface Command<T> {

    void execute();
    T getResult();

}
