package com.example.surge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import Database.DBHandler;

public class AboutMe extends AppCompatActivity {

    public static EditText txt_Name , txt_Password, txt_Phone, txt_Email;
    String name , password, phone, email, address;
    DBHandler db;
    Button update, remove;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

        txt_Name = findViewById(R.id.name2);
        txt_Email = findViewById(R.id.email2);
        txt_Phone = findViewById(R.id.mobileNo2);
        txt_Password = findViewById(R.id.password2);

        db = new DBHandler(this);
        update = findViewById(R.id.btn_update);
        remove = findViewById(R.id.btn_remove);


        showData();


        update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button

                name = txt_Name.getText().toString();
                password = txt_Password.getText().toString();
                phone = txt_Phone.getText().toString();
                email = txt_Email.getText().toString();

                if(!txt_Name.equals("") && !txt_Password.equals("") &&  ( !txt_Phone.equals("") || !txt_Email.equals("")  )){
                    if(db.updateCustomerInfo(name, password, phone, email)) {
                        Toast t = Toast.makeText(getApplicationContext(), "User details successfully Updated! ", Toast.LENGTH_LONG);
                        t.show();
                    }
                    else{
                        Toast t = Toast.makeText(getApplicationContext(), "User details cannot be Updated! ", Toast.LENGTH_LONG);
                        t.show();
                    }
                }
            }
        });

        remove.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                name = txt_Name.getText().toString();
                if(!name.equals("")){
                    db.deleteCustomerInfo(name);
                }
            }
        });

    }

    private void showData()
    {
        db.readLoggedUserInfo("Thamindu");
    }


}
