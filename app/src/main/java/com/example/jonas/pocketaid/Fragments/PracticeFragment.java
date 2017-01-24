package com.example.jonas.pocketaid.Fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jonas.pocketaid.Adapters.InjuryListPracticeAdapter;
import com.example.jonas.pocketaid.MainActivity;
import com.example.jonas.pocketaid.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PracticeFragment extends Fragment {

    private RecyclerView recyclerView;
    private InjuryListPracticeAdapter adapter;
    private FloatingActionButton fab;

    //list of injuries
    String[] injuries = { "Abrasion", "Bites (Animal)", "Bites (Insect)", "Burns (1st & 2nd Degree)",
                            "Burns (3rd Degree)", "Concussion", "Contusion", "Fracture",
                            "Laceration (Major)", "Laceration (Minor)", "Puncture (Severe Bleeding)", "Puncture (Slightly Bleeding)"
    };

    //icons
    Integer[] icon = { R.drawable.ic_abrasion, R.drawable.ic_bites, R.drawable.ic_insect, R.drawable.ic_burns,
                        R.drawable.ic_burns, R.drawable.ic_concussion,
                        R.drawable.ic_contusion, R.drawable.ic_fracture, R.drawable.ic_laceration,
                        R.drawable.ic_laceration, R.drawable.ic_puncture, R.drawable.ic_puncture
    };


    public PracticeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity)getActivity()).setActionBarTitle("Interactive Practice");
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_practice, container, false);
        ((MainActivity)getActivity()).hideOrShowFAB("hide");

        recyclerView = (RecyclerView) rootView.findViewById(R.id.practice_injury_list);
        adapter = new InjuryListPracticeAdapter(getActivity(), injuries, icon);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        return rootView;
    }

}
