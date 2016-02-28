package com.theoreticsinc.brucat.utils;

/**
 * Created by Angelo on 9/30/2015.
 */


import android.content.Context;
import android.graphics.Typeface;
import java.util.Hashtable;

public class FontCache {
    private static Hashtable<String, Typeface> fontCache = new Hashtable();

    public FontCache() {
    }

    public Typeface get(String name, Context context) {
        Typeface tf = (Typeface)fontCache.get(name);
        if(tf == null) {
            try {
                tf = Typeface.createFromAsset(context.getAssets(), name);
            } catch (Exception var4) {
                return null;
            }

            fontCache.put(name, tf);
        }

        return tf;
    }
}
