package com.puper.asuper.checkcurrency.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.puper.asuper.checkcurrency.DB.GuessBaseHelper;
import com.puper.asuper.checkcurrency.DB.GuessCursorWrapper;
import com.puper.asuper.checkcurrency.DB.GuessDbSchema.GuessTable;


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


    private static GuessLab guessLab;
    private Context context;
    private SQLiteDatabase Database;

    private static ContentValues getContentValues(Guess guess){
        ContentValues values = new ContentValues();
        values.put(GuessTable.Cols.UUID, guess.getId().toString());
        values.put(GuessTable.Cols.CB_VALUE, guess.getDollarFromCentrobank());
        values.put(GuessTable.Cols.DATA, guess.getDate().getTime());
        values.put(GuessTable.Cols.VALUE, guess.getValue());

        return values;
    }


    public static GuessLab get(Context context){
        if (guessLab==null) {
            guessLab = new GuessLab(context);
        }
        return guessLab;
    }

    private GuessLab(Context context) {
        this.context = context.getApplicationContext();
        Database = new GuessBaseHelper(this.context)
                .getWritableDatabase();

    }

    public void addGues(Guess g){
        ContentValues values = getContentValues(g);
        Database.insert(GuessTable.NAME,null,values);
    }

    public List<Guess> getGuesses(){
        List<Guess> guesses = new ArrayList<>();

        GuessCursorWrapper cursor = queryCrimes(null,null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                guesses.add(cursor.getGuess());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }

        return guesses;
    }

    public static Date forRightDate(String lol){
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
        GuessCursorWrapper cursor = queryCrimes(
                GuessTable.Cols.UUID + " = ?",
                new String[]{id.toString()}
        );

        try {
            if (cursor.getCount()==0)
                return null;

            cursor.moveToFirst();
            return cursor.getGuess();
        }finally {
            cursor.close();
        }

    }

    public void updateGuess(Guess guess){
        String uuidString = guess.getId().toString();
        ContentValues values = getContentValues(guess);

        Database.update(GuessTable.NAME, values, GuessTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    private GuessCursorWrapper queryCrimes(String whereClause, String[] whereArgs){
        Cursor cursor = Database.query(
                GuessTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new GuessCursorWrapper(cursor);
    }

}
