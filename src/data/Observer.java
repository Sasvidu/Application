package data;

public interface Observer {

    public void update();
    public void update(Subject subject);
}
