package sample.rs.storedoor.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import sample.rs.storedoor.R;
import sample.rs.storedoor.util.ToastUtil;

public class FABBaseActivity extends BaseActivity {

    private RelativeLayout mLayOut;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fabbase);

        mLayOut = (RelativeLayout) findViewById(R.id.container);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        if (null != getSupportActionBar()) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        FloatingActionButton cart = (FloatingActionButton) findViewById(R.id.cart);
        cart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ToastUtil.showToast(FABBaseActivity.this, "Cart");
            }
        });

    }

    protected void setToolBarTitle(String title) {
        TextView toolbarTitle = (TextView) mToolbar.findViewById(R.id.title);
        toolbarTitle.setText(title.toUpperCase());
    }

    public void setLayout(int layoutId) {
        View view = LayoutInflater.from(FABBaseActivity.this).inflate(layoutId, mLayOut, false);
        mLayOut.addView(view);
    }


}
