package com.theoreticsinc.brucat.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.theoreticsinc.brucat.R;
import com.theoreticsinc.brucat.utils.GSONParser;
import com.theoreticsinc.brucat.utils.LazyAdapter;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * Created by Theoretics on 2/24/2016.
 */
public class EventsListFragment extends Fragment {

    ListView listView;
    LazyAdapter adapter;

    private LayoutInflater inflater;

    public EventsListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        int i = getArguments().getInt("SettingsItem");
        View rootView = null;

        rootView = inflater.inflate(R.layout.events_list, container,
                false);
        getActivity().setTitle("Settings");

        GSONParser gsonParser = new GSONParser();
        String configURL = "http://theoreticsinc.com/brucat/configuration.json";
        String defaultAlertsURL = "http://theoreticsinc.com/brucat/events.json";
        String alertsURL = "";
        try {
            //Read URL of Alerts List from a Config JSON in a server
            String URL = gsonParser.readConfig("events");

            if (null == URL) {
                //Read Alerts List from Default URL
                alertsURL = defaultAlertsURL;
            }
            else {
                alertsURL = URL;
            }
        }
        catch (Exception ex) {
            Log.e("AlertsListActivity", ex.getMessage());
        }

        //Process the ALERTS GSON
        try {
            gsonParser.processDataFromGSON(alertsURL, rootView.getContext(), "events");

            listView = (ListView)rootView.findViewById(R.id.listofevents);
            adapter = new LazyAdapter("events", getActivity(), gsonParser.pic_url, gsonParser.name, gsonParser.details);
            listView.setAdapter(adapter);
            List<String> details = gsonParser.details;
            final List<String> finalDetails = details;
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
                    System.out.println("Item Clicked");
                    TextView c = (TextView) v.findViewById(R.id.text);
                    String alertName = c.getText().toString();

                    FragmentManager fragmentManager = getFragmentManager();
                    Fragment newpost = new DetailsFragment();
                    Bundle args = new Bundle();

                    //Intent i = new Intent(AlertsListFragment.this, DetailsActivity.class);

                    ImageView listimage=(ImageView)v.findViewById(R.id.listimage);
                    Bitmap bitmap = ((BitmapDrawable)listimage.getDrawable()).getBitmap();

                    ByteArrayOutputStream bs = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, bs);
                    args.putByteArray("byteArray", bs.toByteArray());
                    args.putString("NAME", alertName);
                    args.putString("DETAILS", finalDetails.get(position));

                    newpost.setArguments(args);
                    fragmentManager.beginTransaction()
                            .replace(R.id.content_frame, newpost)
                            .addToBackStack(null)
                            .commit();
                    //startActivity(i);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        //Button b=(Button)findViewById(R.id.button1);
        //b.setOnClickListener(listener);

        return rootView;
    }

    public View.OnClickListener buttonListener=new View.OnClickListener(){
        @Override
        public void onClick(View arg0) {
            adapter.imageLoader.clearCache();
            adapter.notifyDataSetChanged();
            //finish();
        }
    };

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Do something that differs the Activity's menu here
        inflater.inflate(R.menu.alerts_menu, menu);
        //menu.findItem(R.id.action_refresh).setVisible(true);
        super.onCreateOptionsMenu(menu, inflater);
    }

}
