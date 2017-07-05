package com.puper.asuper.checkcurrency;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * Created by Daniil Smirnov on 05.07.2017.
 * All copy registered MF.
 */
public class GuessFragment extends Fragment {

    private static final String DIALOG_DATE = "DialogDate";

    private TextView USDtoRUB;
    private Button btnDate;
    private Button btnOk;
    private EditText ETValue;
    private TextView setValue;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.guess_fragment,container,false);
       setUSDtoRUB(view);
       chooseDate(view);
       setUserValue(view);
        return view;
    }

    private void setUSDtoRUB(View view){
        USDtoRUB = (TextView) view.findViewById(R.id.UsdToRubToday);
        double today = 0.0;
        try {
             today = new GetFromInternet().execute(new SimpleDateFormat("dd/MM/yyyy").format(new Date())).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if (today==0.0)
            USDtoRUB.setText("Network bad");
        else
            USDtoRUB.setText(String.valueOf(today));

    }

    private void chooseDate(View view){
        btnDate = (Button) view.findViewById(R.id.btn_date);
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                DatePickerFragment a = new DatePickerFragment();
                a.show(fm,DIALOG_DATE);
            }
        });
    }

    private void setUserValue(View view){
        //TODO Нужно разработать приём чисел только в таком формате ##.#
        ETValue = (EditText) view.findViewById(R.id.guess_value);
        setValue = (TextView) view.findViewById(R.id.for_value_set);
        btnOk = (Button) view.findViewById(R.id.btn_ok_for_value);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValue.setText(
                        ETValue.getText().toString()
                );
            }
        });


    }
}
