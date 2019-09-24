package com.example.surge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static String userName = "";
    public static String clothID = "";
    Button loginb, logout, aboutMe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginb = (Button) findViewById(R.id.buttonlogin);
        aboutMe = (Button) findViewById(R.id.buttonAboutMe);


        // hides login button when logged in
        if(userName != "") {
            loginb.setVisibility(View.GONE);
            aboutMe.setVisibility(View.VISIBLE);
        }

        // hides aboutMe button when logged out or removed user's account
        if(userName == "")
        {
            aboutMe.setVisibility(View.GONE);
            loginb.setVisibility(View.VISIBLE);
        }

    }

    public void onClickSignUp(View view)
    {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void onClickBuyInfo(View View)
    {
        Intent intent = new Intent(this, BuyInfo.class);
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
