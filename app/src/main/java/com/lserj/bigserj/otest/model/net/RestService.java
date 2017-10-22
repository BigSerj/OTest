package com.lserj.bigserj.otest.model.net;


import com.lserj.bigserj.otest.model.entity.JsonPlaceHolder;
import com.lserj.bigserj.otest.model.entity.Photos;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestService {

    private static final RestService instance = new RestService();

    private RestApi restApiGetObjects;
    private RestApi restApiGetImages;

    public static RestService getInstance() {
        return instance;
    }

    private RestService() {
        init();
    }




    private void init() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build();


        Retrofit retrofitGetObjects = new Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        restApiGetObjects = retrofitGetObjects.create(RestApi.class);


        Retrofit retrofitGetImages = new Retrofit.Builder()
                .baseUrl("https://api.unsplash.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        restApiGetImages = retrofitGetImages.create(RestApi.class);

    }




    public Observable<List<JsonPlaceHolder>> getJsonPlaceHolder(){
        return restApiGetObjects.getJsonPlaceHolder();
    }

    public Observable<List<Photos>> getImagesList(){
        return restApiGetImages.getImagesList();
    }

}