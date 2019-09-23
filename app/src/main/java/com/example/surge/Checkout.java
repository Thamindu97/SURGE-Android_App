package com.example.surge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Database.DBHandler;

public class Checkout extends AppCompatActivity {

    DBHandler db;

    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        db = new DBHandler(this);

        delete = findViewById(R.id.button_checkout_delete);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteCardDetails();
                Toast.makeText(Checkout.this,"Card Details Deleted Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onClickUpdate(View view)
    {
            Intent intent = new Intent(this, EditBuyInfo.class);
            startActivity(intent);
    }
}
