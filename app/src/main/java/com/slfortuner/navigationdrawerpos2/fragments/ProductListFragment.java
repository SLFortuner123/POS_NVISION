package com.slfortuner.navigationdrawerpos2.fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.slfortuner.navigationdrawerpos2.R;
import com.slfortuner.navigationdrawerpos2.SQLManager;
import com.slfortuner.navigationdrawerpos2.adapters.ProductAdapter;
import com.slfortuner.navigationdrawerpos2.models.Products;


public class ProductListFragment extends Fragment {

    private ListView productListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_product_list, container, false );

        Toolbar toolbar = (Toolbar) view.findViewById( R.id.toolbarp );
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled( false );

        initViews( view );
        loadFromDBTomMemory();
        setProductAdapter();
        setOnClickListener(view);
        return view;
    }

    private void initViews(View view) {

        productListView = view.findViewById( R.id.recyclerViewProducts );


    }

    private void loadFromDBTomMemory() {

        SQLManager sqlManager = SQLManager.instanceOfDatabase( getContext() );
        sqlManager.populateProductListArray();

    }

    private void setProductAdapter() {
        ProductAdapter productAdapter = new ProductAdapter( getContext(), Products.nonDeletedProducts() );
        productListView.setAdapter( productAdapter );

    }

    private void setOnClickListener(View view) {

        productListView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Products selectedProducts = (Products) productListView.getItemAtPosition( position );

                Bundle bundle = new Bundle();
                bundle.putInt( Products.PRODUCT_EDIT_EXTRA, selectedProducts.getId() );
                AddAndEditProductsFragment addAndEditProductsFragment = new AddAndEditProductsFragment();
                addAndEditProductsFragment.setArguments( bundle );
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace( R.id.editProductsframe, addAndEditProductsFragment )
                        .addToBackStack( null )
                        .commit();
//                Intent editProductIntent = new Intent( getContext(), AddAndEditProductsFragment.class );
//                editProductIntent.putExtra( Products.PRODUCT_EDIT_EXTRA, selectedProducts.getId() );
//
//                startActivity( editProductIntent );

            }
        } );

    }
}