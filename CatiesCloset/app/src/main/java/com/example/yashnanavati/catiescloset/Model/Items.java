package com.example.yashnanavati.catiescloset.Model;

/**
 * Created by Game of Threads
 */

// Items call that stores variables object to firebase. This class allows to store individual item id binded to a student it; this will be sent to firebase
public class Items {
    private String id;
    private String studentId;

    public Items(String id, String studentId){
        this.id = id;
        this.studentId = studentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
