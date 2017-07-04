package com.puper.asuper.checkcurrency;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Daniil Smirnov on 27.06.2017.
 * All copy registered MF.
 */
public class MainActivity extends FragmentActivity {

    private FragmentManager fm;
    private Fragment MainFragment;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);

        fm = getSupportFragmentManager();
        MainFragment = fm.findFragmentById(R.id.frame_container);
        if (MainFragment==null){
            MainFragment = new MainFragment();
            fm.beginTransaction()
                    .add(R.id.frame_container,MainFragment)
                    .commit();
        }


    }
}
