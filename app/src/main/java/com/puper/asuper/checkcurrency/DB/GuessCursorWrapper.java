package com.puper.asuper.checkcurrency.DB;

import android.database.Cursor;
import android.database.CursorWrapper;
import com.puper.asuper.checkcurrency.DB.GuessDbSchema.GuessTable;
import com.puper.asuper.checkcurrency.Models.Guess;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Daniil Smirnov on 07.07.2017.
 * All copy registered MF.
 */
public class GuessCursorWrapper extends CursorWrapper {

    public GuessCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public Guess getGuess(){
        String uuidString = getString(getColumnIndex(GuessTable.Cols.UUID));
        double dollarFromCB = getDouble(getColumnIndex(GuessTable.Cols.CB_VALUE));
        long date = getLong(getColumnIndex(GuessTable.Cols.DATA));
        double value = getDouble(getColumnIndex(GuessTable.Cols.VALUE));

        Guess guess = new Guess(UUID.fromString(uuidString));
        guess.setDollarFromCentrobank(dollarFromCB);
        guess.setDate(new Date(date));
        guess.setValue(value);

        return guess;
    }
}
