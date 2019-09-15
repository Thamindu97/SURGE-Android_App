package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler_Shipment extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Shipment.db";

    public DBHandler_Shipment(Context context) {

        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Buy Info

        String SQL_CREATE_Buyinfo =
                "CREATE TABLE " + Shipment.BuyInfo.TABLE4_NAME + " (" +
                        Shipment.BuyInfo._ID + " INTEGER PRIMARY KEY," +
                        Shipment.BuyInfo.COLUMN4_NAME_NAME + " TEXT," +
                        Shipment.BuyInfo.COLUMN4_NAME_PHONE + " TEXT," +
                        Shipment.BuyInfo.COLUMN4_NAME_EMAIL + " TEXT," +
                        Shipment.BuyInfo.COLUMN4_NAME_STREET + " TEXT," +
                        Shipment.BuyInfo.COLUMN4_NAME_CITY + " TEXT)";

        db.execSQL(SQL_CREATE_Buyinfo);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Add Buy Info
    public boolean addBuyInfo(String name, String phone, String email, String street, String city) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Shipment.BuyInfo.COLUMN4_NAME_NAME, name);
        values.put(Shipment.BuyInfo.COLUMN4_NAME_PHONE, phone);
        values.put(Shipment.BuyInfo.COLUMN4_NAME_EMAIL, email);
        values.put(Shipment.BuyInfo.COLUMN4_NAME_STREET, street);
        values.put(Shipment.BuyInfo.COLUMN4_NAME_CITY, city);

        long newRowID = db.insert(Shipment.BuyInfo.TABLE4_NAME, null, values);

        if (newRowID >= 1)
            return true;
        else
            return false;
    }
}
