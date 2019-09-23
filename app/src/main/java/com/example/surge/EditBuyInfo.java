package com.example.surge;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import Database.DBHandler;

public class EditBuyInfo extends AppCompatActivity {

    String uname, uphone, uemail, uaddress;

    public static EditText name, phone, email, address;

    Button save;

    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_buy_info);

        db = new DBHandler(this);

        name = findViewById(R.id.editText_editbuyinfo_name);
        phone = findViewById(R.id.editText_editbuyinfo_phone);
        email = findViewById(R.id.editText_editbuyinfo_email);
        address = findViewById(R.id.editText_editbuyinfo_address);

        save = findViewById(R.id.button_editbuyinfo_save);

        showBuyData();
    }

    public void showBuyData()
    {
        db.showBuyInfo();
    }
}
