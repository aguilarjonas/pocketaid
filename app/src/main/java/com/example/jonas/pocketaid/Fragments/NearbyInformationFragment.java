package com.example.jonas.pocketaid.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jonas.pocketaid.R;


public class NearbyInformationFragment extends Fragment {

    private TextView hospitalNameHolder;
    private TextView hospitalPlaceIDHolder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_nearby_hospital_information, container, false);
//        rootView.requestFocus();

        hospitalNameHolder = (TextView)v.findViewById(R.id.textview_hospitalnameinfo);
        hospitalPlaceIDHolder = (TextView)v.findViewById(R.id.textview_placeidinfo);
        String hospitalName = getArguments().getString("chosenHospital");
        String hospitalPlaceID = getArguments().getString("chosenHospitalPlaceID");
        putTheText(hospitalName, hospitalPlaceID);


//        Toast.makeText(getActivity().getApplicationContext(), hospitalName , Toast.LENGTH_LONG).show();


        // Inflate the layout for this fragment
        return v;
    }

    public void putTheText(String hospitalName, String hospitalPlaceID){
        hospitalNameHolder.setText(hospitalName);
        hospitalPlaceIDHolder.setText(hospitalPlaceID);
    }


}
