package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.surge.AboutMe;
import com.example.surge.Accessories;
import com.example.surge.AccessoriesUpdate;
import com.example.surge.BuyInfo;
import com.example.surge.Clothes;
import com.example.surge.DbBitmapUtility;
import com.example.surge.EditBuyInfo;
import com.example.surge.MainActivity;

import java.util.ArrayList;
import java.util.List;


public class   DBHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Surge2019.db";

    public static String usname, cname;

    public static String aid;

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
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
                        UsersMaster.COLUMN1_NAME_PASSWORD + " TEXT," +
                        UsersMaster.COLUMN1_NAME_IMAGE + " BLOB)";
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

        //Buy Info

        String SQL_CREATE_BuyInfo =
                "CREATE TABLE " + UsersMaster.BuyInfo.TABLE4_NAME + " (" +
                        UsersMaster.BuyInfo._ID + " INTEGER PRIMARY KEY," +
                        UsersMaster.BuyInfo.COLUMN4_NAME_USERNAME + " TEXT," +
                        UsersMaster.BuyInfo.COLUMN4_NAME_PHONE + " TEXT," +
                        UsersMaster.BuyInfo.COLUMN4_NAME_EMAIL + " TEXT," +
                        UsersMaster.BuyInfo.COLUMN4_NAME_ADDRESS + " TEXT)";

        db.execSQL(SQL_CREATE_BuyInfo);

        //clothes table
        String SQL_CREATE_Clothes =
                "CREATE TABLE " + UsersMaster.Clothes.TABLE2_NAME + " (" +
                        UsersMaster.Clothes._ID + " INTEGER PRIMARY KEY ," +
                        UsersMaster.Clothes.COLUMN2_NAME_CLOTHTYPE + " TEXT," +
                        UsersMaster.Clothes.COLUMN2_NAME_SIZE + " TEXT," +
                        UsersMaster.Clothes.COLUMN2_NAME_COLOUR + " TEXT," +
                        UsersMaster.Clothes.COLUMN2_NAME_PRICE + " TEXT," +
                        UsersMaster.Clothes.COLUMN2_NAME_IMAGE + " BLOB)";
        //Specify the primary key from the BaseColumns interface.

        db.execSQL(SQL_CREATE_Clothes);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {


    }

    // Registering an user
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
        values.put(UsersMaster.COLUMN1_NAME_IMAGE, "");

        // Insert new Row
        long newRowId = db.insert(UsersMaster.TABLE1_NAME, null,values);

        if(newRowId >= 1)
            return true;
        else
            return false;
    }

    // User  DP image insert
    public void addUserDPEntry( String uname, byte[] image) throws SQLiteException {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new  ContentValues();
        cv.put(UsersMaster.COLUMN1_NAME_IMAGE,   image);


        //Which row to update, based on the title
        String selection = UsersMaster.COLUMN1_NAME_USERNAME + " LIKE ?";
        String[] selectionArgs = {uname};


        db.update(
                UsersMaster.TABLE1_NAME,
                cv,
                selection,                   // the columns for the WHERE clause
                selectionArgs               // the values for the WHERE clause
        );



    }

    public byte[] retrieveDP(String uName)
    {

        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                UsersMaster.COLUMN1_NAME_IMAGE
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
            byte[] image = cursor.getBlob(0);
            return image;
        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }

        return null;
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

    public void showAccessoriesInfo(String id)
    {
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                UsersMaster.Accessories._ID,
                UsersMaster.Accessories.COLUMN_NAME_TYPE,
                UsersMaster.Accessories.COLUMN_NAME_SIZE,
                UsersMaster.Accessories.COLUMN_NAME_COLOUR,
                UsersMaster.Accessories.COLUMN_NAME_PRICE
        };

        String selection = UsersMaster.Accessories._ID + " = ?" ;
        String[] selectionArgs = {id};



        Cursor cursor = db.query(
                UsersMaster.Accessories.TABLE_NAME,           // the table to query
                projection,                 // the columns to return
                selection,               // the columns for the WHERE clause
                selectionArgs,            // the values for the WHERE clause
                null,               // don't group the rows
                null,                // don't filter by row groups
                null                  // the sort order
        );

        while(cursor.moveToNext()) {
            AccessoriesUpdate editBuyInfo = new AccessoriesUpdate();
            editBuyInfo.accessType.setText(cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Accessories.COLUMN_NAME_TYPE)));
            editBuyInfo.accessColour.setText(cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Accessories.COLUMN_NAME_COLOUR)));
            editBuyInfo.accessSize.setText(cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Accessories.COLUMN_NAME_SIZE)));
            editBuyInfo.accessPrice.setText(cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Accessories.COLUMN_NAME_PRICE)));
        }

    }

    public boolean updateAccessoryInfo(String id, String type, String colour, String size, String price)
    {
        SQLiteDatabase db = getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(UsersMaster.Accessories.COLUMN_NAME_TYPE,type);
        values.put(UsersMaster.Accessories.COLUMN_NAME_COLOUR,colour);
        values.put(UsersMaster.Accessories.COLUMN_NAME_SIZE,size);
        values.put(UsersMaster.Accessories.COLUMN_NAME_PRICE,price);

        String selection = UsersMaster.Accessories._ID + " LIKE ?";
        String[] selectionArgs = {id};

        int count = db.update(UsersMaster.Accessories.TABLE_NAME,values,selection,selectionArgs);

        if (count >= 1)
            return true;
        else
            return false;

    }

    //ACCESSORIES - END

    // ********************* CARD DETAILS *********************

    //INSERT CARD DETAILS

    public boolean addCardDetails(String name, String cardno, String date, String cvv) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UsersMaster.CardDetails.COLUMN3_NAME_USERNAME, name);
        values.put(UsersMaster.CardDetails.COLUMN3_NAME_CARDNO, cardno);
        values.put(UsersMaster.CardDetails.COLUMN3_NAME_EXDATE, date);
        values.put(UsersMaster.CardDetails.COLUMN3_NAME_CVV, cvv);

        cname = name;

        long newRowID = db.insert(UsersMaster.CardDetails.TABLE3_NAME, null, values);

        if (newRowID >= 1)
            return true;
        else
            return false;
    }

    //DELETE CARD DETAILS

    public void deleteCardDetails()
    {
        SQLiteDatabase db = getReadableDatabase();

        String selection = UsersMaster.CardDetails.COLUMN3_NAME_USERNAME + " LIKE ?";
        String[] selectionArgs = {cname};

        db.delete(UsersMaster.CardDetails.TABLE3_NAME,selection,selectionArgs);
    }

    // ********************* END OF CARD DETAILS *********************

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
                UsersMaster.COLUMN1_NAME_PASSWORD,
                UsersMaster.COLUMN1_NAME_IMAGE
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
            if(UsersMaster.COLUMN1_NAME_IMAGE != "") {
                ab.imageView.setImageBitmap(DbBitmapUtility.getImage(retrieveDP(uName)));
            }
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

    // ********************* BUY INFO *********************

    //INSERT BUY INFO

    public boolean addBuyInfo(String name, String phone, String email, String address) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UsersMaster.BuyInfo.COLUMN4_NAME_USERNAME, name);
        values.put(UsersMaster.BuyInfo.COLUMN4_NAME_PHONE, phone);
        values.put(UsersMaster.BuyInfo.COLUMN4_NAME_EMAIL, email);
        values.put(UsersMaster.BuyInfo.COLUMN4_NAME_ADDRESS, address);

        //String usname;

        usname = name;

        long newRowID = db.insert(UsersMaster.BuyInfo.TABLE4_NAME,null, values);

        if (newRowID >= 1)
            return true;
        else
            return false;

    }

    //RETRIEVE BUY INFO

    public void showBuyInfo()
    {
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                UsersMaster.BuyInfo._ID,
                UsersMaster.BuyInfo.COLUMN4_NAME_USERNAME,
                UsersMaster.BuyInfo.COLUMN4_NAME_PHONE,
                UsersMaster.BuyInfo.COLUMN4_NAME_EMAIL,
                UsersMaster.BuyInfo.COLUMN4_NAME_ADDRESS
        };

        String selection = UsersMaster.BuyInfo.COLUMN4_NAME_USERNAME + " = ?" ;
        String[] selectionArgs = {usname};



        Cursor cursor = db.query(
                UsersMaster.BuyInfo.TABLE4_NAME,           // the table to query
                projection,                 // the columns to return
                selection,               // the columns for the WHERE clause
                selectionArgs,            // the values for the WHERE clause
                null,               // don't group the rows
                null,                // don't filter by row groups
                null                  // the sort order
        );

        while(cursor.moveToNext()) {
            EditBuyInfo editBuyInfo = new EditBuyInfo();
            editBuyInfo.name.setText(cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.BuyInfo.COLUMN4_NAME_USERNAME)));
            editBuyInfo.phone.setText(cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.BuyInfo.COLUMN4_NAME_PHONE)));
            editBuyInfo.email.setText(cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.BuyInfo.COLUMN4_NAME_EMAIL)));
            editBuyInfo.address.setText(cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.BuyInfo.COLUMN4_NAME_ADDRESS)));
        }

    }

    //UPDATE BUY INFO

    public boolean updateBuyInfo(String uname, String uphone, String uemail, String uaddress)
    {
        SQLiteDatabase db = getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(UsersMaster.BuyInfo.COLUMN4_NAME_USERNAME,uname);
        values.put(UsersMaster.BuyInfo.COLUMN4_NAME_PHONE,uphone);
        values.put(UsersMaster.BuyInfo.COLUMN4_NAME_EMAIL,uemail);
        values.put(UsersMaster.BuyInfo.COLUMN4_NAME_ADDRESS,uaddress);

        String selection = UsersMaster.BuyInfo.COLUMN4_NAME_USERNAME + " LIKE ?";
        String[] selectionArgs = {uname};

        int count = db.update(UsersMaster.BuyInfo.TABLE4_NAME,values,selection,selectionArgs);

        if (count >= 1)
            return true;
        else
            return false;

    }

    // ********************* END OF BUY INFO *********************

    /////------------------------------------clothes table crud------------------------------------------------/////



    public boolean addClothes(String clothtype, String size, String colour, String price, byte[] image) {
        SQLiteDatabase db = getWritableDatabase();

        // Gets the data repository in write mode
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(UsersMaster.Clothes.COLUMN2_NAME_CLOTHTYPE, clothtype);
        values.put(UsersMaster.Clothes.COLUMN2_NAME_SIZE, size);
        values.put(UsersMaster.Clothes.COLUMN2_NAME_COLOUR, colour);
        values.put(UsersMaster.Clothes.COLUMN2_NAME_PRICE, price);
        values.put(UsersMaster.Clothes.COLUMN2_NAME_IMAGE, image);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(UsersMaster.Clothes.TABLE2_NAME, null, values);

        if(newRowId == -1)
            return false;
        else
            return true;

    }
    //retrieving clothes
    public List readAllClothes(){

        //get readable mode
        SQLiteDatabase db = getReadableDatabase();

        //projection
        String[] projection = {UsersMaster.Clothes._ID,
                UsersMaster.Clothes.COLUMN2_NAME_CLOTHTYPE,
                UsersMaster.Clothes.COLUMN2_NAME_SIZE,
                UsersMaster.Clothes.COLUMN2_NAME_COLOUR,
                UsersMaster.Clothes.COLUMN2_NAME_PRICE,
                UsersMaster.Clothes.COLUMN2_NAME_IMAGE
        };

        //database query which returns a cursor object
        Cursor cursor = db.query(
                UsersMaster.Clothes.TABLE2_NAME,
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

            int cId = cursor.getInt(cursor.getColumnIndexOrThrow(UsersMaster.Clothes._ID));
            String cClothType = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Clothes.COLUMN2_NAME_CLOTHTYPE));
            String cClothSize = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Clothes.COLUMN2_NAME_SIZE));
            String cColour = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Clothes.COLUMN2_NAME_COLOUR));
            String cPrice = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Clothes.COLUMN2_NAME_PRICE));
            byte[] cImage = cursor.getBlob(cursor.getColumnIndexOrThrow(UsersMaster.Clothes.COLUMN2_NAME_IMAGE));

            //add the retrieved stocks information into the product class using the overloaded constructor
            Clothes stocks = new Clothes(cId, cClothType, cClothSize, cColour, cPrice,cImage);

            stockList.add(stocks);
        }

        cursor.close();

        return stockList;
    }


    //updating clothes
    public boolean updateStocks(int cId,String clothtype, String size, String colour, String price){

        //get readable mode
        SQLiteDatabase db = getReadableDatabase();

        //creation of a map of values to have the new values
        ContentValues values = new ContentValues();

        values.put(UsersMaster.Clothes.COLUMN2_NAME_CLOTHTYPE,clothtype);
        values.put(UsersMaster.Clothes.COLUMN2_NAME_SIZE,size);
        values.put(UsersMaster.Clothes.COLUMN2_NAME_COLOUR,colour);
        values.put(UsersMaster.Clothes.COLUMN2_NAME_PRICE,price);

        //selection
        String selection = UsersMaster.Clothes._ID + " LIKE ?";
        String[] selectionArg = new String[] {String.valueOf(cId)};

        //db query to update
        int success = db.update(UsersMaster.Clothes.TABLE2_NAME,
                values,
                selection,
                selectionArg
        );

        if(success == 0){
            return false;
        }
        else{
            return true;
        }
    }

    //Retrieve Cloth Data

    public Cursor ClothData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Clothes", null);
        return cursor;
    }

    //deleting clothes
    public boolean deleteClothes(int pId){

        //get readable mode
        SQLiteDatabase db = getReadableDatabase();

        //selection
        String selection = UsersMaster.Clothes._ID + " LIKE ?";

        //Argument
        String[] selectionArg = {String.valueOf(pId)};

        //query to delete a sale
        int success = db.delete(UsersMaster.Clothes.TABLE2_NAME,
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

    //add cloth image
    public void addUserClothesEntry( String uname, byte[] image) throws SQLiteException {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new  ContentValues();
        cv.put(UsersMaster.Clothes.COLUMN2_NAME_IMAGE,   image);


        //Which row to update, based on the title
        String selection = UsersMaster.Clothes._ID ;
        String[] selectionArgs = {uname};


        db.update(
                UsersMaster.Clothes.TABLE2_NAME,
                cv,
                selection,                   // the columns for the WHERE clause
                selectionArgs               // the values for the WHERE clause
        );



    }


}
