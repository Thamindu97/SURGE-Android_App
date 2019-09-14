package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.surge.Clothes;

import java.util.ArrayList;
import java.util.List;

public class DBHandler_AddClothes extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Clothes.db";

    public DBHandler_AddClothes(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_Clothes =
                "CREATE TABLE " + Contractor.Clothes.TABLE2_NAME + " (" +
                        Contractor.Clothes._ID + " INTEGER PRIMARY KEY ," +
                        Contractor.Clothes.COLUMN2_NAME_CLOTHTYPE + " TEXT," +
                        Contractor.Clothes.COLUMN2_NAME_SIZE + " TEXT," +
                        Contractor.Clothes.COLUMN2_NAME_COLOUR + " TEXT," +
                        Contractor.Clothes.COLUMN2_NAME_PRICE + " TEXT)";
        //Specify the primary key from the BaseColumns interface.

        db.execSQL(SQL_CREATE_Clothes);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + Contractor.Clothes.TABLE2_NAME;
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);

    }

    public boolean addClothes(String clothtype, String size,String colour,String price) {
        SQLiteDatabase db = getWritableDatabase();

        // Gets the data repository in write mode
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(Contractor.Clothes.COLUMN2_NAME_CLOTHTYPE, clothtype);
        values.put(Contractor.Clothes.COLUMN2_NAME_SIZE, size);
        values.put(Contractor.Clothes.COLUMN2_NAME_COLOUR, colour);
        values.put(Contractor.Clothes.COLUMN2_NAME_PRICE, price);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(Contractor.Clothes.TABLE2_NAME, null, values);

        if(newRowId == -1)
            return false;
        else
            return true;

    }

    public List readAllClothes(){

        //get readable mode
        SQLiteDatabase db = getReadableDatabase();

        //projection
        String[] projection = {Contractor.Clothes._ID,
                Contractor.Clothes.COLUMN2_NAME_CLOTHTYPE,
                Contractor.Clothes.COLUMN2_NAME_SIZE,
                Contractor.Clothes.COLUMN2_NAME_COLOUR,
                Contractor.Clothes.COLUMN2_NAME_PRICE
        };

        //database query which returns a cursor object
        Cursor cursor = db.query(
                Contractor.Clothes.TABLE2_NAME,
                projection,
                null,
                null,
                null,
                null,
                null,
                null
        );

        //list declarations
        List<Clothes> stockList = new ArrayList<>();

        while(cursor.moveToNext()){

            int cId = cursor.getInt(cursor.getColumnIndexOrThrow(Contractor.Clothes._ID));
            String cClothType = cursor.getString(cursor.getColumnIndexOrThrow(Contractor.Clothes.COLUMN2_NAME_CLOTHTYPE));
            String cClothSize = cursor.getString(cursor.getColumnIndexOrThrow(Contractor.Clothes.COLUMN2_NAME_SIZE));
            String cColour = cursor.getString(cursor.getColumnIndexOrThrow(Contractor.Clothes.COLUMN2_NAME_COLOUR));
            String cPrice = cursor.getString(cursor.getColumnIndexOrThrow(Contractor.Clothes.COLUMN2_NAME_PRICE));

            //add the retrieved stocks information into the product class using the overloaded constructor
            Clothes stocks = new Clothes(cId, cClothType, cClothSize, cColour, cPrice);

            stockList.add(stocks);
        }

        cursor.close();

        return stockList;
    }
}
