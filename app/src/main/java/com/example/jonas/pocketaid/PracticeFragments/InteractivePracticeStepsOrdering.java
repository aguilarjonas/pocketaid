

package com.example.jonas.pocketaid.PracticeFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.Pair;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jonas.pocketaid.Adapters.DialogPractice;
import com.example.jonas.pocketaid.Adapters.PracticeItemAdapter;
import com.example.jonas.pocketaid.Adapters.StepNumberListAdapter;
import com.example.jonas.pocketaid.InteractiveModules.InteractiveModel;
import com.example.jonas.pocketaid.MainActivity;
import com.example.jonas.pocketaid.R;
import com.woxthebox.draglistview.DragItem;
import com.woxthebox.draglistview.DragListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class InteractivePracticeStepsOrdering extends Fragment {

    private TextView instruction;
    private Button backButton;
    private ArrayList<Pair<Long, String>> mItemArray;
    private DragListView mDragListView;
    String[] abrasionProcedure, animalBitesProcedure, insectBitesProcedure, thermalBurnProcedure, thirdDegreeBurnProcedure, concussionProcedure, contusionProcedure, fractureProcedure,
             majorLacerationProcedure, minorLacerationProcedure, slightPunctureProcedure, severePunctureProcedure, chemicalBurnProcedure, electricalBurnProcedure;
    RecyclerView lvStepNumber;
    Button checkAnswerBT;

    int numberOfMaterials = 0;
    String chosenInjury = "";

    int numberOfErrors = 0;
    int numberOfTries = 0;
    int numberOfCorrect = 0;

    InteractiveModel interModel = InteractiveModel.getInstance();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.list_layout, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((MainActivity) getActivity()).resetActionBar(true, DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        InteractiveModel interModel = InteractiveModel.getInstance();

        numberOfCorrect = interModel.getStage2Stats().get(0);
        numberOfErrors = interModel.getStage2Stats().get(1);
        numberOfTries = interModel.getStage2Stats().get(2);

        mDragListView = (DragListView) rootView.findViewById(R.id.drag_list_view);
        instruction = (TextView) rootView.findViewById(R.id.arrangement_instruction);
        mDragListView.getRecyclerView().setVerticalScrollBarEnabled(true);
        abrasionProcedure = getResources().getStringArray(R.array.abrasion_recommended);
        animalBitesProcedure = getResources().getStringArray(R.array.animal_bites_practice);
        insectBitesProcedure = getResources().getStringArray(R.array.insect_bites_practice);
        thermalBurnProcedure = getResources().getStringArray(R.array.burns_thermal_recommended);
        chemicalBurnProcedure = getResources().getStringArray(R.array.chemical_burns_practice);
        electricalBurnProcedure = getResources().getStringArray(R.array.burns_electrical_recommended);
        thirdDegreeBurnProcedure = getResources().getStringArray(R.array.third_deg_burns_practice);
        concussionProcedure = getResources().getStringArray(R.array.concussion_practice);
        contusionProcedure = getResources().getStringArray(R.array.contusion_practice);
        fractureProcedure = getResources().getStringArray(R.array.fracture_practice);
        majorLacerationProcedure = getResources().getStringArray(R.array.laceration_major_practice);
        minorLacerationProcedure = getResources().getStringArray(R.array.laceration_minor_practice);
        slightPunctureProcedure = getResources().getStringArray(R.array.puncture_slight_recommended);
        severePunctureProcedure = getResources().getStringArray(R.array.puncture_severe_recommended);
        lvStepNumber = (RecyclerView) rootView.findViewById(R.id.lv_step_numberPrac);
        checkAnswerBT = (Button) rootView.findViewById(R.id.btCheckAnswersPrac);

        chosenInjury = getArguments().getString("chosenInjury");
        appendInjuryToInstruction(chosenInjury);
        selectedInjury(chosenInjury);
        numberOfMaterials = interModel.getNumberOfMaterials();

        return rootView;
    }

    public void appendInjuryToInstruction(String injury) {
        instruction.append(injury + ".");
    }

    public void selectedInjury(String chosenInjury){
        String selectedInjury = chosenInjury;
        if (chosenInjury.equals("Abrasion") || chosenInjury.equalsIgnoreCase("Gasgas")){
            checkOrderOfProcedures();
            ArrayList<Integer> numberingProcedure = new ArrayList<>();
            for (int counter = 0; counter < abrasionProcedure.length; ++counter) {
                int stepNumberHolder = counter + 1;
                numberingProcedure.addAll(Arrays.asList(stepNumberHolder));
                setStepNumberAdapter(abrasionProcedure);
                mItemArray.add(new Pair<>(Long.valueOf(counter), "" + abrasionProcedure[counter]));
            }
            shuffleArray();
            getOnClickListener(selectedInjury);

        } else if (chosenInjury.equals("Animal Bites") || chosenInjury.equalsIgnoreCase("Kagat ng hayop")){
            checkOrderOfProcedures();
            ArrayList<Integer> numberingProcedure = new ArrayList<>();
            for (int counter = 0; counter < animalBitesProcedure.length; ++counter) {
                int stepNumberHolder = counter + 1;
                numberingProcedure.addAll(Arrays.asList(stepNumberHolder));
                setStepNumberAdapter(animalBitesProcedure);
                mItemArray.add(new Pair<>(Long.valueOf(counter), "" + animalBitesProcedure[counter]));
            }
            shuffleArray();
            getOnClickListener(selectedInjury);

        } else if (chosenInjury.equals("Insect Bites") || chosenInjury.equalsIgnoreCase("Kagat ng insekto")){
            checkOrderOfProcedures();
            ArrayList<Integer> numberingProcedure = new ArrayList<>();
            for (int counter = 0; counter < insectBitesProcedure.length; ++counter) {
                int stepNumberHolder = counter + 1;
                numberingProcedure.addAll(Arrays.asList(stepNumberHolder));
                setStepNumberAdapter(insectBitesProcedure);
                mItemArray.add(new Pair<>(Long.valueOf(counter), "" + insectBitesProcedure[counter]));
            }
            shuffleArray();
            getOnClickListener(selectedInjury);

        } else if (chosenInjury.equals("Thermal Burns") || chosenInjury.equalsIgnoreCase("Paso")){
            checkOrderOfProcedures();
            ArrayList<Integer> numberingProcedure = new ArrayList<>();
            for (int counter = 0; counter < thermalBurnProcedure.length; ++counter) {
                int stepNumberHolder = counter + 1;
                numberingProcedure.addAll(Arrays.asList(stepNumberHolder));
                setStepNumberAdapter(thermalBurnProcedure);
                mItemArray.add(new Pair<>(Long.valueOf(counter), "" + thermalBurnProcedure[counter]));
            }
            shuffleArray();
            getOnClickListener(selectedInjury);

        } else if (chosenInjury.equals("Chemical Burns") || chosenInjury.equalsIgnoreCase("Paso dulot ng kemikal")){
            checkOrderOfProcedures();
            ArrayList<Integer> numberingProcedure = new ArrayList<>();
            for (int counter = 0; counter < chemicalBurnProcedure.length; ++counter) {
                int stepNumberHolder = counter + 1;
                numberingProcedure.addAll(Arrays.asList(stepNumberHolder));
                setStepNumberAdapter(chemicalBurnProcedure);
                mItemArray.add(new Pair<>(Long.valueOf(counter), "" + chemicalBurnProcedure[counter]));
            }
            shuffleArray();
            getOnClickListener(selectedInjury);

        } else if (chosenInjury.equals("3rd Degree Burns") || chosenInjury.equalsIgnoreCase("Ikatlong digri ng paso")){
            checkOrderOfProcedures();
            ArrayList<Integer> numberingProcedure = new ArrayList<>();
            for (int counter = 0; counter < thirdDegreeBurnProcedure.length; ++counter) {
                int stepNumberHolder = counter + 1;
                numberingProcedure.addAll(Arrays.asList(stepNumberHolder));
                setStepNumberAdapter(thirdDegreeBurnProcedure);
                mItemArray.add(new Pair<>(Long.valueOf(counter), "" + thirdDegreeBurnProcedure[counter]));
            }
            shuffleArray();
            getOnClickListener(selectedInjury);

        } else if (chosenInjury.equals("Concussion") || chosenInjury.equalsIgnoreCase("Untog")){
            checkOrderOfProcedures();
            ArrayList<Integer> numberingProcedure = new ArrayList<>();
            for (int counter = 0; counter < concussionProcedure.length; ++counter) {
                int stepNumberHolder = counter + 1;
                numberingProcedure.addAll(Arrays.asList(stepNumberHolder));
                setStepNumberAdapter(concussionProcedure);
                mItemArray.add(new Pair<>(Long.valueOf(counter), "" + concussionProcedure[counter]));
            }
            shuffleArray();
            getOnClickListener(selectedInjury);

        } else if (chosenInjury.equals("Contusion") || chosenInjury.equalsIgnoreCase("Pasa")){
            checkOrderOfProcedures();
            ArrayList<Integer> numberingProcedure = new ArrayList<>();
            for (int counter = 0; counter < contusionProcedure.length; ++counter) {
                int stepNumberHolder = counter + 1;
                numberingProcedure.addAll(Arrays.asList(stepNumberHolder));
                setStepNumberAdapter(contusionProcedure);
                mItemArray.add(new Pair<>(Long.valueOf(counter), "" + contusionProcedure[counter]));
            }
            shuffleArray();
            getOnClickListener(selectedInjury);

        } else if (chosenInjury.equals("Fracture") || chosenInjury.equalsIgnoreCase("Baling Buto")){
            checkOrderOfProcedures();
            ArrayList<Integer> numberingProcedure = new ArrayList<>();
            for (int counter = 0; counter < fractureProcedure.length; ++counter) {
                int stepNumberHolder = counter + 1;
                numberingProcedure.addAll(Arrays.asList(stepNumberHolder));
                setStepNumberAdapter(fractureProcedure);
                mItemArray.add(new Pair<>(Long.valueOf(counter), "" + fractureProcedure[counter]));
            }
            shuffleArray();
            getOnClickListener(selectedInjury);

        } else if (chosenInjury.equals("Laceration(Major)") || chosenInjury.equalsIgnoreCase("Malubhang laslas")){
            checkOrderOfProcedures();
            ArrayList<Integer> numberingProcedure = new ArrayList<>();
            for (int counter = 0; counter < majorLacerationProcedure.length; ++counter) {
                int stepNumberHolder = counter + 1;
                numberingProcedure.addAll(Arrays.asList(stepNumberHolder));
                setStepNumberAdapter(majorLacerationProcedure);
                mItemArray.add(new Pair<>(Long.valueOf(counter), "" + majorLacerationProcedure[counter]));
            }
            shuffleArray();
            getOnClickListener(selectedInjury);

        } else if (chosenInjury.equals("Laceration(Minor)") || chosenInjury.equalsIgnoreCase("Hindi malubhang laslas")){
            checkOrderOfProcedures();
            ArrayList<Integer> numberingProcedure = new ArrayList<>();
            for (int counter = 0; counter < minorLacerationProcedure.length; ++counter) {
                int stepNumberHolder = counter + 1;
                numberingProcedure.addAll(Arrays.asList(stepNumberHolder));
                setStepNumberAdapter(minorLacerationProcedure);
                mItemArray.add(new Pair<>(Long.valueOf(counter), "" + minorLacerationProcedure[counter]));
            }
            shuffleArray();
            getOnClickListener(selectedInjury);

        } else if (chosenInjury.equals("Puncture(Severe Bleeding)") || chosenInjury.equalsIgnoreCase("Tusok(Malubhang pagdurugo)")){
            checkOrderOfProcedures();
            ArrayList<Integer> numberingProcedure = new ArrayList<>();
            for (int counter = 0; counter < severePunctureProcedure.length; ++counter) {
                int stepNumberHolder = counter + 1;
                numberingProcedure.addAll(Arrays.asList(stepNumberHolder));
                setStepNumberAdapter(severePunctureProcedure);
                mItemArray.add(new Pair<>(Long.valueOf(counter), "" + severePunctureProcedure[counter]));
            }
            shuffleArray();
            getOnClickListener(selectedInjury);

        } else if (chosenInjury.equals("Puncture(Slight Bleeding)") || chosenInjury.equalsIgnoreCase("Tusok(Hindi malubhang pagdurugo)")){
            checkOrderOfProcedures();
            ArrayList<Integer> numberingProcedure = new ArrayList<>();
            for (int counter = 0; counter < slightPunctureProcedure.length; ++counter) {
                int stepNumberHolder = counter + 1;
                numberingProcedure.addAll(Arrays.asList(stepNumberHolder));
                setStepNumberAdapter(slightPunctureProcedure);
                mItemArray.add(new Pair<>(Long.valueOf(counter), "" + slightPunctureProcedure[counter]));
            }
            shuffleArray();
            getOnClickListener(selectedInjury);
        }
    }

    public void setStepNumberAdapter(String[] injury) {
        StepNumberListAdapter adapter = new StepNumberListAdapter(getActivity().getApplicationContext(), injury);
        lvStepNumber.setAdapter(adapter);
        lvStepNumber.setLayoutManager(new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
    }

    public void getOnClickListener(final String chosenInjury){
        checkAnswerBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberOfTries++;
                if(chosenInjury.equals("Abrasion") || chosenInjury.equalsIgnoreCase("Gasgas")){
                    if((mItemArray.get(0).second.startsWith("Wash") || mItemArray.get(0).second.startsWith("Hugasan")) &&
                            (mItemArray.get(1).second.startsWith("Apply") || mItemArray.get(1).second.startsWith("Lagyan")) &&
                            (mItemArray.get(2).second.startsWith("Cover") || mItemArray.get(2).second.startsWith("Takpan")) &&
                            (mItemArray.get(3).second.startsWith("Repeat") || mItemArray.get(3).second.startsWith("Ulitin"))){
                        showDialog(getString(R.string.correct));
                        numberOfMaterials = 3;
                        interModel.setNumberOfMaterials(numberOfMaterials);
                        numberOfCorrect++;
                        goToInteractiveApplication();
                    } else{
                        numberOfMaterials = 3;
                        interModel.setNumberOfMaterials(numberOfMaterials);
                        numberOfErrors++;
                        goToInteractiveApplication();

                        showDialog(getString(R.string.wrong));
                   }
                } else if(chosenInjury.equals("Fracture") || chosenInjury.equalsIgnoreCase("Baling Buto")){
                    if((mItemArray.get(0).second.startsWith("In") || mItemArray.get(0).second.startsWith("Kung")) &&
                            (mItemArray.get(1).second.startsWith("If") || mItemArray.get(1).second.startsWith("Gumamit")) &&
                            (mItemArray.get(2).second.startsWith("Make") || mItemArray.get(2).second.startsWith("Siguraduhin")) &&
                            (mItemArray.get(3).second.startsWith("Support") || mItemArray.get(3).second.contains("Kung mayroong")) &&
                            (mItemArray.get(4).second.startsWith("Raise") || mItemArray.get(4).second.startsWith("Suportahan")) &&
                            (mItemArray.get(5).second.startsWith("Immobilize") || mItemArray.get(5).second.startsWith("Gumamit")) &&
                            (mItemArray.get(6).second.startsWith("Check") || mItemArray.get(6).second.startsWith("Regular")) &&
                            (mItemArray.get(7).second.startsWith("Call") || mItemArray.get(7).second.startsWith("Magpatingin"))){
                        showDialog(getString(R.string.correct));
                        // -1 sa mga materials para di na kasama ringpad sa IP-A
                        numberOfMaterials = (3-1);
                        interModel.setNumberOfMaterials(numberOfMaterials);
                        numberOfCorrect++;
                        goToInteractiveApplication();
                    } else{

                        numberOfMaterials = (3-1);
                        interModel.setNumberOfMaterials(numberOfMaterials);

                        numberOfErrors++;
                        goToInteractiveApplication();

                        showDialog(getString(R.string.wrong));
                   }
                } else if(chosenInjury.equals("Contusion") || chosenInjury.equalsIgnoreCase("Pasa")){
                    if((mItemArray.get(0).second.startsWith("Apply") || mItemArray.get(0).second.startsWith("Ilapat"))&&
                            (mItemArray.get(1).second.startsWith("If") || mItemArray.get(1).second.contains("Kung maaari")) &&
                            (mItemArray.get(2).second.startsWith("Rest") || mItemArray.get(2).second.startsWith("Ipahinga")) &&
                            (mItemArray.get(3).second.contains("If needed") || mItemArray.get(3).second.contains("Kung kinakailangan"))){
                        showDialog(getString(R.string.correct));
                        // -1 sa mga materials para di na kasama ice sa IP-A
                        numberOfMaterials = (2-1);
                        interModel.setNumberOfMaterials(numberOfMaterials);


                        numberOfCorrect++;
                        goToInteractiveApplication();
                    } else{
                        numberOfMaterials = (2-1);
                        interModel.setNumberOfMaterials(numberOfMaterials);
                        numberOfErrors++;
                        goToInteractiveApplication();
                        showDialog(getString(R.string.wrong));
                   }
                } else if(chosenInjury.equals("Concussion") || chosenInjury.equalsIgnoreCase("Untog")){
                    if((mItemArray.get(0).second.startsWith("Apply") || mItemArray.get(0).second.startsWith("Lapatan")) &&
                            (mItemArray.get(1).second.startsWith("Observe") || mItemArray.get(1).second.startsWith("Obserbahan")) &&
                            (mItemArray.get(2).second.startsWith("If") || mItemArray.get(2).second.startsWith("Tumawag"))){
                        showDialog(getString(R.string.correct));
                        // -1 sa mga materials para di na kasama ice sa IP-A
                        numberOfMaterials = (2-1);
                        interModel.setNumberOfMaterials(numberOfMaterials);

                        numberOfCorrect++;
                        goToInteractiveApplication();
                    } else{
                        numberOfMaterials = (2-1);
                        interModel.setNumberOfMaterials(numberOfMaterials);

                        numberOfErrors++;
                        goToInteractiveApplication();

                        showDialog(getString(R.string.wrong));
                    }
                } else if(chosenInjury.equals("3rd Degree Burns") || chosenInjury.equalsIgnoreCase("Ikatlong degree na paso")){
                    if((mItemArray.get(0).second.startsWith("Protect") || mItemArray.get(0).second.startsWith("Protektahan")) &&
                            (mItemArray.get(1).second.startsWith("Remove") || mItemArray.get(1).second.startsWith("Tanggalin")) &&
                            (mItemArray.get(2).second.startsWith("Do") || mItemArray.get(2).second.startsWith("Huwag")) &&
                            (mItemArray.get(3).second.startsWith("If") || mItemArray.get(3).second.startsWith("Ilagay")) &&
                            (mItemArray.get(4).second.startsWith("Call") || mItemArray.get(4).second.startsWith("Tumawag"))){
                        showDialog(getString(R.string.correct));
                        numberOfMaterials = 0;
                        interModel.setNumberOfMaterials(numberOfMaterials);

                        numberOfCorrect++;
                        goToInteractiveApplication();
                    } else{
                        numberOfMaterials = 0;
                        interModel.setNumberOfMaterials(numberOfMaterials);

                        numberOfErrors++;
                        showDialog(getString(R.string.wrong));
                    }
                } else if(chosenInjury.equals("Chemical Burns") || chosenInjury.equalsIgnoreCase("Paso dulot ng kemikal")){
                    if((mItemArray.get(0).second.startsWith("Remove") || mItemArray.get(0).second.contains("Kung mayroon")) &&
                            (mItemArray.get(1).second.startsWith("Place") || mItemArray.get(1).second.startsWith("Panatilihing")) &&
                            (mItemArray.get(2).second.startsWith("Wash") || mItemArray.get(2).second.startsWith("Hugasan")) &&
                            (mItemArray.get(3).second.startsWith("Cover") || mItemArray.get(3).second.startsWith("Takpan")) &&
                            (mItemArray.get(4).second.startsWith("If") || mItemArray.get(4).second.contains("Kung ang kemikal")) &&
                            (mItemArray.get(5).second.startsWith("Seek") || mItemArray.get(5).second.startsWith("Magpasuri"))){
                        showDialog(getString(R.string.correct));
                        numberOfMaterials = 2;
                        interModel.setNumberOfMaterials(numberOfMaterials);

                        numberOfCorrect++;
                        goToInteractiveApplication();
                    } else{
                        numberOfMaterials = 2;
                        interModel.setNumberOfMaterials(numberOfMaterials);

                        numberOfErrors++;
                        goToInteractiveApplication();

                        showDialog(getString(R.string.wrong));
                    }
                } else if(chosenInjury.equals("Thermal Burns") || chosenInjury.equalsIgnoreCase("Paso")){
                    if((mItemArray.get(0).second.startsWith("Hold") || mItemArray.get(0).second.startsWith("Banlawan")) &&
                            (mItemArray.get(1).second.startsWith("Cover") || mItemArray.get(1).second.startsWith("Hugasan")) &&
                            (mItemArray.get(2).second.startsWith("Keep") || mItemArray.get(2).second.startsWith("Takpan"))){
                        showDialog(getString(R.string.correct));
                        numberOfMaterials = 2;
                        interModel.setNumberOfMaterials(numberOfMaterials);


                        numberOfCorrect++;
                        goToInteractiveApplication();
                    } else{
                        goToInteractiveApplication();
                        numberOfMaterials = 2;
                        interModel.setNumberOfMaterials(numberOfMaterials);

                        numberOfErrors++;
                        showDialog(getString(R.string.wrong));
                    }
                } else if(chosenInjury.equals("Insect Bites") || chosenInjury.equalsIgnoreCase("Kagat ng insekto")){
                    if((mItemArray.get(0).second.startsWith("Check") || mItemArray.get(0).second.startsWith("Suriin"))&&
                            (mItemArray.get(1).second.startsWith("Carefully") || mItemArray.get(1).second.startsWith("Kung")) &&
                            (mItemArray.get(2).second.startsWith("Wash") || mItemArray.get(2).second.startsWith("Hugasan")) &&
                            (mItemArray.get(3).second.startsWith("Cover")|| mItemArray.get(3).second.startsWith("Takpan")) &&
                            (mItemArray.get(4).second.startsWith("Apply") || mItemArray.get(4).second.startsWith("Lapatan")) &&
                            (mItemArray.get(5).second.startsWith("Call") || mItemArray.get(5).second.startsWith("Tumawag"))){
                        showDialog(getString(R.string.correct));
                        numberOfMaterials = 3;
                        interModel.setNumberOfMaterials(numberOfMaterials);

                        numberOfCorrect++;

                        goToInteractiveApplication();
                    } else{
                        numberOfMaterials = 3;
                        interModel.setNumberOfMaterials(numberOfMaterials);

                        numberOfErrors++;
                        goToInteractiveApplication();

                        showDialog(getString(R.string.wrong));
                    }
                } else if(chosenInjury.equals("Animal Bites") || chosenInjury.equalsIgnoreCase("Kagat ng hayop")){
                    if((mItemArray.get(0).second.startsWith("Control") || mItemArray.get(0).second.startsWith("Kontrolin")) &&
                            (mItemArray.get(1).second.startsWith("Do") || mItemArray.get(1).second.startsWith("Huwag")) &&
                            (mItemArray.get(2).second.startsWith("Call") || mItemArray.get(2).second.startsWith("Tumawag"))){
                        showDialog(getString(R.string.correct));
                        numberOfMaterials = 1;
                        interModel.setNumberOfMaterials(numberOfMaterials);

                        numberOfCorrect++;

                        goToInteractiveApplication();
                    } else{
                        numberOfMaterials = 1;
                        interModel.setNumberOfMaterials(numberOfMaterials);

                        numberOfErrors++;
                        goToInteractiveApplication();

                        showDialog(getString(R.string.wrong));
                    }
                } else if(chosenInjury.equals("Puncture(Slight Bleeding)") || chosenInjury.equalsIgnoreCase("Tusok(Hindi malubhang pagdurugo)")){
                    if((mItemArray.get(0).second.startsWith("Wash") || mItemArray.get(0).second.startsWith("Hugasan")) &&
                            (mItemArray.get(1).second.startsWith("Clean") || mItemArray.get(1).second.startsWith("Linisin")) &&
                            (mItemArray.get(2).second.startsWith("Apply") || mItemArray.get(2).second.startsWith("Lagyan")) &&
                            (mItemArray.get(3).second.startsWith("Cover") || mItemArray.get(3).second.startsWith("Takpan"))){
                        showDialog(getString(R.string.correct));
                        // -1 sa mga materials para di na kasama gloves sa IP-A

                        numberOfMaterials = (3-1);
                        interModel.setNumberOfMaterials(numberOfMaterials);

                        numberOfCorrect++;

                        goToInteractiveApplication();
                    } else{
                        // -1 sa mga materials para di na kasama gloves sa IP-A

                        numberOfMaterials = (3-1);
                        interModel.setNumberOfMaterials(numberOfMaterials);

                        numberOfErrors++;
                        goToInteractiveApplication();

                        showDialog(getString(R.string.wrong));
                    }
                } else if(chosenInjury.equals("Puncture(Severe Bleeding)") || chosenInjury.equalsIgnoreCase("Tusok(Malubhang pagdurugo)")){
                    if((mItemArray.get(0).second.startsWith("Wash") || mItemArray.get(0).second.startsWith("Hugasan")) &&
                            (mItemArray.get(1).second.startsWith("Apply") || mItemArray.get(1).second.startsWith("Marahang")) &&
                            (mItemArray.get(2).second.startsWith("Clean") || mItemArray.get(2).second.startsWith("Linisin")) &&
                            (mItemArray.get(3).second.startsWith("If") || mItemArray.get(3).second.startsWith("Huwag")) &&
                            (mItemArray.get(4).second.startsWith("Consult") || mItemArray.get(4).second.startsWith("Kumonsulta"))){
                        showDialog(getString(R.string.correct));
                        // -1 sa mga materials para di na kasama gloves sa IP-A

                        numberOfMaterials = (2-1);
                        interModel.setNumberOfMaterials(numberOfMaterials);

                        numberOfCorrect++;

                        goToInteractiveApplication();
                    } else{
                        // -1 sa mga materials para di na kasama gloves sa IP-A

                        numberOfMaterials = (2-1);
                        interModel.setNumberOfMaterials(numberOfMaterials);


                        numberOfErrors++;
                        goToInteractiveApplication();

                        showDialog(getString(R.string.wrong));
                    }
                } else if(chosenInjury.equals("Laceration(Minor)") || chosenInjury.equalsIgnoreCase("Hindi malubhang laslas")){
                    if((mItemArray.get(0).second.startsWith("Use") || mItemArray.get(0).second.startsWith("Magsuot")) &&
                            (mItemArray.get(1).second.startsWith("Wash") || mItemArray.get(1).second.startsWith("Hugasang"))&&
                            (mItemArray.get(2).second.contains("Apply direct") || mItemArray.get(2).second.startsWith("Marahan")) &&
                            (mItemArray.get(3).second.contains("Apply an") || mItemArray.get(3).second.contains("Pahiran")) &&
                            (mItemArray.get(4).second.startsWith("Cover") || mItemArray.get(4).second.startsWith("Takpan"))){
                        showDialog(getString(R.string.correct));
                        // -1 sa mga materials para di na kasama gloves sa IP-A

                        numberOfMaterials = (3-1);
                        interModel.setNumberOfMaterials(numberOfMaterials);

                        numberOfCorrect++;

                        goToInteractiveApplication();
                    } else{
                        // -1 sa mga materials para di na kasama gloves sa IP-A

                        numberOfMaterials = (3-1);
                        interModel.setNumberOfMaterials(numberOfMaterials);

                        numberOfErrors++;
                        goToInteractiveApplication();

                        showDialog(getString(R.string.wrong));
                    }
                } else if(chosenInjury.equals("Laceration(Major)") || chosenInjury.equalsIgnoreCase("Malubhang laslas")){
                    if((mItemArray.get(0).second.startsWith("Put") || mItemArray.get(0).second.startsWith("Magsuot")) &&
                            (mItemArray.get(1).second.startsWith("Control") || mItemArray.get(1).second.startsWith("Kontrolin")) &&
                            (mItemArray.get(2).second.startsWith("Continue") || mItemArray.get(2).second.startsWith("Obserbahan")) &&
                            (mItemArray.get(3).second.startsWith("Care") || mItemArray.get(3).second.startsWith("Iwasan")) &&
                            (mItemArray.get(4).second.startsWith("Have") || mItemArray.get(4).second.startsWith("Tiyaking")) &&
                            (mItemArray.get(5).second.startsWith("Wash") || mItemArray.get(5).second.startsWith("Maghugas")) &&
                            (mItemArray.get(6).second.startsWith("Call") || mItemArray.get(6).second.startsWith("Tumawag"))){
                        showDialog(getString(R.string.correct));
                        // -1 sa mga materials para di na kasama gloves sa IP-A

                        numberOfMaterials = (2-1);
                        interModel.setNumberOfMaterials(numberOfMaterials);

                        numberOfCorrect++;

                        goToInteractiveApplication();
                    } else{
                        numberOfMaterials = (2-1);
                        interModel.setNumberOfMaterials(numberOfMaterials);

                        numberOfErrors++;
                        goToInteractiveApplication();

                        showDialog(getString(R.string.wrong));
                    }
                }
            }
        });
    }

    public void showDialog(String correctOrNot) {
        DialogPractice dialogPractice = new DialogPractice();
        dialogPractice.showDialog(getActivity(), correctOrNot);
    }

    public void goToInteractiveApplication(){

        interModel.setNumberOfError(numberOfErrors);
        interModel.setNumberOfTries(numberOfTries);
        interModel.setNumberOfCorrect(numberOfCorrect);

        interModel.assignSecondStageStats(numberOfCorrect, numberOfErrors, numberOfTries);
        Log.e("SCORE FROM FRAG 1", "CORRECT: " + String.valueOf(interModel.getStage1Stats().get(0)) + " ");
        Log.e("SCORE FROM FRAG 1", "WRONG: " + String.valueOf(interModel.getStage1Stats().get(1)) + " ");
        Log.e("SCORE FROM FRAG 1", "TRIES: " + String.valueOf(interModel.getStage1Stats().get(2)) + " ");

        Log.e("SCORE FROM FRAG 2", "CORRECT: " + String.valueOf(interModel.getStage2Stats().get(0)) + " ");
        Log.e("SCORE FROM FRAG 2", "WRONG: " + String.valueOf(interModel.getStage2Stats().get(1)) + " ");
        Log.e("SCORE FROM FRAG 2", "TRIES: " + String.valueOf(interModel.getStage2Stats().get(2)) + " ");

        InteractivePracticeApplication interactiveApplication = new InteractivePracticeApplication();
        FragmentTransaction fragmentTransaction = getFragmentManager ().beginTransaction();
        Bundle args = new Bundle();
//        args.putString("numberOfMaterials" , String.valueOf(numberOfMaterials));
        args.putString("chosenInjury" , chosenInjury);
        interactiveApplication.setArguments(args);
        fragmentTransaction.add(interactiveApplication, "interactiveApplication")
                .replace(R.id.fragment_container, interactiveApplication)
                .addToBackStack("interactiveApplication")
                .commit();
    }

    public void shuffleArray(){
        Collections.shuffle(mItemArray);
    }

    public void checkOrderOfProcedures(){
        mItemArray = new ArrayList<>();
        mDragListView.setDragListListener(new DragListView.DragListListenerAdapter() {
            @Override
            public void onItemDragStarted(int position) {

            }
            @Override
            public void onItemDragEnded(int fromPosition, int toPosition) {
                    if (Long.valueOf(toPosition).equals((Long)(mItemArray.get(toPosition).first))) {
                        Toast.makeText(mDragListView.getContext(), "Correct position", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(mDragListView.getContext(), "Incorrect position", Toast.LENGTH_SHORT).show();
                }
            }
        });
        setupListRecyclerView();
    }

    private void setupListRecyclerView() {
        mDragListView.setLayoutManager(new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        PracticeItemAdapter listAdapter = new PracticeItemAdapter(mItemArray, R.layout.list_item, R.id.image, false);
        mDragListView.setAdapter(listAdapter, true);
        mDragListView.setCanDragHorizontally(false);
        mDragListView.setCustomDragItem(new MyDragItem(getContext(), R.layout.list_item));
    }

    private static class MyDragItem extends DragItem {
        public MyDragItem(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void onBindDragView(View clickedView, View dragView) {
            CharSequence text = ((TextView) clickedView.findViewById(R.id.text)).getText();
            ((TextView) dragView.findViewById(R.id.text)).setText(text);
            dragView.setBackgroundColor(dragView.getResources().getColor(R.color.list_item_background));
        }
    }
}