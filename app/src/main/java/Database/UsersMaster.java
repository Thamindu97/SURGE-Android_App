package Database;

import android.provider.BaseColumns;

public class UsersMaster implements BaseColumns {

    private UsersMaster(){

    }

    // UserTable
    public static final String TABLE1_NAME = "UserDetailsTable" ;
    public static final String COLUMN1_NAME_USERNAME = "name";
    public static final String COLUMN1_NAME_PASSWORD = "password";
    public static final String COLUMN1_NAME_EMAIL = "email";
    public static final String COLUMN1_NAME_MOBILENO = "mobileno";
    public static final String COLUMN1_NAME_IMAGE = "image";



    // Card Details Table
    public static class CardDetails implements BaseColumns {

        public static final String TABLE3_NAME = "carddetails";
        public static final String COLUMN3_NAME_USERNAME = "username";
        public static final String COLUMN3_NAME_CARDNO = "cardno";
        public static final String COLUMN3_NAME_EXDATE = "exdate";
        public static final String COLUMN3_NAME_CVV = "cvv";
    }

    //Accessories Table
    public static class Accessories implements BaseColumns {

        public static final String TABLE_NAME = "Accessories";
        public static final String COLUMN_NAME_TYPE= "asc_type";
        public static final String COLUMN_NAME_SIZE = "size";
        public static final String COLUMN_NAME_COLOUR = "colour";
        public static final String COLUMN_NAME_PRICE = "price";
    }

    // Buy Info Table
    public static class BuyInfo implements BaseColumns {

        public static final String TABLE4_NAME = "buyinfo";
        public static final String COLUMN4_NAME_USERNAME = "name";
        public static final String COLUMN4_NAME_PHONE = "phone";
        public static final String COLUMN4_NAME_EMAIL = "email";
        public static final String COLUMN4_NAME_ADDRESS = "address";
    }

}

