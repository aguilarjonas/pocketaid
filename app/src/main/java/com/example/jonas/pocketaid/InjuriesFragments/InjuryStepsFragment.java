package com.example.jonas.pocketaid.InjuriesFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
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
    private ListView listView;
    private ListView lv_electrical;
    private ListView lv_chemical;
    private TextView tv_thermal;
    private TextView tv_chemical;
    private TextView tv_electrical;
    private String[] steps;
    private int[] imgSteps;

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
        lv_chemical = (ListView) rootView.findViewById(R.id.listview_chemical);
        lv_electrical = (ListView) rootView.findViewById(R.id.listview_electrical);
        tv_thermal = (TextView) rootView.findViewById(R.id.textview_thermal);
        tv_chemical = (TextView) rootView.findViewById(R.id.textview_chemical);
        tv_electrical = (TextView) rootView.findViewById(R.id.textview_electrical);

        disableSpinner(spinnerOptions, injury);
        setSpinnerOptions(spinnerOptions, injury);

        //spinner options (recommended or alternative)

        return rootView;
    }

    public void setInjuryStepAdapter(String[] step, int[] imgStep, ListView listView) {
        InjuryStepsAdapter adapter = new InjuryStepsAdapter(getActivity().getApplicationContext(), step, imgStep);
        listView.setAdapter(adapter);

        ListUtils.setDynamicHeight(listView);
    }

    public void disableSpinner(Spinner spinnerOptions, String injury) {
        if(injury.toLowerCase().equals("animal") || injury.toLowerCase().equals("concussion") || injury.toLowerCase().equals("contusion") ||
                injury.toLowerCase().equals("major") || injury.toLowerCase().equals("slight")) {
            spinnerOptions.setEnabled(false);
        }
    }

    public void setSpinnerOptions(Spinner spinnerOptions, final String injury) {
        spinnerOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(injury.toLowerCase().equals("abrasion")) {
                    if(position == 0) {
                        steps = getResources().getStringArray(R.array.abrasion_recommended);
                        imgSteps = new int[] { 0, 0, 0, 0 };
                        setInjuryStepAdapter(steps, imgSteps, listView);
                    } else if(position == 1){
                        steps = getResources().getStringArray(R.array.abrasion_alternative);
                        imgSteps = new int[] { 0, 0, 0, 0 };
                        setInjuryStepAdapter(steps, imgSteps, listView);
                    }
                } else if(injury.toLowerCase().equals("animal")) {
                    if(position == 0) {
                        steps = getResources().getStringArray(R.array.bites_animal_recommended);
                        imgSteps = new int[] { 0, 0, 0 };
                        setInjuryStepAdapter(steps, imgSteps, listView);
                    }
                } else if(injury.toLowerCase().equals("insect")) {
                    if(position == 0) {
                        steps = getResources().getStringArray(R.array.bites_insect_recommended);
                        imgSteps = new int[] { 0, 0, 0, 0, 0, 0 };
                        setInjuryStepAdapter(steps, imgSteps, listView);
                    } else if(position == 1){
                        steps = getResources().getStringArray(R.array.bites_insect_alternative);
                        imgSteps = new int[] { 0, 0, 0, 0, 0, 0 };
                        setInjuryStepAdapter(steps, imgSteps, listView);
                    }
                } else if(injury.toLowerCase().equals("firstseconddegree")) {
                    if(position == 0) {
                        steps = getResources().getStringArray(R.array.burns_thermal_recommended);
                        imgSteps = new int[] { 0, 0, 0 };
                        setInjuryStepAdapter(steps, imgSteps, listView);

                        steps = getResources().getStringArray(R.array.burns_chemical_recommended);
                        imgSteps = new int[] { 0, 0, 0, 0, 0, 0 };
                        setInjuryStepAdapter(steps, imgSteps, lv_chemical);

                        steps = getResources().getStringArray(R.array.burns_electrical_recommended);
                        imgSteps = new int[] { 0, 0 };
                        setInjuryStepAdapter(steps, imgSteps, lv_electrical);

                        tv_thermal.setVisibility(View.VISIBLE);
                        tv_chemical.setVisibility(View.VISIBLE);
                        tv_electrical.setVisibility(View.VISIBLE);
                        lv_chemical.setVisibility(View.VISIBLE);
                        lv_electrical.setVisibility(View.VISIBLE);
                    } else if(position == 1){
                        steps = getResources().getStringArray(R.array.burns_thermal_alternative);
                        imgSteps = new int[] { 0, 0, 0 };
                        setInjuryStepAdapter(steps, imgSteps, listView);
                    }
                } else if(injury.toLowerCase().equals("thirddegree")) {
                    if(position == 0) {
                        steps = getResources().getStringArray(R.array.burns_third_recommended);
                        imgSteps = new int[] { 0, 0, 0, 0, 0 };
                        setInjuryStepAdapter(steps, imgSteps, listView);
                    } else if(position == 1){
                        steps = getResources().getStringArray(R.array.burns_third_alternative);
                        imgSteps = new int[] { 0, 0, 0, 0, 0 };
                        setInjuryStepAdapter(steps, imgSteps, listView);
                    }
                } else if(injury.toLowerCase().equals("concussion")) {
                    if(position == 0) {
                        steps = getResources().getStringArray(R.array.concussion_recommended);
                        imgSteps = new int[] { 0, 0 };
                        setInjuryStepAdapter(steps, imgSteps, listView);
                    }
                } else if(injury.toLowerCase().equals("contusion")) {
                    if(position == 0) {
                        steps = getResources().getStringArray(R.array.contusion_recommended);
                        imgSteps = new int[] { 0, 0, 0, 0 };
                        setInjuryStepAdapter(steps, imgSteps, listView);
                    }
                } else if(injury.toLowerCase().equals("fracture")) {
                    if(position == 0) {
                        steps = getResources().getStringArray(R.array.fracture_recommended);
                        imgSteps = new int[] { 0, 0, 0, 0, 0, 0 };
                        setInjuryStepAdapter(steps, imgSteps, listView);
                    }
                } else if(injury.toLowerCase().equals("major")) {
                    if(position == 0) {
                        steps = getResources().getStringArray(R.array.laceration_major_recommended);
                        imgSteps = new int[] { 0, 0, 0, 0, 0, 0 };
                        setInjuryStepAdapter(steps, imgSteps, listView);
                    }
                } else if(injury.toLowerCase().equals("minor")) {
                    if(position == 0) {
                        steps = getResources().getStringArray(R.array.laceration_minor_recommended);
                        imgSteps = new int[] { 0, 0, 0, 0, 0 };
                        setInjuryStepAdapter(steps, imgSteps, listView);
                    } else if(position == 1){
                        steps = getResources().getStringArray(R.array.laceration_minor_alternative);
                        imgSteps = new int[] { 0, 0, 0 };
                        setInjuryStepAdapter(steps, imgSteps, listView);
                    }
                } else if(injury.toLowerCase().equals("severe")) {
                    if(position == 0) {
                        steps = getResources().getStringArray(R.array.puncture_severe_recommended);
                        imgSteps = new int[] { 0, 0, 0, 0 };
                        setInjuryStepAdapter(steps, imgSteps, listView);
                    } else if(position == 1){
                        steps = getResources().getStringArray(R.array.puncture_severe_alternative);
                        imgSteps = new int[] { 0, 0, 0, 0, 0 };
                        setInjuryStepAdapter(steps, imgSteps, listView);
                    }
                } else if(injury.toLowerCase().equals("slight")) {
                    if(position == 0) {
                        steps = getResources().getStringArray(R.array.puncture_slight_recommended);
                        imgSteps = new int[] { 0, 0, 0, 0 };
                        setInjuryStepAdapter(steps, imgSteps, listView);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { /** DO NOTHING **/ }
        });
    }

    public static class ListUtils {
        public static void setDynamicHeight(ListView mListView) {
            ListAdapter mListAdapter = mListView.getAdapter();
            if (mListAdapter == null) {
                // when adapter is null
                return;
            }
            int height = 0;
            int desiredWidth = View.MeasureSpec.makeMeasureSpec(mListView.getWidth(), View.MeasureSpec.UNSPECIFIED);
            for (int i = 0; i < mListAdapter.getCount(); i++) {
                View listItem = mListAdapter.getView(i, null, mListView);
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                height += listItem.getMeasuredHeight();
            }
            ViewGroup.LayoutParams params = mListView.getLayoutParams();
            params.height = height + (mListView.getDividerHeight() * (mListAdapter.getCount() - 1));
            mListView.setLayoutParams(params);
            mListView.requestLayout();
        }
    }
}
