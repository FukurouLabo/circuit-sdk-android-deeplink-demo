package io.cir.demoapp.dao;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import io.cir.demoapp.entity.ItemEntity;

/**
 * ItemエンティティのDAO
 */
public class ItemDao {

    private static final String TABLE_NAME = "ITEM";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_IMAGE_ID = "IMAGE_ID";
    private static final String COLUMN_NAME = "NAME";
    private static final String COLUMN_PRICE = "PRICE";
    private static final String COLUMN_DESCRIPTION = "DESCRIPTION";
    private static final String[] COLUMNS = {COLUMN_ID,COLUMN_IMAGE_ID,COLUMN_NAME,COLUMN_PRICE,COLUMN_DESCRIPTION};

    private SQLiteDatabase db;

    /**
     * コンストラクタ
     *
     * @param db
     */
    public ItemDao (SQLiteDatabase db) {
        this.db = db;
    }

    /**
     * 全てのItemを返却する
     *
     * @return
     */
    public List<ItemEntity> findAll() {
        List<ItemEntity> entityList = new  ArrayList<ItemEntity>();
        Cursor cursor = db.query(
                TABLE_NAME,
                COLUMNS,
                null,
                null,
                null,
                null,
                COLUMN_ID);

        while(cursor.moveToNext()) {
            ItemEntity entity = new ItemEntity();
            entity.setId(cursor.getInt(0));
            entity.setImageId(cursor.getInt(1));
            entity.setName(cursor.getString(2));
            entity.setPrice(cursor.getInt(3));
            entity.setDescription(cursor.getString(4));
            entityList.add(entity);
        }
        return entityList;
    }

    /**
     * IDに該当するItemを返却する
     *
     * @param itemId
     * @return
     */
    public ItemEntity findById(int itemId) {
        String selection = COLUMN_ID + "=" + itemId;
        Cursor cursor = db.query(
                TABLE_NAME,
                COLUMNS,
                selection,
                null,
                null,
                null,
                null);

        cursor.moveToNext();
        ItemEntity entity = new ItemEntity();
        entity.setId(cursor.getInt(0));
        entity.setImageId(cursor.getInt(1));
        entity.setName(cursor.getString(2));
        entity.setPrice(cursor.getInt(3));
        entity.setDescription(cursor.getString(4));

        return entity;
    }
}
