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
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jonas.pocketaid.Adapters.PracticeItemAdapter;
import com.example.jonas.pocketaid.R;
import com.woxthebox.draglistview.DragItem;
import com.woxthebox.draglistview.DragListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ListFragment extends Fragment {

    private ArrayList<Pair<Long, String>> mItemArray;
    private DragListView mDragListView;
    private ArrayAdapter<Integer> stepNumberAdapter;
    String[] abrasionProcedure, animalBitesProcedure, insectBitesProcedure, thermalBurnProcedure, thirdDegreeBurnProcedure, concussionProcedure, contusionProcedure, fractureProcedure,
             majorLacerationProcedure, minorLacerationProcedure, slightPunctureProcedure, severePunctureProcedure, chemicalBurnProcedure, electricalBurnProcedure;
    ListView lvStepNumber;
    Button checkAnswerBT;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.list_layout, container, false);
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
        lvStepNumber = (ListView) rootView.findViewById(R.id.lv_step_numberPrac);
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
                stepNumberAdapter = new ArrayAdapter<Integer>(getActivity(), R.layout.steps_numbering, R.id.rowTextView, numberingProcedure);
                lvStepNumber.setAdapter(stepNumberAdapter);
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
                stepNumberAdapter = new ArrayAdapter<Integer>(getActivity(), R.layout.steps_numbering, R.id.rowTextView, numberingProcedure);
                lvStepNumber.setAdapter(stepNumberAdapter);
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
                stepNumberAdapter = new ArrayAdapter<Integer>(getActivity(), R.layout.steps_numbering, R.id.rowTextView, numberingProcedure);
                lvStepNumber.setAdapter(stepNumberAdapter);
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
                stepNumberAdapter = new ArrayAdapter<Integer>(getActivity(), R.layout.steps_numbering, R.id.rowTextView, numberingProcedure);
                lvStepNumber.setAdapter(stepNumberAdapter);
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
                stepNumberAdapter = new ArrayAdapter<Integer>(getActivity(), R.layout.steps_numbering, R.id.rowTextView, numberingProcedure);
                lvStepNumber.setAdapter(stepNumberAdapter);
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
                stepNumberAdapter = new ArrayAdapter<Integer>(getActivity(), R.layout.steps_numbering, R.id.rowTextView, numberingProcedure);
                lvStepNumber.setAdapter(stepNumberAdapter);
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
                stepNumberAdapter = new ArrayAdapter<Integer>(getActivity(), R.layout.steps_numbering, R.id.rowTextView, numberingProcedure);
                lvStepNumber.setAdapter(stepNumberAdapter);
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
                stepNumberAdapter = new ArrayAdapter<Integer>(getActivity(), R.layout.steps_numbering, R.id.rowTextView, numberingProcedure);
                lvStepNumber.setAdapter(stepNumberAdapter);
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
                stepNumberAdapter = new ArrayAdapter<Integer>(getActivity(), R.layout.steps_numbering, R.id.rowTextView, numberingProcedure);
                lvStepNumber.setAdapter(stepNumberAdapter);
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
                stepNumberAdapter = new ArrayAdapter<Integer>(getActivity(), R.layout.steps_numbering, R.id.rowTextView, numberingProcedure);
                lvStepNumber.setAdapter(stepNumberAdapter);
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
                stepNumberAdapter = new ArrayAdapter<Integer>(getActivity(), R.layout.steps_numbering, R.id.rowTextView, numberingProcedure);
                lvStepNumber.setAdapter(stepNumberAdapter);
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
                stepNumberAdapter = new ArrayAdapter<Integer>(getActivity(), R.layout.steps_numbering, R.id.rowTextView, numberingProcedure);
                lvStepNumber.setAdapter(stepNumberAdapter);
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
                stepNumberAdapter = new ArrayAdapter<Integer>(getActivity(), R.layout.steps_numbering, R.id.rowTextView, numberingProcedure);
                lvStepNumber.setAdapter(stepNumberAdapter);
                mItemArray.add(new Pair<>(Long.valueOf(counter), "" + slightPunctureProcedure[counter]));
            }
            shuffleArray();
            getOnClickListener(selectedInjury);
        }
    }

    public void getOnClickListener(final String chosenInjury){
        checkAnswerBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chosenInjury.equals("Abrasion")){
                    if(mItemArray.get(0).second.startsWith("Wash") && mItemArray.get(1).second.startsWith("Apply") &&
                            mItemArray.get(2).second.startsWith("Cover") && mItemArray.get(3).second.startsWith("Repeat")){
                        goToScoreFragment();
                    } else{
                        Toast.makeText(mDragListView.getContext(), "Incorrect order of procedures. Try again.", Toast.LENGTH_SHORT).show();
                    }
                } else if(chosenInjury.equals("Fracture")){
                    if(mItemArray.get(0).second.startsWith("In") && mItemArray.get(1).second.startsWith("If")
                            && mItemArray.get(2).second.startsWith("Support") && mItemArray.get(3).second.startsWith("Raise")
                            && mItemArray.get(4).second.startsWith("Immobilize") && mItemArray.get(5).second.startsWith("Check")
                            && mItemArray.get(6).second.startsWith("Watch") && mItemArray.get(7).second.startsWith("Call")){
                        goToScoreFragment();
                    } else{
                        Toast.makeText(mDragListView.getContext(), "Incorrect order of procedures. Try again.", Toast.LENGTH_SHORT).show();
                    }
                } else if(chosenInjury.equals("Contusion")){
                    if(mItemArray.get(0).second.startsWith("Apply") && mItemArray.get(1).second.startsWith("If")
                            && mItemArray.get(2).second.startsWith("Rest") && mItemArray.get(3).second.contains("If needed")){
                        goToScoreFragment();
                    } else{
                        Toast.makeText(mDragListView.getContext(), "Incorrect order of procedures. Try again.", Toast.LENGTH_SHORT).show();
                    }
                } else if(chosenInjury.equals("Concussion")){
                    if(mItemArray.get(0).second.startsWith("Apply") && mItemArray.get(1).second.startsWith("Observe")
                            && mItemArray.get(2).second.startsWith("If")){
                        goToScoreFragment();
                    } else{
                        Toast.makeText(mDragListView.getContext(), "Incorrect order of procedures. Try again.", Toast.LENGTH_SHORT).show();
                    }
                } else if(chosenInjury.equals("3rd Degree Burns")){
                    if(mItemArray.get(0).second.startsWith("Protect") && mItemArray.get(1).second.startsWith("Remove")
                            && mItemArray.get(2).second.startsWith("Do") && mItemArray.get(3).second.startsWith("If")
                            && mItemArray.get(4).second.startsWith("Call")){
                        goToScoreFragment();
                    } else{
                        Toast.makeText(mDragListView.getContext(), "Incorrect order of procedures. Try again.", Toast.LENGTH_SHORT).show();
                    }
                } else if(chosenInjury.equals("Chemical Burns")){
                    if(mItemArray.get(0).second.startsWith("Remove") && mItemArray.get(1).second.startsWith("Place") &&
                            mItemArray.get(2).second.startsWith("Wash") && mItemArray.get(3).second.startsWith("Cover")
                            && mItemArray.get(4).second.startsWith("Seek")){
                        goToScoreFragment();
                    } else{
                        Toast.makeText(mDragListView.getContext(), "Incorrect order of procedures. Try again.", Toast.LENGTH_SHORT).show();
                    }
                } else if(chosenInjury.equals("Thermal Burns")){
                    if(mItemArray.get(0).second.startsWith("Hold") && mItemArray.get(1).second.startsWith("Cover") &&
                            mItemArray.get(2).second.startsWith("Keep")){
                        goToScoreFragment();
                    } else{
                        Toast.makeText(mDragListView.getContext(), "Incorrect order of procedures. Try again.", Toast.LENGTH_SHORT).show();
                    }
                } else if(chosenInjury.equals("Insect Bites")){
                    if(mItemArray.get(0).second.startsWith("Check") && mItemArray.get(1).second.startsWith("Carefully") &&
                            mItemArray.get(2).second.startsWith("Wash") && mItemArray.get(3).second.startsWith("Cover")
                            && mItemArray.get(4).second.startsWith("Apply") && mItemArray.get(5).second.startsWith("Call")){
                        goToScoreFragment();
                    } else{
                        Toast.makeText(mDragListView.getContext(), "Incorrect order of procedures. Try again.", Toast.LENGTH_SHORT).show();
                    }
                } else if(chosenInjury.equals("Animal Bites")){
                    if(mItemArray.get(0).second.startsWith("Control") && mItemArray.get(1).second.startsWith("Do") &&
                            mItemArray.get(2).second.startsWith("Call")){
                        goToScoreFragment();
                    } else{
                        Toast.makeText(mDragListView.getContext(), "Incorrect order of procedures. Try again.", Toast.LENGTH_SHORT).show();
                    }
                } else if(chosenInjury.equals("Puncture (Slightly Bleeding)")){
                    if(mItemArray.get(0).second.startsWith("Wash") && mItemArray.get(1).second.startsWith("Clean")
                            && mItemArray.get(2).second.startsWith("Apply") && mItemArray.get(3).second.startsWith("Cover")){
                        goToScoreFragment();
                    } else{
                        Toast.makeText(mDragListView.getContext(), "Incorrect order of procedures. Try again.", Toast.LENGTH_SHORT).show();
                    }
                } else if(chosenInjury.equals("Puncture (Severe Bleeding)")){
                    if(mItemArray.get(0).second.startsWith("Wash") && mItemArray.get(1).second.startsWith("Apply")
                            && mItemArray.get(2).second.startsWith("Clean") && mItemArray.get(3).second.startsWith("Consult")){
                        goToScoreFragment();
                    } else{
                        Toast.makeText(mDragListView.getContext(), "Incorrect order of procedures. Try again.", Toast.LENGTH_SHORT).show();
                    }
                } else if(chosenInjury.equals("Minor Laceration")){
                    if(mItemArray.get(0).second.startsWith("Use") && mItemArray.get(1).second.startsWith("Apply")
                            && mItemArray.get(2).second.startsWith("Wash") && mItemArray.get(3).second.contains("Apply an")
                            && mItemArray.get(4).second.startsWith("Cover")){
                        goToScoreFragment();
                    } else{
                        Toast.makeText(mDragListView.getContext(), "Incorrect order of procedures. Try again.", Toast.LENGTH_SHORT).show();
                    }
                } else if(chosenInjury.equals("Major Laceration")){
                    if(mItemArray.get(0).second.startsWith("Put") && mItemArray.get(1).second.startsWith("Control")
                            && mItemArray.get(2).second.startsWith("Continue") && mItemArray.get(3).second.startsWith("Care")
                            && mItemArray.get(4).second.startsWith("Have") && mItemArray.get(5).second.startsWith("Wash")){
                        goToScoreFragment();
                    } else{
                        Toast.makeText(mDragListView.getContext(), "Incorrect order of procedures. Try again.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void goToScoreFragment(){
        DisplayScoreFragment displayScoreFragment = new DisplayScoreFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager ().beginTransaction();
        fragmentTransaction.add(displayScoreFragment, "displayScoreFragment")
                .replace(R.id.fragment_container, displayScoreFragment)
                .addToBackStack("displayScoreFragment")
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
        mDragListView.setLayoutManager(new LinearLayoutManager(getContext()));
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