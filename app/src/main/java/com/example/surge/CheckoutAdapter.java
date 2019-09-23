package com.example.surge;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.MyViewHolder> {

    private ArrayList<Checkout_Bin> checkout_bins = new ArrayList<>();

    public CheckoutAdapter(ArrayList<Checkout_Bin> checkout_bins) {

        this.checkout_bins = checkout_bins;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_shipmentdetails,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.id.setText(Integer.toString(checkout_bins.get(position).getId()));
        holder.name.setText(checkout_bins.get(position).getName());
        holder.phone.setText(checkout_bins.get(position).getPhone());
        holder.email.setText(checkout_bins.get(position).getEmail());
        holder.street.setText(checkout_bins.get(position).getStreet());
        holder.city.setText(checkout_bins.get(position).getCity());

    }

    @Override
    public int getItemCount() {
        return checkout_bins.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id, name, phone, email, street, city;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            id = (TextView)itemView.findViewById(R.id.shipmentdetails_id);
            name = (TextView)itemView.findViewById(R.id.shipmentdetails_name);
            phone = (TextView)itemView.findViewById(R.id.shipmentdetails_phone);
            email = (TextView)itemView.findViewById(R.id.shipmentdetails_email);
            street = (TextView)itemView.findViewById(R.id.shipmentdetails_street);
            city = (TextView)itemView.findViewById(R.id.shipmentdetails_city);
        }
    }
}
