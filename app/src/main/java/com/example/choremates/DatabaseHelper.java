package com.example.choremates;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    /**
     * constructor method
     * @param context
     */
    public DatabaseHelper(@Nullable Context context) {
        super(context, "Login.db", null, 5);
    }

    /**
     * creates the table user, so we can enter the login information of the user
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user(email TEXT PRIMARY KEY, password TEXT)");
    }

    /**
     * doesn't create a new table if the table already exists
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
    }

    /**
     * a method that inserts the information into the database
     * @param email: the email the user signed up with
     * @param password: the password the user entered
     * @return
     */
    public boolean insert (String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        long ins = db.replace("user", null, contentValues);
        return ins != -1;
    }//end method insert

    /**
     * Check is email exists already
     * @param email: the email that the user is signing up with
     * @return the number of times the email is found in the database
     */
    public boolean checkEmail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email=?", new String[]{email});
        return cursor.getCount() <= 0;
    }//end method checkEmail

    public boolean checkCredentials (String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where email=? and password=?", new String[]{email, password});
        return cursor.getCount() > 0;
    }//end method checkCredentials

    public boolean changePassword (String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", password);
        db.update("user", contentValues, "email=?", new String[]{email});
        return true;
    }//end method checkCredentials

}
