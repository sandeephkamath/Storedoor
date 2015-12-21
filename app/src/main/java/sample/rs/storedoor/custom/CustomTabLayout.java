/*
 * Copyright © 2015, 9x Media Pvt. Ltd.
 * Written under contract by Robosoft Technologies Pvt. Ltd.
 */

package sample.rs.storedoor.custom;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.ViewGroup;

import sample.rs.storedoor.R;

/**
 * Created by nagesh on 21/11/15.
 */
public class CustomTabLayout extends TabLayout {
    private Context mContext;

    public CustomTabLayout(Context context) {
        super(context);
        mContext = context;
    }

    public CustomTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public CustomTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    @Override
    public void setTabsFromPagerAdapter(@NonNull PagerAdapter adapter) {

        this.removeAllTabs();

        ViewGroup slidingTabStrip = (ViewGroup) getChildAt(0);

        for (int i = 0, count = adapter.getCount(); i < count; i++) {
            Tab tab = this.newTab();
            this.addTab(tab.setText(adapter.getPageTitle(i)));
            AppCompatTextView view = (AppCompatTextView) ((ViewGroup) slidingTabStrip.getChildAt(i)).getChildAt(1);
            view.setTextColor(ContextCompat.getColor(mContext, R.color.white));
        }
    }
}
