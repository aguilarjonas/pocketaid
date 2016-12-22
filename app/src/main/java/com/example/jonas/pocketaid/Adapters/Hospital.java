package com.example.jonas.pocketaid.Adapters;

/**
 * Created by Lance on 20 Dec 2016.
 */

public class Hospital {

    private String hospitalName;
    private String hospitalContactNumber;

    public Hospital(String hospitalName, String hospitalContactNumber){
        //constructor
        this.setHospitalName(hospitalName);
        this.setHospitalContactNumber(hospitalContactNumber);
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getHospitalContactNumber() {
        return hospitalContactNumber;
    }

    public void setHospitalContactNumber(String hospitalContactNumber) {
        this.hospitalContactNumber = hospitalContactNumber;
    }
}
