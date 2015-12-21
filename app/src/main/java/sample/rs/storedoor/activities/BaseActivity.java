package sample.rs.storedoor.activities;

import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import sample.rs.storedoor.R;
import sample.rs.storedoor.adapter.NavigationDrawerAdapter;
import sample.rs.storedoor.fragments.BaseFragment;
import sample.rs.storedoor.fragments.HomeFragment;
import sample.rs.storedoor.fragments.NavigationDrawerFragment;
import sample.rs.storedoor.util.StringUtil;

public class BaseActivity extends AppCompatActivity {

    private FrameLayout mLayOut;
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        mLayOut = (FrameLayout) findViewById(R.id.container);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        if (null != getSupportActionBar()) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

    }


    protected void setToolBarTitle(String title) {
        TextView toolbarTitle = (TextView) findViewById(R.id.title);
        toolbarTitle.setText(title.toUpperCase());
    }

    public void setLayout(int layoutId) {
        View view = LayoutInflater.from(BaseActivity.this).inflate(layoutId, mLayOut, false);
        mLayOut.addView(view);
    }

    public void loadFragment(int fragmentContainerId, BaseFragment fragment, @Nullable String tag,
                             int enterAnimId, int exitAnimId,
                             BaseFragment.FragmentTransactionType fragmentTransactionType) {

        performFragmentTranscation(fragmentContainerId, fragment, tag,
                (enterAnimId == 0) ? 0 : enterAnimId,
                (exitAnimId == 0) ? 0 : exitAnimId,
                fragmentTransactionType);
    }

    private void performFragmentTranscation(int fragmentContainerId,
                                            Fragment fragment, String tag,
                                            int enterAnimId, int exitAnimId,
                                            BaseFragment.FragmentTransactionType fragmentTransactionType) {
        switch (fragmentTransactionType) {
            case ADD:
                addFragment(fragmentContainerId, fragment, tag, enterAnimId, exitAnimId);
                break;
            case REPLACE:
                replaceFragment(fragmentContainerId, fragment, tag, enterAnimId, exitAnimId);
                break;
            case ADD_TO_BACK_STACK_AND_ADD:
                addToBackStackAndAdd(fragmentContainerId, fragment, tag, enterAnimId, exitAnimId);
                break;
            case ADD_TO_BACK_STACK_AND_REPLACE:
                addToBackStackAndReplace(fragmentContainerId, fragment, tag, enterAnimId, exitAnimId);
                break;
            case POP_BACK_STACK_AND_REPLACE:
                popBackStackAndReplace(fragmentContainerId, fragment, tag, enterAnimId, exitAnimId);
                break;
            case CLEAR_BACK_STACK_AND_REPLACE:
                clearBackStackAndReplace(fragmentContainerId, fragment, tag, enterAnimId, exitAnimId);
                break;
            default:
                replaceFragment(fragmentContainerId, fragment, tag, enterAnimId, exitAnimId);
        }

    }

    private void addToBackStackAndAdd(int fragmentContainerId, Fragment fragment, String tag, int enterAnimId, int exitAnimId) {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(enterAnimId, 0, 0, exitAnimId)
                .add(fragmentContainerId, fragment, tag)
                .addToBackStack(tag)
                .commit();
    }

    protected void addFragment(int fragmentContainerId, Fragment fragment, String tag, int enterAnimId, int exitAnimId) {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(enterAnimId, exitAnimId)
                .add(fragmentContainerId, fragment, tag)
                .commit();
    }

    private void replaceFragment(int fragmentContainerId, Fragment fragment, @Nullable String tag,
                                 int enterAnimId, int exitAnimId) {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(enterAnimId, exitAnimId)
                .replace(fragmentContainerId, fragment, tag).commit();
    }

    private void popBackStackAndReplace(int fragmentContainerId, Fragment fragment,
                                        @Nullable String tag, int enterAnimId, int exitAnimId) {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(enterAnimId, exitAnimId)
                .replace(fragmentContainerId, fragment, tag).commit();
    }

    private void addToBackStackAndReplace(int fragmentContainerId, Fragment fragment,
                                          @Nullable String tag, int enterAnimId, int exitAnimId) {
        getSupportFragmentManager().beginTransaction().addToBackStack(null)
                .setCustomAnimations(enterAnimId, 0, 0, exitAnimId)
                .replace(fragmentContainerId, fragment, tag).commit();
    }

    private void clearBackStackAndReplace(int fragmentContainerId, Fragment fragment,
                                          @Nullable String tag, int enterAnimId, int exitAnimId) {
        clearBackStack(FragmentManager.POP_BACK_STACK_INCLUSIVE);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(enterAnimId, exitAnimId)
                .replace(fragmentContainerId, fragment, tag).commit();
    }

    private void clearBackStack(int flag) {
        FragmentManager fm = getSupportFragmentManager();

        if (fm.getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry first = fm.getBackStackEntryAt(0);
            fm.popBackStack(first.getId(), flag);
        }
    }

}
