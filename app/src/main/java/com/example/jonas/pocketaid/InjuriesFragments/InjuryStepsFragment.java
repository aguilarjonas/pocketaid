package com.example.jonas.pocketaid.InjuriesFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.jonas.pocketaid.Adapters.InjuryStepsAdapter;
import com.example.jonas.pocketaid.Adapters.RecyclerStepsAdapter;
import com.example.jonas.pocketaid.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 */
public class InjuryStepsFragment extends Fragment {

    private Spinner spinnerOptions;
    private RecyclerView recyclerView;
    private RecyclerView lv_electrical;
    private RecyclerView lv_chemical;
    private TextView tv_thermal;
    private TextView tv_chemical;
    private TextView tv_electrical;
    private String[] steps;
    private String[] notes;
    private int[] imgSteps;

    public InjuryStepsFragment() { /** Required empty public constructor **/ }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_injury_steps, container, false);

        //injury from InjuryInformationFragment
        final String injury = getArguments().getString("injury");

        initializeViews(rootView);
        disableSpinner(spinnerOptions, injury);
        setStepsAndImages(spinnerOptions, injury);
        setRecyclerViewAsNotScrollable();

        return rootView;
    }

    /*
        Function Name : initializeView
        Function Description :  This function will be called in the onCreateView
                                This function initializes the different views/controls used by the fragment
        Function Developer : Jonas Aguilar
     */
    public void initializeViews(ViewGroup rootView) {
        spinnerOptions = (Spinner) rootView.findViewById(R.id.spinner);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.listview_firstaid_steps);
        lv_chemical = (RecyclerView) rootView.findViewById(R.id.listview_chemical);
        lv_electrical = (RecyclerView) rootView.findViewById(R.id.listview_electrical);
        tv_thermal = (TextView) rootView.findViewById(R.id.textview_thermal);
        tv_chemical = (TextView) rootView.findViewById(R.id.textview_chemical);
        tv_electrical = (TextView) rootView.findViewById(R.id.textview_electrical);
    }

    /*
        Function Name : disableSpinner
        Function Description :  This function will be called in the onCreateView
                                This function disables the spinner for some injuries
        Function Developer : Jonas Aguilar
     */
    public void disableSpinner(Spinner spinnerOptions, String injury) {
        if(injury.toLowerCase().equals("animal") || injury.toLowerCase().equals("concussion")
                || injury.toLowerCase().equals("contusion") || injury.toLowerCase().equals("major")
                || injury.toLowerCase().equals("slight")) {
            spinnerOptions.setEnabled(false);
        }
    }

    /*
        Function Name : setStepsAndImages
        Function Description :  This function will be called in the onCreateView
                                This function sets the different steps and images of the injury
        Function Developer : Jonas Aguilar
     */
    public void setStepsAndImages(Spinner spinnerOptions, final String injury) {
        spinnerOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(injury.toLowerCase().equals("abrasion")) {
                    if(position == 0) {
                        setEmptyArrayString(4);
                        steps = getResources().getStringArray(R.array.abrasion_recommended);
                        imgSteps = new int[] { R.drawable.abrasion_1, 0, 0, 0 };
                        setInjuryStepAdapter(steps, imgSteps, notes, recyclerView);
                    } else if(position == 1){
                        setEmptyArrayString(4);
                        steps = getResources().getStringArray(R.array.abrasion_alternative);
                        imgSteps = new int[] { 0, 0, 0, 0 };
                        setInjuryStepAdapter(steps, imgSteps, notes, recyclerView);
                    }
                } else if(injury.toLowerCase().equals("animal")) {
                    if(position == 0) {
                        setEmptyArrayString(3);
                        steps = getResources().getStringArray(R.array.bites_animal_recommended);
                        imgSteps = new int[] { 0, 0, 0 };
                        setInjuryStepAdapter(steps, imgSteps, notes, recyclerView);
                    }
                } else if(injury.toLowerCase().equals("insect")) {
                    if(position == 0) {
                        setEmptyArrayString(6);
                        steps = getResources().getStringArray(R.array.bites_insect_recommended);
                        imgSteps = new int[] { 0, 0, 0, 0, 0, 0 };
                        setInjuryStepAdapter(steps, imgSteps, notes, recyclerView);
                    } else if(position == 1){
                        setEmptyArrayString(6);
                        steps = getResources().getStringArray(R.array.bites_insect_alternative);
                        imgSteps = new int[] { 0, 0, 0, 0, 0, 0 };
                        setInjuryStepAdapter(steps, imgSteps, notes, recyclerView);
                    }
                } else if(injury.toLowerCase().equals("firstseconddegree")) {
                    if(position == 0) {
                        setEmptyArrayString(3);
                        steps = getResources().getStringArray(R.array.burns_thermal_recommended);
                        imgSteps = new int[] { 0, 0, 0 };
                        setInjuryStepAdapter(steps, imgSteps, notes, recyclerView);

                        setEmptyArrayString(46);
                        steps = getResources().getStringArray(R.array.burns_chemical_recommended);
                        imgSteps = new int[] { 0, 0, 0, 0, 0, 0 };
                        setInjuryStepAdapter(steps, imgSteps, notes, lv_chemical);

                        setEmptyArrayString(2);
                        steps = getResources().getStringArray(R.array.burns_electrical_recommended);
                        imgSteps = new int[] { 0, 0 };
                        setInjuryStepAdapter(steps, imgSteps, notes, lv_electrical);

                        tv_thermal.setVisibility(View.VISIBLE);
                        tv_chemical.setVisibility(View.VISIBLE);
                        tv_electrical.setVisibility(View.VISIBLE);
                        lv_chemical.setVisibility(View.VISIBLE);
                        lv_electrical.setVisibility(View.VISIBLE);
                    } else if(position == 1){
                        setEmptyArrayString(3);
                        steps = getResources().getStringArray(R.array.burns_thermal_alternative);
                        imgSteps = new int[] { 0, 0, 0 };
                        setInjuryStepAdapter(steps, imgSteps, notes, recyclerView);
                    }
                } else if(injury.toLowerCase().equals("thirddegree")) {
                    if(position == 0) {
                        notes = getResources().getStringArray(R.array.burns_third_notes);
                        steps = getResources().getStringArray(R.array.burns_third_recommended);
                        imgSteps = new int[] { 0, 0, 0, 0, 0 };
                        setInjuryStepAdapter(steps, imgSteps, notes, recyclerView);
                    } else if(position == 1){
                        setEmptyArrayString(5);
                        steps = getResources().getStringArray(R.array.burns_third_alternative);
                        imgSteps = new int[] { 0, 0, 0, 0, 0 };
                        setInjuryStepAdapter(steps, imgSteps, notes, recyclerView);
                    }
                } else if(injury.toLowerCase().equals("concussion")) {
                    if(position == 0) {
                        setEmptyArrayString(3);
                        steps = getResources().getStringArray(R.array.concussion_recommended);
                        imgSteps = new int[] { 0, 0, 0 };
                        setInjuryStepAdapter(steps, imgSteps, notes, recyclerView);
                    }
                } else if(injury.toLowerCase().equals("contusion")) {
                    if(position == 0) {
                        setEmptyArrayString(4);
                        steps = getResources().getStringArray(R.array.contusion_recommended);
                        imgSteps = new int[] { 0, 0, 0, 0 };
                        setInjuryStepAdapter(steps, imgSteps, notes, recyclerView);
                    }
                } else if(injury.toLowerCase().equals("fracture")) {
                    if(position == 0) {
                        setEmptyArrayString(8);
                        steps = getResources().getStringArray(R.array.fracture_recommended);
                        imgSteps = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
                        setInjuryStepAdapter(steps, imgSteps, notes, recyclerView);
                    }
                } else if(injury.toLowerCase().equals("major")) {
                    if(position == 0) {
                        setEmptyArrayString(6);
                        steps = getResources().getStringArray(R.array.laceration_major_recommended);
                        imgSteps = new int[] { 0, 0, 0, 0, 0, 0 };
                        setInjuryStepAdapter(steps, imgSteps, notes, recyclerView);
                    }
                } else if(injury.toLowerCase().equals("minor")) {
                    if(position == 0) {
                        setEmptyArrayString(5);
                        steps = getResources().getStringArray(R.array.laceration_minor_recommended);
                        imgSteps = new int[] { 0, 0, 0, 0, 0 };
                        setInjuryStepAdapter(steps, imgSteps, notes, recyclerView);
                    } else if(position == 1){
                        setEmptyArrayString(3);
                        steps = getResources().getStringArray(R.array.laceration_minor_alternative);
                        imgSteps = new int[] { 0, 0, 0 };
                        setInjuryStepAdapter(steps, imgSteps, notes, recyclerView);
                    }
                } else if(injury.toLowerCase().equals("severe")) {
                    if(position == 0) {
                        setEmptyArrayString(4);
                        steps = getResources().getStringArray(R.array.puncture_severe_recommended);
                        imgSteps = new int[] { 0, 0, 0, 0 };
                        setInjuryStepAdapter(steps, imgSteps, notes, recyclerView);
                    } else if(position == 1){
                        setEmptyArrayString(5);
                        steps = getResources().getStringArray(R.array.puncture_severe_alternative);
                        imgSteps = new int[] { 0, 0, 0, 0, 0 };
                        setInjuryStepAdapter(steps, imgSteps, notes, recyclerView);
                    }
                } else if(injury.toLowerCase().equals("slight")) {
                    if(position == 0) {
                        setEmptyArrayString(4);
                        steps = getResources().getStringArray(R.array.puncture_slight_recommended);
                        imgSteps = new int[] { 0, 0, 0, 0 };
                        setInjuryStepAdapter(steps, imgSteps, notes, recyclerView);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { /** DO NOTHING **/ }
        });
    }

    /*
        Function Name : setInjuryStepAdapter
        Function Description :  This function will be called in the setStepsAndImages
                                This function sets the adapter, passing the steps, images, and notes if available
        Function Developer : Jonas Aguilar
     */
    public void setInjuryStepAdapter(String[] step, int[] imgStep, String[] notes, RecyclerView recyclerView) {
        RecyclerStepsAdapter adapter = new RecyclerStepsAdapter(getActivity().getApplicationContext(), step, imgStep, notes);
        recyclerView.setAdapter(adapter);
    }

    /*
        Function Name : setRecyclerViewAsNotScrollable
        Function Description :  This function will be called in the onCreateView
                                This function disables the scrollable function of the RecyclerView
        Function Developer : Jonas Aguilar
     */
    public void setRecyclerViewAsNotScrollable() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        lv_chemical.setLayoutManager(new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        lv_electrical.setLayoutManager(new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
    }

    /*
        Function Name : setEmptyArrayString
        Function Description :  This function will be called in the setStepsAndImages
                                This function sets the notes to empty strings
        Function Developer : Jonas Aguilar
     */
    public void setEmptyArrayString(int number) {
        notes = new String[number];
        Arrays.fill(notes, "");
    }
}
