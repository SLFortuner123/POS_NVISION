package com.slfortuner.navigationdrawerpos2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.slfortuner.navigationdrawerpos2.R;
import com.slfortuner.navigationdrawerpos2.models.SummaryModel;

import java.util.ArrayList;

public class SummaryThreeColumn_ListAdapter extends ArrayAdapter<SummaryModel> {

    private LayoutInflater mInflater;
    private ArrayList<SummaryModel> summaryModels;
    private int mViewResourceId;

    public SummaryThreeColumn_ListAdapter(Context context, int textViewResourceId, ArrayList<SummaryModel> summaryModels) {
        super(context, textViewResourceId, summaryModels );
        this.summaryModels = summaryModels;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mViewResourceId, null);

        SummaryModel summaryModel = summaryModels.get(position);

        if (summaryModel != null) {
            TextView firstName = (TextView) convertView.findViewById( R.id.textFirstName );
            TextView lastName = (TextView) convertView.findViewById(R.id.textLastName);
            TextView favFood = (TextView) convertView.findViewById(R.id.textFavFood);
            if (firstName != null) {
                firstName.setText( summaryModel.getFirstName());
            }
            if (lastName != null) {
                lastName.setText((summaryModel.getLastName()));
            }
            if (favFood != null) {
                favFood.setText((summaryModel.getFavFood()));
            }
        }

        return convertView;
    }
}