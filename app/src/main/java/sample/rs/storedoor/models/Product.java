package sample.rs.storedoor.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable{

    private String id;
    private String name;
    private String description;
    private String pirce;
    private String href;
    private String thumb;
    private String special;
    private int rating;

    protected Product(Parcel in) {
        id = in.readString();
        name = in.readString();
        description = in.readString();
        pirce = in.readString();
        href = in.readString();
        thumb = in.readString();
        special = in.readString();
        rating = in.readInt();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public int getRating() {
        return rating;
    }

    public String getSpecial() {
        return special;
    }

    public String getThumb() {
        return thumb;
    }

    public String getHref() {
        return href;
    }

    public String getPrice() {
        return pirce;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(pirce);
        dest.writeString(href);
        dest.writeString(thumb);
        dest.writeString(special);
        dest.writeInt(rating);
    }
}
