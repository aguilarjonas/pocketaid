package com.example.jonas.pocketaid.InjuriesFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.jonas.pocketaid.MainActivity;
import com.example.jonas.pocketaid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LacerationFragment extends Fragment {

    private TextView downloadNote;
    private Button downloadButton;
    private VideoView videoView;

    public LacerationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity)getActivity()).setActionBarTitle("Laceration");
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_laceration, container, false);

        //initialization
        downloadNote = (TextView) rootView.findViewById(R.id.download_note);
        downloadButton = (Button) rootView.findViewById(R.id.laceration_download);
        videoView = (VideoView) rootView.findViewById(R.id.laceration_video);

        //onClickListener ng Download
        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //replace with download logic
                Toast.makeText(getActivity().getApplicationContext(),
                        "Replace with download logic", Toast.LENGTH_LONG).show();
            }
        });

        return rootView;
    }

}
