package com.example.surge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Database.DBHandler;

public class AccessoriesUpdate extends AppCompatActivity {

    String acsType, acsSize, acsColour, acsPrice;

    TextView acsId;

    public static EditText accessType, accessColour, accessSize, accessPrice;

    Button saveAcs;

    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accessories_update);

        db = new DBHandler(this);

        acsId = findViewById(R.id.upAcsId);
        accessType = findViewById(R.id.upAcsType);
        accessColour = findViewById(R.id.upAcsColour);
        accessSize = findViewById(R.id.upAcsSize);
        accessPrice = findViewById(R.id.upAcsPrice);

        saveAcs = findViewById(R.id.buttonSaveAcsUpdate);

        Bundle bundle = getIntent().getExtras();

        if(bundle.getString("key")!= null)
        {
            acsId.setText(bundle.getString("key"));

            db.showAccessoriesInfo(acsId.getText().toString());
            updateAccessory(acsId.getText().toString());
        }


    }

    public void updateAccessory(final String id)
    {
        saveAcs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                acsType = accessType.getText().toString();
                acsColour = accessColour.getText().toString();
                acsSize = accessSize.getText().toString();
                acsPrice = accessPrice.getText().toString();


                    if (db.updateAccessoryInfo(id, acsType, acsColour, acsSize, acsPrice) == true)
                    {
                        Toast.makeText(AccessoriesUpdate.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(AccessoriesUpdate.this, "Data Cannot Be Updated", Toast.LENGTH_SHORT).show();
                    }

            }
        });
    }


}
