package com.example.yashnanavati.catiescloset.Model;

/**
 * Created by Game of Threads
 */

// User class object that stores user class variables that sends to firebase
public class User {

    private String id;
    private String name;


    private static String tag = "User";

    public  User(){
        /*Do Nothing*/
    }
    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {return id;}

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

}
