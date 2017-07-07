package com.puper.asuper.checkcurrency.DB;

/**
 * Created by Daniil Smirnov on 06.07.2017.
 * All copy registered MF.
 */
public class GuessDbSchema {
    public static final class GuessTable{
        public static final String NAME = "guesses";

        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String VALUE = "value";
            public static final String DATA = "date";
            public static final String CB_VALUE ="cb";
        }
    }
}
