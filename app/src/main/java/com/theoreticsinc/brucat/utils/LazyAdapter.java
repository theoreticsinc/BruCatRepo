package com.theoreticsinc.brucat.utils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.theoreticsinc.brucat.R;

import java.util.List;

public class LazyAdapter extends BaseAdapter {
    
    private Activity activity;
    private List<String> imgData;
    private List<String> titleData;
    private List<String> details;
    private static LayoutInflater inflater=null;
    private String type;
    public ImageLoader imageLoader;
    
    public LazyAdapter(String type, Activity a, List<String> d, List<String> name, List<String> details) {
        this.activity = a;
        this.type = type;
        this.imgData = d;
        this.titleData = name;
        this.details = details;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.imageLoader=new ImageLoader(activity.getApplicationContext());
    }

    public int getCount() {
        return imgData.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;

        if (type.compareToIgnoreCase("home") == 0)
        {
            if (convertView == null)
                vi = inflater.inflate(R.layout.homeitems, null);

            TextView text = (TextView) vi.findViewById(R.id.titleText);
            ImageView listimage = (ImageView) vi.findViewById(R.id.previewIcon);
            TextView previewText = (TextView) vi.findViewById(R.id.previewText);
            if (null != text) text.setText(titleData.get(position));
            if (null != previewText) previewText.setText(details.get(position));
            if (null != listimage) imageLoader.DisplayImage(imgData.get(position), listimage);
        }

        else if (type.compareToIgnoreCase("events") == 0)
        {
            if (convertView == null)
                vi = inflater.inflate(R.layout.item, null);

            TextView text = (TextView) vi.findViewById(R.id.text);
            ImageView listimage = (ImageView) vi.findViewById(R.id.listimage);
            if (null != text) text.setText(titleData.get(position));
            if (null != listimage) imageLoader.DisplayImage(imgData.get(position), listimage);
        }

        else if (type.compareToIgnoreCase("newsletters") == 0)
        {
            if (convertView == null)
                vi = inflater.inflate(R.layout.item, null);

            TextView text = (TextView) vi.findViewById(R.id.text);
            ImageView listimage = (ImageView) vi.findViewById(R.id.listimage);
            if (null != text) text.setText(titleData.get(position));
            if (null != listimage) imageLoader.DisplayImage(imgData.get(position), listimage);
        }
        return vi;
    }
}