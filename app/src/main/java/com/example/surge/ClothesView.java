package com.example.surge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;

import Database.DBHandler;


public class ClothesView extends AppCompatActivity {

    ImageView back;
    DBHandler dbHandler;
    List<Clothes> clothesList;
    Vibrator vibr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothes_view);

        //back buuton

        back = (ImageView)findViewById(R.id.button_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it1 = new Intent(ClothesView.this,Add.class);
                startActivity(it1);
            }
        });

        //vibrator sensor
        vibr = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);



        //List View

        final ListView listView = (ListView)findViewById(R.id.clothes_list_view);

        dbHandler = new DBHandler(this);

        clothesList = dbHandler.readAllClothes();

        final AddsAdapter stocksAdapter = new AddsAdapter(this,R.layout.adapter_clothes_view,clothesList);
        listView.setAdapter(stocksAdapter);


        //click to update


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {

                Intent intent = new Intent(ClothesView.this, UpdateClothes.class);

                intent.putExtra("ID", String.valueOf(id));
                startActivity(intent);



                vibr.vibrate(28);
            }
        });



    }

    public void onClickHome(View view){

        Intent intent = new Intent(ClothesView.this,MainActivity.class);
        startActivity(intent);
    }

    public void onClickDeletePage(View view){

        Intent intent = new Intent(ClothesView.this,DeleteClothes.class);
        startActivity(intent);
    }




}
