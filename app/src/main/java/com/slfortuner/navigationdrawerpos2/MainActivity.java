package com.slfortuner.navigationdrawerpos2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.slfortuner.navigationdrawerpos2.fragments.ProductNavigationDrawerFragment;
import com.slfortuner.navigationdrawerpos2.newsale.NewSaleNavigationDrawer;
import com.slfortuner.navigationdrawerpos2.database.DataBaseHelperSummary;
import com.slfortuner.navigationdrawerpos2.summaryList.Summary;
import com.slfortuner.navigationdrawerpos2.models.SummaryModel;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private SummaryModel summaryModelSum;
    private DataBaseHelperSummary myDB1;
    private TextView sumText, cartBadgeTextViews, text11;
    int quantitySum = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        Toolbar toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayShowTitleEnabled( false );


        RelativeLayout framew = findViewById( R.id.summaryfrag );
        framew.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MainActivity.this, Summary.class );
                startActivity( intent );
            }
        } );


        drawer = findViewById( R.id.drawer_layout );
        NavigationView navigationView = findViewById( R.id.navigation_view );
        navigationView.setNavigationItemSelectedListener( this );

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle( this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close );

        drawer.addDrawerListener( toggle );
        toggle.syncState(); //this rotate the action bar toggle//

        if (savedInstanceState == null) {
//            Intent intent;
//            intent = new Intent(MainActivity.this, MainActivity2.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            startActivity(intent);
            getSupportFragmentManager().beginTransaction().replace( R.id.fragment_container,
                    new NewSaleNavigationDrawer() ).commit();
            navigationView.setCheckedItem( R.id.report_menu );
        }

//        cartBadgeTextViews = findViewById( R.id.newgg );
//        cartBadgeTextViews.setText( String.valueOf( myDB1.getCount() ) );

    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen( GravityCompat.START )) {
            drawer.closeDrawer( GravityCompat.START );
            //else close activity as usual
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.report_menu:
//                Intent intent;
//                intent = new Intent( MainActivity.this, MainActivity2.class );
//                intent.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
//                startActivity( intent );
//                break;
                getSupportFragmentManager().beginTransaction().replace( R.id.fragment_container,
                        new NewSaleNavigationDrawer() ).commit();
                break;

            case R.id.product_menu:
                getSupportFragmentManager().beginTransaction().replace( R.id.fragment_container,
                        new ProductNavigationDrawerFragment() ).commit();
                break;

        }

        drawer.closeDrawer( GravityCompat.START );

        return true;
    }



}