package com.pocketaid.jonas.pocketaid.PracticeFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.pocketaid.jonas.pocketaid.InteractiveModules.InteractiveModel;
import com.pocketaid.jonas.pocketaid.R;

public class DisplayScoreFragment extends Fragment {

    InteractiveModel interactiveModel;
    private Button goBack;
    private TextView stageOneCorrectTries;
    private TextView stageTwoCorrectTries;
    private TextView stageThreeCorrectTries;
    private TextView stageOnePercentage;
    private TextView stageTwoPercentage;
    private TextView stageThreePercentage;
    private TextView averagePercentage;
    private ImageView logo;

    public DisplayScoreFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_display_score, container, false);

        initialize(rootView);
        setValues();

        return rootView;
    }

    public void setValues() {
        stageOneCorrectTries.setText(interactiveModel.getStage1Stats().get(0) + "/" + interactiveModel.getStage1Stats().get(2));
        stageTwoCorrectTries.setText(interactiveModel.getStage2Stats().get(0) + "/" + interactiveModel.getStage2Stats().get(2));
        stageThreeCorrectTries.setText(interactiveModel.getStage3Stats().get(0) + "/" + interactiveModel.getStage3Stats().get(2));
        stageOnePercentage.setText(String.format("%.2f", interactiveModel.getStageOnePercentage()) + "%");
        stageTwoPercentage.setText(String.format("%.2f", interactiveModel.getStageTwoPercentage()) + "%");
        stageThreePercentage.setText(String.format("%.2f", interactiveModel.getStageThreePercentage()) + "%");
        averagePercentage.setText(String.format("%.2f", interactiveModel.getAveragePercentage()) + "%");
    }

    public void initialize(ViewGroup rootView) {
        interactiveModel = InteractiveModel.getInstance();
        stageOneCorrectTries = (TextView) rootView.findViewById(R.id.stage1_correcttries);
        stageTwoCorrectTries = (TextView) rootView.findViewById(R.id.stage2_correcttries);
        stageThreeCorrectTries = (TextView) rootView.findViewById(R.id.stage3_correcttries);
        stageOnePercentage = (TextView) rootView.findViewById(R.id.stage1_percentage);
        stageTwoPercentage = (TextView) rootView.findViewById(R.id.stage2_percentage);
        stageThreePercentage = (TextView) rootView.findViewById(R.id.stage3_percentage);
        averagePercentage = (TextView) rootView.findViewById(R.id.textView_percentage);
        logo = (ImageView) rootView.findViewById(R.id.imageView_results);
        goBack = (Button) rootView.findViewById(R.id.goBackButton);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStackImmediate("Practice", 0);
            }
        });
    }
}
