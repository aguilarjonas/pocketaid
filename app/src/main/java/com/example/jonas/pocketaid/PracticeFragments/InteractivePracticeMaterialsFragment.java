package com.example.jonas.pocketaid.PracticeFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jonas.pocketaid.Adapters.DialogPractice;
import com.example.jonas.pocketaid.InteractiveModules.InteractiveAnswerSheet;
import com.example.jonas.pocketaid.InteractiveModules.InteractiveModel;
import com.example.jonas.pocketaid.MainActivity;
import com.example.jonas.pocketaid.R;

import java.util.ArrayList;
import java.util.Collections;

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

    private ViewGroup rootView;
    private TextView instruction;
    private String injuryType;

    ArrayList<Integer> answersUser = new ArrayList<Integer>();
    InteractiveAnswerSheet interactiveSheet = new InteractiveAnswerSheet();

    int numberOfTries = 0;
    int numberOfError = 0;
    int numberOfCorrect = 0;

    ArrayList<Integer> answerRandomized = new ArrayList<Integer>();
    ArrayList<Integer> correctMaterials = new ArrayList<Integer>();

    int materialNumber = 0;

    InteractiveModel interModel;

    int getMaterialNumber = 0;
    int counter = 0 ;
    ArrayList<Integer> materialStorage = new ArrayList<Integer>();;
    ArrayList<Integer> usedImageStorage = new ArrayList<Integer>();;
    ArrayList<Integer> imagesWithMaterials = new ArrayList<Integer>();;





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
        interModel = InteractiveModel.getInstance();
        resetValues();

        setImages(rootView);
