package com.example.jonas.pocketaid;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.jonas.pocketaid.Fragments.AboutFragment;
import com.example.jonas.pocketaid.Fragments.NearbyFragment;
import com.example.jonas.pocketaid.Fragments.PracticeFragment;
import com.example.jonas.pocketaid.InjuriesFragments.InjuryInformationFragment;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    NavigationView navigationView = null;
    Toolbar toolbar = null;

    //Added by Raeven
    MediaController mediaC;
    private VideoView videoView;



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
        com.example.jonas.pocketaid.Fragments.InjuriesFragment injuriesFragment = new com.example.jonas.pocketaid.Fragments.InjuriesFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, injuriesFragment).commit();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //Added by Raeven
        mediaC = new MediaController(this);
        videoView = (VideoView)findViewById(R.id.abrasion_video);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if(getSupportFragmentManager().getBackStackEntryCount() != 0) { //to avoid app getting closed (fragments)
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
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
            com.example.jonas.pocketaid.Fragments.InjuriesFragment injuriesFragment = new com.example.jonas.pocketaid.Fragments.InjuriesFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(injuriesFragment, "Injuries")
                    .replace(R.id.fragment_container, injuriesFragment)
                    .addToBackStack("Injuries")
                    .commit();
        } else if (id == R.id.nav_nearby) {
            NearbyFragment nearbyFragment = new NearbyFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(nearbyFragment, "Nearby")
                    .replace(R.id.fragment_container, nearbyFragment)
                    .addToBackStack("Nearby")
                    .commit();
        } else if (id == R.id.nav_practice) {
            PracticeFragment practiceFragment = new PracticeFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(practiceFragment, "Practice")
                    .replace(R.id.fragment_container, practiceFragment)
                    .addToBackStack("Practice")
                    .commit();
        } else if (id == R.id.nav_about) {
            AboutFragment aboutFragment = new AboutFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
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
            if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[] {
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                }, 200);
            }
        }
    }

    public void downloadVideo(String fromFragment) {
        Toast.makeText(getApplicationContext(), "Downloading", Toast.LENGTH_SHORT).show();
//        if(fromFragment.equals("Abrasion")) {
            InjuryInformationFragment injuriesFragment = (InjuryInformationFragment) getSupportFragmentManager().findFragmentByTag(fromFragment);
            injuriesFragment.downloadTutorial();
//        } else if(fromFragment.equals("Bites")) {
//            BitesFragment bitesFragment = (BitesFragment) getSupportFragmentManager().findFragmentByTag("Bites");
//            bitesFragment.downloadBites();
//        } else if(fromFragment.equals("Burns")) {
//            BurnsFragment burnsFragment = (BurnsFragment) getSupportFragmentManager().findFragmentByTag("Burns");
//            burnsFragment.downloadBurns();
//        } else if(fromFragment.equals("Concussion")) {
//            ConcussionFragment concussionFragment = (ConcussionFragment) getSupportFragmentManager().findFragmentByTag("Concussion");
//            concussionFragment.downloadConcussion();
//        } else if(fromFragment.equals("Contusion")) {
//            ContusionFragment contusionFragment = (ContusionFragment) getSupportFragmentManager().findFragmentByTag("Contusion");
//            contusionFragment.downloadContusion();
//        } else if(fromFragment.equals("Fracture")) {
//            FractureFragment fractureFragment = (FractureFragment) getSupportFragmentManager().findFragmentByTag("Fracture");
//            fractureFragment.downloadFracture();
//        } else if(fromFragment.equals("Laceration")) {
//            LacerationFragment lacerationFragment = (LacerationFragment) getSupportFragmentManager().findFragmentByTag("Laceration");
//            lacerationFragment.downloadLaceration();
//        } else if(fromFragment.equals("Puncture")) {
//            PunctureFragment punctureFragment = (PunctureFragment) getSupportFragmentManager().findFragmentByTag("Puncture");
//            punctureFragment.downloadPuncture();
//        }
    }

    //Added by Raeven
    public void streamVideo (String injuryType, VideoView videoView){
        String vidAddress = "https://s3-ap-southeast-1.amazonaws.com/funtastic4thesis/"+injuryType+".mp4";
        Uri vidUri = Uri.parse(vidAddress);

        videoView.setVideoURI(vidUri);
        MediaController vidControl = new MediaController(this);
        videoView.setMediaController(mediaC);
        mediaC.setAnchorView(videoView);
        videoView.start();
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

    //method to set title bar
    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}



