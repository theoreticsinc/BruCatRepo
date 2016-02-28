package com.theoreticsinc.brucat.models;

/**
 * Created by Theoretics on 2/20/2016.
 */
public class NavItem {
    public String mTitle;
    public String mSubTitle;
    public int mIcon;

    public NavItem(String title, String subtitle, int icon) {
        mTitle = title;
        mSubTitle = subtitle;
        mIcon = icon;
    }
}
