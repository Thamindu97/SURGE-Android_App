package com.example.surge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddBuyInfo extends AppCompatActivity {

    Button addCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_buy_info);

        addCard = (Button) findViewById(R.id.button_buyinfo_card);

    }

    public void onClickAddCard(View view) {

        Intent intent = new Intent(this, CardDetails.class);
        startActivity(intent);

    }
}
