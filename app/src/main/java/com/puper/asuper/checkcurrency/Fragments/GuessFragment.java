package com.puper.asuper.checkcurrency.Fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.puper.asuper.checkcurrency.Constants;
import com.puper.asuper.checkcurrency.GetFromInternet;
import com.puper.asuper.checkcurrency.Models.GuessLab;
import com.puper.asuper.checkcurrency.Models.Guess;
import com.puper.asuper.checkcurrency.R;

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
    private Button Submit;
    private EditText ETValue;
    private TextView setValue;

    private String UserValue;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.guess_fragment,container,false);
       setUSDtoRUB(view);
       chooseDate(view);
       setUserValue(view);
       saveUserGuess(view);

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
        ETValue = (EditText) view.findViewById(R.id.guess_value);
        setValue = (TextView) view.findViewById(R.id.for_value_set);
        btnOk = (Button) view.findViewById(R.id.btn_ok_for_value);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserValue = ETValue.getText().toString();
                if (UserValue.matches("\\d\\d\\d.\\d"))
                    setValue.setText("Your guess = "+UserValue);
                else if (UserValue.matches("\\d\\d.\\d"))
                setValue.setText("Your guess = "+UserValue);
                else if (UserValue.matches("\\d.\\d"))
                    setValue.setText("Your guess = "+UserValue);
                else {
                    Toast.makeText(getActivity(), "Write right params", Toast.LENGTH_SHORT).show();
                    ETValue.setText("");
                }

            }
        });
    }

    private void saveUserGuess(final View view){
        Submit = (Button) view.findViewById(R.id.btn_submit);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String DateParams;
                try {
                    DateParams = DatePickerFragment.getGiveDate();
                }catch (Exception e){
                    Log.e(Constants.ERRORS,"[GuessFragment] - [saveUserGuess] Не получил DateParams");
                    DateParams = "";
                }

                if (DateParams==null||(DateParams.equals(""))|| (setValue.getText().toString().equals("")||
                setValue.getText().toString().isEmpty())){
                    Toast.makeText(getActivity(),"Enter the parameters!",Toast.LENGTH_SHORT).show();
                }else{
                    AlertDialog prepare = new AlertDialog.Builder(getActivity())
                            .setTitle("Your params")
                            .setMessage("Date = "+DateParams+"\n"+"\n"
                            +"Dollar = "+UserValue)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Guess guess = new Guess(GuessLab.forRightDate(DatePickerFragment.getGiveDate()),
                                            Double.parseDouble(UserValue));

                                    if (GuessLab.get(getContext()).getGuesses().contains(guess)){
                                        Toast.makeText(getActivity(),"With this date already existed",Toast.LENGTH_SHORT).show();
                                    }else {
                                        GuessLab.get(getActivity()).addGues(guess);
                                        Toast.makeText(getActivity(),"Saved",Toast.LENGTH_SHORT).show();
                                    }

                                }
                            })
                            .setNegativeButton("Cancel",null)
                            .create();
                    prepare.show();
                }

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        DatePickerFragment.setGiveDate("");
    }
}
