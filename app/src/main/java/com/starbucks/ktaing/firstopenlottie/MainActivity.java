package com.starbucks.ktaing.firstopenlottie;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.app.FragmentActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ArrayList<PlaceholderFragment> fragmentlist = new ArrayList<>();
    private ArrayList<String> animationList = new ArrayList<>();

    private ViewPager mViewPager;
    private final int NUM_PAGES = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getApplicationContext(), getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(0);

        animationList.add("1-birthday.json");
        animationList.add("2-loading.json");
        animationList.add("3-stick-and-ball.json");

        for (int i = 0; i < NUM_PAGES; i++) {
            fragmentlist.add(PlaceholderFragment.newInstance(animationList.get(i), i));
            mSectionsPagerAdapter.notifyDataSetChanged();
        }
    }

    public void delete(int pos) {

        int i = 0;
        while (i <= fragmentlist.size()) {
            if (pos == fragmentlist.get(i).getPosition()) break;
            i++;
        }

        fragmentlist.remove(i);
        mpg.notifyDataSetChanged();
    }



    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private Context mContext;
        public SectionsPagerAdapter(Context context, FragmentManager fm) {
            super(fm);
            mContext = context;

        }

        @Override
        public Fragment getItem(int position) {
            return fragmentlist.get(position);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
@SuppressLint("ValidFragment")
 class PlaceholderFragment extends Fragment {
    private static String key = "FileName";
    private static String key_pos = "position";
    int pos;

    public static PlaceholderFragment newInstance(String animationName, int position) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putString(key, animationName);
        args.putInt(key_pos, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        Bundle args = getArguments();
        String fileName = "1-birthday.json";
        if (args!=null){
            fileName = args.getString(key);
            pos = args.getInt(key_pos);
        }
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activity = (MainActivity) getActivity();
                activity.delete(pos);
            }
        });

//            animations[pageNumber];
//            switch(pageNumber) {
//                case 1: animName = "3-stick-and-ball.json";
//                    break;
//                case 2: animName = "1-birthday.json";
//                    break;
//                case 3: animName = "3-stick-and-ball.json";
//                    break;
//                default: animName = "3-stick-and-ball.json";
//                    break;
//            }

        LottieAnimationView animationView = rootView.findViewById(R.id.animation_view);
        animationView.setAnimation(fileName);
        return rootView;
    }
}
