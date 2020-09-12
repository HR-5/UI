package com.example.ui.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.ui.R;

public class Adapt3 extends PagerAdapter {

    Context context;
    int flag;

    public Adapt3(Context context, int flag) {
        this.context = context;
        this.flag = flag;
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
        View view = layoutInflater.inflate(R.layout.viewpager3, container, false);
        container.addView(view);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle);
        RecyclerAdapt adapt = new RecyclerAdapt();
        recyclerView.setHasFixedSize(true);
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.fall_down);
        if (flag == 1) {
            animation = AnimationUtils.loadAnimation(context, R.anim.right);
        }
        LayoutAnimationController controller = new LayoutAnimationController(animation);
        recyclerView.setLayoutAnimation(controller);
        final GridLayoutManager layoutManager = new GridLayoutManager(context, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapt);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
