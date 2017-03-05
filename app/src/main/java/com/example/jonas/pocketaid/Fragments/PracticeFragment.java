package com.example.jonas.pocketaid.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
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

    //list of injuries
//    String[] injuries;

    String[] injuries = {"Abrasion", "Animal Bites", "Insect Bites", "Thermal Burns", "Chemical Burns",
            "Concussion", "Contusion", "Fracture",
            "Major Laceration", "Minor Laceration", "Puncture (Severe Bleeding)", "Puncture (Slightly Bleeding)"
    };

    //icons
    Integer[] icon = {R.drawable.ic_abrasion, R.drawable.ic_bites, R.drawable.ic_insect, R.drawable.ic_thermal, R.drawable.ic_chemical,
                        R.drawable.ic_concussion, R.drawable.ic_contusion,
                        R.drawable.ic_fracture, R.drawable.ic_laceration,
                        R.drawable.ic_laceration, R.drawable.ic_severe, R.drawable.ic_slight
    };


    public PracticeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        displayIntro();

        // Inflate the layout for this fragment
        ((MainActivity)getActivity()).setActionBarTitle("Interactive Practice");
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_practice, container, false);

        //unlocks menu bar or drawer
        ((MainActivity)getActivity()).resetActionBar(false, DrawerLayout.LOCK_MODE_UNLOCKED);
        ((MainActivity)getActivity()).hideOrShowFAB("hide");
//        injuries = getResources().getStringArray(R.array.injuries_cards); ETO YUNG PART NA BINAGO KO
        recyclerView = (RecyclerView) rootView.findViewById(R.id.practice_injury_list);
        adapter = new InjuryListPracticeAdapter(getActivity(), injuries, icon);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    public void displayIntro(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(getResources().getString(R.string.interactive_practice_intro_title));
        builder.setMessage(getResources().getString(R.string.interactive_practice_intro));
        builder.setPositiveButton("Ok", null);
        builder.show();
    }
}
