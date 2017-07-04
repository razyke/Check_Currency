package com.puper.asuper.checkcurrency;

import android.content.Context;

import java.util.ArrayList;
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

    public List<Guess> getGuesses(){
        return guesses;
    }

    public Guess getGuess(UUID id){
        for (Guess g: guesses){
            if (g.getId().equals(id))
                return g;
        }
        return null;
    }

}
