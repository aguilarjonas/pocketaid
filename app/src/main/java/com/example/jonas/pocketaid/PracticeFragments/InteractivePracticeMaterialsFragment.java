package com.example.jonas.pocketaid.PracticeFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jonas.pocketaid.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class InteractivePracticeMaterialsFragment extends Fragment {

    ImageView imageView_1;
    ImageView imageView_2;
    ImageView imageView_3;
    ImageView imageView_4;
    ImageView imageView_5;
    ImageView imageView_6;

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


    private String injuryType;

    ArrayList<String> answersUser = new ArrayList<String>();
    final ArrayList<String> ANSWER_ABRASION = new ArrayList<String>(){{
        add("1");
        add("2");
        add("3");
    }};

    final ArrayList<String> ANSWER_ANIMAL_BITES = new ArrayList<String>(){{
        add("1");
        add("2");
        add("3");
    }};

    final ArrayList<String> ANSWER_INSECT_BITES = new ArrayList<String>(){{
        add("1");
        add("2");
        add("3");
    }};

    final ArrayList<String> ANSWER_THERMAL = new ArrayList<String>(){{
        add("1");
        add("2");
        add("3");
    }};

    final ArrayList<String> ANSWER_CHEMICAL = new ArrayList<String>(){{
        add("1");
        add("2");
        add("3");
    }};

    final ArrayList<String> ANSWER_THERMAL2 = new ArrayList<String>(){{
        add("1");
        add("2");
        add("3");
    }};

    final ArrayList<String> ANSWER_CONCUSSION = new ArrayList<String>(){{
        add("1");
        add("2");
        add("3");
    }};

    final ArrayList<String> ANSWER_CONTUSION = new ArrayList<String>(){{
        add("1");
        add("2");
        add("3");
    }};

    final ArrayList<String> ANSWER_FRACTURE = new ArrayList<String>(){{
        add("1");
        add("2");
        add("3");
    }};

    final ArrayList<String> ANSWER_MAJOR_LACERATION = new ArrayList<String>(){{
        add("1");
        add("2");
        add("3");
    }};

    final ArrayList<String> ANSWER_MINOR_LACERATION = new ArrayList<String>(){{
        add("1");
        add("2");
        add("3");
    }};

    final ArrayList<String> ANSWER_PUNCTURE_SEVERE = new ArrayList<String>(){{
        add("1");
        add("2");
        add("3");
    }};

    final ArrayList<String> ANSWER_PUNCTURE_SLIGHT = new ArrayList<String>(){{
        add("1");
        add("2");
        add("3");
    }};


    final ArrayList<ArrayList<String>> categoryAnswer = new ArrayList<ArrayList<String>>();
    Button nextButton;

    public InteractivePracticeMaterialsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final String chosenPractice = getArguments().getString("chosenPractice");
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_interactive_practice_materials, container, false);

        setImages(rootView);
        setImageOnClicks();
        chosenPracticeChooser(chosenPractice);


        nextButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkAnswers(checkAnswerSheet()) == false){
                    Toast.makeText(getActivity().getApplicationContext(), "Wrong Answer! Try Again", Toast.LENGTH_SHORT).show();
                }

                else {
                    answersUser.clear();

                    // TODO Auto-generated method stub
                    chosenPracticeChooser(chosenPractice);
                    ListFragment listFrag = new ListFragment();
                    Bundle args = new Bundle();
                    args.putString("chosenInjury", injuryType);
                    listFrag.setArguments(args);

                    FragmentTransaction fragmentTransaction = getFragmentManager ().beginTransaction();
                    fragmentTransaction.add(listFrag, "listFrag")
                            .replace(R.id.fragment_container, listFrag)
                            .addToBackStack("listFrag")
                            .commit();
                }
            }
        });
        return rootView;
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


        nextButton =  (Button) rootView.findViewById(R.id.button_practice_next);
    }

    public void setImageOnClicks(){
        imageView_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (imageView_1_check.getVisibility() == View.VISIBLE){
                    imageView_1_check.setVisibility(View.INVISIBLE);
                    answersUser.remove("1");
                }

                else if (imageView_1_check.getVisibility() == View.INVISIBLE){
                    imageView_1_check.setVisibility(View.VISIBLE);
                    answersUser.add("1");
                }

            }
        });

        imageView_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (imageView_2_check.getVisibility() == View.VISIBLE){
                    imageView_2_check.setVisibility(View.INVISIBLE);
                    answersUser.remove("2");
                }

                else if (imageView_2_check.getVisibility() == View.INVISIBLE){
                    imageView_2_check.setVisibility(View.VISIBLE);
                    answersUser.add("2");
                }

            }
        });

        imageView_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (imageView_3_check.getVisibility() == View.VISIBLE){
                    imageView_3_check.setVisibility(View.INVISIBLE);
                    answersUser.remove("3");
                }

                else if (imageView_3_check.getVisibility() == View.INVISIBLE){
                    imageView_3_check.setVisibility(View.VISIBLE);
                    answersUser.add("3");
                }

            }
        });

        imageView_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (imageView_4_check.getVisibility() == View.VISIBLE){
                    imageView_4_check.setVisibility(View.INVISIBLE);
                    answersUser.remove("4");
                }

                else if (imageView_4_check.getVisibility() == View.INVISIBLE){
                    imageView_4_check.setVisibility(View.VISIBLE);
                    answersUser.add("4");
                }
            }
        });

        imageView_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (imageView_5_check.getVisibility() == View.VISIBLE){
                    imageView_5_check.setVisibility(View.INVISIBLE);
                    answersUser.remove("5");
                }

                else if (imageView_5_check.getVisibility() == View.INVISIBLE){
                    imageView_5_check.setVisibility(View.VISIBLE);
                    answersUser.add("5");
                }
            }
        });

        imageView_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (imageView_6_check.getVisibility() == View.VISIBLE) {
                    imageView_6_check.setVisibility(View.INVISIBLE);
                    answersUser.remove("6");
                } else if (imageView_6_check.getVisibility() == View.INVISIBLE) {
                    imageView_6_check.setVisibility(View.VISIBLE);
                    answersUser.add("6");
                }
            }
        });
    }

    public ArrayList<String> checkAnswerSheet(){

        ArrayList<String> answerSheetDefault = new ArrayList<String>();

        if (injuryType == "Abrasion"){
            return ANSWER_ABRASION;
        }
        else if (injuryType == "Animal Bites"){
            return ANSWER_ANIMAL_BITES;

        } else if ((injuryType == "Insect Bites")){
            return ANSWER_INSECT_BITES;

        } else if ((injuryType == "Thermal Burns")){
            return ANSWER_THERMAL;

        } else if ((injuryType == "Chemical Burns")){
            return ANSWER_CHEMICAL;

        } else if ((injuryType == "3rd Degree Burns")){
            return ANSWER_THERMAL2;

        } else if ((injuryType == "Concussion")){
            return ANSWER_CONCUSSION;

        } else if ((injuryType == "Contusion")){
            return ANSWER_CONTUSION;

        } else if ((injuryType == "Fracture")){
            return ANSWER_FRACTURE;

        } else if ((injuryType == "Major Laceration")){
            return ANSWER_MAJOR_LACERATION;

        } else if ((injuryType == "Minor Laceration")){
            return ANSWER_MINOR_LACERATION;

        } else if ((injuryType == "Puncture (Severe Bleeding)")){
            return ANSWER_PUNCTURE_SEVERE;

        } else if ((injuryType == "Puncture (Slightly Bleeding)")){
            return ANSWER_PUNCTURE_SLIGHT;
        }

        return answerSheetDefault;
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
            abrasionPractice();

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

    public void abrasionPractice (){
        //Set the materials Image here.
        setChecksAsInvisible();
        imageView_1.setImageResource(R.drawable.ic_laceration);
        imageView_2.setImageResource(R.drawable.ic_concussion);
        imageView_3.setImageResource(R.drawable.ic_bites);
        imageView_4.setImageResource(R.drawable.ic_laceration);
        imageView_5.setImageResource(R.drawable.ic_fracture);
        imageView_6.setImageResource(R.drawable.ic_insect);

        textView_1.setText("I");
        textView_2.setText("HATE");
        textView_3.setText("THESIS");
        textView_4.setText("AYOKO");
        textView_5.setText("NA");
        textView_6.setText("MAG ABRASION");

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

        textView_1.setText("I");
        textView_2.setText("HATE");
        textView_3.setText("THESIS");
        textView_4.setText("AYOKO");
        textView_5.setText("NA");
        textView_6.setText("MAG ANIMAL BITES");

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

        textView_1.setText("I");
        textView_2.setText("HATE");
        textView_3.setText("THESIS");
        textView_4.setText("AYOKO");
        textView_5.setText("NA");
        textView_6.setText("MAG INSECT BITES");

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

        textView_1.setText("I");
        textView_2.setText("HATE");
        textView_3.setText("THESIS");
        textView_4.setText("AYOKO");
        textView_5.setText("NA");
        textView_6.setText("MAG THERMAL");

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

        textView_1.setText("I");
        textView_2.setText("HATE");
        textView_3.setText("THESIS");
        textView_4.setText("AYOKO");
        textView_5.setText("NA");
        textView_6.setText("MAG THERMAL2");

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

        textView_1.setText("I");
        textView_2.setText("HATE");
        textView_3.setText("THESIS");
        textView_4.setText("AYOKO");
        textView_5.setText("NA");
        textView_6.setText("MAG CHEMICAL");

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

        textView_1.setText("I");
        textView_2.setText("HATE");
        textView_3.setText("THESIS");
        textView_4.setText("AYOKO");
        textView_5.setText("NA");
        textView_6.setText("MAG CONCUSSION");
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

        textView_1.setText("I");
        textView_2.setText("HATE");
        textView_3.setText("THESIS");
        textView_4.setText("AYOKO");
        textView_5.setText("NA");
        textView_6.setText("MAG CONTUSION");

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

        textView_1.setText("I");
        textView_2.setText("HATE");
        textView_3.setText("THESIS");
        textView_4.setText("AYOKO");
        textView_5.setText("NA");
        textView_6.setText("MAG FRACTURE");

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

        textView_1.setText("I");
        textView_2.setText("HATE");
        textView_3.setText("THESIS");
        textView_4.setText("AYOKO");
        textView_5.setText("NA");
        textView_6.setText("MAG MAJOR LACERATION");

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

        textView_1.setText("I");
        textView_2.setText("HATE");
        textView_3.setText("THESIS");
        textView_4.setText("AYOKO");
        textView_5.setText("NA");
        textView_6.setText("MAG MINOR LACERATION");

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

        textView_1.setText("I");
        textView_2.setText("HATE");
        textView_3.setText("THESIS");
        textView_4.setText("AYOKO");
        textView_5.setText("NA");
        textView_6.setText("MAG SEVERE PUNCTURE");

    }

    public void punctureSlightPractice (){
        //Set the materials Image here.
        setChecksAsInvisible();
        imageView_1.setImageResource(R.drawable.ic_laceration);
        imageView_2.setImageResource(R.drawable.ic_concussion);
        imageView_3.setImageResource(R.drawable.ic_bites);
        imageView_4.setImageResource(R.drawable.ic_laceration);
        imageView_5.setImageResource(R.drawable.ic_fracture);
        imageView_6.setImageResource(R.drawable.ic_insect);

        textView_1.setText("I");
        textView_2.setText("HATE");
        textView_3.setText("THESIS");
        textView_4.setText("AYOKO");
        textView_5.setText("NA");
        textView_6.setText("MAG SlIGHT PUNCTURE");

    }

}
