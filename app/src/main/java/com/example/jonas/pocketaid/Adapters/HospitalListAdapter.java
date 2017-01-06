package com.example.jonas.pocketaid.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jonas.pocketaid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lance on 21 Dec 2016.
 */

public class HospitalListAdapter extends ArrayAdapter {

    List list = new ArrayList();
    //Constructor
    public HospitalListAdapter(Context context, int resource) {
        super(context, resource);
    }

    static class DataHandler{
        TextView hospitalName;
        TextView hospitalVicinity;
        TextView hospitalPlaceID;
    }
    @Override
    public void add (Object object){
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View row;
        row = convertView;
        DataHandler handler;

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.layout_google_maps, parent, false);

            handler = new DataHandler();
            handler.hospitalName = (TextView)row.findViewById(R.id.textView_hospitalName);
            handler.hospitalVicinity = (TextView)row.findViewById(R.id.textView_vicinity);
            handler.hospitalPlaceID = (TextView)row.findViewById(R.id.textview_placeid);
            row.setTag(handler);
        }

        else {
            handler = (DataHandler)row.getTag();
//            handler.hospitalName.setTextColor(Color.WHITE);
//            handler.hospitalName.setBackgroundColor(Color.RED);
        }
        Hospital hospital;

        hospital = (Hospital)this.getItem(position);

//        if (position % 2 == 1) {
//            row.setBackgroundColor(Color.parseColor("#FFB6B546"));
//        }
//
//        else
//            row.setBackgroundColor(Color.parseColor(String.valueOf("#E0FFFF")));


        handler.hospitalName.setText(hospital.getHospitalName());
        handler.hospitalVicinity.setText(hospital.getHospitalVicinity());
        handler.hospitalPlaceID.setText(hospital.getHospitalPlaceID());

        return row;
    }
}
