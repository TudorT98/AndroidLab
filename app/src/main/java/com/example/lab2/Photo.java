package com.example.lab2;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class Photo extends AppCompatActivity {

    ImageView photo;
    Button takePhotoButton;
    String pathToFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        photo = findViewById(R.id.imageView);
        takePhotoButton = findViewById(R.id.CameraButton);
        if(Build.VERSION.SDK_INT >=23)
        {
            requestPermissions(new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE},2);
        }
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispacthPictureTakerAction();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if (requestCode == 1){
                Bitmap bitmap = BitmapFactory.decodeFile(pathToFile);
                photo.setImageBitmap(bitmap);
            }
        }
    }

    private void dispacthPictureTakerAction(){

        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePicture.resolveActivity(getPackageManager()) !=null )
        {
            File photoFile = null;
            photoFile = createPhotoFile();
            if(photoFile != null) {
                Log.d("log","PhotoFileNotEmpty");
                pathToFile = photoFile.getAbsolutePath();
                Uri photoUri = FileProvider.getUriForFile(Photo.this,"com.mydomain.fileprovider",photoFile);
                takePicture.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
                startActivityForResult(takePicture,1);
            }
            else {
                Log.d("Log","photoFileNull");
            }

        }
    }

    private File createPhotoFile() {
            String name = new SimpleDateFormat( "yyyyMMdd_HHmmss").format(new Date());
            File storageDir = getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            File image = null;
            try {
                 image = File.createTempFile(name, ".jpg", storageDir);
            }catch (IOException e)
            {
                e.printStackTrace();
            }
            return image;
    }


}
