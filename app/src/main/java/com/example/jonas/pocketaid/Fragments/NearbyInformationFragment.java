package com.example.jonas.pocketaid.Fragments;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jonas.pocketaid.R;
import com.example.jonas.pocketaid.SearchModules.GetNearbyPlacesData;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;


public class NearbyInformationFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, OnMapReadyCallback,
        LocationListener {

    private TextView hospitalNameHolder;
    private TextView hospitalPlaceIDHolder;
    private TextView hospitalPhoneNumber;
    private ImageButton hospitalCallButton;

    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    private GoogleMap mMap;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    double latitude;
    double longitude;

    String hospitalName;
    double lat ;
    double lng;






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_nearby_hospital_information, container, false);
//        rootView.requestFocus();

        hospitalNameHolder = (TextView)v.findViewById(R.id.textview_hospitalnameinfo);
        hospitalPlaceIDHolder = (TextView)v.findViewById(R.id.textview_placeidinfo);
        hospitalPhoneNumber = (TextView)v.findViewById(R.id.textview_phonenumberinfo);
        hospitalCallButton = (ImageButton)v.findViewById(R.id.image_callhospital);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_hospitalOnly);
        mapFragment.getMapAsync(this);


        String hospitalName = getArguments().getString("chosenHospital");
        String hospitalPlaceID = getArguments().getString("chosenHospitalPlaceID");
        lat = Double.parseDouble(getArguments().getString("chosenHospitalLat"));
        lng = Double.parseDouble(getArguments().getString("chosenHospitalLng"));
        getPhoneNumber(hospitalPlaceID);
        putTheText(hospitalName, hospitalPlaceID);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }

        //Check if Google Play Services Available or not
        if (!CheckGooglePlayServices()) {
            //Log.d("onCreate", "Finishing test case since Google Play Services are not available");
            getActivity().finish();
        }
        else {
            //Log.d("onCreate","Google Play Services available.");
        }




        return v;
    }



    public String getPhoneNumber (String placeID){

        StringBuilder hospitalInfoURL = new StringBuilder("https://maps.googleapis.com/maps/api/place/details/json?");
        String angelKey = "AIzaSyATuUiZUkEc_UgHuqsBJa1oqaODI-3mLs0";
        String raevenKey = "AIzaSyBRaI6vWSTL-W1cJm-SB60xNBjlbb8TMaU";

        hospitalInfoURL.append("placeid=" + placeID);
        hospitalInfoURL.append("&key=" + angelKey );
        //Main Key = AIzaSyATuUiZUkEc_UgHuqsBJa1oqaODI-3mLs0 - Angel's key
        //Alternative Key = AIzaSyBRaI6vWSTL-W1cJm-SB60xNBjlbb8TMaU - Raeven's key
        new NearbyInformationFragment.JSONTask().execute(hospitalInfoURL.toString());

        return (hospitalInfoURL.toString());
    }



    public void putTheText(String hospitalName, String hospitalPlaceID){
        hospitalNameHolder.setText(hospitalName);
        hospitalPlaceIDHolder.setText(hospitalPlaceID);
    }

    private boolean CheckGooglePlayServices() {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(getActivity().getApplicationContext());
        if(result != ConnectionResult.SUCCESS) {
            if(googleAPI.isUserResolvableError(result)) {
                googleAPI.getErrorDialog(getActivity(), result,
                        0).show();
            }
            return false;
        }
        return true;
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity().getApplicationContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public boolean checkLocationPermission(){
        if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {



        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//        MarkerOptions markerOptions = new MarkerOptions();
//        LatLng latLng = new LatLng(lat, lng);
//        Toast.makeText(getActivity().getApplicationContext(), "Coordinates" + lat + " " + lng, Toast.LENGTH_LONG).show();
//
//        markerOptions.position(latLng);
//        markerOptions.title(hospitalName);
//        mMap.addMarker(markerOptions);
//        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
//        mMap.addMarker(markerOptions);
        //markerOptions.snippet(vicinity);
        //automaticHospitalSearch();

        //Click listener ng pointer dun sa marker.
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

            @Override
            public void onInfoWindowClick(Marker marker) {
                marker.hideInfoWindow();
                String markerTitle = marker.getTitle();
                double dlat = marker.getPosition().latitude;
                double dlong = marker.getPosition().longitude;
                final String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr=%f,%f (%s)", dlat, dlong, markerTitle);
                final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                try {
                    startActivity(intent);
                } catch(ActivityNotFoundException ex) {
                    try {
                        Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                        startActivity(unrestrictedIntent);
                    }
                    catch(ActivityNotFoundException innerEx) {
                        Toast.makeText(getActivity().getApplicationContext(), "Please install a Google Maps application", Toast.LENGTH_LONG).show();
                    }
                }

                // Toast.makeText(getActivity().getApplicationContext(),"Ooops Nakiliti ako", Toast.LENGTH_LONG).show();

            }

        });

        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                    android.Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
                //automaticHospitalSearch();
            }
        }
        else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("onLocationChanged", "entered");

        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        //Place current location marker
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        LatLng latLng = new LatLng(lat, lng);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        String hospitalHolder2 = getArguments().getString("chosenHospital");
        markerOptions.title(hospitalHolder2);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mCurrLocationMarker = mMap.addMarker(markerOptions);

        //move map camera
        float zoomLevel = (float) 16.0; //This goes up to 21

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel));
        //mMap.animateCamera(CameraUpdateFactory.zoomIn());
        Toast.makeText(getActivity().getApplicationContext(),"Your Current Location", Toast.LENGTH_LONG).show();

        //Insert *Conditions pag di naka on yung mga Location and stuff* here.
//        automaticHospitalSearch();

        //Log.d("onLocationChanged", String.format("latitude:%.3f longitude:%.3f",latitude,longitude));

        //stop location updates
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            Log.d("onLocationChanged", "Removing Location Updates");
        }
        Log.d("onLocationChanged", "Exit");
    }


    public class JSONTask extends AsyncTask<String, String, String> {

        HttpURLConnection connection = null;
        JSONObject json;
        BufferedReader reader = null;

        @Override
        protected String doInBackground(String... params) {
            try {
                Log.d("onClick", "ANDITO AKO YOOHOOO");

                URL url2 = new URL(params[0]);
                connection = (HttpURLConnection)
                url2.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null){
                    buffer.append(line);
                }

                String finalJSON = buffer.toString();
                ArrayList<String> hospitalInformation = new ArrayList<>();
                JSONObject parentObject = new JSONObject(finalJSON);
                JSONObject parentArray = parentObject.getJSONObject("result");
                //JSONArray  parsedevents = parentArray.getJSONArray("events");
                //int i = 0;

                //JSONObject finalObject = parentArray.getJSONObject(0);
                String phoneNumber = parentArray.getString("formatted_phone_number");
                hospitalInformation.add(phoneNumber);

                return phoneNumber;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(final String s) {

            if (s == null){
                hospitalPhoneNumber.setText("No Phone number Available");
                hospitalCallButton.setSaveEnabled(false);

                hospitalCallButton. setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getActivity().getApplicationContext(),"No phone number available", Toast.LENGTH_LONG).show();

                    }
                });
            }

            else {
                hospitalPhoneNumber.setText(s);
                hospitalCallButton. setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent call = new Intent(Intent.ACTION_DIAL);
                        call.setData(Uri.parse("tel:"+s));
                        startActivity(call);
                    }
                });
            }


        }
    }




}
