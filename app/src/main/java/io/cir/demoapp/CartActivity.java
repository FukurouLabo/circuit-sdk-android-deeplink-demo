package io.cir.demoapp;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.List;
import java.util.ArrayList;

import io.cir.demoapp.adapter.CartListViewAdapter;
import io.cir.demoapp.dao.ItemDao;
import io.cir.demoapp.dao.PurchaseDao;
import io.cir.demoapp.dto.CartListViewDto;
import io.cir.demoapp.entity.ItemEntity;
import io.cir.demoapp.entity.PurchaseEntity;

/**
 * カート画面用Activity
 */
public class CartActivity extends ActionBarActivity {

    private ListView listView = null;
    private List<CartListViewDto> cartListViewDtos = new ArrayList<CartListViewDto>();
    private ItemDao itemDao;
    private PurchaseDao purchaseDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        DemoAppSQLiteOpenHelper helper = new DemoAppSQLiteOpenHelper(getApplicationContext());
        itemDao = new ItemDao(helper.getWritableDatabase());
        purchaseDao = new PurchaseDao(helper.getWritableDatabase());

        // 画面表示用のDTOを生成する
        final List<PurchaseEntity> purchaseEntities = purchaseDao.findAll();
        for (PurchaseEntity purchaseEntity: purchaseEntities) {
            if (purchaseEntity.getPurchaseState() != PurchaseEntity.PURCHASE_STATE.IN_CART){
                continue;
            }
            CartListViewDto cartListViewDto = new CartListViewDto();
            ItemEntity itemEntity = itemDao.findById(purchaseEntity.getItemId());
            cartListViewDto.setItemImage(BitmapFactory.decodeResource(getResources(), itemEntity.getImageId()));
            cartListViewDto.setItemName(itemEntity.getName());
            cartListViewDto.setPurchaseQuantity(String.valueOf(purchaseEntity.getQuantity()) + "個");
            cartListViewDto.setTotalAmount(String.valueOf(itemEntity.getPrice() * purchaseEntity.getQuantity()) + "円");
            cartListViewDtos.add(cartListViewDto);
        }

        // Adapterを用いでListViewの設定をする
        CartListViewAdapter cartListViewAdapter = new CartListViewAdapter(this, 0, cartListViewDtos);
        ListView listView = (ListView)findViewById(R.id.cartListView);
        listView.setAdapter(cartListViewAdapter);

    }

    /**
     * カートに入っているItemを全て購入する
     *
     * @param view
     */
    public void buyItems (View view) {
        // カートに入っているPurchaseを購入済みステータスにする
        List<PurchaseEntity> purchaseEntities = purchaseDao.findAll();
        for (PurchaseEntity purchaseEntity: purchaseEntities) {
            if (purchaseEntity.getPurchaseState() != PurchaseEntity.PURCHASE_STATE.IN_CART) {
                continue;
            }
            purchaseEntity.setPurchaseState(PurchaseEntity.PURCHASE_STATE.BOUGHT);
            purchaseDao.update(purchaseEntity);
        }

        Intent intent = new Intent(getApplicationContext(), thanksActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cart, menu);
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
