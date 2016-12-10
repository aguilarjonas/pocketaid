package com.example.jonas.pocketaid.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jonas.pocketaid.R;

/**
 * Created by alec on 12/10/16.
 */
public class InjuryListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] injuryName;
    private final Integer[] imageId;

    public InjuryListAdapter(Activity context, String[] injuryName, Integer[] imageId) {
        super(context, R.layout.layout_injury_list, injuryName);

        this.context = context;
        this.injuryName = injuryName;
        this.imageId = imageId;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.layout_injury_list, null, true);

        TextView injuryView = (TextView) rowView.findViewById(R.id.injury_name);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.injury_icon);

        injuryView.setText(injuryName[position]);
        imageView.setImageResource(imageId[position]);

        return rowView;
    }
}
