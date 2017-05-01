package com.pocketaid.jonas.pocketaid.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.pocketaid.jonas.pocketaid.MainActivity;
import com.pocketaid.jonas.pocketaid.R;

import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    private Spinner spinnerLanguage;
    private Button buttonLanguage;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity)getActivity()).setActionBarTitle(getResources().getString(R.string.setting));
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_settings, container, false);

        spinnerLanguage = (Spinner) rootView.findViewById(R.id.spinner_language);
        buttonLanguage = (Button) rootView.findViewById(R.id.button_language);

        spinnerLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Locale locale;

                if(position == 0) {
                    getResources().getConfiguration().locale = (Locale.ENGLISH);
                } else if(position == 1) {
                    locale = new Locale("tl");
                    getResources().getConfiguration().locale = locale;
                }

                getResources().updateConfiguration(getResources().getConfiguration(), null);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buttonLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                Intent restart = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                startActivity(restart);
            }
        });

        return rootView;
    }
}
