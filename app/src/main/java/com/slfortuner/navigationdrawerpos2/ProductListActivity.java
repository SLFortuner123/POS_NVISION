package com.slfortuner.navigationdrawerpos2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.slfortuner.navigationdrawerpos2.adapters.ProductAdapter;
import com.slfortuner.navigationdrawerpos2.models.Products;

public class ProductListActivity extends AppCompatActivity {

    private ListView productListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_product_list );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar1 );
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        initWidgets();
        loadFromDBTomMemory();
        setProductAdapter();
        setOnClickListener();

    }

    private void loadFromDBTomMemory(){

        SQLManager sqlManager = SQLManager.instanceOfDatabase( this );
        sqlManager.populateProductListArray();

    }

    private void setProductAdapter()
    {
        ProductAdapter productAdapter = new ProductAdapter( getApplicationContext(), Products.nonDeletedProducts());
        productListView.setAdapter( productAdapter );

    }

    private void setOnClickListener() {

        productListView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                Products selectedProducts = (Products) productListView.getItemAtPosition( position );
                Intent editProductIntent = new Intent(getApplicationContext(),EditProductsActivity.class);
                editProductIntent.putExtra( Products.PRODUCT_EDIT_EXTRA, selectedProducts.getId() );
                startActivity( editProductIntent );

            }
        } );

    }


    private void initWidgets()
    {
        productListView = findViewById( R.id.listViewProducts );
    }

    public void newProduct(View view) {

        Intent newProductIntent = new Intent(this, EditProductsActivity.class);
        startActivity( newProductIntent );

    }

    @Override
    protected void onResume()
    {
        super.onResume();
        setProductAdapter();

    }
}