package com.example.jonas.pocketaid.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jonas.pocketaid.R;

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


public class NearbyInformationFragment extends Fragment {

    private TextView hospitalNameHolder;
    private TextView hospitalPlaceIDHolder;
    private TextView hospitalPhoneNumber;
    private ImageButton hospitalCallButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_nearby_hospital_information, container, false);
//        rootView.requestFocus();

        hospitalNameHolder = (TextView)v.findViewById(R.id.textview_hospitalnameinfo);
        hospitalPlaceIDHolder = (TextView)v.findViewById(R.id.textview_placeidinfo);
        hospitalPhoneNumber = (TextView)v.findViewById(R.id.textview_phonenumberinfo);
        hospitalCallButton = (ImageButton)v.findViewById(R.id.image_callhospital);



        String hospitalName = getArguments().getString("chosenHospital");
        String hospitalPlaceID = getArguments().getString("chosenHospitalPlaceID");
        getPhoneNumber(hospitalPlaceID);
        putTheText(hospitalName, hospitalPlaceID);



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
