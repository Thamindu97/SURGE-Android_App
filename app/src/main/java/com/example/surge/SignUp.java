package com.example.surge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Database.DBHandler;

public class SignUp extends AppCompatActivity implements View.OnClickListener {




    EditText txt_UserName, txt_Email, txt_Password, txt_MobileNo;
    String userName, email, password, mobileNo;
    DBHandler db;
    Button signUp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        txt_UserName = findViewById(R.id.name2);
        txt_MobileNo = findViewById(R.id.mobileno);
        txt_Email = findViewById(R.id.txt_email);
        txt_Password = findViewById(R.id.password1);

        signUp = (Button)findViewById(R.id.btn_signup);

        db = new DBHandler(this);

        signUp.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {


        addUser();



    }

    private void addUser(){



        userName = txt_UserName.getText().toString();
        email = txt_Email.getText().toString();
        mobileNo = txt_MobileNo.getText().toString();
        password = txt_Password.getText().toString();

        // validations
        if(!userName.equals("") && !password.equals("") && ( !mobileNo.equals("") || !email.equals(""))){
            if(db.addRegisterInfo(userName, email, mobileNo, password)){
                Toast t = Toast.makeText(getApplicationContext(), "You have successfully registered!", Toast.LENGTH_LONG);
                t.show();
            }
            else
            {
                Toast t = Toast.makeText(getApplicationContext(), "Can't register!", Toast.LENGTH_LONG);
                t.show();
            }
        }
        else{

            Toast toast = Toast.makeText(getApplicationContext(), "Please fill the empty fields", Toast.LENGTH_LONG);
            toast.show();
        }


    }


}
