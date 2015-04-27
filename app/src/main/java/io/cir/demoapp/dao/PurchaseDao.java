package io.cir.demoapp.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import io.cir.demoapp.entity.PurchaseEntity;

/**
 * PurchaseエンティティのDao
 */
public class PurchaseDao {

    private static final String TABLE_NAME = "PURCHASE";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_ITEM_ID = "ITEM_ID";
    private static final String COLUMN_QUANTITY = "QUANTITY";
    private static final String COLUMN_PURCHASE_STATE = "PURCHASE_STATE";
    private static final String[] COLUMNS = {COLUMN_ID, COLUMN_ITEM_ID, COLUMN_QUANTITY, COLUMN_PURCHASE_STATE };

    private SQLiteDatabase db;

    /**
     * コンストラクタ
     *
     * @param db
     */
    public PurchaseDao (SQLiteDatabase db) {
        this.db = db;
    }

    /**
     * 全てのPurchaseを返却する
     *
     * @return
     */
    public List<PurchaseEntity> findAll() {
        List<PurchaseEntity> entityList = new  ArrayList<PurchaseEntity>();
        Cursor cursor = db.query(
                TABLE_NAME,
                COLUMNS,
                null,
                null,
                null,
                null,
                COLUMN_ID);

        while(cursor.moveToNext()) {
            PurchaseEntity entity = new PurchaseEntity();
            entity.setId(cursor.getInt(0));
            entity.setItemId(cursor.getInt(1));
            entity.setQuantity(cursor.getInt(2));
            entity.setPurchaseState(PurchaseEntity.PURCHASE_STATE.valueOf(cursor.getString(3)));

            entityList.add(entity);
        }
        return entityList;
    }

    /**
     * IDに該当するPurchaseを返却する
     *
     * @param purchaseId
     * @return
     */
    public PurchaseEntity findById(int purchaseId) {
        String selection = COLUMN_ID + "=" + purchaseId;
        Cursor cursor = db.query(
                TABLE_NAME,
                COLUMNS,
                selection,
                null,
                null,
                null,
                null);

        cursor.moveToNext();
        PurchaseEntity entity = new PurchaseEntity();
        entity.setId(cursor.getInt(0));
        entity.setItemId(cursor.getInt(1));
        entity.setQuantity(cursor.getInt(2));
        entity.setPurchaseState(PurchaseEntity.PURCHASE_STATE.valueOf(cursor.getString(3)));

        return entity;
    }

    /**
     * 新たなPurchaseをDB上に作成しエンティティを返却する
     *
     * @param item_id
     * @return
     */
    public PurchaseEntity cratePurchase(int item_id) {
        PurchaseEntity purchaseEntity = new PurchaseEntity();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ITEM_ID, item_id);
        cv.put(COLUMN_QUANTITY, 1);
        cv.put(COLUMN_PURCHASE_STATE, PurchaseEntity.PURCHASE_STATE.IN_CART.name());
        int id = (int)db.insert(TABLE_NAME, null, cv);
        purchaseEntity.setId(id);
        purchaseEntity.setItemId(item_id);
        purchaseEntity.setQuantity(1);
        purchaseEntity.setPurchaseState(PurchaseEntity.PURCHASE_STATE.IN_CART);
        return purchaseEntity;
    }

    /**
     * PurchaseをエンティティからDB上に更新する
     *
     * @param purchaseEntity
     */
    public void update(PurchaseEntity purchaseEntity) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ITEM_ID, purchaseEntity.getItemId());
        cv.put(COLUMN_QUANTITY, purchaseEntity.getQuantity());
        cv.put(COLUMN_PURCHASE_STATE, purchaseEntity.getPurchaseState().name());
        db.update(TABLE_NAME, cv, COLUMN_ID + " = " + purchaseEntity.getId(), null);
    }

}
