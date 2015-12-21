package sample.rs.storedoor.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Category implements Parcelable {

    private String category_id;
    private String parent_id;
    private String name;
    private String image;
    private String href;
    private ArrayList<Category> categories;


    protected Category(Parcel in) {
        category_id = in.readString();
        parent_id = in.readString();
        name = in.readString();
        image = in.readString();
        href = in.readString();
        categories = in.createTypedArrayList(Category.CREATOR);
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getParent_id() {
        return parent_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public String getHref() {
        return href;
    }

    public String getImage() {
        return image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(category_id);
        dest.writeString(parent_id);
        dest.writeString(name);
        dest.writeString(image);
        dest.writeString(href);
        dest.writeTypedList(categories);
    }
}
