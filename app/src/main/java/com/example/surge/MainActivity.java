package com.example.surge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClickSignUp(View view)
    {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }

    public void onClickAddCard(View view)
    {
        Intent intent = new Intent(this, CardDetails.class);
        startActivity(intent);
    }

    public void onClickAdd(View view)
    {
        Intent intent = new Intent(this, Add.class);
        startActivity(intent);
    }
}
