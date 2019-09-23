package com.example.surge;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Database.DBHandler;

public class SignUp extends AppCompatActivity implements View.OnClickListener {




    EditText txt_UserName, txt_Email, txt_Password, txt_MobileNo, txt_Password2;
    String userName, email, password, password2 , mobileNo;
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
        txt_Password2 = findViewById(R.id.password2);

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
        password2 = txt_Password2.getText().toString();


        // validations
        if(!userName.equals("") && !password.equals("") && ( !mobileNo.equals("") || !email.equals(""))){
            if(mobileNo.length() != 10 ){
                Toast t = Toast.makeText(getApplicationContext(), "Please provide a valid mobile No!", Toast.LENGTH_LONG);
                t.show();

            }
            else if(!password.equals(password2)){
                Toast t = Toast.makeText(getApplicationContext(), "Passwords doesn't match", Toast.LENGTH_LONG);
                t.show();
            }

            else if(db.addRegisterInfo(userName, email, mobileNo, password)){
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



