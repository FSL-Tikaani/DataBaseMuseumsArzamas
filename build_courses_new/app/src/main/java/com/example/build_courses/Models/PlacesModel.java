package com.example.build_courses.Models;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class PlacesModel {

    String name_places;
    String description_places;
    String address_places;
    String id;
    String url_address_places;
    String url_img_cardView_places;
    ArrayList<String> array_urls_gallery_places;

    public PlacesModel() {
    }

    // Получение объекта Query для конкретного поста по ID
    public static Query getPlacesQuery(String id) {
        // Получаем данные поста из базы данных по ID
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("Places");
        return reference.orderByChild("id").equalTo(id);
    }

    public PlacesModel(String name_places, String description_places, String address_places, String id, String url_address_places, String url_img_cardView_places, ArrayList<String> array_urls_gallery_places) {
        this.name_places = name_places;
        this.description_places = description_places;
        this.address_places = address_places;
        this.id = id;
        this.url_address_places = url_address_places;
        this.url_img_cardView_places = url_img_cardView_places;
        this.array_urls_gallery_places = array_urls_gallery_places;
    }

    public String getName_places() {
        return name_places;
    }

    public void setName_places(String name_places) {
        this.name_places = name_places;
    }

    public String getDescription_places() {
        return description_places;
    }

    public void setDescription_places(String description_places) {
        this.description_places = description_places;
    }

    public String getAddress_places() {
        return address_places;
    }

    public void setAddress_places(String address_places) {
        this.address_places = address_places;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl_address_places() {
        return url_address_places;
    }

    public void setUrl_address_places(String url_address_places) {
        this.url_address_places = url_address_places;
    }

    public String getUrl_img_cardView_places() {
        return url_img_cardView_places;
    }

    public void setUrl_img_cardView_places(String url_img_cardView_places) {
        this.url_img_cardView_places = url_img_cardView_places;
    }

    public ArrayList<String> getArray_urls_gallery_places() {
        return array_urls_gallery_places;
    }

    public void setArray_urls_gallery_places(ArrayList<String> array_urls_gallery_places) {
        this.array_urls_gallery_places = array_urls_gallery_places;
    }
}