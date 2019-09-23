package com.example.surge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Add extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }

    public void onClick(View view){

        Intent intent2 = new Intent(this, AddClothes.class);

        startActivity(intent2);
    }

    public void onClickAccessories(View view){
        Intent intent3 = new Intent(this, AddAccessories.class);

        startActivity(intent3);
    }

    public void onClickBack(View view){
        Intent intent3 = new Intent(this, MainActivity.class);

        startActivity(intent3);
    }
}
