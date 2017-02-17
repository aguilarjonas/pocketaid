package com.example.jonas.pocketaid.PracticeFragments;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.jonas.pocketaid.MainActivity;
import com.example.jonas.pocketaid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InteractivePracticeApplication extends Fragment {

    ViewGroup rootView;
    ImageView applicationImage;
    Button btTrigger;
    Paint paint;
    RelativeLayout relativeLayout_Touch;
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
        relativeLayout_Touch = (RelativeLayout) rootView.findViewById(R.id.relativeLayout_Touch);
        relativeLayout_Touch.addView(new MyView(getActivity().getApplicationContext()));
        addTouchListener();
        getTrigger();


        return rootView;
    }

    private void addTouchListener() {
//        applicationImage = (ImageView) rootView.findViewById(R.id.touchImage);
        relativeLayout_Touch.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float x = event.getRawX();
                float y = event.getRawY();




                return false;
            }
        });
    }
    public void getTrigger(){

        btTrigger.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(getActivity().getApplicationContext(), "PININDOT MO YUNG SUGAT", Toast.LENGTH_SHORT).show();
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
            Bitmap b= BitmapFactory.decodeResource(getResources(), R.drawable.abrasion_1);
            canvas.drawBitmap(b, 0, 0, paint);
            canvas.drawCircle(point.x, point.y, 100, paint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    point.x = event.getX();
                    point.y = event.getY();
                    String message = String.format("Coordinates: %.2f, %.2f", point.x, point.y);

                    int[] test = new int[2];
                    btTrigger.getLocationOnScreen(test);

                    String message2 = "X: " + test[0] + "," + "Y: " + test[1];
                    Log.e("COORDINATE", message);
                    Log.e("BUTTON COORDINATE", message2);

            }
            invalidate();
            return true;

        }

    }
    class Point {
        float x, y;
    }
}


