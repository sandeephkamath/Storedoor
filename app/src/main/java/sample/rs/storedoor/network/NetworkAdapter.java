package sample.rs.storedoor.network;

import android.content.Context;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.client.Response;
import sample.rs.storedoor.models.Banner;
import sample.rs.storedoor.models.Category;
import sample.rs.storedoor.models.CategoryListResponse;
import sample.rs.storedoor.models.ProductListResponse;

public class NetworkAdapter {

    private static NetworkAdapter mNetworkAdapter;
    private final RestClient mRestClient;

    private NetworkAdapter(Context context) {
        mRestClient = new RestClient(context);
    }

    public static NetworkAdapter get(Context context) {
        if (null == mNetworkAdapter) {
            mNetworkAdapter = new NetworkAdapter(context);
        }
        return mNetworkAdapter;
    }


    public void getProductList(final ResponseCallback<ProductListResponse> responseCallback) {
        Callback<ProductListResponse> restCallback = new RestCallback<ProductListResponse>() {
            @Override
            public void failure(RestError error) {
                responseCallback.failure(error);
            }

            @Override
            public void success(ProductListResponse productListResponse, Response response) {
                responseCallback.success(productListResponse);
            }
        };
        mRestClient.getService().getProductList(restCallback);
    }

    public void getBanners(final ResponseCallback<ArrayList<Banner>> responseCallback) {
        Callback<ArrayList<Banner>> restCallback = new RestCallback<ArrayList<Banner>>() {
            @Override
            public void success(ArrayList<Banner> banners, Response response) {
                responseCallback.success(banners);
            }

            @Override
            public void failure(RestError error) {
                responseCallback.failure(error);
            }
        };
        mRestClient.getService().getBanners(restCallback);
    }

    public void getCategories(final ResponseCallback<CategoryListResponse> responseCallback) {
        Callback<CategoryListResponse> restCallback = new RestCallback<CategoryListResponse>() {
            @Override
            public void success(CategoryListResponse categoryListResponse, Response response) {
                responseCallback.success(categoryListResponse);
            }

            @Override
            public void failure(RestError error) {
                responseCallback.failure(error);
            }
        };
        mRestClient.getService().getCategories(restCallback);
    }

    public void getProducts(String categoryId, final ResponseCallback<ProductListResponse> responseCallback) {
        Callback<ProductListResponse> restCallback = new RestCallback<ProductListResponse>() {
            @Override
            public void failure(RestError error) {
                responseCallback.failure(error);
            }

            @Override
            public void success(ProductListResponse productListResponse, Response response) {
                responseCallback.success(productListResponse);
            }
        };
        mRestClient.getService().getProducts(categoryId, restCallback);
    }

}
