package sample.rs.storedoor.activities;

import android.os.Bundle;

import sample.rs.storedoor.R;
import sample.rs.storedoor.fragments.BaseFragment;
import sample.rs.storedoor.fragments.HomeFragment;
import sample.rs.storedoor.fragments.NavigationDrawerFragment;
import sample.rs.storedoor.fragments.WebviewFragment;
import sample.rs.storedoor.interfaces.OnBannerSelected;
import sample.rs.storedoor.models.Banner;

public class HomeActivity extends FABNavigationDrawerBaseActivity implements OnBannerSelected {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_home);

        loadHomeFragment();

    }


    private void loadWebviewFragment(String url) {
        WebviewFragment productDetailFragment = WebviewFragment.newInstance(url);
        loadFragment(R.id.container, productDetailFragment, WebviewFragment.class.getName(), 0, 0,
                BaseFragment.FragmentTransactionType.ADD_TO_BACK_STACK_AND_ADD);
    }

    private void loadHomeFragment() {
        HomeFragment homeFragment = new HomeFragment();
        loadFragment(R.id.container, homeFragment, HomeFragment.class.getName(), 0, 0,
                BaseFragment.FragmentTransactionType.ADD);
    }

    private void loadNavigFragment() {
        NavigationDrawerFragment navigationdrawerFragment = new NavigationDrawerFragment();
        loadFragment(R.id.container, navigationdrawerFragment, NavigationDrawerFragment.class.getName(), 0, 0,
                BaseFragment.FragmentTransactionType.ADD);
    }

    @Override
    public void onBannerSelected(Banner banner) {
        loadWebviewFragment(banner.getLink());
    }
}
