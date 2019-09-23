package com.example.surge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class AccessoriesAdapter extends ArrayAdapter<Accessories> {

    private static final String TAG = "AccessListAdapter";
    private Context aContext;
    int aResource;

    public AccessoriesAdapter(Context context, int resource, List<Accessories> items) {
        super(context, resource, items);
        aContext = context;
        aResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        int acsId = getItem(position).getAcs_id();
        String acsType = getItem(position).getAcs_type();
        String acsSize = getItem(position).getAcs_size();
        String acsColour = getItem(position).getAcs_colour();
        String acsPrice = getItem(position).getAcs_price();

        Accessories accessories = new Accessories(acsId, acsType, acsSize, acsColour, acsPrice);

        LayoutInflater layoutInflater = LayoutInflater.from(aContext);
        convertView = layoutInflater.inflate(aResource,parent,false);

        TextView tvAcsId = (TextView)convertView.findViewById(R.id.adapter_acsId);
        TextView tvAcsType = (TextView)convertView.findViewById(R.id.adapter_acsType);
        TextView tvAcsSize = (TextView)convertView.findViewById(R.id.adapter_acsSize);
        TextView tvAcsColour= (TextView)convertView.findViewById(R.id.adapter_acsColour);
        TextView tvAcsPrice = (TextView)convertView.findViewById(R.id.adapter_acsPrice);

        tvAcsId.setText(String.valueOf(acsId));
        tvAcsType.setText(acsType);
        tvAcsSize.setText(acsSize);
        tvAcsColour.setText(String.valueOf(acsColour));
        tvAcsPrice.setText(String.valueOf(acsPrice));

        return convertView;
    }
}
