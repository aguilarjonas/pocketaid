package com.example.jonas.pocketaid.InjuriesFragments;


import android.app.DownloadManager;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.jonas.pocketaid.MainActivity;
import com.example.jonas.pocketaid.R;

import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 */
public class InjuryInformationFragment extends Fragment {

    private TextView downloadNote;
    private Button downloadButton;
    private Button streamButton;
    private VideoView videoView;
    private Switch downloadSwitch;
    private ImageView playVideoImage;

    private String myURL = "";
    private String injuryType = "";
    private File videoFile;
    MediaPlayer mediaC;



    public InjuryInformationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        String chosenInjury = getArguments().getString("injury");
        if (chosenInjury.equals("Abrasion")){
            injuryType = "Abrasion";
        }else if (chosenInjury.equals("Bites")){
            injuryType = "Bites";
        }else if (chosenInjury.equals("Burns")){
            injuryType = "Burns";
        }else if (chosenInjury.equals("Concussion")){
            injuryType = "Concussion";
        }else if (chosenInjury.equals("Contusion")){
            injuryType = "Contusion";
        }else if (chosenInjury.equals("Fracture")){
            injuryType = "Fracture";
        }else if (chosenInjury.equals("Laceration")){
            injuryType = "Laceration";
        }else if (chosenInjury.equals("Puncture")){
            injuryType = "Puncture";
        }

        ((MainActivity)getActivity()).setActionBarTitle(injuryType);
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_injury_information, container, false);

        //initialization
        //downloadNote = (TextView) rootView.findViewById(R.id.download_note);
        playVideoImage = (ImageView) rootView.findViewById(R.id.imageView_play);
        downloadSwitch = (Switch) rootView.findViewById(R.id.switch_download);
        //downloadButton = (Button) rootView.findViewById(R.id.video_download);
        //streamButton = (Button) rootView.findViewById(R.id.stream_button);
        videoView = (VideoView) rootView.findViewById(R.id.abrasion_video);

        File extStore = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        videoFile = new File(extStore.getAbsolutePath(), injuryType + ".mp4");

        if (videoFile.exists()){
            downloadSwitch.setChecked(true);
        }

        else
            downloadSwitch.setChecked(false);



        //Para pag click ang video mag play
//        videoView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                ((MainActivity)getActivity()).streamVideo(injuryType, videoView);
//                 return true;
//            }
//        });

        downloadSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                if (isChecked == true){
                    Toast.makeText(getActivity(), "Downloading", Toast.LENGTH_SHORT).show();
                    downloadTutorial();
                }

                else if (isChecked == false){
                    Toast.makeText(getActivity(), "NADELETE NA TANGA", Toast.LENGTH_SHORT).show();
                    videoFile.delete();

                }

            }
        });



        //onClickListener ng Download
//        downloadButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //access checkPermission method sa MainActivity, passes String to fromFragment variable
//                Toast.makeText(getActivity(), "Downloading", Toast.LENGTH_SHORT).show();
//                downloadTutorial();
//
//            }
//        });

        //Click listener ng Stream
        playVideoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //access checkPermission method sa MainActivity, passes String to fromFragment variable
                //((MainActivity)getActivity()).downloadVideo(injuryType);
                ((MainActivity)getActivity()).streamVideo(injuryType, videoView);
                playVideoImage.setVisibility(View.INVISIBLE);

            }
        });

//        //Para pag click ang video mag play
//        videoView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                ((MainActivity)getActivity()).streamVideo(injuryType, videoView);
//                 return true;
//            }
//        });


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
