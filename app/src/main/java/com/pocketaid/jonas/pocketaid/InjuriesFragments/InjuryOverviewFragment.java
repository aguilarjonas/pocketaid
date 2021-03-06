package com.pocketaid.jonas.pocketaid.InjuriesFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.codesgood.views.JustifiedTextView;
import com.pocketaid.jonas.pocketaid.MainActivity;
import com.pocketaid.jonas.pocketaid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InjuryOverviewFragment extends Fragment {

    private String injuryType;
    private ImageView injury_logo;
    private JustifiedTextView overview_causes;

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
        if(injuryChosen.toLowerCase().equals(getResources().getString(R.string.abrasion).toLowerCase())) {
            injuryType = "abrasion";
            initializeView(injuryType, rootView, getResources().getString(R.string.abrasion_cause));
        } else if(injuryChosen.toLowerCase().equals(getResources().getString(R.string.bites).toLowerCase())) {
            injuryType = "bites";
            initializeView(injuryType, rootView, getResources().getString(R.string.insect_bites_cause) + " " + getResources().getString(R.string.animal_bites_cause));
        } else if(injuryChosen.toLowerCase().equals(getResources().getString(R.string.burns_list).toLowerCase())) {
            injuryType = "burns";
            initializeView(injuryType, rootView, getResources().getString(R.string.burns_cause) + " " + getResources().getString(R.string.third_deg_burns_cause));
        } else if(injuryChosen.toLowerCase().equals(getResources().getString(R.string.concussion).toLowerCase())) {
            injuryType = "concussion";
            initializeView(injuryType, rootView, getResources().getString(R.string.concussion_cause));
        } else if(injuryChosen.toLowerCase().equals(getResources().getString(R.string.contusion).toLowerCase())) {
            injuryType = "contusion";
            initializeView(injuryType, rootView, getResources().getString(R.string.contusion_cause));
        } else if(injuryChosen.toLowerCase().equals(getResources().getString(R.string.fracture).toLowerCase())) {
            injuryType = "fracture";
            initializeView(injuryType, rootView, getResources().getString(R.string.fracture_cause));
        } else if(injuryChosen.toLowerCase().equals(getResources().getString(R.string.laceration).toLowerCase())) {
            injuryType = "laceration";
            initializeView(injuryType, rootView, getResources().getString(R.string.laceration_cause));
        } else if(injuryChosen.toLowerCase().equals(getResources().getString(R.string.puncture).toLowerCase())) {
            injuryType = "puncture";
            initializeView(injuryType, rootView, getResources().getString(R.string.puncture_cause));
        }
    }

    /*
        Function Name : initializeView
        Function Description :  This function will be called in the onCreateView
                                This function determines what type of injury was passed by InjuryTabLayout
        Function Developer : Jonas Aguilar
     */
    public void initializeView(String injury, ViewGroup rootView, String cause) {
        injury_logo = (ImageView) rootView.findViewById(R.id.injury_logo);
        injury_logo.setImageResource(getResources().getIdentifier("ic_" + injury, "drawable", getActivity().getPackageName()));

        overview_causes = (JustifiedTextView) rootView.findViewById(R.id.overview_causes);
        overview_causes.setText(cause);
    }
}
