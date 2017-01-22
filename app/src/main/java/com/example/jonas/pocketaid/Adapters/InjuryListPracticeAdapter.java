package com.example.jonas.pocketaid.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jonas.pocketaid.R;

/**
 * Created by alec on 1/22/17.
 */

public class InjuryListPracticeAdapter extends RecyclerView.Adapter<InjuryListPracticeAdapter.MyPracticeHolder> {

    private LayoutInflater inflater;
    private Integer[] injury_logos;
    private String[] injury_names;

    public InjuryListPracticeAdapter(Context context, String[] injury_names, Integer[] injury_logos) {
        inflater = LayoutInflater.from(context);
        this.injury_names = injury_names;
        this.injury_logos = injury_logos;
    }

    @Override
    public MyPracticeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_injurylist_practice, parent, false);
        MyPracticeHolder holder = new MyPracticeHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyPracticeHolder holder, int position) {
        holder.injury_logo.setImageResource(injury_logos[position]);
        holder.injury_name.setText(injury_names[position]);
    }

    @Override
    public int getItemCount() {
        return injury_names.length;
    }

    class MyPracticeHolder extends RecyclerView.ViewHolder {

        ImageView injury_logo;
        TextView injury_name;
        View view;

        public MyPracticeHolder(final View itemView) {
            super(itemView);
            view = itemView;

            injury_logo = (ImageView) itemView.findViewById(R.id.practice_injury_logo);
            injury_name = (TextView) itemView.findViewById(R.id.practice_injury_name);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(injury_name.getText().toString().toLowerCase().equals("abrasion")) {
                        Toast.makeText(itemView.getContext(), "ABRASION", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(itemView.getContext(), "ELSE", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
