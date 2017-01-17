package com.example.jonas.pocketaid.InjuriesFragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jonas.pocketaid.MainActivity;
import com.example.jonas.pocketaid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InjuryOverviewFragment extends Fragment {

    private String injuryType;
    private ImageView injury_logo;
    private TextView overview_injury;
    private TextView overview_causes;
    private FloatingActionButton fab;

    public InjuryOverviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_injury_overview, container, false);
        fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.hide();

        String chosenInjury = getArguments().getString("injury");
        determineInjury(chosenInjury, rootView);

        return rootView;
    }

    public void determineInjury(String injuryChosen, ViewGroup rootView) {
        if(injuryChosen.toLowerCase().equals("abrasion")) {
            injuryType = "abrasion";
            configure(injuryType, rootView);
        } else if(injuryChosen.toLowerCase().equals("bites")) {
            injuryType = "bites";
            configure(injuryType, rootView);
        } else if(injuryChosen.toLowerCase().equals("burns")) {
            injuryType = "burns";
            configure(injuryType, rootView);
        } else if(injuryChosen.toLowerCase().equals("concussion")) {
            injuryType = "concussion";
            configure(injuryType, rootView);
        } else if(injuryChosen.toLowerCase().equals("contusion")) {
            injuryType = "contusion";
            configure(injuryType, rootView);
        } else if(injuryChosen.toLowerCase().equals("fracture")) {
            injuryType = "fracture";
            configure(injuryType, rootView);
        } else if(injuryChosen.toLowerCase().equals("laceration")) {
            injuryType = "laceration";
            configure(injuryType, rootView);
        } else if(injuryChosen.toLowerCase().equals("puncture")) {
            injuryType = "puncture";
            configure(injuryType, rootView);
        }
    }

    public void configure(String injury, ViewGroup rootView) {
        injury_logo = (ImageView) rootView.findViewById(R.id.injury_logo);
        injury_logo.setImageResource(getResources().getIdentifier("ic_" + injury, "drawable", getActivity().getPackageName()));

        overview_injury = (TextView) rootView.findViewById(R.id.overview_injury);
        overview_injury.setText(injury);

        overview_causes = (TextView) rootView.findViewById(R.id.overview_causes);
        overview_causes.setText("makulit");
    }
}
