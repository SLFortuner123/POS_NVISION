package com.slfortuner.navigationdrawerpos2.models;

import java.util.ArrayList;
import java.util.Date;

public class ProductsModel {



    public static ArrayList<ProductsModel> productsModelArrayList = new ArrayList<>();
    public static String PRODUCT_EDIT_EXTRA = "productEdit";

    private int id;
    private String name;
    private String price;
    private String code;
    private Date deleted;

    public ProductsModel(int id, String name, String price, String code, Date deleted) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.code = code;
        this.deleted = deleted;
    }

    public ProductsModel(int id, String name, String price, String code) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.code = code;
        deleted = null;
    }

    public static ProductsModel getProductForID(int passedProductID)
    {
        for (ProductsModel productsModel : productsModelArrayList)

        {
            if(productsModel.getId() == passedProductID)
                return productsModel;
        }
        return null;

    }

    public static ArrayList<ProductsModel> nonDeletedProducts()
    {
        ArrayList<ProductsModel> nonDeleted = new ArrayList<>();
        for (ProductsModel productsModel : productsModelArrayList)
        {
            if(productsModel.getDeleted() == null )
                nonDeleted.add( productsModel );
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
