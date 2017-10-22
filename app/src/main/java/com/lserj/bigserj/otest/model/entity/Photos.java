package com.lserj.bigserj.otest.model.entity;


import com.google.gson.annotations.SerializedName;

public class Photos {

    @SerializedName("id")
    private String id;

    @SerializedName("urls")
    private Urls urls = new Urls();




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Urls getUrls() {
        return urls;
    }

    public void setUrls(Urls urls) {
        this.urls = urls;
    }


    public String getThumb() {
        return urls.getThumb();
    }

    public String getRaw() {
        return urls.getRaw();
    }
}
