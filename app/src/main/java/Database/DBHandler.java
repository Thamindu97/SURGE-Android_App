package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.surge.AboutMe;

import java.util.ArrayList;
import java.util.List;


public class   DBHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Surge.db";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String SQL_CREATE_Customer =
                "CREATE TABLE " + UsersMaster.TABLE1_NAME + " (" +
                        UsersMaster._ID + " INTEGER PRIMARY KEY," +
                        UsersMaster.COLUMN1_NAME_USERNAME + " TEXT," +
                        UsersMaster.COLUMN1_NAME_EMAIL + " TEXT," +
                        UsersMaster.COLUMN1_NAME_MOBILENO + " TEXT," +
                        UsersMaster.COLUMN1_NAME_PASSWORD + " TEXT)";
        //Specify the primary key from the BaseColumns interface.

        db.execSQL(SQL_CREATE_Customer);

        //FOR adding clothes (ashfaq)




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

    public boolean addRegisterInfo(String userName, String email, String phoneNo , String password )
    {
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Create a new map of values
        ContentValues values = new ContentValues();
        values.put(UsersMaster.COLUMN1_NAME_USERNAME, userName);
        values.put(UsersMaster.COLUMN1_NAME_EMAIL, email);
        values.put(UsersMaster.COLUMN1_NAME_MOBILENO, phoneNo);
        values.put(UsersMaster.COLUMN1_NAME_PASSWORD, password);

        // Insert new Row
        long newRowId = db.insert(UsersMaster.TABLE1_NAME, null,values);

        if(newRowId >= 1)
            return true;
        else
            return false;
    }


    // take the details of logged in user
    public void readLoggedUserInfo(String uName)
    {
        SQLiteDatabase db = getReadableDatabase();

        // define a projection that specifies which columns from the database
        // you will actually use after this query
        String[] projection = {
                UsersMaster._ID,
                UsersMaster.COLUMN1_NAME_USERNAME,
                UsersMaster.COLUMN1_NAME_EMAIL,
                UsersMaster.COLUMN1_NAME_MOBILENO,
                //UsersMaster.COLUMN1_NAME_ADDRESS,
                UsersMaster.COLUMN1_NAME_PASSWORD
        };

        //Filter results WHERE "uName" = ''
        String selection = UsersMaster.COLUMN1_NAME_USERNAME + " = ?" ;
        String[] selectionArgs = {uName};



        Cursor cursor = db.query(
                UsersMaster.TABLE1_NAME,           // the table to query
                projection,                 // the columns to return
                selection,               // the columns for the WHERE clause
                selectionArgs,            // the values for the WHERE clause
                null,               // don't group the rows
                null,                // don't filter by row groups
                null                  // the sort order
        );

        while(cursor.moveToNext()) {
            AboutMe ab = new AboutMe();
            ab.txt_Name.setText(cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.COLUMN1_NAME_USERNAME)));
            ab.txt_Email.setText(cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.COLUMN1_NAME_EMAIL)));
            ab.txt_Phone.setText(cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.COLUMN1_NAME_MOBILENO)));
            //ab.txt_Address.setText( cursor.getString( cursor.getColumnIndexOrThrow(UsersMaster.COLUMN1_NAME_ADDRESS)));
            ab.txt_Password.setText(cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.COLUMN1_NAME_PASSWORD)));
        }

    }


}






