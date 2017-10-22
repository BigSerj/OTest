package com.lserj.bigserj.otest.view;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.lserj.bigserj.otest.R;

import static com.lserj.bigserj.otest.model.Constants.*;


public class DetailsFragment extends Fragment {

    private String url;
    private String id;
    private String name;
    private String phone;
    private String website;


    public static DetailsFragment newInstance(FragmentManager manager, String url, int id,
                                              String name, String phone, String website) {

        Fragment fragment = manager.findFragmentByTag(DetailsFragment.class.getName());
        DetailsFragment detailsFragment;

        if (fragment != null && fragment instanceof DetailsFragment)
            detailsFragment = (DetailsFragment) fragment;
        else {
            detailsFragment = new DetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putString(URL,url);
            bundle.putString(ID,String.valueOf(id));
            bundle.putString(NAME,name);
            bundle.putString(PHONE,phone);
            bundle.putString(WEB_SITE,website);
            detailsFragment.setArguments(bundle);
        }
        return detailsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle != null) {
            url = bundle.getString(URL);
            id = bundle.getString(ID);
            name = bundle.getString(NAME);
            phone = bundle.getString(PHONE);
            website = bundle.getString(WEB_SITE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        set(view);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }



    private void set(View view){

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressImageView);

        Glide.with(view.getContext())
                .load(url)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                                Target<Drawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model,
                                                   Target<Drawable> target, DataSource dataSource,
                                                   boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(imageView);

        TextView textId = (TextView) view.findViewById(R.id.textId);
        TextView textName = (TextView) view.findViewById(R.id.textName);
        TextView textPhone = (TextView) view.findViewById(R.id.textPhone);
        TextView textWeb = (TextView) view.findViewById(R.id.textWeb);

        textId.setText(id);
        textName.setText(name);
        textPhone.setText(phone);
        textWeb.setText(website);


    }

}
