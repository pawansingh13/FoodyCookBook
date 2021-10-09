package com.app.foodycookbook.feature.container.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.foodycookbook.R;
import com.app.foodycookbook.feature.container.model.DrawerItem;
import java.util.ArrayList;

public class DrawerListAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<DrawerItem> mDrawerItems;

    public DrawerListAdapter(Context mContext, ArrayList<DrawerItem> mDrawerItems) {
        this.mContext = mContext;
        this.mDrawerItems = mDrawerItems;
    }

    @Override
    public int getCount() {
        return mDrawerItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mDrawerItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater mInflater = (LayoutInflater)
                mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView = mInflater.inflate(R.layout.drawer_layout, null);
        TextView mTvTitle = convertView.findViewById(R.id.tv_menu_item);

        mTvTitle.setText(mDrawerItems.get(position).getDrawerItem());
        mTvTitle.setCompoundDrawablesWithIntrinsicBounds(mDrawerItems.get(position).getDrawerItemImage(), 0, 0, 0);
        return convertView;
    }

    public void mViewVisibleOrNot(TextView mNotification, boolean flag) {
        mNotification.setVisibility(flag ? View.VISIBLE : View.GONE);
    }

}
