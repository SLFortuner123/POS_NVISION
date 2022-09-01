package com.slfortuner.navigationdrawerpos2.summaryList;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.slfortuner.navigationdrawerpos2.MainActivity2;
import com.slfortuner.navigationdrawerpos2.R;
import com.slfortuner.navigationdrawerpos2.adapters.SummaryThreeColumn_ListAdapter;
import com.slfortuner.navigationdrawerpos2.database.DataBaseHelperSummary;
import com.slfortuner.navigationdrawerpos2.models.SummaryModel;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Summary extends AppCompatActivity {

    private DataBaseHelperSummary myDB;
    private ArrayList<SummaryModel> summaryModelList;
    private ListView listView;
    private SummaryModel summaryModel;
    private Button bt;
    private TextView tx, tx2;
    private DataBaseHelperSummary dbMy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_summary );

        Toolbar toolbar = findViewById( R.id.toolbarsum );
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayShowTitleEnabled( false );

        tx = findViewById( R.id.totalsum );
        tx2 = findViewById( R.id.newgg );

        myDB = new DataBaseHelperSummary( this );

        summaryModelList = new ArrayList<>();
        Cursor data = myDB.getListContents();
        int numRows = data.getCount();
        if (numRows == 0) {
            Toast.makeText( Summary.this, "The Database is empty ", Toast.LENGTH_LONG ).show();
        } else {
            int i = 0;
            while (data.moveToNext()) {
                summaryModel = new SummaryModel( data.getString( 1 ), data.getString( 2 ), data.getString( 3 ) );
                summaryModelList.add( i, summaryModel );
                System.out.println( data.getString( 1 ) + " " + data.getString( 2 ) + " " + data.getString( 3 ) );
                System.out.println( summaryModelList.get( i ).getFirstName() );
                i++;
            }
            SummaryThreeColumn_ListAdapter adapter = new SummaryThreeColumn_ListAdapter( this, R.layout.list_adapter_view, summaryModelList );
            listView = (ListView) findViewById( R.id.list_summary );
            listView.setAdapter( adapter );
        }


//        bt = findViewById( R.id.bt );
//        bt.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                tx.setText( String.format( "total : %s", myDB.getSum() ) );
//
//            }
//        } );

        sumPrice();
        setOnClickListener();
//        sum1Price();


    }

    private void sumPrice() {
        tx.setText( String.format( "total : %s", myDB.getSum() ) );
    }

    private void setOnClickListener() {

        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                SummaryModel selectedProducts = (SummaryModel) listView.getItemAtPosition( position );

//                Bundle bundle = new Bundle();
//                bundle.putInt( ProductsModel.PRODUCT_EDIT_EXTRA, selectedProducts.getFirstName() );
//                AddAndEditProductsFragment addAndEditProductsFragment = new AddAndEditProductsFragment();
//                addAndEditProductsFragment.setArguments( bundle );
//                getActivity().getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace( R.id.editProductsframe, addAndEditProductsFragment )
//                        .addToBackStack( null )
//                        .commit();
                Intent editProductIntent = new Intent( Summary.this, MainActivity2.class );

                startActivity( editProductIntent );

            }
        } );
    }

}



//    private void sum1Price(){
//        tx2.setText( String.valueOf( myDB.getCount() ) );
//    }



