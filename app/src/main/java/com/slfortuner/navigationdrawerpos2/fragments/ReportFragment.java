package com.slfortuner.navigationdrawerpos2.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.slfortuner.navigationdrawerpos2.NewSale;
import com.slfortuner.navigationdrawerpos2.R;

import androidx.fragment.app.Fragment;

public class ReportFragment extends Fragment {

    Activity context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


       context = getActivity();

        return inflater.inflate( R.layout.fragment_reports, container, false );
    }

    public void onStart(){

        super.onStart();
        Button btn = (Button) context.findViewById(R.id.report_btn  );
        btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NewSale.class);
                startActivity( intent );
            }
        } );

    }


}