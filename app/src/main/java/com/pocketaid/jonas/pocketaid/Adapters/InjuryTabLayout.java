package com.pocketaid.jonas.pocketaid.Adapters;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pocketaid.jonas.pocketaid.InjuriesFragments.InjuryInformationFragment;
import com.pocketaid.jonas.pocketaid.InjuriesFragments.InjuryInformationSecondTabFragment;
import com.pocketaid.jonas.pocketaid.InjuriesFragments.InjuryOverviewFragment;
import com.pocketaid.jonas.pocketaid.MainActivity;
import com.pocketaid.jonas.pocketaid.R;

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

            if(chosenInjury.toLowerCase().equals(getResources().getString(R.string.bites).toLowerCase())) {
                switch (position) {
                    case 0:
                        return setOverviewFragment(chosenInjury);
                    case 1:
                        return setInjuryInformationFragment(getResources().getString(R.string.animal_tab));
                    case 2:
                        return setInjuryInformationSecondTabFragment(getResources().getString(R.string.insect_tab));
                }
            } else if(chosenInjury.toLowerCase().equals(getResources().getString(R.string.burns_list).toLowerCase())) {
                switch (position) {
                    case 0:
                        return setOverviewFragment(chosenInjury);
                    case 1:
                        return setInjuryInformationFragment(getResources().getString(R.string.first_second_degree_tab));
                    case 2:
                        return setInjuryInformationSecondTabFragment(getResources().getString(R.string.third_degree_tab));
                }
            } else if(chosenInjury.toLowerCase().equals(getResources().getString(R.string.laceration).toLowerCase())) {
                switch (position) {
                    case 0:
                        return setOverviewFragment(chosenInjury);
                    case 1:
                        return setInjuryInformationFragment(getResources().getString(R.string.major_laceration_tab));
                    case 2:
                        return setInjuryInformationSecondTabFragment(getResources().getString(R.string.minor_laceration_tab));
                }
            } else if(chosenInjury.toLowerCase().equals(getResources().getString(R.string.puncture).toLowerCase())) {
                switch (position) {
                    case 0:
                        return setOverviewFragment(chosenInjury);
                    case 1:
                        return setInjuryInformationFragment(getResources().getString(R.string.severe_puncture_tab));
                    case 2:
                        return setInjuryInformationSecondTabFragment(getResources().getString(R.string.slight_puncture_tab));
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
            if(chosenInjury.toLowerCase().equals(getResources().getString(R.string.bites).toLowerCase()) ||
                    chosenInjury.toLowerCase().equals(getResources().getString(R.string.burns_list).toLowerCase()) ||
                    chosenInjury.toLowerCase().equals(getResources().getString(R.string.laceration).toLowerCase()) ||
                    chosenInjury.toLowerCase().equals(getResources().getString(R.string.puncture).toLowerCase())) {
                tab_numbers = 3;
            } else {
                tab_numbers = 2;
            }
            return tab_numbers;
        }

        public CharSequence getPageTitle(int position) {
            String chosenInjury = getArguments().getString("injury");
            if(chosenInjury.toLowerCase().equals(getResources().getString(R.string.burns_list).toLowerCase())) {
                switch (position) {
                    case 0:
                        return getResources().getString(R.string.overview_tab);
                    case 1:
                        return getResources().getString(R.string.first_second_degree_tab);
                    case 2:
                        return getResources().getString(R.string.third_degree_tab);
                }
            } else if(chosenInjury.toLowerCase().equals(getResources().getString(R.string.laceration).toLowerCase())){
                switch (position) {
                    case 0:
                        return getResources().getString(R.string.overview_tab);
                    case 1:
                        return getResources().getString(R.string.major_laceration_tab);
                    case 2:
                        return getResources().getString(R.string.minor_laceration_tab);
                }
            } else if(chosenInjury.toLowerCase().equals(getResources().getString(R.string.puncture).toLowerCase())){
                switch (position) {
                    case 0:
                        return getResources().getString(R.string.overview_tab);
                    case 1:
                        return getResources().getString(R.string.severe_puncture_tab);
                    case 2:
                        return getResources().getString(R.string.slight_puncture_tab);
                }
            } else if(chosenInjury.toLowerCase().equals(getResources().getString(R.string.bites).toLowerCase())){
                switch (position) {
                    case 0:
                        return getResources().getString(R.string.overview_tab);
                    case 1:
                        return getResources().getString(R.string.animal_tab);
                    case 2:
                        return getResources().getString(R.string.insect_tab);
                }
            }
            else {
                switch (position) {
                    case 0:
                        return getResources().getString(R.string.overview_tab);
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
