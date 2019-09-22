package com.example.surge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Database.DBHandler;

public class BuyInfo extends AppCompatActivity {

    EditText name, phone, email, address;

    Button save;

    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_info);

        db = new DBHandler(this);

        name = findViewById(R.id.editText_buyinfo_name);
        phone = findViewById(R.id.editText_buyinfo_phone);
        email = findViewById(R.id.editText_buyinfo_email);
        address = findViewById(R.id.editText_buyinfo_address);

        save = findViewById(R.id.button_buyinfo_card);

        addBuyData();
    }

    public void onClickAddCard(View View)
    {

    }

    public void addBuyData()
    {
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = db.addBuyInfo(name.getText().toString(),
                        phone.getText().toString(),email.getText().toString(),address.getText().toString());

                if (isInserted == true)
                {
                    Toast.makeText(BuyInfo.this,"Data Inserted", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BuyInfo.this, CardDetails.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(BuyInfo.this,"Data Not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}
