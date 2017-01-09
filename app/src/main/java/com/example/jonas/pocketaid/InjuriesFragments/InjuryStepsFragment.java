package com.example.jonas.pocketaid.InjuriesFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.jonas.pocketaid.Adapters.InjuryStepsAdapter;
import com.example.jonas.pocketaid.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class InjuryStepsFragment extends Fragment {

    private Spinner spinnerOptions;
    private Spinner spinnerSubCat;
    private ListView listView;
    private String[] steps;
    private int[] imgSteps;
    private String subCat;

    public InjuryStepsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_injury_steps, container, false);

        //injury from InjuryInformationFragment
        final String injury = getArguments().getString("injury");
        spinnerOptions = (Spinner) rootView.findViewById(R.id.spinner);
        listView = (ListView) rootView.findViewById(R.id.listview_firstaid_steps);
        spinnerSubCat = (Spinner) rootView.findViewById(R.id.spinner2);

        addSpinnerSubCat(rootView, spinnerSubCat, injury);

        spinnerSubCat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(injury.toLowerCase().equals("laceration")) {
                    if(position == 0) {
                        setSubCat("laceration_major");
                    } else if(position == 1) {
                        setSubCat("laceration_minor");
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {/** DO NOTHING **/}
        });

        //spinner options (recommended or alternative)
        spinnerOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(injury.toLowerCase().equals("abrasion")) {
                    if(position == 0) {
                        steps = getResources().getStringArray(R.array.abrasion_recommended);
                        imgSteps = new int[] { R.drawable.ic_about, R.drawable.ic_abrasion, 0, R.drawable.ic_nearby };
                        setInjuryStepAdapter(steps, imgSteps);
                    } else if(position == 1){
                        steps = getResources().getStringArray(R.array.abrasion_alternative);
                        imgSteps = new int[] { R.drawable.ic_nearby, R.drawable.ic_bites, R.drawable.ic_concussion, R.drawable.ic_contusion};
                        setInjuryStepAdapter(steps, imgSteps);
                    }
                } else if(injury.toLowerCase().equals("laceration")) {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { /** DO NOTHING **/ }
        });

        return rootView;
    }

    public void setInjuryStepAdapter(String[] step, int[] imgStep) {
        InjuryStepsAdapter adapter = new InjuryStepsAdapter(getActivity().getApplicationContext(), step, imgStep);
        listView.setAdapter(adapter);
    }

    public void addSpinnerSubCat(ViewGroup rootView, Spinner spinnerSubCat, String injury) {
        if(injury.toLowerCase().equals("burns")) {
            spinnerSubCat.setVisibility(rootView.VISIBLE);
            ArrayList<String> options = new ArrayList<String>();
            options.add("1st and 2nd Degree Burns");
            options.add("3rd Degree Burns");

            ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, options);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinnerSubCat.setAdapter(adapter);
        } else if(injury.toLowerCase().equals("bites")) {
            spinnerSubCat.setVisibility(rootView.VISIBLE);
            ArrayList<String> options = new ArrayList<String>();
            options.add("Animal Bites");
            options.add("Insect Bites");

            ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, options);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinnerSubCat.setAdapter(adapter);
        } else if(injury.toLowerCase().equals("puncture")) {
            spinnerSubCat.setVisibility(rootView.VISIBLE);
            ArrayList<String> options = new ArrayList<String>();
            options.add("Severe Bleeding");
            options.add("Slight Bleeding");

            ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, options);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinnerSubCat.setAdapter(adapter);
        } else if(injury.toLowerCase().equals("laceration")) {
            spinnerSubCat.setVisibility(rootView.VISIBLE);
            ArrayList<String> options = new ArrayList<String>();
            options.add("Major Laceration Wound");
            options.add("Minor Laceration Wound");

            ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, options);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinnerSubCat.setAdapter(adapter);
        }
    }

    public void setSubCat(String subCat) {
        this.subCat = subCat;
    }

    public String getSubCat() {
        return subCat;
    }
}
