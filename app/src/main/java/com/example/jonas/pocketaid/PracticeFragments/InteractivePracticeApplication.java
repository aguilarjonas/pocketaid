package com.example.jonas.pocketaid.PracticeFragments;


import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.jonas.pocketaid.MainActivity;
import com.example.jonas.pocketaid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InteractivePracticeApplication extends Fragment {

    ViewGroup rootView;
    TouchImage applicationImage;
    Button btTrigger;
    Paint paint;
    public InteractivePracticeApplication() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_interactive_practice_application, container, false);
        //changes menu button to Up or Back button
        ((MainActivity) getActivity()).resetActionBar(true, DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btTrigger = (Button) rootView.findViewById(R.id.triggerButton);

        addTouchListener();
        getTrigger();
        return rootView;
    }

    private void addTouchListener(){
        applicationImage = (TouchImage) rootView.findViewById(R.id.touchImage);
        applicationImage.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float x = event.getX();
                float y = event.getY();

                String message = String.format("Coordinates: %.2f, %.2f", x, y);
                Toast.makeText(getActivity().getApplicationContext(), message , Toast.LENGTH_SHORT).show();

                return false;
            }
        });

    }

    public void getTrigger(){
        btTrigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity().getApplicationContext(), "PININDOT MO YUNG SUGAT", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
