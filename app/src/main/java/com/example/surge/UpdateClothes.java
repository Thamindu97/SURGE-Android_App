package com.example.surge;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Database.DBHandler;

public class UpdateClothes extends AppCompatActivity {



    ImageView back;//variable for the back button
    public static EditText eStocksUpdateId, eStocksUpdateClothType, eStocksUpdateSize, eStocksUpdateColour, eStocksUpdatePrice; //variables for the edit text
    DBHandler db;
    Button clothesUpdateButton;
    Vibrator vibr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_clothes);

        //back button
        back = (ImageView)findViewById(R.id.button_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it1 = new Intent(UpdateClothes.this, ClothesView.class);
                startActivity(it1);
            }
        });

        //vibrator sensor
        vibr = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        Intent intent = getIntent();
        String ID = intent.getExtras().getString("ID");


        //update implementation
        //assigning values to the variables using the ids of the EditText
        eStocksUpdateId = findViewById(R.id.edit_clothes_update_id);
        eStocksUpdateId.setText(ID);
        eStocksUpdateClothType = findViewById(R.id.edit_clothes_update_clothtype);
        eStocksUpdateSize = findViewById(R.id.edit_clothes_update_size);
        eStocksUpdateColour = findViewById(R.id.edit_clothes_update_colour);
        eStocksUpdatePrice = findViewById(R.id.edit_clothes_update_price);

        //assigning submit button
        clothesUpdateButton = (Button)findViewById(R.id.button_clothes_update_update);

        //calling the TextWatcher function to each field
        eStocksUpdateId.addTextChangedListener(updateStocksTextWatcher);
        eStocksUpdateClothType.addTextChangedListener(updateStocksTextWatcher);
        eStocksUpdateSize.addTextChangedListener(updateStocksTextWatcher);
        eStocksUpdateColour.addTextChangedListener(updateStocksTextWatcher);
        eStocksUpdatePrice.addTextChangedListener(updateStocksTextWatcher);



    }

    //onclick
    public void onClickUpdate(View view){

        int onStocksUpdateId = Integer.parseInt(eStocksUpdateId.getText().toString());
        String onStocksUpdatetClothType = eStocksUpdateClothType.getText().toString();
        String onStocksUpdateSize = eStocksUpdateSize.getText().toString();
        String onStocksUpdateColour = eStocksUpdateColour.getText().toString();
        String onStocksUpdatePrice = eStocksUpdatePrice.getText().toString();

        //DBHandler object created
        DBHandler dbhandler = new DBHandler(this);

        //Toast creation
        Toast t;

        //check if the insertion was successful
        if(dbhandler.updateStocks(onStocksUpdateId,onStocksUpdatetClothType,onStocksUpdateSize,onStocksUpdateColour,onStocksUpdatePrice)){
            //Toast message if insertion is successful
            t = Toast.makeText(getApplicationContext(),"Stocks has been updated successfully!", Toast.LENGTH_LONG);
            t.show();
            vibr.vibrate(35);
        }
        else{
            //Toast message if insertion fails
            t = Toast.makeText(getApplicationContext(),"Stocks failed to be updated!", Toast.LENGTH_LONG);
            t.show();
        }
    }

    //TextWatcher function
    private TextWatcher updateStocksTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            String onStocksUpdateId = eStocksUpdateId.getText().toString().trim();
            String onStocksUpdatetClothType = eStocksUpdateClothType.getText().toString().trim();
            String onStocksUpdateSize = eStocksUpdateSize.getText().toString().trim();
            String onStocksUpdateColour = eStocksUpdateColour.getText().toString().trim();
            String onStocksUpdatePrice = eStocksUpdatePrice.getText().toString().trim();

            clothesUpdateButton.setEnabled(!onStocksUpdateId.isEmpty() && !onStocksUpdatetClothType.isEmpty() && !onStocksUpdateSize.isEmpty() && !onStocksUpdateColour.isEmpty() && !onStocksUpdatePrice.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };


}
