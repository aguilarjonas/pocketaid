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

import com.example.jonas.pocketaid.InteractiveModules.InteractiveModel;
import com.example.jonas.pocketaid.MainActivity;
import com.example.jonas.pocketaid.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class InteractivePracticeApplication extends Fragment implements View.OnTouchListener {

    ViewGroup rootView;
    TextView tvTrigger;
    ImageView imageview_back;

    RelativeLayout relativeLayout_Touch;

    ImageView material1, material2, material3;

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
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_interactive_practice_application, container, false);
        ((MainActivity) getActivity()).resetActionBar(true, DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        interModel = InteractiveModel.getInstance();

        numberOfCorrect = interModel.getStage3Stats().get(0);
        numberOfErrors = interModel.getStage3Stats().get(1);
        numberOfTries = interModel.getStage3Stats().get(2);

        correctMaterials = interModel.getCorrectMaterials();

        numberOfMaterials = interModel.getNumberOfMaterials();
        chosenInjury = getArguments().getString("chosenInjury");
        clearValues();
        initializeElements();
        setVisibilityForMaterials(numberOfMaterials);
        getTrigger();
        setImagesForMaterials(chosenInjury);

        return rootView;
    }

     /*
        Function Name : clearValues
        Function Description : This function will clear all the values so that the user can
                               try again the Interactive Practice - Application if the user goes back
                               from score fragment.
        Function Developer : Raeven Bauto
     */

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

    /*
       Function Name : initializeElements
       Function Description : This function will initialize the views from the XML
       Function Developer : Raeven Bauto
    */
    public void initializeElements(){

        material1 = (ImageView) rootView.findViewById(R.id.iv_application_material1);
        material2 = (ImageView) rootView.findViewById(R.id.iv_application_material2);
        material3 = (ImageView) rootView.findViewById(R.id.iv_application_material3);

        imageview_back = (ImageView) rootView.findViewById(R.id.imageview_back);
        relativeLayout_Touch = (RelativeLayout) rootView.findViewById(R.id.relativeLayout_Touch);
        relativeLayout_Touch.addView(new MyView(getActivity().getApplicationContext()));
        tvTrigger = (TextView) rootView.findViewById(R.id.triggerTextview);
        buttonNext = (Button) rootView.findViewById(R.id.button_next_application);
    }

      /*
       Function Name : setVisibilityForMaterials
       Function Description : This function will set the visibility of the materials used and not used.
       Function Developer : Raeven Bauto
    */

    public void setVisibilityForMaterials(int numberOfMaterials){

        if (numberOfMaterials == 0){
            material1.setVisibility(View.GONE);
            material2.setVisibility(View.GONE);
            material3.setVisibility(View.GONE);
        }
        else if (numberOfMaterials == 1){
            material2.setVisibility(View.GONE);
            material3.setVisibility(View.GONE);
        }

        else if (numberOfMaterials == 2){
            material3.setVisibility(View.GONE);
        }
    }

    /*
        Function Name : getTrigger
        Function Description : This function will set the touch listeners of all the triggers.
        Function Developer : Raeven Bauto
    */
    public void getTrigger(){
        tvTrigger.setOnTouchListener(this);
        material1.setOnTouchListener(this);
        material2.setOnTouchListener(this);
        material3.setOnTouchListener(this);
        buttonNext.setOnTouchListener(this);
    }

    /*
        Function Name : setImagesForMaterials
        Function Description : This function will set the images chosen in the Interactive Practice
                               -Application
        Function Developer : Raeven Bauto
     */
    public void setImagesForMaterials(String chosenInjury){

        if (chosenInjury.equals("Abrasion") || chosenInjury.equals("Gasgas")){
            material1.setImageResource(correctMaterials.get(0));
            material2.setImageResource(correctMaterials.get(1));
            material3.setImageResource(correctMaterials.get(2));
            int height = 150;
            int width = 150;
            tvTrigger.getLayoutParams().height = height;
            tvTrigger.getLayoutParams().width = width;
        }

        else if (chosenInjury.equals("Fracture") || chosenInjury.equalsIgnoreCase("Baling Buto")){
            material1.setImageResource(correctMaterials.get(0));
            material2.setImageResource(correctMaterials.get(1));
            material3.setImageResource(correctMaterials.get(2));
            tvTrigger.setRotation(340);
            int height = 70;
            int width = 250;
            tvTrigger.getLayoutParams().height = height;
            tvTrigger.getLayoutParams().width = width;

        }

        else if (chosenInjury.equals("Contusion") || chosenInjury.equals("Pasa")){
            material1.setImageResource(correctMaterials.get(0));
            tvTrigger.setRotation(310);
            int height = 75;
            int width = 320;
            tvTrigger.getLayoutParams().height = height;
            tvTrigger.getLayoutParams().width = width;
        }

        else if (chosenInjury.equals("Concussion") || chosenInjury.equals("Untog")){
            material1.setImageResource(correctMaterials.get(0));
        }

        else if (chosenInjury.equals("Chemical Burns") || chosenInjury.equalsIgnoreCase("Paso dulot ng kemikal")){
            material1.setImageResource(correctMaterials.get(0));
            material2.setImageResource(correctMaterials.get(1));
            tvTrigger.setRotation(330);
            int height = 130;
            int width = 220;
            tvTrigger.getLayoutParams().height = height;
            tvTrigger.getLayoutParams().width = width;
        }

        else if (chosenInjury.equals("Thermal Burns") || chosenInjury.equals("Paso")){
            material1.setImageResource(correctMaterials.get(0));
            material2.setImageResource(correctMaterials.get(1));
            tvTrigger.setRotation(323);
            int height = 150;
            int width = 350;
            tvTrigger.getLayoutParams().height = height;
            tvTrigger.getLayoutParams().width = width;
        }

        else if (chosenInjury.equals("Insect Bites") || chosenInjury.equalsIgnoreCase("Kagat ng insekto")){
            material1.setImageResource(correctMaterials.get(0));
            material2.setImageResource(correctMaterials.get(1));
            material3.setImageResource(correctMaterials.get(2));
            tvTrigger.setRotation(320);
            int height = 110;
            int width = 200;
            tvTrigger.getLayoutParams().height = height;
            tvTrigger.getLayoutParams().width = width;
        }

        else if (chosenInjury.equals("Animal Bites") || chosenInjury.equalsIgnoreCase("Kagat ng hayop")){
            material1.setImageResource(correctMaterials.get(0));
            tvTrigger.setRotation(323);
            int height = 200;
            int width = 200;
            tvTrigger.getLayoutParams().height = height;
            tvTrigger.getLayoutParams().width = width;
        }

        else if (chosenInjury.equals("Puncture") || chosenInjury.equalsIgnoreCase("Tusok")){
            material1.setImageResource(correctMaterials.get(0));
            material2.setImageResource(correctMaterials.get(1));
            material3.setImageResource(correctMaterials.get(2));
            tvTrigger.setRotation(320);
            int height = 130;
            int width = 180;
            tvTrigger.getLayoutParams().height = height;
            tvTrigger.getLayoutParams().width = width;
        }

        else if (chosenInjury.equals("Laceration") || chosenInjury.equalsIgnoreCase("Laslas")){
            material1.setImageResource(correctMaterials.get(0));
            material2.setImageResource(correctMaterials.get(1));
            material3.setImageResource(correctMaterials.get(2));
            tvTrigger.setRotation(323);
            int height = 130;
            int width = 370;
            tvTrigger.getLayoutParams().height = height;
            tvTrigger.getLayoutParams().width = width;
        }
    }

    /*
        Function Name : onTouch
        Function Description : This function will set what will happen if triggers are clicked.
        Function Developer : Raeven Bauto
    */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch(v.getId()){
            case R.id.triggerTextview:
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        hasClickTrigger = true;

                    case MotionEvent.ACTION_UP:
                        if (whichMaterial.get(1) == 1 && nextStep == true && whatsNext == 1 && justStarted == true){
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
                        }

                        else if (whichMaterial.get(1) == 3 && nextStep == true && whatsNext == 3){
                            changeImage = true;
                            nextStep = false;
                            if (numberOfMaterials == 3){
                                isItDone = true;
                            }
                        }

                        else changeImage = false;
                }
                break;

            case R.id.iv_application_material1:
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        if (whichMaterial.get(0) == 0 && justStarted == true){
                            whichMaterial.set(0, 0);
                            whichMaterial.set(1, 1);
                            nextStep = true;
                        }
                }
                break;


            case R.id.iv_application_material2:
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        if (whichMaterial.get(1) == 1 && whatsNext == 2){
                            whichMaterial.set(0, 0);
                            whichMaterial.set(1, 2);
                            nextStep = true;
                        }
                }
                break;


            case R.id.iv_application_material3:
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        if (whichMaterial.get(1) == 2 && whatsNext == 3){
                            whichMaterial.set(0, 0);
                            whichMaterial.set(1, 3);
                            nextStep = true;
                        }
                }
                break;

            case R.id.button_next_application:
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
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
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.ic_ip_transparent);
        boolean allDone = false;
        ImageView imageview_back2;


        public MyView(Context context) {
            super(context);
            paint.setColor(Color.RED);
            paint.setStrokeWidth(15);
            paint.setStyle(Paint.Style.STROKE);

        }


        @Override
        protected void onDraw(Canvas canvas) {
            InteractivePracticeApplication interApp = new InteractivePracticeApplication();
            final LayoutInflater factory = getActivity().getLayoutInflater();

            final View rootView = factory.inflate(R.layout.fragment_interactive_practice_application, null);
            imageview_back2 = (ImageView) rootView.findViewById(R.id.imageview_back);


            if (imageNumber == 99){
                imageNumber = 1;

                if (chosenInjury.equals("Abrasion") || chosenInjury.equals("Gasgas")){
                    imageview_back.setImageResource(R.drawable.ic_ip_abrasion_four);
                }

                else if (chosenInjury.equals("Animal Bites") || chosenInjury.equals("Kagat ng hayop")){
                    imageview_back.setImageResource(R.drawable.ic_ip_animal_two);
                }

                else if (chosenInjury.equals("Insect Bites") || chosenInjury.equals("Kagat ng insekto")){
                    imageview_back.setImageResource(R.drawable.ic_ip_insect_four);
                }

                else if (chosenInjury.equals("Thermal Burns") || chosenInjury.equals("Paso")){
                    imageview_back.setImageResource(R.drawable.ic_ip_thermal_three);
                }

                else if (chosenInjury.equals("Chemical Burns") || chosenInjury.equals("Paso dulot ng kemikal")){
                    imageview_back.setImageResource(R.drawable.ic_ip_chemical_three);
                }

                else if (chosenInjury.equals("Concussion") || chosenInjury.equals("Untog")){
                    imageview_back.setImageResource(R.drawable.ic_ip_concussion_two);
                }

                else if (chosenInjury.equals("Contusion") || chosenInjury.equals("Pasa")){
                    imageview_back.setImageResource(R.drawable.ic_ip_contusion_two);
                }

                else if (chosenInjury.equals("Fracture") || chosenInjury.equals("Baling Buto")){
                    imageview_back.setImageResource(R.drawable.ic_ip_fracture_three);
                }

                else if (chosenInjury.equals("Laceration") || chosenInjury.equals("Laslas")){
                    imageview_back.setImageResource(R.drawable.ic_ip_laceration_three);
                }

                else if (chosenInjury.equals("Puncture") || chosenInjury.equals("Tusok")){
                    imageview_back.setImageResource(R.drawable.ic_ip_puncture_three);
                }

                allDone = true;

                interModel.setNumberOfError(numberOfErrors);
                interModel.setNumberOfTries(numberOfTries);
                interModel.setNumberOfCorrect(numberOfCorrect);

                interModel.assignThirdStageStats(numberOfCorrect, numberOfErrors, numberOfTries);
                buttonNext.setVisibility(View.VISIBLE);
            }

            else if (imageNumber != 99 && allDone == false){
                if (chosenInjury.equals("Abrasion") || chosenInjury.equals("Gasgas")){

                    if (imageNumber == 1){
                        imageview_back.setImageResource(R.drawable.ic_ip_abrasion_one);
                    }

                    else if (imageNumber == 2){
                        imageview_back.setImageResource(R.drawable.ic_ip_abrasion_two);
                    }

                    else if (imageNumber == 3){
                        imageview_back.setImageResource(R.drawable.ic_ip_abrasion_three);
                    }

                }

                else if (chosenInjury.equals("Fracture") || chosenInjury.equals("Baling Buto")){
                    if (imageNumber == 1){
                        imageview_back.setImageResource(R.drawable.ic_ip_fracture_one);
                    }

                    else if (imageNumber == 2){
                        imageview_back.setImageResource(R.drawable.ic_ip_fracture_two);
                    }


                }

                else if (chosenInjury.equals("Concussion") || chosenInjury.equals("Untog")){
                    if (imageNumber == 1){
                        imageview_back.setImageResource(R.drawable.ic_ip_concussion_one);
                    }
                }

                else if (chosenInjury.equals("Contusion") || chosenInjury.equals("Pasa")){
                    if (imageNumber == 1){
                        imageview_back.setImageResource(R.drawable.ic_ip_contusion_one);
                    }

                    else if (imageNumber == 2){
                        imageview_back.setImageResource(R.drawable.ic_ip_contusion_two);
                    }
                }

                else if (chosenInjury.equals("Chemical Burns") || chosenInjury.equals("Paso dulot ng kemikal")){
                    if (imageNumber == 1){
                        imageview_back.setImageResource(R.drawable.ic_ip_chemical_one);
                    }

                    else if (imageNumber == 2){
                        imageview_back.setImageResource(R.drawable.ic_ip_chemical_two);
                    }

                }

                else if (chosenInjury.equals("Thermal Burns") || chosenInjury.equals("Paso")){
                    if (imageNumber == 1){
                        imageview_back.setImageResource(R.drawable.ic_ip_thermal_one);
                    }

                    else if (imageNumber == 2){
                        imageview_back.setImageResource(R.drawable.ic_ip_thermal_two);
                    }
                }

                else if (chosenInjury.equals("Insect Bites") || chosenInjury.equals("Kagat ng insekto")){
                    if (imageNumber == 1){
                        imageview_back.setImageResource(R.drawable.ic_ip_insect_one);
                    }

                    else if (imageNumber == 2){
                        imageview_back.setImageResource(R.drawable.ic_ip_insect_two);
                    }

                    else if (imageNumber == 3){
                        imageview_back.setImageResource(R.drawable.ic_ip_insect_three);
                    }

                }

                else if (chosenInjury.equals("Animal Bites") || chosenInjury.equals("Kagat ng hayop")){
                    if (imageNumber == 1){
                        imageview_back.setImageResource(R.drawable.ic_ip_animal_one);
                    }
                }

                else  if (chosenInjury.equals("Puncture") || chosenInjury.equals("Tusok")){
                    if (imageNumber == 1){
                        imageview_back.setImageResource(R.drawable.ic_ip_puncture_one);

                    }

                    else if (imageNumber == 2){
                        imageview_back.setImageResource(R.drawable.ic_ip_puncture_two);

                    }
                }

                else if (chosenInjury.equals("Laceration") || chosenInjury.equals("Laslas")){
                    if (imageNumber == 1){
                        imageview_back.setImageResource(R.drawable.ic_ip_laceration_one);
                    }

                    else if (imageNumber == 2){
                        imageview_back.setImageResource(R.drawable.ic_ip_laceration_two);
                    }
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

                    if (hasClickTrigger == true && changeImage == true){
                        numberOfCorrect++;
                        if (isItDone == true){
                            imageNumber = 99;
                        }

                        else {
                            hasClickTrigger = false;
                            changeImage = false;
                            imageNumber ++;
                        }
                    }

                    else{
                        numberOfErrors++;
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


