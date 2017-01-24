package com.example.jonas.pocketaid.PracticeFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jonas.pocketaid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InteractivePracticeFragment extends Fragment {

    private TextView injury_tv;

    public InteractivePracticeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_interactive_practice, container, false);
        String chosenInjury = getArguments().getString("injury");
        injury_tv = (TextView) rootView.findViewById(R.id.injury);

        injury_tv.setText(chosenInjury);

        return rootView;
    }

}
