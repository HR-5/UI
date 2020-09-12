package com.example.ui.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.ui.R;

public class Adapt2 extends PagerAdapter {

    Context context;
    CardView cardView;
    Animation animation;

    public Adapt2(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view  = layoutInflater.inflate(R.layout.viewpager2,container,false);
        cardView = (CardView) view.findViewById(R.id.imgcard);
        animation = AnimationUtils.loadAnimation(context,R.anim.bounce);
        cardView.startAnimation(animation);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    public void anim(){
        animation = AnimationUtils.loadAnimation(context,R.anim.bounce);
        cardView.startAnimation(animation);
    }
}
