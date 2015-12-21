package sample.rs.storedoor.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sample.rs.storedoor.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductDetailFragment extends Fragment {


    public ProductDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_detail2, container, false);
        return view;
    }


}
