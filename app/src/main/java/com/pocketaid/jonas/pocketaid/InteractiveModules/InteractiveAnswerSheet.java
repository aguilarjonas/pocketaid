package com.pocketaid.jonas.pocketaid.InteractiveModules;

import android.widget.TextView;

import com.pocketaid.jonas.pocketaid.R;

import java.util.ArrayList;
import java.util.Collections;

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

    ArrayList<Integer> RANDOMIZED_ME = new ArrayList<Integer>() {{
        add(1);
        add(2);
        add(3);
        add(4);
        add(5);
        add(6);
    }};


    ArrayList<Integer> MATERIAL_STORAGE = new ArrayList<Integer>() {{
        add(R.drawable.ic_material_antibiotic);
        add(R.drawable.ic_material_bandage);
        add(R.drawable.ic_material_betadine);
        add(R.drawable.ic_material_coldpack);
        add(R.drawable.ic_material_gauze);
        add(R.drawable.ic_material_glove);
        add(R.drawable.ic_material_ice);
        add(R.drawable.ic_material_painreliever);
        add(R.drawable.ic_material_ringpad);
        add(R.drawable.ic_material_sling);
        add(R.drawable.ic_material_soapwater);
    }};


    public ArrayList<Integer> getRANDOMIZED_ME() {
        Collections.shuffle(RANDOMIZED_ME);
        return RANDOMIZED_ME;
    }

    public void setRANDOMIZED_ME(ArrayList<Integer> RANDOMIZED_ME) {
        this.RANDOMIZED_ME = RANDOMIZED_ME;
    }


    public ArrayList<Integer> getMATERIAL_STORAGE() {
        return MATERIAL_STORAGE;
    }

    public void setMATERIAL_STORAGE(ArrayList<Integer> MATERIAL_STORAGE) {
        this.MATERIAL_STORAGE = MATERIAL_STORAGE;
    }
}
