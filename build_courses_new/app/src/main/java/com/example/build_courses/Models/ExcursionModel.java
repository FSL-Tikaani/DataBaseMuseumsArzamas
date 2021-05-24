package com.example.build_courses.Models;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class ExcursionModel {
    String name;
    String them;
    String time;
    String description;
    String phone_number;
    String id;
    String url_img_cardView;
    String id_museum;
    ArrayList<String> array_urls_gallery;

    public static Query getExcursionQuery(String id) {
        // Получаем данные поста из базы данных по ID
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("Excursions");
        return reference.orderByChild("id").equalTo(id);
    }

    public ExcursionModel() {
    }

    public ExcursionModel(String name, String them, String time, String description, String phone_number, String id, String url_img_cardView, String id_museum, ArrayList<String> array_urls_gallery) {
        this.name = name;
        this.them = them;
        this.time = time;
        this.description = description;
        this.phone_number = phone_number;
        this.id = id;
        this.url_img_cardView = url_img_cardView;
        this.id_museum = id_museum;
        this.array_urls_gallery = array_urls_gallery;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThem() {
        return them;
    }

    public void setThem(String them) {
        this.them = them;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl_img_cardView() {
        return url_img_cardView;
    }

    public void setUrl_img_cardView(String url_img_cardView) {
        this.url_img_cardView = url_img_cardView;
    }

    public ArrayList<String> getArray_urls_gallery() {
        return array_urls_gallery;
    }

    public void setArray_urls_gallery(ArrayList<String> array_urls_gallery) {
        this.array_urls_gallery = array_urls_gallery;
    }
}
