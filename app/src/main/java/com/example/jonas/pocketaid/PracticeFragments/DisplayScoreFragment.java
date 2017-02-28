package com.example.jonas.pocketaid.PracticeFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jonas.pocketaid.InteractiveModules.InteractiveModel;
import com.example.jonas.pocketaid.R;

public class DisplayScoreFragment extends Fragment {

    private Button goBack;
    private TextView totalMistakes;
    private TextView totalScore;
    private TextView percentage;
    private ImageView logo;

    public DisplayScoreFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_display_score, container, false);

        InteractiveModel interactiveModel = InteractiveModel.getInstance();
        goBack = (Button) rootView.findViewById(R.id.goBackButton);
        totalMistakes = (TextView) rootView.findViewById(R.id.textView_totalMistakes);
        totalScore = (TextView) rootView.findViewById(R.id.textView_totalScore);

        totalMistakes.setText(Integer.toString(interactiveModel.getNumberOfTries()));
        totalScore.setText(Integer.toString(interactiveModel.getNumberOfError()));

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStackImmediate("Practice", 0);
            }
        });

        return rootView;
    }
}
