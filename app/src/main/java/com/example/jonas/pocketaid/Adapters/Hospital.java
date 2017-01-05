package com.example.jonas.pocketaid.Adapters;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Lance on 20 Dec 2016.
 */

public class Hospital {

    private String hospitalName;
    private String hospitalVicinity;

    private ArrayList<String> hospitalNameList;
    private ArrayList<String> hospitalVicinityList;

    public Hospital(String hospitalName, String hospitalVicinity){
        this.setHospitalName(hospitalName);
        this.setHospitalVicinity(hospitalVicinity);

    }

    public void putHospitalInformationList(ArrayList<String> hospitalNameList, ArrayList<String> hospitalVicinityList){
        this.setHospitalNameList(hospitalNameList);
        this.setHospitalVicinityList(hospitalVicinityList);
    }

//    public void putHospitalInformation(String hospitalName, String hospitalVicinity){
//        this.setHospitalName(hospitalName);
//        this.setHospitalVicinity(hospitalVicinity);
//    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public void setHospitalVicinity(String hospitalVicinity) {
        this.hospitalVicinity = hospitalVicinity;
    }

    public void setHospitalNameList(ArrayList<String> hospitalNameList) {
        this.hospitalNameList = hospitalNameList;
    }

    public void setHospitalVicinityList(ArrayList<String> hospitalVicinityList) {
        this.hospitalVicinityList = hospitalVicinityList;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public String getHospitalVicinity() {
        return hospitalVicinity;
    }

    public ArrayList<String> getHospitalNameList() {
        return hospitalNameList;
    }

    public ArrayList<String> getHospitalVicinityList() {
        return hospitalVicinityList;
    }
}
