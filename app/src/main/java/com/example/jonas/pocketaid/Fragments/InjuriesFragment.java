package com.example.jonas.pocketaid.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
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
    InjuryListAdapter listAdapter;

    //list of injuries
    String[] injuries = {
            "Abrasion",
            "Bites",
            "Burns",
            "Concussion",
            "Contusion",
            "Fracture",
            "Laceration",
            "Puncture"
    };

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


    public InjuriesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity)getActivity()).setActionBarTitle("Injuries");
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_injuries, container, false);
        rootView.requestFocus();

        listView = (ListView) rootView.findViewById(R.id.injuries_listview);
        searchView = (SearchView) rootView.findViewById(R.id.injuries_search);
        searchView.setIconified(false);
        searchView.clearFocus();

        listAdapter = new InjuryListAdapter(getActivity(), getInjuries());
        listView.setAdapter(listAdapter);

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

        //clicking an item on list
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0) {
//                    InjuryInformationFragment abrasionFragment = new InjuryInformationFragment();
//                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
//                    fragmentTransaction.add(abrasionFragment, "Abrasion")
//                            .replace(R.id.fragment_container, abrasionFragment)
//                            .addToBackStack("Abrasion")
//                            .commit();
                    InjuryTabLayout injuryTabLayout = new InjuryTabLayout();
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

                    //To make fragments dynamic
                    Bundle args = new Bundle();
                    String injury = "Abrasion";
                    args.putString("injury", injury);
                    injuryTabLayout.setArguments(args);

                    fragmentTransaction.add(injuryTabLayout, "injuryTabs")
                            .replace(R.id.fragment_container, injuryTabLayout)
                            .addToBackStack("injuryTab")
                            .commit();

                } else if(position == 1) {
//                    InjuryInformationFragment abrasionFragment = new InjuryInformationFragment();
//                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
//                    //To make fragments dynamic
//                    Bundle args = new Bundle();
//                    String injury = "Bites";
//                    args.putString("injury", injury);
//                    abrasionFragment.setArguments(args);
//
//                    fragmentTransaction.add(abrasionFragment, "Bites")
//                            .replace(R.id.fragment_container, abrasionFragment)
//                            .addToBackStack("Bites")
//                            .commit();

                    InjuryTabLayout injuryTabLayout = new InjuryTabLayout();
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

                    //To make fragments dynamic
                    Bundle args = new Bundle();
                    String injury = "Bites";
                    args.putString("injury", injury);
                    injuryTabLayout.setArguments(args);

                    fragmentTransaction.add(injuryTabLayout, "injuryTabs")
                            .replace(R.id.fragment_container, injuryTabLayout)
                            .addToBackStack("injuryTab")
                            .commit();
                } else if(position == 2) {
//                    InjuryInformationFragment abrasionFragment = new InjuryInformationFragment();
//                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
//                    //To make fragments dynamic
//                    Bundle args = new Bundle();
//                    String injury = "Burns";
//                    args.putString("injury", injury);
//                    abrasionFragment.setArguments(args);
//
//                    fragmentTransaction.add(abrasionFragment, "Burns")
//                            .replace(R.id.fragment_container, abrasionFragment)
//                            .addToBackStack("Burns")
//                            .commit();
                    InjuryTabLayout injuryTabLayout = new InjuryTabLayout();
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

                    //To make fragments dynamic
                    Bundle args = new Bundle();
                    String injury = "Burns";
                    args.putString("injury", injury);
                    injuryTabLayout.setArguments(args);

                    fragmentTransaction.add(injuryTabLayout, "injuryTabs")
                            .replace(R.id.fragment_container, injuryTabLayout)
                            .addToBackStack("injuryTab")
                            .commit();
                } else if(position == 3) {
//                    InjuryInformationFragment abrasionFragment = new InjuryInformationFragment();
//                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
//                    //To make fragments dynamic
//                    Bundle args = new Bundle();
//                    String injury = "Concussion";
//                    args.putString("injury", injury);
//                    abrasionFragment.setArguments(args);
//
//                    fragmentTransaction.add(abrasionFragment, "Concussion")
//                            .replace(R.id.fragment_container, abrasionFragment)
//                            .addToBackStack("Concussion")
//                            .commit();
                    InjuryTabLayout injuryTabLayout = new InjuryTabLayout();
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

                    //To make fragments dynamic
                    Bundle args = new Bundle();
                    String injury = "Concussion";
                    args.putString("injury", injury);
                    injuryTabLayout.setArguments(args);

                    fragmentTransaction.add(injuryTabLayout, "injuryTabs")
                            .replace(R.id.fragment_container, injuryTabLayout)
                            .addToBackStack("injuryTab")
                            .commit();
                } else if(position == 4) {
//                    InjuryInformationFragment abrasionFragment = new InjuryInformationFragment();
//                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
//                    //To make fragments dynamic
//                    Bundle args = new Bundle();
//                    String injury = "Contusion";
//                    args.putString("injury", injury);
//                    abrasionFragment.setArguments(args);
//
//                    fragmentTransaction.add(abrasionFragment, "Contusion")
//                            .replace(R.id.fragment_container, abrasionFragment)
//                            .addToBackStack("Contusion")
//                            .commit();
                    InjuryTabLayout injuryTabLayout = new InjuryTabLayout();
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

                    //To make fragments dynamic
                    Bundle args = new Bundle();
                    String injury = "Contusion";
                    args.putString("injury", injury);
                    injuryTabLayout.setArguments(args);

                    fragmentTransaction.add(injuryTabLayout, "injuryTabs")
                            .replace(R.id.fragment_container, injuryTabLayout)
                            .addToBackStack("injuryTab")
                            .commit();
                } else if(position == 5) {
//                    InjuryInformationFragment abrasionFragment = new InjuryInformationFragment();
//                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
//                    //To make fragments dynamic
//                    Bundle args = new Bundle();
//                    String injury = "Fracture";
//                    args.putString("injury", injury);
//                    abrasionFragment.setArguments(args);
//
//                    fragmentTransaction.add(abrasionFragment, "Fracture")
//                            .replace(R.id.fragment_container, abrasionFragment)
//                            .addToBackStack("Fracture")
//                            .commit();
                    InjuryTabLayout injuryTabLayout = new InjuryTabLayout();
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

                    //To make fragments dynamic
                    Bundle args = new Bundle();
                    String injury = "Fracture";
                    args.putString("injury", injury);
                    injuryTabLayout.setArguments(args);

                    fragmentTransaction.add(injuryTabLayout, "injuryTabs")
                            .replace(R.id.fragment_container, injuryTabLayout)
                            .addToBackStack("injuryTab")
                            .commit();
                } else if(position == 6) {
//                    InjuryInformationFragment abrasionFragment = new InjuryInformationFragment();
//                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
//                    //To make fragments dynamic
//                    Bundle args = new Bundle();
//                    String injury = "Laceration";
//                    args.putString("injury", injury);
//                    abrasionFragment.setArguments(args);
//
//                    fragmentTransaction.add(abrasionFragment, "Laceration")
//                            .replace(R.id.fragment_container, abrasionFragment)
//                            .addToBackStack("Laceration")
//                            .commit();
                    InjuryTabLayout injuryTabLayout = new InjuryTabLayout();
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

                    //To make fragments dynamic
                    Bundle args = new Bundle();
                    String injury = "Laceration";
                    args.putString("injury", injury);
                    injuryTabLayout.setArguments(args);

                    fragmentTransaction.add(injuryTabLayout, "injuryTabs")
                            .replace(R.id.fragment_container, injuryTabLayout)
                            .addToBackStack("injuryTab")
                            .commit();
                } else if(position == 7) {
//                    InjuryInformationFragment abrasionFragment = new InjuryInformationFragment();
//                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
//                    //To make fragments dynamic
//                    Bundle args = new Bundle();
//                    String injury = "Puncture";
//                    args.putString("injury", injury);
//                    abrasionFragment.setArguments(args);
//
//                    fragmentTransaction.add(abrasionFragment, "Puncture")
//                            .replace(R.id.fragment_container, abrasionFragment)
//                            .addToBackStack("Puncture")
//                            .commit();
                    InjuryTabLayout injuryTabLayout = new InjuryTabLayout();
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

                    //To make fragments dynamic
                    Bundle args = new Bundle();
                    String injury = "Puncture";
                    args.putString("injury", injury);
                    injuryTabLayout.setArguments(args);

                    fragmentTransaction.add(injuryTabLayout, "injuryTabs")
                            .replace(R.id.fragment_container, injuryTabLayout)
                            .addToBackStack("injuryTab")
                            .commit();
                }
            }
        });

        return rootView;
    }

    //adding of injuries to adapter
    private ArrayList<Injury> getInjuries() {
        ArrayList<Injury> injuriesAL = new ArrayList<Injury>();
        Injury injury;

        for(int ctr = 0; ctr < injuries.length; ctr++) {
            injury = new Injury(injuries[ctr], icon[ctr]);
            injuriesAL.add(injury);
        }

        return injuriesAL;
    }

}
