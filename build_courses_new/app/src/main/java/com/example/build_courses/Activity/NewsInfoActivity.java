package com.example.build_courses.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.build_courses.Adapters.GalleryAdapter;
import com.example.build_courses.MainActivity;
import com.example.build_courses.Models.MuseumModel;
import com.example.build_courses.Models.NewsModel;
import com.example.build_courses.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NewsInfoActivity extends AppCompatActivity {


    DatabaseReference databaseReference;
    TextView tv_name, tv_museum, tv_description;
    String newsID;
    RecyclerView gallery_view;
    GalleryAdapter galleryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_info);


        init();
        getDataFromDataBase();
    }

    private void getDataFromDataBase() {



        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot :  snapshot.getChildren()){
                    NewsModel newsModel = snapshot.getValue(NewsModel.class);

                    if(newsModel != null){
                        setData(newsModel);
                        setupGallery(newsModel);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        databaseReference.addValueEventListener(valueEventListener);
    }

    private void init(){
        setTitle("Новость");
        newsID = getIntent().getStringExtra("newsID");
        tv_name = findViewById(R.id.tv_name_news);
        tv_museum = findViewById(R.id.tv_name_museum_on_news);
        tv_description = findViewById(R.id.tv_content_news);
        gallery_view = findViewById(R.id.gallery_news);
        databaseReference = FirebaseDatabase.getInstance().getReference("news").child(newsID);


    }

    private void setData(NewsModel newsModel){
        tv_name.setText(newsModel.getTitle());
        tv_museum.setText(newsModel.getName_museum());
        tv_description.setText(newsModel.getContent());

    }

    // Установка галереи
    private void setupGallery(NewsModel newsModel) {
        ArrayList<String> images = newsModel.getImages();
        /*images.add("https://bipbap.ru/wp-content/uploads/2017/05/VOLKI-krasivye-i-ochen-umnye-zhivotnye.jpg");
        images.add("https://klike.net/uploads/posts/2019-05/1556708032_1.jpg");
        images.add("https://klike.net/uploads/posts/2019-05/1556708032_1.jpg");
        images.add("https://vjoy.cc/wp-content/uploads/2020/03/vvvvvvvvvvvvvvmi.jpg");*/
        gallery_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        galleryAdapter = new GalleryAdapter(images, GalleryAdapter.MODE_HORIZONTAL);
        galleryAdapter.setOnImageClickListener(new GalleryAdapter.OnImageClickListener() {
            @Override
            public void onClick(View v, String url) {
                Intent intent = new Intent(getApplicationContext(), ImageViewerActivity.class);
                intent.putExtra("imageURL", url);
                startActivity(intent);
            }
        });
        gallery_view.setAdapter(galleryAdapter);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}