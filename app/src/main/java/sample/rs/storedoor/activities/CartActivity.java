package sample.rs.storedoor.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import sample.rs.storedoor.R;
import sample.rs.storedoor.adapter.ProductListAdapter;
import sample.rs.storedoor.fragments.BaseFragment;
import sample.rs.storedoor.fragments.HomeFragment;
import sample.rs.storedoor.fragments.ProductListFragment;
import sample.rs.storedoor.models.Product;
import sample.rs.storedoor.util.SharedPrefUtil;
import sample.rs.storedoor.util.ToastUtil;

public class CartActivity extends NavigationDrawerBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_cart);
        loadCartFragment();
        setToolBarTitle(getString(R.string.cart));
    }

    private void loadCartFragment() {
        ProductListFragment homeFragment = ProductListFragment.newInstance(SharedPrefUtil.getCartProducts(CartActivity.this),
                true, new ProductListAdapter.ProductSelectListener() {
                    @Override
                    public void onProductSelected(Product product) {
                        ToastUtil.showToast(CartActivity.this, product.getName());
                    }

                    @Override
                    public void onAddToCart(Product product) {

                    }
                });
        loadFragment(R.id.container, homeFragment, ProductListFragment.class.getName(), 0, 0,
                BaseFragment.FragmentTransactionType.ADD);
    }


}
