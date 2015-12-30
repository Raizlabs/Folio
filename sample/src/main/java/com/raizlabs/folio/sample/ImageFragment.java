package com.raizlabs.folio.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageFragment extends Fragment {

    private static final String KEY_URL = "url";

    public static ImageFragment newInstance(String photoUrl) {
        ImageFragment fragment = new ImageFragment();
        fragment.setPhotoUrl(photoUrl);
        return fragment;
    }

    private ImageView imageView;
    private String photoUrl;

    public ImageFragment() {
        setArguments(new Bundle());
    }

    void setPhotoUrl(String url) {
        getArguments().putString(KEY_URL, url);
    }

    String getPhotoUrl() {
        return getArguments().getString(KEY_URL);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_image, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageView = (ImageView) view.findViewById(R.id.fragment_image_image);

        Glide.with(this).load(getPhotoUrl()).into(imageView);
    }
}
