package com.example.jonas.pocketaid.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.jonas.pocketaid.Adapters.InjuryListAdapter;
import com.example.jonas.pocketaid.MainActivity;
import com.example.jonas.pocketaid.R;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class InjuriesFragment extends Fragment {

    private ListView listView;
    ArrayAdapter<String> adapter;
    EditText searchEditText;
    ArrayList<HashMap<String, String>> injuryList;


    public InjuriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity)getActivity()).setActionBarTitle("Injuries");
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_injuries, container, false);

        //list of injuries
        String injuries[] = {
                getResources().getString(R.string.fracture),
                getResources().getString(R.string.burns),
                getResources().getString(R.string.bites),
                getResources().getString(R.string.concussion),
                getResources().getString(R.string.contusion),
                getResources().getString(R.string.laceration)
        };

        Integer[] imageId = {
                R.drawable.ic_menu_camera,
                R.drawable.ic_menu_gallery,
                R.drawable.ic_menu_manage,
                R.drawable.ic_menu_send,
                R.drawable.ic_menu_share,
                R.drawable.ic_menu_slideshow
        };

        listView = (ListView) rootView.findViewById(R.id.injuries_listview);
        searchEditText = (EditText) rootView.findViewById(R.id.injuries_search);

        //for searching injuries
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        adapter = new InjuryListAdapter(getActivity(), injuries, imageId);
        listView.setAdapter(adapter);

        return rootView;
    }

}
