package sample.rs.storedoor.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import sample.rs.storedoor.R;

public class ProductDetailActivity extends NavigationDrawerBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_product_detail);
    }


}
