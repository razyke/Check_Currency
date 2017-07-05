package com.puper.asuper.checkcurrency;

import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Daniil Smirnov on 27.06.2017.
 * All copy registered MF.
 */
public class GuessLab {
    private List<Guess> guesses;
    private static GuessLab guessLab;

    public static GuessLab get(Context context){
        if (guessLab==null)
            guessLab = new GuessLab();
        return guessLab;
    }

    public GuessLab() {
     guesses = new ArrayList<>();
    }

    public void addGues(Guess guess){
        guesses.add(guess);
    }
//ДЛЯ ТЕСТА НЕ ЗАБЫТЬ УДАЛИТЬ
    public List<Guess> getGuesses(){

        Guess lol = new Guess(fortestgetDate("12/02/2009"),48.3);
              lol.setDollarFromCentrobank(48.3);
        guesses.add(lol);
        guesses.add(new Guess(fortestgetDate("25/07/2011"),50.8));
        guesses.add(new Guess(fortestgetDate("02/05/2015"),62.3));
        guesses.add(new Guess(fortestgetDate("09/07/2017"),58.3));



        return guesses;
    }
    //ТОЖЕ ДЛЯ ТЕСТА (пока оставить мб пригодиться позже)
    public static Date fortestgetDate(String lol){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        String dateInString = lol;
        Date date = null;
        try {
            date = sdf.parse(dateInString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public Guess getGuess(UUID id){
        for (Guess g: guesses){
            if (g.getId().equals(id))
                return g;
        }
        return null;
    }

}
