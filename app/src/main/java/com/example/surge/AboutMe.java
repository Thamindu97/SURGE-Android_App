package com.example.surge;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.List;

import Database.DBHandler;
import pub.devrel.easypermissions.EasyPermissions;



public class AboutMe extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    public static EditText txt_Name , txt_Password, txt_Phone, txt_Email;
    String name , password, phone, email;
    DBHandler db;
    Button update, remove, upload;
    private static final int SELECT_PICTURE = 1;
    Bitmap yourSelectedImage;
    public static ImageView imageView;
    private static final int CAMERA_REQUEST_CODE = 100;
    private static final String TAG = MainActivity.class.getSimpleName();





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


        imageView = findViewById(R.id.imageView_dp);

        upload = findViewById(R.id.btn_upload);




        showData();


        update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button

                name = txt_Name.getText().toString();
                password = txt_Password.getText().toString();
                phone = txt_Phone.getText().toString();
                email = txt_Email.getText().toString();

                if(!txt_Name.equals("") && !txt_Password.equals("") &&  ( !txt_Phone.equals("") || !txt_Email.equals("")  )){
                    if(phone.length() != 10 ){
                        Toast t = Toast.makeText(getApplicationContext(), "Please provide a valid mobile No!", Toast.LENGTH_LONG);
                        t.show();

                    }

                    else if(db.updateCustomerInfo(name, password, phone, email)) {
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





        upload.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);



            }
        });


    }

    private void showData()
    {
        MainActivity mn = new MainActivity();
        db.readLoggedUserInfo(mn.userName);
    }


    private Bitmap imageView2Bitmap(ImageView view){
        Bitmap bitmap = ((BitmapDrawable)view.getDrawable()).getBitmap();
        return bitmap;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, AboutMe.this);
    }
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        MainActivity mn = new MainActivity();
        Log.d(TAG, "Permission has been granted");
        Intent imageReturnedIntent = new Intent();
        Uri selectedImage = imageReturnedIntent.getData();
        String[] filePathColumn = {MediaStore.Images.Media.DATA};

        Cursor cursor = getContentResolver().query(
                selectedImage, filePathColumn, null, null, null);
        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String filePath = cursor.getString(columnIndex);
        cursor.close();

        try{
            yourSelectedImage = BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage));
        } catch(IOException ie) {

        }

        db.addUserDPEntry(mn.userName, DbBitmapUtility.getBytes(yourSelectedImage));

        imageView.setImageBitmap(DbBitmapUtility.getImage( db.retrieveDP(mn.userName)));
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d(TAG, "Permission has been denied");
    }



    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        MainActivity mn = new MainActivity();

        switch(requestCode) {
            case SELECT_PICTURE:
                if(resultCode == RESULT_OK){
                    if(EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)){
                        Uri selectedImage = imageReturnedIntent.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};

                        Cursor cursor = getContentResolver().query(
                                selectedImage, filePathColumn, null, null, null);
                        cursor.moveToFirst();

                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        String filePath = cursor.getString(columnIndex);
                        cursor.close();

                        try{
                            yourSelectedImage = BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage));
                        } catch(IOException ie) {

                        }


                        db.addUserDPEntry(mn.userName, DbBitmapUtility.getBytes(yourSelectedImage));

                        imageView.setImageBitmap(DbBitmapUtility.getImage( db.retrieveDP(mn.userName)));

                    } else {
                        EasyPermissions.requestPermissions(this, getString(R.string.rationale_camera), CAMERA_REQUEST_CODE, Manifest.permission.READ_EXTERNAL_STORAGE);
                    }

                }
        }
    }

}
