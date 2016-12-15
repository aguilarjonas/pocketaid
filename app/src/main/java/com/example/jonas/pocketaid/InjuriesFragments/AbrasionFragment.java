package com.example.jonas.pocketaid.InjuriesFragments;


import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.example.jonas.pocketaid.MainActivity;
import com.example.jonas.pocketaid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AbrasionFragment extends Fragment {

    private TextView downloadNote;
    private Button downloadButton;
    private VideoView videoView;

    public AbrasionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity)getActivity()).setActionBarTitle("Abrasion");
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_abrasion, container, false);

        //initialization
        downloadNote = (TextView) rootView.findViewById(R.id.download_note);
        downloadButton = (Button) rootView.findViewById(R.id.abrasion_download);
        videoView = (VideoView) rootView.findViewById(R.id.abrasion_video);

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
