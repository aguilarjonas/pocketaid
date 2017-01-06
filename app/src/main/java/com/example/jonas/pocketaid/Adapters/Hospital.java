package com.example.jonas.pocketaid.Adapters;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Lance on 20 Dec 2016.
 */

public class Hospital {

    private String hospitalName;
    private String hospitalVicinity;
    private String hospitalPlaceID;

    private ArrayList<String> hospitalNameList;
    private ArrayList<String> hospitalVicinityList;
    private ArrayList<String> hospitalPlaceIDList;


    public Hospital(String hospitalName, String hospitalVicinity, String hospitalPlaceID){
        this.setHospitalName(hospitalName);
        this.setHospitalVicinity(hospitalVicinity);
        this.setHospitalPlaceID(hospitalPlaceID);
    }

    public void putHospitalInformationList(ArrayList<String> hospitalNameList, ArrayList<String> hospitalVicinityList,
                                           ArrayList<String> hospitalPlaceIDList){
        this.setHospitalNameList(hospitalNameList);
        this.setHospitalVicinityList(hospitalVicinityList);
        this.setHospitalPlaceIDList(hospitalPlaceIDList);
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

    public void setHospitalPlaceID(String hospitalPlaceID) {
        this.hospitalPlaceID = hospitalPlaceID;
    }

    public void setHospitalNameList(ArrayList<String> hospitalNameList) {
        this.hospitalNameList = hospitalNameList;
    }

    public void setHospitalVicinityList(ArrayList<String> hospitalVicinityList) {
        this.hospitalVicinityList = hospitalVicinityList;
    }

    public void setHospitalPlaceIDList(ArrayList<String> hospitalPlaceID) {
        this.hospitalPlaceIDList = hospitalPlaceID;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public String getHospitalVicinity() {
        return hospitalVicinity;
    }

    public String getHospitalPlaceID() {
        return hospitalPlaceID;
    }

    public ArrayList<String> getHospitalNameList() {
        return hospitalNameList;
    }

    public ArrayList<String> getHospitalVicinityList() {
        return hospitalVicinityList;
    }

    public ArrayList<String> getHospitalPlaceIDList() {
        return hospitalPlaceIDList;
    }
}
