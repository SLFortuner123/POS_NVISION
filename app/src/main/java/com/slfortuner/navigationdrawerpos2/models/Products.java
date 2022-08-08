package com.slfortuner.navigationdrawerpos2.models;

import java.util.ArrayList;
import java.util.Date;

public class Products {



    public static ArrayList<Products> productsArrayList = new ArrayList<>();
    public static String PRODUCT_EDIT_EXTRA = "productEdit";

    private int id;
    private String name;
    private String price;
    private String code;
    private Date deleted;

    public Products(int id, String name, String price, String code, Date deleted) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.code = code;
        this.deleted = deleted;
    }

    public Products(int id, String name, String price, String code) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.code = code;
        deleted = null;
    }

    public static Products getProductForID(int passedProductID)
    {
        for (Products products : productsArrayList )

        {
            if(products.getId() == passedProductID)
                return products;
        }
        return null;

    }

    public static ArrayList<Products> nonDeletedProducts()
    {
        ArrayList<Products> nonDeleted = new ArrayList<>();
        for (Products products : productsArrayList)
        {
            if(products.getDeleted() == null )
                nonDeleted.add(products);
        }

        return nonDeleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDeleted() {
        return deleted;
    }

    public void setDeleted(Date deleted) {
        this.deleted = deleted;
    }
}
