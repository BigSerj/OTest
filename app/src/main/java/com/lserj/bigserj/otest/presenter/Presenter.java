package com.lserj.bigserj.otest.presenter;


import android.support.v4.app.FragmentManager;

import com.lserj.bigserj.otest.R;
import com.lserj.bigserj.otest.model.ModelImpl;
import com.lserj.bigserj.otest.model.entity.JsonPlaceHolder;
import com.lserj.bigserj.otest.model.entity.Photos;
import com.lserj.bigserj.otest.view.DetailsFragment;
import com.lserj.bigserj.otest.view.MainActivity;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class Presenter {

    private ModelImpl model;
    private MainActivity view;
    private List<JsonPlaceHolder> jsonPlaceHoldersLast;

    private Disposable disposableObjects;
    private Disposable disposablePhotos;

    public Presenter(ModelImpl model) {
        this.model = model;
    }

    public void viewIsReady() {
        loadObjects();
    }

    private void loadObjects() {

        disposableObjects = model.getAllObjects()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribeWith(new DisposableObserver<List<JsonPlaceHolder>>() {
                    @Override
                    public void onNext(@NonNull final List<JsonPlaceHolder> jsonPlaceHolders) {

                        disposablePhotos = model.getAllPhotos()
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.newThread())
                                .subscribeWith(new DisposableObserver<List<Photos>>() {
                                    @Override
                                    public void onNext(@NonNull List<Photos> photoses) {

                                        for (int i = 0; i < jsonPlaceHolders.size(); i++) {
                                            if (i == photoses.size())
                                                break;
                                            jsonPlaceHolders.get(i).setPhotos(photoses.get(i));
                                        }
                                        jsonPlaceHoldersLast = jsonPlaceHolders;
                                        view.showObjects(jsonPlaceHolders);
                                    }

                                    @Override
                                    public void onError(@NonNull Throwable e) {
                                    }

                                    @Override
                                    public void onComplete() {
                                    }
                                });
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    public void attachView(MainActivity activity) {
        view = activity;
    }

    public void detachView() {
        disposableObjects.dispose();
        disposablePhotos.dispose();
        view = null;
    }

    public void onClickItem(Integer item, FragmentManager supportFragmentManager) {
        if (jsonPlaceHoldersLast != null && supportFragmentManager.getBackStackEntryCount()==0) {
            JsonPlaceHolder jsonPlaceHolder = jsonPlaceHoldersLast.get(item);
            MainActivity.showFragment(supportFragmentManager,
                    DetailsFragment.newInstance(supportFragmentManager,
                            jsonPlaceHolder.getPhotos().getUrls().getRaw(),
                            jsonPlaceHolder.getId(),
                            jsonPlaceHolder.getName(),
                            jsonPlaceHolder.getPhone(),
                            jsonPlaceHolder.getWebsite()),
                    true, R.id.details_fragment);
        }
    }


}
