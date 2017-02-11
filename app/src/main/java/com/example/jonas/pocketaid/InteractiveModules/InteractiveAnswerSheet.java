package com.example.jonas.pocketaid.InteractiveModules;

import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jonas.pocketaid.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Raeven on 11 Feb 2017.
 */

public class InteractiveAnswerSheet {

    TextView textView_1;
    TextView textView_2;
    TextView textView_3;
    TextView textView_4;
    TextView textView_5;
    TextView textView_6;

    final ArrayList<String> ANSWER_ABRASION = new ArrayList<String>(){{
        add("1");
        add("2");
        add("3");
    }};

    final ArrayList<String> ANSWER_ANIMAL_BITES = new ArrayList<String>(){{
        add("1");
        add("2");
        add("3");
        add("4");
    }};

    final ArrayList<String> ANSWER_INSECT_BITES = new ArrayList<String>(){{
        add("1");
        add("2");
        add("3");
    }};

    final ArrayList<String> ANSWER_THERMAL = new ArrayList<String>(){{
        add("1");
        add("2");
        add("3");
    }};

    final ArrayList<String> ANSWER_CHEMICAL = new ArrayList<String>(){{
        add("1");
        add("2");
        add("3");
    }};

    final ArrayList<String> ANSWER_THERMAL2 = new ArrayList<String>(){{
        add("1");
        add("2");
        add("3");
    }};

    final ArrayList<String> ANSWER_CONCUSSION = new ArrayList<String>(){{
        add("1");
        add("2");
        add("3");
    }};

    final ArrayList<String> ANSWER_CONTUSION = new ArrayList<String>(){{
        add("1");
        add("2");
        add("3");
    }};

    final ArrayList<String> ANSWER_FRACTURE = new ArrayList<String>(){{
        add("1");
        add("2");
        add("3");
    }};

    final ArrayList<String> ANSWER_MAJOR_LACERATION = new ArrayList<String>(){{
        add("1");
        add("2");
        add("3");
    }};

    final ArrayList<String> ANSWER_MINOR_LACERATION = new ArrayList<String>(){{
        add("1");
        add("2");
        add("3");
    }};

    final ArrayList<String> ANSWER_PUNCTURE_SEVERE = new ArrayList<String>(){{
        add("1");
        add("2");
        add("3");
    }};

    final ArrayList<String> ANSWER_PUNCTURE_SLIGHT = new ArrayList<String>(){{
        add("1");
        add("2");
        add("3");
    }};

    final ArrayList<String> MATERIALS_ABRASION = new ArrayList<String>(){{
        add("I");
        add("HATE");
        add("THESIS");
        add("AYOKO");
        add("NA");
        add("MAG ABRASION");
    }};

    final ArrayList<String> MATERIALS_ANIMAL_BITES = new ArrayList<String>(){{
        add("I");
        add("HATE");
        add("THESIS");
        add("AYOKO");
        add("NA");
        add("MAG ANIMAL BITES");
    }};

    final ArrayList<String> MATERIALS_INSECT_BITES = new ArrayList<String>(){{
        add("I");
        add("HATE");
        add("THESIS");
        add("AYOKO");
        add("NA");
        add("MAG INSECT BITES");
    }};

    final ArrayList<String> MATERIALS_THERMAL = new ArrayList<String>(){{
        add("I");
        add("HATE");
        add("THESIS");
        add("AYOKO");
        add("NA");
        add("MAG THERMAL");
    }};

    final ArrayList<String> MATERIALS_CHEMICAL = new ArrayList<String>(){{
        add("I");
        add("HATE");
        add("THESIS");
        add("AYOKO");
        add("NA");
        add("MAG CHEMICAL");
    }};

    final ArrayList<String> MATERIALS_THERMAL2 = new ArrayList<String>(){{
        add("I");
        add("HATE");
        add("THESIS");
        add("AYOKO");
        add("NA");
        add("MAG THERMAL2");
    }};

    final ArrayList<String> MATERIALS_CONCUSSION = new ArrayList<String>(){{
        add("I");
        add("HATE");
        add("THESIS");
        add("AYOKO");
        add("NA");
        add("MAG CONCUSSION");
    }};

    final ArrayList<String> MATERIALS_CONTUSION = new ArrayList<String>(){{
        add("I");
        add("HATE");
        add("THESIS");
        add("AYOKO");
        add("NA");
        add("MAG CONTUSSION");
    }};

