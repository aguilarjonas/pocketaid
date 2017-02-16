/**
 * Copyright 2014 Magnus Woxblom
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.jonas.pocketaid.PracticeFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.Pair;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jonas.pocketaid.Adapters.PracticeItemAdapter;
import com.example.jonas.pocketaid.Adapters.StepNumberListAdapter;
import com.example.jonas.pocketaid.MainActivity;
import com.example.jonas.pocketaid.R;
import com.woxthebox.draglistview.DragItem;
import com.woxthebox.draglistview.DragListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ListFragment extends Fragment {

    private ArrayList<Pair<Long, String>> mItemArray;
    private DragListView mDragListView;
    String[] abrasionProcedure, animalBitesProcedure, insectBitesProcedure, thermalBurnProcedure, thirdDegreeBurnProcedure, concussionProcedure, contusionProcedure, fractureProcedure,
             majorLacerationProcedure, minorLacerationProcedure, slightPunctureProcedure, severePunctureProcedure, chemicalBurnProcedure, electricalBurnProcedure;
    RecyclerView lvStepNumber;
    Button checkAnswerBT;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.list_layout, container, false);
        //changes menu button to Up or Back button
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((MainActivity) getActivity()).resetActionBar(true, DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        mDragListView = (DragListView) rootView.findViewById(R.id.drag_list_view);
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
        String chosenInjury = getArguments().getString("chosenInjury");
        selectedInjury(chosenInjury);

        return rootView;
    }

    public void selectedInjury(String chosenInjury){
        String selectedInjury = chosenInjury;
        if (chosenInjury.equals("Abrasion")){
            checkOrderOfProcedures();
            ArrayList<Integer> numberingProcedure = new ArrayList<>();
            for (int counter = 0; counter < abrasionProcedure.length; ++counter) {
                int stepNumberHolder = counter + 1;
                numberingProcedure.addAll(Arrays.asList(stepNumberHolder));
//                stepNumberAdapter = new ArrayAdapter<Integer>(getActivity(), R.layout.steps_numbering, R.id.rowTextView, numberingProcedure);
//                lvStepNumber.setAdapter(stepNumberAdapter);
                setStepNumberAdapter(abrasionProcedure);
                mItemArray.add(new Pair<>(Long.valueOf(counter), "" + abrasionProcedure[counter]));
            }
            shuffleArray();
            getOnClickListener(selectedInjury);

        } else if (chosenInjury.equals("Animal Bites")){
            checkOrderOfProcedures();
            ArrayList<Integer> numberingProcedure = new ArrayList<>();
            for (int counter = 0; counter < animalBitesProcedure.length; ++counter) {
                int stepNumberHolder = counter + 1;
                numberingProcedure.addAll(Arrays.asList(stepNumberHolder));
//                stepNumberAdapter = new ArrayAdapter<Integer>(getActivity(), R.layout.steps_numbering, R.id.rowTextView, numberingProcedure);
//                lvStepNumber.setAdapter(stepNumberAdapter);
                setStepNumberAdapter(animalBitesProcedure);
                mItemArray.add(new Pair<>(Long.valueOf(counter), "" + animalBitesProcedure[counter]));
            }
            shuffleArray();
            getOnClickListener(selectedInjury);

        } else if (chosenInjury.equals("Insect Bites")){
            checkOrderOfProcedures();
            ArrayList<Integer> numberingProcedure = new ArrayList<>();
            for (int counter = 0; counter < insectBitesProcedure.length; ++counter) {
                int stepNumberHolder = counter + 1;
                numberingProcedure.addAll(Arrays.asList(stepNumberHolder));
//                stepNumberAdapter = new ArrayAdapter<Integer>(getActivity(), R.layout.steps_numbering, R.id.rowTextView, numberingProcedure);
//                lvStepNumber.setAdapter(stepNumberAdapter);
                setStepNumberAdapter(insectBitesProcedure);
                mItemArray.add(new Pair<>(Long.valueOf(counter), "" + insectBitesProcedure[counter]));
            }
            shuffleArray();
            getOnClickListener(selectedInjury);

        } else if (chosenInjury.equals("Thermal Burns")){
            checkOrderOfProcedures();
            ArrayList<Integer> numberingProcedure = new ArrayList<>();
            for (int counter = 0; counter < thermalBurnProcedure.length; ++counter) {
                int stepNumberHolder = counter + 1;
                numberingProcedure.addAll(Arrays.asList(stepNumberHolder));
//                stepNumberAdapter = new ArrayAdapter<Integer>(getActivity(), R.layout.steps_numbering, R.id.rowTextView, numberingProcedure);
//                lvStepNumber.setAdapter(stepNumberAdapter);
                setStepNumberAdapter(thermalBurnProcedure);
                mItemArray.add(new Pair<>(Long.valueOf(counter), "" + thermalBurnProcedure[counter]));
            }
            shuffleArray();
            getOnClickListener(selectedInjury);

        } else if (chosenInjury.equals("Chemical Burns")){
            checkOrderOfProcedures();
            ArrayList<Integer> numberingProcedure = new ArrayList<>();
            for (int counter = 0; counter < chemicalBurnProcedure.length; ++counter) {
                int stepNumberHolder = counter + 1;
                numberingProcedure.addAll(Arrays.asList(stepNumberHolder));
//                stepNumberAdapter = new ArrayAdapter<Integer>(getActivity(), R.layout.steps_numbering, R.id.rowTextView, numberingProcedure);
//                lvStepNumber.setAdapter(stepNumberAdapter);
                setStepNumberAdapter(chemicalBurnProcedure);
                mItemArray.add(new Pair<>(Long.valueOf(counter), "" + chemicalBurnProcedure[counter]));
            }
            shuffleArray();
            getOnClickListener(selectedInjury);

        } else if (chosenInjury.equals("3rd Degree Burns")){
            checkOrderOfProcedures();
            ArrayList<Integer> numberingProcedure = new ArrayList<>();
            for (int counter = 0; counter < thirdDegreeBurnProcedure.length; ++counter) {
                int stepNumberHolder = counter + 1;
                numberingProcedure.addAll(Arrays.asList(stepNumberHolder));
//                stepNumberAdapter = new ArrayAdapter<Integer>(getActivity(), R.layout.steps_numbering, R.id.rowTextView, numberingProcedure);
//                lvStepNumber.setAdapter(stepNumberAdapter);
                setStepNumberAdapter(thirdDegreeBurnProcedure);
                mItemArray.add(new Pair<>(Long.valueOf(counter), "" + thirdDegreeBurnProcedure[counter]));
            }
            shuffleArray();
            getOnClickListener(selectedInjury);

        } else if (chosenInjury.equals("Concussion")){
            checkOrderOfProcedures();
            ArrayList<Integer> numberingProcedure = new ArrayList<>();
            for (int counter = 0; counter < concussionProcedure.length; ++counter) {
                int stepNumberHolder = counter + 1;
                numberingProcedure.addAll(Arrays.asList(stepNumberHolder));
//                stepNumberAdapter = new ArrayAdapter<Integer>(getActivity(), R.layout.steps_numbering, R.id.rowTextView, numberingProcedure);
//                lvStepNumber.setAdapter(stepNumberAdapter);
                setStepNumberAdapter(concussionProcedure);
                mItemArray.add(new Pair<>(Long.valueOf(counter), "" + concussionProcedure[counter]));
            }
            shuffleArray();
            getOnClickListener(selectedInjury);

        } else if (chosenInjury.equals("Contusion")){
            checkOrderOfProcedures();
            ArrayList<Integer> numberingProcedure = new ArrayList<>();
            for (int counter = 0; counter < contusionProcedure.length; ++counter) {
                int stepNumberHolder = counter + 1;
                numberingProcedure.addAll(Arrays.asList(stepNumberHolder));
//                stepNumberAdapter = new ArrayAdapter<Integer>(getActivity(), R.layout.steps_numbering, R.id.rowTextView, numberingProcedure);
//                lvStepNumber.setAdapter(stepNumberAdapter);
                setStepNumberAdapter(contusionProcedure);
                mItemArray.add(new Pair<>(Long.valueOf(counter), "" + contusionProcedure[counter]));
            }
            shuffleArray();
            getOnClickListener(selectedInjury);

        } else if (chosenInjury.equals("Fracture")){
            checkOrderOfProcedures();
            ArrayList<Integer> numberingProcedure = new ArrayList<>();
            for (int counter = 0; counter < fractureProcedure.length; ++counter) {
                int stepNumberHolder = counter + 1;
                numberingProcedure.addAll(Arrays.asList(stepNumberHolder));
//                stepNumberAdapter = new ArrayAdapter<Integer>(getActivity(), R.layout.steps_numbering, R.id.rowTextView, numberingProcedure);
//                lvStepNumber.setAdapter(stepNumberAdapter);
                setStepNumberAdapter(fractureProcedure);
                mItemArray.add(new Pair<>(Long.valueOf(counter), "" + fractureProcedure[counter]));
            }
            shuffleArray();
            getOnClickListener(selectedInjury);

        } else if (chosenInjury.equals("Major Laceration")){
            checkOrderOfProcedures();
            ArrayList<Integer> numberingProcedure = new ArrayList<>();
            for (int counter = 0; counter < majorLacerationProcedure.length; ++counter) {
                int stepNumberHolder = counter + 1;
                numberingProcedure.addAll(Arrays.asList(stepNumberHolder));
//                stepNumberAdapter = new ArrayAdapter<Integer>(getActivity(), R.layout.steps_numbering, R.id.rowTextView, numberingProcedure);
//                lvStepNumber.setAdapter(stepNumberAdapter);
                setStepNumberAdapter(majorLacerationProcedure);
                mItemArray.add(new Pair<>(Long.valueOf(counter), "" + majorLacerationProcedure[counter]));
            }
            shuffleArray();
            getOnClickListener(selectedInjury);

        } else if (chosenInjury.equals("Minor Laceration")){
            checkOrderOfProcedures();
            ArrayList<Integer> numberingProcedure = new ArrayList<>();
            for (int counter = 0; counter < minorLacerationProcedure.length; ++counter) {
                int stepNumberHolder = counter + 1;
                numberingProcedure.addAll(Arrays.asList(stepNumberHolder));
//                stepNumberAdapter = new ArrayAdapter<Integer>(getActivity(), R.layout.steps_numbering, R.id.rowTextView, numberingProcedure);
//                lvStepNumber.setAdapter(stepNumberAdapter);
                setStepNumberAdapter(minorLacerationProcedure);
                mItemArray.add(new Pair<>(Long.valueOf(counter), "" + minorLacerationProcedure[counter]));
            }
            shuffleArray();
            getOnClickListener(selectedInjury);

        } else if (chosenInjury.equals("Puncture (Severe Bleeding)")){
            checkOrderOfProcedures();
            ArrayList<Integer> numberingProcedure = new ArrayList<>();
            for (int counter = 0; counter < severePunctureProcedure.length; ++counter) {
                int stepNumberHolder = counter + 1;
                numberingProcedure.addAll(Arrays.asList(stepNumberHolder));
//                stepNumberAdapter = new ArrayAdapter<Integer>(getActivity(), R.layout.steps_numbering, R.id.rowTextView, numberingProcedure);
//                lvStepNumber.setAdapter(stepNumberAdapter);
                setStepNumberAdapter(severePunctureProcedure);
                mItemArray.add(new Pair<>(Long.valueOf(counter), "" + severePunctureProcedure[counter]));
            }
            shuffleArray();
            getOnClickListener(selectedInjury);

        } else if (chosenInjury.equals("Puncture (Slightly Bleeding)")){
            checkOrderOfProcedures();
            ArrayList<Integer> numberingProcedure = new ArrayList<>();
            for (int counter = 0; counter < slightPunctureProcedure.length; ++counter) {
                int stepNumberHolder = counter + 1;
                numberingProcedure.addAll(Arrays.asList(stepNumberHolder));
//                stepNumberAdapter = new ArrayAdapter<Integer>(getActivity(), R.layout.steps_numbering, R.id.rowTextView, numberingProcedure);
//                lvStepNumber.setAdapter(stepNumberAdapter);
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
                if(chosenInjury.equals("Abrasion")){
                    if((mItemArray.get(0).second.startsWith("Wash") || mItemArray.get(0).second.startsWith("Hugasan")) &&
                            (mItemArray.get(1).second.startsWith("Apply") || mItemArray.get(1).second.startsWith("Lagyan")) &&
                            (mItemArray.get(2).second.startsWith("Cover") || mItemArray.get(2).second.startsWith("Takpan")) &&
                            (mItemArray.get(3).second.startsWith("Repeat") || mItemArray.get(3).second.startsWith("Ulitin"))){
                        goToScoreFragment();
                    } else{
                        Toast.makeText(mDragListView.getContext(), "Incorrect order of procedures. Try again.", Toast.LENGTH_SHORT).show();
                    }
                } else if(chosenInjury.equals("Fracture")){
                    if((mItemArray.get(0).second.startsWith("In") || mItemArray.get(0).second.startsWith("Kung")) &&
                            (mItemArray.get(1).second.startsWith("If") || mItemArray.get(1).second.startsWith("Gumamit")) &&
                            (mItemArray.get(2).second.startsWith("Make") || mItemArray.get(2).second.startsWith("Siguraduhin")) &&
                            (mItemArray.get(3).second.startsWith("Support") || mItemArray.get(3).second.contains("Kung mayroong")) &&
                            (mItemArray.get(4).second.startsWith("Raise") || mItemArray.get(4).second.startsWith("Suportahan")) &&
                            (mItemArray.get(5).second.startsWith("Immobilize") || mItemArray.get(5).second.startsWith("Gumamit")) &&
                            (mItemArray.get(6).second.startsWith("Check") || mItemArray.get(6).second.startsWith("Regular")) &&
                            (mItemArray.get(7).second.startsWith("Call") || mItemArray.get(7).second.startsWith("Magpatingin"))){
                        goToScoreFragment();
                    } else{
                        Toast.makeText(mDragListView.getContext(), "Incorrect order of procedures. Try again.", Toast.LENGTH_SHORT).show();
                    }
                } else if(chosenInjury.equals("Contusion")){
                    if((mItemArray.get(0).second.startsWith("Apply") || mItemArray.get(0).second.startsWith("Ilapat"))&&
                            (mItemArray.get(1).second.startsWith("If") || mItemArray.get(1).second.contains("Kung maaari")) &&
                            (mItemArray.get(2).second.startsWith("Rest") || mItemArray.get(2).second.startsWith("Ipahinga")) &&
                            (mItemArray.get(3).second.contains("If needed") || mItemArray.get(3).second.contains("Kung kinakailangan"))){
                        goToScoreFragment();
                    } else{
                        Toast.makeText(mDragListView.getContext(), "Incorrect order of procedures. Try again.", Toast.LENGTH_SHORT).show();
                    }
                } else if(chosenInjury.equals("Concussion")){
                    if((mItemArray.get(0).second.startsWith("Apply") || mItemArray.get(0).second.startsWith("Lapatan")) &&
                            (mItemArray.get(1).second.startsWith("Observe") || mItemArray.get(1).second.startsWith("Obserbahan")) &&
                            (mItemArray.get(2).second.startsWith("If") || mItemArray.get(2).second.startsWith("Tumawag"))){
                        goToScoreFragment();
                    } else{
                        Toast.makeText(mDragListView.getContext(), "Incorrect order of procedures. Try again.", Toast.LENGTH_SHORT).show();
                    }
                } else if(chosenInjury.equals("3rd Degree Burns")){
                    if((mItemArray.get(0).second.startsWith("Protect") || mItemArray.get(0).second.startsWith("Protektahan")) &&
                            (mItemArray.get(1).second.startsWith("Remove") || mItemArray.get(1).second.startsWith("Tanggalin")) &&
                            (mItemArray.get(2).second.startsWith("Do") || mItemArray.get(2).second.startsWith("Huwag")) &&
                            (mItemArray.get(3).second.startsWith("If") || mItemArray.get(3).second.startsWith("Ilagay")) &&
                            (mItemArray.get(4).second.startsWith("Call") || mItemArray.get(4).second.startsWith("Tumawag"))){
                        goToScoreFragment();
                    } else{
                        Toast.makeText(mDragListView.getContext(), "Incorrect order of procedures. Try again.", Toast.LENGTH_SHORT).show();
                    }
                } else if(chosenInjury.equals("Chemical Burns")){
                    if((mItemArray.get(0).second.startsWith("Remove") || mItemArray.get(0).second.contains("Kung mayroon")) &&
                            (mItemArray.get(1).second.startsWith("Place") || mItemArray.get(1).second.startsWith("Panatilihing")) &&
                            (mItemArray.get(2).second.startsWith("Wash") || mItemArray.get(2).second.startsWith("Hugasan")) &&
                            (mItemArray.get(3).second.startsWith("Cover") || mItemArray.get(3).second.startsWith("Takpan")) &&
                            (mItemArray.get(4).second.startsWith("If") || mItemArray.get(4).second.contains("Kung ang kemikal")) &&
                            (mItemArray.get(5).second.startsWith("Seek") || mItemArray.get(5).second.startsWith("Magpasuri"))){
                        goToScoreFragment();
                    } else{
                        Toast.makeText(mDragListView.getContext(), "Incorrect order of procedures. Try again.", Toast.LENGTH_SHORT).show();
                    }
                } else if(chosenInjury.equals("Thermal Burns")){
                    if((mItemArray.get(0).second.startsWith("Hold") || mItemArray.get(0).second.startsWith("Banlawan")) &&
                            (mItemArray.get(1).second.startsWith("Cover") || mItemArray.get(1).second.startsWith("Hugasan")) &&
                            (mItemArray.get(2).second.startsWith("Keep") || mItemArray.get(2).second.startsWith("Takpan"))){
                        goToScoreFragment();
                    } else{
                        Toast.makeText(mDragListView.getContext(), "Incorrect order of procedures. Try again.", Toast.LENGTH_SHORT).show();
                    }
                } else if(chosenInjury.equals("Insect Bites")){
                    if((mItemArray.get(0).second.startsWith("Check") || mItemArray.get(0).second.startsWith("Suriin"))&&
                            (mItemArray.get(1).second.startsWith("Carefully") || mItemArray.get(1).second.startsWith("Kung")) &&
                            (mItemArray.get(2).second.startsWith("Wash") || mItemArray.get(2).second.startsWith("Hugasan")) &&
                            (mItemArray.get(3).second.startsWith("Cover")|| mItemArray.get(3).second.startsWith("Takpan")) &&
                            (mItemArray.get(4).second.startsWith("Apply") || mItemArray.get(4).second.startsWith("Lapatan")) &&
                            (mItemArray.get(5).second.startsWith("Call") || mItemArray.get(5).second.startsWith("Tumawag"))){
                        goToScoreFragment();
                    } else{
                        Toast.makeText(mDragListView.getContext(), "Incorrect order of procedures. Try again.", Toast.LENGTH_SHORT).show();
                    }
                } else if(chosenInjury.equals("Animal Bites")){
                    if((mItemArray.get(0).second.startsWith("Control") || mItemArray.get(0).second.startsWith("Kontrolin")) &&
                            (mItemArray.get(1).second.startsWith("Do") || mItemArray.get(1).second.startsWith("Huwag")) &&
                            (mItemArray.get(2).second.startsWith("Call") || mItemArray.get(2).second.startsWith("Tumawag"))){
                        goToScoreFragment();
                    } else{
                        Toast.makeText(mDragListView.getContext(), "Incorrect order of procedures. Try again.", Toast.LENGTH_SHORT).show();
                    }
                } else if(chosenInjury.equals("Puncture (Slightly Bleeding)")){
                    if((mItemArray.get(0).second.startsWith("Wash") || mItemArray.get(0).second.startsWith("Hugasan")) &&
                            (mItemArray.get(1).second.startsWith("Clean") || mItemArray.get(1).second.startsWith("Linisin")) &&
                            (mItemArray.get(2).second.startsWith("Apply") || mItemArray.get(2).second.startsWith("Lagyan")) &&
                            (mItemArray.get(3).second.startsWith("Cover") || mItemArray.get(3).second.startsWith("Takpan"))){
                        goToScoreFragment();
                    } else{
                        Toast.makeText(mDragListView.getContext(), "Incorrect order of procedures. Try again.", Toast.LENGTH_SHORT).show();
                    }
                } else if(chosenInjury.equals("Puncture (Severe Bleeding)")){
                    if((mItemArray.get(0).second.startsWith("Wash") || mItemArray.get(0).second.startsWith("Hugasan")) &&
                            (mItemArray.get(1).second.startsWith("Apply") || mItemArray.get(1).second.startsWith("Marahang")) &&
                            (mItemArray.get(2).second.startsWith("Clean") || mItemArray.get(2).second.startsWith("Linisin")) &&
                            (mItemArray.get(3).second.startsWith("If") || mItemArray.get(3).second.startsWith("Huwag")) &&
                            (mItemArray.get(4).second.startsWith("Consult") || mItemArray.get(4).second.startsWith("Kumonsulta"))){
                        goToScoreFragment();
                    } else{
                        Toast.makeText(mDragListView.getContext(), "Incorrect order of procedures. Try again.", Toast.LENGTH_SHORT).show();
                    }
                } else if(chosenInjury.equals("Minor Laceration")){
                    if((mItemArray.get(0).second.startsWith("Use") || mItemArray.get(0).second.startsWith("Magsuot")) &&
                            (mItemArray.get(1).second.startsWith("Apply") || mItemArray.get(1).second.startsWith("Marahang"))&&
                            (mItemArray.get(2).second.startsWith("Wash") || mItemArray.get(2).second.startsWith("Hugasan")) &&
                            (mItemArray.get(3).second.contains("Apply an") || mItemArray.get(3).second.contains("Pahiran")) &&
                            (mItemArray.get(4).second.startsWith("Cover") || mItemArray.get(4).second.startsWith("Takpan"))){
                        goToScoreFragment();
                    } else{
                        Toast.makeText(mDragListView.getContext(), "Incorrect order of procedures. Try again.", Toast.LENGTH_SHORT).show();
                    }
                } else if(chosenInjury.equals("Major Laceration")){
                    if((mItemArray.get(0).second.startsWith("Put") || mItemArray.get(0).second.startsWith("Magsuot")) &&
                            (mItemArray.get(1).second.startsWith("Control") || mItemArray.get(1).second.startsWith("Control")) &&
                            (mItemArray.get(2).second.startsWith("Continue") || mItemArray.get(2).second.startsWith("Continue")) &&
                            (mItemArray.get(3).second.startsWith("Care") || mItemArray.get(3).second.startsWith("Iwasan")) &&
                            (mItemArray.get(4).second.startsWith("Have") || mItemArray.get(4).second.startsWith("Tiyaking")) &&
                            (mItemArray.get(5).second.startsWith("Wash") || mItemArray.get(5).second.startsWith("Maghugas")) &&
                            (mItemArray.get(6).second.startsWith("Call") || mItemArray.get(6).second.startsWith("Tumawag"))){
                        goToScoreFragment();
                    } else{
                        Toast.makeText(mDragListView.getContext(), "Incorrect order of procedures. Try again.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void goToScoreFragment(){
        InteractivePracticeApplication interactiveApplication = new InteractivePracticeApplication();
        FragmentTransaction fragmentTransaction = getFragmentManager ().beginTransaction();
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