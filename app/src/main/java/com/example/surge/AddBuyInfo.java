package com.example.surge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Database.DBHandler;
import Database.DBHandler_Shipment;

public class AddBuyInfo extends AppCompatActivity {

    Button addCard;
    Button saveinfo;

    EditText name, phone, email, street, city;

    DBHandler_Shipment db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_buy_info);

        db = new DBHandler_Shipment(this);

        name = findViewById(R.id.editText_buyinfo_name);
        phone = findViewById(R.id.editText_buyinfo_phone);
        email = findViewById(R.id.editText_buyinfo_email);
        street = findViewById(R.id.editText_buyinfo_street);
        city = findViewById(R.id.editText_buyinfo_city);

        addCard = (Button) findViewById(R.id.button_buyinfo_card);
        saveinfo = (Button) findViewById(R.id.button_buyinfo_save);

        addBuyData();

    }

    public void addBuyData()
    {
        saveinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = db.addBuyInfo(name.getText().toString(),
                        phone.getText().toString(),email.getText().toString(),street.getText().toString(),city.getText().toString());

                if (isInserted == true)
                {
                    Toast.makeText(AddBuyInfo.this,"Data Inserted", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(AddBuyInfo.this,"Data Not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onClickAddCard(View view) {

        Intent intent = new Intent(this, CardDetails.class);
        startActivity(intent);
    }
}
