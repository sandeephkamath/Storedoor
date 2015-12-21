/*
 * Copyright © 2015, 9x Media Pvt. Ltd.
 * Written under contract by Robosoft Technologies Pvt. Ltd.
 */

package sample.rs.storedoor.network;

import android.content.Context;
import android.text.TextUtils;


import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import sample.rs.storedoor.R;

/**
 * Created by nagesh on 14/9/15.
 */
public class RestClient {

    private final RestAdapter REST_ADAPTER;

    public RestClient(Context context) {
        String baseUrl = context.getString(R.string.base_url);
        REST_ADAPTER = buildRestAdapter(baseUrl, null);
    }

    private RestAdapter buildRestAdapter(String baseUrl, RequestInterceptor requestInterceptor) {
        RestAdapter.LogLevel logLevel = RestAdapter.LogLevel.FULL;
        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setLogLevel(logLevel);
        if (!TextUtils.isEmpty(baseUrl)) {
            builder.setEndpoint(baseUrl);
        }
        if (requestInterceptor != null) {
            builder.setRequestInterceptor(requestInterceptor);
        }
        return builder.build();
    }

    public ApiService getService() {
        return REST_ADAPTER.create(ApiService.class);
    }


}
