package com.slfortuner.navigationdrawerpos2.models;


import com.slfortuner.navigationdrawerpos2.summaryList.Summary;

import java.util.ArrayList;

public class SummaryModel {

    private String FirstName;
    private String LastName;
    private String FavFood;



    public SummaryModel(String fName, String lName, String fFood){

        FirstName = fName;
        LastName = lName;
        FavFood = fFood;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getFavFood() {
        return FavFood;
    }

    public void setFavFood(String favFood) {
        FavFood = favFood;
    }
}
