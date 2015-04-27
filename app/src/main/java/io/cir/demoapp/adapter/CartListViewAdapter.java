package io.cir.demoapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import io.cir.demoapp.R;
import io.cir.demoapp.dto.CartListViewDto;

/**
 * Created by joytomo on 2015/04/24.
 */
public class CartListViewAdapter extends ArrayAdapter{
    private LayoutInflater layoutInflater_;

    public CartListViewAdapter(Context context, int textViewResourceId, List<CartListViewDto> objects) {
        super(context, textViewResourceId, objects);
        layoutInflater_ = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 特定の行(position)のデータを得る
        CartListViewDto cart = (CartListViewDto)getItem(position);

        // convertViewは使い回しされている可能性があるのでnullの時だけ新しく作る
        if (null == convertView) {
            convertView = layoutInflater_.inflate(R.layout.cart_list_view, null);
        }

        // ImageViewDataのデータをViewの各Widgetにセットする
        ImageView imageView;
        imageView = (ImageView)convertView.findViewById(R.id.image);
        imageView.setImageBitmap(cart.getItemImage());

        TextView nameTextView;
        nameTextView = (TextView)convertView.findViewById(R.id.name);
        nameTextView.setText(cart.getItemName());

        TextView purchaseQuantityTextView;
        purchaseQuantityTextView = (TextView)convertView.findViewById(R.id.purchase_quantity);
        purchaseQuantityTextView.setText(cart.getPurchaseQuantity());

        TextView totalAmountTextView;
        totalAmountTextView = (TextView)convertView.findViewById(R.id.total_amount);
        totalAmountTextView.setText(cart.getTotalAmount());

        return convertView;
    }
}
