package Database;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.surge.AboutMe;
import com.example.surge.Accessories;
import com.example.surge.MainActivity;

import java.util.ArrayList;
import java.util.List;

import java.util.List;

import static android.provider.BaseColumns._ID;


public class   DBHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "SurgeAppDB.db";
    public static final int DATABASE_VERSION = 1;
    private static final String TAG = DBHandler.class.getSimpleName();
    Context context;
    SQLiteDatabase db;
    ContentResolver mContentResolver;


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        mContentResolver = context.getContentResolver();

        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String SQL_CREATE_Customer =
                "CREATE TABLE " + UsersMaster.TABLE1_NAME + " (" +
                        UsersMaster._ID + " INTEGER PRIMARY KEY ," +
                        UsersMaster.COLUMN1_NAME_USERNAME + " TEXT," +
                        UsersMaster.COLUMN1_NAME_EMAIL + " TEXT," +
                        UsersMaster.COLUMN1_NAME_MOBILENO + " TEXT," +
                        UsersMaster.COLUMN1_NAME_PASSWORD + " TEXT)";
        //Specify the primary key from the BaseColumns interface.

        db.execSQL(SQL_CREATE_Customer);

        //FOR adding clothes (ashfaq)

        //Card Details

        String SQL_CREATE_CardDetails =
                "CREATE TABLE " + UsersMaster.CardDetails.TABLE3_NAME + " (" +
                        UsersMaster.CardDetails._ID + " INTEGER PRIMARY KEY," +
                        UsersMaster.CardDetails.COLUMN3_NAME_USERNAME + " TEXT," +
                        UsersMaster.CardDetails.COLUMN3_NAME_CARDNO + " TEXT," +
                        UsersMaster.CardDetails.COLUMN3_NAME_EXDATE + " TEXT," +
                        UsersMaster.CardDetails.COLUMN3_NAME_CVV + " TEXT)";

        db.execSQL(SQL_CREATE_CardDetails);

        //Creating Accessories table =
        String SQL_CREATE_ACCESSORIES =
                "CREATE TABLE " + UsersMaster.Accessories.TABLE_NAME + " (" +
                        UsersMaster.Accessories._ID + " INTEGER PRIMARY KEY," +
                        UsersMaster.Accessories.COLUMN_NAME_TYPE + " TEXT," +
                        UsersMaster.Accessories.COLUMN_NAME_SIZE + " TEXT," +
                        UsersMaster.Accessories.COLUMN_NAME_COLOUR + " TEXT," +
                        UsersMaster.Accessories.COLUMN_NAME_PRICE + " TEXT," +
                        UsersMaster.Accessories.COLUMN_NAME_IMAGE + " BLOB NOT NULL " + " );";

        db.execSQL(SQL_CREATE_ACCESSORIES);


//        String SQL_CREATE_IMAGE_TABLE =
//                "CREATE TABLE " + UsersMaster.Images.TABLE_NAME + " (" +
//                UsersMaster.Images._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
//                UsersMaster.Images.COLUMN_NAME + " BLOB NOT NULL " + " );";
//
//        db.execSQL(SQL_CREATE_IMAGE_TABLE);
//
//        Log.d(TAG, "Database Created Successfully" );


    }

