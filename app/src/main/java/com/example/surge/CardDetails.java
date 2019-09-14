package com.example.surge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import Database.DBHandler;

public class CardDetails extends AppCompatActivity {

    EditText name, cardno, date, cvv;

    Button confirm;

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

        confirm = findViewById(R.id.button_card_save);

        addCardData();
    }

    public void addCardData()
    {
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = db.addCardDetails(name.getText().toString(),
                        cardno.getText().toString(),date.getText().toString(),cvv.getText().toString());

                if (isInserted == true)
                {
                    Toast.makeText(CardDetails.this,"Data Inserted", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(CardDetails.this,"Data Not Inserted", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
