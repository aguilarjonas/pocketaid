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

    private String injuryType;

    ArrayList<String> answersUser = new ArrayList<String>();
    final ArrayList<String> answerAbrasion = new ArrayList<String>(){{
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

        //Bundle mo na lang dito, Angel.
        //Add if else here if the user came back from the arrange fragments.
        //answersUser[0] - determines if the user came back from the arrange fragments.
        //answersUser.add("0");
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

                    for (int i = 0; i < answersUser.size(); i++){
//                        Log.e("ARRAYLIST ANSWER", answersUser.get(i));
                    }

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
            return answerAbrasion;
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

        } else if (chosenPractice.equals("Insect Bites")){
            injuryType = "Insect Bites";
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_SHORT).show();

        } else if (chosenPractice.equals("Thermal Burns")){
            injuryType = "Thermal Burns";
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_SHORT).show();

        } else if (chosenPractice.equals("Chemical Burns")){
            injuryType = "Chemical Burns";
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_SHORT).show();

        } else if (chosenPractice.equals("Electrical Burns")){
            injuryType = "Electrical Burns";
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_SHORT).show();

        } else if (chosenPractice.equals("3rd Degree Burns")){
            injuryType = "3rd Degree Burns";
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_SHORT).show();

        } else if (chosenPractice.equals("Concussion")){
            injuryType = "Concussion";
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_SHORT).show();

        } else if (chosenPractice.equals("Contusion")){
            injuryType = "Contusion";
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_SHORT).show();

        } else if (chosenPractice.equals("Fracture")){
            injuryType = "Fracture";
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_SHORT).show();

        } else if (chosenPractice.equals("Major Laceration")){
            injuryType = "Major Laceration";
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_SHORT).show();

        } else if (chosenPractice.equals("Minor Laceration")){
            injuryType = "Minor Laceration";
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_SHORT).show();

        } else if (chosenPractice.equals("Puncture (Severe Bleeding)")){
            injuryType = "Puncture (Severe Bleeding)";
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_SHORT).show();

        } else if (chosenPractice.equals("Puncture (Slightly Bleeding)")){
            injuryType = "Puncture (Slightly Bleeding)";
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_SHORT).show();
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

        /*
            Set the materials image here.
         */
        imageView_1.setImageResource(R.drawable.ic_laceration);
        imageView_2.setImageResource(R.drawable.ic_concussion);
        imageView_3.setImageResource(R.drawable.ic_bites);
        imageView_4.setImageResource(R.drawable.ic_laceration);
        imageView_5.setImageResource(R.drawable.ic_fracture);
        imageView_6.setImageResource(R.drawable.ic_insect);



    }
}
