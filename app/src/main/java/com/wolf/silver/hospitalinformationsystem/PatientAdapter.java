package com.wolf.silver.hospitalinformationsystem;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sarabesh on 11/21/2017.
 */

public class PatientAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Patient> PItems;

    public PatientAdapter(Activity activity, List<Patient> Items) {
        this.activity = activity;
        this.PItems = Items;
    }

    @Override
    public int getCount() {
        return PItems.size();
    }

    @Override
    public Object getItem(int i) {
        return PItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null)
            view = inflater.inflate(R.layout.patient_list_layout, null);

        TextView name=view.findViewById(R.id.nam);
        TextView age=view.findViewById(R.id.ag);
        TextView sex=view.findViewById(R.id.se);
        TextView address=view.findViewById(R.id.ad);
        TextView ward=view.findViewById(R.id.wa);
        TextView date=view.findViewById(R.id.da);
        TextView phone=view.findViewById(R.id.ph);
        //Button dia=view.findViewById(R.id.button4);
       final Patient p=PItems.get(i);

        name.setText(p.getName());
        age.setText("Age ="+p.getAge());
        sex.setText("Sex ="+p.getSex());
        address.setText("Address ="+p.getAddress());
        ward.setText("Ward ="+p.getWard());
        date.setText("Date ="+p.getDate());
        phone.setText("Phone number="+p.getPhone());


        return view;
    }

}
