package com.example.jonas.pocketaid.PracticeFragments;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jonas.pocketaid.InteractiveModules.InteractiveModel;
import com.example.jonas.pocketaid.MainActivity;
import com.example.jonas.pocketaid.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class InteractivePracticeApplication extends Fragment implements View.OnTouchListener {

    ViewGroup rootView;
    ImageView applicationImage;
    TextView tvTrigger;
    Paint paint;
    RelativeLayout relativeLayout_Touch;

    ImageView material1, material2, material3, material4, material5;

    boolean hasClickTrigger = false;
    ArrayList<Integer> whichMaterial = new ArrayList<Integer>();
    boolean changeImage = false;
    boolean isItDone = false;
    boolean nextStep = false;

    int numberOfMaterials = 0;

    int imageNumber = 1;
    int whatsNext = 1;

    String chosenInjury = "";

    boolean justStarted = true;

    InteractiveModel interModel;
    int numberOfErrors = 0;
    int numberOfTries = 0;
    int numberOfCorrect = 0;

    Button buttonNext;

    ArrayList<Integer> correctMaterials = new ArrayList<Integer>();

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

        interModel = InteractiveModel.getInstance();

        numberOfCorrect = interModel.getStage3Stats().get(0);
        numberOfErrors = interModel.getStage3Stats().get(1);
        numberOfTries = interModel.getStage3Stats().get(2);


        correctMaterials = interModel.getCorrectMaterials();

        Log.e("TESTING", correctMaterials.get(0) + " " + correctMaterials.get(1) + " " + correctMaterials.get(2));


        numberOfMaterials = interModel.getNumberOfMaterials();
        chosenInjury = getArguments().getString("chosenInjury");
        clearValues();
        initializeElements();
        setVisibilityForMaterials(numberOfMaterials);
        getTrigger();
        setImagesForMaterials(chosenInjury);

        return rootView;
    }

    public void setImagesForMaterials(String chosenInjury){

        if (chosenInjury.equals("Abrasion") || chosenInjury.equals("Gasgas")){
            //3
            material1.setImageResource(correctMaterials.get(0));
            material2.setImageResource(correctMaterials.get(1));
            material3.setImageResource(correctMaterials.get(2));

        }

        else if (chosenInjury.equals("Fracture") || chosenInjury.equals("Bali")){
            //3
            material1.setImageResource(correctMaterials.get(0));
            material2.setImageResource(correctMaterials.get(1));
            material3.setImageResource(correctMaterials.get(2));
        }

        else if (chosenInjury.equals("Contusion") || chosenInjury.equals("Pasa")){
            //2
            material1.setImageResource(correctMaterials.get(0));
            material2.setImageResource(correctMaterials.get(1));
        }

        else if (chosenInjury.equals("Concussion") || chosenInjury.equals("Umpog")){
            //2
            material1.setImageResource(correctMaterials.get(0));
            material2.setImageResource(correctMaterials.get(1));
        }

        else if (chosenInjury.equals("3rd Degree Burns")){
            //0
        }

        else if (chosenInjury.equals("Chemical Burns") || chosenInjury.equals("Paso dulot ng kemikal")){
            //2
            material1.setImageResource(correctMaterials.get(0));
            material2.setImageResource(correctMaterials.get(1));
        }

        else if (chosenInjury.equals("Thermal Burns") || chosenInjury.equals("Paso")){
            //3
            material1.setImageResource(correctMaterials.get(0));
            material2.setImageResource(correctMaterials.get(1));
//            material3.setImageResource(correctMaterials.get(2));
        }

        else if (chosenInjury.equals("Insect Bites") || chosenInjury.equals("Kagat ng insekto")){
            //3
            material1.setImageResource(correctMaterials.get(0));
            material2.setImageResource(correctMaterials.get(1));
            material3.setImageResource(correctMaterials.get(2));
        }

        else if (chosenInjury.equals("Animal Bites") || chosenInjury.equals("Kagat ng hayop")){
            //1
            material1.setImageResource(correctMaterials.get(0));
        }

        else if (chosenInjury.equals("Puncture(Slight Bleeding)") || chosenInjury.equals("Hindi malubhang laslas")){
            //3
            material1.setImageResource(correctMaterials.get(0));
            material2.setImageResource(correctMaterials.get(1));
            material3.setImageResource(correctMaterials.get(2));
        }

        else if (chosenInjury.equals("Puncture(Severe Bleeding)") || chosenInjury.equals("Tusok(Malubhang pagdurugo)")){
            //3
            material1.setImageResource(correctMaterials.get(0));
            material2.setImageResource(correctMaterials.get(1));
            material3.setImageResource(correctMaterials.get(2));
        }

        else if (chosenInjury.equals("Laceration(Minor)") || chosenInjury.equals("Hindi malubhang laslas")){
            //3
            material1.setImageResource(correctMaterials.get(0));
            material2.setImageResource(correctMaterials.get(1));
//            material3.setImageResource(correctMaterials.get(2));
        }

        else if (chosenInjury.equals("Laceration(Major)") || chosenInjury.equals("Malubhang laslas")){
            //3
            material1.setImageResource(correctMaterials.get(0));
            material2.setImageResource(correctMaterials.get(1));
            material3.setImageResource(correctMaterials.get(2));
        }
    }



    public void getTrigger(){
        tvTrigger.setOnTouchListener(this);
        material1.setOnTouchListener(this);
        material2.setOnTouchListener(this);
        material3.setOnTouchListener(this);
        buttonNext.setOnTouchListener(this);
    }

    public void setVisibilityForMaterials(int numberOfMaterials){

        if (numberOfMaterials == 0){
            material1.setVisibility(View.GONE);
            material2.setVisibility(View.GONE);
            material3.setVisibility(View.GONE);
//            material4.setVisibility(View.GONE);
        }
        else if (numberOfMaterials == 1){
            material2.setVisibility(View.GONE);
            material3.setVisibility(View.GONE);
//            material4.setVisibility(View.GONE);
        }

        else if (numberOfMaterials == 2){
            material3.setVisibility(View.GONE);
//            material4.setVisibility(View.GONE);
        }

//        else if (numberOfMaterials == 3){
//            material4.setVisibility(View.GONE);
//        }
    }

    public void initializeElements(){

        material1 = (ImageView) rootView.findViewById(R.id.iv_application_material1);
        material2 = (ImageView) rootView.findViewById(R.id.iv_application_material2);
        material3 = (ImageView) rootView.findViewById(R.id.iv_application_material3);


        relativeLayout_Touch = (RelativeLayout) rootView.findViewById(R.id.relativeLayout_Touch);
        relativeLayout_Touch.addView(new MyView(getActivity().getApplicationContext()));
        tvTrigger = (TextView) rootView.findViewById(R.id.triggerTextview);
        buttonNext = (Button) rootView.findViewById(R.id.button_next_application);
    }

    public void clearValues(){

        hasClickTrigger = false;
        whichMaterial.add(0, 0);
        whichMaterial.add(1, 0);
        whatsNext = 1;
        changeImage = false;
        isItDone = false;
        nextStep = false;
        justStarted = true;
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch(v.getId()){
            case R.id.triggerTextview:
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
//                        Toast.makeText(getActivity().getApplicationContext(), "PININDOT MO YUNG SUGAT", Toast.LENGTH_SHORT).show();
                        Log.e("SUGAT:", "ANDITO AKO SA SUGAT");
                        hasClickTrigger = true;

                    case MotionEvent.ACTION_UP:
                        if (whichMaterial.get(1) == 1 && nextStep == true && whatsNext == 1 && justStarted == true){
                            Log.e("TAMA KA:", "MAY TAMA KA ... SA ULO");
//                            whichMaterial = 2;
                            changeImage = true;
                            whatsNext = 2;
                            justStarted = false;
                            nextStep = false;
                            if (numberOfMaterials == 1){
                                isItDone = true;
                            }
                        }

                        else if (whichMaterial.get(1) == 2 && nextStep == true && whatsNext == 2){
                            changeImage = true;
                            whatsNext = 3;
                            nextStep = false;


                            if (numberOfMaterials == 2){
                                isItDone = true;
                            }

                            Log.e("HELLO", "2nd");
                        }

                        else if (whichMaterial.get(1) == 3 && nextStep == true && whatsNext == 3){
                            changeImage = true;
                            nextStep = false;

//                            isItDone = true;
                            if (numberOfMaterials == 3){
                                isItDone = true;
                            }
                            Log.e("HELLO", "3nd");

                        }

                        else changeImage = false;
                }
                break;

            case R.id.iv_application_material1:
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
//                        Toast.makeText(getActivity().getApplicationContext(), "PININDOT MO YUNG GAMIT", Toast.LENGTH_SHORT).show();
                        if (whichMaterial.get(0) == 0 && justStarted == true){
                            whichMaterial.set(0, 0);
                            whichMaterial.set(1, 1);
                            nextStep = true;
                        }
                        Log.e("GAMIT:", "ANDITO AKO SA GAMIT11");
                }
                break;


            case R.id.iv_application_material2:
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
//                        Toast.makeText(getActivity().getApplicationContext(), "PININDOT MO YUNG GAMIT", Toast.LENGTH_SHORT).show();
                        if (whichMaterial.get(1) == 1 && whatsNext == 2){
                            whichMaterial.set(0, 0);
                            whichMaterial.set(1, 2);
                            nextStep = true;
                            Log.e("GAMIT:", "ANDITO AKO SA GAMIT22");

                        }
                }
                break;


            case R.id.iv_application_material3:
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
//                        Toast.makeText(getActivity().getApplicationContext(), "PININDOT MO YUNG GAMIT", Toast.LENGTH_SHORT).show();
                        if (whichMaterial.get(1) == 2 && whatsNext == 3){
                            whichMaterial.set(0, 0);
                            whichMaterial.set(1, 3);
                            nextStep = true;
                            Log.e("GAMIT:", "ANDITO AKO SA GAMIT33");

                        }


                }
                break;

            case R.id.button_next_application:
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
//                        Toast.makeText(getActivity().getApplicationContext(), "PININDOT MO YUNG GAMIT", Toast.LENGTH_SHORT).show();
                        DisplayScoreFragment displayScoreFragment = new DisplayScoreFragment();
                        FragmentTransaction fragmentTransaction = getFragmentManager ().beginTransaction();
                        fragmentTransaction.add(displayScoreFragment, "displayScoreFragment")
                                .replace(R.id.fragment_container, displayScoreFragment)
                                .addToBackStack("displayScoreFragment")
                                .commit();


                }


        }
        return false;
    }


    class MyView extends View{
        Paint paint = new Paint();
        Point point = new Point();
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.ic_applogo);
        boolean allDone = false;

        public MyView(Context context) {
            super(context);
            paint.setColor(Color.RED);
            paint.setStrokeWidth(15);
            paint.setStyle(Paint.Style.STROKE);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            Log.e("onDraw:", "onDraw");
            //Change this default to a transparent background

            if (imageNumber == 99){
                imageNumber = 1;

                if (chosenInjury.equals("Abrasion")){
                    b = BitmapFactory.decodeResource(getResources(), R.drawable.ic_ip_abrasion_four);
                }

                else if (chosenInjury.equals("Animal Bites")){
                    b= BitmapFactory.decodeResource(getResources(), R.drawable.ic_ip_animal_two_copy);
                }

                else if (chosenInjury.equals("Insect Bites")){
                    b= BitmapFactory.decodeResource(getResources(), R.drawable.ic_ip_insect_four_copy);
                }

                else if (chosenInjury.equals("Thermal Burns")){
                    b= BitmapFactory.decodeResource(getResources(), R.drawable.ic_ip_thermal_three_copy);
                }

                else if (chosenInjury.equals("Chemical Burns")){
                    b= BitmapFactory.decodeResource(getResources(), R.drawable.ic_ip_chemical_three_copy);
                }

                else if (chosenInjury.equals("Concussion")){
                    b= BitmapFactory.decodeResource(getResources(), R.drawable.ic_ip_concussion_two_copy);
                }

                else if (chosenInjury.equals("Contusion")){
                   b= BitmapFactory.decodeResource(getResources(), R.drawable.ic_ip_contusion_two_copy);
                }

                else if (chosenInjury.equals("Fracture")){
                    b= BitmapFactory.decodeResource(getResources(), R.drawable.ic_ip_fracture_three);
                }

                else if (chosenInjury.equals("Laceration(Major)")){
                    b= BitmapFactory.decodeResource(getResources(), R.drawable.ic_ip_major_lac_three_copy);

                }

                else if (chosenInjury.equals("Laceration(Minor)")){
                    b= BitmapFactory.decodeResource(getResources(), R.drawable.ic_ip_major_lac_three_copy);
                }

                else  if (chosenInjury.equals("Puncture(Severe Bleeding)")){
                    b= BitmapFactory.decodeResource(getResources(), R.drawable.ic_ip_puncture_three_copy);
                }

                else if (chosenInjury.equals("Puncture(Slight Bleeding)")){
                    b= BitmapFactory.decodeResource(getResources(), R.drawable.ic_ip_puncture_three_copy);
                }


                    allDone = true;

                interModel.setNumberOfError(numberOfErrors);
                interModel.setNumberOfTries(numberOfTries);
                interModel.setNumberOfCorrect(numberOfCorrect);

                interModel.assignThirdStageStats(numberOfCorrect, numberOfErrors, numberOfTries);
                buttonNext.setVisibility(View.VISIBLE);
            }

            else if (imageNumber != 99 && allDone == false){
                if (chosenInjury.equals("Abrasion")){

                    if (imageNumber == 1){
                        b= BitmapFactory.decodeResource(getResources(), R.drawable.ic_ip_abrasion_one);
                    }

                    else if (imageNumber == 2){
                        b= BitmapFactory.decodeResource(getResources(), R.drawable.ic_ip_abrasion_two);
                    }

                    else if (imageNumber == 3){
                        b= BitmapFactory.decodeResource(getResources(), R.drawable.ic_ip_abrasion_three);
                    }

                }

                else if (chosenInjury.equals("Fracture")){
                    if (imageNumber == 1){
                        b= BitmapFactory.decodeResource(getResources(), R.drawable.ic_ip_fracture_one);
                    }

                    else if (imageNumber == 2){
                        b= BitmapFactory.decodeResource(getResources(), R.drawable.ic_ip_fracture_two);
                    }


                }

                else if (chosenInjury.equals("Concussion")){
                    if (imageNumber == 1){
                        b= BitmapFactory.decodeResource(getResources(), R.drawable.ic_ip_concussion_one_copy);
                    }

//                    else if (imageNumber == 2){
//                        b= BitmapFactory.decodeResource(getResources(), R.drawable.);
//                    }
//
//                    else if (imageNumber == 3){
//                        b= BitmapFactory.decodeResource(getResources(), R.drawable.ic_ip_fracture_three);
//                    }
                }

                else if (chosenInjury.equals("Contusion")){
                    //Bawas isa
                    if (imageNumber == 1){
                        b= BitmapFactory.decodeResource(getResources(), R.drawable.ic_ip_contusion_one_copy);
                    }

                    else if (imageNumber == 2){
                        b= BitmapFactory.decodeResource(getResources(), R.drawable.ic_ip_contusion_two_copy);
                    }

//                    else if (imageNumber == 3){
//                        b= BitmapFactory.decodeResource(getResources(), R.drawable.ic_nearby);
//                    }
                }

                //Remove
                else if (chosenInjury.equals("3rd Degree Burns")){
                    if (imageNumber == 1){
                        b= BitmapFactory.decodeResource(getResources(), R.drawable.abrasion_1);
                    }

                    else if (imageNumber == 2){
                        b= BitmapFactory.decodeResource(getResources(), R.drawable.checkmark);
                    }

                    else if (imageNumber == 3){
                        b= BitmapFactory.decodeResource(getResources(), R.drawable.ic_nearby);
                    }
                }

                else if (chosenInjury.equals("Chemical Burns")){
                    if (imageNumber == 1){
                        b= BitmapFactory.decodeResource(getResources(), R.drawable.ic_ip_chemical_one_copy);
                    }

                    else if (imageNumber == 2){
                        b= BitmapFactory.decodeResource(getResources(), R.drawable.ic_ip_chemical_two_copy);
                    }

                }

                else if (chosenInjury.equals("Thermal Burns")){
                    if (imageNumber == 1){
                        b= BitmapFactory.decodeResource(getResources(), R.drawable.ic_ip_thermal_one_copy);
                    }

                    else if (imageNumber == 2){
                        b= BitmapFactory.decodeResource(getResources(), R.drawable.ic_ip_thermal_two_copy);
                    }
                }

                else if (chosenInjury.equals("Insect Bites")){
                    if (imageNumber == 1){
                        b= BitmapFactory.decodeResource(getResources(), R.drawable.ic_ip_insect_one_copy);
                    }

                    else if (imageNumber == 2){
                        b= BitmapFactory.decodeResource(getResources(), R.drawable.ic_ip_insect_two_copy);
                    }

                    else if (imageNumber == 3){
                        b= BitmapFactory.decodeResource(getResources(), R.drawable.ic_ip_insect_three_copy);

                    }

                }

                else if (chosenInjury.equals("Animal Bites")){
                    if (imageNumber == 1){
                        b= BitmapFactory.decodeResource(getResources(), R.drawable.ic_ip_animal_one_copy);
                    }

//                    else if (imageNumber == 2){
//                    }

//                    else if (imageNumber == 3){
//                        b= BitmapFactory.decodeResource(getResources(), R.drawable.ic_nearby);
//                    }
                }

                else if (chosenInjury.equals("Puncture(Slight Bleeding)")){
                    if (imageNumber == 1){
                        b= BitmapFactory.decodeResource(getResources(), R.drawable.ic_ip_puncture_one_copy);
                    }

                    else if (imageNumber == 2){
                        b= BitmapFactory.decodeResource(getResources(), R.drawable.ic_ip_puncture_two_copy);
                    }

//                    else if (imageNumber == 3){
//                        b= BitmapFactory.decodeResource(getResources(), R.drawable.ic_ip_puncture_three_copy);
//                    }
                }

                else  if (chosenInjury.equals("Puncture(Severe Bleeding)")){
                    if (imageNumber == 1){
                        b= BitmapFactory.decodeResource(getResources(), R.drawable.ic_ip_puncture_one_copy);
                    }

                    else if (imageNumber == 2){
                        b= BitmapFactory.decodeResource(getResources(), R.drawable.ic_ip_puncture_two_copy);
                    }

//                    else if (imageNumber == 3){
//                    }
                }

                else if (chosenInjury.equals("Laceration(Minor)")){
                    if (imageNumber == 1){
                        b= BitmapFactory.decodeResource(getResources(), R.drawable.ic_ip_major_lac_one_copy);
                    }

                    else if (imageNumber == 2){
                        b= BitmapFactory.decodeResource(getResources(), R.drawable.ic_ip_major_lac_two_copy);
                    }

//                    else if (imageNumber == 3){
//                    }
                }

                else if (chosenInjury.equals("Laceration(Major)")){
                    if (imageNumber == 1){
                        b= BitmapFactory.decodeResource(getResources(), R.drawable.ic_ip_major_lac_one_copy);
                    }

                    else if (imageNumber == 2){
                        b= BitmapFactory.decodeResource(getResources(), R.drawable.ic_ip_major_lac_two_copy);
                    }
//
//                    else if (imageNumber == 3){
//                        b= BitmapFactory.decodeResource(getResources(), R.drawable.ic_ip_major_lac_three_copy);
//                    }
                }
            }


            canvas.drawBitmap(b, 0, 0, paint);
            canvas.drawCircle(point.x, point.y, 100, paint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    numberOfTries++;
                    point.x = event.getX();
                    point.y = event.getY();
//                    Toast.makeText(getActivity().getApplicationContext(), "PININDOT MO YUNG PICTURE", Toast.LENGTH_SHORT).show();
                    Log.e("PICTURE:", "ANDITO AKO SA PICTURE");

                    if (hasClickTrigger == true && changeImage == true){
                        numberOfCorrect++;
                        Log.e("PICTURE:", "HEHEHE and galing ko");
                        if (isItDone == true){
                            imageNumber = 99;
                            Log.e("IMAGE NUMBER:", "99");
                        }

                        else {
                            hasClickTrigger = false;
                            changeImage = false;
                            imageNumber ++;
                        }
                    }

                    else{
                        numberOfErrors++;
                        Log.e("PICTURE:", "HEHEHE di ka magaling");
                    }
            }

            invalidate();
            return true;
        }
    }
    class Point {
        float x, y;
    }
}