//    public boolean addToDb(byte[] image) throws SQLiteException {
//        SQLiteDatabase database = this.getWritableDatabase();
//        ContentValues cv = new  ContentValues();
//        cv.put(UsersMaster.Accessories.COLUMN_NAME_IMAGE, image);
//        database.insert(UsersMaster.Accessories.TABLE_NAME, null, cv);
//
//        long newRowId = db.insert(UsersMaster.Accessories.TABLE_NAME, null,cv);
//
//        if (newRowId == -1)
//            return false;
//        else
//            return true;
//    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + UsersMaster.Accessories.TABLE_NAME);
        onCreate(db);
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

    //ACCESSORIES - START

    public boolean addAccessory(String ascType, String ascSize, String ascColour, String ascPrice, byte[] image) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UsersMaster.Accessories.COLUMN_NAME_TYPE, ascType);
        values.put(UsersMaster.Accessories.COLUMN_NAME_SIZE, ascSize);
        values.put(UsersMaster.Accessories.COLUMN_NAME_COLOUR, ascColour);
        values.put(UsersMaster.Accessories.COLUMN_NAME_PRICE, ascPrice);
        values.put(UsersMaster.Accessories.COLUMN_NAME_IMAGE, image);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(UsersMaster.Accessories.TABLE_NAME, null, values);

        if(newRowId == -1)
            return false;
        else
            return true;
    }

    public boolean deleteAccessory(int id) {
        //get readable mode
        SQLiteDatabase db = getReadableDatabase();

        //selection
        String selection = UsersMaster.Accessories._ID + " LIKE ?";

        //Argument
        String[] selectionArg = {String.valueOf(id)};

        //query to delete a sale
        int success = db.delete(UsersMaster.Accessories.TABLE_NAME,
                selection,
                selectionArg
        );

        if(success == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor getAllAccessories() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+UsersMaster.Accessories.TABLE_NAME,null);
        return res;
    }

    public List readAllAccessories(){

        //get a readable mode of the db
        SQLiteDatabase db = getReadableDatabase();

        //projection
        String[] projection = {UsersMaster.Accessories._ID,
                UsersMaster.Accessories.COLUMN_NAME_TYPE,
                UsersMaster.Accessories.COLUMN_NAME_SIZE,
                UsersMaster.Accessories.COLUMN_NAME_COLOUR,
                UsersMaster.Accessories.COLUMN_NAME_PRICE
        };

        //database query which returns a cursor object
        Cursor cursor = db.query(
                UsersMaster.Accessories.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null,
                null
        );

        //list declarations
        List<Accessories> AccessList = new ArrayList<>();

        while(cursor.moveToNext()){

            int acsId = cursor.getInt(cursor.getColumnIndexOrThrow(UsersMaster.Accessories._ID));
            String acsType = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Accessories.COLUMN_NAME_TYPE));
            String acsSize = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Accessories.COLUMN_NAME_SIZE));
            String acsColour = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Accessories.COLUMN_NAME_COLOUR));
            String acsPrice = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Accessories.COLUMN_NAME_PRICE));

            //add the retrieved stocks information into the product class using the overloaded constructor
            Accessories acs = new Accessories(acsId, acsType, acsSize, acsColour, acsPrice);

            AccessList.add(acs);
        }

        cursor.close();

        return AccessList;
    }

    //ACCESSORIES - END

    //Add Card Detials
    public boolean addCardDetails(String name, String cardno, String date, String cvv) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UsersMaster.CardDetails.COLUMN3_NAME_USERNAME, name);
        values.put(UsersMaster.CardDetails.COLUMN3_NAME_CARDNO, cardno);
        values.put(UsersMaster.CardDetails.COLUMN3_NAME_EXDATE, date);
        values.put(UsersMaster.CardDetails.COLUMN3_NAME_CVV, cvv);

        long newRowID = db.insert(UsersMaster.CardDetails.TABLE3_NAME, null, values);

        if (newRowID >= 1)
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
            ab.txt_Password.setText(cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.COLUMN1_NAME_PASSWORD)));
        }

    }

    // update customer details
    public boolean updateCustomerInfo(String userName, String email, String phone, String password) {

        SQLiteDatabase db = getReadableDatabase();

        //New value for one column
        ContentValues values = new ContentValues();
        values.put(UsersMaster.COLUMN1_NAME_EMAIL, email);
        values.put(UsersMaster.COLUMN1_NAME_MOBILENO,phone);
        values.put(UsersMaster.COLUMN1_NAME_PASSWORD,password);

        //Which row to update, based on the title
        String selection = UsersMaster.COLUMN1_NAME_USERNAME + " LIKE ?";
        String[] selectionArgs = {userName};

        int count = db.update(
                UsersMaster.TABLE1_NAME,
                values,
                selection,                   // the columns for the WHERE clause
                selectionArgs               // the values for the WHERE clause
        );

        if(count >= 1)
            return true;
        else
            return false;
    }

    // delete customer info
    public void deleteCustomerInfo(String userName){

        SQLiteDatabase db = getReadableDatabase();

        //Define 'where' part of query
        String selection = UsersMaster.COLUMN1_NAME_USERNAME + " LIKE ?";

        //Specify arguments n placeholder order
        String[] selectionArgs = { userName };

        //Issue SQL statement
        db.delete(UsersMaster.TABLE1_NAME, selection, selectionArgs);

    }



    // search the given user
    public boolean readUserInfo(String uName, String pwd)
    {
        SQLiteDatabase db = getReadableDatabase();

        // define a projection that specifies which columns from the database
        // you will actually use after this query
        String[] projection = {
                UsersMaster._ID,
                UsersMaster.COLUMN1_NAME_USERNAME,
                UsersMaster.COLUMN1_NAME_PASSWORD
        };

        //Filter results WHERE "userName" = '?' and "password" = '?'
        String selection = UsersMaster.COLUMN1_NAME_USERNAME + " = ?" + " AND " + UsersMaster.COLUMN1_NAME_PASSWORD + " = ?";

        String[] selectionArgs = {uName, pwd};


        Cursor cursor = db.query(
                UsersMaster.TABLE1_NAME,           // the table to query
                projection,                 // the columns to return
                selection,               // the columns for the WHERE clause
                selectionArgs,            // the values for the WHERE clause
                null,               // don't group the rows
                null,                // don't filter by row groups
                null                  // the sort order
        );


        while(cursor.moveToNext()){
            MainActivity mn = new MainActivity();
            mn.userName =(cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.COLUMN1_NAME_USERNAME)));
        }

        if (cursor.getCount() == 0)
            return false;
        else
            return true;
        // cursor.close();

    }

}
