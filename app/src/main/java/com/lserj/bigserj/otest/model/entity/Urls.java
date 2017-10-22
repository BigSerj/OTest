package com.lserj.bigserj.otest.model.entity;


import com.google.gson.annotations.SerializedName;

public class Urls {

    @SerializedName("raw")
    private String raw;

    @SerializedName("thumb")
    private String thumb;



    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }
}
