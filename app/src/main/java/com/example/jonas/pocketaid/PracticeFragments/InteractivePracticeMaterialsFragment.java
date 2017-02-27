package com.example.jonas.pocketaid.PracticeFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jonas.pocketaid.Adapters.DialogPractice;
import com.example.jonas.pocketaid.InteractiveModules.InteractiveAnswerSheet;
import com.example.jonas.pocketaid.InteractiveModules.InteractiveModel;
import com.example.jonas.pocketaid.MainActivity;
import com.example.jonas.pocketaid.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class InteractivePracticeMaterialsFragment extends Fragment implements View.OnClickListener {

    ImageView imageView_1;
    ImageView imageView_2;
    ImageView imageView_3;
    ImageView imageView_4;
    ImageView imageView_5;
    ImageView imageView_6;

    FrameLayout frameLayout_1;
    ImageView imageView_1_check;
    ImageView imageView_2_check;
    ImageView imageView_3_check;
    ImageView imageView_4_check;
    ImageView imageView_5_check;
    ImageView imageView_6_check;

    TextView textView_1;
    TextView textView_2;
    TextView textView_3;
    TextView textView_4;
    TextView textView_5;
    TextView textView_6;

    private ViewGroup rootView;
    private String injuryType;

    ArrayList<String> answersUser = new ArrayList<String>();
    InteractiveAnswerSheet interactiveSheet = new InteractiveAnswerSheet();

    int numberOfTries = 0;
    int numberOfError = 0;
    int numberOfCorrect = 0;



    final ArrayList<ArrayList<String>> categoryAnswer = new ArrayList<ArrayList<String>>();
    Button nextButton;

    public InteractivePracticeMaterialsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final String CHOSEN_PRACTICE = getArguments().getString("chosenPractice");
        //changes menu button to Up or Back button
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((MainActivity) getActivity()).resetActionBar(true, DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_interactive_practice_materials, container, false);

        setImages(rootView);
//        setImageOnClicks();
        chosenPracticeChooser(CHOSEN_PRACTICE);


        nextButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkAnswers(interactiveSheet.checkAnswerSheet(CHOSEN_PRACTICE)) == false){
//                    Toast.makeText(getActivity().getApplicationContext(), "Wrong Answer! Try Again", Toast.LENGTH_SHORT).show();
                    showDialog(getString(R.string.wrong));
                    numberOfTries++;
                    numberOfError++;
                } else {
                    answersUser.clear();
                    showDialog(getString(R.string.correct));
                    numberOfTries++;
                    numberOfCorrect++;
                    Log.e("Correct", numberOfCorrect + " ");
                    Log.e("Error", numberOfError + " " );
                    Log.e("Tries", numberOfTries + " " );

                    moveToListFragment();
                }
            }
        });
        return rootView;
    }

    public void showDialog(String correctOrNot) {
        DialogPractice dialogPractice = new DialogPractice();
        dialogPractice.showDialog(getActivity(), correctOrNot);
    }

    public void moveToListFragment() {
        InteractivePracticeStepsOrdering listFrag = new InteractivePracticeStepsOrdering();
        Bundle args = new Bundle();
        args.putString("chosenInjury", injuryType);

        InteractiveModel interModel = InteractiveModel.getInstance();
        interModel.setNumberOfTries(numberOfTries);
        interModel.setNumberOfError(numberOfError);
        interModel.setNumberOfCorrect(numberOfCorrect);
//        args.putString("numberOfTries", String.valueOf(numberOfTries));
//        args.putString("numberOfErrors", String.valueOf(numberOfError));

        listFrag.setArguments(args);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(listFrag, "listFrag")
                .replace(R.id.fragment_container, listFrag)
                .addToBackStack("listFrag")
                .commit();
    }


    public void setImages(ViewGroup rootView){
        imageView_1 = (ImageView) rootView.findViewById(R.id.imageView_pic1);
        imageView_2 = (ImageView) rootView.findViewById(R.id.imageView_pic2);
        imageView_3 = (ImageView) rootView.findViewById(R.id.imageView_pic3);
        imageView_4 = (ImageView) rootView.findViewById(R.id.imageView_pic4);
        imageView_5 = (ImageView) rootView.findViewById(R.id.imageView_pic5);
        imageView_6 = (ImageView) rootView.findViewById(R.id.imageView_pic6);

        imageView_1_check = (ImageView) rootView.findViewById(R.id.imageView1_check);
        imageView_2_check = (ImageView) rootView.findViewById(R.id.imageView2_check);
        imageView_3_check = (ImageView) rootView.findViewById(R.id.imageView3_check);
        imageView_4_check = (ImageView) rootView.findViewById(R.id.imageView4_check);
        imageView_5_check = (ImageView) rootView.findViewById(R.id.imageView5_check);
        imageView_6_check = (ImageView) rootView.findViewById(R.id.imageView6_check);

        textView_1 = (TextView) rootView.findViewById(R.id.textView_materials1);
        textView_2 = (TextView) rootView.findViewById(R.id.textView_materials2);
        textView_3 = (TextView) rootView.findViewById(R.id.textView_materials3);
        textView_4 = (TextView) rootView.findViewById(R.id.textView_materials4);
        textView_5 = (TextView) rootView.findViewById(R.id.textView_materials5);
        textView_6 = (TextView) rootView.findViewById(R.id.textView_materials6);

        imageView_1.setOnClickListener(this);
        imageView_2.setOnClickListener(this);
        imageView_3.setOnClickListener(this);
        imageView_4.setOnClickListener(this);
        imageView_5.setOnClickListener(this);
        imageView_6.setOnClickListener(this);



        nextButton =  (Button) rootView.findViewById(R.id.button_practice_next);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.imageView_pic1:
                if (imageView_1_check.getVisibility() == View.VISIBLE){
                    imageView_1_check.setVisibility(View.INVISIBLE);
                    answersUser.remove("1");
                }

                else if (imageView_1_check.getVisibility() == View.INVISIBLE){
                    imageView_1_check.setVisibility(View.VISIBLE);
                    answersUser.add("1");
                }
                break;

            case R.id.imageView_pic2:
                if (imageView_2_check.getVisibility() == View.VISIBLE){
                    imageView_2_check.setVisibility(View.INVISIBLE);
                    answersUser.remove("2");
                }

                else if (imageView_2_check.getVisibility() == View.INVISIBLE){
                    imageView_2_check.setVisibility(View.VISIBLE);
                    answersUser.add("2");
                }
                break;

            case R.id.imageView_pic3:
                if (imageView_3_check.getVisibility() == View.VISIBLE){
                    imageView_3_check.setVisibility(View.INVISIBLE);
                    answersUser.remove("3");
                }

                else if (imageView_3_check.getVisibility() == View.INVISIBLE){
                    imageView_3_check.setVisibility(View.VISIBLE);
                    answersUser.add("3");
                }
                break;

            case R.id.imageView_pic4:
                if (imageView_4_check.getVisibility() == View.VISIBLE){
                    imageView_4_check.setVisibility(View.INVISIBLE);
                    answersUser.remove("4");
                }

                else if (imageView_4_check.getVisibility() == View.INVISIBLE){
                    imageView_4_check.setVisibility(View.VISIBLE);
                    answersUser.add("4");
                }
                break;

            case R.id.imageView_pic5:
                if (imageView_5_check.getVisibility() == View.VISIBLE){
                    imageView_5_check.setVisibility(View.INVISIBLE);
                    answersUser.remove("5");
                }

                else if (imageView_5_check.getVisibility() == View.INVISIBLE){
                    imageView_5_check.setVisibility(View.VISIBLE);
                    answersUser.add("5");
                }
                break;

            case R.id.imageView_pic6:
                if (imageView_6_check.getVisibility() == View.VISIBLE) {
                    imageView_6_check.setVisibility(View.INVISIBLE);
                    answersUser.remove("6");
                } else if (imageView_6_check.getVisibility() == View.INVISIBLE) {
                    imageView_6_check.setVisibility(View.VISIBLE);
                    answersUser.add("6");
                }
                break;
        }
    }

    public boolean checkAnswers(ArrayList<String> answerSheet){

        boolean isAllCorrect = false;
        int numberOfCorrect = 0;
        int numberOfIncorrect = 0;

        if (answersUser.size() != answerSheet.size()){
            isAllCorrect = false;
        }

        else {
            for (int i = 0; i < answerSheet.size(); i++) {
                for (int j = 0; j < answersUser.size(); j++) {

                    if (answerSheet.get(i) == answersUser.get(j)){
                        numberOfCorrect++;
                    }

                    else{
                        numberOfIncorrect++;
                    }
                }
            }//End of 1st For Loop

            int testHolder = answerSheet.size();
            Log.e("Correct/Item", numberOfCorrect + "/" + testHolder);
            Log.e("Incorrect", numberOfIncorrect + "");

            if (numberOfCorrect == answerSheet.size() ){
                isAllCorrect = true;
            } else
                isAllCorrect = false;
        }
        return isAllCorrect;
    }

    public void chosenPracticeChooser(String chosenPractice){

        if (chosenPractice.equals("Abrasion")){
            injuryType = "Abrasion";
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_SHORT).show();
            abrasionPractice(chosenPractice);

        } else if (chosenPractice.equals("Animal Bites")){
            injuryType = "Animal Bites";
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_SHORT).show();
            animalBitesPractice();

        } else if (chosenPractice.equals("Insect Bites")){
            injuryType = "Insect Bites";
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_SHORT).show();
            insectBitesPractice();

        } else if (chosenPractice.equals("Thermal Burns")){
            injuryType = "Thermal Burns";
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_SHORT).show();
            thermalPractice();

        } else if (chosenPractice.equals("Chemical Burns")){
            injuryType = "Chemical Burns";
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_SHORT).show();
            chemicalPractice();

        } else if (chosenPractice.equals("3rd Degree Burns")){
            injuryType = "3rd Degree Burns";
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_SHORT).show();
            thermalPractice2();

        } else if (chosenPractice.equals("Concussion")){
            injuryType = "Concussion";
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_SHORT).show();
            concussionPractice();

        } else if (chosenPractice.equals("Contusion")){
            injuryType = "Contusion";
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_SHORT).show();
            contussionPractice();

        } else if (chosenPractice.equals("Fracture")){
            injuryType = "Fracture";
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_SHORT).show();
            fracturePractice();

        } else if (chosenPractice.equals("Major Laceration")){
            injuryType = "Major Laceration";
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_SHORT).show();
            majorLacerationPractice();

        } else if (chosenPractice.equals("Minor Laceration")){
            injuryType = "Minor Laceration";
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_SHORT).show();
            minorLacerationPractice();

        } else if (chosenPractice.equals("Puncture (Severe Bleeding)")){
            injuryType = "Puncture (Severe Bleeding)";
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_SHORT).show();
            punctureSeverePractice();

        } else if (chosenPractice.equals("Puncture (Slightly Bleeding)")){
            injuryType = "Puncture (Slightly Bleeding)";
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_SHORT).show();
            punctureSlightPractice();
        }
    }

    public void setChecksAsInvisible(){
        imageView_1_check.setVisibility(View.INVISIBLE);
        imageView_2_check.setVisibility(View.INVISIBLE);
        imageView_3_check.setVisibility(View.INVISIBLE);
        imageView_4_check.setVisibility(View.INVISIBLE);
        imageView_5_check.setVisibility(View.INVISIBLE);
        imageView_6_check.setVisibility(View.INVISIBLE);
    }

    public void setTheChosenText(ArrayList<String> textHolder){

        textView_1.setText(textHolder.get(0));
        textView_2.setText(textHolder.get(1));
        textView_3.setText(textHolder.get(2));
        textView_4.setText(textHolder.get(3));
        textView_5.setText(textHolder.get(4));
        textView_6.setText(textHolder.get(5));

    }

    public void abrasionPractice (String injuryType){
        //Set the materials Image here.
        setChecksAsInvisible();
        imageView_1.setImageResource(R.drawable.ic_laceration);
        imageView_2.setImageResource(R.drawable.ic_concussion);
        imageView_3.setImageResource(R.drawable.ic_bites);
        imageView_4.setImageResource(R.drawable.ic_laceration);
        imageView_5.setImageResource(R.drawable.ic_fracture);
        imageView_6.setImageResource(R.drawable.ic_insect);

        setTheChosenText(interactiveSheet.getMaterialTexts(injuryType));
    }

    public void animalBitesPractice (){
        //Set the materials Image here.
        setChecksAsInvisible();
        imageView_1.setImageResource(R.drawable.ic_laceration);
        imageView_2.setImageResource(R.drawable.ic_concussion);
        imageView_3.setImageResource(R.drawable.ic_bites);
        imageView_4.setImageResource(R.drawable.ic_laceration);
        imageView_5.setImageResource(R.drawable.ic_fracture);
        imageView_6.setImageResource(R.drawable.ic_insect);

        setTheChosenText(interactiveSheet.getMaterialTexts(injuryType));
    }

    public void insectBitesPractice (){
        //Set the materials Image here.
        setChecksAsInvisible();
        imageView_1.setImageResource(R.drawable.ic_laceration);
        imageView_2.setImageResource(R.drawable.ic_concussion);
        imageView_3.setImageResource(R.drawable.ic_bites);
        imageView_4.setImageResource(R.drawable.ic_laceration);
        imageView_5.setImageResource(R.drawable.ic_fracture);
        imageView_6.setImageResource(R.drawable.ic_insect);

        setTheChosenText(interactiveSheet.getMaterialTexts(injuryType));
    }

    public void thermalPractice (){
        //Set the materials Image here.
        setChecksAsInvisible();
        imageView_1.setImageResource(R.drawable.ic_laceration);
        imageView_2.setImageResource(R.drawable.ic_concussion);
        imageView_3.setImageResource(R.drawable.ic_bites);
        imageView_4.setImageResource(R.drawable.ic_laceration);
        imageView_5.setImageResource(R.drawable.ic_fracture);
        imageView_6.setImageResource(R.drawable.ic_insect);

        setTheChosenText(interactiveSheet.getMaterialTexts(injuryType));
    }

    public void thermalPractice2 (){
        //Set the materials Image here.
        setChecksAsInvisible();
        imageView_1.setImageResource(R.drawable.ic_laceration);
        imageView_2.setImageResource(R.drawable.ic_concussion);
        imageView_3.setImageResource(R.drawable.ic_bites);
        imageView_4.setImageResource(R.drawable.ic_laceration);
        imageView_5.setImageResource(R.drawable.ic_fracture);
        imageView_6.setImageResource(R.drawable.ic_insect);

        setTheChosenText(interactiveSheet.getMaterialTexts(injuryType));
    }



    public void chemicalPractice (){
        //Set the materials Image here.
        setChecksAsInvisible();
        imageView_1.setImageResource(R.drawable.ic_laceration);
        imageView_2.setImageResource(R.drawable.ic_concussion);
        imageView_3.setImageResource(R.drawable.ic_bites);
        imageView_4.setImageResource(R.drawable.ic_laceration);
        imageView_5.setImageResource(R.drawable.ic_fracture);
        imageView_6.setImageResource(R.drawable.ic_insect);

        setTheChosenText(interactiveSheet.getMaterialTexts(injuryType));
    }

    public void concussionPractice (){
        //Set the materials Image here.
        setChecksAsInvisible();
        imageView_1.setImageResource(R.drawable.ic_laceration);
        imageView_2.setImageResource(R.drawable.ic_concussion);
        imageView_3.setImageResource(R.drawable.ic_bites);
        imageView_4.setImageResource(R.drawable.ic_laceration);
        imageView_5.setImageResource(R.drawable.ic_fracture);
        imageView_6.setImageResource(R.drawable.ic_insect);

        setTheChosenText(interactiveSheet.getMaterialTexts(injuryType));
    }

    public void contussionPractice (){
        //Set the materials Image here.
        setChecksAsInvisible();
        imageView_1.setImageResource(R.drawable.ic_laceration);
        imageView_2.setImageResource(R.drawable.ic_concussion);
        imageView_3.setImageResource(R.drawable.ic_bites);
        imageView_4.setImageResource(R.drawable.ic_laceration);
        imageView_5.setImageResource(R.drawable.ic_fracture);
        imageView_6.setImageResource(R.drawable.ic_insect);

        setTheChosenText(interactiveSheet.getMaterialTexts(injuryType));
    }

    public void fracturePractice (){
        //Set the materials Image here.
        setChecksAsInvisible();
        imageView_1.setImageResource(R.drawable.ic_laceration);
        imageView_2.setImageResource(R.drawable.ic_concussion);
        imageView_3.setImageResource(R.drawable.ic_bites);
        imageView_4.setImageResource(R.drawable.ic_laceration);
        imageView_5.setImageResource(R.drawable.ic_fracture);
        imageView_6.setImageResource(R.drawable.ic_insect);

        setTheChosenText(interactiveSheet.getMaterialTexts(injuryType));
    }

    public void majorLacerationPractice (){
        //Set the materials Image here.
        setChecksAsInvisible();
        imageView_1.setImageResource(R.drawable.ic_laceration);
        imageView_2.setImageResource(R.drawable.ic_concussion);
        imageView_3.setImageResource(R.drawable.ic_bites);
        imageView_4.setImageResource(R.drawable.ic_laceration);
        imageView_5.setImageResource(R.drawable.ic_fracture);
        imageView_6.setImageResource(R.drawable.ic_insect);

        setTheChosenText(interactiveSheet.getMaterialTexts(injuryType));
    }

    public void minorLacerationPractice (){
        //Set the materials Image here.
        setChecksAsInvisible();
        imageView_1.setImageResource(R.drawable.ic_laceration);
        imageView_2.setImageResource(R.drawable.ic_concussion);
        imageView_3.setImageResource(R.drawable.ic_bites);
        imageView_4.setImageResource(R.drawable.ic_laceration);
        imageView_5.setImageResource(R.drawable.ic_fracture);
        imageView_6.setImageResource(R.drawable.ic_insect);

        setTheChosenText(interactiveSheet.getMaterialTexts(injuryType));
    }

    public void punctureSeverePractice (){
        //Set the materials Image here.
        setChecksAsInvisible();
        imageView_1.setImageResource(R.drawable.ic_laceration);
        imageView_2.setImageResource(R.drawable.ic_concussion);
        imageView_3.setImageResource(R.drawable.ic_bites);
        imageView_4.setImageResource(R.drawable.ic_laceration);
        imageView_5.setImageResource(R.drawable.ic_fracture);
        imageView_6.setImageResource(R.drawable.ic_insect);

        setTheChosenText(interactiveSheet.getMaterialTexts(injuryType));
    }

    public void punctureSlightPractice () {
        //Set the materials Image here.
        setChecksAsInvisible();
        imageView_1.setImageResource(R.drawable.ic_laceration);
        imageView_2.setImageResource(R.drawable.ic_concussion);
        imageView_3.setImageResource(R.drawable.ic_bites);
        imageView_4.setImageResource(R.drawable.ic_laceration);
        imageView_5.setImageResource(R.drawable.ic_fracture);
        imageView_6.setImageResource(R.drawable.ic_insect);

        setTheChosenText(interactiveSheet.getMaterialTexts(injuryType));
    }



}
