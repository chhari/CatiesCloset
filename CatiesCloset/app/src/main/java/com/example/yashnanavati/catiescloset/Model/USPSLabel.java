package com.example.yashnanavati.catiescloset.Model;

import java.util.Date;
import java.util.List;

/**
 * Created by Game of Threads
 */

// Model class object that sends model class variables to firebase
public class USPSLabel {

    private Date id;
    private String name;
    private String emailid;
    private String address;
    private String cityZip;
    private List<String> weights;

    public USPSLabel(Date id, String name, String emailid, String address, String cityZip, List<String> weights){
        this.id = id;
        this.name = name;
        this.emailid = emailid;
        this.address = address;
        this.cityZip = cityZip;
        this.weights = weights;
    }

    public USPSLabel(){
        // Does Nothing
    }

    public Date getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmailid() {
        return emailid;
    }

    public String getCityZip() {
        return cityZip;
    }

    public List<String> getWeights() {
        return weights;
    }

    public void setId(Date id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public void setCityZip(String cityZip) {
        this.cityZip = cityZip;
    }

    public void setWeights(List<String> weights) {
        this.weights = weights;
    }
}
