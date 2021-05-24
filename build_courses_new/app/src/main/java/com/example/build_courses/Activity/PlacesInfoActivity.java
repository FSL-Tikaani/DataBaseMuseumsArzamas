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

import com.example.build_courses.Adapters.GalleryAdapter;
import com.example.build_courses.MainActivity;
import com.example.build_courses.Models.PlacesModel;
import com.example.build_courses.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PlacesInfoActivity extends AppCompatActivity {

    TextView tv_name, tv_description, tv_address;
    RecyclerView recyclerView_gallery;
    GalleryAdapter galleryAdapter;
    String url_address;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_info);

        init();
        getDataFromDatabase();
    }



    private void installGallery(PlacesModel model){
        ArrayList<String> images = model.getArray_urls_gallery_places();
        recyclerView_gallery.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        galleryAdapter = new GalleryAdapter(images, GalleryAdapter.MODE_HORIZONTAL);
        galleryAdapter.setOnImageClickListener(new GalleryAdapter.OnImageClickListener() {
            @Override
            public void onClick(View v, String url) {
                Intent intent = new Intent(getApplicationContext(), ImageViewerActivity.class);
                intent.putExtra("imageURL", url);
                startActivity(intent);
            }
        });
        recyclerView_gallery.setAdapter(galleryAdapter);
    }





    private void getDataFromDatabase() {
        String placesID = getIntent().getStringExtra("placesID");

        PlacesModel.getPlacesQuery(placesID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    PlacesModel place = ds.getValue(PlacesModel.class);
                    assert place != null;
                    setDataInTextView(place);
                    //Toast.makeText(getApplicationContext(),"Пришло", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void init(){
        setTitle("Достопримечательность");

        tv_name  = findViewById(R.id.tv_places_name);
        tv_description = findViewById(R.id.tv_places_description);
        tv_address = findViewById(R.id.tv_places_address);

        recyclerView_gallery = findViewById(R.id.recyclerView_excursions);
    }

    private void setDataInTextView(PlacesModel placeModel){
        tv_name.setText(placeModel.getName_places());
        tv_description.setText(placeModel.getDescription_places());
        tv_address.setText(placeModel.getAddress_places());

        installGallery(placeModel);
        url_address = placeModel.getUrl_address_places();
    }

    public void openGoogleMapsOnClick(View view){
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse(url_address));
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}