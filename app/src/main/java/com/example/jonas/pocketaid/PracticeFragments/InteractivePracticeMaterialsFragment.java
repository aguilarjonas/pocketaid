package com.example.jonas.pocketaid.PracticeFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jonas.pocketaid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InteractivePracticeMaterialsFragment extends Fragment {

     View rootView;
     ImageView imageView_1 ;
     ImageView imageView_2 ;
     ImageView imageView_3 ;
     ImageView imageView_4 ;
     ImageView imageView_5 ;
     ImageView imageView_6 ;
     Button nextButton;



    public InteractivePracticeMaterialsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        String chosenPractice = getArguments().getString("chosenPractice");
        rootView = (View) inflater.inflate(R.layout.fragment_interactive_practice_materials, container, false);

        setImages(rootView);
        chosenPracticeChooser(chosenPractice);

        return rootView;

    }

    public View setImages(View rootView){
        imageView_1 = (ImageView) rootView.findViewById(R.id.imageView_pic1);
        imageView_2 = (ImageView) rootView.findViewById(R.id.imageView_pic2);
        nextButton =  (Button) rootView.findViewById(R.id.button_practice_next);

        nextButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

            }
        });

        return rootView;
    }




    public void chosenPracticeChooser(String chosenPractice){

        if (chosenPractice == "Abrasion"){
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_LONG).show();
            abrasionPractice();
        }

        else if (chosenPractice == "Bites (Animal)"){
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_LONG).show();

        }

        else if (chosenPractice == "Bites (Insect)"){
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_LONG).show();

        }

        else if (chosenPractice == "Burns (1st & 2nd Degree)"){
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_LONG).show();

        }

        else if (chosenPractice == "Burns (3rd Degree)"){
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_LONG).show();

        }

        else if (chosenPractice == "Concussion"){
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_LONG).show();

        }

        else if (chosenPractice == "Contusion"){
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_LONG).show();

        }

        else if (chosenPractice == "Fracture"){
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_LONG).show();

        }

        else if (chosenPractice == "Laceration (Major)"){
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_LONG).show();

        }

        else if (chosenPractice == "Laceration (Minor)"){
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_LONG).show();

        }

        else if (chosenPractice == "Puncture (Severe Bleeding)"){
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_LONG).show();

        }

        else if (chosenPractice == "Puncture (Slightly Bleeding)"){
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_LONG).show();

        }

        //return rootView;
    }

    public void abrasionPractice (){
        imageView_1.setImageResource(R.drawable.ic_laceration);

    }





}
