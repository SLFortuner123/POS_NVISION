package com.slfortuner.navigationdrawerpos2.abandoneds;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.slfortuner.navigationdrawerpos2.R;
import com.slfortuner.navigationdrawerpos2.models.ProductsModel;

public class EditProductsActivity extends AppCompatActivity {

    private EditText nameEditText, priceEditText, codeEditText;
    private Button deleteButton;
    private ProductsModel selectedProductsModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_edit_products );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar2 );
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
//        iniWidgets();

//        checkForEditProducts();

    }


//    private void iniWidgets() {
//        nameEditText = findViewById( R.id.nameEdit );
//        priceEditText = findViewById( R.id.priceEdit );
//        codeEditText = findViewById( R.id.codeEdit );
//        deleteButton = findViewById( R.id.deleteProduct );
//    }

//    private void checkForEditProducts() {
//        Intent previousIntent = getIntent();
//
//        int passedProductID = previousIntent.getIntExtra( ProductsModel.PRODUCT_EDIT_EXTRA, -1 );
//        selectedProductsModel = ProductsModel.getProductForID( passedProductID );
//
//        if (selectedProductsModel != null) {
//            nameEditText.setText( selectedProductsModel.getName() );
//            priceEditText.setText( selectedProductsModel.getPrice() );
//            codeEditText.setText( selectedProductsModel.getCode() );
//        }
//        else
//        {
//            deleteButton.setVisibility( View.INVISIBLE );
//        }
//    }

//
//    public void saveProduct(View view) {
//
//        ProductListDatabseHelper sqlManager = ProductListDatabseHelper.instanceOfDatabase( this );
//
//        String name = String.valueOf( nameEditText.getText() );
//        String price = String.valueOf( priceEditText.getText() );
//        String code = String.valueOf( codeEditText.getText() );
//
//        if (selectedProductsModel == null) {
//
//            int id = ProductsModel.productsModelArrayList.size();
//            ProductsModel newProducts = new ProductsModel( id, name, price, code );
//            ProductsModel.productsModelArrayList.add( newProducts );
//            sqlManager.addProductsToDatabase( newProducts );
//
//
//
//        } else {
//            selectedProductsModel.setName( name );
//            selectedProductsModel.setPrice( price );
//            selectedProductsModel.setCode( code );
//            sqlManager.updateProductInDB( selectedProductsModel );
//        }
//
//        finish();
//
//    }


//    public void deleteProduct(View view)
//    {
//        selectedProductsModel.setDeleted( new Date () );
//        ProductListDatabseHelper sqlManager = ProductListDatabseHelper.instanceOfDatabase( this );
//        sqlManager.updateProductInDB( selectedProductsModel );
//        finish();
//
//    }
}