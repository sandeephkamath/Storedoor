package sample.rs.storedoor.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import sample.rs.storedoor.R;
import sample.rs.storedoor.adapter.ProductListAdapter;
import sample.rs.storedoor.constants.Constants;
import sample.rs.storedoor.fragments.BaseFragment;
import sample.rs.storedoor.fragments.CategoryFragment;
import sample.rs.storedoor.fragments.ProductListFragment;
import sample.rs.storedoor.models.Category;
import sample.rs.storedoor.models.Product;
import sample.rs.storedoor.util.SharedPrefUtil;
import sample.rs.storedoor.util.StringUtil;
import sample.rs.storedoor.util.ToastUtil;

public class CategoryDetailActivity extends FABNavigationDrawerBaseActivity {

    private Category mCategory;
    private int mSubCategoryPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_category_detail);
        mCategory = getIntent().getExtras().getParcelable(Constants.EXTRA_CATEGORY);
        mSubCategoryPosition = getIntent().getExtras().getInt(Constants.EXTRA_SUB_CATEGORY_POSITION);
        loadCategoryFragment();
        setToolBarTitle(StringUtil.removeAmp(mCategory.getName()));
    }


    private void loadCategoryFragment() {
        CategoryFragment categoryFragment = CategoryFragment.newInstance(mCategory, mSubCategoryPosition, new ProductListAdapter.ProductSelectListener() {
            @Override
            public void onProductSelected(Product product) {
                ToastUtil.showToast(CategoryDetailActivity.this, product.getPrice());
            }

            @Override
            public void onAddToCart(Product product) {
                SharedPrefUtil.addToCart(CategoryDetailActivity.this, product);
            }
        });
        loadFragment(R.id.container, categoryFragment, ProductListFragment.class.getName(), 0, 0,
                BaseFragment.FragmentTransactionType.ADD);
    }
}
