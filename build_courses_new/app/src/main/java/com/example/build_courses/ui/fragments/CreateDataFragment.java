package com.example.build_courses.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.build_courses.Models.ExcursionModel;
import com.example.build_courses.Models.MuseumModel;
import com.example.build_courses.Models.NewsModel;
import com.example.build_courses.Models.PlacesModel;
import com.example.build_courses.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CreateDataFragment extends Fragment {

    EditText et_name, et_address, et_description, et_contacts, et_time_work, et_id;
    Button button, button2, button3, button4, button5;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_create_data, container, false);


        init(root);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                String MuseumId;
                DatabaseReference reference = database.getReference("Museums");
                DatabaseReference keyReference = reference.push();
                MuseumId = keyReference.getKey();

                ArrayList<String> arrayList = new ArrayList<>();
                ArrayList<String> array_excursions = new ArrayList<>();

                array_excursions.add("-MaN1xPSLEx5RkeXHv3O");
                array_excursions.add("-MaN5ORvSWCpVNIeEXUS");

                arrayList.add("https://klike.net/uploads/posts/2019-05/1556708032_1.jpg");
                arrayList.add("https://klike.net/uploads/posts/2019-05/1556708032_1.jpg");

                MuseumModel museumModel = new MuseumModel("", et_name.getText().toString(), et_address.getText().toString(),
                        et_description.getText().toString(), et_contacts.getText().toString(), et_time_work.getText().toString(),
                        MuseumId, "", arrayList, array_excursions);

                keyReference.setValue(museumModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.i("MyApp", "Данные отправлены");
                        Toast.makeText(getContext(), "Норм", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("MyApp", "Данные не отправлены!");
                        Toast.makeText(getContext(), "error!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });




        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                String PlacesId;
                DatabaseReference reference = database.getReference("Places");
                DatabaseReference keyReference = reference.push();
                PlacesId = keyReference.getKey();

                ArrayList<String> arrayList_urls_gallery = new ArrayList<>();

                PlacesModel placesModel = new PlacesModel("Place 1", "Description1", "Address1", PlacesId, "test_url_address", "",  arrayList_urls_gallery);

                keyReference.setValue(placesModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.i("MyApp", "Данные отправлены");
                        Toast.makeText(getContext(), "Норм", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("MyApp", "Данные не отправлены!");
                        Toast.makeText(getContext(), "error!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });



        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                String ExcursionID;
                DatabaseReference reference = database.getReference("Excursions");
                DatabaseReference keyReference = reference.push();
                ExcursionID = keyReference.getKey();

                ArrayList<String> arrayList_urls_gallery = new ArrayList<>();

                ExcursionModel excursionModel = new ExcursionModel("name1", "them", "time", "description", "+799999999999", ExcursionID, "", "1", arrayList_urls_gallery );

                keyReference.setValue(excursionModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.i("MyApp", "Данные отправлены");
                        Toast.makeText(getContext(), "Норм", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("MyApp", "Данные не отправлены!");
                        Toast.makeText(getContext(), "error!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                String NewsID;
                DatabaseReference reference = database.getReference("News");
                DatabaseReference keyReference = reference.push();
                NewsID = keyReference.getKey();

                ArrayList<String> arrayList_urls_gallery = new ArrayList<>();
                arrayList_urls_gallery.add("test");

                NewsModel newsModel = new NewsModel("news_titile" ,"News_content", "name_museum", NewsID," ", arrayList_urls_gallery);

                keyReference.setValue(newsModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.i("MyApp", "Данные отправлены");
                        Toast.makeText(getContext(), "Норм", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("MyApp", "Данные не отправлены!");
                        Toast.makeText(getContext(), "error!", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });






        return root;
    }




    private void init(View root){
        et_name = root.findViewById(R.id.et_name);
        et_address = root.findViewById(R.id.et_address);
        et_description = root.findViewById(R.id.et_description);
        et_contacts = root.findViewById(R.id.et_contacts);
        et_time_work = root.findViewById(R.id.et_work_time);
        button = root.findViewById(R.id.btn_save);
        button2 = root.findViewById(R.id.btn_sendData);
        button3 = root.findViewById(R.id.btn_create_excursion);
        button4 = root.findViewById(R.id.button4);
        button5 = root.findViewById(R.id.btn_create_news);
    }






}
