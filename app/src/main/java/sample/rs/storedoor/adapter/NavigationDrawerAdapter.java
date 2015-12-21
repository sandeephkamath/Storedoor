package sample.rs.storedoor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import sample.rs.storedoor.R;
import sample.rs.storedoor.models.Category;
import sample.rs.storedoor.util.ImageUrlUtil;
import sample.rs.storedoor.util.StringUtil;
import sample.rs.storedoor.util.ToastUtil;

public class NavigationDrawerAdapter extends BaseExpandableListAdapter {

    private ArrayList<Category> mCategories;
    private Context mContext;

    public NavigationDrawerAdapter(ArrayList<Category> categories, Context context) {
        mCategories = categories;
        mContext = context;
    }

    @Override
    public int getGroupCount() {
        return mCategories.size();
    }

    @Override
    public int getChildrenCount(int position) {
        int size = 0;
        if (null != mCategories.get(position).getCategories())
            size = mCategories.get(position).getCategories().size();
        return size;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mCategories.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        if (null != mCategories.get(groupPosition).getCategories())
            return mCategories.get(groupPosition).getCategories().get(childPosition);
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        CategoryHolder categoryHolder;
        if (null == convertView) {
            categoryHolder = new CategoryHolder();
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.item_category, null);
            categoryHolder.categoryImage = (ImageView) convertView.findViewById(R.id.category_image);
            categoryHolder.categoryName = (TextView) convertView.findViewById(R.id.category_name);
            convertView.setTag(categoryHolder);
        } else {
            categoryHolder = (CategoryHolder) convertView.getTag();
        }
        Glide.with(mContext)
                .load(mCategories.get(groupPosition).getImage())
                .into(categoryHolder.categoryImage);
        categoryHolder.categoryName.setText(StringUtil.removeAmp(mCategories.get(groupPosition).getName()));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        SubcategoryHolder subcategoryHolder;
        if (null == convertView) {
            subcategoryHolder = new SubcategoryHolder();
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.item_sub_category, null);
            subcategoryHolder.subCategoryName = (TextView) convertView.findViewById(R.id.sub_category_name);
            convertView.setTag(subcategoryHolder);
        } else {
            subcategoryHolder = (SubcategoryHolder) convertView.getTag();
        }

        if (null != mCategories.get(groupPosition).getCategories())
            subcategoryHolder.subCategoryName.setText(StringUtil.removeAmp(mCategories.get(groupPosition).getCategories().get(childPosition).getName()));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public class CategoryHolder {
        TextView categoryName;
        ImageView categoryImage;
    }

    public class SubcategoryHolder {
        TextView subCategoryName;
    }
}

