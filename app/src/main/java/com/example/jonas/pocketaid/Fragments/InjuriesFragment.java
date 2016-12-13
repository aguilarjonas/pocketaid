package com.example.jonas.pocketaid.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.jonas.pocketaid.Adapters.Injury;
import com.example.jonas.pocketaid.Adapters.InjuryListAdapter;
import com.example.jonas.pocketaid.InjuriesFragments.AbrasionFragment;
import com.example.jonas.pocketaid.InjuriesFragments.BitesFragment;
import com.example.jonas.pocketaid.InjuriesFragments.BurnsFragment;
import com.example.jonas.pocketaid.InjuriesFragments.ConcussionFragment;
import com.example.jonas.pocketaid.InjuriesFragments.ContusionFragment;
import com.example.jonas.pocketaid.InjuriesFragments.FractureFragment;
import com.example.jonas.pocketaid.InjuriesFragments.LacerationFragment;
import com.example.jonas.pocketaid.InjuriesFragments.PunctureFragment;
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

        listView = (ListView) rootView.findViewById(R.id.injuries_listview);
        searchView = (SearchView) rootView.findViewById(R.id.injuries_search);

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
                    AbrasionFragment abrasionFragment = new AbrasionFragment();
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.add(abrasionFragment, "Abrasion")
                            .replace(R.id.fragment_container, abrasionFragment)
                            .addToBackStack("Abrasion")
                            .commit();
                } else if(position == 1) {
                    BitesFragment bitesFragment = new BitesFragment();
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.add(bitesFragment, "Bites")
                            .replace(R.id.fragment_container, bitesFragment)
                            .addToBackStack("Bites")
                            .commit();
                } else if(position == 2) {
                    BurnsFragment burnsFragment = new BurnsFragment();
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.add(burnsFragment, "Burns")
                            .replace(R.id.fragment_container, burnsFragment)
                            .addToBackStack("Burns")
                            .commit();
                } else if(position == 3) {
                    ConcussionFragment concussionFragment = new ConcussionFragment();
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.add(concussionFragment, "Concussion")
                            .replace(R.id.fragment_container, concussionFragment)
                            .addToBackStack("Concussion")
                            .commit();
                } else if(position == 4) {
                    ContusionFragment contusionFragment = new ContusionFragment();
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.add(contusionFragment, "Contusion")
                            .replace(R.id.fragment_container, contusionFragment)
                            .addToBackStack("Contusion")
                            .commit();
                } else if(position == 5) {
                    FractureFragment fractureFragment = new FractureFragment();
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.add(fractureFragment, "Fracture")
                            .replace(R.id.fragment_container, fractureFragment)
                            .addToBackStack("Fracture")
                            .commit();
                } else if(position == 6) {
                    LacerationFragment lacerationFragment = new LacerationFragment();
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.add(lacerationFragment, "Laceration")
                            .replace(R.id.fragment_container, lacerationFragment)
                            .addToBackStack("Laceration")
                            .commit();
                } else if(position == 7) {
                    PunctureFragment punctureFragment = new PunctureFragment();
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.add(punctureFragment, "Puncture")
                            .replace(R.id.fragment_container, punctureFragment)
                            .addToBackStack("Puncture")
                            .commit();
                }
            }
        });

        return rootView;
    }

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
