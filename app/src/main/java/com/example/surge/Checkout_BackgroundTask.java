package com.example.surge;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Database.DBHandler_Shipment;
import Database.Shipment;

public class Checkout_BackgroundTask extends AsyncTask<Void, Void, Void> {

    private RecyclerView recyclerView;
    private Context context;
    private CheckoutAdapter adapter;
    private ArrayList<Checkout_Bin> checkout_bins = new ArrayList<>();

    public Checkout_BackgroundTask(RecyclerView recyclerView, Context context) {

        this.recyclerView = recyclerView;
        this.context = context;

    }

    @Override
    protected void onPreExecute() {

        adapter = new CheckoutAdapter(checkout_bins);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected Void doInBackground(Void... voids) {

        DBHandler_Shipment dbHandler_shipment = new DBHandler_Shipment(context);
        SQLiteDatabase database = dbHandler_shipment.getReadableDatabase();

        int id;
        String name, phone, email, street, city;

        Cursor cursor = dbHandler_shipment.readBuyInfo(database);
        while (cursor.moveToNext())
        {
            id = cursor.getInt(cursor.getColumnIndex("id"));
            name = cursor.getString(cursor.getColumnIndex(Shipment.BuyInfo.COLUMN4_NAME_NAME));
            phone = cursor.getString(cursor.getColumnIndex(Shipment.BuyInfo.COLUMN4_NAME_PHONE));
            email = cursor.getString(cursor.getColumnIndex(Shipment.BuyInfo.COLUMN4_NAME_EMAIL));
            street = cursor.getString(cursor.getColumnIndex(Shipment.BuyInfo.COLUMN4_NAME_STREET));
            city = cursor.getString(cursor.getColumnIndex(Shipment.BuyInfo.COLUMN4_NAME_CITY));
        }

        return null;

    }

    @Override
    protected void onPostExecute(Void aVoid) {

        super.onPostExecute(aVoid);

    }
}
