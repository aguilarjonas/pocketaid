package com.example.jonas.pocketaid.Fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jonas.pocketaid.Adapters.Hospital;
import com.example.jonas.pocketaid.Adapters.HospitalListAdapter;
import com.example.jonas.pocketaid.MainActivity;
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
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

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
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NearbyFragment extends Fragment implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;
    ViewGroup rootView;
    MapView mapView;
    double latitude;
    double longitude;
    private RecyclerView recyclerViewNearby;
    private HospitalListAdapter adapter;
    private SlidingUpPanelLayout mLayout;
    private ImageView ivAnchor;
    private GoogleMap mMap;
    private Snackbar snackbar;

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    String bufferCatcher = "";


    public NearbyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity)getActivity()).setActionBarTitle("Nearby Hospitals");

        //unlocks menu bar or drawer
        ((MainActivity)getActivity()).resetActionBar(false, DrawerLayout.LOCK_MODE_UNLOCKED);

        //hide FAB
        ((MainActivity) getActivity()).hideOrShowFAB("hide");
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_nearby, container, false);

        checkVersionAndGooglePlayServices();
        initializeViews(rootView);
        configureMap(savedInstanceState);
        configureSlidingPanel();

        return rootView;
    }

     /*
        Function Name : initializeViews
        Function Description : This function will be called in the onCreateView.
                               This function will initialize the following elements from the XML.
        Function Developer : Raeven Bauto
     */
    public void initializeViews(ViewGroup rootView) {
        mapView = (MapView) rootView.findViewById(R.id.map);
        recyclerViewNearby = (RecyclerView)rootView.findViewById(R.id.recyclerView_nearby);
        ivAnchor = (ImageView) rootView.findViewById(R.id.nearby_anchor);
        mLayout = (SlidingUpPanelLayout) rootView.findViewById(R.id.sliding_layout);
    }

     /*
        Function Name : configureMap
        Function Description : This function will be called in the onCreateView.
                               This function will initialize the map and check the map
                               to the server of Google if it is ready for use.
        Function Developer : Raeven Bauto
     */
    public void configureMap(Bundle savedInstanceState) {
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
    }

     /*
        Function Name : configureSlidingPanel
        Function Description : This function will be called in the onCreateView.
                               This function will initialize and customize the sliding panel
                               in the Nearby Hospitals.
        Function Developer : Raeven Bauto
     */
    public void configureSlidingPanel() {
        mLayout.setAnchorPoint(0.5f);
        mLayout.setPanelState(SlidingUpPanelLayout.PanelState.ANCHORED);

        mLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) { /** DO NOTHING **/ }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                if(newState == SlidingUpPanelLayout.PanelState.EXPANDED) {
                    ivAnchor.setImageResource(R.drawable.ic_down);
                } else if(newState == SlidingUpPanelLayout.PanelState.COLLAPSED || newState == SlidingUpPanelLayout.PanelState.ANCHORED) {
                    ivAnchor.setImageResource(R.drawable.ic_up);
                }
            }
        });
    }

    /*
       Function Name : checkVersionAndGooglePlayServices
       Function Description : This function will check the phone's OS version.
       Function Developer : Angel Montoya
    */
    public void checkVersionAndGooglePlayServices() {
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

    @Override
    public void onStart() {
        super.onStart();
        boolean isLocationEnabled = false;
        try {
            if (isConnectedToNetwork(getContext()) == true) {

                if (isLocationServiceEnabled() == false){
                    //Toast.makeText(getActivity().getApplicationContext(),"No location", Toast.LENGTH_LONG).show();
                    snackbar = Snackbar.make(getView(), "No location service", Snackbar.LENGTH_INDEFINITE);
                    snackbar.setAction("Retry", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    getActivity().getSupportFragmentManager().popBackStack();
                                    NearbyFragment nearbyFragment = new NearbyFragment();
                                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                                    fragmentTransaction.add(nearbyFragment, "Nearby")
                                            .replace(R.id.fragment_container, nearbyFragment)
                                            .addToBackStack("Nearby")
                                            .commit();
                                }
                            })
                            .show();
                }

                else
                    isLocationEnabled = true;

            }

            if (isConnectedToNetwork(getContext()) == false && isLocationServiceEnabled() == false) {
                snackbar = Snackbar.make(getView(), "No internet connection and location service.", Snackbar.LENGTH_INDEFINITE);
                snackbar.setAction("Retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getActivity().getSupportFragmentManager().popBackStack();
                        NearbyFragment nearbyFragment = new NearbyFragment();
                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.add(nearbyFragment, "Nearby")
                                .replace(R.id.fragment_container, nearbyFragment)
                                .addToBackStack("Nearby")
                                .commit();
                    }
                })
                        .show();
            }
        } catch (NullPointerException e){
            Toast.makeText(getActivity().getApplicationContext(),"No location211", Toast.LENGTH_LONG).show();

        }
        
    }

     /*
        Function Name : CheckGooglePlayServices
        Function Description : This function has will check the Google Play Services
        Function Developer : Angel Montoya
     */

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

      /*
        Function Name : automaticHospitalSearch
        Function Description : This function will trigger functions that will retrieve the
                                nearby hospitals
        Function Developer : Raeven Bauto
     */
    public void automaticHospitalSearch() {
        String Hospital = "hospital";
        //Log.d("onClick", "Button is Clicked");
        try {
            mMap.clear();
        }
        catch (NullPointerException e){

        }
        ArrayList<String> url = new ArrayList<String>();

        String urlHolder = getUrl(latitude, longitude, Hospital);

        url.add(urlHolder);



        new JSONTask().execute(url);


    }

     /*
        Function Name : catchBufferString
        Function Description : This function will catch the JSON from DataParser.java.
        Function Developer : Raeven Bauto
     */
    public void catchBufferString(String holder){
        bufferCatcher = holder;
        //Log.e("HELLO", bufferCatcher);
    }

    /*
        Function Name : buildgoogleApiClient
        Function Description : This function will build the Google API client.
        Function Developer : Angel Montoya
     */
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity().getApplicationContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
        //automaticHospitalSearch();
    }


     /*
        Function Name : getUrl
        Function Description : This function will build the URL in getting the JSON.
        Function Developer : Raeven Bauto
     */
    private String getUrl(double latitude, double longitude, String nearbyPlace) {
        StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlacesUrl.append("location=" + latitude + "," + longitude);
        //googlePlacesUrl.append("&radius=8000");
        googlePlacesUrl.append("&rankby=distance");
        googlePlacesUrl.append("&name=hospital|center|medical");
        googlePlacesUrl.append("&type=" + nearbyPlace);
        googlePlacesUrl.append("&sensor=true");
        googlePlacesUrl.append("&key=" + "AIzaSyBRaI6vWSTL-W1cJm-SB60xNBjlbb8TMaU"); //dito yung api key nasa sticky note
        //Main Key = AIzaSyCwWyLYaWT48CXkNBE7Le0naOl-A5VUVUE - Angel's key
        //Alternative Key = AIzaSyBRaI6vWSTL-W1cJm-SB60xNBjlbb8TMaU - Raeven's key
        Log.d("getUrl", googlePlacesUrl.toString());
        return (googlePlacesUrl.toString());
    }

     /*
        Function Name : isConnectedToNetwork
        Function Description : This function will check if the phone is connected to the internet.
        Function Developer : Raeven Bauto
     */

    public static boolean isConnectedToNetwork(Context context)
    {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();

    }

     /*
        Function Name : isLocationServiceEnabled
        Function Description : This function will check if the location is turned on.
        Function Developer : Raeven Bauto
     */

    public boolean isLocationServiceEnabled(){
        try {
            LocationManager locManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

            if (locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                //GPS enabled
                return true;
            }

            else{
                //GPS disabled
                return false;
            }
        }

        catch (NullPointerException e){

        }
        return false;
    }

     /*
        Function Name : checkLocationPermission
        Function Description : This function will check if location is allowed to be used
                                by the application.
        Function Developer : Angel Montoya
     */
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
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
        if(snackbar != null && snackbar.isShown()) {
            snackbar.dismiss();
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }


     /*
        Function Name : onMapReady
        Function Description : This function will be called if the map has been synced with
                               Google API
        Function Developer : Raeven Bauto
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setMapToolbarEnabled(false);
        mMap.setMapStyle(new MapStyleOptions(getResources()
                .getString(R.string.custom_map)));


        //automaticHospitalSearch();

        //Click listener ng pointer dun sa marker.
//        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
//
//            @Override
//            public void onInfoWindowClick(Marker marker) {
//                marker.hideInfoWindow();
//                String markerTitle = marker.getTitle();
//                double dlat = marker.getPosition().latitude;
//                double dlong = marker.getPosition().longitude;
//                final String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr=%f,%f (%s)", dlat, dlong, markerTitle);
//                final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
//                try {
//                    startActivity(intent);
//                } catch(ActivityNotFoundException ex) {
//                    try {
//                        Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
//                        startActivity(unrestrictedIntent);
//                    }
//                    catch(ActivityNotFoundException innerEx) {
//                        Toast.makeText(getActivity().getApplicationContext(), "Please install a Google Maps application", Toast.LENGTH_LONG).show();
//                    }
//                }
//
//               // Toast.makeText(getActivity().getApplicationContext(),"Ooops Nakiliti ako", Toast.LENGTH_LONG).show();
//
//            }
//
//        });

        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                    android.Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
                //automaticHospitalSearch();
            }
        } else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }
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



    /*
        Function Name : onLocationChanged
        Function Description : This function will be called if location is enabled as well
                               as if location is enabled.
        Function Developer : Raeven Bauto
     */

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
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        //markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mCurrLocationMarker = mMap.addMarker(markerOptions);

        //move map camera
        float zoomLevel = (float) 16.0; //This goes up to 21

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel));
        //mMap.animateCamera(CameraUpdateFactory.zoomIn());
        //Toast.makeText(getActivity().getApplicationContext(),"Your Current Location", Toast.LENGTH_LONG).show();

        //Insert *Conditions pag di naka on yung mga Location and stuff* here.



        if (isLocationServiceEnabled()){
           if (isConnectedToNetwork(getContext()) == false){
//                Toast.makeText(getActivity().getApplicationContext(),"No internetasda connection", Toast.LENGTH_LONG).show();
               Snackbar.make(getView(), "No internet connection", Snackbar.LENGTH_INDEFINITE)
                       .setAction("Retry", new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {
                               Fragment nearbyFragment = getFragmentManager().findFragmentByTag("Nearby");
                               FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                               fragmentTransaction.detach(nearbyFragment);
                               fragmentTransaction.attach(nearbyFragment);
                               fragmentTransaction.commit();
                           }
                       })
                       .show();
            }

            else
               automaticHospitalSearch();
        }

        Log.d("onLocationChanged", String.format("latitude:%.3f longitude:%.3f",latitude,longitude));

        //stop location updates
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            Log.d("onLocationChanged", "Removing Location Updates");
        }
        Log.d("onLocationChanged", "Exit");

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }




    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted. Do the
                    // contacts-related task you need to do.
                    if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                            android.Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }

                } else {

                    // Permission denied, Disable the functionality that depends on this permission.
                    Toast.makeText(getActivity().getApplicationContext(), "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other permissions this app might request.
            // You can add here other case statements according to your requirement.
        }
    }


     /*
        Function Name : JSONTask
        Function Description : This function will be called by automaticHospitalSearch
                               to get the JSON from GoogleMaps and get the required
                               information to be displayed in the Nearby Hospitals.
                               In addition this function will mark the hospitals in the
                               map.
        Function Developer : Raeven Bauto
     */


    public class JSONTask extends AsyncTask<ArrayList<String>, String, ArrayList<String>>{
        int counter = 0;
        String hospitalName = "";
        String hospitalVicinity = "";
        String hospitalPlaceID = "";
        String hospitalLatitude = "";
        String hospitalLongitude = "";
        Hospital hospitalClass = new Hospital(hospitalName, hospitalVicinity, hospitalPlaceID, hospitalLatitude, hospitalLongitude);
        ProgressDialog progressDialog = new ProgressDialog(getActivity());

        @Override
        protected ArrayList<String> doInBackground(ArrayList<String>... params) {
            HttpURLConnection connection = null;
            JSONObject json;
            BufferedReader reader = null;

            try{
                URL url2 = new URL(params[0].get(0));
                connection = (HttpURLConnection) url2.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null){
                    buffer.append(line);
                }

                String finalJSON = buffer.toString();
                ArrayList<String> JSONList = new ArrayList<>();
                JSONList.add(finalJSON);
                ArrayList<String> hospitalNamesList = new ArrayList<>();
                ArrayList<String> hospitalVicinitiesList = new ArrayList<>();
                ArrayList<String> hospitalPlaceIDList = new ArrayList<>();
                ArrayList<String> hospitalLatitudeList = new ArrayList<>();
                ArrayList<String> hospitalLongitudeList = new ArrayList<>();


                JSONObject parentObject = new JSONObject(finalJSON);
                JSONArray parentArray = parentObject.getJSONArray("results");

                int i = 0;

                counter = parentArray.length();
                while (i != counter){
                    JSONObject finalObject = parentArray.getJSONObject(i);
//                    JSONObject finalObject2 = parentArray3.getJSONObject(i);

                    String hospitalName = finalObject.getString("name");
                    hospitalNamesList.add(hospitalName);

                    String hospitalVicinity = finalObject.getString("vicinity");
                    hospitalVicinitiesList.add(hospitalVicinity);

                    String hospitalPlaceID = finalObject.getString("place_id");
                    hospitalPlaceIDList.add(hospitalPlaceID);

                    JSONObject hospitalLatitude1 = finalObject.getJSONObject("geometry");
                    JSONObject hospitalLatitude2 = hospitalLatitude1.getJSONObject("location");
                    hospitalLatitude = hospitalLatitude2.getString("lat");
                    hospitalLatitudeList.add(hospitalLatitude);

                    JSONObject hospitalLongitude1 = finalObject.getJSONObject("geometry");
                    JSONObject hospitalLongitude2 = hospitalLongitude1.getJSONObject("location");
                    hospitalLongitude = hospitalLatitude2.getString("lng");
                    hospitalLongitudeList.add(hospitalLongitude);

                    i++;
                }

                hospitalClass.putHospitalInformationList(hospitalNamesList, hospitalVicinitiesList, hospitalPlaceIDList,
                        hospitalLatitudeList, hospitalLongitudeList);
                return JSONList;
            }
            catch (MalformedURLException e){
                e.printStackTrace();
            }
            catch (IOException e){
                e.printStackTrace();
            }
            catch (JSONException e){
                e.printStackTrace();
            }

            finally{
                if (connection != null){
                    connection.disconnect();
                }
                try {
                    if (reader != null){
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //return null;
            return null;
        }

        @Override
        public void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        protected void onPostExecute(ArrayList<String> testingArray) {
            //super.onPostExecute();
            //Log.d("DALIRI", "DALIRI MO");
            Object[] DataTransfer = new Object[2];
            DataTransfer[0] = mMap;
            DataTransfer[1] = testingArray.get(0).toString();
            //Log.d("onClick", url);
            GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
            getNearbyPlacesData.execute(DataTransfer);

            List<Hospital> data = new ArrayList<>();
            int i = 0;
            ArrayList<String> hospitalNameList = hospitalClass.getHospitalNameList();
            ArrayList<String> hospitalVicinityList = hospitalClass.getHospitalVicinityList();
            ArrayList<String> hospitalPlaceIDList = hospitalClass.getHospitalPlaceIDList();
            ArrayList<String> hospitalLatitudeList = hospitalClass.getHospitalLatitudeList();
            ArrayList<String> hospitalLongitudeList = hospitalClass.getHospitalLongitudeList();

            while (i != counter){

                String hospitalName = hospitalNameList.get(i);
                String hospitalVicinity = hospitalVicinityList.get(i);
                String hospitalPlaceID = hospitalPlaceIDList.get(i);
                String latitude = hospitalLatitudeList.get(i);
                String longitude = hospitalLongitudeList.get(i);

                //Toast.makeText(getActivity().getApplicationContext(), latitude, Toast.LENGTH_LONG).show();

                Hospital nHospital = new Hospital(hospitalName, hospitalVicinity, hospitalPlaceID, latitude, longitude);
                data.add(nHospital);

                i++;
            }


            adapter = new HospitalListAdapter(getActivity(), data);
            recyclerViewNearby.setLayoutManager(new GridLayoutManager(getActivity(), 1));
            recyclerViewNearby.setItemAnimator(new DefaultItemAnimator());
            recyclerViewNearby.setAdapter(adapter);
            //tvData.setText(result);
            progressDialog.dismiss();
        }
    }

}


