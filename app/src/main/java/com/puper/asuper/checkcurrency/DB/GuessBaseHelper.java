package com.puper.asuper.checkcurrency.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.puper.asuper.checkcurrency.DB.GuessDbSchema.GuessTable;

/**
 * Created by Daniil Smirnov on 06.07.2017.
 * All copy registered MF.
 */
public class GuessBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "guessBase.db";

    public GuessBaseHelper(Context context){
        super(context,DATABASE_NAME,null,VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ GuessTable.NAME+"("+
        " _id integer primary key autoincrement, " +
        GuessTable.Cols.UUID + ", "+
        GuessTable.Cols.CB_VALUE + ", "+
        GuessTable.Cols.DATA +", "+
        GuessTable.Cols.VALUE + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
