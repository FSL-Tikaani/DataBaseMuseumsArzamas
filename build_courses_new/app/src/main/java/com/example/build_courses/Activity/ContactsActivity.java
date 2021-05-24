package com.example.build_courses.Activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.build_courses.MainActivity;
import com.example.build_courses.Models.MuseumModel;
import com.example.build_courses.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class ContactsActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts_activity);


        String museumID = getIntent().getStringExtra("museumID");
        getDataFromDataBase(museumID);
    }


    //получаем данные из базы данных
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


    //ставим текст
    private void setupMuseumData(MuseumModel museum) {
        TextView tv_contacts = findViewById(R.id.tv_contacts);
        tv_contacts.setText(museum.getContacts());
    }

}
