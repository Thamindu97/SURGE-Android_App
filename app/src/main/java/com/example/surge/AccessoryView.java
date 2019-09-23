package com.example.surge;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import Database.DBHandler;

public class AccessoryView extends AppCompatActivity {

    DBHandler db;
    List<Accessories> acsList;
    Vibrator vibr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accessory_view);

        vibr = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        final ListView listView = (ListView)findViewById(R.id.acs_list_view);

        db = new DBHandler(this);

        acsList = db.readAllAccessories();

        final AccessoriesAdapter accessAdapter = new AccessoriesAdapter(this, R.layout.adapter_accessories_view,acsList);
        listView.setAdapter(accessAdapter);
    }


}