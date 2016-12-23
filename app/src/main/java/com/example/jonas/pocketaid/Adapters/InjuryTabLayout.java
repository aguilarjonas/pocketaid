package com.example.jonas.pocketaid.Adapters;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.jonas.pocketaid.InjuriesFragments.InjuryInformationFragment;
import com.example.jonas.pocketaid.InjuriesFragments.InjuryOverviewFragment;
import com.example.jonas.pocketaid.R;

/**
 * Created by alec on 12/23/16.
 */

public class InjuryTabLayout extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int tab_numbers = 2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_injury_tabs, container, false);
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
            switch (position) {
                case 0:
                    return new InjuryOverviewFragment();
                case 1:
                    String chosenInjury = getArguments().getString("injury");
                    InjuryInformationFragment injuryInformationFragment = new InjuryInformationFragment();
                    Bundle args = new Bundle();
                    args.putString("injury", chosenInjury);
                    injuryInformationFragment.setArguments(args);

                    return injuryInformationFragment;
            }

            return null;
        }

        @Override
        public int getCount() {
            return tab_numbers;
        }

        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "OVERVIEW";
                case 1:
                    return "INJURY PAKIBAGO";
            }

            return null;
        }
    }
}
