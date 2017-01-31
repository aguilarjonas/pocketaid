package com.example.jonas.pocketaid.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.jonas.pocketaid.MainActivity;
import com.example.jonas.pocketaid.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {

    private TextView aboutText;

    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity)getActivity()).setActionBarTitle("About");
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_about, container, false);

        aboutText = (TextView) rootView.findViewById(R.id.about_disclaimer_body);
        aboutText.setText(Html.fromHtml(getString(R.string.disclaimer)));

        return rootView;
    }

}
