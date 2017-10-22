package com.lserj.bigserj.otest.model.entity;

import com.google.gson.annotations.SerializedName;

public class JsonPlaceHolder {


    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("phone")
    private String phone;

    @SerializedName("website")
    private String website;

    public Photos getPhotos() {
        return photos;
    }



    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    private Photos photos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
