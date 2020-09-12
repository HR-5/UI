package com.example.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;

import androidx.core.app.ActivityOptionsCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.util.Pair;

import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout[] constraintLayouts;
    TextView[] textViews;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        textViews = new TextView[4];
        textViews[0] = (TextView) findViewById(R.id.t1);
        textViews[1] = (TextView) findViewById(R.id.t2);
        textViews[2] = (TextView) findViewById(R.id.t3);
        textViews[3] = (TextView) findViewById(R.id.t4);
        constraintLayouts = new ConstraintLayout[4];
        constraintLayouts[0] = (ConstraintLayout) findViewById(R.id.con1);
        constraintLayouts[1] = (ConstraintLayout) findViewById(R.id.con2);
        constraintLayouts[2] = (ConstraintLayout) findViewById(R.id.con3);
        constraintLayouts[3] = (ConstraintLayout) findViewById(R.id.con4);
        onclick();
    }

    private void onclick() {
        constraintLayouts[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(0);
            }
        });
        constraintLayouts[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(1);
            }
        });
        constraintLayouts[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(2);
            }
        });
        constraintLayouts[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(3);
            }
        });
    }

    private void click(int id) {
        Pair<View,String> pair = Pair.create((View) constraintLayouts[id], "cons");
        Pair<View,String> pair1 = Pair.create((View) textViews[id], "title");
        Pair<View,String> pair2 = Pair.create((View) textViews[id], "title");
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pair1);
        Intent intent = new Intent(this, SectionActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
}
