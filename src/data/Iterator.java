package data;

//Iterator design pattern is used to traverse the data structures, so that the implementation of the data structures can be hidden from other classes in the program.
public interface Iterator {

    public boolean hasNext(); //Method to check whether the collection (data structure's) end is reached.
    public Object next(); //Method to return the next element of the collection.

}
