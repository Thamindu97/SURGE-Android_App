package com.example.surge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Checkout extends AppCompatActivity {

    Button confirmpay;

    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView_shipmentdetails);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        confirmpay = findViewById(R.id.button_checkout_confirmpay);
    }

    public void onClickConfirm(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

        Toast.makeText(this,"Order Successful", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {

        super.onResume();

        new Checkout_BackgroundTask(recyclerView,this).execute();

    }
}
