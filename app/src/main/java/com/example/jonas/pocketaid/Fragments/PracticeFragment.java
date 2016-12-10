package com.example.jonas.pocketaid.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jonas.pocketaid.MainActivity;
import com.example.jonas.pocketaid.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PracticeFragment extends Fragment {


    public PracticeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity)getActivity()).setActionBarTitle("Interactive Practice");
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_practice, container, false);

        return rootView;
    }

}
