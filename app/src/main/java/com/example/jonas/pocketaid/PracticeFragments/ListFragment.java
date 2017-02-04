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
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jonas.pocketaid.Adapters.PracticeItemAdapter;
import com.example.jonas.pocketaid.InteractivePracticeSwipeRefreshLayout.MySwipeRefreshLayout;
import com.example.jonas.pocketaid.R;
import com.woxthebox.draglistview.DragItem;
import com.woxthebox.draglistview.DragListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ListFragment extends Fragment {

    private ArrayList<Pair<Long, String>> mItemArray;
    private DragListView mDragListView;
    private MySwipeRefreshLayout mRefreshLayout;
    String[] abrasionProcedure, animalBitesProcedure, insectBitesProcedure, thermalBurnProcedure,
             thirdDegreeBurnProcedure, concussionProcedure, contusionProcedure, fractureProcedure,
             majorLacerationProcedure, minorLacerationProcedure, slightPunctureProcedure, severePunctureProcedure,
             chemicalBurnProcedure, electricalBurnProcedure;
    ListView lvStepNumber;
    private ArrayAdapter<Integer> stepNumberAdapter ;
    Button checkAnswerBT;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.list_layout, container, false);
        mRefreshLayout = (MySwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_layout);
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

        if (chosenInjury.equals("Abrasion")){
            checkOrderOfProcedures();
            ArrayList<Integer> numberingProcedure = new ArrayList<>();
            for (int counter = 0; counter < abrasionProcedure.length; ++counter) {
                int stepNumberHolder = counter + 1;
                numberingProcedure.addAll(Arrays.asList(stepNumberHolder));
                stepNumberAdapter = new ArrayAdapter<Integer>(getActivity(), R.layout.steps_numbering, numberingProcedure);
                lvStepNumber.setAdapter(stepNumberAdapter);
                mItemArray.add(new Pair<>(Long.valueOf(counter), "" + abrasionProcedure[counter]));
            }
            Collections.shuffle(mItemArray);

        } else if (chosenInjury.equals("Animal Bites")){
            checkOrderOfProcedures();
            ArrayList<Integer> numberingProcedure = new ArrayList<>();
            for (int counter = 0; counter < animalBitesProcedure.length; ++counter) {
                int stepNumberHolder = counter + 1;
                numberingProcedure.addAll(Arrays.asList(stepNumberHolder));
                stepNumberAdapter = new ArrayAdapter<Integer>(getActivity(), R.layout.steps_numbering, numberingProcedure);
                lvStepNumber.setAdapter(stepNumberAdapter);
                mItemArray.add(new Pair<>(Long.valueOf(counter), "" + animalBitesProcedure[counter]));
            }
            Collections.shuffle(mItemArray);

        } else if (chosenInjury.equals("Insect Bites")){
            checkOrderOfProcedures();
            ArrayList<Integer> numberingProcedure = new ArrayList<>();
            for (int counter = 0; counter < insectBitesProcedure.length; ++counter) {
                int stepNumberHolder = counter + 1;
                numberingProcedure.addAll(Arrays.asList(stepNumberHolder));
                stepNumberAdapter = new ArrayAdapter<Integer>(getActivity(), R.layout.steps_numbering, numberingProcedure);
                lvStepNumber.setAdapter(stepNumberAdapter);
                mItemArray.add(new Pair<>(Long.valueOf(counter), "" + insectBitesProcedure[counter]));
            }
            Collections.shuffle(mItemArray);

        } else if (chosenInjury.equals("Thermal Burns")){
            checkOrderOfProcedures();
            ArrayList<Integer> numberingProcedure = new ArrayList<>();
            for (int counter = 0; counter < thermalBurnProcedure.length; ++counter) {
                int stepNumberHolder = counter + 1;
                numberingProcedure.addAll(Arrays.asList(stepNumberHolder));
                stepNumberAdapter = new ArrayAdapter<Integer>(getActivity(), R.layout.steps_numbering, numberingProcedure);
                lvStepNumber.setAdapter(stepNumberAdapter);
                mItemArray.add(new Pair<>(Long.valueOf(counter), "" + thermalBurnProcedure[counter]));
            }
            Collections.shuffle(mItemArray);

        } else if (chosenInjury.equals("Electrical Burns")){
            checkOrderOfProcedures();
            ArrayList<Integer> numberingProcedure = new ArrayList<>();
            for (int counter = 0; counter < electricalBurnProcedure.length; ++counter) {
                int stepNumberHolder = counter + 1;
                numberingProcedure.addAll(Arrays.asList(stepNumberHolder));
                stepNumberAdapter = new ArrayAdapter<Integer>(getActivity(), R.layout.steps_numbering, numberingProcedure);
                lvStepNumber.setAdapter(stepNumberAdapter);
                mItemArray.add(new Pair<>(Long.valueOf(counter), "" + electricalBurnProcedure[counter]));
            }
            Collections.shuffle(mItemArray);

        } else if (chosenInjury.equals("Chemical Burns")){
            checkOrderOfProcedures();
            ArrayList<Integer> numberingProcedure = new ArrayList<>();
            for (int counter = 0; counter < chemicalBurnProcedure.length; ++counter) {
                int stepNumberHolder = counter + 1;
                numberingProcedure.addAll(Arrays.asList(stepNumberHolder));
                stepNumberAdapter = new ArrayAdapter<Integer>(getActivity(), R.layout.steps_numbering, numberingProcedure);
                lvStepNumber.setAdapter(stepNumberAdapter);
                mItemArray.add(new Pair<>(Long.valueOf(counter), "" + chemicalBurnProcedure[counter]));
            }
            Collections.shuffle(mItemArray);

        } else if (chosenInjury.equals("3rd Degree Burns")){
            checkOrderOfProcedures();
            ArrayList<Integer> numberingProcedure = new ArrayList<>();
            for (int counter = 0; counter < thirdDegreeBurnProcedure.length; ++counter) {
                int stepNumberHolder = counter + 1;
                numberingProcedure.addAll(Arrays.asList(stepNumberHolder));
                stepNumberAdapter = new ArrayAdapter<Integer>(getActivity(), R.layout.steps_numbering, numberingProcedure);
                lvStepNumber.setAdapter(stepNumberAdapter);
                mItemArray.add(new Pair<>(Long.valueOf(counter), "" + thirdDegreeBurnProcedure[counter]));
            }
            Collections.shuffle(mItemArray);

        } else if (chosenInjury.equals("Concussion")){
            checkOrderOfProcedures();
            ArrayList<Integer> numberingProcedure = new ArrayList<>();
            for (int counter = 0; counter < concussionProcedure.length; ++counter) {
                int stepNumberHolder = counter + 1;
                numberingProcedure.addAll(Arrays.asList(stepNumberHolder));
                stepNumberAdapter = new ArrayAdapter<Integer>(getActivity(), R.layout.steps_numbering, numberingProcedure);
                lvStepNumber.setAdapter(stepNumberAdapter);
                mItemArray.add(new Pair<>(Long.valueOf(counter), "" + concussionProcedure[counter]));
            }
            Collections.shuffle(mItemArray);

        } else if (chosenInjury.equals("Contusion")){
            checkOrderOfProcedures();
            ArrayList<Integer> numberingProcedure = new ArrayList<>();
            for (int counter = 0; counter < contusionProcedure.length; ++counter) {
                int stepNumberHolder = counter + 1;
                numberingProcedure.addAll(Arrays.asList(stepNumberHolder));
                stepNumberAdapter = new ArrayAdapter<Integer>(getActivity(), R.layout.steps_numbering, numberingProcedure);
                lvStepNumber.setAdapter(stepNumberAdapter);
                mItemArray.add(new Pair<>(Long.valueOf(counter), "" + contusionProcedure[counter]));
            }
            Collections.shuffle(mItemArray);

        } else if (chosenInjury.equals("Fracture")){
            checkOrderOfProcedures();
            ArrayList<Integer> numberingProcedure = new ArrayList<>();
            for (int counter = 0; counter < fractureProcedure.length; ++counter) {
                int stepNumberHolder = counter + 1;
                numberingProcedure.addAll(Arrays.asList(stepNumberHolder));
                stepNumberAdapter = new ArrayAdapter<Integer>(getActivity(), R.layout.steps_numbering, numberingProcedure);
                lvStepNumber.setAdapter(stepNumberAdapter);
                mItemArray.add(new Pair<>(Long.valueOf(counter), "" + fractureProcedure[counter]));
            }
            Collections.shuffle(mItemArray);

        } else if (chosenInjury.equals("Major Laceration")){
            checkOrderOfProcedures();
            ArrayList<Integer> numberingProcedure = new ArrayList<>();
            for (int counter = 0; counter < majorLacerationProcedure.length; ++counter) {
                int stepNumberHolder = counter + 1;
                numberingProcedure.addAll(Arrays.asList(stepNumberHolder));
                stepNumberAdapter = new ArrayAdapter<Integer>(getActivity(), R.layout.steps_numbering, numberingProcedure);
                lvStepNumber.setAdapter(stepNumberAdapter);
                mItemArray.add(new Pair<>(Long.valueOf(counter), "" + majorLacerationProcedure[counter]));
            }
            Collections.shuffle(mItemArray);

        } else if (chosenInjury.equals("Minor Laceration")){
            checkOrderOfProcedures();
            ArrayList<Integer> numberingProcedure = new ArrayList<>();
            for (int counter = 0; counter < minorLacerationProcedure.length; ++counter) {
                int stepNumberHolder = counter + 1;
                numberingProcedure.addAll(Arrays.asList(stepNumberHolder));
                stepNumberAdapter = new ArrayAdapter<Integer>(getActivity(), R.layout.steps_numbering, numberingProcedure);
                lvStepNumber.setAdapter(stepNumberAdapter);
                mItemArray.add(new Pair<>(Long.valueOf(counter), "" + minorLacerationProcedure[counter]));
            }
            Collections.shuffle(mItemArray);

        } else if (chosenInjury.equals("Puncture (Severe Bleeding)")){
            checkOrderOfProcedures();
            ArrayList<Integer> numberingProcedure = new ArrayList<>();
            for (int counter = 0; counter < severePunctureProcedure.length; ++counter) {
                int stepNumberHolder = counter + 1;
                numberingProcedure.addAll(Arrays.asList(stepNumberHolder));
                stepNumberAdapter = new ArrayAdapter<Integer>(getActivity(), R.layout.steps_numbering, numberingProcedure);
                lvStepNumber.setAdapter(stepNumberAdapter);
                mItemArray.add(new Pair<>(Long.valueOf(counter), "" + severePunctureProcedure[counter]));
            }
            Collections.shuffle(mItemArray);

        } else if (chosenInjury.equals("Puncture (Slightly Bleeding)")){
            checkOrderOfProcedures();
            ArrayList<Integer> numberingProcedure = new ArrayList<>();
            for (int counter = 0; counter < slightPunctureProcedure.length; ++counter) {
                int stepNumberHolder = counter + 1;
                numberingProcedure.addAll(Arrays.asList(stepNumberHolder));
                stepNumberAdapter = new ArrayAdapter<Integer>(getActivity(), R.layout.steps_numbering, numberingProcedure);
                lvStepNumber.setAdapter(stepNumberAdapter);
                mItemArray.add(new Pair<>(Long.valueOf(counter), "" + slightPunctureProcedure[counter]));
            }
            Collections.shuffle(mItemArray);
        }
    }

    public void checkOrderOfProcedures(){
        mItemArray = new ArrayList<>();
        mDragListView.setDragListListener(new DragListView.DragListListenerAdapter() {
            @Override
            public void onItemDragStarted(int position) {
                mRefreshLayout.setEnabled(false);
            }
            @Override
            public void onItemDragEnded(int fromPosition, int toPosition) {
                mRefreshLayout.setEnabled(true);
                boolean checkAnswer = false;

                    if (Long.valueOf(toPosition).equals((Long)(mItemArray.get(toPosition).first))) {
                        // This is the correct position
                        checkAnswerBT.setClickable(true);
                        checkAnswer = true;
                        Toast.makeText(mDragListView.getContext(), "Correct position", Toast.LENGTH_SHORT).show();

                        checkAnswerBT.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                DisplayScoreFragment displayScoreFragment = new DisplayScoreFragment();
                                FragmentTransaction fragmentTransaction = getFragmentManager ().beginTransaction();
                                fragmentTransaction.add(displayScoreFragment, "displayScoreFragment")
                                        .replace(R.id.fragment_container, displayScoreFragment)
                                        .addToBackStack("displayScoreFragment")
                                        .commit();
                            }
                        });
                    } else {
                        // This is not the correct position
                        checkAnswerBT.setClickable(false);
                        checkAnswer = false;
                        Toast.makeText(mDragListView.getContext(), "Incorrect position", Toast.LENGTH_SHORT).show();
                    }
            }
        });



        mRefreshLayout.setScrollingView(mDragListView.getRecyclerView());
        mRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getContext(), R.color.colorAccent));
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
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
