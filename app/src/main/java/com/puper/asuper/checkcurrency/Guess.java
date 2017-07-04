package com.puper.asuper.checkcurrency;


import java.util.Date;
import java.util.UUID;

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






        //value = Double.parseDouble()
        /*Псевдокод

        Если дата прогноза(DP) не настала, то указываем - результат будет известен .getDate

       Если DP >= Текущей даты и dollarFromCentoBank != "" || != null;
       ТО
            Взять инфу с сервера у казыванием параметра где req= будет DP
            http://www.cbr.ru/scripts/XML_daily.asp?date_req=20/06/2017

           dollarFromCentroBank = http://///

           ЕСЛИ
                (DFCB)




        double Dollar = Service.getFromInternetValueDollar

            Dollar = Dollar * 10;
            int i = (int) Math.round(Dollar);
            Dollar = (double)i / 10;
            Теперь полученная валюта с интернета будет иметь формать ##.#

            int between = 0;

            if(Dollar>value){
            double convert1 = Dollar * 10;
            int d =
            }

        if (Dollar==value)
        return "Right"
        else if(Dollar<
         */


        return null;
    }
}
