package sample.rs.storedoor.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import sample.rs.storedoor.R;
import sample.rs.storedoor.adapter.ProductListAdapter;
import sample.rs.storedoor.constants.Constants;
import sample.rs.storedoor.models.Category;
import sample.rs.storedoor.models.Product;
import sample.rs.storedoor.models.ProductListResponse;
import sample.rs.storedoor.network.NetworkAdapter;
import sample.rs.storedoor.network.ResponseCallback;
import sample.rs.storedoor.network.RestError;
import sample.rs.storedoor.util.NetworkCheckUtility;
import sample.rs.storedoor.util.ToastUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductListFragment extends BaseFragment {

    private static final String ARG_CATEGORY = "arg_category";
    private static final String ARG_PRODUCT_LIST = "arg_product_list";
    private static final String ARG_IS_CART = "arg_is_cart";
    private static ProductListAdapter.ProductSelectListener mListener;
    private ProgressBar mProgressBar;
    private RecyclerView mProductList;
    private Category mCategory;
    private TextView mNoProducts;
    private ArrayList<Product> mProducts;
    private boolean mIsCart;

    public ProductListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != getArguments()) {
            mCategory = getArguments().getParcelable(ARG_CATEGORY);
            mProducts = getArguments().getParcelableArrayList(ARG_PRODUCT_LIST);
            mIsCart = getArguments().getBoolean(ARG_IS_CART, false);
        }

    }

    public static ProductListFragment newInstance(ArrayList<Product> products, boolean isCart, ProductListAdapter.ProductSelectListener listener) {
        mListener = listener;
        Bundle bundle = new Bundle();
        bundle.putBoolean(ARG_IS_CART, isCart);
        bundle.putParcelableArrayList(ARG_PRODUCT_LIST, products);
        ProductListFragment productListFragment = new ProductListFragment();
        productListFragment.setArguments(bundle);
        return productListFragment;
    }

    public static ProductListFragment newInstance(Category category, boolean isCart, ProductListAdapter.ProductSelectListener listener) {
        mListener = listener;
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_CATEGORY, category);
        bundle.putBoolean(ARG_IS_CART, isCart);
        ProductListFragment fragment = new ProductListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progress);
        mProductList = (RecyclerView) view.findViewById(R.id.productList);
        mNoProducts = (TextView) view.findViewById(R.id.no_products);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (null == mProducts)
            loadProductList();
        else {
            initProductList(mProducts);
        }
    }

    private void loadProductList() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        if (NetworkCheckUtility.isNetworkAvailable(getActivity())) {
            ResponseCallback<ProductListResponse> productListResponseCallback =
                    new ResponseCallback<ProductListResponse>() {

                        @Override
                        public void success(ProductListResponse productListResponse) {
                            initProductList(productListResponse.getProducts());
                        }

                        @Override
                        public void failure(RestError error) {
                            mProgressBar.setVisibility(View.GONE);
                            ToastUtil.showToast(getContext(), "Error=" + error.getMessage());
                        }
                    };
            if (null != mCategory && null == mCategory.getCategory_id())
                NetworkAdapter.get(getActivity()).getProductList(productListResponseCallback);
            else
                NetworkAdapter.get(getActivity()).getProducts(mCategory.getCategory_id(), productListResponseCallback);
        } else {
            // TODO: 14/12/15 Handle No Network
        }
    }

    private void initProductList(ArrayList<Product> products) {
        mProgressBar.setVisibility(View.GONE);
        if (products.size() > 0) {
            ProductListAdapter productListAdapter = new ProductListAdapter(products, mIsCart, mListener);
            mProductList.setLayoutManager(new LinearLayoutManager(getActivity()));
            mProductList.setAdapter(productListAdapter);
        } else {
            mNoProducts.setVisibility(View.VISIBLE);
        }
    }

}
