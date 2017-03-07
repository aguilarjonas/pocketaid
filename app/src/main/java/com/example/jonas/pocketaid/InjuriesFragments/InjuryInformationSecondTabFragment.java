package com.example.jonas.pocketaid.InjuriesFragments;


import android.app.DownloadManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.jonas.pocketaid.MainActivity;
import com.example.jonas.pocketaid.R;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 */
public class InjuryInformationSecondTabFragment extends Fragment {

    private VideoView videoView;
    private Switch downloadSwitch;
    private ImageView playVideoImage;
    private TextView switchTextView;
    private String myURL = "";
    private String injuryType;
    private File videoFile;
    private YouTubePlayerSupportFragment youTubePlayerSupportFragment;
    private String youtubeLink;
    private YouTubePlayer youTubePlayer;

    private long downloadCode = 0;
    DownloadManager manager;

    public InjuryInformationSecondTabFragment() { /** Required empty public constructor **/ }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_injury_information_second_tab, container, false);

        String chosenInjury = getArguments().getString("injury");
        determineInjuryType(chosenInjury);
        initializeViews(rootView);
        inflateStepsFragment();
        downloadSwitchListener();
        videoStreamListener();

        return rootView;
    }

    /*
        Function Name : determineInjuryType
        Function Description :  This function will be called in the onCreateView
                                This function determines what type of injury was passed by InjuryTabLayout
        Function Developer : Jonas Aguilar
     */
    public void determineInjuryType(String chosenInjury) {
        if (chosenInjury.equals("Abrasion")) {
            youtubeLink = getResources().getString(R.string.abrasion_youtube);
            injuryType = "Abrasion";

//        } else if(chosenInjury.toLowerCase().equals("Gasgas")) {
//            injuryType = "Gasgas";
//            youtubeLink = getResources().getString(R.string.abrasion_youtube);
//        }
        } else if (chosenInjury.equals("Concussion")){
            injuryType = "Concussion";
            youtubeLink = getResources().getString(R.string.concussion_youtube);
        } else if (chosenInjury.equals("Contusion")){
            injuryType = "Contusion";
            youtubeLink = getResources().getString(R.string.contusion_youtube);
        } else if (chosenInjury.equals("Fracture")){
            injuryType = "Fracture";
            youtubeLink = getResources().getString(R.string.fracture_youtube);
        } else if(chosenInjury.equals("animal")){
            injuryType = "Animal";
            youtubeLink = getResources().getString(R.string.animal_bites_youtube);
        } else if(chosenInjury.equals("insect")){
            injuryType = "Insect";
            youtubeLink = getResources().getString(R.string.insect_bites_youtube);
        } else if(chosenInjury.toLowerCase().equals("first_second_degree")) {
            injuryType = "FirstSecondDegree";
            youtubeLink = getResources().getString(R.string.first_second_degree_youtube);
        } else if(chosenInjury.toLowerCase().equals("third_degree")) {
            injuryType = "ThirdDegree";
            youtubeLink = getResources().getString(R.string.third_degree_youtube);
        } else if(chosenInjury.toLowerCase().equals("major")) {
            injuryType = "Major";
            youtubeLink = getResources().getString(R.string.major_laceration_youtube);
        } else if(chosenInjury.toLowerCase().equals("minor")) {
            injuryType = "Minor";
            youtubeLink = getResources().getString(R.string.minor_laceration_youtube);
        } else if(chosenInjury.toLowerCase().equals("severe")) {
            injuryType = "Severe";
            youtubeLink = getResources().getString(R.string.severe_puncture_youtube);
        } else if(chosenInjury.toLowerCase().equals("slight")) {
            injuryType = "Slight";
            youtubeLink = getResources().getString(R.string.slight_puncture_youtube);
        }
    }

    /*
        Function Name : initializeView
        Function Description :  This function will be called in the onCreateView
                                This function initializes the different views/controls used by the fragment
        Function Developer : Jonas Aguilar
     */
    public void initializeViews(ViewGroup rootView) {
        playVideoImage = (ImageView) rootView.findViewById(R.id.imageView_play);
        downloadSwitch = (Switch) rootView.findViewById(R.id.switch_download);
        switchTextView = (TextView) rootView.findViewById(R.id.tv_switch_download);
        videoView = (VideoView) rootView.findViewById(R.id.injury_video);
        youTubePlayerSupportFragment = YouTubePlayerSupportFragment.newInstance();
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.injury_youtubevideo, youTubePlayerSupportFragment).commit();
    }

    /*
        Function Name : inflateStepsFragment
        Function Description :  This function will be called in the onCreateView
                                This function inflates the steps fragment below the video
        Function Developer : Jonas Aguilar
     */
    public void inflateStepsFragment(){
        InjuryStepsFragment injuryStepsFragment = new InjuryStepsFragment();
        Bundle args = new Bundle();
        args.putString("injury", injuryType);
        injuryStepsFragment.setArguments(args);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_steps_second, injuryStepsFragment).commit();
    }

    /*
        Function Name : downloadSwitchListener
        Function Description :  This function will be called in the onCreateView
                                This function observes the Switch behavior (onClick)
                                This function checks if the file was downloaded and changes the textview
                                    from Download to Downloaded or vice versa
        Function Developer : Jonas Aguilar
     */
    public void downloadSwitchListener() {
        File extStore = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        videoFile = new File(extStore.getAbsolutePath() + "/.VideoTutorials/", injuryType + ".mp4");

        //sets the switch and the text to whether downloaded or not
        if (videoFile.exists()){
            downloadSwitch.setChecked(true);
            switchTextView.setText(getString(R.string.Downloaded));
        } else {
            downloadSwitch.setChecked(false);
        }

        downloadSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                if (isChecked){
                    ConnectivityManager cm = (ConnectivityManager) getActivity().getApplicationContext()
                            .getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo ni = cm.getActiveNetworkInfo();

                    if(ni != null && ni.isConnected()) {
                        Toast.makeText(getActivity(), "Downloading", Toast.LENGTH_SHORT).show();
                        downloadTutorial();
                    } else {
                        Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if(downloadCode == 0){
                        Toast.makeText(getActivity(), "Video Deleted", Toast.LENGTH_SHORT).show();
                        videoFile.delete();
                    }

                    else{
                        Toast.makeText(getActivity(), "Download Cancelled", Toast.LENGTH_SHORT).show();
                        manager.remove(downloadCode);
                    }
                }
            }
        });
    }

    /*
        Function Name : videoStreamListener
        Function Description :  This function will be called in the onCreateView
                                This function observes the behavior of the Play Button
        Function Developer : Jonas Aguilar
     */
    public void videoStreamListener() {
        playVideoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //access checkPermission method sa MainActivity, passes String to fromFragment variable
                //((MainActivity)getActivity()).downloadVideo(injuryType);
                ConnectivityManager cm = (ConnectivityManager) getActivity().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo ni = cm.getActiveNetworkInfo();

                if(ni != null && ni.isConnected()) {
                    ((MainActivity)getActivity()).streamVideo(injuryType, videoView, youTubePlayerSupportFragment, youtubeLink);
                    playVideoImage.setVisibility(View.INVISIBLE);
                } else {
                    playVideoImage.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /*
        Function Name : downloadTutorial
        Function Description :  This function will be called in the downloadSwitchListener
                                This function downloads the injury video when the switch is clicked
        Function Developer : Jonas Aguilar
     */
    public void downloadTutorial() {
        //myURL = "https://s3-ap-southeast-1.amazonaws.com/funtastic4thesis/"+injuryType+".mp4";
        myURL = "http://d2f5qcrcmzlmuh.cloudfront.net/"+injuryType+".mp4";

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(myURL));
        request.setTitle(injuryType + " Video");
        request.setDescription("Video is being downloaded...");

        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        String fileName = URLUtil.guessFileName(myURL, null, MimeTypeMap.getFileExtensionFromUrl(myURL));
        File dir = new File(Environment.getExternalStorageDirectory() + "/Download/.VideoTutorials/");
        dir.mkdirs();

        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS +"/.VideoTutorials/", fileName);

        DownloadManager manager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
//        manager.enqueue(request);

        downloadCode = manager.enqueue(request);

    }

}
