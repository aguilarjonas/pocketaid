package com.example.jonas.pocketaid.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jonas.pocketaid.Fragments.NearbyInformationFragment;
import com.example.jonas.pocketaid.MainActivity;
import com.example.jonas.pocketaid.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Lance on 21 Dec 2016.
 */

public class HospitalListAdapter extends RecyclerView.Adapter<HospitalListAdapter.MyNearbyHolder> {

    private Hospital hospital;
    private LayoutInflater inflater;
    private Context context;
    private List<Hospital> list = Collections.emptyList();

    //Constructor
    public HospitalListAdapter(Context context, List<Hospital> list) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    @Override
    public MyNearbyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_google_maps, parent, false);
        MyNearbyHolder holder = new MyNearbyHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyNearbyHolder holder, int position) {
        Hospital current = list.get(position);

        holder.hospitalName.setText(current.getHospitalName());
        holder.hospitalVicinity.setText(current.getHospitalVicinity());
//        holder.hospitalPlaceID.setText(current.getHospitalPlaceID());
        holder.hospitalLatitude.setText(current.getHospitalLatitude());
        holder.hospitalLongitude.setText(current.getHospitalLongitude());
    }

    class MyNearbyHolder extends RecyclerView.ViewHolder {

        TextView hospitalName;
        TextView hospitalVicinity;
        TextView hospitalPlaceID;
        TextView hospitalLatitude;
        TextView hospitalLongitude;
        View view;

        public MyNearbyHolder(View itemView) {
            super(itemView);
            view = itemView;

            hospitalName = (TextView) view.findViewById(R.id.textView_hospitalName);
            hospitalVicinity = (TextView) view.findViewById(R.id.textView_vicinity);
            hospitalPlaceID = (TextView) view.findViewById(R.id.textview_placeID);
            hospitalLatitude = (TextView) view.findViewById(R.id.textview_latitude);
            hospitalLongitude = (TextView) view.findViewById(R.id.textview_longitude);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String chosenHospital = ((TextView)v.findViewById(R.id.textView_hospitalName)).getText().toString();
                    String chosenHospitalPlaceID = ((TextView)v.findViewById(R.id.textview_placeid)).getText().toString();
                    String chosenHospitalLat = ((TextView)v.findViewById(R.id.textview_latitude)).getText().toString();
                    String chosenHospitalLng = ((TextView)v.findViewById(R.id.textview_longitude)).getText().toString();

                    String chosenHospitalVicinity = ((TextView)v.findViewById(R.id.textView_vicinity)).getText().toString();

                    NearbyInformationFragment nearbyHospitalInformation = new NearbyInformationFragment();
                    FragmentTransaction fragmentTransaction = ((MainActivity)context).getSupportFragmentManager().beginTransaction();
                    Bundle args = new Bundle();
                    args.putString("chosenHospital" , chosenHospital);
                    args.putString("chosenHospitalPlaceID" , chosenHospitalPlaceID);
                    args.putString("chosenHospitalLat", chosenHospitalLat);
                    args.putString("chosenHospitalLng", chosenHospitalLng);
                    args.putString("chosenHospitalVicinity", chosenHospitalVicinity);

                    nearbyHospitalInformation.setArguments(args);

                    fragmentTransaction.add(nearbyHospitalInformation, "nearbyHospitalInformation")
                            .replace(R.id.fragment_container, nearbyHospitalInformation)
                            .addToBackStack("nearbyHospitalInformation")
                            .commit();
                }
            });
        }
    }

//    @NonNull
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//
//        View row;
//        row = convertView;
//        DataHandler handler;
//
//        if (convertView == null){
//            LayoutInflater inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            row = inflater.inflate(R.layout.layout_google_maps, parent, false);
//
//            handler = new DataHandler();
//            handler.hospitalName = (TextView)row.findViewById(R.id.textView_hospitalName);
//            handler.hospitalVicinity = (TextView)row.findViewById(R.id.textView_vicinity);
//            handler.hospitalPlaceID = (TextView)row.findViewById(R.id.textview_placeid);
//            handler.hospitalLatitude = (TextView)row.findViewById(R.id.textview_latitude);
//            handler.hospitalLongitude = (TextView)row.findViewById(R.id.textview_longitude);
//
//            row.setTag(handler);
//        }
//
//        else {
//            handler = (DataHandler)row.getTag();
////            handler.hospitalName.setTextColor(Color.WHITE);
////            handler.hospitalName.setBackgroundColor(Color.RED);
//        }
//        Hospital hospital;
//
//        hospital = (Hospital)this.getItem(position);
//
////        if (position % 2 == 1) {
////            row.setBackgroundColor(Color.parseColor("#FFB6B546"));
////        }
////
////        else
////            row.setBackgroundColor(Color.parseColor(String.valueOf("#E0FFFF")));
//
//
//        handler.hospitalName.setText(hospital.getHospitalName());
//        handler.hospitalVicinity.setText(hospital.getHospitalVicinity());
//        handler.hospitalPlaceID.setText(hospital.getHospitalPlaceID());
//        handler.hospitalLatitude.setText(hospital.getHospitalLatitude());
//        handler.hospitalLongitude.setText(hospital.getHospitalLongitude());
//
//        return row;
//    }
}
