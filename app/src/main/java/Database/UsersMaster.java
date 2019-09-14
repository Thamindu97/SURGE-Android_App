package Database;

import android.provider.BaseColumns;

public class UsersMaster implements BaseColumns {

    private UsersMaster(){

    }

    // UserTable
    public static final String TABLE1_NAME = "UserTable" ;
    public static final String COLUMN1_NAME_USERNAME = "name";
    public static final String COLUMN1_NAME_PASSWORD = "password";
    public static final String COLUMN1_NAME_EMAIL = "email";
    public static final String COLUMN1_NAME_MOBILENO = "mobileno";


    // Card Details Table
    public static class CardDetails implements BaseColumns {

        public static final String TABLE_NAME = "carddetails";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_CARDNO = "cardno";
        public static final String COLUMN_NAME_EXDATE= "exdate";
        public static final String COLUMN_NAME_CVV= "cvv";
    }


}

