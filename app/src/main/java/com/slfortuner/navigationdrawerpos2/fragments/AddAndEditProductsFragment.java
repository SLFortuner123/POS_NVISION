package com.slfortuner.navigationdrawerpos2.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.slfortuner.navigationdrawerpos2.R;
import com.slfortuner.navigationdrawerpos2.SQLManager;
import com.slfortuner.navigationdrawerpos2.models.Products;

import java.util.Date;


public class AddAndEditProductsFragment extends Fragment {

    private EditText nameEditText, priceEditText, codeEditText;
    private Button deleteButton;
    private Products selectedProducts;
    private Toolbar toolbar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_add_and_edit_products, container, false );

        Toolbar toolbar = (Toolbar) view.findViewById( R.id.toolbar2 );
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled( false );



        iniWidgets( view );
        onStart( view );
        onSave( view );
        checkForEditProducts( view );


        return view;
    }



    public void onStart(View view) {

        super.onStart();
        Button btns = (Button) view.findViewById( R.id.deleteBT );
        btns.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedProducts.setDeleted( new Date() );
                SQLManager sqlManager = SQLManager.instanceOfDatabase( getContext() );
                sqlManager.updateProductInDB( selectedProducts );
                getActivity().finish();

//                getActivity().getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace( R.id.frag1, new ProductListFragment() )
//                        .commit();
//
//                Intent intent = new Intent(context, ProductListActivity.class);
//                startActivity( intent );
            }
        } );

    }

    public void onSave(View view) {

        super.onStart();
        Button btn = (Button) view.findViewById( R.id.saveBT );
        btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLManager sqlManager = SQLManager.instanceOfDatabase( getContext() );

                String name = String.valueOf( nameEditText.getText() );
                String price = String.valueOf( priceEditText.getText() );
                String code = String.valueOf( codeEditText.getText() );

                if (selectedProducts == null) {

                    int id = Products.productsArrayList.size();
                    Products newProducts = new Products( id, name, price, code );
                    Products.productsArrayList.add( newProducts );
                    sqlManager.addProductsToDatabase( newProducts );


                } else {
                    selectedProducts.setName( name );
                    selectedProducts.setPrice( price );
                    selectedProducts.setCode( code );
                    sqlManager.updateProductInDB( selectedProducts );
                }


                getActivity().finish();

            }
        } );

    }


    private void iniWidgets(View view) {
        nameEditText = view.findViewById( R.id.nameEdit );
        priceEditText = view.findViewById( R.id.priceEdit );
        codeEditText = view.findViewById( R.id.codeEdit );
        deleteButton = view.findViewById( R.id.deleteBT );
    }

    private void checkForEditProducts(View view) {
//        Intent previousIntent = getActivity().getIntent();
//        int passedProductID = previousIntent.getIntExtra( Products.PRODUCT_EDIT_EXTRA, -1 );
//        selectedProducts = Products.getProductForID( previousIntent );
        Bundle bundle = this.getArguments();
       if (bundle != null) {
           int id = getArguments().getInt( Products.PRODUCT_EDIT_EXTRA, -1 );

//        int id = getArguments().getInt( Products.PRODUCT_EDIT_EXTRA, -1 );
           selectedProducts = Products.getProductForID( id );

       }
        if (selectedProducts != null) {
            nameEditText.setText( selectedProducts.getName() );
            priceEditText.setText( selectedProducts.getPrice() );
            codeEditText.setText( selectedProducts.getCode() );
        } else {
            deleteButton.setVisibility( View.INVISIBLE );
        }
    }


    public void saveProduct(View view) {

        SQLManager sqlManager = SQLManager.instanceOfDatabase( getContext() );

        String name = String.valueOf( nameEditText.getText() );
        String price = String.valueOf( priceEditText.getText() );
        String code = String.valueOf( codeEditText.getText() );

        if (selectedProducts == null) {

            int id = Products.productsArrayList.size();
            Products newProducts = new Products( id, name, price, code );
            Products.productsArrayList.add( newProducts );
            sqlManager.addProductsToDatabase( newProducts );


        } else {
            selectedProducts.setName( name );
            selectedProducts.setPrice( price );
            selectedProducts.setCode( code );
            sqlManager.updateProductInDB( selectedProducts );
        }

        getActivity().finish();

    }


    public void deleteProduct(View view) {
        selectedProducts.setDeleted( new Date() );
        SQLManager sqlManager = SQLManager.instanceOfDatabase( getContext() );
        sqlManager.updateProductInDB( selectedProducts );
        getActivity().finish();
    }

}