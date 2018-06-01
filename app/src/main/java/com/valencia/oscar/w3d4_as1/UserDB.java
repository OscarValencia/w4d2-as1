package com.valencia.oscar.w3d4_as1;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDB extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "USERS";
    private static final String COLUMN_1 = "ID_VALUE";
    private static final String COLUMN_2 = "ID_NAME";
    private static final String COLUMN_3 = "LOGIN_USERNAME";
    private static final String COLUMN_4 = "LOGIN_PASSWORD";
    private static final String COLUMN_5 = "LOGIN_SALT";
    private static final String COLUMN_6 = "LOGIN_MD5";
    private static final String COLUMN_7 = "LOGIN_SHA1";
    private static final String COLUMN_8 = "LOGIN_SHA256";
    private static final String COLUMN_9 = "NAME_TITLE";
    private static final String COLUMN_10 = "NAME_FIRST";
    private static final String COLUMN_11 = "NAME_LAST";
    private static final String COLUMN_12 = "PICTURE_LARGE";
    private static final String COLUMN_13 = "PICTURE_MEDIUM";
    private static final String COLUMN_14 = "PICTURE_THUMBNAIL";
    private static final String COLUMN_15 = "LOCATION_STREET";
    private static final String COLUMN_16 = "LOCATION_CITY";
    private static final String COLUMN_17 = "LOCATION_STATE";
    private static final String COLUMN_18 = "LOCATION_POSTCODE";
    private static final String COLUMN_19 = "DBO";
    private static final String COLUMN_20 = "REGISTERED";
    private static final String COLUMN_21 = "PHONE";
    private static final String COLUMN_22 = "CELL";
    private static final String COLUMN_23 = "EMAIL";
    private static final String COLUMN_24 = "GENDER";
    private static final String COLUMN_25 = "NAT";

    private static final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME
            +" ( "
            +COLUMN_1+" VARCHAR PRIMARY KEY NOT NULL, "
            +COLUMN_2+" VARCHAR NOT NULL, "
            +COLUMN_3+" VARCHAR NOT NULL, "
            +COLUMN_4+" VARCHAR NOT NULL, "
            +COLUMN_5+" VARCHAR, "
            +COLUMN_6+" VARCHAR, "
            +COLUMN_7+" VARCHAR, "
            +COLUMN_8+" VARCHAR, "
            +COLUMN_9+" VARCHAR, "
            +COLUMN_10+" VARCHAR NOT NULL, "
            +COLUMN_11+" VARCHAR NOT NULL, "
            +COLUMN_12+" VARCHAR, "
            +COLUMN_13+" VARCHAR, "
            +COLUMN_14+" VARCHAR, "
            +COLUMN_15+" VARCHAR, "
            +COLUMN_16+" VARCHAR, "
            +COLUMN_17+" VARCHAR, "
            +COLUMN_18+" INTEGER, "
            +COLUMN_19+" DATETIME, "
            +COLUMN_20+" DATETIME, "
            +COLUMN_21+" VARCHAR, "
            +COLUMN_22+" VARCHAR, "
            +COLUMN_23+" VARCHAR, "
            +COLUMN_24+" VARCHAR, "
            +COLUMN_25+" VARCHAR "
            +" )";
    private static final String SQL_DROP = "DROP TABLE IF EXISTS "+TABLE_NAME;


    public UserDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public UserDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public static String getTableName() {
        return TABLE_NAME;
    }

    public static String getColumn1() {
        return COLUMN_1;
    }

    public static String getColumn2() {
        return COLUMN_2;
    }

    public static String getColumn3() {
        return COLUMN_3;
    }

    public static String getColumn4() {
        return COLUMN_4;
    }

    public static String getColumn5() {
        return COLUMN_5;
    }

    public static String getColumn6() {
        return COLUMN_6;
    }

    public static String getColumn7() {
        return COLUMN_7;
    }

    public static String getColumn8() {
        return COLUMN_8;
    }

    public static String getColumn9() {
        return COLUMN_9;
    }

    public static String getColumn10() {
        return COLUMN_10;
    }

    public static String getColumn11() {
        return COLUMN_11;
    }

    public static String getColumn12() {
        return COLUMN_12;
    }

    public static String getColumn13() {
        return COLUMN_13;
    }

    public static String getColumn14() {
        return COLUMN_14;
    }

    public static String getColumn15() {
        return COLUMN_15;
    }

    public static String getColumn16() {
        return COLUMN_16;
    }

    public static String getColumn17() {
        return COLUMN_17;
    }

    public static String getColumn18() {
        return COLUMN_18;
    }

    public static String getColumn19() {
        return COLUMN_19;
    }

    public static String getColumn20() {
        return COLUMN_20;
    }

    public static String getColumn21() {
        return COLUMN_21;
    }

    public static String getColumn22() {
        return COLUMN_22;
    }

    public static String getColumn23() {
        return COLUMN_23;
    }

    public static String getColumn24() {
        return COLUMN_24;
    }

    public static String getColumn25() {
        return COLUMN_25;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP);
        db.execSQL(SQL_CREATE);
    }
}
