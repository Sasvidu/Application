package data;

public interface Subject {

    public void attach(Observer observer);
    public void detach(Observer observer);
    public void notifyStateChange();
    public void notifyStateChange(Subject subject);

}
