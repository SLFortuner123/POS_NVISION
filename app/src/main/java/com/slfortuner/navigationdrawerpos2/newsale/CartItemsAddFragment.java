package com.slfortuner.navigationdrawerpos2.newsale;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.slfortuner.navigationdrawerpos2.R;
import com.slfortuner.navigationdrawerpos2.summaryList.Summary;
import com.slfortuner.navigationdrawerpos2.models.ProductsModel;
import com.slfortuner.navigationdrawerpos2.database.DataBaseHelperSummary;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartItemsAddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartItemsAddFragment extends Fragment {

    ImageButton plusQuantity, minusQuantity, toCart1;
    Button addToCart;
    TextView quantityNum, itemTotalPrice, testRun;

    int quantity = 0;
    public Uri mCurrentCartUri;
    boolean hasAllRequiredValues;

    DataBaseHelperSummary myDB;

    private ProductsModel selectedProductsModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate( R.layout.fragment_cart_items_add, container, false );

        myDB = new DataBaseHelperSummary( getContext() );

        setPlusQuantity(view);
        setMinusQuantity(view);
//   git push -u -f origin main
        setAddtoCart( view );
        iniWidgets( view );
        checkForEditProducts( view );

//        Toolbar toolbar = view.findViewById( R.id.toolbar );
//        ((MainActivity) getActivity()).getSupportActionBar().hide();
//        ((MainActivity) getActivity()).setSupportActionBar( toolbar );


        plusQuantity = view.findViewById( R.id.addquantity1 );
        minusQuantity = view.findViewById( R.id.subquantity1 );

        quantityNum = view.findViewById( R.id.quantity1 );
        itemTotalPrice = view.findViewById( R.id.itemTotalPrice1 );
//        toCart1 = view.findViewById( R.id.cartt );
//        addToCart = view.findViewById( R.id.addItemToCart1 );


        return view;
    }

//    private void setToCart1(View view) {
//
//        super.onStart();
//        ImageButton toCart1 = (ImageButton) view.findViewById( R.id.cartt );
//        toCart1.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                getActivity().getSupportFragmentManager()
////                        .beginTransaction()
////                        .replace( R.id.frag1, new ProductListFragment() )
////                        .commit();
//
//                Intent intent = new Intent( getContext(), Summary.class );
//                startActivity( intent );
//            }
//        } );
//
//    }

    private void setAddtoCart(View view) {

        super.onStart();
        Button addToCart = (Button) view.findViewById( R.id.addItemToCart1 );
        addToCart.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = itemTotalPrice.getText().toString();
                String data2 = quantityNum.getText().toString();
                String data3 = testRun.getText().toString();
                Intent intent = new Intent( getContext(), Summary.class );
                startActivity( intent );
                if (quantityNum.length() != 0) {

                    AddData( data, data2, data3 );
                    itemTotalPrice.setText( "" );
                    quantityNum.setText( "" );
                    testRun.setText( "" );
                } else {
                    Toast.makeText( getContext(), "Could't add to cart", Toast.LENGTH_LONG ).show();
                }

                getActivity().finish();
            }
        } );

    }

    private void setPlusQuantity(View view) {

        super.onStart();
        ImageButton plusQuantity = (ImageButton) view.findViewById( R.id.addquantity1 );
        plusQuantity.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int basePrice = Integer.parseInt( String.valueOf( selectedProductsModel.getPrice() ) );
                quantity++;
                displayQuantity();
                int itemPrice = basePrice * quantity;
                String setnewPrice = String.valueOf( itemPrice );
                itemTotalPrice.setText( setnewPrice );
            }
        } );

    }


    private void setMinusQuantity(View view) {

        super.onStart();
        ImageButton minusQuantity = (ImageButton) view.findViewById( R.id.subquantity1 );
        minusQuantity.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int basePrice = Integer.parseInt( String.valueOf( selectedProductsModel.getPrice() ) );
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
    private void displayQuantity() {

        quantityNum.setText(String.valueOf( quantity ));

    }


    private void iniWidgets(View view) {
        testRun = view.findViewById( R.id.testrun );


    }

    private void checkForEditProducts(View view) {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            int id = getArguments().getInt( ProductsModel.PRODUCT_EDIT_EXTRA, -1 );
            selectedProductsModel = ProductsModel.getProductForID( id );

        }
//        Intent previousIntent = getIntent();
//
//        int passedProductID = previousIntent.getIntExtra( ProductsModel.PRODUCT_EDIT_EXTRA, -1 );
//        selectedProductsModel = ProductsModel.getProductForID( passedProductID );

        if (selectedProductsModel != null) {
            testRun.setText( selectedProductsModel.getName() );

        }

    }



    public void AddData(String firstName,String lastName, String favFood ){
        boolean insertData = myDB.addData(firstName,lastName,favFood);

        if(insertData==true){
            Toast.makeText(getContext(),"Successfully added to Cart!",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getContext(),"Something went wrong!.",Toast.LENGTH_LONG).show();
        }
    }
}