package com.example.surge;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

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
                Intent intent = new Intent(Checkout.this, CardDetails.class);
                startActivity(intent);
            }
        });
    }

    public void onClickUpdate(View view)
    {
        Intent intent = new Intent(this, EditBuyInfo.class);
        startActivity(intent);
    }

    public void onClickConfirmPay(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Toast.makeText(Checkout.this,"Order Confirmed", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
}
