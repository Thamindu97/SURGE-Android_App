package com.example.surge;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Database.DBHandler;


public class AddClothes extends AppCompatActivity {

    EditText clothtype,size,colour, price;
    String txtcloth,txtsize,txtcolour,txtprice;

    Button addcloth;
    DBHandler db;

    Vibrator vibr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_clothes);

        db = new DBHandler(this);

        clothtype = findViewById(R.id.txtClothtype);
        size= findViewById(R.id.txtSize);
        colour = findViewById(R.id.txtColour);
        price = findViewById(R.id.txtPrice);

        addcloth=findViewById(R.id.buttonAddforSale);
        

        //vibrator sensor
        vibr = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

    }

    public void onClick(View v) {

        AddData();

    }

    public  void AddData() {

        txtcloth = clothtype.getText().toString();
        txtsize = size.getText().toString();
        txtcolour = colour.getText().toString();
        txtprice = price.getText().toString();


        //validation
                    if(!txtcloth.equals("") && !txtsize.equals("") &&  !txtcolour.equals("") &&  !txtprice.equals("") ){

                        //boolean isInserted = db.addClothes(clothtype.getText().toString(),size.getText().toString(),colour.getText().toString(),price.getText().toString());

                                    if(db.addClothes(txtcloth,txtsize,txtcolour,txtprice)) {
                                                Toast.makeText(AddClothes.this, "Data Inserted", Toast.LENGTH_LONG).show();

                                                    Intent intent = new Intent(AddClothes.this, ClothesView.class);
                                                    startActivity(intent);
                                                    vibr.vibrate(35);

                                    }else {
                                        Toast.makeText(AddClothes.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                                            }

                    }else{
                            Toast toast = Toast.makeText(getApplicationContext(), "Please fill the empty fields", Toast.LENGTH_LONG);
                            toast.show();

                        }
    }

    public void onClickHome(View view){

        Intent intent = new Intent(AddClothes.this,MainActivity.class);
        startActivity(intent);
    }
}
