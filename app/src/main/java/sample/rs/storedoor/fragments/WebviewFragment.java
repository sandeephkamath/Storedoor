package sample.rs.storedoor.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import sample.rs.storedoor.R;
import sample.rs.storedoor.models.Product;

/**
 * A simple {@link Fragment} subclass.
 */
public class WebviewFragment extends BaseFragment {

    private static final String ARG_PRODUCT = "product";
    private static final String ARG_URL = "arg_url";
    private WebView webView;
    private Product mProduct;
    private String mUrl;
    private ProgressBar mProgressBar;


    public WebviewFragment() {

    }


    public static WebviewFragment newInstance(String url) {
        Bundle args = new Bundle();
        args.putString(ARG_URL, url);
        WebviewFragment fragment = new WebviewFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);
        webView = (WebView) view.findViewById(R.id.productView);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progress);
        if (null != mProduct)
            loadProduct();
        else
            loadUrl(mUrl);
        return view;
    }

    private void loadUrl(String url) {
        webView.getSettings().setJavaScriptEnabled(true);
        try {
            webView.loadUrl(URLDecoder.decode(url.replace("&amp;", "&"), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                mProgressBar.setVisibility(View.GONE);
            }
        });
    }

    private void loadProduct() {
        loadUrl(mProduct.getHref());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != getArguments()) {
            mProduct = getArguments().getParcelable(ARG_PRODUCT);
            mUrl = getArguments().getString(ARG_URL);
        }
    }


}
