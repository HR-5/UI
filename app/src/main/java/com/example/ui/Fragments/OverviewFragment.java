package com.example.ui.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.transition.TransitionInflater;
import androidx.viewpager.widget.ViewPager;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ui.Adapter.Adapt1;
import com.example.ui.Adapter.Adapt2;
import com.example.ui.MainActivity;
import com.example.ui.R;
import com.example.ui.SectionActivity;

import java.util.ArrayList;

public class OverviewFragment extends Fragment {

    int id,flag;
    ViewPager viewPager1, viewPager2;
    float py, px;
    ArrayList<String> titles = new ArrayList<>();
    CardView cardView,cardView2,viewcard;
    ConstraintLayout constraintLayout;
    ImageView plus;
    TextView t1,t2;
    Adapt2 adapt2;

    public OverviewFragment() {
        // Required empty public constructor
    }


    public static OverviewFragment newInstance(int id,int flag) {
        OverviewFragment fragment = new OverviewFragment();
        Bundle args = new Bundle();
        args.putInt("id", id);
        args.putInt("flag", flag);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt("id");
            flag = getArguments().getInt("flag");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_overview, container, false);
        viewPager1 = (ViewPager) view.findViewById(R.id.viewPage1);
        viewPager2 = (ViewPager) view.findViewById(R.id.view2);
        cardView = (CardView) view.findViewById(R.id.cardView);
        viewcard = (CardView) view.findViewById(R.id.viewcard);
        cardView2 = (CardView) view.findViewById(R.id.top);
        constraintLayout = (ConstraintLayout) view.findViewById(R.id.linear);
        plus = (ImageView) view.findViewById(R.id.plus);
        t1 = (TextView) view.findViewById(R.id.textView2);
        t2 = (TextView) view.findViewById(R.id.textView3);
        if (flag==0) {
            Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.up);
            viewcard.startAnimation(animation);
        }
        set();
        return view;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        titles.add("Highlights");
        titles.add("Science");
        titles.add("Gaming");
        titles.add("Movies");
        Adapt1 adapt1 = new Adapt1(titles, getContext());
        viewPager1.setAdapter(adapt1);
        viewPager1.setCurrentItem(id);
        viewPager1.setClipChildren(false);
        viewPager1.canScrollHorizontally(0);
        viewPager1.setOffscreenPageLimit(adapt1.getCount());
        adapt2 = new Adapt2(getContext());
        viewPager2.setAdapter(adapt2);
        viewPager2.setOffscreenPageLimit(1);
        viewPager2.setCurrentItem(id);
        viewPager2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        py = motionEvent.getY();
                        px = motionEvent.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        if (Math.abs(motionEvent.getY() - py) > Math.abs(motionEvent.getX() - px)) {
                            if ((py - motionEvent.getY()) > 10) {
                                DetailFragment fragment = DetailFragment.newInstance(id, titles,0);
                                fragment.setSharedElementEnterTransition(TransitionInflater.from(getActivity()).inflateTransition(R.transition.trans));
                                fragment.setEnterTransition(TransitionInflater.from(getActivity()).inflateTransition(android.R.transition.move));
                                getFragmentManager().beginTransaction().replace(R.id.container, fragment)
                                        .addToBackStack("transaction")
                                        .addSharedElement(cardView, cardView.getTransitionName())
                                        .addSharedElement(viewcard,viewcard.getTransitionName())
                                        .commit();
                                return false;
                            } else if ((motionEvent.getY() - py) > (10)) {
                                Intent intent = new Intent(getContext(), MainActivity.class);
                                getActivity().startActivity(intent);
                            }
                        } else {
                            if ((motionEvent.getX() - px) > 10) {
                                if (id > 0) {
                                    id--;
                                    viewPager1.setCurrentItem(id, true);
                                    viewPager2.setCurrentItem(id, true);
                                    set();
                                    shake();
                                    return false;
                                }
                            } else if ((motionEvent.getX() - px) < (-10)) {
                                if (id < 3) {
                                    id++;
                                    viewPager1.setCurrentItem(id, true);
                                    viewPager2.setCurrentItem(id, true);
                                    set();
                                    shake();
                                    return false;
                                }
                            }
                        }
                        break;
                }
                return true;
            }
        });
    }

    private void set(){
        if(id%2==0){
            t1.setTextColor(getResources().getColor(R.color.b12));
            t2.setTextColor(getResources().getColor(R.color.b12));
            constraintLayout.setBackgroundResource(R.drawable.back2);
            plus.setBackgroundColor(getResources().getColor(R.color.b12));
        }
        else {
            t1.setTextColor(getResources().getColor(R.color.b11));
            t2.setTextColor(getResources().getColor(R.color.b11));
            constraintLayout.setBackgroundResource(R.drawable.back3);
            plus.setBackgroundColor(getResources().getColor(R.color.b11));
        }
    }

    private void shake(){
        adapt2.anim();
    }
}