    final ArrayList<String> MATERIALS_FRACTURE = new ArrayList<String>(){{
        add("I");
        add("HATE");
        add("THESIS");
        add("AYOKO");
        add("NA");
        add("MAG FRACTURE");
    }};

    final ArrayList<String> MATERIALS_MAJOR_LACERATION = new ArrayList<String>(){{
        add("I");
        add("HATE");
        add("THESIS");
        add("AYOKO");
        add("NA");
        add("MAG MAJOR LACERATION");
    }};

    final ArrayList<String> MATERIALS_MINOR_LACERATION= new ArrayList<String>(){{
        add("I");
        add("HATE");
        add("THESIS");
        add("AYOKO");
        add("NA");
        add("MAG MINOR LACERATION");
    }};

    final ArrayList<String> MATERIALS_PUNCTURE_SEVERE = new ArrayList<String>(){{
        add("I");
        add("HATE");
        add("THESIS");
        add("AYOKO");
        add("NA");
        add("MAG PUNCTURE1");
    }};

    final ArrayList<String> MATERIALS_PUNCTURE_SLIGHT = new ArrayList<String>(){{
        add("I");
        add("HATE");
        add("THESIS");
        add("AYOKO");
        add("NA");
        add("MAG PUNCTURE2");
    }};
    public ArrayList<String> checkAnswerSheet(String injuryType){

        ArrayList<String> answerSheetDefault = new ArrayList<String>();

        if (injuryType == "Abrasion"){
            return ANSWER_ABRASION;
        }
        else if (injuryType == "Animal Bites"){
            return ANSWER_ANIMAL_BITES;

        } else if ((injuryType == "Insect Bites")){
            return ANSWER_INSECT_BITES;

        } else if ((injuryType == "Thermal Burns")){
            return ANSWER_THERMAL;

        } else if ((injuryType == "Chemical Burns")){
            return ANSWER_CHEMICAL;

        } else if ((injuryType == "3rd Degree Burns")){
            return ANSWER_THERMAL2;

        } else if ((injuryType == "Concussion")){
            return ANSWER_CONCUSSION;

        } else if ((injuryType == "Contusion")){
            return ANSWER_CONTUSION;

        } else if ((injuryType == "Fracture")){
            return ANSWER_FRACTURE;

        } else if ((injuryType == "Major Laceration")){
            return ANSWER_MAJOR_LACERATION;

        } else if ((injuryType == "Minor Laceration")){
            return ANSWER_MINOR_LACERATION;

        } else if ((injuryType == "Puncture (Severe Bleeding)")){
            return ANSWER_PUNCTURE_SEVERE;

        } else if ((injuryType == "Puncture (Slightly Bleeding)")){
            return ANSWER_PUNCTURE_SLIGHT;
        }

        return answerSheetDefault;
    }

    public ArrayList<String> getMaterialTexts(String injuryType){
        ArrayList<String> materialsSheetDefault = new ArrayList<String>();


        if (injuryType == "Abrasion"){
            return MATERIALS_ABRASION;
        }
        else if (injuryType == "Animal Bites"){
            return MATERIALS_ANIMAL_BITES;

        } else if ((injuryType == "Insect Bites")){
            return MATERIALS_INSECT_BITES;

        } else if ((injuryType == "Thermal Burns")){
            return MATERIALS_THERMAL;

        } else if ((injuryType == "Chemical Burns")){
            return MATERIALS_CHEMICAL;

        } else if ((injuryType == "3rd Degree Burns")){
            return MATERIALS_THERMAL2;

        } else if ((injuryType == "Concussion")){
            return MATERIALS_CONCUSSION;

        } else if ((injuryType == "Contusion")){
            return MATERIALS_CONTUSION;

        } else if ((injuryType == "Fracture")){
            return MATERIALS_FRACTURE;

        } else if ((injuryType == "Major Laceration")){
            return MATERIALS_MAJOR_LACERATION;

        } else if ((injuryType == "Minor Laceration")){
            return MATERIALS_MINOR_LACERATION;

        } else if ((injuryType == "Puncture (Severe Bleeding)")){
            return MATERIALS_PUNCTURE_SEVERE;

        } else if ((injuryType == "Puncture (Slightly Bleeding)")){
            return MATERIALS_PUNCTURE_SLIGHT;
        }

        return materialsSheetDefault;
    }




}
