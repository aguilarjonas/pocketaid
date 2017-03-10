package com.example.jonas.pocketaid.Fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jonas.pocketaid.Adapters.Injury;
import com.example.jonas.pocketaid.Adapters.InjuryListAdapter;
import com.example.jonas.pocketaid.Adapters.InjuryTabLayout;
import com.example.jonas.pocketaid.MainActivity;
import com.example.jonas.pocketaid.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class InjuriesFragment extends Fragment {

    private ListView listView;
    private SearchView searchView;
    private String[] injuries;
    InjuryListAdapter listAdapter;

    //icons
    Integer[] icon = {
            R.drawable.ic_abrasion,
            R.drawable.ic_bites,
            R.drawable.ic_burns,
            R.drawable.ic_concussion,
            R.drawable.ic_contusion,
            R.drawable.ic_fracture,
            R.drawable.ic_laceration,
            R.drawable.ic_puncture
    };


    public InjuriesFragment() { /** Required empty public constructor **/ }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity)getActivity()).setActionBarTitle(getResources().getString(R.string.injuries));
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_injuries, container, false);
        ((MainActivity)getActivity()).resetActionBar(false, DrawerLayout.LOCK_MODE_UNLOCKED);
        ((MainActivity) getActivity()).hideOrShowFAB("show");
        rootView.requestFocus();

        initializeViewsAndConfigure(rootView);
        searchViewListener();
        listViewOnItemClick();

        return rootView;
    }

    /*
        Function Name : initializeViewsAndConfigure
        Function Description :  This function will be called in the onCreateView
                                This function initializes the views needed in the fragment
                                This function sets the attributes of the SearchView
                                This function creates and sets an adapter in the ListView
        Function Developer : Jonas Aguilar
     */
    public void initializeViewsAndConfigure(ViewGroup rootView) {
        injuries = getResources().getStringArray(R.array.injuries);
        listView = (ListView) rootView.findViewById(R.id.injuries_listview);
        searchView = (SearchView) rootView.findViewById(R.id.injuries_search);

        searchView.setIconified(false);
        searchView.clearFocus();

        listAdapter = new InjuryListAdapter(getActivity(), setAndGetInjuries(injuries));
        listView.setAdapter(listAdapter);
    }

    /*
        Function Name : searchViewListener
        Function Description :  This function will be called in the onCreateView
                                This function listens for any changes in the text when using the SearchView
        Function Developer : Jonas Aguilar
     */
    public void searchViewListener() {
        //searching for an injury
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                listAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    /*
        Function Name : listViewOnItemClick
        Function Description :  This function will be called in the onCreateView
                                This function observes if an item in the list is clicked
        Function Developer : Jonas Aguilar
     */
    public void listViewOnItemClick() {
        //clicking an item on list
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView injuryName = (TextView) view.findViewById(R.id.injury_name);
                String injuryInString = injuryName.getText().toString().toLowerCase();
                if(injuryInString.equals(getResources().getString(R.string.abrasion).toLowerCase())) {
                    setInjuryTabLayout(getResources().getString(R.string.abrasion));
                } else if(injuryInString.equals(getResources().getString(R.string.bites).toLowerCase())) {
                    setInjuryTabLayout(getResources().getString(R.string.bites));
                } else if(injuryInString.equals(getResources().getString(R.string.burns_list).toLowerCase())) {
                    setInjuryTabLayout(getResources().getString(R.string.burns_list));
                } else if(injuryInString.equals(getResources().getString(R.string.concussion).toLowerCase())) {
                    setInjuryTabLayout(getResources().getString(R.string.concussion));
                } else if(injuryInString.equals(getResources().getString(R.string.contusion).toLowerCase())) {
                    setInjuryTabLayout(getResources().getString(R.string.contusion));
                } else if(injuryInString.equals(getResources().getString(R.string.fracture).toLowerCase())) {
                    setInjuryTabLayout(getResources().getString(R.string.fracture));
                } else if(injuryInString.equals(getResources().getString(R.string.laceration).toLowerCase())) {
                    setInjuryTabLayout(getResources().getString(R.string.laceration));
                } else if(injuryInString.equals(getResources().getString(R.string.puncture).toLowerCase())) {
                    setInjuryTabLayout(getResources().getString(R.string.puncture));
                }
            }
        });
    }

    /*
        Function Name : setInjuryTabLayout
        Function Description :  This function will be called in the listViewOnItemClick
                                This function puts an argument and passes it to InjuryTabLayout
        Function Developer : Jonas Aguilar
     */
    public void setInjuryTabLayout(String injury) {
        InjuryTabLayout injuryTabLayout = new InjuryTabLayout();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                android.R.anim.slide_in_left, android.R.anim.slide_out_right);

        //To make fragments dynamic
        Bundle args = new Bundle();
        args.putString("injury", injury);
        injuryTabLayout.setArguments(args);

        fragmentTransaction.add(injuryTabLayout, "injuryTabs")
                .replace(R.id.fragment_container, injuryTabLayout)
                .addToBackStack("injuryTab")
                .commit();

        hideKeyboard();
    }

    /*
        Function Name : setAndGetInjuries
        Function Description :  This function will be called in the initializeViewsAndConfigure
                                This function adds and returns the list of injuries
        Function Developer : Jonas Aguilar
     */
    private ArrayList<Injury> setAndGetInjuries(String[] injuries) {
        ArrayList<Injury> injuriesAL = new ArrayList<Injury>();
        Injury injury;

        for(int ctr = 0; ctr < injuries.length; ctr++) {
            injury = new Injury(injuries[ctr], icon[ctr]);
            injuriesAL.add(injury);
        }

        return injuriesAL;
    }

    /*
        Function Name : hideKeyboard
        Function Description :  This function will be called in the setInjuryTabLayout
                                This function hides the keyboard after moving from the InjuriesFragment to InjuryTabLayout
        Function Developer : Jonas Aguilar
     */
    public void hideKeyboard() {
        try {
            InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(getContext().INPUT_METHOD_SERVICE);
            View view = ((Activity)getContext()).getCurrentFocus();

            if (view != null) {
                inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
