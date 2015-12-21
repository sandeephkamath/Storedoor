package sample.rs.storedoor.models;

import java.util.ArrayList;

public class CategoryListResponse {
    private boolean success;
    private ArrayList<Category> categories;

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public boolean isSuccess() {
        return success;
    }
}
