package com.puper.asuper.checkcurrency;


import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * Created by Daniil Smirnov on 27.06.2017.
 * All copy registered MF.
 */
public class Guess {
    private Date date;
    private UUID id;
    private double value;
    private double dollarFromCentrobank;

    public Guess(Date date, double value){
        this.date = date;
        this.value = value;
        id = UUID.randomUUID();
    }

    public double getDollarFromCentrobank() {
        return dollarFromCentrobank;
    }

    public void setDollarFromCentrobank(double dollarFromCentrobank) {
        this.dollarFromCentrobank = dollarFromCentrobank;
    }

    public UUID getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getAnswer(){

        if (date.getTime() <= System.currentTimeMillis()) {
            if (dollarFromCentrobank == 0.0) {
                String FD = new SimpleDateFormat("dd/MM/yyyy").format(date);
                try {
                    dollarFromCentrobank = new GetFromInternet().execute(FD).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Log.e(Constants.ERRORS, "[Guess] - [getAnswer] - Прервано");
                    return "Error";
                } catch (ExecutionException e) {
                    e.printStackTrace();
                    Log.e(Constants.ERRORS, "[Guess] - [getAnswer] - Ошибка выполнения");
                    return "Error";
                }
                if (dollarFromCentrobank == 0.0) {
                    Log.e(Constants.ERRORS, "[Guess] - [getAnswer] - Не получили данные с ЦБ");
                    return "Error";
                }
                return getTransformation(dollarFromCentrobank, value);
            } else
                return getTransformation(dollarFromCentrobank, value);
        }

        return "Time";

    }



    private String getTransformation(double Dollar, double GuessValue){

        if (Dollar==GuessValue)
            return "Right";
        else if ((Dollar>GuessValue && (Dollar-GuessValue<=1.0)) || (Dollar<GuessValue &&(GuessValue-Dollar<=1.0))){
            return "Near";
        }else
            return "Far";
    }
}
