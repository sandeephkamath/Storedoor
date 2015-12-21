package sample.rs.storedoor.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;
import sample.rs.storedoor.R;
import sample.rs.storedoor.adapter.BannerPagerAdapter;
import sample.rs.storedoor.models.Banner;
import sample.rs.storedoor.network.NetworkAdapter;
import sample.rs.storedoor.network.ResponseCallback;
import sample.rs.storedoor.network.RestError;
import sample.rs.storedoor.util.NetworkCheckUtility;
import sample.rs.storedoor.util.ToastUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {

    private ViewPager mBanner;
    private BannerPagerAdapter mBannerPagerAdapter;
    private CircleIndicator mCircleIndicator;
    private int currentPage;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mBanner = (ViewPager) view.findViewById(R.id.banner_pager);
        mCircleIndicator = (CircleIndicator) view.findViewById(R.id.indicator);
        initBanner();
        return view;
    }

    private void initBanner() {
        if (NetworkCheckUtility.isNetworkAvailable(getActivity())) {
            ResponseCallback<ArrayList<Banner>> responseCallback = new ResponseCallback<ArrayList<Banner>>() {
                @Override
                public void success(ArrayList<Banner> banners) {
                    mBannerPagerAdapter = new BannerPagerAdapter(getChildFragmentManager(), banners, getActivity());
                    mBanner.setAdapter(mBannerPagerAdapter);
                    mCircleIndicator.setViewPager(mBanner);
                    initAutoSlide();
                }

                @Override
                public void failure(RestError error) {
                    ToastUtil.showToast(getActivity(), "Banner Error " + error.toString());
                }
            };
            NetworkAdapter.get(getActivity()).getBanners(responseCallback);
        }


    }

    private void initAutoSlide() {
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {

            public void run() {
                if (currentPage == mBannerPagerAdapter.getCount() - 1) {
                    currentPage = 0;
                }
                mBanner.setCurrentItem(currentPage++, true);
            }
        };

        final Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                handler.post(Update);
            }
        }, 100, 3000);

        mBanner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                currentPage = position;
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
