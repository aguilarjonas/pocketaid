package com.example.jonas.pocketaid.Adapters;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.example.jonas.pocketaid.InjuriesFragments.InjuryInformationFragment;
import com.example.jonas.pocketaid.InjuriesFragments.InjuryInformationSecondTabFragment;
import com.example.jonas.pocketaid.InjuriesFragments.InjuryOverviewFragment;
import com.example.jonas.pocketaid.MainActivity;
import com.example.jonas.pocketaid.R;

/**
 * Created by alec on 12/23/16.
 */

public class InjuryTabLayout extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int tab_numbers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((MainActivity)getActivity()).setActionBarTitle(getArguments().getString("injury"));
        View rootView = inflater.inflate(R.layout.layout_injury_tabs, container, false);

        //changes menu button to Up or Back button
        ((MainActivity) getActivity()).resetActionBar(true, DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((MainActivity) getActivity()).hideOrShowFAB("hide");

        tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);

        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        return rootView;
    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            String chosenInjury = getArguments().getString("injury");

            if(chosenInjury.toLowerCase().equals("bites")) {
                switch (position) {
                    case 0:
                        return setOverviewFragment(chosenInjury);
                    case 1:
                        return setInjuryInformationFragment("animal");
                    case 2:
                        return setInjuryInformationSecondTabFragment("insect");
                }
            } else if(chosenInjury.toLowerCase().equals("burns")) {
                switch (position) {
                    case 0:
                        return setOverviewFragment(chosenInjury);
                    case 1:
                        return setInjuryInformationFragment("first_second_degree");
                    case 2:
                        return setInjuryInformationSecondTabFragment("third_degree");
                }
            } else if(chosenInjury.toLowerCase().equals("laceration")) {
                switch (position) {
                    case 0:
                        return setOverviewFragment(chosenInjury);
                    case 1:
                        return setInjuryInformationFragment("major");
                    case 2:
                        return setInjuryInformationSecondTabFragment("minor");
                }
            } else if(chosenInjury.toLowerCase().equals("puncture")) {
                switch (position) {
                    case 0:
                        return setOverviewFragment(chosenInjury);
                    case 1:
                        return setInjuryInformationFragment("severe");
                    case 2:
                        return setInjuryInformationSecondTabFragment("slight");
                }
            } else {
                switch (position) {
                    case 0:
                        return setOverviewFragment(chosenInjury);
                    case 1:
                        return setInjuryInformationFragment(chosenInjury);
                }
            }

            return null;
        }

        @Override
        public int getCount() {
            String chosenInjury = getArguments().getString("injury");
            if(chosenInjury.toLowerCase().equals("bites") ||
                    chosenInjury.toLowerCase().equals("burns") ||
                    chosenInjury.toLowerCase().equals("laceration") ||
                    chosenInjury.toLowerCase().equals("puncture")) {
                tab_numbers = 3;
            } else {
                tab_numbers = 2;
            }

            return tab_numbers;
        }

        public CharSequence getPageTitle(int position) {
            String chosenInjury = getArguments().getString("injury");
            if(chosenInjury.toLowerCase().equals("burns")) {
                switch (position) {
                    case 0:
                        return "OVERVIEW";
                    case 1:
                        return "1st & 2nd Degree";
                    case 2:
                        return "3rd Degree";
                }
            } else if(chosenInjury.toLowerCase().equals("laceration")){
                switch (position) {
                    case 0:
                        return "OVERVIEW";
                    case 1:
                        return "MAJOR";
                    case 2:
                        return "MINOR";
                }
            } else if(chosenInjury.toLowerCase().equals("puncture")){
                switch (position) {
                    case 0:
                        return "OVERVIEW";
                    case 1:
                        return "SEVERE";
                    case 2:
                        return "SLIGHT";
                }
            } else if(chosenInjury.toLowerCase().equals("bites")){
                switch (position) {
                    case 0:
                        return "OVERVIEW";
                    case 1:
                        return "ANIMAL";
                    case 2:
                        return "INSECT";
                }
            } else {
                switch (position) {
                    case 0:
                        return "OVERVIEW";
                    case 1:
                        return chosenInjury;
                }
            }

            return null;
        }

        public Fragment setOverviewFragment(String chosenInjury) {
            InjuryOverviewFragment injuryOverviewFragment = new InjuryOverviewFragment();
            Bundle args = new Bundle();
            args.putString("injury", chosenInjury);
            injuryOverviewFragment.setArguments(args);

            return injuryOverviewFragment;
        }

        public Fragment setInjuryInformationFragment(String chosenInjury) {
            InjuryInformationFragment injuryInformationFragment = new InjuryInformationFragment();
            Bundle args = new Bundle();
            args.putString("injury", chosenInjury);
            injuryInformationFragment.setArguments(args);

            return injuryInformationFragment;
        }

        public Fragment setInjuryInformationSecondTabFragment(String chosenInjury) {
            InjuryInformationSecondTabFragment injuryInformationSecondTabFragment = new InjuryInformationSecondTabFragment();
            Bundle args = new Bundle();
            args.putString("injury", chosenInjury);
            injuryInformationSecondTabFragment.setArguments(args);

            return injuryInformationSecondTabFragment;
        }
    }
}
