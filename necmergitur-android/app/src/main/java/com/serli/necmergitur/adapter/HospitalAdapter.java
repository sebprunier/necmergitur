package com.serli.necmergitur.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.serli.necmergitur.R;
import com.serli.necmergitur.model.Hopital;

/**
 * Created by florianpires on 16/01/16.
 */
public class HospitalAdapter extends ArrayAdapter<Hopital> {


    public HospitalAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.simplerow, null);
        }

        Hopital hospital = getItem(position);

        if (hospital != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.rowTextView);

            if (tt1 != null) {
                tt1.setText(hospital.getName());
            }
        }

        return v;
    }
}
