package com.example.surge;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Database.DBHandler;

public class DeleteClothes extends AppCompatActivity {

    EditText eClothesDeleteId;
    Button clothesDeleteButton;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_clothes);

        //back

        back = (ImageView)findViewById(R.id.button_back2);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it1 = new Intent(DeleteClothes.this, ClothesView.class);
                startActivity(it1);
            }
        });


        eClothesDeleteId = findViewById(R.id.edit_clothes_update_id2);

        //assigning delete button
        clothesDeleteButton = (Button)findViewById(R.id.buttondeleteclothes3);

        //calling the TextWatcher function to the id field
        eClothesDeleteId.addTextChangedListener(deleteSalesTextWatcher);
    }

    public void onClickDelete(View view){

        int onStocksDeleteId = Integer.parseInt(eClothesDeleteId.getText().toString());

        //DBHandler object created
        DBHandler dbhandler = new DBHandler(this);

        //Toast creation
        Toast t;

        //check if the insertion was successful
        if(dbhandler.deleteClothes(onStocksDeleteId)){
            //Toast message if deletion is successful
            t = Toast.makeText(getApplicationContext(),"Cloth has been deleted from MY ADDS!", Toast.LENGTH_LONG);
            t.show();
            Intent intent = new Intent(DeleteClothes.this, ClothesView.class);
            startActivity(intent);
        }
        else{
            //Toast message if insertion fails
            t = Toast.makeText(getApplicationContext(),"Cloth deletion failed!", Toast.LENGTH_LONG);
            t.show();
        }
    }

    private TextWatcher deleteSalesTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            String onSalesDeleteId = eClothesDeleteId.getText().toString().trim();

            clothesDeleteButton.setEnabled(!onSalesDeleteId.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}
