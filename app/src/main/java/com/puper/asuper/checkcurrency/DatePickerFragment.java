package com.puper.asuper.checkcurrency;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;


/**
 * Created by Daniil Smirnov on 05.07.2017.
 * All copy registered MF.
 */
public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // определяем текущую дату
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // создаем DatePickerDialog и возвращаем его
        Dialog picker = new DatePickerDialog(getActivity(), this,
                year, month, day);
        picker.setTitle("Choose date");

        return picker;
    }
    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onDateSet(android.widget.DatePicker datePicker, int year, int month, int day) {
        month++;
        String date = String.format("%02d/%02d/%d",day,month,year);
        Date fromCalendar = GuessLab.fortestgetDate(date);
        if (new Date().getTime()>=fromCalendar.getTime()){
            Toast.makeText(getActivity(),"Write correct date",Toast.LENGTH_SHORT).show();
        }else {
            TextView tv = (TextView) getActivity().findViewById(R.id.for_date_set);
            tv.setText("Date " + day + "-" + month + "-" + year);
        }
    }
}
