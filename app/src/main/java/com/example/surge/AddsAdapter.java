package com.example.surge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.List;

public class AddsAdapter extends ArrayAdapter<Clothes>{

    private static final String TAG = "salesListAdapter";
    private Context saContext;
    int saResource;

    public AddsAdapter(Context context, int resource, List<Clothes> objects) {
        super(context, resource, objects);
        saContext = context;
        saResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        int id = getItem(position).getclothes_id();
        String clothType = getItem(position).getclothType();
        String clothSize = getItem(position).getclothSize();
        String clothColour = getItem(position).getclothColour();
        String clothPrice = getItem(position).getclothPrice();

        Clothes clothes = new Clothes(id,clothType,clothSize ,clothColour,clothPrice);

        LayoutInflater layoutInflater = LayoutInflater.from(saContext);
        convertView = layoutInflater.inflate(saResource,parent,false);

        TextView tvId = (TextView)convertView.findViewById(R.id.adapter_clothes_view_id);
        TextView tvclothType = (TextView)convertView.findViewById(R.id.adapter_clothes_view_clothtype);
        TextView tvclothSize = (TextView)convertView.findViewById(R.id.adapter_clothes_view_size);
        TextView tvclothColour= (TextView)convertView.findViewById(R.id.adapter_clothes_view_colour);
        TextView tvclothPrice = (TextView)convertView.findViewById(R.id.adapter_clothes_view_price);

        tvId.setText(String.valueOf(id));
        tvclothType.setText(clothType);
        tvclothSize.setText(clothSize);
        tvclothColour.setText(String.valueOf(clothColour));
        tvclothPrice.setText(String.valueOf(clothPrice));

        return convertView;

    }
}
