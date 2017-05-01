package com.pocketaid.jonas.pocketaid.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.pocketaid.jonas.pocketaid.R;

import java.util.ArrayList;

/**
 * Created by alec on 12/10/16.
 */
public class InjuryListAdapter extends BaseAdapter implements Filterable {

    Context context;
    ArrayList<Injury> injuries;
    FilterClass filterClass;
    ArrayList<Injury> filteredInjuries;

    public InjuryListAdapter(Context context, ArrayList<Injury> injuries) {
        this.context = context;
        this.injuries = injuries;
        this.filteredInjuries = injuries;
    }

    @Override
    public int getCount() {
        return injuries.size();
    }

    @Override
    public Object getItem(int position) {
        return injuries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return injuries.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null) {
            convertView = inflater.inflate(R.layout.layout_injury_list, null);
        }

        TextView injuryTextView = (TextView) convertView.findViewById(R.id.injury_name);
        ImageView injuryImageView = (ImageView) convertView.findViewById(R.id.injury_icon);

        injuryTextView.setText(injuries.get(position).getInjuryName());
        injuryImageView.setImageResource(injuries.get(position).getInjuryLogo());

        return convertView;
    }

    @Override
    public Filter getFilter() {
        if(filterClass == null) {
            filterClass = new FilterClass();
        }

        return filterClass;
    }

    public class FilterClass extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();

            if(constraint != null && constraint.length() > 0) {
                constraint = constraint.toString().toLowerCase();
                ArrayList<Injury> filters = new ArrayList<Injury>();

                for(int ctr = 0; ctr < filteredInjuries.size(); ctr++) {
                    if(filteredInjuries.get(ctr).getInjuryName().toLowerCase().contains(constraint)) {
                        Injury injury = new Injury(filteredInjuries.get(ctr).getInjuryName(), filteredInjuries.get(ctr).getInjuryLogo());

                        filters.add(injury);
                    }
                }

                filterResults.count = filters.size();
                filterResults.values = filters;
            } else {
                filterResults.count = filteredInjuries.size();
                filterResults.values = filteredInjuries;
            }

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            injuries = (ArrayList<Injury>) results.values;
            notifyDataSetChanged();
        }
    }
}
