package com.slfortuner.navigationdrawerpos2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.slfortuner.navigationdrawerpos2.models.ProductsModel;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class ProductListDatabseHelper extends SQLiteOpenHelper {

    private static ProductListDatabseHelper sqLManager;

    private static final String DATABASE_NAME = "ProductDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Products";
    private static final String COUNTER = "Counter";

    private static final String ID_FIELD = "id";
    private static final String NAME_FIELD = "name";
    private static final String PRICE_FIELD = "price";
    private static final String CODE_FIELD = "code";
    private static final String DELETED_FIELD = "deleted";

    private static final DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");



    public ProductListDatabseHelper(Context context) {
        super( context, DATABASE_NAME, null, DATABASE_VERSION );
    }




    public static ProductListDatabseHelper instanceOfDatabase(Context context)
    {
        if (sqLManager == null)
            sqLManager = new ProductListDatabseHelper(context) ;

        return sqLManager;

    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        StringBuilder sql;
        sql = new StringBuilder()
                .append( " CREATE TABLE " )
                .append( TABLE_NAME )
                .append("(" )
                .append( COUNTER )
                .append( " INTEGER PRIMARY KEY AUTOINCREMENT, " )
                .append( ID_FIELD )
                .append( " INT, " )
                .append( NAME_FIELD )
                .append( " TEXT, " )
                .append( PRICE_FIELD )
                .append( " TEXT, " )
                .append( CODE_FIELD )
                .append( " TEXT, " )
                .append( DELETED_FIELD )
                .append( " TEXT)" );

        sqLiteDatabase.execSQL( sql.toString() );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

 //       switch (oldVersion)
 //       {
 //           case 1:
 //               sqLiteDatabase.execSQL( "ALTER TABLE" + TABLE_NAME + " ADD COLUMN " + NEW_COLUMN + "TEXT" );
//
  //          case 1:
 //               sqLiteDatabase.execSQL( "ALTER TABLE" + TABLE_NAME + " ADD COLUMN " + NEW_COLUMN + "TEXT" );
//
 //       }
    }

    public void addProductsToDatabase(ProductsModel productsModel)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_FIELD, productsModel.getId());
        contentValues.put(NAME_FIELD, productsModel.getName());
        contentValues.put(PRICE_FIELD, productsModel.getPrice());
        contentValues.put(CODE_FIELD, productsModel.getCode());
        contentValues.put(DELETED_FIELD, getStringsFromDate( productsModel.getDeleted()));

        sqLiteDatabase.insert( TABLE_NAME, null, contentValues );

    }

    public void populateProductListArray(){

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        try (Cursor result = sqLiteDatabase.rawQuery( "SELECT * FROM " + TABLE_NAME, null )) {
            if (result.getCount() != 0)
            {
                while (result.moveToNext())
                {
                    int id = result.getInt( 1 );
                    String name = result.getString( 2 );
                    String price = result.getString( 3 );
                    String code = result.getString( 4 );
                    String stringDeleted = result.getString( 5 );
                    Date deleted = getDateFromString( stringDeleted );
                    ProductsModel productsModel = new ProductsModel( id, name, price, code, deleted);
                    ProductsModel.productsModelArrayList.add( productsModel );



                }
            }
        }

    }

    public void updateProductInDB(ProductsModel productsModel)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_FIELD, productsModel.getId());
        contentValues.put(NAME_FIELD, productsModel.getName());
        contentValues.put(PRICE_FIELD, productsModel.getPrice());
        contentValues.put(CODE_FIELD, productsModel.getCode());
        contentValues.put(DELETED_FIELD, getStringsFromDate( productsModel.getDeleted()));


        sqLiteDatabase.update( TABLE_NAME, contentValues, ID_FIELD + " =?", new String[]{String.valueOf( productsModel.getId())});
    }

    private String getStringsFromDate(Date date)
    {
        if(date == null)
            return null;
        return  dateFormat.format( date );

    }

        private Date getDateFromString  (String string)
        {
            try
            {
                return dateFormat.parse( string );
            }
            catch (ParseException | NullPointerException e) {
                return null;
            }
        }
    {


    }

}
