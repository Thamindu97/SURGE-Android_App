package com.example.surge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Database.DBHandler;
import Database.DBHandler_AddClothes;



public class AddAccessories extends AppCompatActivity {

    EditText ascType, ascGender, ascColour, ascPrice;

    Button addAccessory;
    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_accessories);

        db = new DBHandler(this);

        ascType = findViewById(R.id.acsTypeText);
        ascGender= findViewById(R.id.acsGenderText);
        ascColour = findViewById(R.id.acsColorText);
        ascPrice = findViewById(R.id.acsPriceText);

        addAccessory = findViewById(R.id.acsAdd);
        AddData();
    }

    public  void AddData() {
        addAccessory.setOnClickListener(


                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = db.addAccessory(ascType.getText().toString(),
                                ascGender.getText().toString(), ascColour.getText().toString(), ascPrice.getText().toString());

                        if(isInserted == true) {
                            Toast.makeText(AddAccessories.this, "Accessory added.", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(AddAccessories.this, AccessoryView.class);
                            startActivity(intent);
                        }else
                            Toast.makeText(AddAccessories.this,"Accessory not added",Toast.LENGTH_LONG).show();
                    }
                }


        );


    }
}
