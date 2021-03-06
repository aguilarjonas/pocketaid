package com.pocketaid.jonas.pocketaid;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.pocketaid.jonas.pocketaid.Fragments.AboutFragment;
import com.pocketaid.jonas.pocketaid.Fragments.InjuriesFragment;
import com.pocketaid.jonas.pocketaid.Fragments.NearbyFragment;
import com.pocketaid.jonas.pocketaid.Fragments.PracticeFragment;
import com.pocketaid.jonas.pocketaid.Fragments.SettingsFragment;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.io.File;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawer;
    private FloatingActionButton fab;
    NavigationView navigationView = null;
    Toolbar toolbar = null;

    //Added by Raeven
    MediaController mediaC;
    private SlidingUpPanelLayout mLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermission();

        //redirect to phone with 911 pre-dialled
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent call = new Intent(Intent.ACTION_DIAL);
                call.setData(Uri.parse("tel:911"));
                startActivity(call);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //initial page after app loads
        InjuriesFragment injuriesFragment = new InjuriesFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, injuriesFragment).commit();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //Added by Raeven
        mediaC = new MediaController(this);
        mLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else if(getSupportFragmentManager().getBackStackEntryCount() != 0) { //to avoid app getting closed (fragments)
            getSupportFragmentManager().popBackStack();
        }
        else if (mLayout != null &&
                (mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED || mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.ANCHORED)) {
            mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                return true;
            case R.id.settings:
                SettingsFragment settingsFragment = new SettingsFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(settingsFragment, "Settings")
                        .replace(R.id.fragment_container, settingsFragment)
                        .addToBackStack("Settings")
                        .commit();

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        //nav bar items redirection
        if (id == R.id.nav_injuries) {
            InjuriesFragment injuriesFragment = new InjuriesFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            getSupportFragmentManager().popBackStack(null, android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fragmentTransaction.add(injuriesFragment, "Injuries")
                    .replace(R.id.fragment_container, injuriesFragment)
                    .addToBackStack("Injuries")
                    .commit();
        } else if (id == R.id.nav_nearby) {
            NearbyFragment nearbyFragment = new NearbyFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            getSupportFragmentManager().popBackStack(null, android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fragmentTransaction.add(nearbyFragment, "Nearby")
                    .replace(R.id.fragment_container, nearbyFragment)
                    .addToBackStack("Nearby")
                    .commit();
        } else if (id == R.id.nav_practice) {
            PracticeFragment practiceFragment = new PracticeFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            getSupportFragmentManager().popBackStack(null, android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fragmentTransaction.add(practiceFragment, "Practice")
                    .replace(R.id.fragment_container, practiceFragment)
                    .addToBackStack("Practice")
                    .commit();
        } else if (id == R.id.nav_about) {
            AboutFragment aboutFragment = new AboutFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            getSupportFragmentManager().popBackStack(null, android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fragmentTransaction.add(aboutFragment, "About")
                    .replace(R.id.fragment_container, aboutFragment)
                    .addToBackStack("About")
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void checkPermission() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if((ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                    && (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
                requestPermissions(new String[] {
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                }, 200);
            }
        }
    }

    /*
        Function Name : streamVideo
        Function Description :  This function is access from InjuryInformationFragment & InjuryInformationSecondFragment
        Function Developer : Raeven Bauto
     */
    public boolean streamVideo (String injuryType, VideoView videoView, YouTubePlayerSupportFragment youTubePlayerSupportFragment, final String youtubeLink, ImageView videoPlayImage){
        File extStore = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File myFile = new File(extStore.getAbsolutePath() + "/.VideoTutorials/", injuryType + ".mp4");

        if (myFile.exists()) {
//            Toast.makeText(getApplicationContext(), "Merong File", Toast.LENGTH_SHORT).show();
            videoPlayImage.setVisibility(View.INVISIBLE);
            videoView.setVideoPath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() +
                    "/.VideoTutorials/" + injuryType + ".mp4");
            MediaController vidControl = new MediaController(this);
            videoView.setMediaController(mediaC);
            mediaC.setAnchorView(videoView);
            videoView.start();

            return true;
        }

        else {
//            Toast.makeText(getApplicationContext(), "Walang File", Toast.LENGTH_SHORT).show();
            ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo ni = cm.getActiveNetworkInfo();

            if(ni != null && ni.isConnected()) {
                videoView.setVisibility(View.GONE);
                videoPlayImage.setVisibility(View.INVISIBLE);
                youTubePlayerSupportFragment.initialize(getResources().getString(R.string.youtube_api_key), new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                        youTubePlayer.setShowFullscreenButton(false);
                        youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                        youTubePlayer.loadVideo(youtubeLink);
                        youTubePlayer.play();
                    }

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                        Log.d("ERROR", youTubeInitializationResult.name());
                    }
                });
            } else {
                videoPlayImage.setVisibility(View.VISIBLE);
                Toast.makeText(this.getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 200:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    return;
                }
            default:
                checkPermission();
        }
    }

    /*
        Function Name : setActionBarTitle
        Function Description :  This function changes the action bar title
        Function Developer : Jonas Aguilar
     */
    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(""); //to solve Android's bug
        getSupportActionBar().setTitle(title);
    }

    /*
        Function Name : hideOrShowFAB
        Function Description :  This function hides or shows the FAB
        Function Developer : Jonas Aguilar
     */
    public void hideOrShowFAB(String hideOrShow) {
        fab = (FloatingActionButton) findViewById(R.id.fab);

        if(hideOrShow.toLowerCase().equals("hide")) {
            fab.hide();
        } else if(hideOrShow.toLowerCase().equals("show")){
            fab.show();
        }
    }

    /*
        Function Name : resetActionBar
        Function Description :  This function sets the action bar to back button or not
        Function Developer : Jonas Aguilar
     */
    public void resetActionBar(boolean childAction, int drawerMode) {
        if (childAction) {
            //sets icon to back button
            toggle.setDrawerIndicatorEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getSupportFragmentManager().popBackStack();
                }
            });
        } else {
            toggle.setDrawerIndicatorEnabled(true);
        }

        drawer.setDrawerLockMode(drawerMode);
    }
}



