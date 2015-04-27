package io.cir.demoapp;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;


/**
 * SQLiteOpenHelperの継承クラス
 */
public class DemoAppSQLiteOpenHelper extends SQLiteOpenHelper{

    private static final String CREATE_TABLE_ITEM = "create table ITEM ("
            + "ID integer primary key autoincrement,"
            + "IMAGE_ID integer,"
            + "NAME varchar(255),"
            + "PRICE integer,"
            + "DESCRIPTION text"
            + " );";
    private static final String CREATE_TABLE_PURCHASE = "create table PURCHASE ("
            + "ID integer primary key autoincrement,"
            + "ITEM_ID integer,"
            + "QUANTITY integer,"
            + "PURCHASE_STATE varchar(255)"
            + " );";
    private static final String DROP_TABLE_ITEM = "drop table ITEM;";
    private static final String DROP_TABLE_PURCHASE = "drop table PURCHASE;";
    private static final String INITIAL_INSERT_ITEM = "insert into ITEM (IMAGE_ID, NAME, PRICE, DESCRIPTION) values"
            + "(" + R.drawable.dummy_image + ", '商品1', 1000, 'とてもいい商品です'),"
            + "(" + R.drawable.dummy_image + ", '商品2', 2000, 'とてもいい商品です'),"
            + "(" + R.drawable.dummy_image + ", '商品3', 3000, 'とてもいい商品です'),"
            + "(" + R.drawable.dummy_image + ", '商品4', 4000, 'とてもいい商品です'),"
            + "(" + R.drawable.dummy_image + ", '商品5', 5000, 'とてもいい商品です'),"
            + "(" + R.drawable.dummy_image + ", '商品6', 6000, 'とてもいい商品です'),"
            + "(" + R.drawable.dummy_image + ", '商品7', 7000, 'とてもいい商品です'),"
            + "(" + R.drawable.dummy_image + ", '商品8', 8000, 'とてもいい商品です'),"
            + "(" + R.drawable.dummy_image + ", '商品9', 9000, 'とてもいい商品です')"
            + ";";

    public DemoAppSQLiteOpenHelper(Context c) {
        super(c, Config.DB_NAME, null, Config.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ITEM);
        db.execSQL(INITIAL_INSERT_ITEM);
        db.execSQL(CREATE_TABLE_PURCHASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_ITEM);
        db.execSQL(DROP_TABLE_PURCHASE);
        onCreate(db);
    }

}
