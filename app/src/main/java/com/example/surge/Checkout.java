package com.example.surge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Checkout extends AppCompatActivity {

    public static String uname = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
    }

    public void onClickUpdate(View view)
    {
        if(!uname.equals("")) {
            Intent intent = new Intent(this, EditBuyInfo.class);
            startActivity(intent);
        }
    }
}
