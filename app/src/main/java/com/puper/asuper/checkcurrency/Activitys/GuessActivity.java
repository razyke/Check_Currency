package com.puper.asuper.checkcurrency.Activitys;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import com.puper.asuper.checkcurrency.Fragments.GuessFragment;
import com.puper.asuper.checkcurrency.R;

/**
 * Created by Daniil Smirnov on 05.07.2017.
 * All copy registered MF.
 */
public class GuessActivity extends FragmentActivity {

    private FragmentManager fm;
    private Fragment GuessFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.guess_activity);

        fm = getSupportFragmentManager();
        GuessFragment = fm.findFragmentById(R.id.guess_activity_container);
        if (GuessFragment==null){
            GuessFragment = new GuessFragment();
            fm.beginTransaction()
                    .add(R.id.guess_activity_container,GuessFragment)
                    .commit();
        }


    }
}
