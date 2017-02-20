package com.example.jonas.pocketaid.PracticeFragments;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jonas.pocketaid.MainActivity;
import com.example.jonas.pocketaid.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class InteractivePracticeApplication extends Fragment {

    ViewGroup rootView;
    ImageView applicationImage;
    TextView tvTrigger;
    Paint paint;
    RelativeLayout relativeLayout_Touch;

    ImageView material1;
    ImageView material2;
    ImageView material3;
    ImageView material4;

    boolean hasClickTrigger = false;
    ArrayList<Integer> whichMaterial = new ArrayList<Integer>();
    boolean changeImage = false;
    boolean isItDone = false;
    boolean nextStep = false;

    int numberOfMaterials = 0;

    int imageNumber = 1;
    int whatsNext = 1;

    boolean justStarted = true;
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

        numberOfMaterials = Integer.parseInt(getArguments().getString("numberOfMaterials"));

        clearValues();
        initializeElements();
        setVisibilityForMaterials(numberOfMaterials);
        getTrigger();

        return rootView;
    }

    public void setVisibilityForMaterials(int numberOfMaterials){

        if (numberOfMaterials == 0){
            material1.setVisibility(View.GONE);
            material2.setVisibility(View.GONE);
            material3.setVisibility(View.GONE);
            material4.setVisibility(View.GONE);
        }
        else if (numberOfMaterials == 1){
            material2.setVisibility(View.GONE);
            material3.setVisibility(View.GONE);
            material4.setVisibility(View.GONE);
        }

        else if (numberOfMaterials == 2){
            material3.setVisibility(View.GONE);
            material4.setVisibility(View.GONE);
        }

        else if (numberOfMaterials == 3){
            material4.setVisibility(View.GONE);
        }
    }

    public void initializeElements(){

        material1 = (ImageView) rootView.findViewById(R.id.iv_application_material1);
        material2 = (ImageView) rootView.findViewById(R.id.iv_application_material2);
        material3 = (ImageView) rootView.findViewById(R.id.iv_application_material3);
        material4 = (ImageView) rootView.findViewById(R.id.iv_application_material4);


        relativeLayout_Touch = (RelativeLayout) rootView.findViewById(R.id.relativeLayout_Touch);
        relativeLayout_Touch.addView(new MyView(getActivity().getApplicationContext()));
        tvTrigger = (TextView) rootView.findViewById(R.id.triggerTextview);
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

    public void getTrigger(){

        tvTrigger.setOnTouchListener(new View.OnTouchListener(){
            private Rect rect;

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        Toast.makeText(getActivity().getApplicationContext(), "PININDOT MO YUNG SUGAT", Toast.LENGTH_SHORT).show();
                        Log.e("SUGAT:", "ANDITO AKO SA SUGAT");
                        hasClickTrigger = true;

                    case MotionEvent.ACTION_UP:
                        if (whichMaterial.get(1) == 1 && nextStep == true && whatsNext == 1 && justStarted == true){
                            Log.e("TAMA KA:", "MAY TAMA KA ... SA ULO");
//                            whichMaterial = 2;
                            changeImage = true;
                            whatsNext = 2;
                            justStarted = false;
                            if (numberOfMaterials == 1){
                                isItDone = true;
                            }
                        }

                        else if (whichMaterial.get(1) == 2 && nextStep == true && whatsNext == 2){
                            changeImage = true;
                            whatsNext = 3;
                            if (numberOfMaterials == 2){
                                isItDone = true;
                            }
                        }

                        else if (whichMaterial.get(1) == 3 && nextStep == true && whatsNext == 3){
                            changeImage = true;
//                            isItDone = true;
                            if (numberOfMaterials == 3){
                                isItDone = true;
                            }

                        }

                        else changeImage = false;
                }



                return false;
            }
        });

        material1.setOnTouchListener(new View.OnTouchListener(){
            private Rect rect;
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                //By default, the whichMaterial == 0.
                /*
                    This prevents the user from triggering the material again, if the user has completed
                    the first step
                 */
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        Toast.makeText(getActivity().getApplicationContext(), "PININDOT MO YUNG GAMIT", Toast.LENGTH_SHORT).show();
                        if (whichMaterial.get(0) == 0 && justStarted == true){
                            whichMaterial.set(0, 0);
                            whichMaterial.set(1, 1);
                            nextStep = true;
                        }

                        if (whatsNext != 1){
                            whichMaterial.set(1,1);
                        }

                        Log.e("GAMIT:", "ANDITO AKO SA GAMIT");
                }


                return false;
            }
        });

        material2.setOnTouchListener(new View.OnTouchListener(){
            private Rect rect;
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                //By default, the whichMaterial == 0.
                /*
                    This prevents the user from triggering the material again, if the user has completed
                    the second step
                 */
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        Toast.makeText(getActivity().getApplicationContext(), "PININDOT MO YUNG GAMIT", Toast.LENGTH_SHORT).show();
                        if (whichMaterial.get(1) == 1){
                            whichMaterial.set(0, 0);
                            whichMaterial.set(1, 2);
                            nextStep = true;
                        }

                        if (whatsNext != 2){
                            whichMaterial.set(1,2);
                        }

                        Log.e("GAMIT:", "ANDITO AKO SA GAMIT");
                }


                return false;
            }
        });

        material3.setOnTouchListener(new View.OnTouchListener(){
            private Rect rect;
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                //By default, the whichMaterial == 0.
                /*
                    This prevents the user from triggering the material again, if the user has completed
                    the second step
                 */

                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        Toast.makeText(getActivity().getApplicationContext(), "PININDOT MO YUNG GAMIT", Toast.LENGTH_SHORT).show();
                        if (whichMaterial.get(1) == 2){
                            whichMaterial.set(0, 0);
                            whichMaterial.set(1, 3);
                            nextStep = true;
                        }

                        if (whatsNext != 3 || whatsNext == 3){
                            whichMaterial.set(1,3);
                        }
                        Log.e("GAMIT:", "ANDITO AKO SA GAMIT");
                }


                return false;
            }
        });



    }


    class MyView extends View{
        Paint paint = new Paint();
        Point point = new Point();

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
            Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.ic_chemical);
            if (imageNumber == 1){
                 b= BitmapFactory.decodeResource(getResources(), R.drawable.abrasion_1);
            }

            else if (imageNumber == 2){
                 b= BitmapFactory.decodeResource(getResources(), R.drawable.checkmark);
            }

            else if (imageNumber == 3){
                b= BitmapFactory.decodeResource(getResources(), R.drawable.ic_nearby);
            }

            else if (imageNumber == 99){
                imageNumber = 1;
                DisplayScoreFragment displayScoreFragment = new DisplayScoreFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager ().beginTransaction();
                fragmentTransaction.add(displayScoreFragment, "displayScoreFragment")
                        .replace(R.id.fragment_container, displayScoreFragment)
                        .addToBackStack("displayScoreFragment")
                        .commit();
            }

            canvas.drawBitmap(b, 0, 0, paint);
            canvas.drawCircle(point.x, point.y, 100, paint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    point.x = event.getX() ;
                    point.y = event.getY() ;
                    Toast.makeText(getActivity().getApplicationContext(), "PININDOT MO YUNG PICTURE", Toast.LENGTH_SHORT).show();
                    Log.e("PICTURE:", "ANDITO AKO SA PICTURE");

                    if (hasClickTrigger == true && changeImage == true){
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

                    else
                        Log.e("PICTURE:", "HEHEHE di ka magaling");


            }
            invalidate();
            return true;

        }

    }
    class Point {
        float x, y;
    }
}


