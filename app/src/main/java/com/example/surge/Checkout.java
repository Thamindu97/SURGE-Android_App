package com.example.surge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Checkout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
    }

    public void onClickUpdate(View view)
    {
            Intent intent = new Intent(this, EditBuyInfo.class);
            startActivity(intent);
    }
}
