package sample.rs.storedoor.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.AbstractList;

import sample.rs.storedoor.R;
import sample.rs.storedoor.adapter.viewholder.ProductHolder;
import sample.rs.storedoor.models.Product;
import sample.rs.storedoor.util.ToastUtil;

public class ProductListAdapter extends RecyclerView.Adapter {

    private AbstractList<Product> products;
    private ProductSelectListener mListener;
    private boolean mIsCart;

    public ProductListAdapter(AbstractList<Product> products, boolean isCart, ProductSelectListener listener) {
        this.products = products;
        mIsCart = isCart;
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ProductHolder(LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.item_product, viewGroup, false), mIsCart, mListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ProductHolder productHolder = (ProductHolder) viewHolder;
        productHolder.bindData(products.get(position));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public interface ProductSelectListener {
        void onProductSelected(Product product);

        void onAddToCart(Product product);
    }

}
