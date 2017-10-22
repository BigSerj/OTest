package com.lserj.bigserj.otest.model;


import com.lserj.bigserj.otest.model.entity.JsonPlaceHolder;
import com.lserj.bigserj.otest.model.entity.Photos;
import com.lserj.bigserj.otest.model.net.RestService;

import java.util.List;

import io.reactivex.Observable;

public class ModelImpl implements Model {


    @Override
    public Observable<List<JsonPlaceHolder>> getAllObjects() {
        return RestService
                .getInstance()
                .getJsonPlaceHolder();
    }

    @Override
    public Observable<List<Photos>> getAllPhotos() {
        return RestService
                .getInstance()
                .getImagesList();
    }
}
