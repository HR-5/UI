package com.example.ui.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.ui.Adapter.Adapt1;
import com.example.ui.Adapter.Adapt2;
import com.example.ui.R;

import java.util.ArrayList;

public class OverviewFragment extends Fragment {

    int id;
    ViewPager viewPager1,viewPager2;
    volatile int pageid;

    public OverviewFragment() {
        // Required empty public constructor
    }


    public static OverviewFragment newInstance(int id) {
        OverviewFragment fragment = new OverviewFragment();
        Bundle args = new Bundle();
        args.putInt("id",id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt("id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_overview, container, false);
        viewPager1 = (ViewPager) view.findViewById(R.id.viewPage1);
        viewPager2 = (ViewPager) view.findViewById(R.id.view2);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayList<String> titles = new ArrayList<>();
        titles.add("Highlights");
        titles.add("Science");
        titles.add("Gaming");
        titles.add("Movies");
        Adapt1 adapt1 = new Adapt1(titles,getContext());
        viewPager1.setAdapter(adapt1);
        viewPager1.setOffscreenPageLimit(3);
        viewPager1.setCurrentItem(id);
        Adapt2 adapt2 = new Adapt2(getContext());
        viewPager2.setAdapter(adapt2);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.setCurrentItem(id);
        viewPager1.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                viewPager2.setCurrentItem(position,true);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager2.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                viewPager1.setCurrentItem(position,true);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//        viewPager1.setPageTransformer(true,new ViewStack());
    }


    private class ViewStack implements ViewPager.PageTransformer
    {

        @Override
        public void transformPage(@NonNull View page, float position) {
            if(position>=0){
                page.setTranslationX(100);
            }
        }
    }
}
