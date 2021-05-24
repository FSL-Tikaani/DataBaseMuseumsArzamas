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

import com.example.build_courses.Adapters.ExcursionsAdapter;
import com.example.build_courses.Adapters.GalleryAdapter;
import com.example.build_courses.MainActivity;
import com.example.build_courses.Models.ExcursionModel;
import com.example.build_courses.Models.MuseumModel;
import com.example.build_courses.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MuseumInfoActivity extends AppCompatActivity {

    TextView tv_info_name_museum;
    TextView tv_info_description;
    TextView tv_info_contacts;
    TextView tv_info_address;
    TextView tv_info_work_time;
    RecyclerView gallery_view, excursionRecyclerView;
    GalleryAdapter galleryAdapter;
    DatabaseReference databaseReference;
    ExcursionsAdapter excursionsAdapter;
    String museumID;
    String url_maps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museum_info);

        init();
        getDataFromDataBase();
        initializeAdapter();
        initializeRecyclerView();

    }

    private void initializeRecyclerView() {
        excursionRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        excursionRecyclerView.setAdapter(excursionsAdapter);
    }

    private void initializeAdapter() {
        ArrayList<ExcursionModel> excursions = new ArrayList<>();

        excursionsAdapter = new ExcursionsAdapter(new ArrayList<>());

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    ExcursionModel model = ds.getValue(ExcursionModel.class);

                    if(model != null){
                        excursions.add(model);
                        excursionsAdapter.setDataSetExcursion(excursions);

                        excursionsAdapter.setExcursionOnClickListener(new ExcursionsAdapter.ExcursionOnClickListener() {
                            @Override
                            public void ExcursionOnExtrasClick(View view, String excursionID) {
                                Intent intent = new Intent(getApplicationContext(), ExcursionInfoActivity.class);
                                intent.putExtra("ExcursionID", excursionID);
                                startActivity(intent);
                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        //выбираем только подходящие экскурсии
        Query query = databaseReference.orderByChild("id_museum").equalTo(museumID);
        query.addValueEventListener(valueEventListener);

    }

    // Установка галереи
    private void setupGallery(MuseumModel museumModel) {
        ArrayList<String> images = museumModel.getArrayList();
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





    //при нажатии на адрес будет перекидывать на google карты

    public void onClickStartGoogleMaps(View view){
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse(url_maps));
        startActivity(intent);
    }


    //получаем данные с бд
    private void getDataFromDataBase(){
        MuseumModel.getMuseumQuery(museumID).addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            MuseumModel museum = ds.getValue(MuseumModel.class);
                            setDataInTextView(museum);
                            //Toast.makeText(getApplicationContext(),"Пришло", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                }
        );
    }

    //инициализация
    private void init(){

        setTitle("Музей");
        //text view
        museumID = getIntent().getStringExtra("museumID");
        tv_info_name_museum = findViewById(R.id.tv_info_name_museum);
        tv_info_description = findViewById(R.id.tv_info_museum_description);
        tv_info_contacts = findViewById(R.id.tv_info_museum_contacts);
        tv_info_address = findViewById(R.id.tv_info_museum_address);
        tv_info_work_time = findViewById(R.id.tv_info_museum_work_time);
        gallery_view = findViewById(R.id.gallery_museums);



        //recycler
        excursionRecyclerView = findViewById(R.id.recyclerView_excursions);

        databaseReference = FirebaseDatabase.getInstance().getReference("Excursions");
    }

    //ставим данные в text_view
    private void setDataInTextView(MuseumModel museumModel){
        tv_info_name_museum.setText(museumModel.getName());
        tv_info_description.setText(museumModel.getDescription());
        tv_info_contacts.setText(museumModel.getContacts());
        tv_info_address.setText(museumModel.getAddress());
        tv_info_work_time.setText(museumModel.getWork_time());


        url_maps = museumModel.getUrl_address();
        setupGallery(museumModel);

    }


    //колхоз, по другому не роботатет
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}