package com.slfortuner.navigationdrawerpos2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.slfortuner.navigationdrawerpos2.models.Products;
import com.slfortuner.navigationdrawerpos2.summaryList.DataBaseHelperSummary;

import org.w3c.dom.Text;

public class CartAddItems extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    ImageButton plusQuantity, minusQuantity, toCart1;
    Button addToCart;
    TextView quantityNum, itemTotalPrice, testRun;

    int quantity = 0;
    public Uri mCurrentCartUri;
    boolean hasAllRequiredValues;

    DataBaseHelperSummary myDB;

    private Products selectedProducts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_cart_add_items );

        myDB = new DataBaseHelperSummary( this );

        iniWidgets();
        checkForEditProducts();

        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar2 );
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );




        plusQuantity = findViewById( R.id.addquantity );
        minusQuantity = findViewById( R.id.subquantity );
        addToCart = findViewById( R.id.addItemToCart );
        quantityNum = findViewById( R.id.quantity );
        itemTotalPrice = findViewById( R.id.itemTotalPrice );
        toCart1 = findViewById( R.id.cart1);

 
///
      //   testRun = findViewById( R.id.testrun );
      //   testRun.setText( getIntent().getExtras().getString( "name" ) );


        toCart1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(CartAddItems.this, Summary.class);
                startActivity( intent );

            }
        } );

        addToCart.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = itemTotalPrice.getText().toString();
                String data2 = quantityNum.getText().toString();
                String data3 = testRun.getText().toString();
                Intent intent = new Intent(CartAddItems.this, Summary.class );
                startActivity( intent);
                if (quantityNum.length() !=0 ){

                    AddData(data, data2, data3 );
                    itemTotalPrice.setText("" );
                    quantityNum.setText( "" );
                    testRun.setText( "" );
                }
                else {
                    Toast.makeText( CartAddItems.this, "Could't add to cart", Toast.LENGTH_LONG).show();
                }

                finish();
            }
        } );





        plusQuantity.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ///////////itemTotalPrice.setText(String.valueOf(selectedProducts.getPrice()));
                int basePrice = Integer.parseInt( String.valueOf( selectedProducts.getPrice() ) );
                quantity++;
                displayQuantity();
                int itemPrice = basePrice * quantity;
                String setnewPrice = String.valueOf( itemPrice );
                itemTotalPrice.setText( setnewPrice );

            }
        } );


        minusQuantity.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int basePrice = Integer.parseInt( String.valueOf( selectedProducts.getPrice() ) );
                if (quantity == 0) {
//// make a toast if u wanted
                }else{
                    quantity--;
                    displayQuantity();
                    int itemPrice = basePrice * quantity;
                    String setnewPrice = String.valueOf( itemPrice );
                    itemTotalPrice.setText( setnewPrice );

                }
            }
        } );


    }

    private void iniWidgets() {
        testRun = findViewById( R.id.testrun );


    }

    private void checkForEditProducts() {
        Intent previousIntent = getIntent();

        int passedProductID = previousIntent.getIntExtra( Products.PRODUCT_EDIT_EXTRA, -1 );
        selectedProducts = Products.getProductForID( passedProductID );

        if (selectedProducts != null) {
            testRun.setText( selectedProducts.getName() );

        }

    }


    private void displayQuantity() {

        quantityNum.setText(String.valueOf( quantity ));

    }


    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
//////////////////////////summary//////////////////////

    public void AddData(String firstName,String lastName, String favFood ){
        boolean insertData = myDB.addData(firstName,lastName,favFood);

        if(insertData==true){
            Toast.makeText(CartAddItems.this,"Successfully added to Cart!",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(CartAddItems.this,"Something went wrong!.",Toast.LENGTH_LONG).show();
        }
    }
}