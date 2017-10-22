package com.lserj.bigserj.otest.model;


import com.lserj.bigserj.otest.model.entity.JsonPlaceHolder;
import com.lserj.bigserj.otest.model.entity.Photos;

import java.util.List;

import io.reactivex.Observable;

public interface Model {

    Observable<List<JsonPlaceHolder>> getAllObjects();
    Observable<List<Photos>> getAllPhotos();


    
}
