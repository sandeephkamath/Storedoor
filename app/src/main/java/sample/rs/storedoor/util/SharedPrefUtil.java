package sample.rs.storedoor.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

import sample.rs.storedoor.R;
import sample.rs.storedoor.constants.Constants;
import sample.rs.storedoor.models.Product;

public class SharedPrefUtil {

    private static ArrayList<Product> mCartProducts;

    public static SharedPreferences getSharedPreferences(final Context context) {
        return android.preference.PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static boolean getBoolean(final Context context, final String key, final boolean defaultValue) {
        return SharedPrefUtil.getSharedPreferences(context).getBoolean(key, defaultValue);
    }

    public static boolean putBoolean(final Context context, final String key, final boolean pValue) {
        final SharedPreferences.Editor editor = SharedPrefUtil.getSharedPreferences(context).edit();

        editor.putBoolean(key, pValue);

        return editor.commit();
    }

    public static String getString(final Context context, final String key, final String defaultValue) {
        return SharedPrefUtil.getSharedPreferences(context).getString(key, defaultValue);
    }

    public static boolean putString(final Context context, final String key, final String pValue) {
        final SharedPreferences.Editor editor = SharedPrefUtil.getSharedPreferences(context).edit();

        editor.putString(key, pValue);

        return editor.commit();
    }

    public static void addToCart(Context context, Product product) {
        String productsString = SharedPrefUtil.getString(context, Constants.SP_CART, "");

        JSONArray productsJson = null;
        try {
            productsJson = new JSONArray(productsString);
        } catch (JSONException e) {
            e.printStackTrace();
            productsJson = new JSONArray();
        }
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Product>>() {
        }.getType();
        mCartProducts = gson.fromJson(productsJson.toString(), type);
        mCartProducts.add(product);
        productsString = gson.toJson(mCartProducts);
        SharedPrefUtil.putString(context, Constants.SP_CART, productsString);
        ToastUtil.showToast(context,product.getName()+ " "+context.getString(R.string.added_to_cart));
    }

    public static ArrayList<Product> getCartProducts(Context context) {
        String productsString = SharedPrefUtil.getString(context, Constants.SP_CART, "");

        JSONArray productsJson = null;
        try {
            productsJson = new JSONArray(productsString);
        } catch (JSONException e) {
            e.printStackTrace();
            productsJson = new JSONArray();
        }
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Product>>() {
        }.getType();
        mCartProducts = gson.fromJson(productsJson.toString(), type);
        return mCartProducts;
    }

}
