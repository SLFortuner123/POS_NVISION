package com.slfortuner.navigationdrawerpos2;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Display;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.slfortuner.navigationdrawerpos2.summaryList.DataBaseHelperSummary;
import com.slfortuner.navigationdrawerpos2.summaryList.ThreeColumn_ListAdapter;
import com.slfortuner.navigationdrawerpos2.summaryList.User;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class Summary extends AppCompatActivity {

    DataBaseHelperSummary myDB;
    ArrayList<User> userList;
    ListView listView;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_summary );

        myDB = new DataBaseHelperSummary( this );

        userList = new ArrayList<>();
        Cursor data = myDB.getListContents();
        int numRows = data.getCount();
        if (numRows == 0) {
            Toast.makeText(Summary.this,"The Database is empty ", Toast.LENGTH_LONG).show();
        } else {
            int i = 0;
            while (data.moveToNext()) {
                user = new User( data.getString( 1 ), data.getString( 2 ), data.getString( 3 ) );
                userList.add( i, user );
                System.out.println( data.getString( 1 ) + " " + data.getString( 2 ) + " " + data.getString( 3 ) );
                System.out.println( userList.get( i ).getFirstName() );
                i++;
            }
            ThreeColumn_ListAdapter adapter = new ThreeColumn_ListAdapter( this, R.layout.list_adapter_view, userList );
            listView = (ListView) findViewById( R.id.list_summary );
            listView.setAdapter( adapter );
        }

    }


}
