package io.cir.demoapp.dto;

import android.graphics.Bitmap;

/**
 * カート画面表示用DTO
 */
public class CartListViewDto {

    private Bitmap itemImage_;
    private String itemName_;
    private String purchaseQuantity_;
    private String totalAmount_;

    public void setItemImage (Bitmap itemImage) {
        this.itemImage_ = itemImage;
    }

    public void setItemName (String itemName) {
        this.itemName_ = itemName;
    }

    public void setPurchaseQuantity (String purchaseQuantity) {
        this.purchaseQuantity_ = purchaseQuantity;
    }

    public void setTotalAmount (String totalAmount) {
        this.totalAmount_ = totalAmount;
    }

    public Bitmap getItemImage () {
        return this.itemImage_;
    }

    public String getItemName () {
        return this.itemName_;
    }

    public String getPurchaseQuantity () {
        return this.purchaseQuantity_;
    }

    public String getTotalAmount () {
        return this.totalAmount_;
    }
}
