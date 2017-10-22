package com.lserj.bigserj.otest.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lserj.bigserj.otest.R;
import com.lserj.bigserj.otest.model.ModelImpl;
import com.lserj.bigserj.otest.model.entity.JsonPlaceHolder;
import com.lserj.bigserj.otest.presenter.Presenter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerViewAdapter recyclerViewAdapter;
    private Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        recyclerViewAdapter.setListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Integer item) {
                presenter.onClickItem(item, getSupportFragmentManager());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }


    private void init(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        GridLayoutManager gm = new GridLayoutManager(this, 1);
        gm.canScrollHorizontally();
        recyclerView.setLayoutManager(gm);

        recyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<JsonPlaceHolder>());
        recyclerView.setAdapter(recyclerViewAdapter);

        ModelImpl model = new ModelImpl();
        presenter = new Presenter(model);
        presenter.attachView(this);
        presenter.viewIsReady();
    }

    public void showObjects(List<JsonPlaceHolder> objects) {
        recyclerViewAdapter.swap(objects);
    }

    public static void showFragment(FragmentManager fragmentManager, Fragment fragment,
                                    boolean addToBackStack, int layout) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(layout, fragment, fragment.getClass().getName());
        if (addToBackStack) fragmentTransaction.addToBackStack(null);
        fragmentTransaction.setTransition(2).commit();
    }



}
