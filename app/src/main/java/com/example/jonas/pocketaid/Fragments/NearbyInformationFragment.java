package com.example.jonas.pocketaid.Fragments;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jonas.pocketaid.MainActivity;
import com.example.jonas.pocketaid.R;
import com.example.jonas.pocketaid.RouteModules.DirectionsJSONParser;
import com.example.jonas.pocketaid.RouteModules.DownloadDirectionUrl;
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
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

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
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


public class NearbyInformationFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, OnMapReadyCallback,
        LocationListener {

    private TextView hospitalNameHolder;
    private TextView hospitalVicinityHolder;
    private TextView hospitalPhoneNumber;
    private TextView hospitalDistance;
    private ImageButton hospitalCallButton;
    private ImageButton hospitalGoToButton;


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
    String hospitalVicinity;

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity)getActivity()).setActionBarTitle(getArguments().getString("chosenHospital"));
        View v = inflater.inflate(R.layout.fragment_nearby_hospital_information, container, false);

        //changes menu button to Up or back button
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((MainActivity) getActivity()).resetActionBar(true, DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        //hide FAB
        ((MainActivity) getActivity()).hideOrShowFAB("hide");

        beforeStartFragment(v);
        checkAndroidVersion();

        return v;
    }

    /*
        Function Name : beforeStartFragment
        Function Description : This function will be called in the onCreateView.
                               This function has been created to maintain readability of codes.
        Function Developer : Raeven Bauto
     */
    private void beforeStartFragment(View v){
        hospitalNameHolder = (TextView)v.findViewById(R.id.textview_hospitalnameinfo);
        hospitalVicinityHolder = (TextView)v.findViewById(R.id.textview_hospitaladdress);
        hospitalPhoneNumber = (TextView)v.findViewById(R.id.textview_phonenumberinfo);
        hospitalDistance = (TextView)v.findViewById(R.id.textView_distance);

        hospitalCallButton = (ImageButton)v.findViewById(R.id.image_callhospital);
        hospitalGoToButton = (ImageButton)v.findViewById(R.id.image_gotohospital);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_hospitalOnly);
        mapFragment.getMapAsync(this);

        final String hospitalName = getArguments().getString("chosenHospital");
        String hospitalPlaceID = getArguments().getString("chosenHospitalPlaceID");
        lat = Double.parseDouble(getArguments().getString("chosenHospitalLat"));
        lng = Double.parseDouble(getArguments().getString("chosenHospitalLng"));
        hospitalVicinity = getArguments().getString("chosenHospitalVicinity");

        hospitalGoToButton. setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String strUri = "http://maps.google.com/maps?q=loc:" + lat + "," + lng + " (" + hospitalName + ")";
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(strUri));

                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");

                startActivity(intent);
            }
        });

        getPhoneNumber(hospitalPlaceID);
        putTheText(hospitalName, hospitalVicinity);
    }

     /*
        Function Name : getPhoneNumber
        Function Description : This function will get the phone number from
                               the JSON returned by Google Maps.
        Function Developer : Raeven Bauto
     */

    public String getPhoneNumber (String placeID){

        StringBuilder hospitalInfoURL = new StringBuilder("https://maps.googleapis.com/maps/api/place/details/json?");
        String angelKey = "AIzaSyATuUiZUkEc_UgHuqsBJa1oqaODI-3mLs0";
        String raevenKey = "AIzaSyBRaI6vWSTL-W1cJm-SB60xNBjlbb8TMaU";
        String testKey = "AIzaSyDaHKjPR-NLen5OL_UfGTr53d0oP6S0tzM";

        hospitalInfoURL.append("placeid=" + placeID);
        hospitalInfoURL.append("&key=" + testKey );

        Log.d("PLACEID JSON", String.valueOf(hospitalInfoURL));
        //Main Key = AIzaSyATuUiZUkEc_UgHuqsBJa1oqaODI-3mLs0 - Angel's key
        //Alternative Key = AIzaSyBRaI6vWSTL-W1cJm-SB60xNBjlbb8TMaU - Raeven's key
        new NearbyInformationFragment.JSONTask().execute(hospitalInfoURL.toString());

        return (hospitalInfoURL.toString());
    }

    /*
        Function Name : putTheText
        Function Description : This function will replace the textview in the XML
                               with the hospital's phone number.
        Function Developer : Raeven Bauto
     */

    private void putTheText(String hospitalName, String hospitalVicinity){
        hospitalNameHolder.setText(hospitalName);
        hospitalVicinityHolder.setText(hospitalVicinity);
    }

     /*
        Function Name : onMapReady
        Function Description : This function will run if the MapFragment is ready to be used.
        Function Developer : Raeven Bauto
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setMapToolbarEnabled(false);
        mMap.setMapStyle(new MapStyleOptions(getResources()
                .getString(R.string.custom_map)));

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
            }

        });
        initializeGooglePlay(mMap);
    }

    public void checkAndroidVersion(){
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }

        //Check if Google Play Services Available or not
        if (!CheckGooglePlayServices()) {
            Log.d("onCreate", "Finishing test case since Google Play Services are not available");
            getActivity().finish();
        }
        else {
            Log.d("onCreate","Google Play Services available.");
        }
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

    private void initializeGooglePlay(GoogleMap mMap){
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
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        //markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.doctor71));
        mCurrLocationMarker = mMap.addMarker(markerOptions);

        //move map camera
        float zoomLevel = (float) 16.0; //This goes up to 21

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel));
        //mMap.animateCamera(CameraUpdateFactory.zoomIn());
        //Toast.makeText(getActivity().getApplicationContext(),"Your Current Location", Toast.LENGTH_LONG).show();

        LatLng currPosition = new LatLng(latitude, longitude);
        LatLng destiPosition = new LatLng(lat, lng);

        // Getting URL to the Google Directions API
        DownloadDirectionUrl download = new DownloadDirectionUrl();
        String url = download.getDirectionsUrl(currPosition, destiPosition);

        // Start downloading json data from Google Directions API.
        DownloadTask downloadTask = new DownloadTask();

        downloadTask.execute(url);


        //stop location updates
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            Log.d("onLocationChanged", "Removing Location Updates");
        }
        Log.d("onLocationChanged", "Exit");
    }

    /*
        Function Name : JSONTask
        Function Description : This function will get the JSON from Google Maps without affecting
                               the UI.
        Function Developer : Raeven Bauto
     */

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
                        Toast.makeText(getActivity().getApplicationContext(),"No Phone Number Available", Toast.LENGTH_LONG).show();

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


    // Fetches data from url passed
    public class DownloadTask extends AsyncTask<String, Void, String> {

        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try{
                // Fetching the data from web service
                DownloadDirectionUrl download = new DownloadDirectionUrl();
                data = download.downloadUrl(url[0]);
            }catch(Exception e){
                Log.d("Background Task",e.toString());
            }
            return data;
        }

        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);
        }
    }

    /** A class to parse the Google Places in JSON format */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String,String>>> >{
        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try{
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();
                JSONArray jRoutes = null;


                jRoutes = jObject.getJSONArray("routes");

                // Starts parsing data
                routes = parser.parse(jObject);
            }catch(Exception e){
                e.printStackTrace();
            }
            return routes;
        }

        @Override
        public void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("Loading...");
            progressDialog.show();
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points = null;
            PolylineOptions lineOptions = null;
            MarkerOptions markerOptions = new MarkerOptions();
            String distance = "";

            try {
                // Traversing through all the routes
                for(int i=0;i<result.size();i++){
                    points = new ArrayList<LatLng>();
                    lineOptions = new PolylineOptions();

                    // Fetching i-th route
                    List<HashMap<String, String>> path = result.get(i);

                    // Fetching all the points in i-th route
                    for(int j=0;j<path.size();j++){
                        HashMap<String,String> point = path.get(j);

                        double lat = Double.parseDouble(point.get("lat"));
                        double lng = Double.parseDouble(point.get("lng"));

                        //To instantiate only once.
                        if (j == 0){
                            distance = point.get("distance");
                            Log.d("DISTANCEEEEEe", distance);
                        }

                        LatLng position = new LatLng(lat, lng);

                        points.add(position);
                    }

                    // Adding all the points in the route to LineOptions
                    lineOptions.addAll(points);
                    lineOptions.width(25);
                    lineOptions.color(Color.RED);
                    lineOptions.geodesic(true);
                }

                hospitalDistance.setText(distance);


                // Drawing polyline in the Google Map for the i-th route
                mMap.addPolyline(lineOptions);
            }
            catch (NullPointerException e){
                Toast.makeText(getActivity().getApplicationContext(), "No Internet Connection.", Toast.LENGTH_LONG).show();
            }

            progressDialog.dismiss();
        }
    }



}
