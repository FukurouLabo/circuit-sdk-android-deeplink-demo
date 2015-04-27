package io.cir.demoapp.entity;

/**
 * Created by joytomo on 2015/04/24.
 */
public class PurchaseEntity {
    private int id;
    private int itemId;
    private int quantity;
    private String purchaseState;

    public static enum PURCHASE_STATE{
        IN_CART,
        BOUGHT,
        SHIPPED;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPurchaseState(PURCHASE_STATE purchaseState) {
        this.purchaseState = purchaseState.name();
    }

    public int getId() {
        return this.id;
    }

    public int getItemId() {
        return this.itemId;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public PURCHASE_STATE getPurchaseState() {
        return PURCHASE_STATE.valueOf(this.purchaseState);
    }
}
