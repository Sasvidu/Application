package data;

//A class to store the login details of the operators.

public class User {

    //Encapsulated variables to store the username and password
    private String username;
    private String password;

    //Parameterized constructor to ensure each user object has its attributes instantiated
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    //Getters:
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
