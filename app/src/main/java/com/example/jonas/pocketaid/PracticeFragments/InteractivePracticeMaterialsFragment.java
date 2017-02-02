package com.example.jonas.pocketaid.PracticeFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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

     ImageView imageView_1_check;
     ImageView imageView_2_check;
     ImageView imageView_3_check;
     ImageView imageView_4_check;
     ImageView imageView_5_check;
     ImageView imageView_6_check;

    private String injuryType;

    Button nextButton;

    public InteractivePracticeMaterialsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final String chosenPractice = getArguments().getString("chosenPractice");
        rootView = (View) inflater.inflate(R.layout.fragment_interactive_practice_materials, container, false);

        setImages(rootView);
        chosenPracticeChooser(chosenPractice);

        //Bundle mo na lang dito, Angel.

        nextButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                chosenPracticeChooser(chosenPractice);
                ListFragment listFrag = new ListFragment();
                Bundle args = new Bundle();
                args.putString("chosenInjury", injuryType);
                listFrag.setArguments(args);

                FragmentTransaction fragmentTransaction = getFragmentManager ().beginTransaction();
                fragmentTransaction.add(listFrag, "listFrag")
                        .replace(R.id.fragment_container, listFrag)
                        .addToBackStack("listFrag")
                        .commit();
            }
        });
        return rootView;

    }

    public View setImages(View rootView){
        imageView_1 = (ImageView) rootView.findViewById(R.id.imageView_pic1);
        imageView_2 = (ImageView) rootView.findViewById(R.id.imageView_pic2);
        imageView_3 = (ImageView) rootView.findViewById(R.id.imageView_pic3);
        imageView_4 = (ImageView) rootView.findViewById(R.id.imageView_pic4);
        imageView_5 = (ImageView) rootView.findViewById(R.id.imageView_pic5);
        imageView_6 = (ImageView) rootView.findViewById(R.id.imageView_pic6);

        imageView_1_check = (ImageView) rootView.findViewById(R.id.imageView1_check);
        imageView_2_check = (ImageView) rootView.findViewById(R.id.imageView2_check);
        imageView_3_check = (ImageView) rootView.findViewById(R.id.imageView3_check);
        imageView_4_check = (ImageView) rootView.findViewById(R.id.imageView4_check);
        imageView_5_check = (ImageView) rootView.findViewById(R.id.imageView5_check);
        imageView_6_check = (ImageView) rootView.findViewById(R.id.imageView6_check);


        nextButton =  (Button) rootView.findViewById(R.id.button_practice_next);
        return rootView;
    }


    public void chosenPracticeChooser(String chosenPractice){

        if (chosenPractice == "Abrasion"){
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_LONG).show();
            abrasionPractice();
            injuryType = "Abrasion";
        } else if (chosenPractice == "Bites (Animal)"){
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_LONG).show();
            injuryType = "Bites (Animal)";
        } else if (chosenPractice == "Bites (Insect)"){
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_LONG).show();
            injuryType = "Bites (Insect)";
        } else if (chosenPractice == "Burns (1st & 2nd Degree)"){
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_LONG).show();
            injuryType = "Burns (1st & 2nd Degree)";
        } else if (chosenPractice == "Burns (3rd Degree)"){
            injuryType = "Burns (3rd Degree)";
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_LONG).show();
        } else if (chosenPractice == "Concussion"){
            injuryType = "Concussion";
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_LONG).show();
        } else if (chosenPractice == "Contusion"){
            injuryType = "Contusion";
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_LONG).show();
        } else if (chosenPractice == "Fracture"){
            injuryType = "Fracture";
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_LONG).show();
        } else if (chosenPractice == "Laceration (Major)"){
            injuryType = "Laceration (Major)";
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_LONG).show();
        } else if (chosenPractice == "Laceration (Minor)"){
            injuryType = "Laceration (Minor)";
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_LONG).show();
        } else if (chosenPractice == "Puncture (Severe Bleeding)"){
            injuryType = "Puncture (Severe Bleeding)";
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_LONG).show();
        } else if (chosenPractice == "Puncture (Slightly Bleeding)"){
            injuryType = "Puncture (Slightly Bleeding)";
            Toast.makeText(getActivity().getApplicationContext(), chosenPractice, Toast.LENGTH_LONG).show();
        }
    }

    public void abrasionPractice (){

        //Set the materials Image here.
        imageView_1.setImageResource(R.drawable.ic_laceration);
        imageView_2.setImageResource(R.drawable.ic_concussion);
        imageView_3.setImageResource(R.drawable.ic_bites);
        imageView_4.setImageResource(R.drawable.ic_laceration);
        imageView_5.setImageResource(R.drawable.ic_fracture);
        imageView_6.setImageResource(R.drawable.ic_insect);

        imageView_1_check.setVisibility(View.INVISIBLE);
        imageView_2_check.setVisibility(View.INVISIBLE);
        imageView_3_check.setVisibility(View.INVISIBLE);
        imageView_4_check.setVisibility(View.INVISIBLE);
        imageView_5_check.setVisibility(View.INVISIBLE);
        imageView_6_check.setVisibility(View.INVISIBLE);

        imageView_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "Tickled Image 1", Toast.LENGTH_LONG).show();

                if (imageView_1_check.getVisibility() == View.VISIBLE){
                    imageView_1_check.setVisibility(View.INVISIBLE);
                }

                else if (imageView_1_check.getVisibility() == View.INVISIBLE){
                    imageView_1_check.setVisibility(View.VISIBLE);
                }

            }
        });

        imageView_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "Tickled Image 2", Toast.LENGTH_LONG).show();

                if (imageView_2_check.getVisibility() == View.VISIBLE){
                    imageView_2_check.setVisibility(View.INVISIBLE);
                }

                else if (imageView_2_check.getVisibility() == View.INVISIBLE){
                    imageView_2_check.setVisibility(View.VISIBLE);
                }

            }
        });

        imageView_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "Tickled Image 3", Toast.LENGTH_LONG).show();

                if (imageView_3_check.getVisibility() == View.VISIBLE){
                    imageView_3_check.setVisibility(View.INVISIBLE);
                }

                else if (imageView_3_check.getVisibility() == View.INVISIBLE){
                    imageView_3_check.setVisibility(View.VISIBLE);
                }

            }
        });

        imageView_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "Tickled Image 4", Toast.LENGTH_LONG).show();

                if (imageView_4_check.getVisibility() == View.VISIBLE){
                    imageView_4_check.setVisibility(View.INVISIBLE);
                }

                else if (imageView_4_check.getVisibility() == View.INVISIBLE){
                    imageView_4_check.setVisibility(View.VISIBLE);
                }

            }
        });

        imageView_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "Tickled Image 5", Toast.LENGTH_LONG).show();

                if (imageView_5_check.getVisibility() == View.VISIBLE){
                    imageView_5_check.setVisibility(View.INVISIBLE);
                }

                else if (imageView_5_check.getVisibility() == View.INVISIBLE){
                    imageView_5_check.setVisibility(View.VISIBLE);
                }

            }
        });

        imageView_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "Tickled Image 6", Toast.LENGTH_LONG).show();

                if (imageView_6_check.getVisibility() == View.VISIBLE){
                    imageView_6_check.setVisibility(View.INVISIBLE);
                }

                else if (imageView_6_check.getVisibility() == View.INVISIBLE){
                    imageView_6_check.setVisibility(View.VISIBLE);
                }

            }
        });




    }





}
