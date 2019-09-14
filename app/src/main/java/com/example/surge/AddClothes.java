package com.example.surge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Database.DBHandler_AddClothes;

public class AddClothes extends AppCompatActivity {

    EditText clothtype,size,colour, price;

    Button addcloth;
    DBHandler_AddClothes db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_clothes);

        db = new DBHandler_AddClothes(this);

        clothtype = findViewById(R.id.txtClothtype);
        size= findViewById(R.id.txtSize);
        colour = findViewById(R.id.txtColour);
        price = findViewById(R.id.txtPrice);

        addcloth=findViewById(R.id.buttonAddClothes);
        AddData();

    }

    public  void AddData() {
        addcloth.setOnClickListener(


                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = db.addClothes(clothtype.getText().toString(),
                                size.getText().toString(),colour.getText().toString(),price.getText().toString());
                        if(isInserted == true) {
                            Toast.makeText(AddClothes.this, "Data Inserted", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(AddClothes.this, ClothesView.class);
                            startActivity(intent);
                        }else
                            Toast.makeText(AddClothes.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }


        );


    }
}
