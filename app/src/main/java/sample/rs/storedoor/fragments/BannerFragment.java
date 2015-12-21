package sample.rs.storedoor.fragments;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import sample.rs.storedoor.R;
import sample.rs.storedoor.interfaces.OnBannerSelected;
import sample.rs.storedoor.models.Banner;
import sample.rs.storedoor.util.ImageUrlUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class BannerFragment extends BaseFragment {


    private static final String ARG_BANNER = "arg_banner";
    private ImageView mBannerImage;
    private Banner mBanner;
    private OnBannerSelected mListener;

    public BannerFragment() {
        // Required empty public constructor
    }

    public static BannerFragment newInstance(Banner banner, Context context) {
        Bundle args = new Bundle();
        args.putParcelable(ARG_BANNER, banner);
        BannerFragment fragment = new BannerFragment();
        fragment.setArguments(args);
        fragment.setListener(context);
        return fragment;
    }

    private void setListener(Context context) {
        mListener = (OnBannerSelected) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != getArguments()) {
            mBanner = getArguments().getParcelable(ARG_BANNER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_banner, container, false);
        mBannerImage = (ImageView) view.findViewById(R.id.banner_image);
        initBanner();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onBannerSelected(mBanner);
            }
        });
        return view;
    }

    private void initBanner() {
        Glide.with(getActivity())
                .load(ImageUrlUtil.getImage600x422(mBanner.getImage()))
                .into(mBannerImage);
    }


}
