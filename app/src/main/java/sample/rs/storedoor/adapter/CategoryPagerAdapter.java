package sample.rs.storedoor.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import sample.rs.storedoor.fragments.ProductListFragment;
import sample.rs.storedoor.models.Category;
import sample.rs.storedoor.util.StringUtil;

public class CategoryPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Category> mCategories;
    private ProductListAdapter.ProductSelectListener mListener;

    public CategoryPagerAdapter(FragmentManager fragmentManager, Category parentCategory, ProductListAdapter.ProductSelectListener listener) {
        super(fragmentManager);
        mCategories = parentCategory.getCategories();
        mListener = listener;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        fragment = ProductListFragment.newInstance(mCategories.get(position), false, mListener);
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return StringUtil.removeAmp(mCategories.get(position).getName());
    }

    @Override
    public int getCount() {
        return mCategories.size();
    }
}
