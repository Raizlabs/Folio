package com.raizlabs.folio;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * {@link ViewPagerFolio} implementation which simply shows the {@link ViewPager}'s current page out of the total pages.
 */
public class SimpleTextFolio extends TextView implements ViewPagerFolio {

    private ViewPager viewPager;

    public SimpleTextFolio(Context context) {
        super(context);
    }

    public SimpleTextFolio(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SimpleTextFolio(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SimpleTextFolio(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void setViewPager(ViewPager viewPager) {
        if (this.viewPager != null) {
            this.viewPager.removeOnPageChangeListener(pageChangeListener);
        }

        this.viewPager = viewPager;
        viewPager.addOnPageChangeListener(pageChangeListener);
        setSelectedPage(viewPager.getCurrentItem());
    }

    void setSelectedPage(int position) {
        if (viewPager != null) {
            PagerAdapter adapter = viewPager.getAdapter();
            if (adapter != null) {
                setText(String.format("%d/%d", position + 1, adapter.getCount()));
            }
        }
    }

    private final ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            setSelectedPage(position);
        }

        @Override
        public void onPageSelected(int position) {
            setSelectedPage(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
