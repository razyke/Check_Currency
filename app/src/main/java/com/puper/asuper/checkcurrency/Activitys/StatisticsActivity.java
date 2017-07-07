package com.puper.asuper.checkcurrency.Activitys;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import com.puper.asuper.checkcurrency.R;
import com.puper.asuper.checkcurrency.Fragments.StatisticsFragment;

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
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
