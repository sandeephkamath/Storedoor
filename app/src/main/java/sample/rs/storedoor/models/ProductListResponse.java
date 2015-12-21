package sample.rs.storedoor.models;

import java.util.ArrayList;

public class ProductListResponse {
    private boolean success;
    private ArrayList<Product> products;

    public ArrayList<Product> getProducts() {
        return products;
    }

    public boolean isSuccess() {
        return success;
    }
}
