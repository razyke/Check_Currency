package com.puper.asuper.checkcurrency;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * Created by Daniil Smirnov on 27.06.2017.
 * All copy registered MF.
 */
public class StatisticsActivity extends FragmentActivity {

    private Fragment StatisticsFragment;
    private FragmentManager fm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics_activity);
        fm = getSupportFragmentManager();
        StatisticsFragment = fm.findFragmentById(R.id.statistics_fragment_container);

        if (StatisticsFragment == null){
            StatisticsFragment = new StatisticsFragment();
            fm.beginTransaction()
                    .add(R.id.statistics_fragment_container,StatisticsFragment)
                    .commit();
        }

    }
}