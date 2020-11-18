package lk.sliit.mad.myapplication.database;

import android.provider.BaseColumns;

public final class FeedReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private FeedReaderContract() {}

    /* Inner class that defines the table contents */
    public static class User implements BaseColumns {
        public static final String TABLE_NAME = "user"; //Table Name
        public static final String COLUMN_NAME = "name";//Column Names
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_TYPE = "type";
    }
    /* Inner class that defines the table contents */
    public static class Message implements BaseColumns {
        public static final String TABLE_NAME = "message"; //Table Name
        public static final String COLUMN_USER = "user";//Column Names
        public static final String COLUMN_SUBJECT = "subject";
        public static final String COLUMN_MESSAGE = "message";
    }
}
