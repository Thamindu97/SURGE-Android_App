package Database;

import android.provider.BaseColumns;

public class Shipment implements BaseColumns {

    private Shipment() {

    }

    //Shipment Details
    public static class BuyInfo implements BaseColumns {

        public static final String TABLE4_NAME = "buyinfo";
        public static final String COLUMN4_NAME_NAME = "name";
        public static final String COLUMN4_NAME_PHONE = "phone";
        public static final String COLUMN4_NAME_EMAIL = "email";
        public static final String COLUMN4_NAME_STREET = "street";
        public static final String COLUMN4_NAME_CITY = "city";
    }
}
