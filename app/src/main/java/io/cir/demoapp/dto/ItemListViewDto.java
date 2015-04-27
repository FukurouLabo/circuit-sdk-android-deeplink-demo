package io.cir.demoapp.dto;

import android.graphics.Bitmap;

/**
 * 商品一覧表示画面でのレコードを表すDTO
 */
public class ItemListViewDto {

    private Bitmap itemImage_;
    private String itemName_;
    private String itemPrice_;

    public void setItemImage (Bitmap itemImage) {
        itemImage_ = itemImage;
    }

    public void setItemName (String itemName) {
        itemName_ = itemName;
    }

    public void setItemPrice (String itemPrice) {
        itemPrice_ = itemPrice;
    }

    public Bitmap getItemImage () {
        return itemImage_;
    }

    public String getItemName () {
        return itemName_;
    }

    public String getItemPrice () {
        return itemPrice_;
    }
}
