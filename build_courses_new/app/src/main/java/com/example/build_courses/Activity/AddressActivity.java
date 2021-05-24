package com.example.build_courses.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.build_courses.MainActivity;
import com.example.build_courses.Models.MuseumModel;
import com.example.build_courses.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class AddressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        String museumID = getIntent().getStringExtra("museumID");

        getDataFromDataBase(museumID);

    }

    //вытаскиваем данные из бд
    private void getDataFromDataBase(String ID){
        MuseumModel.getMuseumQuery(ID).addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            MuseumModel museum = ds.getValue(MuseumModel.class);
                            assert museum != null;
                            setupMuseumData(museum);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                }
        );
    }

    private void setupMuseumData(MuseumModel museum) {
        TextView address_info = findViewById(R.id.address_info);
        address_info.setText(museum.getAddress());
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
}