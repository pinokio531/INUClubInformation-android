package com.ourincheon.app_center.mainActivity.Setting.ModifyClubInformation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;
import com.ourincheon.app_center.R;
import com.ourincheon.app_center.application.NetworkController;
import com.ourincheon.app_center.model.ErrorMsgResult;

import java.io.File;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by SanJuku on 2018-02-22.
 */

public class ModifyPhoto extends Activity {

    private final int GALLERY_CODE=1112;

    private ImageButton first_photo;
    private ImageButton second_photo;
    private ImageButton third_photo;
    private ImageButton fourth_photo;
    String baseUrl = "http://inuclub.us.to:3303/";
    String imageUrl;
    String image1, image2, image3, image4 = null;

    private int imagenum = 0;
    private int clubnum;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_photo);


        Intent intent = getIntent();
        String number = intent.getStringExtra("clubIdNumber");
        clubnum = Integer.parseInt(number);



        first_photo = (ImageButton) findViewById(R.id.imageButton);
        second_photo = (ImageButton) findViewById(R.id.imageButton2);
        third_photo = (ImageButton) findViewById(R.id.imageButton3);
        fourth_photo = (ImageButton) findViewById(R.id.imageButton4);

        Call<ArrayList<JsonObject>> call = NetworkController.getInstance().getNetworkInterface().getDetailInformation(clubnum);
        call.enqueue(new Callback<ArrayList<JsonObject>>() {
            @Override
            public void onResponse(Call<ArrayList<JsonObject>> call, Response<ArrayList<JsonObject>> response) {
                image1 = response.body().get(0).getAsJsonObject().get("image1").toString().replace("\"", "");
                image2 = response.body().get(0).getAsJsonObject().get("image2").toString().replace("\"", "");
                image3 = response.body().get(0).getAsJsonObject().get("image3").toString().replace("\"", "");
                image4 = response.body().get(0).getAsJsonObject().get("image4").toString().replace("\"", "");

                selectedImage();

            }

            @Override
            public void onFailure(Call<ArrayList<JsonObject>> call, Throwable t) {

            }
        });

        first_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagenum = 1;
                selectGallery();
            }
        });

        second_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagenum = 2;
                selectGallery();
            }
        });

        third_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagenum = 3;
                selectGallery();
            }
        });

        fourth_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagenum = 4;
                selectGallery();
            }
        });

        Button bt_finish = (Button) findViewById(R.id.buttonFinish);
        bt_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Toast.makeText(ModifyPhoto.this, "사진이 변경되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void selectedImage(){
        if(image1.length() < 5){
            first_photo.setImageResource(R.drawable.photoplus);
        }
        else{
            imageUrl = baseUrl + image1;
            Glide.with(ModifyPhoto.this).load(imageUrl).into(first_photo);
        }


        if(image2.length() < 5){
            second_photo.setImageResource(R.drawable.photoplus);
        }
        else{
            imageUrl = baseUrl + image2;
            Glide.with(ModifyPhoto.this).load(imageUrl).into(second_photo);
        }

        if(image3.length() < 5){
            third_photo.setImageResource(R.drawable.photoplus);
        }
        else{
            imageUrl = baseUrl + image3;
            Glide.with(ModifyPhoto.this).load(imageUrl).into(third_photo);
        }

        if(image4.length() < 5){
            fourth_photo.setImageResource(R.drawable.photoplus);
        }
        else{
            imageUrl = baseUrl + image4;
            Glide.with(ModifyPhoto.this).load(imageUrl).into(fourth_photo);
        }
    }

    private void selectGallery() {

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("*/*");
        startActivityForResult(intent, GALLERY_CODE);
        Log.d("갤러리 선택", "성공");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            switch (requestCode) {

                case GALLERY_CODE:
                    sendPicture(data.getData());
                    Log.d("갤러리 클릭", "성공");
                    break;

                default:
                    break;
            }

        }
    }

    @SuppressLint("ResourceAsColor")
    private void sendPicture(Uri imgUri) {

        String imagePath = getRealPathFromURI(imgUri);

        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);

        UploadImages(imagePath);

        switch (imagenum){
            case 1:
                first_photo.setImageBitmap(bitmap);
                break;
            case 2:
                second_photo.setImageBitmap(bitmap);
                break;
            case 3:
                third_photo.setImageBitmap(bitmap);
                break;
            case 4:
                fourth_photo.setImageBitmap(bitmap);
                break;
        }

        Log.d("갤러리 경로 지정", "성공");


    }

    private String getRealPathFromURI(Uri contentUri) {
        int column_index=0;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if(cursor.moveToFirst()){
            column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        }

        return cursor.getString(column_index);
    }

    public void UploadImages(String filePath){

        File file = new File(filePath);

        RequestBody userfile = RequestBody.create(MediaType.parse("image/*"), file);

        MultipartBody.Part body =MultipartBody.Part.createFormData("userfile", file.getName(), userfile);

        Call<ErrorMsgResult> call = NetworkController.getInstance().getNetworkInterface().imageUpload(clubnum, imagenum, userfile, body);
        call.enqueue(new Callback<ErrorMsgResult>() {
            @Override
            public void onResponse(Call<ErrorMsgResult> call, Response<ErrorMsgResult> response) {

                Log.d("이미지 업로드", "성공");
                Log.d("이미지 업로드 성공 안됨", String.valueOf(response.code()));

            }

            @Override
            public void onFailure(Call<ErrorMsgResult> call, Throwable t) {
                Log.d("이미지 업로드", "성공실패");
            }
        });
    }

}
