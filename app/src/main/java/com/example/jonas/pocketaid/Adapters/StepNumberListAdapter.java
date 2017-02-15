package com.example.jonas.pocketaid.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jonas.pocketaid.R;

/**
 * Created by alec on 2/15/17.
 */

public class StepNumberListAdapter extends RecyclerView.Adapter<StepNumberListAdapter.MyHolder> {

    private LayoutInflater inflater;
    private String[] injurySteps;

    public StepNumberListAdapter(Context context, String[] injurySteps) {
        inflater = LayoutInflater.from(context);
        this.injurySteps = injurySteps;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.steps_numbering, parent, false);
        MyHolder myHolder = new MyHolder(view);

        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.stepNumTV.setText(Integer.toString(position+1));
    }

    @Override
    public int getItemCount() {
        return injurySteps.length;
    }

    class MyHolder extends RecyclerView.ViewHolder {

        TextView stepNumTV;

        public MyHolder(View itemView) {
            super(itemView);

            stepNumTV = (TextView) itemView.findViewById(R.id.rowTextView);
        }
    }
}