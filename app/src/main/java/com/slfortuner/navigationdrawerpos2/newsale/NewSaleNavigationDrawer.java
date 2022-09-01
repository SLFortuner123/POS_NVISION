package com.slfortuner.navigationdrawerpos2.newsale;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.slfortuner.navigationdrawerpos2.database.ProductListDatabseHelper;
import com.slfortuner.navigationdrawerpos2.adapters.ProductAdapter;
import com.slfortuner.navigationdrawerpos2.models.ProductsModel;
import com.slfortuner.navigationdrawerpos2.R;

import androidx.fragment.app.Fragment;

public class NewSaleNavigationDrawer extends Fragment {

    private ListView productListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate( R.layout.fragment_reports, container, false );
        initViews( view );
        loadFromDBTomMemory();
        setProductAdapter();
        setOnClickListener1(view);
        return view;
    }


    private void initViews(View view) {

        productListView = view.findViewById( R.id.recyclerViewProducts1 );


    }

    private void loadFromDBTomMemory() {

        ProductListDatabseHelper productListDatabseHelper = ProductListDatabseHelper.instanceOfDatabase( getContext() );

        productListDatabseHelper.populateProductListArray();

    }

    private void setProductAdapter() {
        ProductAdapter productAdapter = new ProductAdapter( getContext(), ProductsModel.nonDeletedProducts() );
        productListView.setAdapter( productAdapter );

    }

    private void setOnClickListener1(View view) {

        productListView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                ProductsModel selectedProductsModel = (ProductsModel) productListView.getItemAtPosition( position );

                Bundle bundle = new Bundle();
                bundle.putInt( ProductsModel.PRODUCT_EDIT_EXTRA, selectedProductsModel.getId() );
                CartItemsAddFragment cardItemFragment = new CartItemsAddFragment();
                cardItemFragment.setArguments( bundle );
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace( R.id.fragment_container, cardItemFragment )
                        .addToBackStack( null )
                        .commit();
//                Intent editProductIntent = new Intent( getContext(), AddAndEditProductsFragment.class );
//                editProductIntent.putExtra( ProductsModel.PRODUCT_EDIT_EXTRA, selectedProductsModel.getId() );
//
//                startActivity( editProductIntent );

            }
        } );

    }

//    public void onStart(){
//
//        super.onStart();
//        Button btn = (Button) context.findViewById(R.id.report_btn  );
//        btn.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, NewSale.class);
//                startActivity( intent );
//            }
//        } );
//
//    }


}