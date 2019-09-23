package com.example.surge;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.afollestad.materialdialogs.MaterialDialog;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Blob;

import Database.DBHandler;

public class AddClothes extends AppCompatActivity  {

    EditText clothtype,size,colour, price;
    String txtcloth;
    String txtsize;
    String txtcolour;
    String txtprice;
    Blob txtimage;

    ImageView clothimage;

    Button addcloth,pickImage;

    private static final int SELECT_PHOTO = 1;
    private static final int CAPTURE_PHOTO = 2;
    private ProgressDialog progressBar;
    private int progressBarStatus = 0;
    private Handler progressBarbHandler = new Handler();

    Bitmap thumbnail;


    private static final int SELECT_PICTURE = 1;
    Bitmap yourSelectedImage;
    public static ImageView imageView;
    private static final int CAMERA_REQUEST_CODE = 100;
    private static final String TAG = MainActivity.class.getSimpleName();
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
        //upload = findViewById(R.id.button5_Uploadcloth);

        clothimage = findViewById(R.id.imageViewCloth);
        pickImage =  findViewById(R.id.pick_image);


        //pickImage.setOnClickListener(this);

        if (ContextCompat.checkSelfPermission(AddClothes.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            clothimage.setEnabled(false);
            ActivityCompat.requestPermissions(AddClothes.this, new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, 0);
        } else {
            clothimage.setEnabled(true);
        }

        db = new DBHandler(this);
        

        //vibrator sensor
        vibr = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

    }


    ////////////////////////////pick image part----------------------------------------------------////////////////////////////////////////////////


    public void onClickPickImage(final View view) {
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
                                        clothimage.setImageResource(R.drawable.ic_account_circle_black);
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
                clothimage.setEnabled(true);
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
                    clothimage.setImageBitmap(selectedImage);


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


    private void onCaptureImageResult(Intent data) {
        thumbnail = (Bitmap) data.getExtras().get("data");

        //set Progress Bar
        setProgressBar();
        //set profile picture form camera
        clothimage.setMaxWidth(200);
        clothimage.setImageBitmap(thumbnail);

    }

    ////////////////////////////---------------------------------------------------------------///////////////////////////////////////////////////

    public void onClick(View v) {

        AddData();

    }

    public  void AddData() {

        txtcloth = clothtype.getText().toString();
        txtsize = size.getText().toString();
        txtcolour = colour.getText().toString();
        txtprice = price.getText().toString();


        clothimage.setDrawingCacheEnabled(true);
        clothimage.buildDrawingCache();
        Bitmap bitmap = clothimage.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        //validation
                    if(!txtcloth.equals("") && !txtsize.equals("") &&  !txtcolour.equals("") &&  !txtprice.equals("") ){


                                    if(db.addClothes(txtcloth,txtsize,txtcolour,txtprice,data)) {
                                                Toast.makeText(AddClothes.this, "Data Inserted", Toast.LENGTH_LONG).show();

                                                    Intent intent = new Intent(AddClothes.this, AddClothes.class);
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


    public void onClickUpload(View v) {
        // Code here executes on main thread after user presses button

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);



    }

    public void onClickList(View view){

        Intent intent = new Intent(AddClothes.this,ClothesView.class);
        startActivity(intent);
    }


}
