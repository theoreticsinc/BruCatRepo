package com.theoreticsinc.brucat.utils;


/**
 * Created by Angelo on 9/23/2015.
 */

import android.content.Context;
import android.os.StrictMode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.theoreticsinc.brucat.models.ConfigItems;
import com.theoreticsinc.brucat.models.ConfigModel;
import com.theoreticsinc.brucat.models.ConfigPage;
import com.theoreticsinc.brucat.models.EventsItems;
import com.theoreticsinc.brucat.models.EventsModel;
import com.theoreticsinc.brucat.models.EventsPage;
import com.theoreticsinc.brucat.models.HomeListItems;
import com.theoreticsinc.brucat.models.HomeListModel;
import com.theoreticsinc.brucat.models.HomeListPage;
import com.theoreticsinc.brucat.models.NewsletterItems;
import com.theoreticsinc.brucat.models.NewsletterModel;
import com.theoreticsinc.brucat.models.NewsletterPage;

public class GSONParser {

    final String TAG = "GsonParser.java";
    static final String SERVER = "http://theoreticsinc.com/brucat/configuration.json";

    public List<String> id;
    public List<String> name;
    public List<String> pic_url;
    public List<String> details;
    MemoryCache memoryCache=new MemoryCache();

    public GSONParser() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        id = new ArrayList<>();
        name = new ArrayList<>();
        pic_url = new ArrayList<>();
        details = new ArrayList<>();
    }

    public static String readGSONfromServer(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);
            System.out.println(buffer.toString());
            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }

    public static void main(String[] args) throws Exception {
        //new JSONParser().readGSONfromServer(urlString);
        String url = readGSONfromServer(SERVER);
        //http://184.95.54.213/schoolapp/newsletters.json
        //http://www.javascriptkit.com/dhtmltutors/javascriptkit.json
        Gson gson = new Gson();
        ConfigPage page = gson.fromJson(url, ConfigPage.class);
        ConfigModel config = page.config;
        System.out.println("Page Config:"+page.config.list.get(0));
        for (ConfigItems item : config.list)
            System.out.println("    " + item.id + "    " + item.type + " " + item.url);
    }

    public String readConfig(String type) {
        try {
            String configURL = readGSONfromServer(SERVER);
            Gson gson = new Gson();
            ConfigPage page = gson.fromJson(configURL, ConfigPage.class);
            ConfigModel config = page.config;
            //System.out.println(page.newsletters);
            for (ConfigItems item : config.list) {
                System.out.println("    " + item.id + "    " + item.type + " " + item.url);
                String configType = item.type.toString();
                if (0 == configType.compareToIgnoreCase(type)) {
                    return item.url;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void processDataFromGSON(String urlString, Context context, String type) throws Exception{
        String dataFromServer = "";
        String dataFromSDCard = "";
        Gson gson = new Gson();
        try {
            dataFromServer = readGSONfromServer(urlString);
        } catch (Exception e) {
            e.printStackTrace();
        }

            if (null != dataFromServer && dataFromServer.isEmpty() == false) {

                FileCache fileCache = new FileCache(context);
                if (type.compareToIgnoreCase("homelist") == 0) {
                    HomeListPage page = gson.fromJson(dataFromServer, HomeListPage.class);
                    HomeListModel homelist = page.homelist;
                    for (HomeListItems item : homelist.items) {
                        System.out.println("CLOUD ITEMS:    " + item.name + "    " + item.details + " " + item.pic_url);
                        id.add(item.id);
                        name.add(item.name);
                        pic_url.add(item.pic_url);
                        details.add(item.details);
                    }
                }
                else if (type.compareToIgnoreCase("newsletters") == 0) {
                    NewsletterPage page = gson.fromJson(dataFromServer, NewsletterPage.class);
                    NewsletterModel newsletters = page.newsletters;
                    System.out.println(page.newsletters);
                    for (NewsletterItems item : newsletters.items) {
                        System.out.println("CLOUD ITEMS:    " + item.name + "    " + item.details + " " + item.pic_url);
                        id.add(item.id);
                        name.add(item.name);
                        pic_url.add(item.pic_url);
                        details.add(item.details);
                    }
                }
                else if (type.compareToIgnoreCase("events") == 0) {
                    EventsPage page = gson.fromJson(dataFromServer, EventsPage.class);
                    EventsModel events = page.events;
                    System.out.println(page.events);
                    for (EventsItems item : events.items) {
                        System.out.println("CLOUD ITEMS:    " + item.name + "    " + item.details + " " + item.pic_url);
                        id.add(item.id);
                        name.add(item.name);
                        pic_url.add(item.pic_url);
                        details.add(item.details);
                    }
                }
                //SAVE TO SDCARD

                fileCache.downloadFile2SD(urlString, type + ".gson");
            }
            //CHECK FROM SDCARD
            else {
                FileCache fileCache = new FileCache(context);
                if (fileCache.isExternalStorageReadable()) {
                    //FileCache returns String from SDCARD
                    dataFromSDCard = fileCache.getGSONFromSDCache(type);
                    gson = new Gson();
                    if (type.compareToIgnoreCase("homelist") == 0) {
                        HomeListPage page = gson.fromJson(dataFromSDCard, HomeListPage.class);
                        HomeListModel l = page.homelist;
                        for (HomeListItems item : l.items) {
                            id.add(item.id);
                            name.add(item.name);
                            pic_url.add(item.pic_url);
                            details.add(item.details);

                        }
                    }
                    else if (type.compareToIgnoreCase("newsletters") == 0) {
                        NewsletterPage page = gson.fromJson(dataFromSDCard, NewsletterPage.class);
                        NewsletterModel newsletters = page.newsletters;
                        //System.out.println(page.newsletters);
                        for (NewsletterItems item : newsletters.items) {
                            System.out.println("SD ITEMS:    " + item.name + "    " + item.details + " " + item.pic_url);
                            id.add(item.id);
                            name.add(item.name);
                            pic_url.add(item.pic_url);
                            details.add(item.details);

                        }
                    }
                    else if (type.compareToIgnoreCase("events") == 0) {
                        EventsPage page = gson.fromJson(dataFromSDCard, EventsPage.class);
                        EventsModel events = page.events;
                        //System.out.println(page.newsletters);
                        for (EventsItems item : events.items) {
                            System.out.println("SD ITEMS:    " + item.name + "    " + item.details + " " + item.pic_url);
                            id.add(item.id);
                            name.add(item.name);
                            pic_url.add(item.pic_url);
                            details.add(item.details);

                        }
                    }
                }
            }

    }
}