//        setImageOnClicks();
        chosenPracticeChooser(CHOSEN_PRACTICE);


        nextButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkAnswers(answerRandomized, materialNumber, answersUser) == false){
//                    Toast.makeText(getActivity().getApplicationContext(), "Wrong Answer! Try Again", Toast.LENGTH_SHORT).show();
                    showDialog(getString(R.string.wrong));
                    setChecksAsInvisible();
                    answersUser.clear();
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

    public void resetValues(){

        getMaterialNumber = 0;
        counter = 0;
        answerRandomized = interactiveSheet.getRANDOMIZED_ME();
        usedImageStorage.clear();
        imagesWithMaterials.clear();

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
        interModel.assignFirstStageStats(numberOfCorrect, numberOfError, numberOfTries);


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

        imageView_1.setOnClickListener(this);
        imageView_2.setOnClickListener(this);
        imageView_3.setOnClickListener(this);
        imageView_4.setOnClickListener(this);
        imageView_5.setOnClickListener(this);
        imageView_6.setOnClickListener(this);

        instruction = (TextView) rootView.findViewById(R.id.material_instruction);
        nextButton =  (Button) rootView.findViewById(R.id.button_practice_next);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.imageView_pic1:
                if (imageView_1_check.getVisibility() == View.VISIBLE){
                    imageView_1_check.setVisibility(View.INVISIBLE);
                    answersUser.remove(Integer.valueOf(1));
                }

                else if (imageView_1_check.getVisibility() == View.INVISIBLE){
                    imageView_1_check.setVisibility(View.VISIBLE);
                    answersUser.add(1);
                }
                break;

            case R.id.imageView_pic2:
                if (imageView_2_check.getVisibility() == View.VISIBLE){
                    imageView_2_check.setVisibility(View.INVISIBLE);
                    answersUser.remove(Integer.valueOf(2));

                }

                else if (imageView_2_check.getVisibility() == View.INVISIBLE){
                    imageView_2_check.setVisibility(View.VISIBLE);
                    answersUser.add(2);
                }
                break;

            case R.id.imageView_pic3:
                if (imageView_3_check.getVisibility() == View.VISIBLE){
                    imageView_3_check.setVisibility(View.INVISIBLE);
                    answersUser.remove(Integer.valueOf(3));
                }

                else if (imageView_3_check.getVisibility() == View.INVISIBLE){
                    imageView_3_check.setVisibility(View.VISIBLE);
                    answersUser.add(3);
                }
                break;

            case R.id.imageView_pic4:
                if (imageView_4_check.getVisibility() == View.VISIBLE){
                    imageView_4_check.setVisibility(View.INVISIBLE);
                    answersUser.remove(Integer.valueOf(4));
                }

                else if (imageView_4_check.getVisibility() == View.INVISIBLE){
                    imageView_4_check.setVisibility(View.VISIBLE);
                    answersUser.add(4);
                }
                break;

            case R.id.imageView_pic5:
                if (imageView_5_check.getVisibility() == View.VISIBLE){
                    imageView_5_check.setVisibility(View.INVISIBLE);
                    answersUser.remove(Integer.valueOf(5));
                }

                else if (imageView_5_check.getVisibility() == View.INVISIBLE){
                    imageView_5_check.setVisibility(View.VISIBLE);
                    answersUser.add(5);
                }
                break;

            case R.id.imageView_pic6:
                if (imageView_6_check.getVisibility() == View.VISIBLE) {
                    imageView_6_check.setVisibility(View.INVISIBLE);
                    answersUser.remove(Integer.valueOf(6));
                } else if (imageView_6_check.getVisibility() == View.INVISIBLE) {
                    imageView_6_check.setVisibility(View.VISIBLE);
                    answersUser.add(6);
                }
                break;
        }
    }

    public boolean checkAnswers(ArrayList<Integer> answerSheet, int materialNumber,
                    ArrayList<Integer> answersUser){

        boolean isAllCorrect = false;
        int numberOfCorrect = 0;
        int numberOfIncorrect = 0;
        int numberOfLoops = 0;


        if (answersUser.size() != materialNumber){
            isAllCorrect = false;
        }

        else {
            for (int i = 0; i < materialNumber; i++) {
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


            if (numberOfCorrect == materialNumber ){
                isAllCorrect = true;
            } else
                isAllCorrect = false;
        }
        return isAllCorrect;
    }

    public void chosenPracticeChooser(String chosenPractice){

        if (chosenPractice.equals("Abrasion") || chosenPractice.equals("Gasgas")){
            if(chosenPractice.equals("Abrasion")){
                injuryType = "Abrasion";
            } else if (chosenPractice.equals("Gasgas")){
                injuryType = "Gasgas";
            }
            abrasionPractice(chosenPractice);

        } else if (chosenPractice.equals("Animal Bites") || chosenPractice.equals("Kagat ng hayop")){
            if(chosenPractice.equals("Animal Bites")){
                injuryType = "Animal Bites";
            } else if (chosenPractice.equals("Kagat ng hayop")){
                injuryType = "Kagat ng hayop";
            }
            animalBitesPractice(chosenPractice);

        } else if (chosenPractice.equals("Insect Bites") || chosenPractice.equals("Kagat ng insekto")){
            if(chosenPractice.equals("Insect Bites")){
                injuryType = "Insect Bites";
            } else if (chosenPractice.equals("Kagat ng insekto")){
                injuryType = "Kagat ng insekto";
            }
            insectBitesPractice(chosenPractice);

        } else if (chosenPractice.equals("Thermal Burns") || chosenPractice.equals("Paso")){
            if(chosenPractice.equals("Thermal Burns")){
                injuryType = "Thermal Burns";
            } else if (chosenPractice.equals("Paso")){
                injuryType = "Paso";
            }
            thermalPractice(chosenPractice);

        } else if (chosenPractice.equals("Chemical Burns") || chosenPractice.equals("Paso dulot ng kemikal")){
            if(chosenPractice.equals("Chemical Burns")){
                injuryType = "Chemical Burns";
            } else if (chosenPractice.equals("Paso dulot ng kemikal")){
                injuryType = "Paso dulot ng kemikal";
            }
            chemicalPractice(chosenPractice);

        } else if (chosenPractice.equals("3rd Degree Burns") || chosenPractice.equals("Ikatlong digri na paso")){
            if(chosenPractice.equals("3rd Degree Burns")){
                injuryType = "3rd Degree Burns";
            } else if (chosenPractice.equals("Ikatlong digri na paso")){
                injuryType = "Ikatlong digri na paso";
            }
            thermalPractice2(chosenPractice);

        } else if (chosenPractice.equals("Concussion") || chosenPractice.equals("Umpog")){
            if(chosenPractice.equals("Concussion")){
                injuryType = "Concussion";
            } else if (chosenPractice.equals("Umpog")){
                injuryType = "Umpog";
            }
            concussionPractice(chosenPractice);

        } else if (chosenPractice.equals("Contusion") || chosenPractice.equals("Pasa")){
            if(chosenPractice.equals("Contusion")){
                injuryType = "Contusion";
            } else if (chosenPractice.equals("Pasa")){
                injuryType = "Pasa";
            }
            contussionPractice(chosenPractice);

        } else if (chosenPractice.equals("Fracture") || chosenPractice.equals("Bali")){
            if(chosenPractice.equals("Fracture")){
                injuryType = "Fracture";
            } else if (chosenPractice.equals("Bali")){
                injuryType = "Bali";
            }
            fracturePractice(chosenPractice);

        } else if (chosenPractice.equals("Major Laceration") || chosenPractice.equals("Malubhang laslas")){
            if(chosenPractice.equals("Major Laceration")){
                injuryType = "Major Laceration";
            } else if (chosenPractice.equals("Malubhang laslas")){
                injuryType = "Malubhang laslas";
            }
            majorLacerationPractice(chosenPractice);

        } else if (chosenPractice.equals("Minor Laceration") || chosenPractice.equals("Hindi malubhang laslas")){
            if(chosenPractice.equals("Minor Laceration")){
                injuryType = "Minor Laceration";
            } else if (chosenPractice.equals("Hindi malubhang laslas")){
                injuryType = "Hindi malubhang laslas";
            }
            minorLacerationPractice(chosenPractice);

        } else if (chosenPractice.equals("Puncture (Severe Bleeding)") || chosenPractice.equals("Tusok(Malubhang pagdurugo)")){
            if(chosenPractice.equals("Puncture (Severe Bleeding)")){
                injuryType = "Puncture (Severe Bleeding)";
            } else if (chosenPractice.equals("Tusok(Malubhang pagdurugo)")){
                injuryType = "Tusok(Malubhang pagdurugo)";
            }
            punctureSeverePractice(chosenPractice);

        } else if (chosenPractice.equals("Puncture (Slightly Bleeding)") || chosenPractice.equals("Tusok(Hindi malubhang pagdurugo)")){
            if(chosenPractice.equals("Puncture (Slightly Bleeding)")){
                injuryType = "Puncture (Slightly Bleeding)";
            } else if (chosenPractice.equals("Tusok(Hindi malubhang pagdurugo)")){
                injuryType = "Tusok(Hindi malubhang pagdurugo)";
            }
            punctureSlightPractice(chosenPractice);
        }

        instruction.append(injuryType + ".");
    }

    public void setChecksAsInvisible(){
        imageView_1_check.setVisibility(View.INVISIBLE);
        imageView_2_check.setVisibility(View.INVISIBLE);
        imageView_3_check.setVisibility(View.INVISIBLE);
        imageView_4_check.setVisibility(View.INVISIBLE);
        imageView_5_check.setVisibility(View.INVISIBLE);
        imageView_6_check.setVisibility(View.INVISIBLE);
    }

    public void setImagesRandomly(int materialNumber){
         getMaterialNumber = 0;
         counter = 0 ;

        interModel.setCorrectMaterials(correctMaterials);

        for(int i = 0; i < materialNumber; i++){
            for(int j = 0; j <= 6; j++){

                if (answerRandomized.get(i) == j){
                    Log.e("HELLOO", "YOOHOOO EQUALS ME");
                    if (getMaterialNumber == 3 ){
                        Log.e("AWwww", "Break na kami");
                        break;
                    }

                    else {
                        if (j == 1){
                            imageView_1.setImageResource(correctMaterials.get(getMaterialNumber));
                            Log.e("HELLOO", "DAPAT NAG PALIT SI 1");
                        }

                        else if (j == 2){
                            imageView_2.setImageResource(correctMaterials.get(getMaterialNumber));
                            Log.e("HELLOO", "DAPAT NAG PALIT SI 2");

                        }

                        else if (j == 3){
                            imageView_3.setImageResource(correctMaterials.get(getMaterialNumber));
                            Log.e("HELLOO", "DAPAT NAG PALIT SI 3");

                        }

                        else if (j == 4){
                            imageView_4.setImageResource(correctMaterials.get(getMaterialNumber));
                            Log.e("HELLOO", "DAPAT NAG PALIT SI 4");

                        }

                        else if (j == 5){
                            imageView_5.setImageResource(correctMaterials.get(getMaterialNumber));
                            Log.e("HELLOO", "DAPAT NAG PALIT SI 5");

                        }

                        else if (j == 6){
                            imageView_6.setImageResource(correctMaterials.get(getMaterialNumber));
                            Log.e("HELLOO", "DAPAT NAG PALIT SI 6");

                        }
                        getMaterialNumber++;
                    }
                }

            }
        }

        materialStorage = interactiveSheet.getMATERIAL_STORAGE();
        usedImageStorage = correctMaterials;


        if (materialNumber == 4){
            imagesWithMaterials.add(answerRandomized.get(0));
            imagesWithMaterials.add(answerRandomized.get(1));
            imagesWithMaterials.add(answerRandomized.get(2));
            imagesWithMaterials.add(answerRandomized.get(3));
        }

        else if (materialNumber == 3){
            imagesWithMaterials.add(answerRandomized.get(0));
            imagesWithMaterials.add(answerRandomized.get(1));
            imagesWithMaterials.add(answerRandomized.get(2));
        }

        else if (materialNumber == 2){
            imagesWithMaterials.add(answerRandomized.get(0));
            imagesWithMaterials.add(answerRandomized.get(1));
//            imagesWithMaterials.add(answerRandomized.get(2));
        }

        else if (materialNumber == 1){
            imagesWithMaterials.add(answerRandomized.get(0));
        }


        Collections.shuffle(materialStorage);
        do {

//            if (counter == 11){
//                counter--;
//            }

            if (usedImageStorage.contains(materialStorage.get(counter))) {
                counter++;
            }

            else if (!imagesWithMaterials.contains(1)) {
                imageView_1.setImageResource(materialStorage.get(counter));
                usedImageStorage.add(materialStorage.get(counter));
                imagesWithMaterials.add(1);
            }

            else if (!imagesWithMaterials.contains(2)) {
                imageView_2.setImageResource(materialStorage.get(counter));
                usedImageStorage.add(materialStorage.get(counter));
                imagesWithMaterials.add(2);
            }

            else if (!imagesWithMaterials.contains(3)) {
                imageView_3.setImageResource(materialStorage.get(counter));
                usedImageStorage.add(materialStorage.get(counter));
                imagesWithMaterials.add(3);
            }

            else if (!imagesWithMaterials.contains(4)) {
                imageView_4.setImageResource(materialStorage.get(counter));
                usedImageStorage.add(materialStorage.get(counter));
                imagesWithMaterials.add(4);
            }

            else if (!imagesWithMaterials.contains(5)) {
                imageView_5.setImageResource(materialStorage.get(counter));
                usedImageStorage.add(materialStorage.get(counter));
                imagesWithMaterials.add(5);
            }

            else if (!imagesWithMaterials.contains(6)) {
                imageView_6.setImageResource(materialStorage.get(counter));
                usedImageStorage.add(materialStorage.get(counter));
                imagesWithMaterials.add(6);
            }

        } while(!imagesWithMaterials.containsAll(answerRandomized));

    }


    public void abrasionPractice (String injuryType){
        //Set the materials Image here.
        //Soap & Water, Betadine, Gauze/Bandaid
        setChecksAsInvisible();
        correctMaterials.add(R.drawable.ic_material_soapwater);
        correctMaterials.add(R.drawable.ic_material_betadine);
        correctMaterials.add(R.drawable.ic_material_gauze); //Change to gauze
        //Collections.shuffle(answerRandomized);
        materialNumber = 3;
        setImagesRandomly(materialNumber);
//
    }

    public void animalBitesPractice (String injuryType){
        //Set the materials Image here.
        //Clean Gauze
        setChecksAsInvisible();
        correctMaterials.add(R.drawable.ic_material_gauze);
        materialNumber = 1;
        setImagesRandomly(materialNumber);

//
    }

    public void insectBitesPractice (String injuryType){
        //Set the materials Image here.
        //Tweezers, Soap and Water, Gauze, Cold pack
        setChecksAsInvisible();
        correctMaterials.add(R.drawable.ic_material_tweezer);
        correctMaterials.add(R.drawable.ic_material_soapwater);
        correctMaterials.add(R.drawable.ic_material_gauze);
//        correctMaterials.add(R.drawable.ic_material_coldpack);
        materialNumber = 3;
        setImagesRandomly(materialNumber);
//
    }

    public void thermalPractice (String injuryType){
        //Set the materials Image here.
        //Water, Gauze, Soap and Water
        setChecksAsInvisible();
        correctMaterials.add(R.drawable.ic_material_water);
        correctMaterials.add(R.drawable.ic_material_gauze);
        correctMaterials.add(R.drawable.ic_material_soapwater);
        //correctMaterials.add(R.drawable.ic_material_coldpack);
        materialNumber = 3;
        setImagesRandomly(materialNumber);
//
    }

    public void thermalPractice2 (String injuryType){
        //Set the materials Image here.
        //None
        setChecksAsInvisible();
        correctMaterials.add(R.drawable.ic_material_soapwater);
        correctMaterials.add(R.drawable.ic_material_gauze);
        correctMaterials.add(R.drawable.ic_material_coldpack);
        //correctMaterials.add(R.drawable.ic_material_coldpack);
        materialNumber = 3;
        setImagesRandomly(materialNumber);
//
    }



    public void chemicalPractice (String injuryType){
        //Set the materials Image here.
        //Soap and water, Gauze
        setChecksAsInvisible();
        correctMaterials.add(R.drawable.ic_material_soapwater);
        correctMaterials.add(R.drawable.ic_material_gauze);
        //correctMaterials.add(R.drawable.ic_material_coldpack);
        materialNumber = 2;
        setImagesRandomly(materialNumber);
//
    }

    public void concussionPractice (String injuryType){
        //Set the materials Image here.
        //Cold Pack, Ice
        setChecksAsInvisible();
        correctMaterials.add(R.drawable.ic_material_ice);
        correctMaterials.add(R.drawable.ic_material_coldpack);
//        correctMaterials.add(R.drawable.ic_material_gauze);
        //correctMaterials.add(R.drawable.ic_material_coldpack);
        materialNumber = 2;
        setImagesRandomly(materialNumber);
//
    }

    public void contussionPractice (String injuryType){
        //Set the materials Image here.
        //Cold Pack, Ice, PainReliever
        setChecksAsInvisible();
        correctMaterials.add(R.drawable.ic_material_coldpack);
        correctMaterials.add(R.drawable.ic_material_ice);
        //correctMaterials.add(R.drawable.ic_material_coldpack);
        materialNumber = 2;
        setImagesRandomly(materialNumber);
//
    }

    public void fracturePractice (String injuryType){
        //Set the materials Image here.
        //Gauze, Ring Pad, Sling, Bandage
        setChecksAsInvisible();
        correctMaterials.add(R.drawable.ic_material_ringpad);
        correctMaterials.add(R.drawable.ic_material_bandage);
        correctMaterials.add(R.drawable.ic_material_sling);
        materialNumber = 3;
        setImagesRandomly(materialNumber);
//
    }

    public void majorLacerationPractice (String injuryType){
        //Set the materials Image here.
        //Gloves, Gauze, Soap and Water, Antibiotic

        setChecksAsInvisible();
        correctMaterials.add(R.drawable.ic_material_glove);
        correctMaterials.add(R.drawable.ic_material_soapwater);
        correctMaterials.add(R.drawable.ic_material_gauze);
        materialNumber = 3;
        setImagesRandomly(materialNumber);
//
    }

    public void minorLacerationPractice (String injuryType){
        //Set the materials Image here.
        //Gloves, Gauze, Soap and Water, Antibiotic
        setChecksAsInvisible();
        correctMaterials.add(R.drawable.ic_material_glove);
        correctMaterials.add(R.drawable.ic_material_soapwater);
        correctMaterials.add(R.drawable.ic_material_gauze);
        materialNumber = 3;
        setImagesRandomly(materialNumber);
//
    }

    public void punctureSeverePractice (String injuryType){
        //Set the materials Image here.
        //Gloves, Soap and Water, Gauze
        setChecksAsInvisible();
        correctMaterials.add(R.drawable.ic_material_glove);
        correctMaterials.add(R.drawable.ic_material_soapwater);
        correctMaterials.add(R.drawable.ic_material_gauze);
        //correctMaterials.add(R.drawable.ic_material_coldpack);
        materialNumber = 3;
        setImagesRandomly(materialNumber);
//
    }

    public void punctureSlightPractice (String injuryType) {
        //Set the materials Image here.
        //Gloves, Soap and Water, Gauze
        setChecksAsInvisible();
        correctMaterials.add(R.drawable.ic_material_glove);
        correctMaterials.add(R.drawable.ic_material_soapwater);
        correctMaterials.add(R.drawable.ic_material_gauze);
        //correctMaterials.add(R.drawable.ic_material_coldpack);
        materialNumber = 3;
        setImagesRandomly(materialNumber);
//
    }



}
