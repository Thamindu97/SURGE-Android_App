package com.example.surge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static String userName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    public void onClickSignUp(View view)
    {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void onClickBuyItNow(View view)
    {
        Intent intent = new Intent(this, AddBuyInfo.class);
        startActivity(intent);
    }

    public void onClickAdd(View view)
    {
        Intent intent = new Intent(this, Add.class);
        startActivity(intent);
    }

    public void onClickAboutMe(View view)
    {
        if(!userName.equals("")) {
            Intent intent = new Intent(this, AboutMe.class);
            startActivity(intent);
        }
    }
}
