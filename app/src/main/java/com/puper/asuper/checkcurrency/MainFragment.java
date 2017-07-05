package com.puper.asuper.checkcurrency;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Daniil Smirnov on 27.06.2017.
 * All copy registered MF.
 */
public class MainFragment extends Fragment {

    Button goToStatisticsPage;
    Button exitFromApp;
    Button goToGuessPage;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment,container,false);
        goToStatisticsPage = (Button) view.findViewById(R.id.btn_statistics);
        goToStatisticsPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(getContext(),StatisticsActivity.class);
                startActivity(go);
            }
        });
        exitFromApp = (Button) view.findViewById(R.id.btn_exit);
        exitFromApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
        goToGuessPage = (Button) view.findViewById(R.id.btn_guess);
        goToGuessPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(getContext(),GuessActivity.class);
                startActivity(go);
            }
        });
        return view;
    }



}
