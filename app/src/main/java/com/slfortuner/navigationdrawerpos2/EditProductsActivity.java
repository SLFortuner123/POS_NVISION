package com.slfortuner.navigationdrawerpos2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.slfortuner.navigationdrawerpos2.models.Products;

import java.util.Date;

public class EditProductsActivity extends AppCompatActivity {

    private EditText nameEditText, priceEditText, codeEditText;
    private Button deleteButton;
    private Products selectedProducts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_edit_products );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar2 );
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        iniWidgets();

        checkForEditProducts();

    }


    private void iniWidgets() {
        nameEditText = findViewById( R.id.nameEdit );
        priceEditText = findViewById( R.id.priceEdit );
        codeEditText = findViewById( R.id.codeEdit );
        deleteButton = findViewById( R.id.deleteProduct );
    }

    private void checkForEditProducts() {
        Intent previousIntent = getIntent();

        int passedProductID = previousIntent.getIntExtra( Products.PRODUCT_EDIT_EXTRA, -1 );
        selectedProducts = Products.getProductForID( passedProductID );

        if (selectedProducts != null) {
            nameEditText.setText( selectedProducts.getName() );
            priceEditText.setText( selectedProducts.getPrice() );
            codeEditText.setText( selectedProducts.getCode() );
        }
        else
        {
            deleteButton.setVisibility( View.INVISIBLE );
        }
    }


    public void saveProduct(View view) {

        SQLManager sqlManager = SQLManager.instanceOfDatabase( this );

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

        finish();

    }


    public void deleteProduct(View view)
    {
        selectedProducts.setDeleted( new Date () );
        SQLManager sqlManager = SQLManager.instanceOfDatabase( this );
        sqlManager.updateProductInDB( selectedProducts );
        finish();

    }
}