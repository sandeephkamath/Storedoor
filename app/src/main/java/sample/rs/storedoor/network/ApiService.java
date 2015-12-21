package sample.rs.storedoor.network;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import sample.rs.storedoor.models.Banner;
import sample.rs.storedoor.models.Category;
import sample.rs.storedoor.models.CategoryListResponse;
import sample.rs.storedoor.models.ProductListResponse;

public interface ApiService {

    String API_KEY = "2201606";

    /* Home Page */
    @GET("/products&key=" + API_KEY)
    void getProductList(Callback<ProductListResponse> responseCallback);

    /*Banner Image*/
    @GET("/products&banner=1&key=" + API_KEY)
    void getBanners(Callback<ArrayList<Banner>> responseCallback);

    /* Category List*/
    @GET("/categories&parent=0&level=2&key=" + API_KEY)
    void getCategories(Callback<CategoryListResponse> responseCallback);

    /* Products under category*/
    @GET("/products&category={category}&key=" + API_KEY)
    void getProducts(@Path("category") String categoryId,
                     Callback<ProductListResponse> responseCallback);
}
