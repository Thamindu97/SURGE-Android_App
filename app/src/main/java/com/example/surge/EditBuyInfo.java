package com.example.surge;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        updateBuyData();
    }

    public void showBuyData()
    {
        db.showBuyInfo();
    }

    public void updateBuyData()
    {
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uname = name.getText().toString();
                uphone = phone.getText().toString();
                uemail = email.getText().toString();
                uaddress = address.getText().toString();

                if (!name.equals("") && !phone.equals("") && !email.equals("") && !address.equals(""))
                {
                    if (uphone.length() != 10)
                    {
                        Toast.makeText(EditBuyInfo.this, "Please Enter A Valid Number", Toast.LENGTH_SHORT).show();
                    }
                    else if (db.updateBuyInfo(uname, uphone, uemail, uaddress))
                    {
                        Toast.makeText(EditBuyInfo.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
                else
                {
                    Toast.makeText(EditBuyInfo.this, "Fields Cannot Be Empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
