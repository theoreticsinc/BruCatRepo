package com.theoreticsinc.brucat.utils;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.theoreticsinc.brucat.R;
import com.theoreticsinc.brucat.models.NavItem;

import java.util.ArrayList;

/**
 * Created by Theoretics on 2/20/2016.
 */
public class DrawerListAdapter extends BaseAdapter {

    Context mContext;
    ArrayList<NavItem> mNavItems;

    public DrawerListAdapter(Context context, ArrayList<NavItem> navItems) {
        mContext = context;
        mNavItems = navItems;
    }

    @Override
    public int getCount() {
        return mNavItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mNavItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.drawer_item, null);
        }
        else {
            view = convertView;
        }

        TextView titleView = (TextView) view.findViewById(R.id.titleText);
        titleView.setTextSize(14.0f);
        titleView.setTextColor(Color.WHITE);
        TextView subtitleView = (TextView) view.findViewById(R.id.subTitle);
        subtitleView.setTextSize(10.0f);
        subtitleView.setTextColor(Color.LTGRAY);
        ImageView iconView = (ImageView) view.findViewById(R.id.icon);

        titleView.setText( mNavItems.get(position).mTitle );
        subtitleView.setText( mNavItems.get(position).mSubTitle );
        iconView.setImageResource(mNavItems.get(position).mIcon);

        return view;
    }
}
