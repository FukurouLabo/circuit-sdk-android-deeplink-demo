package io.cir.demoapp.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import io.cir.demoapp.R;
import io.cir.demoapp.dto.ItemListViewDto;

/**
 * 商品一覧表示画面用Adapter
 */
public class ItemListViewAdapter extends ArrayAdapter {
    private LayoutInflater layoutInflater_;

    public ItemListViewAdapter(Context context, int textViewResourceId, List<ItemListViewDto> objects) {
        super(context, textViewResourceId, objects);
        layoutInflater_ = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 特定の行(position)のデータを得る
        ItemListViewDto item = (ItemListViewDto)getItem(position);

        // convertViewは使い回しされている可能性があるのでnullの時だけ新しく作る
        if (null == convertView) {
            convertView = layoutInflater_.inflate(R.layout.item_list_view, null);
        }

        // ImageViewDataのデータをViewの各Widgetにセットする
        ImageView imageView;
        imageView = (ImageView)convertView.findViewById(R.id.image);
        imageView.setImageBitmap(item.getItemImage());

        TextView textView;
        textView = (TextView)convertView.findViewById(R.id.name);
        textView.setText(item.getItemName());

        TextView priceTextView;
        priceTextView = (TextView)convertView.findViewById(R.id.price);
        priceTextView.setText(item.getItemPrice());

        return convertView;
    }
}
