package lk.sliit.mad.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import java.util.ArrayList;
import java.util.List;

import lk.sliit.mad.myapplication.entity.Message;

public class FeedReaderDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "IT18141948.db"; //Database Name
    //For User Table
    private static final String SQL_CREATE_ENTRIES = //FeedReaderContract.FeedEntry(User) = FeedReaderContract FeedEntry inner class
            "CREATE TABLE " + FeedReaderContract.User.TABLE_NAME + " (" +
                    FeedReaderContract.User._ID + " INTEGER PRIMARY KEY," +
                    FeedReaderContract.User.COLUMN_NAME + " TEXT," +
                    FeedReaderContract.User.COLUMN_PASSWORD + " TEXT," +
                    FeedReaderContract.User.COLUMN_TYPE + " TEXT)";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedReaderContract.User.TABLE_NAME;
    //For Message Table
    private static final String SQL_CREATE_ENTRIES_MESSAGE = //FeedReaderContract.FeedEntry = FeedReaderContract FeedEntry inner class
            "CREATE TABLE " + FeedReaderContract.Message.TABLE_NAME + " (" +
                    FeedReaderContract.Message._ID + " INTEGER PRIMARY KEY," +
                    FeedReaderContract.Message.COLUMN_USER + " TEXT," +
                    FeedReaderContract.Message.COLUMN_SUBJECT + " TEXT," +
                    FeedReaderContract.Message.COLUMN_MESSAGE + " TEXT)";
    private static final String SQL_DELETE_ENTRIES_MESSAGE =
            "DROP TABLE IF EXISTS " + FeedReaderContract.Message.TABLE_NAME;

    public FeedReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES_MESSAGE);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_DELETE_ENTRIES_MESSAGE);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

///////////////////////////////////////////////  USER  ////////////////////////////////////////////

    //Add User Data
    public long addUser(String name, String password, String type) {
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.User.COLUMN_NAME, name);
        values.put(FeedReaderContract.User.COLUMN_PASSWORD, password);
        values.put(FeedReaderContract.User.COLUMN_TYPE, type);

// Insert the new row, returning the primary key value of the new  row
        long newRowId = db.insert(FeedReaderContract.User.TABLE_NAME, null, values);
        return newRowId;
    }

    //Update User Data
    public boolean updateUser(String name, String password, String type) {
        SQLiteDatabase db = getWritableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.User.COLUMN_NAME, name);
        values.put(FeedReaderContract.User.COLUMN_PASSWORD, password);
        values.put(FeedReaderContract.User.COLUMN_TYPE, type);

// Which row to update, based on the title
        String selection = FeedReaderContract.User.COLUMN_NAME + " LIKE ?";
        String[] selectionArgs = {name};

        int count = db.update(
                FeedReaderContract.User.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        if(count > 0){
            return true;
        }else {
            return false;
        }
    }

public void deleteInfo(String name){
    SQLiteDatabase db = getWritableDatabase();
    // Define 'where' part of query.
    String selection = FeedReaderContract.User.COLUMN_NAME + " LIKE ?";
// Specify arguments in placeholder order.
    String[] selectionArgs = { "MyTitle" };
// Issue SQL statement.
    int deletedRows = db.delete(FeedReaderContract.User.TABLE_NAME, selection, selectionArgs);

}

public List loadData(){
    SQLiteDatabase db = getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
    String[] projection = {
            BaseColumns._ID,
            FeedReaderContract.User.COLUMN_NAME,
            FeedReaderContract.User.COLUMN_PASSWORD,
            FeedReaderContract.User.COLUMN_TYPE
    };

// Filter results WHERE "title" = 'My Title'
    String selection =  FeedReaderContract.User.COLUMN_NAME + " = ?";
    String[] selectionArgs = { "My Title" };

// How you want the results sorted in the resulting Cursor
    String sortOrder =
            FeedReaderContract.User.COLUMN_NAME + " ASC";

    Cursor cursor = db.query(
            FeedReaderContract.User.TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            null,              // The columns for the WHERE clause
            null,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            sortOrder               // The sort order
    );
    List itemIds = new ArrayList<>();
    while(cursor.moveToNext()) {
        long itemId = cursor.getLong(
                cursor.getColumnIndexOrThrow(FeedReaderContract.User._ID));
        itemIds.add(itemId);
    }
    cursor.close();
    return itemIds;

}



    public Cursor loadSelectedData(String userName, String password) {
        SQLiteDatabase db = getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                FeedReaderContract.User.COLUMN_NAME,
                FeedReaderContract.User.COLUMN_PASSWORD,
                FeedReaderContract.User.COLUMN_TYPE
        };

// Filter results WHERE "title" = 'My Title'
        String selection = FeedReaderContract.User.COLUMN_NAME + " LIKE ?" + " AND " + FeedReaderContract.User.COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {userName, password};

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                FeedReaderContract.User.COLUMN_NAME + " ASC";

        Cursor cursor = db.query(
                FeedReaderContract.User.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        return cursor;

    }
    ///////////////////////////////////////////////  MESSAGE  ////////////////////////////////////////////

    //Add Message Data
    public long addMessage(String user, String subject, String message) {
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.Message.COLUMN_USER, user);
        values.put(FeedReaderContract.Message.COLUMN_SUBJECT, subject);
        values.put(FeedReaderContract.Message.COLUMN_MESSAGE, message);

// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(FeedReaderContract.Message.TABLE_NAME, null, values);
return newRowId;
    }

    //Update Message Data
    public void updateMessage(String user, String subject, String message) {
        SQLiteDatabase db = getWritableDatabase();

// New value for one column
        String title = "MyNewTitle";
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.Message.COLUMN_USER, title);

// Which row to update, based on the title
        String selection = FeedReaderContract.Message.COLUMN_MESSAGE + " LIKE ?";
        String[] selectionArgs = {"MyOldTitle"};

        int count = db.update(
                FeedReaderContract.Message.TABLE_NAME,
                values,
                selection,
                selectionArgs);


    }



    public ArrayList loadMessageData(){
        SQLiteDatabase db = getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                FeedReaderContract.Message.COLUMN_USER,
                FeedReaderContract.Message.COLUMN_SUBJECT,
                FeedReaderContract.Message.COLUMN_MESSAGE
        };


// How you want the results sorted in the resulting Cursor
        String sortOrder =
                FeedReaderContract.Message.COLUMN_SUBJECT + " ASC";

        Cursor cursor = db.query(
                FeedReaderContract.Message.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        ArrayList itemIds = new ArrayList<>();
        while(cursor.moveToNext()) {

            String user = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.Message.COLUMN_USER));
            String subject = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.Message.COLUMN_SUBJECT));
            String msg = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.Message.COLUMN_MESSAGE));
            Message message = new Message(user,subject,msg);
            itemIds.add(message);
        }
        cursor.close();
        return itemIds;

    }
}
