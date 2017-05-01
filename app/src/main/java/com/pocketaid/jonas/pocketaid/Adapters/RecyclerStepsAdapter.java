package com.pocketaid.jonas.pocketaid.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pocketaid.jonas.pocketaid.R;

/**
 * Created by alec on 1/17/17.
 */

public class RecyclerStepsAdapter extends RecyclerView.Adapter<RecyclerStepsAdapter.MyHolder> {

    private LayoutInflater inflater;
    int[] images;
    String[] steps;
    String[] notes;

    public RecyclerStepsAdapter(Context context, String[] step, int[] image, String[] note) {
        inflater = LayoutInflater.from(context);
        this.steps = step;
        this.images = image;
        this.notes = note;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_firstaid_steps, parent, false);
        MyHolder myHolder = new MyHolder(view);

        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.stepNumTV.setText(Integer.toString(position+1));
        holder.stepsNotes.setText(notes[position]);
        holder.stepsIV.setImageResource(images[position]);
        holder.stepsTV.setText(steps[position]);
    }

    @Override
    public int getItemCount() {
        return steps.length;
    }

    class MyHolder extends RecyclerView.ViewHolder {

        TextView stepsNotes;
        TextView stepsTV;
        TextView stepNumTV;
        ImageView stepsIV;

        public MyHolder(View itemView) {
            super(itemView);

            stepsIV = (ImageView) itemView.findViewById(R.id.step_image);
            stepsTV = (TextView) itemView.findViewById(R.id.steps);
            stepsNotes = (TextView) itemView.findViewById(R.id.additional_note);
            stepNumTV = (TextView) itemView.findViewById(R.id.step_number);
        }
    }
}
