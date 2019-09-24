package com.example.surge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Database.DBHandler;

public class CardDetails extends AppCompatActivity {

    String cname, ccardno, cdate, ccvv;

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

                cname = name.getText().toString();
                ccardno = cardno.getText().toString();
                cdate = date.getText().toString();
                ccvv = cvv.getText().toString();

                if (!cname.equals("") && !ccardno.equals("") && !cdate.equals("") && !ccvv.equals(""))
                {
                    if (ccardno.length() != 12)
                    {
                        Toast.makeText(CardDetails.this, "Please Enter A Valid Number", Toast.LENGTH_SHORT).show();
                    }
                    if (ccvv.length() != 3)
                    {
                        Toast.makeText(CardDetails.this, "Please Enter A Valid Number", Toast.LENGTH_SHORT).show();
                    }
                    else if (db.addCardDetails(cname, ccardno, cdate, ccvv))
                    {
                        Toast.makeText(CardDetails.this,"Data Inserted", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(CardDetails.this, "Fields Cannot Be Empty", Toast.LENGTH_SHORT).show();
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
