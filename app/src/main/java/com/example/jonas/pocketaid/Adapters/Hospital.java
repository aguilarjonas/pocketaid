package com.example.jonas.pocketaid.Adapters;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by Lance on 20 Dec 2016.
 */

public class Hospital {

    private String hospitalName;
    private String hospitalVicinity;
    private String hospitalPlaceID;
    private String hospitalLatitude;
    private String hospitalLongitude;

    private ArrayList<String> hospitalNameList;
    private ArrayList<String> hospitalVicinityList;
    private ArrayList<String> hospitalPlaceIDList;
    private ArrayList<String> hospitalLatitudeList;
    private ArrayList<String> hospitalLongitudeList;



    public Hospital(String hospitalName, String hospitalVicinity, String hospitalPlaceID, String hospitalLatitude, String hospitalLongitude){
        this.setHospitalName(hospitalName);
        this.setHospitalVicinity(hospitalVicinity);
        this.setHospitalPlaceID(hospitalPlaceID);
        this.setHospitalLatitude(hospitalLatitude);
        this.setHospitalLongitude(hospitalLongitude);
    }

    public void putHospitalInformationList(ArrayList<String> hospitalNameList, ArrayList<String> hospitalVicinityList,
                                           ArrayList<String> hospitalPlaceIDList, ArrayList<String> hospitalLatitudeList,
                                           ArrayList<String> hospitalLongitudeList){
        this.setHospitalNameList(hospitalNameList);
        this.setHospitalVicinityList(hospitalVicinityList);
        this.setHospitalPlaceIDList(hospitalPlaceIDList);
        this.setHospitalLatitudeList(hospitalLatitudeList);
        this.setHospitalLongitudeList(hospitalLongitudeList);
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

    public void setHospitalLatitude(String hospitalLatitude) {
        this.hospitalLatitude = hospitalLatitude;
    }

    public void setHospitalLongitude(String hospitalLongitude) {
        this.hospitalLongitude = hospitalLongitude;
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

    public void setHospitalLatitudeList(ArrayList<String> hospitalLatitudeList) {
        this.hospitalLatitudeList = hospitalLatitudeList;
    }

    public void setHospitalLongitudeList(ArrayList<String> hospitalLongitudeList) {
        this.hospitalLongitudeList = hospitalLongitudeList;
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

    public String getHospitalLatitude() {
        return hospitalLatitude;
    }

    public String getHospitalLongitude() {
        return hospitalLongitude;
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

    public ArrayList<String> getHospitalLatitudeList() {
        return hospitalLatitudeList;
    }

    public ArrayList<String> getHospitalLongitudeList() {
        return hospitalLongitudeList;
    }
}
