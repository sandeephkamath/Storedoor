package sample.rs.storedoor.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import sample.rs.storedoor.R;
import sample.rs.storedoor.adapter.CategoryPagerAdapter;
import sample.rs.storedoor.adapter.ProductListAdapter;
import sample.rs.storedoor.custom.CustomTabLayout;
import sample.rs.storedoor.models.Category;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends BaseFragment {

    private static final String ARG_CATEGORY = "arg_category";
    private CustomTabLayout mTabLayout;
    private Category mCategory;
    private CategoryPagerAdapter mCategoryPagerAdapter;
    private static ProductListAdapter.ProductSelectListener mListener;
    private ViewPager mCategoryPager;

    public CategoryFragment() {
        // Required empty public constructor
    }

    public static CategoryFragment newInstance(Category category, ProductListAdapter.ProductSelectListener listener) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_CATEGORY, category);
        CategoryFragment categoryFragment = new CategoryFragment();
        mListener = listener;
        categoryFragment.setArguments(bundle);
        return categoryFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        mTabLayout = (CustomTabLayout) view.findViewById(R.id.tab_layout);
        mCategoryPager = (ViewPager) view.findViewById(R.id.pager);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initPagerAdapter();
        setPagerAdapter();
        addTabLayoutPagerInteraction();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCategory = (Category) getArguments().getParcelable(ARG_CATEGORY);
    }

    private void initPagerAdapter() {
        if (null != mCategory.getCategories())
            mCategoryPagerAdapter = new CategoryPagerAdapter(getChildFragmentManager(), mCategory, mListener);
    }

    private void setPagerAdapter() {
        if (null != mCategoryPagerAdapter) {
            mCategoryPager.setAdapter(mCategoryPagerAdapter);
            mTabLayout.setTabsFromPagerAdapter(mCategoryPagerAdapter);
        }
    }

    private void addTabLayoutPagerInteraction() {
        if (null != mCategoryPager.getAdapter()) {
            mTabLayout.setupWithViewPager(mCategoryPager);
            mCategoryPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        }
    }
}
