package data;

public class UserCollection implements Collection{

    //Attributes:
    private static UserCollection instance;
    private User[] users;

    //Define Iterator Class:
    private class UserCollectionIterator implements Iterator{

        private int index;

        @Override
        public boolean hasNext() {
            if(index < users.length){
                return true;
            }
            return false;
        }

        @Override
        public User next() {
            if(this.hasNext()){
                //Return the next element (TO the caller's perspective) and increment the index.
                return users[index++];
            }
            return null;
        }

    }

    //Encapsulated Parameterized Constructor:
    private UserCollection(User[] users){
        this.users = users;
    }

    //Method to retrieve the singular instance:
    public static UserCollection getUserCollection(User[] users){
        if(instance == null){
            instance = new UserCollection(users);
        }
        return instance;
    }

    @Override
    public Iterator getIterator() {
        return new UserCollectionIterator();
    }

}
