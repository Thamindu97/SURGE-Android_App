package com.example.surge;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Database.DBHandler;

public class Login extends AppCompatActivity {

    Button login;
    TextView uName, pwd;
    String name , password;
    DBHandler db;

    Vibrator vibr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DBHandler(this);
        login = (Button)findViewById(R.id.btn_login);

        uName = (TextView)findViewById(R.id.txt_logusername);
        pwd = (TextView)findViewById(R.id.txt_logpassword);

        //vibrator sensor
        vibr = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);


        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button

                name = uName.getText().toString();
                password = pwd.getText().toString();


                if(!name.equals("") && !password.equals("")) {
                    if( db.readUserInfo(name,password)){
                        Intent MainActivity = new Intent(getApplicationContext(), com.example.surge.MainActivity.class);
                        startActivity(MainActivity);
                        vibr.vibrate(35);

                    }
                    else {
                        Toast t = Toast.makeText(getApplicationContext(), "User is not existing or Invalid password!", Toast.LENGTH_LONG);
                        t.show();
                    }
                }
                else if(password.equals("") && !name.equals("")  ){
                    Toast toast = Toast.makeText(getApplicationContext(), "Please input password for the given account", Toast.LENGTH_LONG);
                    toast.show();
                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Please fill login details", Toast.LENGTH_LONG);
                    toast.show();
                }

            }
        });

    }

    public void onClickSignUp(View view)
    {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }




}
