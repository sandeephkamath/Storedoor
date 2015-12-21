package sample.rs.storedoor.adapter.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import sample.rs.storedoor.R;
import sample.rs.storedoor.adapter.ProductListAdapter;
import sample.rs.storedoor.models.Product;
import sample.rs.storedoor.util.ImageUrlUtil;
import sample.rs.storedoor.util.LogUtils;
import sample.rs.storedoor.util.ToastUtil;

public class ProductHolder extends RecyclerView.ViewHolder {
    private TextView name, price, oldPrice;
    private ImageView productImage;
    private Context context;
    private ProductListAdapter.ProductSelectListener mListener;
    private RelativeLayout productContainer;
    private Button mAddToCart;
    private boolean mIsCart;

    public ProductHolder(View productView, boolean isCart, ProductListAdapter.ProductSelectListener listener) {
        super(productView);
        name = (TextView) productView.findViewById(R.id.name);
        productImage = (ImageView) productView.findViewById(R.id.productImage);
        productContainer = (RelativeLayout) productView.findViewById(R.id.productContainer);
        price = (TextView) productView.findViewById(R.id.price);
        oldPrice = (TextView) productView.findViewById(R.id.oldprice);
        mAddToCart = (Button) productView.findViewById(R.id.add_to_cart);
        context = productView.getContext();
        mIsCart = isCart;
        mListener = listener;

    }

    public void bindData(final Product product) {
        name.setText(product.getName());
        Glide.with(context)
                .load(product.getThumb())
                .placeholder(R.drawable.placeholder)
                .into(productImage);

        price.setText(product.getPrice());
        if (!"false".equalsIgnoreCase(String.valueOf(product.getSpecial()))) {
            oldPrice.setText(product.getPrice());
            oldPrice.setVisibility(View.VISIBLE);
            price.setText(product.getSpecial());
        }

        productContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onProductSelected(product);
            }
        });

        mAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onAddToCart(product);
            }
        });

        if (!mIsCart)
            mAddToCart.setVisibility(View.VISIBLE);
    }
}
