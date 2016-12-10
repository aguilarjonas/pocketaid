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
public class NearbyFragment extends Fragment {


    public NearbyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity)getActivity()).setActionBarTitle("Nearby Hospitals");
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_nearby, container, false);

        return rootView;
    }

}
