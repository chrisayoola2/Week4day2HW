package com.example.week4day2hw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ImageActivity extends AppCompatActivity {
    ImageView ivImageView;
//    List<Item> listOfItems;
//    private int position;
//    public ImageActivity(List items, int position){
//        this.listOfItems = items;
//        this.position = position;

    //}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        ivImageView = findViewById(R.id.ivImageView);
        Intent recievedIntent = getIntent();
        Bundle bundle = recievedIntent.getExtras();
        String myUrl = bundle.getString("imageurl");
        Glide.with(ivImageView).load(myUrl).into(ivImageView);
    }
}
