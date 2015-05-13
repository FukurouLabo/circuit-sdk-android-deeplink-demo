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
            + "DESCRIPTION text);";
    private static final String CREATE_TABLE_PURCHASE = "create table PURCHASE ("
            + "ID integer primary key autoincrement,"
            + "ITEM_ID integer,"
            + "QUANTITY integer,"
            + "PURCHASE_STATE varchar(255));";
    private static final String DROP_TABLE_ITEM = "drop table ITEM;";
    private static final String DROP_TABLE_PURCHASE = "drop table PURCHASE;";
    private static final String INITIAL_INSERT_ITEM = "insert into ITEM (IMAGE_ID, NAME, PRICE, DESCRIPTION) values"
            + "(" + R.drawable.carrot + ", 'ニンジン', 100, '千葉県産。有機農法で作られたニンジンです。ニンジンはカロテン類が豊富であり、半本で1日のビタミンA必要量がとれます。生食、炒める、煮るなど、多くの方法で調理が可能です。'),"
            + "(" + R.drawable.potato + ", 'ジャガイモ', 50, '北海道産のメークイン。肉じゃがや粉吹き芋、ポテトサラダ、いももち、カレー、シチュー、グラタン、おでん、味噌汁などの具にも広くご利用いただけます。'),"
            + "(" + R.drawable.tomato + ", 'トマト', 200, '静岡県産。トマトには抗酸化作用を持つとされる成分リコピンが多量に含まれています。甘みが強く、サラダや焼きトマト、サルサ、ピザ、パスタ用ソース、スープなどにおすすめです。');";

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
