package com.example.ui.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ui.Adapter.Adapt3;
import com.example.ui.Adapter.RecyclerAdapt;
import com.example.ui.R;

import java.util.ArrayList;
import java.util.Collection;


public class DetailFragment extends Fragment {

    ViewPager viewPager;
    int id;
    ArrayList<String> titles;
    TextView titl;
    ImageView[] dash;
    float py, px;

    public DetailFragment() {
        // Required empty public constructor
    }


    public static DetailFragment newInstance(int id, ArrayList<String> titles) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putInt("id", id);
        args.putSerializable("title", titles);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt("id");
            titles = (ArrayList<String>) getArguments().getSerializable("title");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.viewpage3);
        titl = (TextView) view.findViewById(R.id.title);
        dash = new ImageView[4];
        dash[0] = (ImageView) view.findViewById(R.id.i1);
        dash[1] = (ImageView) view.findViewById(R.id.i2);
        dash[2] = (ImageView) view.findViewById(R.id.i3);
        dash[3] = (ImageView) view.findViewById(R.id.i4);
        set();
        return view;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Adapt3 adapt3 = new Adapt3(getContext());
        viewPager.setAdapter(adapt3);
        viewPager.setCurrentItem(id);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        py = motionEvent.getY();
                        px = motionEvent.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        if (Math.abs(motionEvent.getY() - py) > Math.abs(motionEvent.getX() - px)) {
                            if ((motionEvent.getY() - py) > 10) {
                                OverviewFragment fragment = OverviewFragment.newInstance(id);
                                FragmentManager manager = getFragmentManager();
                                FragmentTransaction transaction = manager.beginTransaction();
                                transaction.replace(R.id.container, fragment);
                                transaction.commit();
                                break;
                            }
                        } else {
                            if ((motionEvent.getX() - px) > 10) {
                                if (id > 0) {
                                    id--;
                                    viewPager.setCurrentItem(id, true);
                                    set();
                                }
                            } else if ((motionEvent.getX() - px) < (-10)) {
                                if (id < 3) {
                                    id++;
                                    viewPager.setCurrentItem(id, true);
                                    set();
                                }
                            }
                        }
                        break;
                }
                return true;
            }
        });
    }

    private void set() {
        titl.setText(titles.get(id));
        for (int i = 0; i < 4; i++) {
            if (id == i) {
                dash[id].setImageResource(R.drawable.dashse);
            } else
                dash[i].setImageResource(R.drawable.dash);
        }

    }
}
