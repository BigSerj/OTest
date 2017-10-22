package com.lserj.bigserj.otest.model.net;


import com.lserj.bigserj.otest.model.entity.JsonPlaceHolder;
import com.lserj.bigserj.otest.model.entity.Photos;

import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RestApi {

    @GET("users")
    Observable<List<JsonPlaceHolder>> getJsonPlaceHolder();

    @GET("photos/?client_id=813ec579be42fbbdf3118b724e54299ac9d2148a028fd0f1e7e6bd7d4c03d122")
    Observable<List<Photos>> getImagesList();



}
