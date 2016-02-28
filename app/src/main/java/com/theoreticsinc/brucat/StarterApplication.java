/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.theoreticsinc.brucat;

import android.app.Application;
import android.provider.Settings;

/**
 * Done Manually Because of the Manifest receivers
 * Notes to change for a new school
 * change Logo src found in:
 * 1.custom_actionbar.xml for android API14 or Greater
 * 2.dashboard.xml api10
 * 3.details.xml api10
 * 4.alerts.xml api10
 * 5.websitelink.xml api10
 * Server API Key AIzaSyBTKm0KfqSAC_B7vcyAmdhtciqbVs9U3G4
 * Sender ID 885178811503
 */
public class StarterApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    // Enable Local Datastore.
    //Parse.enableLocalDatastore(this);

    // Add your initialization code here
   // Parse.initialize(this);
    //Parse.initialize(this, "@string/parse_app_id", "@string/parse_client_key");
    //ParseInstallation.getCurrentInstallation().saveInBackground();

    String android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
/*    ParseInstallation.getCurrentInstallation().put("Unique_Id", android_id);
    ParseInstallation.getCurrentInstallation().saveInBackground();

    ParseUser.enableAutomaticUser();
    ParseACL defaultACL = new ParseACL();
    // Optionally enable public read access.
    // defaultACL.setPublicReadAccess(true);
    ParseACL.setDefaultACL(defaultACL, true);
    */
  }
}
