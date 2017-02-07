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
            shuffleArray();

            checkAnswerBT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(mItemArray.get(0).second.startsWith("Wash") && mItemArray.get(1).second.startsWith("Apply") &&
                            mItemArray.get(2).second.startsWith("Cover") && mItemArray.get(3).second.startsWith("Repeat")){
                        DisplayScoreFragment displayScoreFragment = new DisplayScoreFragment();
                        FragmentTransaction fragmentTransaction = getFragmentManager ().beginTransaction();
                        fragmentTransaction.add(displayScoreFragment, "displayScoreFragment")
                                .replace(R.id.fragment_container, displayScoreFragment)
                                .addToBackStack("displayScoreFragment")
                                .commit();
                    } else{
                        Toast.makeText(mDragListView.getContext(), "Mali order ng steps bobo!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

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
            shuffleArray();

            checkAnswerBT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(mItemArray.get(0).second.startsWith("Control") && mItemArray.get(1).second.startsWith("Do") &&
                            mItemArray.get(2).second.startsWith("Call")){
                        DisplayScoreFragment displayScoreFragment = new DisplayScoreFragment();
                        FragmentTransaction fragmentTransaction = getFragmentManager ().beginTransaction();
                        fragmentTransaction.add(displayScoreFragment, "displayScoreFragment")
                                .replace(R.id.fragment_container, displayScoreFragment)
                                .addToBackStack("displayScoreFragment")
                                .commit();
                    } else{
                        Toast.makeText(mDragListView.getContext(), "Mali order ng steps bobo!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

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
            shuffleArray();

            checkAnswerBT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(mItemArray.get(0).second.startsWith("Check") && mItemArray.get(1).second.startsWith("Carefully") &&
                            mItemArray.get(2).second.startsWith("Wash") && mItemArray.get(3).second.startsWith("Cover")
                            && mItemArray.get(4).second.startsWith("Apply") && mItemArray.get(5).second.startsWith("Call")){
                        DisplayScoreFragment displayScoreFragment = new DisplayScoreFragment();
                        FragmentTransaction fragmentTransaction = getFragmentManager ().beginTransaction();
                        fragmentTransaction.add(displayScoreFragment, "displayScoreFragment")
                                .replace(R.id.fragment_container, displayScoreFragment)
                                .addToBackStack("displayScoreFragment")
                                .commit();
                    } else{
                        Toast.makeText(mDragListView.getContext(), "Mali order ng steps bobo!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

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
            shuffleArray();

            checkAnswerBT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(mItemArray.get(0).second.startsWith("Hold") && mItemArray.get(1).second.startsWith("Cover") &&
                            mItemArray.get(2).second.startsWith("Keep")){
                        DisplayScoreFragment displayScoreFragment = new DisplayScoreFragment();
                        FragmentTransaction fragmentTransaction = getFragmentManager ().beginTransaction();
                        fragmentTransaction.add(displayScoreFragment, "displayScoreFragment")
                                .replace(R.id.fragment_container, displayScoreFragment)
                                .addToBackStack("displayScoreFragment")
                                .commit();
                    } else{
                        Toast.makeText(mDragListView.getContext(), "Mali order ng steps bobo!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

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
            shuffleArray();

            checkAnswerBT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(mItemArray.get(0).second.startsWith("Remove") && mItemArray.get(1).second.startsWith("Place") &&
                            mItemArray.get(2).second.startsWith("Wash") && mItemArray.get(3).second.startsWith("Cover")
                            && mItemArray.get(4).second.startsWith("Seek")){
                        DisplayScoreFragment displayScoreFragment = new DisplayScoreFragment();
                        FragmentTransaction fragmentTransaction = getFragmentManager ().beginTransaction();
                        fragmentTransaction.add(displayScoreFragment, "displayScoreFragment")
                                .replace(R.id.fragment_container, displayScoreFragment)
                                .addToBackStack("displayScoreFragment")
                                .commit();
                    } else{
                        Toast.makeText(mDragListView.getContext(), "Mali order ng steps bobo!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

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
            shuffleArray();

            checkAnswerBT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mItemArray.get(0).second.startsWith("Protect") && mItemArray.get(1).second.startsWith("Remove")
                            && mItemArray.get(2).second.startsWith("Do") && mItemArray.get(3).second.startsWith("If")
                            && mItemArray.get(4).second.startsWith("Call")){
                        DisplayScoreFragment displayScoreFragment = new DisplayScoreFragment();
                        FragmentTransaction fragmentTransaction = getFragmentManager ().beginTransaction();
                        fragmentTransaction.add(displayScoreFragment, "displayScoreFragment")
                                .replace(R.id.fragment_container, displayScoreFragment)
                                .addToBackStack("displayScoreFragment")
                                .commit();

                    } else{
                        Toast.makeText(mDragListView.getContext(), "Mali order ng steps bobo!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

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
            shuffleArray();

            checkAnswerBT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mItemArray.get(0).second.startsWith("Apply") && mItemArray.get(1).second.startsWith("Observe")
                            && mItemArray.get(2).second.startsWith("If")){
                        DisplayScoreFragment displayScoreFragment = new DisplayScoreFragment();
                        FragmentTransaction fragmentTransaction = getFragmentManager ().beginTransaction();
                        fragmentTransaction.add(displayScoreFragment, "displayScoreFragment")
                                .replace(R.id.fragment_container, displayScoreFragment)
                                .addToBackStack("displayScoreFragment")
                                .commit();

                    } else{
                        Toast.makeText(mDragListView.getContext(), "Mali order ng steps bobo!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

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
            shuffleArray();

            checkAnswerBT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mItemArray.get(0).second.startsWith("Apply") && mItemArray.get(1).second.startsWith("If")
                            && mItemArray.get(2).second.startsWith("Rest") && mItemArray.get(3).second.contains("If needed")){
                        DisplayScoreFragment displayScoreFragment = new DisplayScoreFragment();
                        FragmentTransaction fragmentTransaction = getFragmentManager ().beginTransaction();
                        fragmentTransaction.add(displayScoreFragment, "displayScoreFragment")
                                .replace(R.id.fragment_container, displayScoreFragment)
                                .addToBackStack("displayScoreFragment")
                                .commit();

                    } else{
                        Toast.makeText(mDragListView.getContext(), "Mali order ng steps bobo!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

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
            shuffleArray();

            checkAnswerBT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mItemArray.get(0).second.startsWith("In") && mItemArray.get(1).second.startsWith("If")
                            && mItemArray.get(2).second.startsWith("Support") && mItemArray.get(3).second.startsWith("Raise")
                            && mItemArray.get(4).second.startsWith("Immobilize") && mItemArray.get(5).second.startsWith("Check")
                            && mItemArray.get(6).second.startsWith("Watch") && mItemArray.get(7).second.startsWith("Call")){
                        DisplayScoreFragment displayScoreFragment = new DisplayScoreFragment();
                        FragmentTransaction fragmentTransaction = getFragmentManager ().beginTransaction();
                        fragmentTransaction.add(displayScoreFragment, "displayScoreFragment")
                                .replace(R.id.fragment_container, displayScoreFragment)
                                .addToBackStack("displayScoreFragment")
                                .commit();

                    } else{
                        Toast.makeText(mDragListView.getContext(), "Mali order ng steps bobo!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

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
            shuffleArray();

            checkAnswerBT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mItemArray.get(0).second.startsWith("Put") && mItemArray.get(1).second.startsWith("Control")
                            && mItemArray.get(2).second.startsWith("Continue") && mItemArray.get(3).second.startsWith("Care")
                            && mItemArray.get(4).second.startsWith("Have") && mItemArray.get(5).second.startsWith("Wash")){
                        DisplayScoreFragment displayScoreFragment = new DisplayScoreFragment();
                        FragmentTransaction fragmentTransaction = getFragmentManager ().beginTransaction();
                        fragmentTransaction.add(displayScoreFragment, "displayScoreFragment")
                                .replace(R.id.fragment_container, displayScoreFragment)
                                .addToBackStack("displayScoreFragment")
                                .commit();

                    } else{
                        Toast.makeText(mDragListView.getContext(), "Mali order ng steps bobo!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

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
            shuffleArray();

            checkAnswerBT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mItemArray.get(0).second.startsWith("Use") && mItemArray.get(1).second.startsWith("Apply")
                            && mItemArray.get(2).second.startsWith("Wash") && mItemArray.get(3).second.contains("Apply an")
                            && mItemArray.get(4).second.startsWith("Cover")){
                        DisplayScoreFragment displayScoreFragment = new DisplayScoreFragment();
                        FragmentTransaction fragmentTransaction = getFragmentManager ().beginTransaction();
                        fragmentTransaction.add(displayScoreFragment, "displayScoreFragment")
                                .replace(R.id.fragment_container, displayScoreFragment)
                                .addToBackStack("displayScoreFragment")
                                .commit();

                    } else{
                        Toast.makeText(mDragListView.getContext(), "Mali order ng steps bobo!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

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
            shuffleArray();

            checkAnswerBT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mItemArray.get(0).second.startsWith("Wash") && mItemArray.get(1).second.startsWith("Apply")
                            && mItemArray.get(2).second.startsWith("Clean") && mItemArray.get(3).second.startsWith("Consult")){
                        DisplayScoreFragment displayScoreFragment = new DisplayScoreFragment();
                        FragmentTransaction fragmentTransaction = getFragmentManager ().beginTransaction();
                        fragmentTransaction.add(displayScoreFragment, "displayScoreFragment")
                                .replace(R.id.fragment_container, displayScoreFragment)
                                .addToBackStack("displayScoreFragment")
                                .commit();

                    } else{
                        Toast.makeText(mDragListView.getContext(), "Mali order ng steps bobo!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

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
            shuffleArray();
            checkAnswerBT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mItemArray.get(0).second.startsWith("Wash") && mItemArray.get(1).second.startsWith("Clean")
                            && mItemArray.get(2).second.startsWith("Apply") && mItemArray.get(3).second.startsWith("Cover")){
                        DisplayScoreFragment displayScoreFragment = new DisplayScoreFragment();
                        FragmentTransaction fragmentTransaction = getFragmentManager ().beginTransaction();
                        fragmentTransaction.add(displayScoreFragment, "displayScoreFragment")
                                .replace(R.id.fragment_container, displayScoreFragment)
                                .addToBackStack("displayScoreFragment")
                                .commit();

                    } else{
                        Toast.makeText(mDragListView.getContext(), "Mali order ng steps bobo!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    public void shuffleArray(){
        Collections.shuffle(mItemArray);
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

                    if (Long.valueOf(toPosition).equals((Long)(mItemArray.get(toPosition).first))) {
                        // This is the correct position
                        Toast.makeText(mDragListView.getContext(), "Correct position", Toast.LENGTH_SHORT).show();
                    } else {
                        // This is not the correct position
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
