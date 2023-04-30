package com.example.foodnow.backend;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.example.foodnow.Restaurant;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "foodnow.db";
    private final String _restaurant_id = FavoriteContract.FavouriteEntry.COLUMN_NAME_RESTAURANT_ID;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTables());
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
        db.execSQL(dropTables());
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private String createTables() {
        return "CREATE TABLE " + FavoriteContract.FavouriteEntry.TABLE_NAME + " ("
                +
                FavoriteContract.FavouriteEntry._ID + " INTEGER PRIMARY KEY, " +
                _restaurant_id + " INTEGER UNIQUE)";
    }

    private String dropTables() {
        return "DROP TABLE IF EXISTS " +
                FavoriteContract.FavouriteEntry.TABLE_NAME;
    }

    public long setFavourite(Restaurant restaurant) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(_restaurant_id, restaurant.id);

        return db.insert(FavoriteContract.FavouriteEntry.TABLE_NAME, null, values);
    }

    public long unsetFavourite(Restaurant restaurant) {
        SQLiteDatabase db = this.getWritableDatabase();

        String selection = _restaurant_id + " = ?";
        String[] selectionArgs = {Integer.toString(restaurant.id)};

        return db.delete(FavoriteContract.FavouriteEntry.TABLE_NAME, selection, selectionArgs);
    }


    public ArrayList<Integer> getFavourites() {
        ArrayList<Integer> favourites = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                _restaurant_id
        };
        // How you want the results sorted in the resulting Cursor
        String sortOrder = _restaurant_id + " ASC";
        Cursor cursor = db.query(
                FavoriteContract.FavouriteEntry.TABLE_NAME, // The table to query
                projection, // The array of columns to return
                null, // The columns for the WHERE clause
                null, // The values for the WHERE clause
                null, // don't group the rows
                null, // don't filter by row groups
                sortOrder // The sort order
        );

        while (cursor.moveToNext()) {
            long id =
                    cursor.getLong(cursor.getColumnIndexOrThrow(BaseColumns._ID));
            int restaurant_id =
                    cursor.getInt(cursor.getColumnIndexOrThrow(_restaurant_id));
            favourites.add(restaurant_id);
        }
        cursor.close();
        return favourites;
    }

    public boolean getFavouriteByID(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                _restaurant_id
        };
        // Filter results WHERE "id" = condition
        String selection = _restaurant_id + " = ?";
        String[] selectionArgs = {Integer.toString(id)};
        Cursor cursor = db.query(
                FavoriteContract.FavouriteEntry.TABLE_NAME, // The table to query
                projection, // The array of columns to return
                selection, // The columns for the WHERE clause
                selectionArgs, // The values for the WHERE clause
                null, // don't group the rows
                null, // don't filter by row groups
                null // The sort order
        );
        //if the cursor has next, it means that the restaurant was in favourites
        //therefore return true
        //otherwise, the query returned 0 rows, meaning the the restaurant was not in favourites
        //therefore return false;
        boolean res = cursor.moveToNext();
        //close the cursor
        cursor.close();
        return res;
    }
}