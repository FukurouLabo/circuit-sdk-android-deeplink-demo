package io.cir.demoapp;

import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import io.cir.demoapp.dao.ItemDao;
import io.cir.demoapp.dao.PurchaseDao;
import io.cir.demoapp.entity.ItemEntity;
import io.cir.demoapp.entity.PurchaseEntity;

/**
 * 商品個別画面用Activity
 */
public class ItemDetailActivity extends ActionBarActivity {

    private ItemDao itemDao;
    private PurchaseDao purchaseDao;
    private ItemEntity item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        DemoAppSQLiteOpenHelper helper = new DemoAppSQLiteOpenHelper(getApplicationContext());
        itemDao = new ItemDao(helper.getWritableDatabase());
        purchaseDao = new PurchaseDao(helper.getWritableDatabase());
        Intent intent = getIntent();
        /**
         * Circuit SDK導入時の編集箇所 - ここから
         *
         * intent.getStringExtraでURLスキームに付与したパラメータの値を取得することができる
         */
        String deepLinkItemId = intent.getStringExtra("deepLinkItemId");
        if  (deepLinkItemId == null) {
            item = itemDao.findById(intent.getIntExtra("itemId", 1));
        } else {
            item = itemDao.findById(Integer.parseInt(deepLinkItemId));
        }
        /**
         * Circuit SDK導入時の編集箇所 - ここまで
         */

        ImageView itemImageImageView = (ImageView) findViewById(R.id.item_image);
        itemImageImageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), item.getImageId()));
        TextView itemNameTextView = (TextView) findViewById(R.id.item_name);
        itemNameTextView.setText(item.getName());
        TextView itemPriceTextView = (TextView) findViewById(R.id.item_price);
        itemPriceTextView.setText(String.valueOf(item.getPrice()) + "円");
        TextView itemDescriptionTextView = (TextView) findViewById(R.id.item_description);
        itemDescriptionTextView.setText(item.getDescription());
    }

    /**
     * カートにアイテムを追加し、カート画面に遷移する
     *
     * @param view
     */
    public void addCart(View view) {
        // カートにアイテムを追加する処理。すでに同じものがカートに入っていれば個数を+1する
        List<PurchaseEntity> purchaseEntities = purchaseDao.findAll();
        boolean firstAddItem = true;
        for (PurchaseEntity purchaseEntity:purchaseEntities) {
            if (purchaseEntity.getItemId() == item.getId() && purchaseEntity.getPurchaseState() == PurchaseEntity.PURCHASE_STATE.IN_CART) {
                purchaseEntity.setQuantity(purchaseEntity.getQuantity() + 1);
                purchaseDao.update(purchaseEntity);
                firstAddItem = false;
                break;
            }
        }
        if (firstAddItem) {
            purchaseDao.cratePurchase(item.getId());
        }
        // カート画面に遷移
        Intent intent = new Intent(getApplicationContext(), CartActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_item_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
