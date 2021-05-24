package com.example.build_courses.Models;

import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class MuseumModel {
    String backgroundImageURL;      //путь к background card view
    String name;                    //название музея
    String address;                 //адресс музея
    String description;             //описание музея
    String contacts;                //контакты музея
    String work_time;               //время работы
    String id;                      //id ( генерируется автоматически)
    String url_address;             //путь для открытия Google карт
    ArrayList<String> arrayList;


    //model
    public MuseumModel(String backgroundImageURL, String name, String address, String description, String contacts, String work_time, String id, String url_address, ArrayList<String> arrayList, ArrayList<String> array_excursions) {
        this.backgroundImageURL = backgroundImageURL;
        this.name = name;
        this.address = address;
        this.description = description;
        this.contacts = contacts;
        this.work_time = work_time;
        this.id = id;
        this.url_address = url_address;
        this.arrayList = arrayList;
    }

    public MuseumModel() {}

    // Получение объекта Query для конкретного поста по ID
    public static Query getMuseumQuery(String id) {
        // Получаем данные поста из базы данных по ID
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("Museums");
        return reference.orderByChild("id").equalTo(id);
    }


    public ArrayList<String> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    public String getUrl_address() {
        return url_address;
    }

    public void setUrl_address(String url_address) {
        this.url_address = url_address;
    }

    public void setWork_time(String work_time) {
        this.work_time = work_time;
    }

    public String getWork_time() {
        return work_time;
    }

    public String getBackgroundImageURL() {
        return backgroundImageURL;
    }

    public void setBackgroundImageURL(String backgroundImageURL) {
        this.backgroundImageURL = backgroundImageURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
