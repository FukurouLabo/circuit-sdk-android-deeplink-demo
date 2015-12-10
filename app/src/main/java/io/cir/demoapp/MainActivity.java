package io.cir.demoapp;

import java.util.List;
import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.content.Intent;

import io.cir.demoapp.adapter.ItemListViewAdapter;
import io.cir.demoapp.dao.ItemDao;
import io.cir.demoapp.dto.ItemListViewDto;
import io.cir.demoapp.entity.ItemEntity;
import io.cir.x.ButtonCreativeView;

/**
 * トップ画面 = アイテム一覧表示画面用Activity
 */
public class MainActivity extends ActionBarActivity {

    private ListView listView = null;
    private List<ItemListViewDto> itemListViewDtos = new ArrayList<ItemListViewDto>();
    private ItemDao itemDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButtonCreativeView button = (ButtonCreativeView) findViewById(android.R.id.button1);
        button.loadCreative("xta33987");

        DemoAppSQLiteOpenHelper helper = new DemoAppSQLiteOpenHelper(getApplicationContext());
        itemDao = new ItemDao(helper.getWritableDatabase());

        // 画面表示用DTOを生成
        final List<ItemEntity> itemEntities = itemDao.findAll();
        for (ItemEntity itemEntity: itemEntities) {
            ItemListViewDto itemListViewDto = new ItemListViewDto();
            itemListViewDto.setItemImage(BitmapFactory.decodeResource(getResources(), itemEntity.getImageId()));
            itemListViewDto.setItemName(itemEntity.getName());
            itemListViewDto.setItemPrice(String.valueOf(itemEntity.getPrice()) + "円");
            itemListViewDtos.add(itemListViewDto);
        }

        // Adapterを用いてDTOからListViewの設定をする
        ItemListViewAdapter itemListViewAdapter = new ItemListViewAdapter(this, 0, itemListViewDtos);
        ListView listView = (ListView)findViewById(R.id.itemListView);
        listView.setAdapter(itemListViewAdapter);

        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView < ? > parent, View v,int position, long id){
                Intent intent = new Intent(getApplicationContext(), ItemDetailActivity.class);
                intent.putExtra("itemId", itemEntities.get(position).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
