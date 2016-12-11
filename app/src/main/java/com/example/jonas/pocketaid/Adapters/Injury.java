package com.example.jonas.pocketaid.Adapters;

/**
 * Created by alec on 12/11/16.
 */
public class Injury {

    private String injuryName;
    private int injuryLogo;

    public Injury(String name, int logo){
        this.injuryName = name;
        this.injuryLogo = logo;
    }

    public String getInjuryName() {
        return injuryName;
    }

    public void setInjuryName(String injuryName) {
        this.injuryName = injuryName;
    }

    public int getInjuryLogo() {
        return injuryLogo;
    }

    public void setInjuryLogo(int injuryLogo) {
        this.injuryLogo = injuryLogo;
    }
}
