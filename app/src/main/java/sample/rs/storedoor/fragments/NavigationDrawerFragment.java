package sample.rs.storedoor.fragments;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.ArrayList;

import sample.rs.storedoor.R;
import sample.rs.storedoor.activities.CategoryDetailActivity;
import sample.rs.storedoor.adapter.NavigationDrawerAdapter;
import sample.rs.storedoor.constants.Constants;
import sample.rs.storedoor.models.Category;
import sample.rs.storedoor.models.CategoryListResponse;
import sample.rs.storedoor.network.NetworkAdapter;
import sample.rs.storedoor.network.ResponseCallback;
import sample.rs.storedoor.network.RestError;
import sample.rs.storedoor.util.NetworkCheckUtility;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends BaseFragment {

    private ExpandableListView mCategoryList;
    private ArrayList<Category> mCategories;
    private DrawerInteractionListener mListener;
    private int mLastExpandedPosition;
    private ImageView mProfilePic;
    private TextView mUserName;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (DrawerInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity()
                    + " must implement OnPhotoGalleryFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_navigationdrawer, container, false);
        mCategoryList = (ExpandableListView) view.findViewById(R.id.navigation_list);
        mUserName = (TextView) view.findViewById(R.id.user_name);
        mProfilePic = (ImageView) view.findViewById(R.id.profile_pic);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initNavigationList();
        initUserInfo();
    }

    private void initUserInfo() {
        Glide.with(getActivity()).
                load(R.drawable.placeholder)
                .asBitmap()
                .fitCenter()
                .into(new BitmapImageViewTarget(mProfilePic) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(getActivity().getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        mProfilePic.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }

    private void initNavigationList() {
        if (NetworkCheckUtility.isNetworkAvailable(getActivity())) {
            ResponseCallback<CategoryListResponse> responseCallback = new ResponseCallback<CategoryListResponse>() {
                @Override
                public void success(CategoryListResponse categoryListResponse) {
                    NavigationDrawerAdapter navigationDrawerAdapter = new NavigationDrawerAdapter(categoryListResponse.getCategories(), getActivity());
                    mCategories = categoryListResponse.getCategories();
                    mCategoryList.setAdapter(navigationDrawerAdapter);
                }

                @Override
                public void failure(RestError error) {
                    // TODO: 16/12/15 Handle server error
                }
            };
            NetworkAdapter.get(getActivity()).getCategories(responseCallback);
        } else {
            // TODO: 16/12/15 Handle no network
        }

        mCategoryList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                mListener.onDrawerItemClicked();
                Intent intent = new Intent(getActivity(), CategoryDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(Constants.EXTRA_CATEGORY, mCategories.get(groupPosition));
                intent.putExtras(bundle);
                startActivity(intent);

                if (mLastExpandedPosition != -1
                        && groupPosition != mLastExpandedPosition) {
                    mCategoryList.collapseGroup(mLastExpandedPosition);
                }
                mLastExpandedPosition = groupPosition;

                return true;
            }
        });

        mCategoryList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                if (mLastExpandedPosition != -1
                        && groupPosition != mLastExpandedPosition) {
                    mCategoryList.collapseGroup(mLastExpandedPosition);
                }
                mLastExpandedPosition = groupPosition;
            }
        });
    }

    public interface DrawerInteractionListener {
        void onDrawerItemClicked();
    }


}
