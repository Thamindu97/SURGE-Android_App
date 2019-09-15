package com.example.surge;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Database.DBHandler;


public class AddAccessories extends AppCompatActivity {

    EditText ascType, ascSize, ascColour, ascPrice, ascDelID;

    Button addAccessory, viewAccessory, deleteAccessory;
    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_accessories);

        db = new DBHandler(this);

        ascType = findViewById(R.id.acsTypeText);
        ascSize= findViewById(R.id.acsSizeText);
        ascColour = findViewById(R.id.acsColorText);
        ascPrice = findViewById(R.id.acsPriceText);
        ascDelID = findViewById(R.id.acsDeleteText);

        addAccessory = findViewById(R.id.acsAdd);
        viewAccessory = findViewById(R.id.acsView);
        deleteAccessory = findViewById(R.id.acsDelete);

        AddAccessory();
        viewAll();
        deleteAccessory();

    }

    public  void AddAccessory() {
        addAccessory.setOnClickListener(


                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = db.addAccessory(ascType.getText().toString(),
                                ascSize.getText().toString(), ascColour.getText().toString(), ascPrice.getText().toString());

                        if(isInserted == true) {
                            Toast.makeText(AddAccessories.this, "Accessory added.", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(AddAccessories.this, AddAccessories.class);
                            startActivity(intent);
                        }else
                            Toast.makeText(AddAccessories.this,"Accessory not added.",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void viewAll() {
        viewAccessory.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = db.getAllAccessories();
                        if (res.getCount() == 0) {
                            // show message
                            displayMessage("Error", "Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :" + res.getString(0) + "\n");
                            buffer.append("Item Type :" + res.getString(1) + "\n");
                            buffer.append("Size :" + res.getString(2) + "\n");
                            buffer.append("Colour :" + res.getString(2) + "\n");
                            buffer.append("Price :" + res.getString(3) + "\n\n");
                        }

                        // Show all data
                        displayMessage("Data", buffer.toString());
                    }
                }
        );
    }

    public void displayMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void deleteAccessory() {
        deleteAccessory.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int delID = Integer.parseInt(ascDelID.getText().toString());

                        if(db.deleteAccessory(delID) == true)
                            Toast.makeText(AddAccessories.this, "Accessory deleted.", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AddAccessories.this, "Accessory could not be deleted. Re-check ID.", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}
