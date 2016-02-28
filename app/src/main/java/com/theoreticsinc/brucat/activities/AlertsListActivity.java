package com.theoreticsinc.brucat.activities;

/**
 * Created by Angelo on 9/27/2015.
 */

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.List;

import com.theoreticsinc.brucat.R;
import com.theoreticsinc.brucat.utils.GSONParser;
import com.theoreticsinc.brucat.utils.LazyAdapter;

@TargetApi(10)
public class AlertsListActivity extends Activity implements PopupMenu.OnMenuItemClickListener{

    ListView listView;
    LazyAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alerts_list);

        if(getResources().getBoolean(R.bool.portrait_only)){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        if ((getResources().getConfiguration().screenLayout &      Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE) {
            //Toast.makeText(this, "Large screen", Toast.LENGTH_SHORT).show();

        }
        else if ((getResources().getConfiguration().screenLayout &      Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
            //Toast.makeText(this, "Normal sized screen" , Toast.LENGTH_SHORT).show();

        }
        else if ((getResources().getConfiguration().screenLayout &      Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_SMALL) {
            //Toast.makeText(this, "Small sized screen" , Toast.LENGTH_SHORT).show();
        }
        else {
            //Toast.makeText(this, "Screen size is neither large, normal or small" , Toast.LENGTH_SHORT).show();
        }

        GSONParser gsonParser = new GSONParser();

        String configURL = "http://184.95.54.213/brucat/configuration.json";
        String defaultAlertsURL = "http://184.95.54.213/brucat/newsletters.json";
        String alertsURL = "";
        try {
            //Read URL of Alerts List from a Config JSON in a server
            String URL = gsonParser.readConfig("newsletters");

            if (null == URL) {
                //Read Alerts List from Default URL
                alertsURL = defaultAlertsURL;
            }
            else {
                alertsURL = URL;
            }
        }
        catch (Exception ex) {
            Log.e("AlertsListActivity",ex.getMessage());
        }

        //Process the ALERTS GSON
        try {
            gsonParser.processDataFromGSON(alertsURL, getApplicationContext(), "newsletters");

            listView = (ListView)findViewById(R.id.listofalerts);
            adapter = new LazyAdapter("newsletters", this, gsonParser.pic_url, gsonParser.name, gsonParser.details);
            listView.setAdapter(adapter);
            List<String> details = gsonParser.details;
            final List<String> finalDetails = details;
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
                System.out.println("Item Clicked");


                TextView c = (TextView) v.findViewById(R.id.text);
                String alertName = c.getText().toString();
                Toast.makeText(AlertsListActivity.this, alertName, Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(AlertsListActivity.this, DetailsActivity.class);

                    ImageView listimage=(ImageView)v.findViewById(R.id.listimage);
                    Bitmap bitmap = ((BitmapDrawable)listimage.getDrawable()).getBitmap();

                    ByteArrayOutputStream bs = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 75, bs);
                    i.putExtra("byteArray", bs.toByteArray());

                    i.putExtra("NAME", alertName);
                    i.putExtra("DETAILS", finalDetails.get(position));

                    startActivity(i);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        //Button b=(Button)findViewById(R.id.button1);
        //b.setOnClickListener(listener);

        ImageButton menuButton = (ImageButton) findViewById(R.id.menuButton2);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int currentapiVersion = android.os.Build.VERSION.SDK_INT;
                if (currentapiVersion < 10) {
                    openOptionsMenu();
                } else if (currentapiVersion >= 10) {
                    PopupMenu popupMenu = new PopupMenu(AlertsListActivity.this, v);
                    popupMenu.setOnMenuItemClickListener(AlertsListActivity.this);
                    popupMenu.inflate(R.menu.menu_main);
                    popupMenu.show();
                }
            }
        });


    }

    @Override
    public void onDestroy()
    {
        listView.setAdapter(null);
        super.onDestroy();
    }

    public OnClickListener listener=new OnClickListener(){
        @Override
        public void onClick(View arg0) {
            adapter.imageLoader.clearCache();
            adapter.notifyDataSetChanged();
            finish();
        }
    };

    private List<String> mStrings;

    @Override
    public void finish() {
        // Prepare data intent
        Intent data = new Intent();
        //data.putExtra("returnKey1", "Swinging on a star. ");
        //data.putExtra("returnKey2", "You could be better then you are. ");
        // Activity finished ok, return the data
        setResult(RESULT_OK, data);
        super.finish();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            adapter.imageLoader.clearCache();
            adapter.notifyDataSetChanged();
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}