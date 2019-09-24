package com.example.surge;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.afollestad.materialdialogs.MaterialDialog;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import Database.DBHandler;


public class AddAccessories extends AppCompatActivity implements View.OnClickListener{

    EditText ascType, ascSize, ascColour, ascPrice, ascDelID;

    Button addAccessory, viewAccessory, deleteAccessory;
    DBHandler db;

    Vibrator vibr;

    private ImageView profileImageView;
    private Button pickImage;

    private static final int SELECT_PHOTO = 1;
    private static final int CAPTURE_PHOTO = 2;

    private ProgressDialog progressBar;
    private int progressBarStatus = 0;
    private Handler progressBarbHandler = new Handler();
    private boolean hasImageChanged = false;


    Bitmap thumbnail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_accessories);

        profileImageView = (ImageView) findViewById(R.id.profileImageView);
        pickImage = (Button) findViewById(R.id.pick_image);

        ascType = findViewById(R.id.acsTypeText);
        ascSize= findViewById(R.id.acsSizeText);
        ascColour = findViewById(R.id.acsColorText);
        ascPrice = findViewById(R.id.acsPriceText);
        ascDelID = findViewById(R.id.acsDeleteText);

        addAccessory = findViewById(R.id.acsAdd);
        viewAccessory = findViewById(R.id.acsView);
        deleteAccessory = findViewById(R.id.acsDelete);

        pickImage.setOnClickListener(this);

        if (ContextCompat.checkSelfPermission(AddAccessories.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            profileImageView.setEnabled(false);
            ActivityCompat.requestPermissions(AddAccessories.this, new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, 0);
        } else {
            profileImageView.setEnabled(true);
        }

        db = new DBHandler(this);

        AddAccessory();
        viewAll();
        deleteAccessory();


    }

    public  void AddAccessory() {
        addAccessory.setOnClickListener(


                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        profileImageView.setDrawingCacheEnabled(true);
                        profileImageView.buildDrawingCache();
                        Bitmap bitmap = profileImageView.getDrawingCache();
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        byte[] data = baos.toByteArray();

                        if (!ascType.getText().toString().equals("") && !ascSize.getText().toString().equals("") && !ascColour.getText().toString().equals("") && !ascPrice.getText().toString().equals("")){
                            boolean isInserted = db.addAccessory(ascType.getText().toString(),
                                    ascSize.getText().toString(), ascColour.getText().toString(), ascPrice.getText().toString(), data);
                            if (isInserted == true) {
                                Toast.makeText(AddAccessories.this, "Accessory added.", Toast.LENGTH_LONG).show();
                                emptyFields();
                            } else
                                Toast.makeText(AddAccessories.this, "Accessory not added.", Toast.LENGTH_LONG).show();
                        } else {
                            Toast toast = Toast.makeText(getApplicationContext(), "Please fill all required fields.", Toast.LENGTH_LONG);
                            toast.show();
                        }
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
                            buffer.append("Colour :" + res.getString(3) + "\n");
                            buffer.append("Price :" + res.getString(4) + "\n");
                            buffer.append("Image : " + res.getBlob(5) + "\n\n");
                        }

                        // Show all data
                        displayMessage("Data", buffer.toString());
//                        Intent intent = new Intent(AddAccessories.this, AccessoryView.class);
//                        startActivity(intent);
                        //vibr.vibrate(35);
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

    public void updateAccessory(View view)
    {
        Intent intent = new Intent(this, AccessoriesUpdate.class);
        String keyIdentifier = null;
        intent.putExtra("key", ascDelID.getText().toString());
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.pick_image:
                new MaterialDialog.Builder(this)
                        .title(R.string.uploadImages)
                        .items(R.array.uploadImages)
                        .itemsIds(R.array.itemIds)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                switch (which){
                                    case 0:
                                        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                                        photoPickerIntent.setType("image/*");
                                        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
                                        break;
                                    case 1:
                                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                        startActivityForResult(intent, CAPTURE_PHOTO);
                                        break;
                                    case 2:
                                        profileImageView.setImageResource(R.drawable.ic_account_circle_black);
                                        break;
                                }
                            }
                        })
                        .show();
                break;

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                profileImageView.setEnabled(true);
            }
        }
    }

    public void setProgressBar(){
        progressBar = new ProgressDialog(this);
        progressBar.setCancelable(true);
        progressBar.setMessage("Please wait...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setProgress(0);
        progressBar.setMax(100);
        progressBar.show();
        progressBarStatus = 0;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressBarStatus < 100){
                    progressBarStatus += 30;

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    progressBarbHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressBarStatus);
                        }
                    });
                }
                if (progressBarStatus >= 100) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progressBar.dismiss();
                }

            }
        }).start();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SELECT_PHOTO){
            if(resultCode == RESULT_OK) {
                try {
                    final Uri imageUri = data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    //set Progress Bar
                    setProgressBar();
                    //set profile picture form gallery
                    profileImageView.setImageBitmap(selectedImage);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }else if(requestCode == CAPTURE_PHOTO){
            if(resultCode == RESULT_OK) {
                onCaptureImageResult(data);
            }
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_sort:
                Intent intent = new Intent(this, DetailsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    private void onCaptureImageResult(Intent data) {
        thumbnail = (Bitmap) data.getExtras().get("data");

        //set Progress Bar
        setProgressBar();
        //set profile picture form camera
        profileImageView.setMaxWidth(200);
        profileImageView.setImageBitmap(thumbnail);

    }

    private void emptyFields() {
        ascType.setText("");
        ascSize.setText("");
        ascColour.setText("");
        ascPrice.setText("");
        profileImageView.setImageResource(R.drawable.ic_account_circle_black);

    }
}
