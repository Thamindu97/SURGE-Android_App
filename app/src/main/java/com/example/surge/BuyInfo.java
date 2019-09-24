package com.example.surge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Database.DBHandler;

public class BuyInfo extends AppCompatActivity {

    String uname, uphone, uemail, uaddress;

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

        save = findViewById(R.id.button_buyinfo_save);

        addBuyData();

    }

    public void onClickNext(View View)
    {
        Intent intent = new Intent(BuyInfo.this, CardDetails.class);
        startActivity(intent);
    }

    public void addBuyData()
    {
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                uname = name.getText().toString();
                uphone = phone.getText().toString();
                uemail = email.getText().toString();
                uaddress = address.getText().toString();

                if (!uname.equals("") && !uphone.equals("") && !uemail.equals("") && !uaddress.equals(""))
                {
                    if (uphone.length() != 10)
                    {
                        Toast.makeText(BuyInfo.this, "Please Enter A Valid Number", Toast.LENGTH_SHORT).show();
                    }
                    if (db.addBuyInfo(uname, uphone, uemail, uaddress))
                    {
                        Toast.makeText(BuyInfo.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(BuyInfo.this, "Fields Cannot Be Empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
