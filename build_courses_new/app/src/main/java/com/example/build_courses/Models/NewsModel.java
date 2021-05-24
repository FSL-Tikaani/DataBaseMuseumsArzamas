package com.example.build_courses.Models;

import java.util.ArrayList;

public class NewsModel {
    String title;
    String content;
    String name_museum;
    String id;
    String url_img_cardView;
    ArrayList<String> images;

    public NewsModel() {
    }

    public NewsModel(String title, String content, String name_museum, String id, String url_img_cardView, ArrayList<String> images) {
        this.title = title;
        this.content = content;
        this.name_museum = name_museum;
        this.id = id;
        this.url_img_cardView = url_img_cardView;
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName_museum() {
        return name_museum;
    }

    public void setName_museum(String name_museum) {
        this.name_museum = name_museum;
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

    public void setUrl_img_cardView(String url_img_catdView) {
        this.url_img_cardView = url_img_catdView;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }
}
