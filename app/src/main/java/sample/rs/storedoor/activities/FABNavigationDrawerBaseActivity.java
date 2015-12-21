package sample.rs.storedoor.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import sample.rs.storedoor.R;
import sample.rs.storedoor.fragments.BaseFragment;
import sample.rs.storedoor.fragments.HomeFragment;
import sample.rs.storedoor.fragments.NavigationDrawerFragment;
import sample.rs.storedoor.util.ToastUtil;

public class FABNavigationDrawerBaseActivity extends BaseActivity implements NavigationDrawerFragment.DrawerInteractionListener {

    private RelativeLayout mLayOut;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fabnavigation_drawer_base);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mLayOut = (RelativeLayout) findViewById(R.id.container);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        setSupportActionBar(mToolbar);
        if (null != getSupportActionBar()) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        FloatingActionButton cart = (FloatingActionButton) findViewById(R.id.cart);
        cart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(FABNavigationDrawerBaseActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });

        NavigationDrawerFragment navigationDrawerFragment = new NavigationDrawerFragment();
        loadFragment(R.id.drawerContainer, navigationDrawerFragment, HomeFragment.class.getName(), 0, 0,
                BaseFragment.FragmentTransactionType.ADD);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.hello_world, R.string.hello_world) {

                public void onDrawerClosed(View view) {
                    supportInvalidateOptionsMenu();
                    //drawerOpened = false;
                }

                public void onDrawerOpened(View drawerView) {
                    supportInvalidateOptionsMenu();
                    //drawerOpened = true;
                }
            };
            mDrawerToggle.setDrawerIndicatorEnabled(true);
            mDrawerLayout.setDrawerListener(mDrawerToggle);
        }
    }

    public void setLayout(int layoutId) {
        View view = LayoutInflater.from(FABNavigationDrawerBaseActivity.this).inflate(layoutId, mLayOut, false);
        mLayOut.addView(view);
    }

    @Override
    public void onDrawerItemClicked() {
        mDrawerLayout.closeDrawer(Gravity.LEFT);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (item.getItemId() == android.R.id.home) {
            if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                mDrawerLayout.closeDrawer(Gravity.LEFT);
            } else {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }

        } else if (item.getItemId() == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
