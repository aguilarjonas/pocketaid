package com.example.jonas.pocketaid.InjuriesFragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
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
        ((MainActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(false);
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getContext().INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(rootView.getWindowToken(), 0);

        String chosenInjury = getArguments().getString("injury");
        determineInjury(chosenInjury, rootView);

        return rootView;
    }

    /*
        Function Name : determineInjuryType
        Function Description :  This function will be called in the onCreateView
                                This function determines what type of injury was passed by InjuryTabLayout
        Function Developer : Jonas Aguilar
     */
    public void determineInjury(String injuryChosen, ViewGroup rootView) {
        if(injuryChosen.toLowerCase().equals("abrasion")) {
            injuryType = "abrasion";
            initializeView(injuryType, rootView);
        } else if(injuryChosen.toLowerCase().equals("bites")) {
            injuryType = "bites";
            initializeView(injuryType, rootView);
        } else if(injuryChosen.toLowerCase().equals("burns")) {
            injuryType = "burns";
            initializeView(injuryType, rootView);
        } else if(injuryChosen.toLowerCase().equals("concussion")) {
            injuryType = "concussion";
            initializeView(injuryType, rootView);
        } else if(injuryChosen.toLowerCase().equals("contusion")) {
            injuryType = "contusion";
            initializeView(injuryType, rootView);
        } else if(injuryChosen.toLowerCase().equals("fracture")) {
            injuryType = "fracture";
            initializeView(injuryType, rootView);
        } else if(injuryChosen.toLowerCase().equals("laceration")) {
            injuryType = "laceration";
            initializeView(injuryType, rootView);
        } else if(injuryChosen.toLowerCase().equals("puncture")) {
            injuryType = "puncture";
            initializeView(injuryType, rootView);
        }
    }

    /*
        Function Name : initializeView
        Function Description :  This function will be called in the onCreateView
                                This function determines what type of injury was passed by InjuryTabLayout
        Function Developer : Jonas Aguilar
     */
    public void initializeView(String injury, ViewGroup rootView) {
        injury_logo = (ImageView) rootView.findViewById(R.id.injury_logo);
        injury_logo.setImageResource(getResources().getIdentifier("ic_" + injury, "drawable", getActivity().getPackageName()));

        overview_causes = (TextView) rootView.findViewById(R.id.overview_causes);
        overview_causes.setText("makulit");
    }


}
