package com.example.build_courses.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.build_courses.Adapters.GalleryAdapter;
import com.example.build_courses.Models.ExcursionModel;
import com.example.build_courses.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ExcursionInfoActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    RecyclerView gallery_excursion;
    GalleryAdapter galleryAdapter;
    TextView tv_name, tv_them, tv_time, tv_description;

    String phone_number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excursion_info);


        init();
        getDataFromDataBase();

    }

    private void getDataFromDataBase() {
        String excursionID = getIntent().getStringExtra("ExcursionID");
        ExcursionModel.getExcursionQuery(excursionID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    ExcursionModel excursion = ds.getValue(ExcursionModel.class);
                    if(excursion != null){
                        Toast.makeText(getApplicationContext(), "Пришло", Toast.LENGTH_SHORT).show();
                        setData(excursion);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void installGallery(ExcursionModel model){
        ArrayList<String> array_urls = model.getArray_urls_gallery();

        gallery_excursion.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        galleryAdapter = new GalleryAdapter(array_urls, GalleryAdapter.MODE_HORIZONTAL);
        galleryAdapter.setOnImageClickListener(new GalleryAdapter.OnImageClickListener() {
            @Override
            public void onClick(View v, String url) {
                Intent intent = new Intent(getApplicationContext(), ImageViewerActivity.class);
                intent.putExtra("imageURL", url);
                startActivity(intent);
            }
        });
        gallery_excursion.setAdapter(galleryAdapter);
    }

    private void setData(ExcursionModel excursionModel){
        tv_name.setText(excursionModel.getName());
        tv_them.setText(excursionModel.getThem());
        tv_time.setText(excursionModel.getTime());
        tv_description.setText(excursionModel.getDescription());
        phone_number = excursionModel.getPhone_number();
        installGallery(excursionModel);
    }

    private void init() {
        setTitle("Экскурсия");

        tv_name = findViewById(R.id.tv_name_excursion);
        tv_them = findViewById(R.id.tv_them);
        tv_time = findViewById(R.id.tv_time);
        tv_description = findViewById(R.id.tv_description);
        //firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("Excursions");
        //gallery
        gallery_excursion = findViewById(R.id.gallery_excursions);

        String excursionID = getIntent().getStringExtra("ExcursionID");
        Toast.makeText(this, excursionID, Toast.LENGTH_SHORT).show();
    }

    public void go_excursionOnClick(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + phone_number));
        startActivity(intent);
    }
}