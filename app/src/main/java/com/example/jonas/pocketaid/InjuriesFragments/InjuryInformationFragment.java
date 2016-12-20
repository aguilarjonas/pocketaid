package com.example.jonas.pocketaid.InjuriesFragments;


import android.app.DownloadManager;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.jonas.pocketaid.MainActivity;
import com.example.jonas.pocketaid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InjuryInformationFragment extends Fragment {

    private TextView downloadNote;
    private Button downloadButton;
    private Button streamButton;
    private VideoView videoView;
    private String myURL = "";
    private String injuryType = "";
    MediaPlayer mediaC;



    public InjuryInformationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        String chosenInjury = getArguments().getString("injury");
        if (chosenInjury == "Abrasion"){
            injuryType = "Abrasion";
        }else if (chosenInjury == "Bites"){
            injuryType = "Bites";
        }else if (chosenInjury == "Burns"){
            injuryType = "Burns";
        }else if (chosenInjury == "Concussion"){
            injuryType = "Concussion";
        }else if (chosenInjury == "Contusion"){
            injuryType = "Contusion";
        }else if (chosenInjury == "Fracture"){
            injuryType = "Fracture";
        }else if (chosenInjury == "Laceration"){
            injuryType = "Laceration";
        }else if (chosenInjury == "Puncture"){
            injuryType = "Puncture";
        }

        ((MainActivity)getActivity()).setActionBarTitle(injuryType);
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_injury_information, container, false);

        //initialization
        downloadNote = (TextView) rootView.findViewById(R.id.download_note);
        downloadButton = (Button) rootView.findViewById(R.id.abrasion_download);
        streamButton = (Button) rootView.findViewById(R.id.stream_button);
        videoView = (VideoView) rootView.findViewById(R.id.abrasion_video);

        //Para pag click ang video mag play
//        videoView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                ((MainActivity)getActivity()).streamVideo(injuryType, videoView);
//                 return true;
//            }
//        });



        //onClickListener ng Download
        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //access checkPermission method sa MainActivity, passes String to fromFragment variable
                ((MainActivity)getActivity()).downloadVideo(injuryType);

            }
        });

        //Click listener ng Stream
        streamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //access checkPermission method sa MainActivity, passes String to fromFragment variable
                //((MainActivity)getActivity()).downloadVideo(injuryType);
                ((MainActivity)getActivity()).streamVideo(injuryType, videoView);

            }
        });


        return rootView;
    }

    public void downloadTutorial() {
        myURL = "https://s3-ap-southeast-1.amazonaws.com/funtastic4thesis/"+injuryType+".mp4";
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(myURL));
        request.setTitle(injuryType + " Video");
        request.setDescription("File is being downloaded...");

        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        String fileName = URLUtil.guessFileName(myURL, null, MimeTypeMap.getFileExtensionFromUrl(myURL));

        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);

        DownloadManager manager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }


}
