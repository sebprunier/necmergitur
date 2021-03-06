package com.serli.necmergitur.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.serli.necmergitur.R;
import com.serli.necmergitur.model.Hopital;
import com.serli.necmergitur.model.TensionColor;

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
            TextView tt2 = (TextView) v.findViewById(R.id.randomTextView);
            LinearLayout ll = (LinearLayout) v.findViewById(R.id.layoutBackground);

            tt1.setTextColor(Color.BLACK);
            tt2.setTextColor(Color.WHITE);
            if (tt1 != null) {
                tt1.setText(hospital.getName());
            }
            if (tt2 != null) {
//                String leftValue = String.valueOf(hospital.getReveil().nombreLitsOccupes+  hospital.getReveil().nombrePatientsEnRoute);
                String rightValue = String.valueOf(hospital.getReveil().nombreLitsDisponibles);
                tt2.setText(String.format("%s", rightValue));
            }
            if (ll != null && hospital.getReveil()!=null) {
                String tension = hospital.getReveil().tension;
                ll.setBackgroundColor(getContext().getResources().getColor(TensionColor.findCode(tension)));
            }

        }

        return v;
    }
}
