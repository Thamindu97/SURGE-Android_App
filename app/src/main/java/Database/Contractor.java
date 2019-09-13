package Database;

import android.provider.BaseColumns;

public class Contractor {

    private Contractor(){

    }

    public static class Clothes implements BaseColumns {
        public static final String TABLE2_NAME = "Clothes";
        public static final String COLUMN2_NAME_CLOTHTYPE = "cloth_type";
        public static final String COLUMN2_NAME_SIZE = "size";
        public static final String COLUMN2_NAME_COLOUR = "colour";
        public static final String COLUMN2_NAME_PRICE = "price";
    }
}
