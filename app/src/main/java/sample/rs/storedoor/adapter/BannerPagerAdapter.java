package sample.rs.storedoor.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import sample.rs.storedoor.fragments.BannerFragment;
import sample.rs.storedoor.interfaces.OnBannerSelected;
import sample.rs.storedoor.models.Banner;

public class BannerPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Banner> mBanners;
    private Context mContext;

    public BannerPagerAdapter(FragmentManager fragmentManager, ArrayList<Banner> banners, Context context) {
        super(fragmentManager);
        mBanners = banners;
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        fragment = BannerFragment.newInstance(mBanners.get(position), mContext);
        return fragment;
    }

    @Override
    public int getCount() {
        return mBanners.size();
    }
}
