package com.example.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.ui.Adapter.Adapt1;
import com.example.ui.Fragments.OverviewFragment;

import java.util.ArrayList;

public class SectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);
        int id = getIntent().getIntExtra("id",0);
        OverviewFragment overviewFragment = OverviewFragment.newInstance(id,0);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container,overviewFragment);
        transaction.commit();
    }
}
