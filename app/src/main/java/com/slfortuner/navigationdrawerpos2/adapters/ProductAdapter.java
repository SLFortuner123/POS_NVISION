package com.slfortuner.navigationdrawerpos2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.slfortuner.navigationdrawerpos2.R;
import com.slfortuner.navigationdrawerpos2.models.ProductsModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ProductAdapter extends ArrayAdapter<ProductsModel> {

    public ProductAdapter(Context context, List<ProductsModel> products)
    {
        super(context,0, products);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ProductsModel productsModel = getItem( position );
        if (convertView == null)
            convertView = LayoutInflater.from( getContext()).inflate( R.layout.product_cell, parent, false);

        TextView name = convertView.findViewById( R.id.cellName);
        TextView price = convertView.findViewById( R.id.cellPrice);
        TextView code = convertView.findViewById( R.id.cellCode);


        name.setText( productsModel.getName() );
        price.setText( productsModel.getPrice() );
        code.setText( productsModel.getCode() );

        return convertView;
    }


}
