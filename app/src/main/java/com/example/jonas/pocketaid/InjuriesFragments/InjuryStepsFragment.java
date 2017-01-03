package com.example.jonas.pocketaid.InjuriesFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.jonas.pocketaid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InjuryStepsFragment extends Fragment {

    private Spinner spinnerOptions;
    private ListView listView;
    private String[] steps;

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

        spinnerOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(injury.toLowerCase().equals("abrasion")) {
                    if(position == 0) {
                        steps = getResources().getStringArray(R.array.abrasion_recommended);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.layout_firstaid_steps, R.id.steps,
                                steps);

                        listView.setAdapter(adapter);
                    } else {
                        steps = getResources().getStringArray(R.array.abrasion_alternative);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.layout_firstaid_steps, R.id.steps,
                                steps);

                        listView.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return rootView;
    }

}
