package com.example.surge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import Database.DBHandler;

public class CardDetails extends AppCompatActivity {

    EditText name, cardno, date, cvv;

    Button save;

    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);

        db = new DBHandler(this);

        name = findViewById(R.id.editText_card_name);
        cardno = findViewById(R.id.editText_card_cnumber);
        date = findViewById(R.id.editText_card_date);
        cvv = findViewById(R.id.editText_card_cvv);

        save = findViewById(R.id.button_card_save);

        addCardData();
    }

    public void addCardData()
    {
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = db.addCardDetails(name.getText().toString(),
                        cardno.getText().toString(),date.getText().toString(),cvv.getText().toString());

                if (isInserted == true)
                {
                    Toast.makeText(CardDetails.this,"Data Inserted", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(CardDetails.this,"Data Not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onClickCheckout(View View)
    {
        Intent intent = new Intent(this, Checkout.class);
        startActivity(intent);
    }
}
