package com.example.jonas.pocketaid.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jonas.pocketaid.R;

/**
 * Created by alec on 1/7/17.
 */

public class InjuryStepsAdapter extends ArrayAdapter<String> {

    int[] images;
    String[] steps;
    Context ctx;
    LayoutInflater inflater;

    public InjuryStepsAdapter(Context context, String[] steps, int[] images) {
        super(context, R.layout.layout_firstaid_steps, steps);

        this.ctx = context;
        this.steps = steps;
        this.images = images;
    }

    public class ViewHolder {
        TextView stepsTV;
        TextView stepNumTV;
        ImageView stepsIV;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_firstaid_steps, null);
        }

        final ViewHolder holder = new ViewHolder();
        holder.stepsTV = (TextView) convertView.findViewById(R.id.steps);
        holder.stepsIV = (ImageView) convertView.findViewById(R.id.step_image);
        holder.stepNumTV = (TextView) convertView.findViewById(R.id.step_number);

        holder.stepNumTV.setText(Integer.toString(position+1));
        holder.stepsTV.setText(steps[position]);
        holder.stepsIV.setImageResource(images[position]);

        return convertView;
    }
}
