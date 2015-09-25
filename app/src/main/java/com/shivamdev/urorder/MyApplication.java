package com.shivamdev.urorder;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by shivamchopra on 06/09/15.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, Constants.PARSE_USER, Constants.PARSE_KEY);
    }
}
