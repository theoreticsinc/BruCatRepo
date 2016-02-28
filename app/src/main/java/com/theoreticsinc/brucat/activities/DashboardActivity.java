package com.theoreticsinc.brucat.activities;

/**
 * Created by Angelo on 9/30/2015.
 */

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.theoreticsinc.brucat.R;
import com.theoreticsinc.brucat.utils.BadgeView;

//TargetApi(10)
public class DashboardActivity extends Activity implements PopupMenu.OnMenuItemClickListener {

    private static final int REQUEST_CODE = 0;
    private LinearLayout homeNav1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        homeNav1 = (LinearLayout) findViewById(R.id.homeNav1);
        homeNav1.setVisibility(View.INVISIBLE);

        //Mock Menu Button

        ImageButton menuButton = (ImageButton) findViewById(R.id.menuButton);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentapiVersion = android.os.Build.VERSION.SDK_INT;
                if (currentapiVersion < 10) {
                    openOptionsMenu();
                }
                else if (currentapiVersion >= 10) {
                    PopupMenu popupMenu = new PopupMenu(DashboardActivity.this, v);
                    popupMenu.setOnMenuItemClickListener(DashboardActivity.this);
                    popupMenu.inflate(R.menu.menu_main);
                    popupMenu.show();
                }
            }
        });

        //Main Dashboards Menus
/*
        View newsletterTarget = findViewById(R.id.newsletterTarget);
        final BadgeView newsletterBadge = new BadgeView(this, newsletterTarget);
        //newsletterBadge.setBackgroundResource(R.drawable.badge_ifaux);
        //newsletterBadge.setText("1");
        //newsletterBadge.show();

        ImageButton newsletterButton = (ImageButton) findViewById(R.id.newsletterTarget);
        newsletterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this, AlertsListActivity.class);
                startActivityForResult(i, REQUEST_CODE);
                //startActivity(i);
            }
        });

        ImageButton eventsTarget = (ImageButton) findViewById(R.id.eventsTarget);
        final BadgeView eventsBadge = new BadgeView(this, eventsTarget);
        //eventsBadge.setText("5");
        //eventsBadge.show();


        View calendarTarget = findViewById(R.id.calendarTarget);
        final BadgeView calendarBadge = new BadgeView(this, calendarTarget);
        //calendarBadge.setText("18");
        //calendarBadge.show();
        calendarTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this, CalendarActivity.class);
                startActivity(i);
            }
        });


        ImageButton alertTarget = (ImageButton) findViewById(R.id.alertTarget);
        final BadgeView alertBadge = new BadgeView(this, alertTarget);
        alertBadge.setText("4");
        //alertBadge.setText("New");
        //alertBadge.setTextColor(Color.BLUE);
        //alertBadge.setBadgeBackgroundColor(Color.YELLOW);
        alertBadge.setTextSize(12);
        alertBadge.show();

        //ImageButton alertButton = (ImageButton) findViewById(R.id.alertTarget);
        alertTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this, AlertsListActivity.class);
                startActivityForResult(i, REQUEST_CODE);
                //startActivity(i);
            }
        });
        */

/*
        Typeface face = new FontCache().get("fonts/" + "Bernardo Moda Bold.ttf", getApplicationContext());

        TextView dateToday = (TextView) findViewById(R.id.dateToday);
        TextView msg = (TextView) findViewById(R.id.dailyMsg);
        TextView author = (TextView) findViewById(R.id.author);

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        dateToday.setText("Today   " + sdf.format(new Date()));
        dateToday.setTypeface(face);

        msg.setText("The difference between school and life? In school, youre taught a lesson and then given a test.  In life, youre given a test that teaches you a lesson.");
        msg.setTypeface(face);
        author.setText("Tom Bodett");
        author.setTypeface(face);
        */
/*
        String strJson="{\"Employee\" :[ {\"id\":\"01\",\"name\":\"Gopal Varma\",\"salary\":\"500000\"}, {\"id\":\"02\",\"name\":\"Sairamkrishna\",\"salary\":\"500000\"}, {\"id\":\"03\",\"name\":\"Sathish kallakuri\",\"salary\":\"600000\"} ]}";
        String data = "";
        try {
            JSONObject jsonRootObject = new JSONObject(strJson);

            //Get the instance of JSONArray that contains JSONObjects
            JSONArray jsonArray = jsonRootObject.optJSONArray("Employee");

            //Iterate the jsonArray and print the info of JSONObjects
            for(int i=0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                int id = Integer.parseInt(jsonObject.optString("id").toString());
                String name = jsonObject.optString("name").toString();
                float salary = Float.parseFloat(jsonObject.optString("salary").toString());

                data += "Node"+i+" : \n id= "+ id +" \n Name= "+ name +" \n Salary= "+ salary +" \n ";
            }
            //msg.setText(data);
        } catch (JSONException e) {e.printStackTrace();}
*/

        //new BackgroundSave().execute(getApplicationContext());
        ImageView homeTarget = (ImageView) findViewById(R.id.Home);
        final BadgeView homeBadge = new BadgeView(this, homeTarget);
        homeBadge.setText("4");
        homeBadge.setTextSize(12);
        homeBadge.show();

        homeTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeNav1.setVisibility(View.VISIBLE);
                //Intent i = new Intent(DashboardActivity.this, AlertsListActivity.class);
                //startActivity(i);
            }
        });

        ImageView alertTarget = (ImageView) findViewById(R.id.Alerts);
        final BadgeView alertBadge = new BadgeView(this, alertTarget);
        alertBadge.setText("4");
        //alertBadge.setText("New");
        //alertBadge.setTextColor(Color.BLUE);
        //alertBadge.setBadgeBackgroundColor(Color.YELLOW);
        alertBadge.setTextSize(12);
        alertBadge.show();

        alertTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this, AlertsListActivity.class);
                startActivityForResult(i, REQUEST_CODE);

            }
        });
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("RESULT FROM OTHER ACTIVITY:" +requestCode + ":" + resultCode);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            if (data.hasExtra("returnKey1")) {
                //Toast.makeText(this, data.getExtras().getString("returnKey1"), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                       //DashboardActivity.super.onBackPressed();
                        finish();
                    }
                }).create().show();
    }

    @Override
    public void onResume() {
        super.onResume();
        /*
        new AlertDialog.Builder(this)
                .setTitle("Resume Home?")
                .setMessage("Press No to Reset")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        //DashboardActivity.super.onBackPressed();
                        //finish();
                    }
                }).create().show();
        */
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    public class BackgroundSave extends AsyncTask {
        public BackgroundSave(){
        }
        @Override
        protected Object doInBackground(Object... arg0) {
            try{
                String android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }
}
