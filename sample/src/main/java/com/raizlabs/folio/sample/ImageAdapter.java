package com.raizlabs.folio.sample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

class ImageAdapter extends FragmentStatePagerAdapter {

    private String[] urls;

    public ImageAdapter(FragmentManager fm, String[] urls) {
        super(fm);
        this.urls = urls;
    }

    @Override
    public Fragment getItem(int position) {
        return ImageFragment.newInstance(urls[position]);
    }

    @Override
    public int getCount() {
        return urls.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Item " + position;
    }
}
