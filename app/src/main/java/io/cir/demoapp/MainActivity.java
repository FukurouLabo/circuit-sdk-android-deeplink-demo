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
import android.widget.Toast;

import io.cir.demoapp.adapter.ItemListViewAdapter;
import io.cir.demoapp.dao.ItemDao;
import io.cir.demoapp.dto.ItemListViewDto;
import io.cir.demoapp.entity.ItemEntity;

import io.cir.CircuitDirect;
import io.cir.CircuitDirectException;
import io.cir.DirectInfo;

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

        // ここからCircuit
        CircuitDirect.getDirectInfo(this, new CircuitDirect.GetLinkListener() {
            @Override
            public void onSuccess(DirectInfo directInfo) {
                // ここにディープリンク実行処理を記述します。
                // 以下のコードはディープリンクを御社独自実装あるいは他社SDKにて実装されている際の一例となります。
                try {
                    int item_id = Integer.parseInt(directInfo.getDirectLinkQueryStrings().get("item_id").toString());
                    Intent intent = new Intent(getApplicationContext(), ItemDetailActivity.class);
                    intent.putExtra("itemId", item_id);
                    startActivity(intent);
                } catch (Exception e){
                }
            }
            @Override
            public void onError(CircuitDirectException e) {
                Toast.makeText(MainActivity.this, "Failed to get direct info", Toast.LENGTH_SHORT).show();
            }
        });

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

    @Override
    protected void onPause() {
        super.onPause();
        // ここからCircuit
        CircuitDirect.onPause(this);
        // ここまでCircuit
    }
}
