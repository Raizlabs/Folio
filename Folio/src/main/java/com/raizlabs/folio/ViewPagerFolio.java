package com.raizlabs.folio;

import android.support.v4.view.ViewPager;

/**
 * Class which defines a Folio which displays the state of a {@link ViewPager}.
 */
public interface ViewPagerFolio {

    /**
     * Binds this Folio to the given {@link ViewPager}, displaying its state.
     * @param viewPager The ViewPager to show the state of.
     */
    void setViewPager(ViewPager viewPager);
}
