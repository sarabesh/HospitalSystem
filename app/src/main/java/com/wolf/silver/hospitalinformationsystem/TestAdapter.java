package com.wolf.silver.hospitalinformationsystem;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sarabesh on 11/26/2017.
 */

public class TestAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Test> TItems;


    public TestAdapter(Activity activity, List<Test> Items) {
        this.activity = activity;
        this.TItems = Items;
    }
    @Override
    public int getCount() {
        return TItems.size();
    }

    @Override
    public Object getItem(int i) {
        return TItems.get(i);
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
            view = inflater.inflate(R.layout.test_layout, null);
        TextView pid=view.findViewById(R.id.pid);
        TextView spec=view.findViewById(R.id.spec);
        TextView test=view.findViewById(R.id.tst);
        TextView res=view.findViewById(R.id.res);
        TextView ref=view.findViewById(R.id.rang);
        TextView date=view.findViewById(R.id.dat);

        final Test t=TItems.get(i);

        pid.setText(t.getLid());
        test.setText(t.getTest_name());
        spec.setText(t.getSpec());
        res.setText(t.getResult());
        ref.setText(t.getRef());
        date.setText(t.getDate());
        return view;
    }
}
